package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Reserva;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletEliminarReserva
 */
public class ServletEliminarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Inicializo el objeto Httpsession y la variable como atributo del servlet que
	// voy a utilizar.
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEliminarReserva() {
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

			// Se obtiene el ID de la reserva que se va a borrar desde los parámetros de la solicitud
			int idParaBorrar = Integer.parseInt(request.getParameter("id_Reserva"));

			// Eliminar reserva de la tabla reservas
			try {
				// Crear una instancia de Reserva y establecer el ID para borrar
				Reserva reserva = new Reserva();
				reserva.setId_Reserva(idParaBorrar);

				// Llamar al método eliminarReserva() del objeto Reserva
				reserva.eliminarReserva();

				// Manejar la respuesta para el cliente
				// Si todo va bien confirma la eliminación y redirije a adminRes.html
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Reserva eliminada correctamente");
				response.sendRedirect("adminRes.html?mensaje=Reserva+eliminada+correctamente");
			} catch (SQLException e) {
				// Si algo falla manda mensaje de error y redirije a adminRes.html
				System.out.println("Error al eliminar la reserva de la base de datos: " + e.getMessage());
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Error al eliminar la reserva de la base de datos");
				response.sendRedirect("adminRes.html?mensaje=Error+al+eliminar+la+reserva.");
			}
		}
	}
}