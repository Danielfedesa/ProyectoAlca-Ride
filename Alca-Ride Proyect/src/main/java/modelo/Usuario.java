package modelo;

import java.sql.SQLException;
import com.google.gson.Gson;
import DAO.DaoUsuario;

/**
 * Clase Usuario que representa la información,
 * constructores y métodos referentes a los
 * usuarios del sistema.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 04/2024
 */
public class Usuario {
	
	// Atributos de la clase Usuario:
	private int id_Usuario;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int telefono;
	private String email;
	private String dni;
	private String carnet;
	private String direccion;
	
	/**
     * Constructor por defecto (vacío).
     */
	public Usuario() {
		
	}

	/**
     * Constructor completo Usuario.
     * @param id_Usuario Identificador único del usuario
     * @param nombre Nombre del usuario
     * @param apellido1 Primer apellido del usuario
     * @param apellido2 Segundo apellido del usuario
     * @param telefono Teléfono del usuario
     * @param email Correo electrónico del usuario
     * @param dni Documento Nacional de Identidad del usuario
     * @param carnet Tipo de carnet del usuario
     * @param direccion Dirección del usuario
     */
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

	/**
     * Constructor para crear un Usuario sin el id.
     * @param nombre Nombre del usuario
     * @param apellido1 Primer apellido del usuario
     * @param apellido2 Segundo apellido del usuario
     * @param telefono Teléfono del usuario
     * @param email Correo electrónico del usuario
     * @param dni Documento Nacional de Identidad del usuario
     * @param carnet Tipo de carnet del usuario
     * @param direccion Dirección del usuario
     */
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

	// Getters y setters:

    /**
     * Obtiene el identificador del usuario.
     * @return id_Usuario Identificador del usuario
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
     * Obtiene el nombre del usuario.
     * @return nombre Nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * @param nombre Nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el primer apellido del usuario.
     * @return apellido1 Primer apellido del usuario
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Establece el primer apellido del usuario.
     * @param apellido1 Primer apellido del usuario
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * Obtiene el segundo apellido del usuario.
     * @return apellido2 Segundo apellido del usuario
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Establece el segundo apellido del usuario.
     * @param apellido2 Segundo apellido del usuario
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * Obtiene el telefono del usuario.
     * @return telefono Telefono del usuario
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Establece el telefono del usuario.
     * @param telefono Telefono del usuario
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el correo electronico del usuario.
     * @return email Correo electronico del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electronico del usuario.
     * @param email Correo electronico del usuario
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el DNI del usuario.
     * @return dni DNI del usuario
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del usuario.
     * @param dni DNI del usuario
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el carnet del usuario.
     * @return carnet Carnet del usuario
     */
    public String getCarnet() {
        return carnet;
    }

    /**
     * Establece el carnet del usuario.
     * @param carnet Carnet del usuario
     */
    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    /**
     * Obtiene la direccion del usuario.
     * @return direccion Direccion del usuario
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la direccion del usuario.
     * @param direccion Direccion del usuario
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Retorna una representacion en cadena del objeto Usuario.
     * @return String con los atributos del Usuario
     */
	@Override
	public String toString() {
		return "Usuario [id_Usuario=" + id_Usuario + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2="
				+ apellido2 + ", telefono=" + telefono + ", email=" + email + ", dni=" + dni + ", carnet=" + carnet
				+ ", direccion=" + direccion + "]";
	}
	
	
	/**
     * Metodo para crear un nuevo usuario en la base de datos.
     * @return int Identificador del usuario insertado
     * @throws SQLException Si ocurre un error al interactuar con la base de datos
     */
	// Crear una instancia de DaoUsuario
	// Insertar el usuario actual en la base de datos
	// Retornar el resultado de la inserción
	public int crearUsuario() throws SQLException {
		DaoUsuario usu = new DaoUsuario();
		int id_UsuarioInsertado = usu.insertar(this);
		return id_UsuarioInsertado;
	}

	/**
     * Metodo para listar todos los usuarios.
     * @return String JSON con la lista de usuarios
     * @throws SQLException Si ocurre un error al interactuar con la base de datos
     */
	 // Cadena JSON resultante
	 // Crear objeto Gson
	 // Crear instancia de DaouSUARIO
	 // Meter en el objeto json lo que genere el objetoGson con el método toJson (lo convierte a json)
	 // Convertir la lista de USUARIOS a JSON
	 // Retornar la cadena JSON
	public String listarUsuarios() throws SQLException {
		String json = "";
		Gson objetoGson = new Gson();
		DaoUsuario resultado = new DaoUsuario();
		json = objetoGson.toJson(resultado.listar());
		return json;
	}
	
	 /**
     * Metodo para recuperar un usuario para modificarlo despues en el formulario.
     * @param id_Usuario Identificador del usuario a recuperar
     * @throws SQLException Si ocurre un error al interactuar con la base de datos
     */
	// Crear instancia de DaoReserva
    // Leer los datos de la reserva desde la base de datos
	// Crear un usuario auxiliar en base al id_Usuario con todos los datos
    // Establecer los datos recuperados en la reserva actual
	public void recuperarUsuario(int id_Usuario) throws SQLException {
		DaoUsuario dao = new DaoUsuario();
		Usuario u = dao.leer(id_Usuario);
		
		this.setId_Usuario(u.getId_Usuario());
		this.setNombre(u.getNombre());
		this.setApellido1(u.getApellido1());
		this.setApellido2(u.getApellido2());
		this.setTelefono(u.getTelefono());
		this.setEmail(u.getEmail());
		this.setDni(u.getDni());
		this.setCarnet(u.getCarnet());
		this.setDireccion(u.getDireccion());
	}	
	
	 /**
     * Metodo para convertir los datos del usuario en un JSON.
     * @return String JSON con los datos del usuario
     */
	 // Variable con cadena vacía
	 // Crear objeto Gson
	 // Meter en el json lo que devuelve el objeto Gson y darle como parametro a mi mismo(Usuario)
	 // Convertir el usuario actual a JSON
	 // Retornar la cadena JSON
	public String dameJson() {
		String json = "";
		Gson gson = new Gson();
		json = gson.toJson(this);
		return json;
	}
	
	/**
     * Metodo para insertar la actualizacion de los datos de un usuario en la base de datos.
     * @return boolean true si la actualizacion fue correcta, false en caso contrario
     * @throws SQLException Si ocurre un error al interactuar con la base de datos
     */
	// Crear una instancia de DaoUsuario
    // Actualizar los datos del usuario en la base de datos
	public boolean actualizarUsuario() throws SQLException {
		DaoUsuario daoUsuario = new DaoUsuario();
		return daoUsuario.actualizarUser(this);
	}
	/**
     * Metodo para eliminar un usuario de la base de datos.
     * @throws SQLException Si hay un error al interactuar con la base de datos
     */
	// Crear una instancia de DaoUsuario
    // Eliminar el usuario actual de la base de datos
	@SuppressWarnings("static-access")
	public void eliminarUsuario() throws SQLException {
		DaoUsuario borrar = new DaoUsuario();
		borrar.eliminarUser(this);
	}
	
}
