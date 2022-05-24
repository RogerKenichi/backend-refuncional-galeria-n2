package com.example.testedemo;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import javax.servlet.http.Part;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import model.Autor;
import dao.AutorDao;
import dao.JsonConversor;


@SpringBootApplication(scanBasePackages = "com.example")
//@RestController
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TesteDemoApplication.class);
	}
	
	@RequestMapping(value = "/teste")
	public ResponseEntity<String> Teste() {
		return ResponseEntity.ok("Olá prostiranhas");
	}
	// https://start.spring.io/
	// Rodar no cmd na pasta do projeto: mvn spring-boot:run 
	
	@RequestMapping(value = "/imagem")
	public void getImage(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		ServletContext cntx= request.getServletContext();
	      // Get the absolute path of the image
	      //String filename = cntx.getRealPath("/Imagens/Jailson_Mendes.png");
		 String[] listaStrings = {
			 "C:/Users/Roger/Pictures/FQuHDmGaIAAAlTB.jpg", //0
			 "C:/Users/Roger/Pictures/FQ_eRSBUYAIDcew.jpg", //1
			 "C:/Users/Roger/Pictures/FQ4puEmaUAAZL33.jpg", //2
			 "C:/Users/Roger/Pictures/Memes/Essex.png", //3
			 "C:/Users/Roger/Pictures/Jailson_Mendes.png", //4
			 "C:/Users/Roger/Pictures/Peter_Strasser.png", //5
			 "C:/Users/Roger/Pictures/Desenhos/Nep padoru.png", //6
			 "C:/Users/Roger/Pictures/Ezc2fvCUYAEyfk4.jpg", //7
			 "C:/Users/Roger/Pictures/Memes/unknown.png", // 8
			 "C:/Users/Roger/Pictures/Memes/hotel_yamato.png", //9
			 "C:/Users/Roger/Pictures/Memes/fala_portugues_alienígena_filho_da_puta.png", //10
			 "C:/Users/Roger/Pictures/Memes/tenrai_.jpg"  //11
		 };
		 
		 
		 
		 try {
			 Connection connection = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3306/teste",
				"root", "9989"
			);
			 
			 try (PreparedStatement statement = connection.prepareStatement("""
			            SELECT *
			            FROM tb_teste
			        """)) {
			    ResultSet resultSet = statement.executeQuery();
			    while (resultSet.next()) {
			        int val2 = resultSet.getInt("campo1");
			        System.out.println(val2);
			    }
			}
			 
			 connection.close();
		 }catch (SQLException se)
		 {
		 }
		 
		 
		 /* ESPAÇO RESERVADO PARA VERIFICAR NO BANCO DE DADOS*/
		 

	        System.out.println("Aooooooooooooooooooo");
		 
		 String filename = listaStrings[Integer.parseInt(request.getParameter("codigo"))];
	      String mime = cntx.getMimeType(filename);
	      if (mime == null) {
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        return;
	      }

	      response.setContentType(mime);
	      File file = new File(filename);
	      response.setContentLength((int)file.length());

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
	
	@PostMapping(value = "/imagem")
	public void postImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
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
	    response.setContentType("text/plain;charset=UTF-8");
	    response.getWriter().print("The file uploaded sucessfully.");
	}
	

	
	@RequestMapping(value = "/bancodedados")
	public ResponseEntity<String> BancoDeDados() {
		int val2 = 0;
		try {
			 Connection connection = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3306/teste",
				"root", "9989"
			);
			 
			 try (PreparedStatement statement = connection.prepareStatement("""
			            SELECT *
			            FROM tb_teste where campo1 = 5
			        """)) {
			    ResultSet resultSet = statement.executeQuery();
			    while (resultSet.next()) {
			        val2 = resultSet.getInt("campo1");
			        System.out.println(val2);
			    }
			}
			 
			 connection.close();
		 }catch (SQLException se)
		 {
		 }
		return ResponseEntity.ok(Integer.toString(val2));
	}
	
	@PostMapping(value = "/api/autor/teste")
	public void testeAutor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		String nome = request.getParameter("nome");
		System.out.println(nome);
	}
}
