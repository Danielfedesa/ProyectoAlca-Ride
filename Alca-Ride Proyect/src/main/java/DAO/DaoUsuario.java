package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.ConexionDB;
import modelo.Usuario;
import modelo.UsuarioViewModel;

/**
 * Clase para realizar operaciones relacionadas con la tabla 'usuarios' en la
 * base de datos.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 04/2024
 */
public class DaoUsuario {

	/**
	 * Atributo estatico que gestiona la conexion a la base de datos. en toda la
	 * aplicacion, permitiendo la reutilizacion de la misma conexion. Inicialmente,
	 * la instancia esta establecida en null.
	 * @see java.sql.Connection
	 */
	// Genera un atributo de tipo Connection llamado "con" y la instancia en null:
	public static Connection con = null;

	/**
	 * Constructor de la clase DaoReserva para establecer conexion con la base de datos.
	 * @throws SQLException Si hay un error de conexion
	 */
	// Nada más se crea el objeto DaoMotocicleta le decimos al atributo con que
	// conecte con DBConnection.getConexion()
	// para que se inicie la conexión nada más se instancie el objeto.
	public DaoUsuario() throws SQLException {
		this.con = ConexionDB.getConexion();
	}

	/**
	 * Metodo para insertar un nuevo usuario en la base de datos. 
	 * @param u Objeto Usuario para insertar
	 * @return ID del usuario insertado
	 * @throws SQLException Si hay un error de insercion en base de datos
	 */
	public int insertar(Usuario u) throws SQLException {
		// Sentencia SQL para insertar un nuevo registro en la tabla usuarios.
		String sql = "INSERT INTO usuarios (nombre, apellido1, apellido2, telefono, email, dni, carnet, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		// Inicializa el ID del usuario a 0.
		int id = 0;

		// Prepara la sentencia SQL y obtiene el ID generado con el GENERATED_KEYS
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		// Establece los parámetros en la sentencia SQL.
		ps.setString(1, u.getNombre());
		ps.setString(2, u.getApellido1());
		ps.setString(3, u.getApellido2());
		ps.setInt(4, u.getTelefono());
		ps.setString(5, u.getEmail());
		ps.setString(6, u.getDni());
		ps.setString(7, u.getCarnet());
		ps.setString(8, u.getDireccion());

		// Ejecuta la sentencia y actualiza el número de filas afectadas.
		int filas = ps.executeUpdate();

		// Obtiene el ID generado.
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			id = rs.getInt(1);
		}

		// Cierra el PreparedStatement.
		ps.close();

		// Devuelve el ID del usuario insertado para pasarlo posteriormente al registro
		// del Login
		return id;
	}

	/**
	 * Metodo para listar todos los usuarios de la base de datos obteniendo tambien
	 * el nombre_Usuario 
	 * @return ArrayList de UsuarioViewModel con los datos de los usuarios
	 * @throws SQLException Si hay un error de lectura en base de datos
	 */
	// Crear un arraylist de la clase UsuarioViewModel (nombre_Usuario)
	public ArrayList<UsuarioViewModel> listar() throws SQLException {
		// Sentencia SQL para seleccionar todos los registros de la tabla usuarios y el
		// nombre_Usuario de la tabla login.
		String sql = "SELECT usuarios.*, login.nombre_Usuario AS nombre_Usuario FROM usuarios INNER JOIN login ON usuarios.id_Usuario = login.id_Usuario";
		// Hacer el prepareStatement pero esta vez de vuelta, lo haré con un select.
		PreparedStatement ps = con.prepareStatement(sql);
		// Almacenar en un objeto tipo ResultSet que es el que utiliza MySQL para
		// almacenar esa colección sin objetos, sino con los datos que recibe la BD
		// Recibir los datos utilizando el executeQuery.
		ResultSet rs = ps.executeQuery();

		// Inicializa el ArrayList para almacenar los objetos UsuarioViewModel.
		ArrayList<UsuarioViewModel> result = null;

		// Lee el resultado del ResultSet y crea objetos UsuarioViewModel.
		// Quiero que el ArrayList contenga los objetos de tipo Usuario para trabajar
		// con ellos en mi modelo de objetos.
		while (rs.next()) {
			// Preguntar si el array sigue null, si no es así lo inicializamos
			if (result == null) {
				result = new ArrayList<UsuarioViewModel>();
			}
			// Añadir al result los elementos que obtengo de la BD definiendo los tipos de
			// dato que necesito recuperar.
			result.add(
					new UsuarioViewModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
		}

		// Cierra el PreparedStatement.
		ps.close();

		// Devuelve el ArrayList de UsuarioViewModel.
		return result;

	}

	/**
	 * Metodo para leer un usuario de la base de datos por su ID y modificarlo posteriormente.
	 * @param id_Usuario ID del usuario para leer
	 * @return Objeto Usuario con los datos del usuario leido
	 * @throws SQLException Si hay un error de lectura en base de datos
	 */
	public Usuario leer(int id_Usuario) throws SQLException {
		// Sentencia para seleccionar un registro de la tabla usuarios por su ID.
		// Mandar el id y construyo el SQL
		String sql = "SELECT * FROM usuarios WHERE id_Usuario=?";
		// Prepara la sentencia SQL
		PreparedStatement ps = con.prepareStatement(sql);
		// Mandar el preparedStatement y el id
		ps.setInt(1, id_Usuario); // Estableces el id_Cliente en la consulta SQL

		// Ejecutar la consulta y obtener el resultado.
		ResultSet rs = ps.executeQuery();
		rs.next();

		// Crea un objeto Usuario con los datos obtenidos del resultSet
		Usuario u = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
				rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));

		// Cierra el PreparedStatement.
		ps.close();

		// Retorna el objeto Usuario.
		return u;
	}

	/**
	 * Metodo para insertar la modificacion de un usuario en la base de datos. 
	 * @param user Objeto Usuario con los datos actualizados
	 * @return true si la actualizacion fue correcta, false si esta falla
	 */
	public boolean actualizarUser(Usuario user) {

		try {
			// Sentencia para actualizar un registro de la tabla usuarios por su ID.
			String sql = "UPDATE usuarios SET nombre=?, apellido1=?, apellido2=?, telefono=?, email=?, dni=?, carnet=?, direccion=? WHERE id_Usuario=?";

			// Prepara la sentencia SQL
			PreparedStatement ps = con.prepareStatement(sql);
			// Establece los parámetros en la sentencia SQL.
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getApellido1());
			ps.setString(3, user.getApellido2());
			ps.setInt(4, user.getTelefono());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getDni());
			ps.setString(7, user.getCarnet());
			ps.setString(8, user.getDireccion());
			ps.setInt(9, user.getId_Usuario());

			// Ejecuta la sentencia y obtiene el resultado.
			int rs = ps.executeUpdate();
			// Retorna true si se actualizó correctamente, false si no.
			if (rs == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Metodo para eliminar un usuario de la base de datos.
	 * @param e Objeto Usuario para eliminar
	 * @throws SQLException Si hay un error de eliminacion en base de datos
	 */
	public static void eliminarUser(Usuario e) throws SQLException {
		// Sentencia para eliminar un registro de la tabla usuarios por su ID.
		String sql = "DELETE FROM usuarios WHERE id_Usuario = ?";

		// Prepara la sentencia SQL
		PreparedStatement ps = con.prepareStatement(sql);
		// Mandar el preparedStatement y el id
		ps.setInt(1, e.getId_Usuario());

		// Ejecuta la sentencia.
		int filas = ps.executeUpdate();

		// Cierra el PreparedStatement.
		ps.close();
	}

}
