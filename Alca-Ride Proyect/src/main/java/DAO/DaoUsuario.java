package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

import controlador.ConexionDB;
import modelo.Usuario;

public class DaoUsuario {

	
	//Método para conectar
	public static Connection con = null;
	public static Object getInstance;
	
	public DaoUsuario() throws SQLException {
		this.con = ConexionDB.getConexion();
	}
	
	//Método para insertar usuarios.
	public int insertar(Usuario u) throws SQLException {
		String sql = "INSERT INTO Usuario (nombre, apellido1, apellido2, telefono, email, dni, carnet, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		int id = 0;
		
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//Importo java.sql. Statement.RETURN_GENERATED_KEYS para recuperar el id_Usuario
		ps.setString(1, u.getNombre());
		ps.setString(2, u.getApellido1());
		ps.setString(3, u.getApellido2());
		ps.setInt(4, u.getTelefono());
		ps.setString(5, u.getEmail());
		ps.setString(6, u.getDni());
		ps.setString(7, u.getCarnet());
		ps.setString(8, u.getDireccion());
		
		
		int filas = ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		
			ps.close();
			
			return id;
	}
	
	//Método para listar usuarios.
	//Creo un arraylist de la clase Usuario y le llamo listar.
	public ArrayList<Usuario> listar() throws SQLException { //Importo
		//Hago el prepareStatement pero esta vez de vuelta, lo haré con un select.
		String sql = "SELECT * FROM usuario";
		PreparedStatement ps = con.prepareStatement(sql);
		//Almaceno en un objeto tipo ResultSet que es el que utiliza MySQL para almacenar esa colección sin objetos, sino con los datos que recibe la BD
		//Dado que quiero recibir datos utilizo el executeQuery
		ResultSet rs = ps.executeQuery();
		
		//Hago el Arraylist:
		ArrayList<Usuario> result = null;
		
		//Quiero que el ArrayList contenga los objetos de tipo Usuario para trabajar con ellos en mi modelo de objetos.
		while(rs.next()) {
			//Pregunto si el array sigue null:
			if(result == null) {
				result = new ArrayList<Usuario>();
			}
			//Añado al result los elementos que obtengo de la BD definiendo los tipos de dato que necesito recuperar.
			result.add(new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
		}
		return result;
		
	}
	
	
	//Hago un método que obtiene los elementos y los convierte en json
		public String listarJson() throws SQLException {
			String json = "";
			Gson objetoGson = new Gson();
			//Meto en el objeto json lo que genere el objetoGson con el método toJson (lo convierte a json)
			json = objetoGson.toJson(this.listar());
			return json;
		}
	
	
	
	
}
