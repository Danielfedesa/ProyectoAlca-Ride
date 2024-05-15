package modelo;

public class LoginCliente extends Login {
	
	public LoginCliente() {
		is_Admin = false;
	}
		
	public LoginCliente(String nombre_Usuario, String pass,int id_Usuario) {
		super();
		this.nombre_Usuario = nombre_Usuario;
		this.pass = pass;
		this.id_Usuario = id_Usuario;
	}
	
	public LoginCliente(String nombre_Usuario, String pass) {
		super();
		this.nombre_Usuario = nombre_Usuario;
		this.pass = pass;
	}
	
}
