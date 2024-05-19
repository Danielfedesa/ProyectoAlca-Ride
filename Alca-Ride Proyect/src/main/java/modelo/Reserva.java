package modelo;

import java.sql.Date;
import java.sql.SQLException;
import com.google.gson.Gson;
import DAO.DaoReserva;

/**
 * Clase Reserva que representa la informacion,
 * constructores y métodos referentes a las reservas.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 04/2024
 */
public class Reserva {
	
	// Atributos de la clase Reserva:
	private int id_Reserva;
	private int id_Moto;
	private int id_Cliente;
	private int id_Admin = 1;
	private Date fecha_Realiza;
	private Date fecha_Inicio;
	private Date fecha_Fin;
	private String estado;
		
    /**
     * Constructor por defecto (vacio).
     */
	public Reserva() {
		super();
	}

	/**
     * Constructor completo con todos los parametros.
     * @param id_Reserva Identificador unico de la reserva
     * @param fecha_Realiza Fecha en que se realiza la reserva
     * @param fecha_Inicio Fecha de inicio de la reserva
     * @param fecha_Fin Fecha de fin de la reserva
     * @param estado Estado de la reserva
     */
	public Reserva(int id_Reserva, Date fecha_Realiza, Date fecha_Inicio, Date fecha_Fin, String estado) {
		super();
		this.id_Reserva = id_Reserva;
		this.fecha_Realiza = fecha_Realiza;
		this.fecha_Inicio = fecha_Inicio;
		this.fecha_Fin = fecha_Fin;
		this.estado = estado;
	}
	
	/**
     * Constructor completo para mostrar en listado ADMINISTRADOR Y CLIENTE.
     * @param id_Reserva Identificador unico de la reserva
     * @param id_Moto Identificador unico de la motocicleta
     * @param id_Cliente Identificador unico del cliente
     * @param id_Admin Identificador unico del administrador
     * @param fecha_Realiza Fecha en que se realiza la reserva
     * @param fecha_Inicio Fecha de inicio de la reserva
     * @param fecha_Fin Fecha de fin de la reserva
     * @param estado Estado de la reserva
     */
	public Reserva(int id_Reserva, int id_Moto, int id_Cliente, int id_Admin, Date fecha_Realiza, Date fecha_Inicio, Date fecha_Fin,
			String estado) {
		super();
		this.id_Reserva = id_Reserva;
		this.id_Moto = id_Moto;
		this.id_Cliente = id_Cliente;
		this.id_Admin = id_Admin;
		this.fecha_Realiza = fecha_Realiza;
		this.fecha_Inicio = fecha_Inicio;
		this.fecha_Fin = fecha_Fin;
		this.estado = estado;
	}
	
	/**
     * Constructor para formulario Modificar Reserva.
     * @param id_Reserva Identificador unico de la reserva
     * @param id_Cliente Identificador unico del cliente
     * @param id_Moto Identificador unico de la motocicleta
     * @param fecha_Inicio Fecha de inicio de la reserva
     * @param fecha_Fin Fecha de fin de la reserva
     * @param estado Estado de la reserva
     */
	public Reserva(int id_Reserva, int id_Cliente, int id_Moto, Date fecha_Inicio, Date fecha_Fin, String estado) {
		super();
		this.id_Reserva = id_Reserva;
		this.id_Cliente = id_Cliente;
		this.id_Moto = id_Moto;
		this.fecha_Inicio = fecha_Inicio;
		this.fecha_Fin = fecha_Fin;
		this.estado = estado;
	}
			
	/**
     * Constructor para insertar reserva.
     * @param id_Moto Identificador unico de la motocicleta
     * @param id_Cliente Identificador unico del cliente
     * @param fecha_Realiza Fecha en que se realiza la reserva
     * @param fecha_Inicio Fecha de inicio de la reserva
     * @param fecha_Fin Fecha de fin de la reserva
     */
	public Reserva(int id_Moto, int id_Cliente, Date fecha_Realiza, Date fecha_Inicio, Date fecha_Fin) {
		super();
		this.id_Moto = id_Moto;
		this.id_Cliente = id_Cliente;
		this.fecha_Realiza = fecha_Realiza;
		this.fecha_Inicio = fecha_Inicio;
		this.fecha_Fin = fecha_Fin;
	}

