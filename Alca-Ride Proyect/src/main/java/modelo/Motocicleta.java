package modelo;

import java.sql.SQLException;
import com.google.gson.Gson;
import DAO.DaoMotocicleta;

/**
 * Clase Motocicleta que representa la informacion,
 * constructores y metodos referentes a las motocicletas.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 04/2024
 */
public class Motocicleta {
	
	// Atributos de la clase Motocicleta:
	private int id_Moto;
	private String tipo;
	private String matricula;
	private int cilindrada;
	private String tipo_Carnet;
	private String marca;
	private String modelo;
	private int anio;
	private double precio_Dia;
	
	/**
     * Constructor por defecto (vacío).
     */
	public Motocicleta() {
		
	}
	
	/**
     * Constructor completo con todos los parametros.
     * @param id_Moto Identificador de la motocicleta
     * @param tipo Tipo de la motocicleta
     * @param matricula Matricula de la motocicleta
     * @param cilindrada Cilindrada de la motocicleta
     * @param tipo_Carnet Tipo de carnet necesario
     * @param marca Marca de la motocicleta
     * @param modelo Modelo de la motocicleta
     * @param anio Ano de la motocicleta
     * @param precio_Dia Precio por dia del alquiler
     */
	public Motocicleta(int id_Moto, String tipo, String matricula, int cilindrada, String tipo_Carnet, String marca,
			String modelo, int anio, double precio_Dia) {
		super();
		this.id_Moto = id_Moto;
		this.tipo = tipo;
		this.matricula = matricula;
		this.cilindrada = cilindrada;
		this.tipo_Carnet = tipo_Carnet;
		this.marca = marca;
		this.modelo = modelo;
		this.anio = anio;
		this.precio_Dia = precio_Dia;
	}
	
	/**
     * Constructor sin id_Moto.
     * @param tipo Tipo de la motocicleta
     * @param matricula Matricula de la motocicleta
     * @param cilindrada Cilindrada de la motocicleta
     * @param tipo_Carnet Tipo de carnet necesario
     * @param marca Marca de la motocicleta
     * @param modelo Modelo de la motocicleta
     * @param anio Ano de la motocicleta
     * @param precio_Dia Precio por dia del alquiler
     */
	public Motocicleta(String tipo, String matricula, int cilindrada, String tipo_Carnet, String marca,
			String modelo, int anio, double precio_Dia) {
		super();
		this.tipo = tipo;
		this.matricula = matricula;
		this.cilindrada = cilindrada;
		this.tipo_Carnet = tipo_Carnet;
		this.marca = marca;
		this.modelo = modelo;
		this.anio = anio;
		this.precio_Dia = precio_Dia;
	}

	//Getters y setters:
	
	/**
     * Obtiene el identificador de la motocicleta.
     * @return id_Moto Identificador de la motocicleta
     */
	public int getId_Moto() {
		return id_Moto;
	}
	
	/**
     * Establece el identificador de la motocicleta.
     * @param id_Moto Identificador de la motocicleta
     */
	public void setId_Moto(int id_Moto) {
		this.id_Moto = id_Moto;
	}
	
	/**
     * Obtiene el tipo de la motocicleta.
     * @return tipo Tipo de la motocicleta
     */
	public String getTipo() {
		return tipo;
	}
	
	/**
     * Establece el tipo de la motocicleta.
     * @param tipo Tipo de la motocicleta
     */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
     * Obtiene la matricula de la motocicleta.
     * @return matricula Matricula de la motocicleta
     */
	public String getMatricula() {
		return matricula;
	}
	
	/**
     * Establece la matricula de la motocicleta.
     * @param matricula Matricula de la motocicleta
     */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	/**
     * Obtiene la cilindrada de la motocicleta.
     * @return cilindrada Cilindrada de la motocicleta
     */
	public int getCilindrada() {
		return cilindrada;
	}
	
