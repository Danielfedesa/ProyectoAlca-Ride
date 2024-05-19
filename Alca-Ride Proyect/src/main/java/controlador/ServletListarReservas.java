package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Reserva;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletListarReservas
 */
public class ServletListarReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Inicializo el objeto Httpsession y la variable como atributo del servlet que
	// voy a utilizar.
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListarReservas() {
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

			// AQUI VA EL RESTO DE CODIGO QUE PERTENECE AL PERMISO DE ADMINISTRADOR
			try {
		        // Obtiene el objeto PrintWriter para enviar la respuesta al cliente
				PrintWriter out = response.getWriter();
				
		        // Crea una instancia de la clase Reserva
				Reserva res = new Reserva();
				
		        // Llama al método listarReservas() para obtener una lista de todas las reservas en formato String
				String resultado = res.listarReservas();
				
		        // Imprime el resultado en la respuesta HTTP para que el cliente lo reciba
				out.print(resultado);
				System.out.println(resultado);

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

	}

}
