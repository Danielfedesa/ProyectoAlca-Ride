package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.ConexionDB;
import modelo.Reserva;
import modelo.ReservaViewModel;

/**
 * Clase para realizar operaciones relacionadas con la tabla 'reservas' en la
 * base de datos.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 04/2024
 */
public class DaoReserva {

	/**
	 * Atributo estatico que gestiona la conexion a la base de datos. en toda la
	 * aplicacion, permitiendo la reutilizacion de la misma conexion. Inicialmente,
	 * la instancia esta establecida en null.
	 * @see java.sql.Connection
	 */
	// Genera un atributo de tipo Connection llamado "con" y la instancia en null:
	public static Connection con = null;

	/**
	 * Constructor de la clase DaoReserva para establecer conexion con la base de datos.
	 * @throws SQLException Si hay un error de conexion
	 */
	// Nada más se crea el objeto DaoMotocicleta le decimos al atributo con que
	// conecte con DBConnection.getConexion()
	// para que se inicie la conexión nada más se instancie el objeto.
	public DaoReserva() throws SQLException {
		this.con = ConexionDB.getConexion();
	}

	/**
	 * Metodo para insertar una nueva reserva en la base de datos. 
	 * @param reserva Objeto Reserva para insertar
	 * @return true si se inserto correctamente, false si no se inserto
	 * @throws SQLException Si hay un error de SQL
	 */
	public boolean insertarReserva(Reserva reserva) throws SQLException {
		Connection con = ConexionDB.getConexion();

		// Inicializa una variable para comprobar conflictos de fechas
		boolean comprobar = false;

		// Sentencia para comprobar si existen coincidencias de registro en la tabla reservas.
		// Esta sentencia comprueba que no existan coincidencias entre las fechas de reservas ya
		// realizadas de un mismo vehículo y que las mismas no tengan el estado de canceladas.
		String sqlFechas = "SELECT * FROM reservas "
				+ " WHERE id_Moto = ? "
				+ " AND ( "
				+ "     (? >= fecha_Inicio AND ? <= fecha_Fin) "
				+ "     OR \n"
				+ "     (? >= fecha_Inicio AND ? <= fecha_Fin) "
				+ "     OR \n"
				+ "     (fecha_Inicio <= ? AND fecha_Fin >= ?) "
				+ "     OR \n"
				+ "		(fecha_Inicio >= ? AND fecha_Fin <= ?) "
				+ " ) "
				+ " AND estado <> 'Cancelada';";

		// Prepara la sentencia SQL
		PreparedStatement psFechas = con.prepareStatement(sqlFechas);
		// Establece los parámetros en la sentencia SQL
		psFechas.setInt(1, reserva.getId_Moto());
		psFechas.setDate(2, reserva.getFecha_Inicio());
		psFechas.setDate(3, reserva.getFecha_Inicio());
		psFechas.setDate(4, reserva.getFecha_Fin());
		psFechas.setDate(5, reserva.getFecha_Fin());
		psFechas.setDate(6, reserva.getFecha_Inicio());
		psFechas.setDate(7, reserva.getFecha_Fin());
		psFechas.setDate(8, reserva.getFecha_Inicio());
		psFechas.setDate(9, reserva.getFecha_Fin());
		// Almacenar en un objeto tipo ResultSet que es el que utiliza MySQL para
		// almacenar esa colección sin objetos, sino con los datos que recibe la BD
		// Recibir los datos utilizando el executeQuery.
		ResultSet rs = psFechas.executeQuery();

		// Compruebo si existen reservas que coincidan con las fechas y vehículos
		if (rs.next()) {
			int count = rs.getInt("count");
			if (count > 0) {
				comprobar = true; // Hay conflictos, se debe comprobar
			}
		}

		// Si no hay conflictos inserta la reserva
		if (!comprobar) {
			// Sentencia para insertar un nuevo registro en la tabla reservas.
			String sql = "INSERT INTO reservas (id_Moto, id_Cliente, id_Admin, fecha_Realiza, fecha_Inicio, fecha_Fin, estado) VALUES (?, ?, 1, ?, ?, ?, 'Pendiente')";
			// Prepara la sentencia SQL
			PreparedStatement ps = con.prepareStatement(sql);

			// Establece los parámetros en la sentencia SQL.
			ps.setInt(1, reserva.getId_Moto());
			ps.setInt(2, reserva.getId_Cliente());
			ps.setDate(3, reserva.getFecha_Realiza());
			ps.setDate(4, reserva.getFecha_Inicio());
			ps.setDate(5, reserva.getFecha_Fin());
			ps.executeUpdate();

			// Cierra el PreparedStatement.
			ps.close();

			// Insertada correctamente
			return true;

			// No se pudo insertar la reserva
		} else {
			System.out.println("La reserva coincide con otras existentes.");
			return false;
		}
	}

