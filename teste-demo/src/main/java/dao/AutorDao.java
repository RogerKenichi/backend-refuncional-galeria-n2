package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import interfaces.IHelperDao;
import model.Autor;
import database.Conexao_DB;

public class AutorDao implements IHelperDao<Autor>{

	@Override
	public void insert(Autor obj) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
		        CALL sp_insert_autor (?, ?, ?)
		      """)) {
		    statement.setString(1, obj.getName());
		    statement.setString(2, obj.getDescricao());
		    statement.setInt(3, obj.getCodImgPreview());
		    int rowsInserted = statement.executeUpdate();
		    System.out.println("inserted to tb_autor");
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		conectar.FecharConexao(conexao);
	}

	@Override
	public void update(Autor obj) {
		// TODO Auto-generated method stub
				Conexao_DB conectar = new Conexao_DB();
				Connection conexao = conectar.AbrirConexao();
				
				try (PreparedStatement statement = conexao.prepareStatement("""
			            CALL sp_update_autor(?, ?, ?, ?);
			        """)) {
					statement.setInt(1, obj.getId());
					statement.setString(2, obj.getName());
					statement.setString(3, obj.getDescricao());
					statement.setInt(4, obj.getCodImgPreview());
				    int rowsInserted = statement.executeUpdate();
		    		conectar.FecharConexao(conexao);
				    System.out.println("Updated tb_autor");
		    		
				} catch (SQLException se)
				{
					System.out.println(se.getMessage());
				}
	}

	@Override
	public void delete(Autor obj) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
		        CALL sp_delete_autor (?)
		      """)) {
		    statement.setInt(1, obj.getId());
		    int rowsInserted = statement.executeUpdate();
		    System.out.println("deleted from tb_autor");
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		conectar.FecharConexao(conexao);
	}

	@Override
	public String find(int id) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		Autor modelAutor = new Autor();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
	            CALL sp_select_autor(?);
	        """)) {
			statement.setInt(1, id);
	    	ResultSet resultSet = statement.executeQuery();
    		conectar.FecharConexao(conexao);
	    	
	    	if(!resultSet.next()){
	    		return null;
	    	}

	    	modelAutor.setId(id);
    		modelAutor.setName(resultSet.getString("autor_nome"));
    		modelAutor.setDescricao(resultSet.getString("autor_descricao"));
    		modelAutor.setCodImgPreview(resultSet.getInt("cod_autor_img_preview"));
    		
    		String autorJsonString = new Gson().toJson(modelAutor);
	    	
	    	return autorJsonString;
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		
		return null;
	}

	@Override
	public String findAll() {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		
		ArrayList<Autor> listaAutores = new ArrayList<Autor>();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
	            CALL sp_select_autores();
	        """)) {
	    	ResultSet resultSet = statement.executeQuery();
    		conectar.FecharConexao(conexao);
	    	
	    	if(!resultSet.next()){
	    		return null;
	    	}
	    	
	    	    	
	    	do {
	    		Autor model = new Autor();
	    		model.setId(resultSet.getInt("SK_autor_id"));
	    		model.setName(resultSet.getString("autor_nome"));
	    		model.setDescricao(resultSet.getString("autor_descricao"));	  
	    		model.setCodImgPreview(resultSet.getInt("cod_autor_img_preview"));	   		
	    		listaAutores.add(model);
	    	} while (resultSet.next());
	    	
	    	String autorJsonString = new Gson().toJson(listaAutores);
	    	return autorJsonString;
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		return null;
	}

	@Override
	public Autor findModel(int id) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		Autor modelAutor = new Autor();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
	            CALL sp_select_autor(?);
	        """)) {
			statement.setInt(1, id);
	    	ResultSet resultSet = statement.executeQuery();
    		conectar.FecharConexao(conexao);
	    	
	    	if(!resultSet.next()){
	    		return null;
	    	}

	    	modelAutor.setId(id);
    		modelAutor.setName(resultSet.getString("autor_nome"));
    		modelAutor.setDescricao(resultSet.getString("autor_descricao"));  
    		modelAutor.setCodImgPreview(resultSet.getInt("cod_autor_img_preview"));
	    	
	    	return modelAutor;
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		
		return null;
	}
	
}