	// Getters y setters:
	
	/**
     * Obtiene el identificador de la reserva.
     * @return id_Reserva Identificador de la reserva
     */
	public int getId_Reserva() {
		return id_Reserva;
	}

	/**
     * Establece el identificador de la reserva.
     * @param id_Reserva Identificador de la reserva
     */
	public void setId_Reserva(int id_Reserva) {
		this.id_Reserva = id_Reserva;
	}
	
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
     * Obtiene el identificador del cliente.
     * @return id_Cliente Identificador del cliente
     */
	public int getId_Cliente() {
		return id_Cliente;
	}
	
	/**
     * Establece el identificador del cliente.
     * @param id_Cliente Identificador del cliente
     */
	public void setId_Cliente(int id_Cliente) {
		this.id_Cliente = id_Cliente;
	}
	
	/**
     * Obtiene el identificador del administrador.
     * @return id_Admin Identificador del administrador
     */
	public int getId_Admin() {
		return id_Admin;
	}
	
	/**
     * Establece el identificador del administrador.
     * @param id_Admin Identificador del administrador
     */
	public void setId_Admin(int id_Admin) {
		this.id_Admin = id_Admin;
	}
	
	/**
     * Obtiene la fecha en que se realiza la reserva.
     * @return fecha_Realiza Fecha en que se realiza la reserva
     */
	public Date getFecha_Realiza() {
		return fecha_Realiza;
	}
	
	/**
     * Establece la fecha en que se realiza la reserva.
     * @param fecha_Realiza Fecha en que se realiza la reserva
     */
	public void setFecha_Realiza(Date fecha_Realiza) {
		this.fecha_Realiza = fecha_Realiza;
	}
	
	/**
     * Obtiene la fecha de inicio de la reserva.
     * @return fecha_Inicio Fecha de inicio de la reserva
     */
	public Date getFecha_Inicio() {
		return fecha_Inicio;
	}
	
	/**
     * Establece la fecha de inicio de la reserva.
     * @param fecha_Inicio Fecha de inicio de la reserva
     */
	public void setFecha_Inicio(Date fecha_Inicio) {
		this.fecha_Inicio = fecha_Inicio;
	}
	
	/**
     * Obtiene la fecha de fin de la reserva.
     * @return fecha_Fin Fecha de fin de la reserva
     */
	public Date getFecha_Fin() {
		return fecha_Fin;
	}
	
	/**
     * Establece la fecha de fin de la reserva.
     * @param fecha_Fin Fecha de fin de la reserva
     */
	public void setFecha_Fin(Date fecha_Fin) {
		this.fecha_Fin = fecha_Fin;
	}
	
	/**
     * Obtiene el estado de la reserva.
     * @return estado Estado de la reserva
     */
	public String getEstado() {
		return estado;
	}
	
	/**
     * Establece el estado de la reserva.
     * @param estado Estado de la reserva
     */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
     * Convierte el objeto Reserva a una cadena de texto.
     * @return Representacion en cadena del objeto Reserva
     */
		@Override
	public String toString() {
		return "Reserva [id_Reserva=" + id_Reserva + ", id_Moto=" + id_Moto + ", id_Admin=" + id_Admin
				+ ", fecha_Realiza=" + fecha_Realiza + ", fecha_Inicio=" + fecha_Inicio + ", fecha_Fin=" + fecha_Fin
				+ ", estado=" + estado + "]";
	}
		
	 /**
	  * Metodo para insertar una reserva en la base de datos.
	  * @return true si la reserva fue insertada correctamente, false en caso contrario
	  * @throws SQLException Si ocurre un error en la base de datos
	  */
	 // Crear una instancia de DaoReserva
	 // Insertar la reserva actual en la base de datos
	 // Retornar el resultado de la inserción
	  public boolean crearReserva() throws SQLException {	      
	      DaoReserva daoReserva = new DaoReserva();	      
	      boolean reservaInsertada = daoReserva.insertarReserva(this);	      
	      return reservaInsertada;
	}
	  
