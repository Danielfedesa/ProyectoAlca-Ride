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
		String sql = "INSERT INTO login (nombre_Usuario, pass, id_Usuario) VALUES (?, ?, ?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, l.getNombre_Usuario());
		ps.setString(2, l.getPass());
		ps.setInt(3, l.getId_Usuario()); //Le paso el id_Usuario recién creado
		
		int filas = ps.executeUpdate();
		
		ps.close();
	}
	
	//Método leer
	public Login leerLogin(Login k) throws SQLException {
		String sql = "SELECT id_Login, nombre_Usuario, is_Admin FROM login WHERE nombre_Usuario = ? AND pass = ?";
		
		//Variables para almacenar el nombre y password que le pasamos por el formulario.
		String nombreUs = k.getNombre_Usuario();
		String passUs = k.getPass();		
		
		//Creamos la sentrencia para consultar en BDD por nombre_Usuario y is_Admin
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, nombreUs);
		ps.setString(2, passUs);
		
		//Recogemos el resultado en la variable filas del ResultSet
		ResultSet filas = ps.executeQuery();
		//Creamos objeto de la clase Login
		Login objLogin = new Login();
		//Inicializamos el objLogin con el nombre_Usuario vacío para validar luego si existe o no
		objLogin.setNombre_Usuario("");
		//Leemos el ResultSet mediante un while
		while(filas.next())
		{			
			objLogin.setId_Login(filas.getInt("id_Login"));
			objLogin.setNombre_Usuario(filas.getString("nombre_Usuario"));
			objLogin.setIs_Admin(filas.getBoolean("is_Admin"));// = true; //filas["is_Admin"];	
		}
						
		ps.close();
		
		//Devolvemos el objLogin relleno o vacío en su caso
		return objLogin;
	}
	
	//Método eliminar
		public static void eliminarLog(Login e) throws SQLException {
			String sql = "DELETE FROM login WHERE id_Usuario = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, e.getId_Usuario());
			
			int filas = ps.executeUpdate();
			
			ps.close();
		}
	
}
