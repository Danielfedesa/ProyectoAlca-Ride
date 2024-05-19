package modelo;

import java.sql.SQLException;
import DAO.DaoLogin;

/**
 * Clase Login que representa la informacion,
 * constructores y metodos del Login de un usuario.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 04/2024
 */
public class Login {
	
	// Atributos de la clase Login:
	private int id_Login;
	protected String nombre_Usuario;
	protected String pass;
	protected int id_Usuario;
	protected boolean is_Admin = false;
	
	/**
     * Constructor por defecto (vacio).
     */
	public Login() {
		
	}

	/**
     * Constructor completo con todos los parametros.
     * @param id_Login Identificador unico del login
     * @param nombre_Usuario Nombre de usuario
     * @param pass Contrasena
     * @param id_Usuario Identificador unico del usuario
     * @param is_Admin Indica si el usuario es administrador
     */
	public Login(int id_Login, String nombre_Usuario, String pass, int id_Usuario, boolean is_Admin) {
		super();
		this.id_Login = id_Login;
		this.nombre_Usuario = nombre_Usuario;
		this.pass = pass;
		this.id_Usuario = id_Usuario;
		this.is_Admin = is_Admin;
	}

	/**
     * Constructor sin el parametro is_Admin.
     * @param nombre_Usuario Nombre de usuario
     * @param pass Contrasena
     * @param id_Usuario Identificador unico del usuario
     */
	public Login(String nombre_Usuario, String pass,int id_Usuario) {
		super();
		this.nombre_Usuario = nombre_Usuario;
		this.pass = pass;
		this.id_Usuario = id_Usuario;
	}
	
	 /**
     * Constructor con el nombre de usuario y la contrasena.
     * @param nombre_Usuario Nombre de usuario
     * @param pass Contrasena
     */
	public Login(String nombre_Usuario, String pass) {
		super();
		this.nombre_Usuario = nombre_Usuario;
		this.pass = pass;
	}

	/**
     * Obtiene el identificador del login.
     * @return Identificador del login
     */
	public int getId_Login() {
		return id_Login;
	}

	/**
     * Establece el identificador del login.
     * @param id_Login Identificador del login
     */
	public void setId_Login(int id_Login) {
		this.id_Login = id_Login;
	}

	/**
     * Obtiene el nombre de usuario.
     * @return Nombre de usuario
     */
	public String getNombre_Usuario() {
		return nombre_Usuario;
	}

	 /**
     * Establece el nombre de usuario.
     * @param nombre_Usuario Nombre de usuario
     */
	public void setNombre_Usuario(String nombre_Usuario) {
		this.nombre_Usuario = nombre_Usuario;
	}

	/**
     * Obtiene la contrasena del usuario.
     * @return Contrasena del usuario
     */
	public String getPass() {
		return pass;
	}

	/**
     * Establece la contrasena del usuario.
     * @param pass Contrasena del usuario
     */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
     * Obtiene el identificador del usuario.
     * @return Identificador del usuario
     */
	public int getId_Usuario() {
		return id_Usuario;
	}

	/**
     * Establece el identificador del usuario.
     * @param id_Usuario Identificador del usuario
     */
	public void setId_Usuario(int id_Usuario) {
		this.id_Usuario = id_Usuario;
	}

	/**
     * Indica si el usuario es administrador.
     * @return true si es administrador, false en caso contrario
     */
	public boolean isIs_Admin() {
		return is_Admin;
	}
	
	/**
     * Establece si el usuario es administrador.
     * @param is_Admin true si es administrador, false en caso contrario
     */
	public void setIs_Admin(boolean is_Admin) {
		this.is_Admin = is_Admin;
	}

	/**
     * Convierte el objeto Login a una cadena de texto.
     * @return Representacion en cadena del objeto Login
     */
	@Override
	public String toString() {
		return "Login [id_Login=" + id_Login + ", nombre_Usuario=" + nombre_Usuario + ", pass=" + pass + ", id_Usuario="
				+ id_Usuario + ", is_Admin=" + is_Admin + "]";
	}
	
	 /**
     * Crea un nuevo login en la base de datos.
     * @throws SQLException Si ocurre un error en la base de datos
     */	
	// Método crearLogin
	// Crea una instancia de DaoLogin
	// Inserta el login actual en base de datos mediante el método en DaoLogin
	public void crearLogin() throws SQLException {
		DaoLogin log = new DaoLogin();
		log.insertar(this);		
	}
	
	/**
     * Inicia sesion verificando las credenciales del usuario.
     * @return Objeto Login con los datos del usuario si las credenciales son correctas
     * @throws SQLException Si ocurre un error en la base de datos
     */
	// Método leerLogin para iniciar la sesion
	// Crear una instancia de DaoLogin
	// Acudir al DaoLogin y leer el login a partir del usuario y la pass
	// Retornar lo leído por el DaoLogin a la variable miLogin
	public Login iniciarSesion() throws SQLException {
		DaoLogin log = new DaoLogin();
		Login milogin = log.leerLogin(this);
		//Retorna lo leido por el Dao a la variable miLogin
		return milogin;
	}
	
	/**
     * Elimina el login de la base de datos.
     * @throws SQLException Si ocurre un error en la base de datos
     */	
	// Método eliminarLogin
    // Crear una instancia de DaoLogin
	// Eliminar el login actual de la base de datos
	@SuppressWarnings("static-access")
	public void eliminarLogin() throws SQLException {
		DaoLogin borrar = new DaoLogin();
		borrar.eliminarLog(this);
	}
		
}