	 /**
	  * Metodo para listar todas las reservas en formato JSON. (Administrador)
	  * @return Cadena JSON con la lista de todas las reservas
	  * @throws SQLException Si ocurre un error en la base de datos
	  */
	 // Cadena JSON resultante
	 // Crear objeto Gson
	 // Crear instancia de DaoReserva
	 // Meter en el objeto json lo que genere el objetoGson con el método toJson (lo convierte a json)
	 // Convertir la lista de reservas a JSON
	 // Retornar la cadena JSON
	  public String listarReservas() throws SQLException {	      
	      String json = "";	      
	      Gson objetoGson = new Gson();	      
	      DaoReserva resultado = new DaoReserva();	      
	      json = objetoGson.toJson(resultado.listar());	      
	      return json;
	}
	  
	 /**
	  * Metodo para listar todas las reservas de un cliente específico en formato JSON.
	  * @param id_Cliente Identificador unico del cliente
	  * @return Cadena JSON con la lista de reservas del cliente
	  * @throws SQLException Si ocurre un error en la base de datos
	  */
	 // Pasar como argumento el id_Cliente para que solo recupere el listado de la sesión activa
	 // Cadena JSON resultante
	 // Crear objeto Gson
	 // Crear instancia de DaoReserva
	 // Meter en el objeto json lo que genere el objetoGson con el método toJson (lo convierte a json)
	 // Convertir la lista de reservas del cliente a JSON
	 // Retornar la cadena JSON
	  public String listarReservasCliente(int id_Cliente) throws SQLException {	      
	      String json = "";	      
	      Gson objetoGson = new Gson();	        
	      DaoReserva resultado = new DaoReserva();	        
	      json = objetoGson.toJson(resultado.listarResCliente(id_Cliente));	        
	      return json;
	 }	  
		
	  /**
	   * Metodo para recuperar los datos de una reserva especifica para modificación posterior.
	   * @param id_Reserva Identificador de la reserva
	   * @throws SQLException Si ocurre un error en la base de datos
	   */
	  // Crear instancia de DaoReserva
      // Leer los datos de la reserva desde la base de datos
	  // Crear una reservaa auxiliar en base al id_Reserva con todos los datos
      // Establecer los datos recuperados en la reserva actual
	  public void recuperarReserva(int id_Reserva) throws SQLException {
	      DaoReserva dao = new DaoReserva();
	      Reserva r = dao.leerFormulario(id_Reserva);
	      
	      this.setId_Reserva(r.getId_Reserva());
	      this.setId_Cliente(r.getId_Cliente());
	      this.setId_Moto(r.getId_Moto());
	      this.setFecha_Inicio(r.getFecha_Inicio());
	      this.setFecha_Fin(r.getFecha_Fin());
	      this.setEstado(r.getEstado());
	 }
	
	  /**
	   * Metodo para obtener los datos de la reserva en formato JSON para el metoro recuperarReserva.
	   * @return Cadena JSON con los datos de la reserva
	   */
	  // Cadena JSON resultante
	  // Crear objeto Gson
	  // Meter en el json lo que devuelve el objeto Gson y darle como parametro a mi mismo(Reserva)
	  // Convertir la reserva actual a JSON
	  // Retornar la cadena JSON
	  public String dameJson() {	        
	      String json = "";	        
	      Gson gson = new Gson();	        
	      json = gson.toJson(this);	        
	      return json;
	 }
	  
	  /**
	   * Metodo para insertar la actualización de datos de la reserva en la base de datos.
	   * @return true si la actualización fue exitosa, false en caso contrario
	   * @throws SQLException Si ocurre un error en la base de datos
	   */
	  // Crear una instancia de DaoReserva
      // Actualizar los datos de la reserva en la base de datos
	  public boolean actualizarReserva() throws SQLException {	        
	      DaoReserva daoReserva = new DaoReserva();
	      return daoReserva.actualizarRes(this);
	 }
	  
	  /**
	   * Metodo para eliminar una reserva de la base de datos.
	   * @throws SQLException Si ocurre un error en la base de datos
	   */
      // Crear una instancia de DaoReserva
      // Eliminar la reserva actual de la base de datos

	  public void eliminarReserva() throws SQLException {
	      DaoReserva borrar = new DaoReserva();
	      borrar.eliminarRese(this);
	 }
	  
}
