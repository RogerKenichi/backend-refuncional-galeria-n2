package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import interfaces.IHelperDao;
import model.Imagemxtags;
import database.Conexao_DB;

public class ImagemxtagsDao implements IHelperDao<Imagemxtags>{

	@Override
	public void insert(Imagemxtags obj) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
		        CALL sp_insert_imagemxtags (?, ?)
		      """)) {
		    statement.setInt(1, obj.getImagemId());
		    statement.setInt(2, obj.getTagId());
		    int rowsInserted = statement.executeUpdate();
		    System.out.println("inserted to tb_imagemxtags");
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		conectar.FecharConexao(conexao);
	}

	@Override
	public void update(Imagemxtags obj) {
		// TODO Auto-generated method stub
				Conexao_DB conectar = new Conexao_DB();
				Connection conexao = conectar.AbrirConexao();
				
				try (PreparedStatement statement = conexao.prepareStatement("""
			            CALL sp_update_imagemxtags(?, ?);
			        """)) {
					statement.setInt(1, obj.getImagemId());
					statement.setInt(2, obj.getTagId());
				    int rowsInserted = statement.executeUpdate();
		    		conectar.FecharConexao(conexao);
				    System.out.println("Updated tb_imagemxtags");
		    		
				} catch (SQLException se)
				{
					System.out.println(se.getMessage());
				}
	}

	@Override
	public void delete(Imagemxtags obj) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
		        CALL sp_delete_imagemxtags (?, ?)
		      """)) {
		    statement.setInt(1, obj.getImagemId());
		    statement.setInt(2, obj.getTagId());
		    int rowsInserted = statement.executeUpdate();
		    System.out.println("deleted from tb_imagemxtags");
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
		ArrayList<Imagemxtags> listaTags = new ArrayList<Imagemxtags>();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
	            CALL sp_select_imagemxtags();
	        """)) {
	    	ResultSet resultSet = statement.executeQuery();
    		conectar.FecharConexao(conexao);
	    	
	    	if(!resultSet.next()){
	    		return null;
	    	}
	    	
	    	    	
	    	do {
	    		Imagemxtags model = new Imagemxtags();
	    		model.setImagemId(id);
	    		model.setTagId(resultSet.getInt("FK_tag_id"));  		
	    		listaTags.add(model);
	    	} while (resultSet.next());
	    	
	    	String jsonString = new Gson().toJson(listaTags);
	    	return jsonString;
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		return null;
	}

	@Override
	public String findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Imagemxtags findModel(int id) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		Imagemxtags model = new Imagemxtags();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
	            CALL sp_select_imagemxtags_especifico(?, ?);
	        """)) {
			statement.setInt(1, id);
	    	ResultSet resultSet = statement.executeQuery();
    		conectar.FecharConexao(conexao);
	    	
	    	if(!resultSet.next()){
	    		return null;
	    	}

	    	model.setImagemId(id);
	    	model.setTagId(resultSet.getInt("FK_tag_id"));
	    	
	    	return model;
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		
		return null;
	}
	
}
