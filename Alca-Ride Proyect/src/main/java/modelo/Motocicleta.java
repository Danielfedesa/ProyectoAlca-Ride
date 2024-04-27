package modelo;

import java.sql.SQLException;

import DAO.DaoMotocicleta;

public class Motocicleta {
	
	//Atributos de la clase Motocicleta:
	private int id_Moto;
	private String tipo;
	private String matricula;
	private String cilindrada;
	private String tipo_Carnet;
	private String marca;
	private String modelo;
	private String anio;
	private double precio_Dia;
	
	
	public Motocicleta() {
		
	}
	
	//Constructor sin id_Moto.
	public Motocicleta(String tipo, String matricula, String cilindrada, String tipo_Carnet, String marca,
			String modelo, String anio, double precio_Dia) {
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

	//Constructor completo:
	public Motocicleta(int id_Moto, String tipo, String matricula, String cilindrada, String tipo_Carnet, String marca,
			String modelo, String anio, double precio_Dia) {
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

	//Getters y setters:
	public int getId_Moto() {
		return id_Moto;
	}

	public void setId_Moto(int id_Moto) {
		this.id_Moto = id_Moto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(String cilindrada) {
		this.cilindrada = cilindrada;
	}

	public String getTipo_Carnet() {
		return tipo_Carnet;
	}

	public void setTipo_Carnet(String tipo_Carnet) {
		this.tipo_Carnet = tipo_Carnet;
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

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public double getPrecio_Dia() {
		return precio_Dia;
	}

	public void setPrecio_Dia(double precio_Dia) {
		this.precio_Dia = precio_Dia;
	}
	

	//Método toString:
	@Override
	public String toString() {
		return "Motocicleta [id_Moto=" + id_Moto + ", tipo=" + tipo + ", matricula=" + matricula + ", cilindrada="
				+ cilindrada + ", tipo_Carnet=" + tipo_Carnet + ", marca=" + marca + ", modelo=" + modelo + ", anio="
				+ anio + ", precio_Dia=" + precio_Dia + "]";
	}
	
	//Método para crear Motocicleta:
		public void crearMotocicleta() throws SQLException { //Añado excepción.
			DaoMotocicleta moto = new DaoMotocicleta();//Importamos el objeto
			moto.insertar(this);
		}

	//Método para leer Motocicleta:
	
	//Método para actualizar Motocicleta:
		
	//Método para eliminar Motocicleta:
}
