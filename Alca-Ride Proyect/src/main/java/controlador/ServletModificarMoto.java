package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Motocicleta;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletModificarMoto
 */
public class ServletModificarMoto extends HttpServlet {
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
	public ServletModificarMoto() {
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

			// Se obtiene el ID de la moto que se va a modificar desde los parámetros del formulario
			int id = Integer.parseInt(request.getParameter("id_Moto"));

			// Crear una instancia de Motocicleta
			Motocicleta m = new Motocicleta();

			// Llama al método actualizarMoto de la clase Motocicleta para generar un objeto Dao
			try {
				m.recuperarMoto(id);
				// Llama al método dameJson de la clase Motocicleta
				out.print(m.dameJson());

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
			int id = Integer.parseInt(request.getParameter("id_Moto"));
			String tipo = request.getParameter("tipo");
			String matricula = request.getParameter("matricula");
			int cilindrada = Integer.parseInt(request.getParameter("cilindrada"));
			String tipo_Carnet = request.getParameter("tipo_Carnet");
			String marca = request.getParameter("marca");
			String modelo = request.getParameter("modelo");
			int anio = Integer.parseInt(request.getParameter("anio"));
			double precio_Dia = Double.parseDouble(request.getParameter("precio_Dia"));

	        // Crea una instancia de la clase Motocicleta con los parámetros obtenidos
			Motocicleta moto = new Motocicleta(id, tipo, matricula, cilindrada, tipo_Carnet, marca, modelo, anio, precio_Dia);

			try {
	            // Llama al método actualizarMoto() para actualizar los datos de la motocicleta
				boolean respuesta = moto.actualizarMoto();

				// Mando mensaje por ventana emergente a JavaScript
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Motocileta modificada correctamente");
	            // Redirige al cliente a la página adminPro.html + mensaje
				response.sendRedirect("adminPro.html?mensaje=Motocicleta+modificada+correctamente");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
