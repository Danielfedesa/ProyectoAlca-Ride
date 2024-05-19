package modelo;

/**
 * Clase LoginAdmin que representa la informacion
 * del Login estableciendo el Administrador.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 04/2024
 */
public class LoginAdmin extends Login {
	
	/**
     * Constructor que establece el usuario como administrador.
     */
	public LoginAdmin() {
		is_Admin = true;
	}
	
	/**
     * Constructor que establece el nombre de usuario, contrasena e ID de usuario.
     * @param nombre_Usuario Nombre de usuario
     * @param pass Contrasena
     * @param id_Usuario Identificador unico del usuario
     */
	public LoginAdmin(String nombre_Usuario, String pass,int id_Usuario) {
		super();
		this.nombre_Usuario = nombre_Usuario;
		this.pass = pass;
		this.id_Usuario = id_Usuario;
	}
	
	/**
     * Constructor que establece el nombre de usuario y la contrasena.
     * @param nombre_Usuario Nombre de usuario
     * @param pass Contrasena
     */
	public LoginAdmin(String nombre_Usuario, String pass) {
		super();
		this.nombre_Usuario = nombre_Usuario;
		this.pass = pass;
	}
	
	
}
