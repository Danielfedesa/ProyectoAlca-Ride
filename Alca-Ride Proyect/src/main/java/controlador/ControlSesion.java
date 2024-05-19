package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ControlSesion
 */
public class ControlSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Inicializo el objeto Httpsession y la variable como atributo del servlet que
	// voy a utilizar.
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlSesion() {
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

		// Instanciar un objeto session
		HttpSession sesion;

		// Inicializar el objeto con el request.getSession para abrir una "puerta" llamada sesion
		sesion = request.getSession();

		// Variable para almacenar el dato que queremos guardar en la sesion, debería
		// entrar de un request.getParameter
		String nombreUsuario = "Daniel";

		// Le decimos a sesion con el método setAttribute que nos GUARDE con la clave
		// que queramos el valor que le doy, en este caso el id.
		// Necesitaría traer los datos con algún método del Dao
		// sesion.setAttribute("id_Login", DaoLogin.dameid);
		sesion.setAttribute("nombreUsuario", nombreUsuario);
		sesion.setAttribute("id_Login", 1);
		sesion.setAttribute("is_Admin", 1);

		// Ahora leemos los datos que hemos traido y lo parseamos a String, ya que es texto plano.
		String respuesta1 = (String) sesion.getAttribute("nombreUsuario");
		int respuesta2 = (int) sesion.getAttribute("id_Login");
		int respuesta3 = (int) sesion.getAttribute("is_Admin");

		System.out.println(respuesta1);
		System.out.println(respuesta2);
		System.out.println(respuesta3);

	}

	/*
	 * Esta parte sería la que metería en el resto de servlets que necesiten el dato
	 * de sesion (en el doGet) La sesión caduca, hay que mirar la duración en la
	 * configuración de apache pero suelen ser 30 minutos.
	 * 
	 * HttpSession = request.getSession(); String respuesta1 = (String)
	 * sesion.getAttribute("nombreUsuario"); System.out.println(respuesta1);
	 */

	/*
	 * Para cerrar la sesión meteremos el siguiente código. Este código elimina
	 * cualquier dato que esté en la sesión.
	 * 
	 * HttpSession sesion = request.getSession();
	 * 
	 * sesion.invalidate();
	 * 
	 */

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
