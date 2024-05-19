package modelo;

/**
 * Clase LoginAdmin que representa la informacion
 * del Login estableciendo el Cliente.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 04/2024
 */
public class LoginCliente extends Login {
	
	/**
     * Constructor que establece el usuario como no administrador (cliente).
     */
	public LoginCliente() {
		is_Admin = false;
	}
		
	/**
     * Constructor que establece el nombre de usuario, contrasena e ID de usuario.
     * @param nombre_Usuario Nombre de usuario
     * @param pass Contrasena
     * @param id_Usuario Identificador único del usuario
     */
	public LoginCliente(String nombre_Usuario, String pass,int id_Usuario) {
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
	public LoginCliente(String nombre_Usuario, String pass) {
		super();
		this.nombre_Usuario = nombre_Usuario;
		this.pass = pass;
	}	
}
