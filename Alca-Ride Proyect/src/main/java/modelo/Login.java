package modelo;

import java.sql.SQLException;

import DAO.DaoLogin;


public class Login {

	
	
	private int id_Login;
	protected String nombre_Usuario;
	protected String pass;
	protected int id_Usuario;
	protected boolean is_Admin = false;
	
	
	public Login() {
		
	}


	public Login(int id_Login, String nombre_Usuario, String pass, int id_Usuario, boolean is_Admin) {
		super();
		this.id_Login = id_Login;
		this.nombre_Usuario = nombre_Usuario;
		this.pass = pass;
		this.id_Usuario = id_Usuario;
		this.is_Admin = is_Admin;
	}


	public Login(String nombre_Usuario, String pass,int id_Usuario) {
		super();
		this.nombre_Usuario = nombre_Usuario;
		this.pass = pass;
		this.id_Usuario = id_Usuario;
	}
	
	public Login(String nombre_Usuario, String pass) {
		super();
		this.nombre_Usuario = nombre_Usuario;
		this.pass = pass;
	}


	public int getId_Login() {
		return id_Login;
	}


	public void setId_Login(int id_Login) {
		this.id_Login = id_Login;
	}


	public String getNombre_Usuario() {
		return nombre_Usuario;
	}


	public void setNombre_Usuario(String nombre_Usuario) {
		this.nombre_Usuario = nombre_Usuario;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public int getId_Usuario() {
		return id_Usuario;
	}


	public void setId_Usuario(int id_Usuario) {
		this.id_Usuario = id_Usuario;
	}


	public boolean isIs_Admin() {
		return is_Admin;
	}


	public void setIs_Admin(boolean is_Admin) {
		this.is_Admin = is_Admin;
	}


	@Override
	public String toString() {
		return "Login [id_Login=" + id_Login + ", nombre_Usuario=" + nombre_Usuario + ", pass=" + pass + ", id_Usuario="
				+ id_Usuario + ", is_Admin=" + is_Admin + "]";
	}
	
		
	// Método crearLogin
	public void crearLogin() throws SQLException {
		DaoLogin log = new DaoLogin();
		log.insertar(this);
	}
	// Método leerLogin
	public Login leerLogin() throws SQLException {
		//acudir al DAO y leer el login a partir del usuario y la contraseña proporcionadas
		DaoLogin log = new DaoLogin();
		Login milogin = log.leerLogin(this);
		//Retorna lo leido por el Dao a la variable miLogin
		return milogin;		
	}
		
		
	// Método actualizarLogin
		
	// Método eliminarLogin
	public void eliminarLogin() throws SQLException {
		DaoLogin borrar = new DaoLogin();
		borrar.eliminarLog(this);
	}
	
	
}
