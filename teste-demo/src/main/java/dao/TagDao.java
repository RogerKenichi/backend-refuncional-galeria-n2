package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import interfaces.IHelperDao;
import model.Autor;
import model.Tag;
import database.Conexao_DB;

public class TagDao implements IHelperDao<Tag>{

	@Override
	public void insert(Tag obj) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
		        CALL sp_insert_tag (?, ?)
		      """)) {
		    statement.setInt(1, obj.getFkId());
		    statement.setString(2, obj.getTag());
		    int rowsInserted = statement.executeUpdate();
		    System.out.println("inserted to tb_tag");
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		conectar.FecharConexao(conexao);
	}

	@Override
	public void update(Tag obj) {
		// TODO Auto-generated method stub
				Conexao_DB conectar = new Conexao_DB();
				Connection conexao = conectar.AbrirConexao();
				
				try (PreparedStatement statement = conexao.prepareStatement("""
			            CALL sp_update_tag(?, ?, ?);
			        """)) {
					statement.setInt(1, obj.getId());
					statement.setInt(2, obj.getFkId());
					statement.setString(3, obj.getTag());
				    int rowsInserted = statement.executeUpdate();
		    		conectar.FecharConexao(conexao);
				    System.out.println("Updated tb_tag");
		    		
				} catch (SQLException se)
				{
					System.out.println(se.getMessage());
				}
	}

	@Override
	public void delete(Tag obj) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
		        CALL sp_delete_tag (?)
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
		Tag modelTag = new Tag();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
	            CALL sp_select_tag(?);
	        """)) {
			statement.setInt(1, id);
	    	ResultSet resultSet = statement.executeQuery();
    		conectar.FecharConexao(conexao);
	    	
	    	if(!resultSet.next()){
	    		return null;
	    	}

	    	modelTag.setId(id);
	    	modelTag.setFkId(resultSet.getInt("FK_tag_id"));
	    	modelTag.setTag(resultSet.getString("tag"));
    		
    		String autorJsonString = new Gson().toJson(modelTag);
	    	
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
		
		ArrayList<Tag> listaTags = new ArrayList<Tag>();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
	            CALL sp_select_tags();
	        """)) {
	    	ResultSet resultSet = statement.executeQuery();
    		conectar.FecharConexao(conexao);
	    	
	    	if(!resultSet.next()){
	    		return null;
	    	}
	    	
	    	    	
	    	do {
	    		Tag model = new Tag();
	    		model.setId(resultSet.getInt("SK_tag_id"));
	    		model.setFkId(resultSet.getInt("FK_tag_id"));
	    		model.setTag(resultSet.getString("tag"));	    		
	    		listaTags.add(model);
	    	} while (resultSet.next());
	    	
	    	String tagJsonString = new Gson().toJson(listaTags);
	    	return tagJsonString;
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		return null;
	}

	@Override
	public Tag findModel(int id) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		Tag modelTag = new Tag();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
	            CALL sp_select_tag(?);
	        """)) {
			statement.setInt(1, id);
	    	ResultSet resultSet = statement.executeQuery();
    		conectar.FecharConexao(conexao);
	    	
	    	if(!resultSet.next()){
	    		return null;
	    	}

	    	modelTag.setId(id);
	    	modelTag.setFkId(resultSet.getInt("FK_tag_id"));
	    	modelTag.setTag(resultSet.getString("tag"));
	    	
	    	return modelTag;
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		
		return null;
	}
	
}
