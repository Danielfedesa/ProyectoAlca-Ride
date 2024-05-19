package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlador.ConexionDB;
import modelo.Login;

/**
 * Clase para realizar operaciones relacionadas con la tabla 'login' en la base
 * de datos.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 04/2024
 */
public class DaoLogin {

	/**
	 * Atributo estatico que gestiona la conexion a la base de datos. en toda la
	 * aplicacion, permitiendo la reutilizacion de la misma conexion. Inicialmente,
	 * la instancia esta establecida en null.
	 * @see java.sql.Connection
	 */
	// Genera un atributo de tipo Connection llamado "con" y la instancia en null:
	public static Connection con = null;

	/**
	 * Constructor de la clase DaoLogin para establecer conexión con la base de datos.
	 * @throws SQLException Si hay un error de conexion
	 */
	// Nada más se crea el objeto DaoMotocicleta le decimos al atributo con que
	// conecte con DBConnection.getConexion()
	// para que se inicie la conexión nada más se instancie el objeto.
	public DaoLogin() throws SQLException {
		this.con = ConexionDB.getConexion();
	}

	/**
	 * Metodo para insertar un nuevo registro de login en la base de datos.
	 * @param l Objeto Login para insertar
	 * @throws SQLException Si hay un error de insercion
	 */
	public void insertar(Login l) throws SQLException {
		// Sentencia para insertar un nuevo registro en la tabla login.
		String sql = "INSERT INTO login (nombre_Usuario, pass, id_Usuario) VALUES (?, ?, ?)";

		// Prepara la sentencia SQL.
		PreparedStatement ps = con.prepareStatement(sql);
		// Establece los parámetros en la sentencia SQL.
		ps.setString(1, l.getNombre_Usuario());
		ps.setString(2, l.getPass());
		ps.setInt(3, l.getId_Usuario()); // Le paso el id_Usuario recién creado

		// Ejecuta la sentencia y actualiza el número de filas afectadas.
		int filas = ps.executeUpdate();

		// Cierra el PreparedStatement.
		ps.close();
	}

	/**
	 * Metodo para leer un registro de login desde la base de datos para iniciar
	 * sesion.
	 * @param k Objeto Login con el nombre de usuario y contrasena
	 * @return Objeto Login con los datos del usuario si existe, o un objeto vacio
	 *         si no existe
	 * @throws SQLException Si hay un error de lectura en base de datos
	 */
	public Login leerLogin(Login k) throws SQLException {
		// Sentencia SQL para seleccionar un registro de la tabla login.
		String sql = "SELECT id_Login, nombre_Usuario, is_Admin FROM login WHERE nombre_Usuario = ? AND pass = ?";

		// Variables para almacenar el nombre y password que le pasamos por el
		// formulario.
		String nombreUs = k.getNombre_Usuario();
		String passUs = k.getPass();

		// Preparar la sentrencia para consultar en BDD por nombre_Usuario y is_Admin
		PreparedStatement ps = con.prepareStatement(sql);
		// Establece los parámetros en la sentencia SQL.
		ps.setString(1, nombreUs);
		ps.setString(2, passUs);

		// Ejecuta la consulta y obtiene el resultado.
		// Recoge el resultado en la variable filas del ResultSet
		ResultSet filas = ps.executeQuery();
		// Crea un objeto de la clase Login
		Login objLogin = new Login();
		// Inicializa el objLogin con el nombre_Usuario vacío para validar luego si
		// existe o no
		objLogin.setNombre_Usuario("");
		// Lee el resultado del ResultSet mediante un while
		while (filas.next()) {
			objLogin.setId_Login(filas.getInt("id_Login"));
			objLogin.setNombre_Usuario(filas.getString("nombre_Usuario"));
			objLogin.setIs_Admin(filas.getBoolean("is_Admin"));
		}

		// Imprime el objeto objLogin.
		System.out.println(objLogin);
		// Cierra el PreparedStatement.
		ps.close();

		// Devolvemos el objLogin relleno o vacío en su caso
		return objLogin;
	}

	/**
	 * Metodo para eliminar un registro de login de la base de datos.
	 * @param e Objeto Login para eliminar
	 * @throws SQLException Si hay un error de eliminacion en base de datos
	 */
	public static void eliminarLog(Login e) throws SQLException {
		// Sentencia SQL para eliminar un registro de la tabla login.
		String sql = "DELETE FROM login WHERE id_Usuario = ?";

		// Prepara la sentencia SQL.
		PreparedStatement ps = con.prepareStatement(sql);
		// Establece el parámetro en la sentencia SQL.
		ps.setInt(1, e.getId_Usuario());

		// Ejecuta la sentencia y actualiza el número de filas afectadas.
		int filas = ps.executeUpdate();

		// Cierra el PreparedStatement.
		ps.close();
	}

}