	/**
	 * Metodo para listar todas las reservas en el area del administrador. 
	 * @return ArrayList de Reserva con las reservas
	 * @throws SQLException Si hay un error de lectura en base de datos
	 */
	// Crear un arraylist de la clase Reserva
	public ArrayList<Reserva> listar() throws SQLException {
		// Sentencia para seleccionar todos los registros de la tabla reservas
		String sql = "SELECT id_Reserva, id_Moto, id_Cliente, id_Admin, fecha_Realiza, fecha_Inicio, fecha_Fin, estado FROM reservas";
		// Hacer el prepareStatement pero esta vez de vuelta, lo haré con un select.
		PreparedStatement ps = con.prepareStatement(sql);
		// Almacenar en un objeto tipo ResultSet que es el que utiliza MySQL para
		// almacenar esa colección sin objetos, sino con los datos que recibe la BD
		// Recibir los datos utilizando el executeQuery.
		ResultSet rs = ps.executeQuery();

		// Inicializa el ArrayList para almacenar los objetos Reserva.
		ArrayList<Reserva> result = new ArrayList<>();

		// Lee el resultado del ResultSet y crea objetos Reserva.
		// Quiero que el ArrayList contenga los objetos de tipo Reserva para trabajar
		// con ellos en mi modelo de objetos.
		while (rs.next()) {
			int id_Reserva = rs.getInt("id_Reserva");
			int id_Moto = rs.getInt("id_Moto");
			int id_Cliente = rs.getInt("id_Cliente");
			int id_Admin = rs.getInt("id_Admin");
			Date fecha_Realiza = rs.getDate("fecha_Realiza");
			Date fecha_Inicio = rs.getDate("fecha_Inicio");
			Date fecha_Fin = rs.getDate("fecha_Fin");
			String estado = rs.getString("estado");

			// Añadir al result los elementos que obtengo de la BD definiendo los tipos de
			// dato que necesito recuperar.
			Reserva reserva = new Reserva(id_Reserva, id_Moto, id_Cliente, id_Admin, fecha_Realiza, fecha_Inicio, fecha_Fin, estado);
			result.add(reserva);
		}

		// Cierra el PreparedStatement.
		ps.close();

		// Devuelve el ArrayList de Reserva.
		return result;
	}

	/**
	 * Metodo para listar las reservas de un cliente en su area personal mostrando
	 * tambien marca y modelo de ReservaViewModel. 
	 * @param id_Cliente ID del cliente para listar sus reservas
	 * @return ArrayList de ReservaViewModel con las reservas del cliente
	 * @throws SQLException Si hay un error de lectura en base de datos
	 */
	// Le paso como argumento el id_Cliente para que solo me muestre las reservas de
	// la sesion activa
	public ArrayList<ReservaViewModel> listarResCliente(int id_Cliente) throws SQLException {

		// Sentencia SQL para que seleccione los datos de las motos mezclando las
		// tablas, siendo el id_Moto
		// el dato que determine el resto de parámetros
		String sql = "SELECT r.id_Reserva, r.id_Moto, r.id_Cliente, r.id_Admin, r.fecha_Realiza, "
				+ "r.fecha_Inicio, r.fecha_Fin, r.estado, m.marca, m.modelo, m.precio_Dia "
				+ "FROM reservas r " 
				+ "JOIN motocicletas m ON r.id_Moto = m.id_Moto "
				+ "WHERE r.id_Cliente = ?";

		// Prepara la sentencia SQL
		PreparedStatement ps = con.prepareStatement(sql);
		// Mandar el preparedStatement y el id
		ps.setInt(1, id_Cliente); // Estableces el id_Cliente en la consulta SQL
		ResultSet rs = ps.executeQuery();

		// Inicializa el ArrayList para almacenar los objetos ReservaViewModel.
		ArrayList<ReservaViewModel> result = new ArrayList<>();

		// Lee el resultado del ResultSet y crea objetos ReservaViewModel.
		// Quiero que el ArrayList contenga los objetos de tipo Reserva para trabajar
		// con ellos en mi modelo de objetos.
		while (rs.next()) {
			int id_Reserva = rs.getInt("id_Reserva");
			String marca = rs.getString("marca");
			String modelo = rs.getString("modelo");
			double precio = rs.getDouble("precio_Dia");
			Date fecha_Realiza = rs.getDate("fecha_Realiza");
			Date fecha_Inicio = rs.getDate("fecha_Inicio");
			Date fecha_Fin = rs.getDate("fecha_Fin");
			String estado = rs.getString("estado");

			// Crea un objeto ReservaViewModel con los datos obtenidos del resultSet
			ReservaViewModel reserva = new ReservaViewModel(id_Reserva, marca, modelo, precio, fecha_Realiza,
					fecha_Inicio, fecha_Fin, estado);
			// Añadir al result los elementos que obtengo de la BD definiendo los tipos de
			// dato que necesito recuperar.
			result.add(reserva);
		}

		// Cierra el PreparedStatement.
		ps.close();

		// Devuelve el ArrayList de ReservaViewModel.
		return result;
	}

