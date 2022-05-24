package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao_DB {
	
	public Connection AbrirConexao()
	{
		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/galeria_n2",
					"root", "9989");
			return connection;
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}
	}
	
	public void FecharConexao(Connection conexao)
	{
		try {
			conexao.close();
		} catch (SQLException se) {
			System.out.println(se.getMessage());			
		}
	}
}
