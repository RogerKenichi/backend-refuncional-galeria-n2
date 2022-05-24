package com.example.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.ImagemDao;
import model.Imagem;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
public class ImagemApi {

	/*
	 * public static void main(String[] args) {
	 * SpringApplication.run(TesteDemoApplication.class, args); }
	 */

	// POST IMAGEM CADASTRAR
	@PostMapping(value = "/api/imagem/cadastrar")
	public void insertImagem(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		Part filePart = request.getPart("file");
	    String fileName = filePart.getSubmittedFileName();
	    String filePathString = "C:\\Users\\Roger\\Desktop\\java-uploads\\";
	    
	    
	    File f = new File(filePathString + fileName);
	    if(f.exists() && !f.isDirectory()) {
	    	String fileExtension = fileName.substring(fileName.indexOf("."));
	    	fileName = fileName.substring(0, fileName.indexOf("."));
	        fileName = fileName + "_" + fileExtension;
	    }
	    
	    for (Part part : request.getParts()) {
	      part.write(filePathString + fileName);
	    }
	    
	    String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	    System.out.println(timeStamp);
	    
		Imagem model = new Imagem();
		model.setImgPath(filePathString + fileName);
		model.setAutorId(Integer.parseInt(request.getParameter("autor_id")));
		model.setDescricao(request.getParameter("img_descricao"));
		//Date date = Date.valueOf(request.getParameter("data_envio"));
		Date date = Date.valueOf(timeStamp);
		model.setDataEnvio(date);

		ImagemDao dao = new ImagemDao();
		dao.insert(model);
		
	    response.setContentType("text/plain;charset=UTF-8");
	    response.getWriter().print("The file uploaded sucessfully.");
	}

	// POST IMAGEM UPDATE
	@PostMapping(value = "/api/imagem/update")
	public void updateAutor(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		int fkAutorId = Integer.parseInt(request.getParameter("FK_autor_id"));
		String descricao = request.getParameter("img_descricao");

		Imagem model = new Imagem();
		ImagemDao dao = new ImagemDao();

		model = dao.findModel(id);

		if (fkAutorId > 0)
			model.setAutorId(fkAutorId);
		if (!descricao.isEmpty())
			model.setDescricao(descricao);

		dao.update(model);
		response.getWriter().append("Atualizado: ");
	}

	// GET IMAGEM ARQUIVO
	@RequestMapping(value = "/api/imagem")
	public void getImagemArquivo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Imagem model = new Imagem();
		ImagemDao dao = new ImagemDao();
		model = dao.findModel(id);

		ServletContext cntx = request.getServletContext();
		String mime = cntx.getMimeType(model.getImgPath());
		if (mime == null) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		response.setContentType(mime);
		File file = new File(model.getImgPath());
		response.setContentLength((int) file.length());

		FileInputStream in = new FileInputStream(file);
		OutputStream out = response.getOutputStream();

		// Copy the contents of the file to the output stream
		byte[] buf = new byte[1024];
		int count = 0;
		while ((count = in.read(buf)) >= 0) {
			out.write(buf, 0, count);
		}
		out.close();
		in.close();
	}

	// GET IMAGEM ENCONTRAR TODOS
	@RequestMapping(value = "/api/imagem/delete")
	public void deleteAutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Imagem model = new Imagem();
		model.setId(id);

		ImagemDao dao = new ImagemDao();
		dao.delete(model);
	}

	// GET IMAGEM ENCONTRAR
	@RequestMapping(value = "/api/imagem/find")
	public void findAutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Precisa mudar o retorno para JSON;
		int id = Integer.parseInt(request.getParameter("id"));

		ImagemDao dao = new ImagemDao();
		String resposta = dao.find(id);

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (resposta == null)
			out.print("A tabela de imagens está vazia");
		else
			out.print(resposta);

		out.flush();
	}

	// GET IMAGEM ENCONTRAR TODOS
	@RequestMapping(value = "/api/imagem/findall")
	public void findAllAutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ImagemDao dao = new ImagemDao();
		String resposta = dao.findAll();

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (resposta == null)
			out.print("A tabela de imagens está vazia");
		else
			out.print(resposta);

		out.flush();
	}
}