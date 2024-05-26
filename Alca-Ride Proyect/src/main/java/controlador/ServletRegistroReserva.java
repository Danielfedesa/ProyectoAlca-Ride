package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Reserva;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class ServletRegistroReserva
 */
public class ServletRegistroReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Inicializo el objeto Httpsession y la variable como atributo del servlet que
	// voy a utilizar.
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletRegistroReserva() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Control de sesión para CLIENTE
		// Obtiene la sesión actual asociada a la solicitud del cliente
		sesion = request.getSession(false);

		// Comprueba si la sesión es null o si no contiene un atributo ID
		if (sesion == null || sesion.getAttribute("id") == null) {
			// Si alguna de estas condiciones es verdadera, redirijo al usuario a la página
			// de login2 para que inicie la sesión
			response.sendRedirect("login2.html");
		} else {
			// Si ambas condiciones son falsas, puedo acceder de manera segura al valor del
			// atributo ID.
			// Le paso el parámetro que necesite para la verificación de los que tengo
			// almacenados en la sesion
			// En este caso el id
			int idSesion = (int) sesion.getAttribute("id");

			// Condiciona la página a que el ID sea diferente a 0, si es así continúa y si
			// no le saco al login.
			if (idSesion != 0) {

			    // Obtiene los parámetros del formulario enviados en la solicitud
				int id_Moto = Integer.parseInt(request.getParameter("id_Moto"));
				String fechaRecogidaString = request.getParameter("fecha_Inicio");
				String fechaDevoString = request.getParameter("fecha_Fin");

				// Parseo de las fechas
				// Define el formato de las fechas que estamos recibiendo del formulario
				SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
				// Convierte la cadena de texto en un objeto Date utilizando el método parse
				// de la clase SimpleDateFormat.
				java.util.Date fechaRecogidaUtil = null;
				java.util.Date fechaDevoUtil = null;
				// Maneja las posibles excelciones del parseo
				try {
					fechaRecogidaUtil = formatoFecha.parse(fechaRecogidaString);
					fechaDevoUtil = formatoFecha.parse(fechaDevoString);
				} catch (ParseException e) {
					e.printStackTrace();
					// Manejo de errores en el parseo de fechas
					response.sendRedirect("error.html");
					return;
				}

				// Convierte a java.sql.Date ya que es el tipo que necesitamos para trabajar con bases de datos en JDBC.
				java.sql.Date fechaRecogidaSql = new java.sql.Date(fechaRecogidaUtil.getTime());
				java.sql.Date fechaDevoSql = new java.sql.Date(fechaDevoUtil.getTime());

				// Fecha actual
				java.sql.Date fechaRealiza = new java.sql.Date(System.currentTimeMillis());

				// Creación del objeto Reserva con la fecha actual
				Reserva res = new Reserva(id_Moto, idSesion, fechaRealiza, fechaRecogidaSql, fechaDevoSql);

				try {
					// Utiliza la misma instancia de Reserva creada anteriormente
					if (res.crearReserva()) { 
						// Reserva insertada correctamente
						response.sendRedirect("confReserva.html");
					} else {
						// No se pudo insertar la reserva debido a conflictos de fechas
						System.out.println("Conflicto de vehiculo y fechas");
						response.setContentType("text/plain");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write("El vehículo no está disponible en las fechas seleccionadas");
						response.sendRedirect(
								"formInsertarReserva.html?mensaje=Fechas+no+disponibles+para+el+vehiculo+seleccionado.+ Por+favor,+seleccione+otra+fecha.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
					// Problemas en la base de datos
					response.sendRedirect("error.html");
				}

			} else {
				// Redirijo al login
				response.sendRedirect("login2.html");
			}
		}
	}
}