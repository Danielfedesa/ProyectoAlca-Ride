package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Reserva;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class ServletModificarReserva
 */
public class ServletModificarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * Objeto HttpSession utilizado para gestionar los atributos de la sesión.
     */
	// Inicializo el objeto Httpsession y la variable como atributo del servlet que
	// voy a utilizar.
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletModificarReserva() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Control de sesión para ADMINISTRADOR
		// Obtiene la sesión actual asociada a la solicitud del cliente
		HttpSession sesion = request.getSession();

		// Obtiene los atributos de sesión necesarios para verificar los permisos
		// idSesion es el identificador de la sesión del usuario
		// Si son de administrador le enseño la tabla.
		int idSesion = (Integer) sesion.getAttribute("id");
		boolean isAdmin = (boolean) sesion.getAttribute("admin");

		// Verifica si el usuario ha iniciado sesión y es un administrador
		if (idSesion != 0 && isAdmin == true) {
			// Obtiene el objeto PrintWriter para enviar la respuesta al cliente
			PrintWriter out = response.getWriter();

			/*
			 * Por el doGet leo la información del formulario para posteriormente modificar
			 * y enviar los nuevos datos por el doPost
			 */
			
			// Se obtiene el ID de la reserva que se va a modificar desde los parámetros del formulario
			int id = Integer.parseInt(request.getParameter("id_Reserva"));

			// Crear una instancia de Reserva
			Reserva r = new Reserva();

			// Llamo al método recuperarReserva de la clase Reserva para generar un objeto Dao
			try {
				r.recuperarReserva(id);
				// Llama al método dameJson de la clase Reserva
				out.print(r.dameJson());

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Control de sesión para ADMINISTRADOR
		// Obtiene la sesión actual asociada a la solicitud del cliente
		HttpSession sesion = request.getSession();

		// Obtiene los atributos de sesión necesarios para verificar los permisos
		// idSesion es el identificador de la sesión del usuario
		// Si son de administrador le enseño la tabla.
		int idSesion = (Integer) sesion.getAttribute("id");
		boolean isAdmin = (boolean) sesion.getAttribute("admin");

		// Verifica si el usuario ha iniciado sesión y es un administrador
		if (idSesion != 0 && isAdmin == true) {

	        // Obtiene los parámetros enviados desde el formulario
			int idReserva = Integer.parseInt(request.getParameter("id_Reserva"));
			int idCliente = Integer.parseInt(request.getParameter("id_Cliente"));
			int idMoto = Integer.parseInt(request.getParameter("id_Moto"));
			String fechaRecogidaString = request.getParameter("fecha_Inicio");
			String fechaDevoString = request.getParameter("fecha_Fin");
			String estado = request.getParameter("estado");

			// Parseo de las fechas
	        // Define el formato de las fechas recibidas
			SimpleDateFormat formatoFecha = new SimpleDateFormat("MMM dd, yyyy");
			java.util.Date fechaRecogidaUtil = null;
			java.util.Date fechaDevoUtil = null;
			try {
	            // Intenta parsear las fechas recibidas del formulario
				fechaRecogidaUtil = formatoFecha.parse(fechaRecogidaString);
				fechaDevoUtil = formatoFecha.parse(fechaDevoString);
			} catch (ParseException e) {
				e.printStackTrace();
				// Manejo de errores en el parseo de fechas
				response.sendRedirect("error.html");
				return;
			}

			// Convertir a java.sql.Date
	        // Convierte las fechas de java.util.Date a java.sql.Date
			java.sql.Date fechaRecogidaSql = new java.sql.Date(fechaRecogidaUtil.getTime());
			java.sql.Date fechaDevoSql = new java.sql.Date(fechaDevoUtil.getTime());

	        // Crea una instancia de la clase Reserva con los datos obtenidos
			Reserva res = new Reserva(idReserva, idCliente, idMoto, fechaRecogidaSql, fechaDevoSql, estado);

			try {
	            // Llama al método actualizarReserva() para actualizar los datos de la reserva en la base de datos
				boolean respuesta = res.actualizarReserva();

				// Mando mensaje por ventana emergente a JavaScript y redirijo a ademinRes.html
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Reserva modificada correctamente");
				response.sendRedirect("adminRes.html?mensaje=Reserva+modificada+correctamente");
																								
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
