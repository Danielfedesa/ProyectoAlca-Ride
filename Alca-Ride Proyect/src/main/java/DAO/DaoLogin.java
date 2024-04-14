package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import controlador.ConexionDB;
import modelo.Login;

public class DaoLogin {

	
	//Método conectar
	public static Connection con = null;
	
	public DaoLogin() throws SQLException {
		this.con = ConexionDB.getConexion();
	}
	
	//Método insertar
	public void insertar(Login l) throws SQLException {
		String sql = "INSERT INTO Login (nombre_Usuario, pass, id_Usuario) VALUES (?, ?, ?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, l.getNombre_Usuario());
		ps.setString(2, l.getPass());
		ps.setInt(3, l.getId_Usuario()); //Le paso el id_Usuario recién creado
		
		int filas = ps.executeUpdate();
		
		ps.close();
	}
	
	//Método leer
	public Login leer(Login k) throws SQLException {
		String sql = "SELECT * FROM login WHERE nombre_Usuario = ? AND pass = ?";
				//+  k.getNombre_Usuario() + "'  AND pass = '" + k.getPass() +"'";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, k.getNombre_Usuario());
		ps.setString(2, k.getPass());
		
		ResultSet filas = ps.executeQuery(sql);
		
		Login objLogin = new Login();
		objLogin.setIs_Admin(false);// = true; //filas["is_Admin"];
				
		ps.close();
		
		return objLogin;
	}
	
}
