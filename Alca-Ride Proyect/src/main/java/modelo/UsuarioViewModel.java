package modelo;

/**
 * Clase auxiliar para mostrar nombre_Usuario perteneciente
 * a la clase Login en la vista de Usuario.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 04/2024
 */
public class UsuarioViewModel {
	
	// Atributos de la clase UsuarioViewModel:
	private int id_Usuario;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int telefono;
	private String email;
	private String dni;
	private String carnet;
	private String direccion;
	private String nombre_Usuario;
	
	/**
	 * Constructor por defecto (vacio).
	 */
	public UsuarioViewModel() {
		
	}

	
	/**
	 * Constructor completo de UsuarioViewModel.
	 * @param id_Usuario Identificador unico del usuario
	 * @param nombre Nombre del usuario
	 * @param apellido1 Primer apellido del usuario
	 * @param apellido2 Segundo apellido del usuario
	 * @param telefono Telefono del usuario
	 * @param email Correo electronico del usuario
	 * @param dni DNI del usuario
	 * @param carnet Tipo de carnet del usuario
	 * @param direccion Direccion del usuario
	 * @param nombre_Usuario Nombre de usuario registrado en clase Login
	 */
	public UsuarioViewModel(int id_Usuario, String nombre, String apellido1, String apellido2, int telefono,
			String email, String dni, String carnet, String direccion, String nombre_Usuario) {
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
		this.nombre_Usuario = nombre_Usuario;
	}
	
	// Getters y setters:
	
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
	 * Obtiene el nombre del usuario.
	 * @return Nombre del usuario
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
	 * Obtiene el nombre de usuario.
	 * @return nombre_Usuario Nombre de usuario
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
     * Convierte el objeto UsuarioViewModel a una cadena de texto.
     * @return Representacion en cadena del objeto UsuarioViewModel
     */	
		@Override
		public String toString() {
			return "UsuarioViewModel [id_Usuario=" + id_Usuario + ", nombre=" + nombre + ", apellido1=" + apellido1
					+ ", apellido2=" + apellido2 + ", telefono=" + telefono + ", email=" + email + ", dni=" + dni
					+ ", carnet=" + carnet + ", direccion=" + direccion + ", nombre_Usuario=" + nombre_Usuario + "]";
		}
		
}
