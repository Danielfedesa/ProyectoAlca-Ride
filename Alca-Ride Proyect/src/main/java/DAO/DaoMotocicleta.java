package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.ConexionDB;
import modelo.Motocicleta;

/**
 * Clase para realizar operaciones relacionadas con la tabla 'motocicletas' en
 * la base de datos.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 04/2024
 */
public class DaoMotocicleta {

	/**
	 * Atributo estatico que gestiona la conexion a la base de datos. en toda la
	 * aplicacion, permitiendo la reutilizacion de la misma conexion. Inicialmente,
	 * la instancia esta establecida en null.
	 * @see java.sql.Connection
	 */
	// Genero un atributo de tipo Connection llamado "con" y la instancio en null:
	public static Connection con = null;

	/**
	 * Constructor de la clase DaoMotocicleta.
	 * @throws SQLException Si hay un error de conexion
	 */
	// Nada mas se crea el objeto DaoMotocicleta le decimos al atributo con que
	// conecte con DBConnection.getConexion()
	// para que se inicie la conexion nada mas se instancie el objeto.
	public DaoMotocicleta() throws SQLException {
		this.con = ConexionDB.getConexion();
	}

	/**
	 * Metodo para insertar una nueva motocicleta en la base de datos.
	 * @param m Objeto Motocicleta para insertar
	 * @throws SQLException Si hay un error de insercion
	 */
	public void insertar(Motocicleta m) throws SQLException {
		// Sentencia sql para insertar un registro en la tabla motocicleta:
		String sql = "INSERT INTO Motocicletas (tipo, matricula, cilindrada, tipo_Carnet, marca, modelo, anio, precio_Dia) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		// Prepara la sentencia SQL
		PreparedStatement ps = con.prepareStatement(sql);
		// Establece los parámetros en la sentencia SQL.
		ps.setString(1, m.getTipo());
		ps.setString(2, m.getMatricula());
		ps.setInt(3, m.getCilindrada());
		ps.setString(4, m.getTipo_Carnet());
		ps.setString(5, m.getMarca());
		ps.setString(6, m.getModelo());
		ps.setInt(7, m.getAnio());
		ps.setDouble(8, m.getPrecio_Dia());

		// Ejecuta la sentencia y actualiza el número de filas afectadas.
		int filas = ps.executeUpdate();

		// Cierra el PreparedStatement.
		ps.close();
	}

	/**
	 * Metodo para listar todas las motocicletas de la base de datos.
	 * @return ArrayList de Motocicleta con los datos de las motocicletas
	 * @throws SQLException Si hay un error de lectura en base de datos
	 */
	public ArrayList<Motocicleta> listar() throws SQLException {
		// Sentencia para seleccionar todos los registros de la tabla motocicletas.
		String sql = "SELECT id_Moto, tipo, matricula, cilindrada, tipo_Carnet, marca, modelo, anio, precio_Dia FROM motocicletas";
		// Hacer el prepareStatement pero esta vez de vuelta, lo haré con un select.
		PreparedStatement ps = con.prepareStatement(sql);
		// Almacenar en un objeto tipo ResultSet que es el que utiliza MySQL para
		// almacenar esa colección sin objetos, sino con los datos que recibe la BD
		// Recibir los datos utilizando el executeQuery.
		ResultSet rs = ps.executeQuery();

		// Inicializa el ArrayList para almacenar los objetos Motocicleta
		ArrayList<Motocicleta> result = null;
		// Lee el resultado del ResultSet y crea objetos Motocicleta.
		// Quiero que el ArrayList contenga los objetos de tipo Motocicleta para
		// trabajar con ellos en mi modelo de objetos.
		while (rs.next()) {
			// Preguntar si el array sigue null, si no es así lo inicializamos
			if (result == null) {
				result = new ArrayList<Motocicleta>();
			}
			// Añadir al result los elementos que obtengo de la BD definiendo los tipos de
			// dato que necesito recuperar.
			result.add(new Motocicleta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9)));
		}

		// Cierra el PreparedStatement.
		ps.close();

		// Devuelve el ArrayList de Motocicleta.
		return result;

	}

	/**
	 * Metodo para leer una motocicleta de la base de datos por su ID y modificarla
	 * posteriormente.
	 * @param id_Moto ID de la motocicleta a leer
	 * @return Objeto Motocicleta con los datos de la motocicleta leida
	 * @throws SQLException Si hay un error de lectura
	 */
	public Motocicleta leerFormulario(int id_Moto) throws SQLException {
		// Sentencia para seleccionar un registro de la tabla motocicletas por su ID.
		// Mandar el id y construyo el SQL
		String sql = "SELECT * FROM motocicletas WHERE id_Moto=?";
		// Prepara la sentencia SQL
		PreparedStatement ps = con.prepareStatement(sql);
		// Mandar el preparedStatement y el id
		ps.setInt(1, id_Moto);

		// Ejecutar la consulta y obtener el resultado.
		ResultSet rs = ps.executeQuery();
		rs.next();

		// Crea un objeto Motocicleta con los datos obtenidos del resultSet
		Motocicleta m = new Motocicleta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));

		// Cierra el PreparedStatement.
		ps.close();

		// Retorna el objeto Motocicleta.
		return m;
	}

	/**
	 * Metodo para insertar la actualizacion de una motocicleta en la base de datos. 
	 * @param moto Objeto Motocicleta con los datos actualizados
	 * @return true si la actualizacion fue correcta, false si esta falla
	 */
	public boolean actualizarMoto(Motocicleta moto) {

		try {
			// Sentencia para actualizar un registro de la tabla motocicletas por su ID.
			String sql = "UPDATE motocicletas SET tipo=?, matricula=?, cilindrada=?, tipo_Carnet=?, marca=?, modelo=?, anio=?, precio_Dia=? WHERE id_Moto=?";

			// Prepara la sentencia SQL
			PreparedStatement ps = con.prepareStatement(sql);

			// Establece los parámetros en la sentencia SQL.
			ps.setString(1, moto.getTipo());
			ps.setString(2, moto.getMatricula());
			ps.setInt(3, moto.getCilindrada());
			ps.setString(4, moto.getTipo_Carnet());
			ps.setString(5, moto.getMarca());
			ps.setString(6, moto.getModelo());
			ps.setInt(7, moto.getAnio());
			ps.setDouble(8, moto.getPrecio_Dia());
			ps.setInt(9, moto.getId_Moto());

			// Ejecuta la sentencia y obtiene el resultado.
			int rs = ps.executeUpdate();
			// Retorna true si se actualizó correctamente, false si no.
			if (rs == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Metodo para eliminar una motocicleta de la base de datos.
	 * @param m Objeto Motocicleta a eliminar
	 * @throws SQLException Si hay un error en base de datos
	 */
	public static void eliminarMoto(Motocicleta m) throws SQLException {
		// Sentencia para eliminar un registro de la tabla motocicletas por su ID.
		String sql = "DELETE FROM motocicletas WHERE id_Moto = ?";

		// Prepara la sentencia SQL
		PreparedStatement ps = con.prepareStatement(sql);
		// Mandar el preparedStatement y el id
		ps.setInt(1, m.getId_Moto());

		// Ejecuta la sentencia.
		int filas = ps.executeUpdate();

		// Cierra el PreparedStatement.
		ps.close();
	}

}
