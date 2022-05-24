package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import interfaces.IHelperDao;
import model.AutorMidiaSocial;
import database.Conexao_DB;

public class AutorMidiaSocialDao implements IHelperDao<AutorMidiaSocial>{

	@Override
	public void insert(AutorMidiaSocial obj) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
		        CALL sp_insert_autormidiasocial (?, ?, ?, ?)
		      """)) {
		    statement.setInt(1, obj.getAutorId());
		    statement.setInt(2, obj.getMidiaSocialId());
		    statement.setString(3, obj.getLink());
		    statement.setString(4, obj.getTag());
		    int rowsInserted = statement.executeUpdate();
		    System.out.println("inserted to tb_autor_midia_social");
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		conectar.FecharConexao(conexao);
	}

	@Override
	public void update(AutorMidiaSocial obj) {
		// TODO Auto-generated method stub
				Conexao_DB conectar = new Conexao_DB();
				Connection conexao = conectar.AbrirConexao();
				
				try (PreparedStatement statement = conexao.prepareStatement("""
			            CALL sp_update_autormidiasocial(?, ?, ?, ?);
			        """)) {
				    statement.setInt(1, obj.getAutorId());
				    statement.setInt(2, obj.getMidiaSocialId());
				    statement.setString(3, obj.getLink());
				    statement.setString(4, obj.getTag());
				    int rowsInserted = statement.executeUpdate();
		    		conectar.FecharConexao(conexao);
				    System.out.println("Updated tb_autor_midia_social");
		    		
				} catch (SQLException se)
				{
					System.out.println(se.getMessage());
				}
	}

	@Override
	public void delete(AutorMidiaSocial obj) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
		        CALL sp_delete_autormidiasocial (?, ?)
		      """)) {
		    statement.setInt(1, obj.getAutorId());
		    statement.setInt(2, obj.getMidiaSocialId());
		    int rowsInserted = statement.executeUpdate();
		    System.out.println("deleted from tb_autor_midia_social");
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
		ArrayList<AutorMidiaSocial> lista = new ArrayList<AutorMidiaSocial>();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
	            CALL sp_select_autormidiasocial(?);
	        """)) {
	    	ResultSet resultSet = statement.executeQuery();
    		conectar.FecharConexao(conexao);
	    	
	    	if(!resultSet.next()){
	    		return null;
	    	}
	    	    	
	    	do {
	    		AutorMidiaSocial model = new AutorMidiaSocial();
		    	model.setAutorId(id);
		    	model.setMidiaSocialId(resultSet.getInt("FK_midia_social_tipo_id"));
		    	model.setLink(resultSet.getString("midia_social_link"));
		    	model.setTag(resultSet.getString("midia_social_tag"));		
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
	public String findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AutorMidiaSocial findModel(int id) {
		// TODO Auto-generated method stub
		Conexao_DB conectar = new Conexao_DB();
		Connection conexao = conectar.AbrirConexao();
		AutorMidiaSocial model = new AutorMidiaSocial();
		
		try (PreparedStatement statement = conexao.prepareStatement("""
	            CALL sp_select_autormidiasocial(?);
	        """)) {
			statement.setInt(1, id);
	    	ResultSet resultSet = statement.executeQuery();
    		conectar.FecharConexao(conexao);
	    	
	    	if(!resultSet.next()){
	    		return null;
	    	}

	    	model.setAutorId(id);
	    	model.setMidiaSocialId(resultSet.getInt("FK_midia_social_tipo_id"));
	    	model.setLink(resultSet.getString("midia_social_link"));
	    	model.setTag(resultSet.getString("midia_social_tag"));	
	    	
	    	return model;
		} catch (SQLException se)
		{
			System.out.println(se.getMessage());
		}
		
		return null;
	}
	
}
