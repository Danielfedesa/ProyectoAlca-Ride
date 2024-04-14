package modelo;

import java.sql.SQLException;

import DAO.DaoUsuario;

public class Usuario {
	
	
	private int id_Usuario;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int telefono;
	private String email;
	private String dni;
	private String carnet;
	private String direccion;
	
	public Usuario() {
		
	}

	public Usuario(int id_Usuario, String nombre, String apellido1, String apellido2, int telefono, String email,
			String dni, String carnet, String direccion) {
		super();
		this.id_Usuario = id_Usuario;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.telefono = telefono;
		this.email = email;
		this.dni = dni;
		this.carnet = carnet;
		this.direccion = direccion;
	}

	public Usuario(String nombre, String apellido1, String apellido2, int telefono, String email, String dni,
			String carnet, String direccion) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.telefono = telefono;
		this.email = email;
		this.dni = dni;
		this.carnet = carnet;
		this.direccion = direccion;
	}

	public int getId_Usuario() {
		return id_Usuario;
	}

	public void setId_Usuario(int id_Usuario) {
		this.id_Usuario = id_Usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Usuario [id_Usuario=" + id_Usuario + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2="
				+ apellido2 + ", telefono=" + telefono + ", email=" + email + ", dni=" + dni + ", carnet=" + carnet
				+ ", direccion=" + direccion + "]";
	}
	
	// Método iniciar sesión
	
	// Método cerrar sesión
	
	// Método crearUsuario
	public int crearUsuario() throws SQLException {
		DaoUsuario usu = new DaoUsuario();
		int id_UsuarioInsertado = usu.insertar(this);
		return id_UsuarioInsertado;
	}
	// Método leerUsuario
	
	// Método actualizarUsuario
	
	// Método eliminarUsuario

}
