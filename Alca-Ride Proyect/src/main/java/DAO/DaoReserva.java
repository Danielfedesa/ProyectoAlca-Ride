package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.ConexionDB;
import modelo.Motocicleta;
import modelo.Reserva;

public class DaoReserva {


	//Genero un atributo de tipo Connection llamado "con" y la instancio en null:
	public static Connection con = null; //Importo Connection.
	
	
	//Nada más se crea el objeto DaoMotocicleta le decimos al atributo con que conecte con DBConnection.getConexion() para que
	//se inicie la conexión nada más se instancie el objeto.
	public DaoReserva() throws SQLException {
		this.con = ConexionDB.getConexion(); //Importo y añado excepción.
	}
	
	// Método para insertar reserva
		public boolean insertarReserva(Reserva reserva) throws SQLException {
		    Connection con = ConexionDB.getConexion();

		    boolean comprobar = false;
		    
		    String sqlFechas = "SELECT COUNT(*) AS count FROM alcaride_bd.reservas \n"
		                + "WHERE \n"
		                + "    id_moto = ? \n"
		                + "    AND \n"
		                + "    ( \n"
		                + "        (? >= fecha_Inicio AND ? <= fecha_Fin) \n"
		                + "        OR \n"
		                + "        (? >= fecha_Inicio AND ? <= fecha_Fin) \n"
		                + "    ) \n"
		                + "    OR \n"
		                + "    (fecha_Inicio <= ? AND fecha_Fin >= ?);";
		    
		    PreparedStatement psFechas = con.prepareStatement(sqlFechas);
		    psFechas.setInt(1, reserva.getId_Moto());
		    psFechas.setDate(2, reserva.getFecha_Inicio());
		    psFechas.setDate(3, reserva.getFecha_Inicio());
		    psFechas.setDate(4, reserva.getFecha_Fin());
		    psFechas.setDate(5, reserva.getFecha_Fin());
		    psFechas.setDate(6, reserva.getFecha_Inicio());
		    psFechas.setDate(7, reserva.getFecha_Fin());
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
		        String sql = "INSERT INTO reservas (id_Moto, id_Cliente, id_Admin, fecha_Realiza, fecha_Inicio, fecha_Fin, estado) VALUES (?, ?, 1, ?, ?, ?, 'Pendiente')";
		        PreparedStatement ps = con.prepareStatement(sql);

		        ps.setInt(1, reserva.getId_Moto());
		        ps.setInt(2, reserva.getId_Cliente());
		        ps.setDate(3, reserva.getFecha_Realiza());
		        ps.setDate(4, reserva.getFecha_Inicio());
		        ps.setDate(5, reserva.getFecha_Fin());
		        ps.executeUpdate();

		        ps.close();
		        return true; // Insertada correctamente
		    } else {
		        System.out.println("La reserva coincide con otras existentes.");
		        return false; // No se pudo insertar la reserva
		    }
		}
	
	//Método para listar reservas ADMINISTRADOR.
	public ArrayList<Reserva> listar() throws SQLException {
	    String sql = "SELECT id_Reserva, id_Moto, id_Cliente, id_Admin, fecha_Realiza, fecha_Inicio, fecha_Fin, estado FROM reservas";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();

	    ArrayList<Reserva> result = new ArrayList<>();
	    while (rs.next()) {
	        int id_Reserva = rs.getInt("id_Reserva");
	        int id_Moto = rs.getInt("id_Moto");
	        int id_Cliente = rs.getInt("id_Cliente");
	        int id_Admin = rs.getInt("id_Admin");
	        Date fecha_Realiza = rs.getDate("fecha_Realiza");
	        Date fecha_Inicio = rs.getDate("fecha_Inicio");
	        Date fecha_Fin = rs.getDate("fecha_Fin");
	        String estado = rs.getString("estado");

	        Reserva reserva = new Reserva(id_Reserva, id_Moto, id_Cliente, id_Admin, fecha_Realiza, fecha_Inicio, fecha_Fin, estado);
	        result.add(reserva);
	    }

	    ps.close();
	    return result;
	}
	
	//Método para listar reservas CLIENTE.
		public ArrayList<Reserva> listarResCliente(String idLogin) throws SQLException {
		    String sql = "SELECT id_Reserva, id_Moto, id_Cliente, id_Admin, fecha_Realiza, fecha_Inicio, fecha_Fin, estado FROM reservas WHERE id_Cliente = ?";
		    PreparedStatement ps = con.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();

		    ArrayList<Reserva> result = new ArrayList<>();
		    while (rs.next()) {
		        int id_Reserva = rs.getInt("id_Reserva");
		        int id_Moto = rs.getInt("id_Moto");
		        int id_Cliente = rs.getInt("id_Cliente");
		        int id_Admin = rs.getInt("id_Admin");
		        Date fecha_Realiza = rs.getDate("fecha_Realiza");
		        Date fecha_Inicio = rs.getDate("fecha_Inicio");
		        Date fecha_Fin = rs.getDate("fecha_Fin");
		        String estado = rs.getString("estado");

		        Reserva reserva = new Reserva(id_Reserva, id_Moto, id_Cliente, id_Admin, fecha_Realiza, fecha_Inicio, fecha_Fin, estado);
		        result.add(reserva);
		    }

		    ps.close();
		    return result;
		}
		
	
	//Metodo obtener registro por el id PARA LUEGO PODIFICAR LA RESERVA
			public Reserva leerFormulario(int id_Reserva) throws SQLException {
				System.out.println("Llego al metodo obtenerRegistro del DAO");

				//Le mando el id y construyo el SQL
				String sql = "SELECT id_Reserva, id_Cliente, id_Moto, fecha_Inicio, fecha_Fin, estado FROM reservas WHERE id_Reserva=?";
				//Contstruyo el preparedstatement
				PreparedStatement ps = con.prepareStatement(sql);
				//Mando el preparedStatement y el id
				ps.setInt(1, id_Reserva);

				//Ejecuto el prepared
				ResultSet rs = ps.executeQuery();
				
				rs.next();
				
				//Le digo que nos genere un objeto Reserva con los datos que está recibiendo el resultset
				Reserva r = new Reserva(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getDate(5), rs.getString(6));
				ps.close();
				System.out.println("He obtenido el registro");
				System.out.println(r);

				
				//Retornamos el objeto r
				return r;
			}

			public void eliminarRese(Reserva r) throws SQLException {
			    String sql = "DELETE FROM reservas WHERE id_Reserva = ?";
			    
			    PreparedStatement ps = con.prepareStatement(sql);
			    ps.setInt(1, r.getId_Reserva());
			    
			    int filas = ps.executeUpdate();
			    
			    ps.close();
			}
	
}
