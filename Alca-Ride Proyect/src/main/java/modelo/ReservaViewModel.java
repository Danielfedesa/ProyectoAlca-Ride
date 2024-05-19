package modelo;

import java.sql.Date;

/**
 * Clase ReservaViewModel que representa la vista de la informacion de una reserva.
 * Se utiliza en la lista de reservas del CLIENTE para proporcionar a la misma los datos de MARCA y MODELO.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 04/2024
 */
public class ReservaViewModel {

	// Atributos de la clase ReservaViewModel:
	private int id_Reserva;
	private String marca;
	private String modelo;
	private double precio_Dia;
	private Date fecha_Realiza;
	private Date fecha_Inicio;
	private Date fecha_Fin;
	private String estado;
	
	/**
     * Constructor por defecto (vacio).
     */
	public ReservaViewModel() {
		super();
	}

	/**
     * Constructor completo ReservaViewModel.
     * @param id_Reserva Identificador unico de la reserva
     * @param marca Marca de la motocicleta
     * @param modelo Modelo de la motocicleta
     * @param precio_Dia Precio por dia de la motocicleta
     * @param fecha_Realiza Fecha en que se realiza la reserva
     * @param fecha_Inicio Fecha de inicio de la reserva
     * @param fecha_Fin Fecha de fin de la reserva
     * @param estado Estado de la reserva
     */
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
     * Obtiene el precio por dia de la motocicleta.
     * @return precio_Dia Precio por dia de la motocicleta
     */
    public double getPrecio_Dia() {
        return precio_Dia;
    }

    /**
     * Establece el precio por dia de la motocicleta.
     * @param precio_Dia Precio por dia de la motocicleta
     */
    public void setPrecio_Dia(double precio_Dia) {
        this.precio_Dia = precio_Dia;
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

}