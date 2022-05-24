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

import dao.TagDao;
import model.Tag;

@RestController
public class TagApi {

	/*public static void main(String[] args) {
		SpringApplication.run(TesteDemoApplication.class, args);
	}*/

	// POST TAG CADASTRAR
	@PostMapping(value = "/api/tag/cadastrar")
	public void insertTag(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Tag tag = new Tag();
		tag.setFkId(Integer.parseInt(request.getParameter("FK_tag_id")));
		tag.setTag(request.getParameter("tag"));

		TagDao tagdao = new TagDao();
		tagdao.insert(tag);

		response.getWriter().append("Entrou: ").append(request.getContextPath()).append(" - ")
				.append(String.valueOf(new Date()));
	}

	// POST TAG UPDATE
	@PostMapping(value = "/api/tag/update")
	public void updateAutor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int codTag = Integer.parseInt(request.getParameter("id"));
		int fkId = Integer.parseInt(request.getParameter("FK_tag_id"));
		String tagdescricao = request.getParameter("tag");
		
		Tag tag = new Tag();
		TagDao tagDao = new TagDao();
		
		tag = tagDao.findModel(codTag);

		if (fkId > 0)
			tag.setFkId(fkId);
		if (!tagdescricao.isEmpty())
			tag.setTag(tagdescricao);

		tagDao.update(tag);
		response.getWriter().append("Atualizado: ").append(request.getContextPath()).append(" - ")
				.append(String.valueOf(new Date()));
	}

	// GET TAG ENCONTRAR TODOS
	@RequestMapping(value = "/api/tag/delete")
	public void deleteAutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Tag model = new Tag();
		model.setId(id);

		TagDao dao = new TagDao();
		dao.delete(model);
	}

	// GET TAG ENCONTRAR
	@RequestMapping(value = "/api/tag/find")
	public void findAutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Precisa mudar o retorno para JSON;
		int id = Integer.parseInt(request.getParameter("id"));

		TagDao dao = new TagDao();
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
	@RequestMapping(value = "/api/tag/findall")
	public void findAllAutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TagDao dao = new TagDao();
		String resposta = dao.findAll();

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (resposta == null)
			out.print("A tabela de tags está vazia");
		else
			out.print(resposta);

		out.flush();
	}
}