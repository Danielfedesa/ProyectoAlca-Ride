package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ServletCerrarSesion
 */
public class ServletCerrarSesion extends HttpServlet {
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
	public ServletCerrarSesion() {
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

		HttpSession sesion = request.getSession();

		sesion.invalidate();
		response.sendRedirect("sesionCerrada.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
