package modelo;

import java.sql.Date;
import java.sql.SQLException;

import com.google.gson.Gson;

import DAO.DaoReserva;

public class ReservaViewModel {

	private int id_Reserva;
	private String marca;
	private String modelo;
	private double precio_Dia;
	private Date fecha_Realiza;
	private Date fecha_Inicio;
	private Date fecha_Fin;
	private String estado;
	
	
	
	
	public ReservaViewModel() {
		super();
	}

	public ReservaViewModel(int id_Reserva, String marca, String modelo, double precio_Dia, Date fecha_Realiza,
			Date fecha_Inicio, Date fecha_Fin, String estado) {
		super();
		this.id_Reserva = id_Reserva;
		this.marca = marca;
		this.modelo = modelo;
		this.precio_Dia = precio_Dia;
		this.fecha_Realiza = fecha_Realiza;
		this.fecha_Inicio = fecha_Inicio;
		this.fecha_Fin = fecha_Fin;
		this.estado = estado;
	}
	
	public int getId_Reserva() {
		return id_Reserva;
	}
	public void setId_Reserva(int id_Reserva) {
		this.id_Reserva = id_Reserva;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public double getPrecio_Dia() {
		return precio_Dia;
	}
	public void setPrecio_Dia(double precio_Dia) {
		this.precio_Dia = precio_Dia;
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
	
}
