package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Login;
import modelo.Usuario;

import java.io.IOException;
import java.sql.SQLException;

import DAO.DaoLogin;
import DAO.DaoUsuario;

/**
 * Servlet implementation class ServletEliminarUsuario
 */
public class ServletEliminarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Inicializo el objeto Httpsession y la variable como atributo del servlet que
	// voy a utilizar.
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEliminarUsuario() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

			// Se obtiene el ID del usuario que se va a borrar desde los parámetros de la solicitud
			int idParaBorrar = Integer.parseInt(request.getParameter("id_Usuario"));
			
			
			/* 
			* Tenemos que borrar el usuario de las dos tablas.
			* En primer lugar lo haremos de la tabla login
			* Posteriormente de la tabla usuarios
			* De esta forma el usuario será borrado por completo del sistema
			*/
			
			// Eliminar usuario de la tabla login
			try {
				// Crear una instancia de Login y establecer el ID para borrar
				Login login = new Login();
				login.setId_Usuario(idParaBorrar);

				// Llamar al método eliminarLogin() del objeto Login
				login.eliminarLogin();

			// Manejar la respuesta para el cliente
			// Si todo va bien confirma la eliminación y redirije a adminUsu.html
			} catch (SQLException e) {
				System.out.println("Error al eliminar el usuario de la tabla login: " + e.getMessage());																											
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Error al eliminar el login de la base de datos");
				// Si algo falla manda mensaje de error y redirije a adminUsu.html
				response.sendRedirect("adminUsu.html?mensaje=Error+al+eliminar+el+login+de+la+base+de+datos.");
			}

			// Eliminar usuario de la tabla usuarios
			try {
				// Crear una instancia de Usuario y establecer el ID para borrar
				Usuario usuario = new Usuario();
				usuario.setId_Usuario(idParaBorrar);

				// Llamar al método eliminarUsuario() del objeto Usuario
				usuario.eliminarUsuario();

				// Manejar la respuesta para el cliente
				// Si todo va bien confirma la eliminación y redirije a adminUsu.html
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Usuario eliminado correctamente");
				response.sendRedirect("adminUsu.html?mensaje=Usuario+eliminado+correctamente");																				

			} catch (SQLException e) {
				System.out.println("Error al eliminar el usuario de la tabla usuarios: " + e.getMessage());																							
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Error al eliminar el usuario de la base de datos");
				// Si algo falla manda mensaje de error y redirije a adminUsu.html
				response.sendRedirect("adminUsu.html?mensaje=Error+al+eliminar+el+usuario+de+la+base+de+datos.");
			}
		}
	}
}
