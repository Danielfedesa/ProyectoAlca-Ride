package modelo;

public class LoginAdmin extends Login {
	
	public LoginAdmin() {
		is_Admin = true;
	}
	
	public LoginAdmin(String nombre_Usuario, String pass,int id_Usuario) {
		super();
		this.nombre_Usuario = nombre_Usuario;
		this.pass = pass;
		this.id_Usuario = id_Usuario;
	}
	
	public LoginAdmin(String nombre_Usuario, String pass) {
		super();
		this.nombre_Usuario = nombre_Usuario;
		this.pass = pass;
	}
	
	
}