	/**
     * Establece la cilindrada de la motocicleta.
     * @param cilindrada Cilindrada de la motocicleta
     */
	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}
	
	/**
     * Obtiene el tipo de carnet necesario.
     * @return tipo_Carnet Tipo de carnet necesario
     */
	public String getTipo_Carnet() {
		return tipo_Carnet;
	}
	
	/**
     * Establece el tipo de carnet necesario.
     * @param tipo_Carnet Tipo de carnet necesario
     */
	public void setTipo_Carnet(String tipo_Carnet) {
		this.tipo_Carnet = tipo_Carnet;
	}
	
	/**
     * Obtiene la marca de la motocicleta.
     * @return marca Marca de la motocicleta
     */
	public String getMarca() {
		return marca;
	}
	
	/**
     * Establece la marca de la motocicleta.
     * @param marca Marca de la motocicleta
     */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	/**
     * Obtiene el modelo de la motocicleta.
     * @return modelo Modelo de la motocicleta
     */
	public String getModelo() {
		return modelo;
	}
	
	/**
     * Establece el modelo de la motocicleta.
     * @param modelo Modelo de la motocicleta
     */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	/**
     * Obtiene el ano de la motocicleta.
     * @return anio Ano de la motocicleta
     */
	public int getAnio() {
		return anio;
	}
	
	/**
     * Establece el ano de la motocicleta.
     * @param anio Ano de la motocicleta
     */
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	/**
     * Obtiene el precio por dia del alquiler.
     * @return precio_Dia Precio por dia del alquiler
     */
	public double getPrecio_Dia() {
		return precio_Dia;
	}
	
	/**
     * Establece el precio por dia del alquiler.
     * @param precio_Dia Precio por dia del alquiler
     */
	public void setPrecio_Dia(double precio_Dia) {
		this.precio_Dia = precio_Dia;
	}
	

	/**
     * Convierte el objeto Motocicleta a una cadena de texto.
     * @return Representacion en cadena del objeto Motocicleta
     */
	@Override
	public String toString() {
		return "Motocicleta [id_Moto=" + id_Moto + ", tipo=" + tipo + ", matricula=" + matricula + ", cilindrada="
				+ cilindrada + ", tipo_Carnet=" + tipo_Carnet + ", marca=" + marca + ", modelo=" + modelo + ", anio="
				+ anio + ", precio_Dia=" + precio_Dia + "]";
	}
	
	/**
     * Metodo para insertar una motocicleta en la base de datos.
     * @throws SQLException Si ocurre un error en la base de datos
     */
	// Crear una instancia de DaoMotocicleta
    // Insertar la motocicleta actual en la base de datos
	public void crearMotocicleta() throws SQLException {  
        DaoMotocicleta moto = new DaoMotocicleta();
        moto.insertar(this);
    }

	/**
     * Método para listar motocicletas en formato JSON.
     * @return Cadena JSON con la lista de motocicletas
     * @throws SQLException Si ocurre un error en la base de datos
     */
	// Cadena JSON resultante
	// Crear objeto Gson
	// Crear instancia de DaoMotocicleta
	// Meter en el objeto json lo que genere el objetoGson con el método toJson (lo convierte a json)
	// Convertir la lista de motocicletas a JSON
	// Retornar la cadena JSON
	public String listarMotos() throws SQLException {        
        String json = "";        
        Gson objetoGson = new Gson();        
        DaoMotocicleta resultado = new DaoMotocicleta();        
        json = objetoGson.toJson(resultado.listar());    
        return json;
    }
	
	/**
     * Metodo para recuperar una motocicleta por su ID y cargar sus datos.
     * @param id_Moto Identificador unico de la motocicleta
     * @throws SQLException Si ocurre un error en la base de datos
     */
    // Crear una instancia de DaoMotocicleta
	// Crear una motocicleta auxiliar en base al id_Moto con todos los datos
    // Establecer los atributos de la motocicleta actual con los datos recuperados

    public void recuperarMoto(int id_Moto) throws SQLException {
        DaoMotocicleta dao = new DaoMotocicleta();
        
        Motocicleta m = dao.leerFormulario(id_Moto);
        
        this.setId_Moto(m.getId_Moto());
        this.setTipo(m.getTipo());
        this.setMatricula(m.getMatricula());
        this.setCilindrada(m.getCilindrada());
        this.setTipo_Carnet(m.getTipo_Carnet());
        this.setMarca(m.getMarca());
        this.setModelo(m.getModelo());
        this.setAnio(m.getAnio());
        this.setPrecio_Dia(m.getPrecio_Dia());
    }
		
    /**
     * Metodo para obtener los datos de la motocicleta en formato JSON.
     * @return Cadena JSON con los datos de la motocicleta
     */
    // Método para leer datos y luego pintar en el formulario para modificar
    // Cadena JSON resultante
    // Crear objeto Gson
	// Meter en el json lo que devuelve el objeto Gson y darle como parametro a mi mismo(Motocicleta)
    // Convertir la motocicleta actual a JSON
    // Retornar la cadena JSON
    public String dameJson() {   
        String json = "";       
        Gson gson = new Gson();        
        json = gson.toJson(this);        
        return json;
    }
	
    /**
     * Metodo para insertar la actualizacion de los datos de la motocicleta en la base de datos.
     * @return true si la actualizacion fue correcta, false en caso contrario
     * @throws SQLException Si ocurre un error en la base de datos
     */
    // Crear una instancia de DaoMotocicleta
    // Actualizar los datos de la motocicleta en la base de datos
    public boolean actualizarMoto() throws SQLException {      
        DaoMotocicleta daoMoto = new DaoMotocicleta();
        return daoMoto.actualizarMoto(this);
    }
    
    /**
     * Metodo para eliminar una motocicleta de la base de datos.
     * @throws SQLException Si ocurre un error en la base de datos
     */
    // Crear una instancia de DaoMotocicleta
    // Eliminar la motocicleta actual de la base de datos
    @SuppressWarnings("static-access")
	public void eliminarMotocicleta() throws SQLException {     
        DaoMotocicleta borrar = new DaoMotocicleta();
        borrar.eliminarMoto(this);
    }

}
