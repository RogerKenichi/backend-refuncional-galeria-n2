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

import dao.AutorMidiaSocialDao;
import model.AutorMidiaSocial;

@RestController
public class AutorMidiaSocialApi {

	/*public static void main(String[] args) {
		SpringApplication.run(TesteDemoApplication.class, args);
	}*/

	// POST TAG CADASTRAR
	@PostMapping(value = "/api/autormidiasocial/cadastrar")
	public void insertTag(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		AutorMidiaSocial model = new AutorMidiaSocial();
		model.setAutorId(Integer.parseInt(request.getParameter("FK_tag_id")));
		model.setMidiaSocialId(Integer.parseInt(request.getParameter("FK_midia_social_tipo_id")));
		model.setTag(request.getParameter("tag"));

		AutorMidiaSocialDao dao = new AutorMidiaSocialDao();
		dao.insert(model);

		response.getWriter().append("Entrou: ").append(request.getContextPath()).append(" - ")
				.append(String.valueOf(new Date()));
	}

	// POST TAG UPDATE
	@PostMapping(value = "/api/autormidiasocial/update")
	public void updateAutor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int codAutor = Integer.parseInt(request.getParameter("id"));
		int codRedeSocial = Integer.parseInt(request.getParameter("FK_midia_social_tipo_id"));
		String link = request.getParameter("midia_social_link");
		String tag = request.getParameter("midia_social_tag");
		
		AutorMidiaSocial model = new AutorMidiaSocial();
		AutorMidiaSocialDao dao = new AutorMidiaSocialDao();
		
		model = dao.findModel(codAutor);

		if (codRedeSocial > 0)
			model.setMidiaSocialId(codRedeSocial);
		if (!link.isEmpty())
			model.setLink(link);
		if (!tag.isEmpty())
			model.setTag(tag);

		dao.update(model);
		response.getWriter().append("Atualizado: ").append(request.getContextPath()).append(" - ")
				.append(String.valueOf(new Date()));
	}

	// GET TAG DELETAR TODOS
	@RequestMapping(value = "/api/autormidiasocial/delete")
	public void deleteAutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		AutorMidiaSocial model = new AutorMidiaSocial();
		model.setAutorId(id);
		model.setMidiaSocialId(Integer.parseInt(request.getParameter("FK_midia_social_tipo_id")));

		AutorMidiaSocialDao dao = new AutorMidiaSocialDao();
		dao.delete(model);
	}

	// GET TAG ENCONTRAR
	@RequestMapping(value = "/api/autormidiasocial/find")
	public void findAutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Precisa mudar o retorno para JSON;
		int id = Integer.parseInt(request.getParameter("id"));

		AutorMidiaSocialDao dao = new AutorMidiaSocialDao();
		String resposta = dao.find(id);

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (resposta == null)
			out.print("A tabela de tags está vazia");
		else
			out.print(resposta);

		out.flush();
	}

	// GET TAG ENCONTRAR TODOS
	@RequestMapping(value = "/api/autormidiasocial/findall")
	public void findAllAutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}
}