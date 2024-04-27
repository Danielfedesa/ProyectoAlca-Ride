package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

import controlador.ConexionDB;
import modelo.Login;
import modelo.Usuario;
import modelo.UsuarioViewModel;

public class DaoUsuario {

	
	//Método para conectar
	public static Connection con = null;
	public static Object getInstance;
	
	public DaoUsuario() throws SQLException {
		this.con = ConexionDB.getConexion();
	}
	
	//Método para insertar usuarios.
	public int insertar(Usuario u) throws SQLException {
		String sql = "INSERT INTO usuarios (nombre, apellido1, apellido2, telefono, email, dni, carnet, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
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
	//Creo un arraylist de la clase UsuarioViewModel (nombre_Usuario) y le llamo listar.
	public ArrayList<UsuarioViewModel> listar() throws SQLException { //Importo
		//Hago el prepareStatement pero esta vez de vuelta, lo haré con un select.
		String sql = "SELECT usuarios.*, login.nombre_Usuario AS nombre_Usuario FROM usuarios INNER JOIN login ON usuarios.id_Usuario = login.id_Usuario";
		PreparedStatement ps = con.prepareStatement(sql);
		//Almaceno en un objeto tipo ResultSet que es el que utiliza MySQL para almacenar esa colección sin objetos, sino con los datos que recibe la BD
		//Dado que quiero recibir datos utilizo el executeQuery
		ResultSet rs = ps.executeQuery();
		
		//Hago el Arraylist:
		ArrayList<UsuarioViewModel> result = null;
		
		//Quiero que el ArrayList contenga los objetos de tipo Usuario para trabajar con ellos en mi modelo de objetos.
		while(rs.next()) {
			//Pregunto si el array sigue null, si no es así lo inicializamos
			if(result == null) {
				result = new ArrayList<UsuarioViewModel>();
			}
			//Añado al result los elementos que obtengo de la BD definiendo los tipos de dato que necesito recuperar.
			result.add(new UsuarioViewModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
		}
		
		return result;
		
	}
	
	//Método para modificar usuarios.
	//Metodo obtener registro por el id
	public Usuario leer(int id_Usuario) throws SQLException {
		//Le mando el id y construyo el SQL
		String sql = "SELECT * FROM usuarios WHERE id_Usuario=?";
		//Contstruyo el preparedstatement
		PreparedStatement ps = con.prepareStatement(sql);
		//Mando el preparedStatement y el id
		ps.setInt(1, id_Usuario);
		
		//Ejecuto el prepared
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		//Le digo que nos genere un objeto Usuario con los datos que está recibiendo el resukltset
		Usuario u = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
		//Retornamos el objeto u
		return u;
	}
	
	//Metodo para insertar la modificación en BD
	public boolean actualizarUser(Usuario user) {
        
        try {
        	String sql = "UPDATE usuarios SET nombre=?, apellido1=?, apellido2=?, telefono=?, email=?, dni=?, carnet=?, direccion=? WHERE id_Usuario=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido1());
            ps.setString(3, user.getApellido2());
            ps.setInt(4, user.getTelefono());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getDni());
            ps.setString(7, user.getCarnet());
            ps.setString(8, user.getDireccion());
            ps.setInt(9, user.getId_Usuario());
            
            int rs = ps.executeUpdate();
            if(rs == 1)
            {
            	return true;	
            }
            else
            {
            	return false;
            }
            	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	//Método para eliminar usuarios
			public static void eliminarUser(Usuario e) throws SQLException {
				String sql = "DELETE FROM usuarios WHERE id_Usuario = ?";
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, e.getId_Usuario());
				
				int filas = ps.executeUpdate();
				
				ps.close();
			}
	
	
	
	
	
	
}