	/**
	 * Metodo para leer una reserva de la base de datos por su ID y modificarla posteriormente.
	 * @param id_Reserva ID de la Reserva para leer
	 * @return Objeto Reserva con los datos de la reserva leída
	 * @throws SQLException Si hay un error de lectura en base de datos
	 */
	public Reserva leerFormulario(int id_Reserva) throws SQLException {
		// Sentencia para seleccionar un registro de la tabla reservas por su ID.
		// Mandar el id y construyo el SQL
		String sql = "SELECT id_Reserva, id_Cliente, id_Moto, fecha_Inicio, fecha_Fin, estado FROM reservas WHERE id_Reserva=?";
		// Prepara la sentencia SQL
		PreparedStatement ps = con.prepareStatement(sql);
		// Mandar el preparedStatement y el id
		ps.setInt(1, id_Reserva); // Estableces el id_Cliente en la consulta SQL

		// Ejecutar la consulta y obtener el resultado.
		ResultSet rs = ps.executeQuery();
		rs.next();

		// Crea un objeto Reserva con los datos obtenidos del resultSet
		Reserva r = new Reserva(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getDate(5),
				rs.getString(6));

		// Cierra el PreparedStatement.
		ps.close();

		// Retorna el objeto Reserva.
		return r;
	}

	/**
	 * Metodo para insertar la modificacion de una reserva en la base de datos.
	 * @param res Objeto Reserva con los datos actualizados
	 * @return true si la actualizacion fue correcta, false si esta falla
	 */
	public boolean actualizarRes(Reserva res) {

		try {
			// Sentencia para actualizar un registro de la tabla reservas por su ID.
			String sql = "UPDATE reservas SET id_Cliente=?, id_Moto=?, fecha_Inicio=?, fecha_Fin=?, estado=? WHERE id_Reserva=?";

			// Prepara la sentencia SQL
			PreparedStatement ps = con.prepareStatement(sql);
			// Establece los parámetros en la sentencia SQL.
			ps.setInt(1, res.getId_Cliente());
			ps.setInt(2, res.getId_Moto());
			ps.setDate(3, res.getFecha_Inicio());
			ps.setDate(4, res.getFecha_Fin());
			ps.setString(5, res.getEstado());
			ps.setInt(6, res.getId_Reserva());

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
	 * Metodo para eliminar una reserva de la base de datos.
	 * @param r Objeto Reserva para eliminar
	 * @throws SQLException Si hay un error de eliminacion en base de datos
	 */
	public void eliminarRese(Reserva r) throws SQLException {
		// Sentencia para eliminar un registro de la tabla reservas por su ID.
		String sql = "DELETE FROM reservas WHERE id_Reserva = ?";

		// Prepara la sentencia SQL
		PreparedStatement ps = con.prepareStatement(sql);
		// Mandar el preparedStatement y el id
		ps.setInt(1, r.getId_Reserva());

		// Ejecuta la sentencia.
		int filas = ps.executeUpdate();

		// Cierra el PreparedStatement.
		ps.close();
	}

}
