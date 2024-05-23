package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletModificarUsuario
 */
public class ServletModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Inicializo el objeto Httpsession y la variable como atributo del servlet que
	// voy a utilizar.
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletModificarUsuario() {
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
			
			// Se obtiene el ID del usuario que se va a modificar desde los parámetros del formulario
			int id = Integer.parseInt(request.getParameter("id_Usuario"));

			// Crear una instancia de Usuario
			Usuario u = new Usuario();

			// Llama al método recuperarUsuario de la clase Usuario para generar un objeto Dao
			try {
				u.recuperarUsuario(id);
				// Llama al método dameJson de la clase Usuario
				out.print(u.dameJson());
				
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
			int id = Integer.parseInt(request.getParameter("id_Usuario"));
			String nombre = request.getParameter("nombre");
			String apellido1 = request.getParameter("apellido1");
			String apellido2 = request.getParameter("apellido2");
			int telefono = Integer.parseInt(request.getParameter("telefono"));
			String email = request.getParameter("email");
			String dni = request.getParameter("dni");
			String carnet = request.getParameter("carnet");
			String direccion = request.getParameter("direccion");

	        // Crea una instancia de la clase Motocicleta con los parámetros obtenidos
			Usuario user = new Usuario(id, nombre, apellido1, apellido2, telefono, email, dni, carnet, direccion);

			try {
	            // Llama al método actualizarUsuario() para actualizar los datos del usuario
				boolean respuesta = user.actualizarUsuario();

				// Mando mensaje por ventana emergente a JavaScript
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Usuario modificado correctamente");
	            // Redirige al cliente a la página admiUsu.html + mensaje
				response.sendRedirect("adminUsu.html?mensaje=Usuario+modificado+correctamente");
																							
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
