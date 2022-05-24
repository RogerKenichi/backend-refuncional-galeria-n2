package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.AutorDao;
import model.Autor;

@RestController
public class AutorApi {
	// POST AUTOR CADASTRAR
		@PostMapping(value = "/api/autor/cadastrar")
		public void insertAutor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
		{
			Autor autor = new Autor();
			autor.setName(request.getParameter("nome"));
			autor.setDescricao(request.getParameter("descricao"));
			autor.setCodImgPreview(Integer.parseInt(request.getParameter("cod_img_preview")));
			
			AutorDao autordao = new AutorDao();
			autordao.insert(autor);
			
			response.getWriter().append("Entrou: ").append(request.getContextPath()).append(" - ").append(String.valueOf(new Date()));
		
			/*response.setContentType("application/json");
			// Get the printwriter object from response to write the required json object to the output stream      
			PrintWriter out = response.getWriter();
			// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
			out.print(jsonObject);
			out.flush();*/
		}
		
		// POST AUTOR UPDATE
		@PostMapping(value = "/api/autor/update")
		public void updateAutor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
		{
			int codigoAutor = Integer.parseInt(request.getParameter("id"));
			String nomeAutor = request.getParameter("nome");
			String descricaoAutor = request.getParameter("descricao");
			int codImgPreview = Integer.parseInt(request.getParameter("cod_img_preview"));
			
			Autor autor = new Autor();	
			AutorDao autordao = new AutorDao();
			
			autor = autordao.findModel(codigoAutor);
			
			if(!nomeAutor.isEmpty())
				autor.setName(nomeAutor);
			if(!descricaoAutor.isEmpty())
				autor.setDescricao(descricaoAutor);
			if(codImgPreview > 0)
				autor.setCodImgPreview(codImgPreview);
			
			autordao.update(autor);
			response.getWriter().append("Atualizado: ").append(request.getContextPath()).append(" - ").append(String.valueOf(new Date()));
		}
		
		// GET AUTOR ENCONTRAR TODOS
		@RequestMapping(value = "/api/autor/delete")
		public void deleteAutor(HttpServletRequest request, HttpServletResponse response) throws IOException
		{
			int id_autor = Integer.parseInt(request.getParameter("id"));
			
			Autor autor = new Autor();
			autor.setId(id_autor);
			
			AutorDao autordao = new AutorDao();
			autordao.delete(autor);
		}
		
		// GET AUTOR ENCONTRAR
		@RequestMapping(value = "/api/autor/find")
		public void findAutor(HttpServletRequest request, HttpServletResponse response) throws IOException
		{
			// Precisa mudar o retorno para JSON;
			int id_autor = Integer.parseInt(request.getParameter("id"));
			
			AutorDao autordao = new AutorDao();
			String resposta = autordao.find(id_autor);

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
		
			if(resposta == null)
				out.print("A tabela de autores está vazia");
			else
				out.print(resposta);

			out.flush();
		}
		
		// GET AUTOR ENCONTRAR TODOS
		@RequestMapping(value = "/api/autor/findall")
		public void findAllAutor(HttpServletRequest request, HttpServletResponse response) throws IOException
		{
			AutorDao autordao = new AutorDao();
			String resposta = autordao.findAll();

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			if(resposta == null)
				out.print("A tabela de autores está vazia");
			else
				out.print(resposta);
			
			out.flush();
		}
}