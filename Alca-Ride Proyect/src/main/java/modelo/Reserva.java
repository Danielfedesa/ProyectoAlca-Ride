package modelo;

import java.sql.Date;
import java.sql.SQLException;

import com.google.gson.Gson;

import DAO.DaoReserva;

public class Reserva {
	

	private int id_Reserva;
	private int id_Moto;
	private int id_Cliente;
	private int id_Admin = 1;
	private Date fecha_Realiza;
	private Date fecha_Inicio;
	private Date fecha_Fin;
	private String estado;
	
	
	public Reserva() {
		super();
	}

	//Constructor completo Reserva
	public Reserva(int id_Reserva, Date fecha_Realiza, Date fecha_Inicio, Date fecha_Fin, String estado) {
		super();
		this.id_Reserva = id_Reserva;
		this.fecha_Realiza = fecha_Realiza;
		this.fecha_Inicio = fecha_Inicio;
		this.fecha_Fin = fecha_Fin;
		this.estado = estado;
	}
	
	//Constructor completo para mostrar en listado ADMINISTRADOR Y CLIENTE
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
	
	//Constructor para formulario Modificar Reserva
	public Reserva(int id_Reserva, int id_Cliente, int id_Moto, Date fecha_Inicio, Date fecha_Fin, String estado) {
		super();
		this.id_Reserva = id_Reserva;
		this.id_Cliente = id_Cliente;
		this.id_Moto = id_Moto;
		this.fecha_Inicio = fecha_Inicio;
		this.fecha_Fin = fecha_Fin;
		this.estado = estado;
	}
		
	
	//Constructor para insertar reserva
	public Reserva(int id_Moto, int id_Cliente, Date fecha_Realiza, Date fecha_Inicio, Date fecha_Fin) {
		super();
		this.id_Moto = id_Moto;
		this.id_Cliente = id_Cliente;
		this.fecha_Realiza = fecha_Realiza;
		this.fecha_Inicio = fecha_Inicio;
		this.fecha_Fin = fecha_Fin;
	}

		public int getId_Reserva() {
		return id_Reserva;
	}

	public void setId_Reserva(int id_Reserva) {
		this.id_Reserva = id_Reserva;
	}

	public int getId_Moto() {
		return id_Moto;
	}

	public void setId_Moto(int id_Moto) {
		this.id_Moto = id_Moto;
	}
	
	public int getId_Cliente() {
		return id_Cliente;
	}

	public void setId_Cliente(int id_CLiente) {
		this.id_Cliente = id_CLiente;
	}

	public int getId_Admin() {
		return id_Admin;
	}

	public void setId_Admin(int id_Admin) {
		this.id_Admin = id_Admin;
	}

	public Date getFecha_Realiza() {
		return fecha_Realiza;
	}

	public void setFecha_Realiza(Date fecha_Realiza) {
		this.fecha_Realiza = fecha_Realiza;
	}

	public Date getFecha_Inicio() {
		return fecha_Inicio;
	}

	public void setFecha_Inicio(Date fecha_Inicio) {
		this.fecha_Inicio = fecha_Inicio;
	}

	public Date getFecha_Fin() {
		return fecha_Fin;
	}

	public void setFecha_Fin(Date fecha_Fin) {
		this.fecha_Fin = fecha_Fin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
		@Override
	public String toString() {
		return "Reserva [id_Reserva=" + id_Reserva + ", id_Moto=" + id_Moto + ", id_Admin=" + id_Admin
				+ ", fecha_Realiza=" + fecha_Realiza + ", fecha_Inicio=" + fecha_Inicio + ", fecha_Fin=" + fecha_Fin
				+ ", estado=" + estado + "]";
	}
		
		//Método insertar reserva
		public boolean crearReserva() throws SQLException {
		    DaoReserva daoReserva = new DaoReserva();
		    boolean reservaInsertada = daoReserva.insertarReserva(this);
		    return reservaInsertada;
		}


		//Método listar reservas Administrador (todas las reservas)
		//Hago un método que obtiene los elementos del Dao y los convierte en json
		public String listarReservas() throws SQLException {
			String json = "";
			Gson objetoGson = new Gson();
			//Meto en el objeto json lo que genere el objetoGson con el método toJson (lo convierte a json)
			DaoReserva resultado = new DaoReserva();
			json = objetoGson.toJson(resultado.listar());
			return json;
		}
		
		//Método listar reservas Cliente
		//Hago un método que obtiene los elementos del Dao y los convierte en json
		//Le paso como argumento el id_Cliente para que solo me recupere el listado de la sesión activa
		public String listarReservasCliente(int id_Cliente) throws SQLException {
			String json = "";
			Gson objetoGson = new Gson();
			//Meto en el objeto json lo que genere el objetoGson con el método toJson (lo convierte a json)
			DaoReserva resultado = new DaoReserva();
			json = objetoGson.toJson(resultado.listarResCliente(id_Cliente));
			return json;
		}
		
		//Método modificar reserva
		// Método recuperarReserva para modificarla despues (formulario modificar)
				public void recuperarReserva(int id_Reserva) throws SQLException {
					System.out.println("Llego al metodo recuperarReserva");
					//Genero un objeto dao
					DaoReserva dao = new DaoReserva();
					//Creo una reservaa auxiliar en base al id_Reserva con todos los datos
					Reserva r = dao.leerFormulario(id_Reserva);
					
					this.setId_Reserva(r.getId_Reserva());
					this.setId_Cliente(r.getId_Cliente());
					this.setId_Moto(r.getId_Moto());
					this.setFecha_Inicio(r.getFecha_Inicio());
					this.setFecha_Fin(r.getFecha_Fin());
					this.setEstado(r.getEstado());
				}	
				
				//Método para hacer un json con los datos del método recuperarReserva.
				public String dameJson() {
					System.out.println("Metodo dameJson");

					//Creo variable con cadena vacía
					String json = "";
					// Creo objeto Gson llamado gson
					Gson gson = new Gson();
					//En el json meto lo que devuelve el objeto Gson y le doy de parametro a mi mismo(Usuario)
					json = gson.toJson(this);
					//Nos devuelve el json
					System.out.println("He creado el Json");

					return json;
				}
				
				//Metodo actualizar reserva para insertar las modificaciones en la base de datos
				public boolean actualizarReserva() throws SQLException {
					DaoReserva daoReserva = new DaoReserva();
					return daoReserva.actualizarRes(this);
				}
	
				//Método eliminar reserva
				public void eliminarReserva() throws SQLException {
				    DaoReserva borrar = new DaoReserva();
				    borrar.eliminarRese(this); 
				}
}
