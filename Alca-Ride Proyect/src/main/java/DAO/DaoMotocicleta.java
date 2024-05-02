package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.ConexionDB;
import modelo.Motocicleta;

public class DaoMotocicleta {

	
	//Genero un atributo de tipo Connection llamado "con" y la instancio en null:
	public static Connection con = null; //Importo Connection.
	
	
	//Nada más se crea el objeto DaoMotocicleta le decimos al atributo con que conecte con DBConnection.getConexion() para que
	//se inicie la conexión nada más se instancie el objeto.
	public DaoMotocicleta() throws SQLException {
		this.con = ConexionDB.getConexion(); //Importo y añado excepción.
	}
	
	//Método público que inserta recibiendo un onjeto Motocicleta.
	public void insertar(Motocicleta m) throws SQLException { //Importo ModeloMotocicleta.
		
		//Sentencia sql para insertar en la tabla Motocicleta:
		String sql = "INSERT INTO Motocicletas (tipo, matricula, cilindrada, tipo_Carnet, marca, modelo, anio, precio_Dia) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		//le pasamos la variable con la sentencia sql que acabamos de meter.
		PreparedStatement ps = con.prepareStatement(sql); //Importo PreparedStatement y añado excepción.
		//Al estar usando el preparestatement debemos cargar los datos (En este caso son todos String)
		ps.setString(1, m.getTipo());
		ps.setString(2, m.getMatricula());
		ps.setInt(3, m.getCilindrada());
		ps.setString(4, m.getTipo_Carnet());
		ps.setString(5, m.getMarca());
		ps.setString(6, m.getModelo());
		ps.setInt(7, m.getAnio());
		ps.setDouble(8, m.getPrecio_Dia());
		
	//Hago un update para comprobar:
	int filas = ps.executeUpdate();
	
	//Cierro el preparedStatement:
	ps.close();
	}
	
	//Método para listar motocicletas.
	public ArrayList<Motocicleta> listar() throws SQLException {
		
		String sql = "SELECT id_Moto, tipo, matricula, cilindrada, tipo_Carnet, marca, modelo, anio, precio_Dia FROM motocicletas";
		PreparedStatement ps = con.prepareStatement(sql);	
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Motocicleta> result = null;
		while(rs.next()) {
			if(result == null) {
				result = new ArrayList<Motocicleta>();
			}
			result.add(new Motocicleta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9)));
		}
		
		ps.close();
		
		return result;

	}
	
}
