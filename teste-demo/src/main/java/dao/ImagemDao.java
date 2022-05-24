package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import interfaces.IHelperDao;
import model.Imagem;
import database.Conexao_DB;

public class ImagemDao implements IHelperDao<Imagem>{

	@Override
	public void insert(Imagem obj) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
		        CALL sp_insert_imagem (?, ?, ?, ?)
		      """)) {
		    statement.setInt(1, obj.getAutorId());
		    statement.setString(2, obj.getImgPath());
		    statement.setString(3, obj.getDescricao());
		    statement.setDate(4, obj.getDataEnvio());
		    int rowsInserted = statement.executeUpdate();
		    System.out.println("inserted to tb_imagem");
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		conectar.FecharConexao(conexao);
	}

	@Override
	public void update(Imagem obj) {
		// TODO Auto-generated method stub
				Conexao_DB conectar = new Conexao_DB();
				Connection conexao = conectar.AbrirConexao();
				
				try (PreparedStatement statement = conexao.prepareStatement("""
			            CALL sp_update_imagem(?, ?, ?, ?, ?);
			        """)) {
				    statement.setInt(1, obj.getAutorId());
				    statement.setString(2, obj.getImgPath());
				    statement.setString(3, obj.getDescricao());
				    statement.setDate(4, obj.getDataEnvio());
				    int rowsInserted = statement.executeUpdate();
		    		conectar.FecharConexao(conexao);
				    System.out.println("Updated tb_imagem");
		    		
				} catch (SQLException se)
				{
					System.out.println(se.getMessage());
				}
	}

	@Override
	public void delete(Imagem obj) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
		        CALL sp_delete_imagem (?)
		      """)) {
		    statement.setInt(1, obj.getId());
		    int rowsInserted = statement.executeUpdate();
		    System.out.println("deleted from tb_imagem");
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
		Imagem model = new Imagem();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
	            CALL sp_select_imagem(?);
	        """)) {
			statement.setInt(1, id);
	    	ResultSet resultSet = statement.executeQuery();
    		conectar.FecharConexao(conexao);
	    	
	    	if(!resultSet.next()){
	    		return null;
	    	}

	    	model.setId(id);
	    	model.setAutorId(resultSet.getInt("FK_autor_id"));
	    	model.setImgPath(resultSet.getString("img_path"));
	    	model.setDescricao(resultSet.getString("img_descricao"));
	    	model.setDataEnvio(resultSet.getDate("data_envio"));
    		
    		String jsonString = new Gson().toJson(model);
	    	
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
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		
		ArrayList<Imagem> lista = new ArrayList<Imagem>();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
	            CALL sp_select_imagens();
	        """)) {
	    	ResultSet resultSet = statement.executeQuery();
    		conectar.FecharConexao(conexao);
	    	
	    	if(!resultSet.next()){
	    		return null;
	    	}

	    	    	
	    	do {
	    		Imagem model = new Imagem();
		    	model.setId(resultSet.getInt("SK_imagem_id"));
		    	model.setAutorId(resultSet.getInt("FK_autor_id"));
		    	model.setImgPath(resultSet.getString("img_path"));
		    	model.setDescricao(resultSet.getString("img_descricao"));
		    	model.setDataEnvio(resultSet.getDate("data_envio"));   		
	    		lista.add(model);
	    	} while (resultSet.next());
	    	
	    	String jsonString = new Gson().toJson(lista);
	    	return jsonString;
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		return null;
	}

	@Override
	public Imagem findModel(int id) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		Imagem model = new Imagem();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
	            CALL sp_select_imagem(?);
	        """)) {
			statement.setInt(1, id);
	    	ResultSet resultSet = statement.executeQuery();
    		conectar.FecharConexao(conexao);
	    	
	    	if(!resultSet.next()){
	    		return null;
	    	}

	    	model.setId(id);
	    	model.setAutorId(resultSet.getInt("FK_autor_id"));
	    	model.setImgPath(resultSet.getString("img_path"));
	    	model.setDescricao(resultSet.getString("img_descricao"));
	    	model.setDataEnvio(resultSet.getDate("data_envio"));   	
	    	
	    	return model;
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		
		return null;
	}
	
}
