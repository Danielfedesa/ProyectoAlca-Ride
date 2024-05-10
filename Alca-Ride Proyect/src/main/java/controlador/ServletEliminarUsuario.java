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
	//Inicializo el objeto Httpsession y la variable como atributo del servlet que voy a utilizar.
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        
		//Control de sesión para ADMINISTRADOR
		HttpSession sesion = request.getSession();
									
		//Si tengo los datos que necesito, compruebo si son de administrador o de cliente.
		//Si son de administrador le enseño la tabla.
		int idSesion = (Integer) sesion.getAttribute("id");
		boolean isAdmin = (boolean) sesion.getAttribute("admin");

		if (idSesion != 0 && isAdmin == true) {
		
				// Se obtiene el ID del usuario que se va a borrar desde los parámetros de la solicitud
		        int idParaBorrar = Integer.parseInt(request.getParameter("id_Usuario"));
		
		        // Eliminar usuario de la tabla 'login'
		        try {
		            Login login = new Login(); // Se crea un objeto Login para representar al usuario
		            login.setId_Usuario(idParaBorrar); // Se establece el ID del usuario a borrar en el objeto Login
		            
		            login.eliminarLogin();
		            System.out.println("Usuario borrado correctamente de la tabla login"); // Se imprime un mensaje de éxito en la consola
		            
		            } catch (SQLException e) {
		            System.out.println("Error al eliminar el usuario de la tabla login: " + e.getMessage()); // Se imprime un mensaje de error en la consola si ocurre una excepción SQL
		            response.setContentType("text/plain");
		            response.setCharacterEncoding("UTF-8");
		            response.getWriter().write("Error al eliminar el login de la base de datos");
		            response.sendRedirect("adminUsu.html?mensaje=Error+al+eliminar+el+login+de+la+base+de+datos.");
		        }
		
		        // Eliminar usuario de la tabla 'usuarios'
		        try {
		            Usuario usuario = new Usuario(); // Se crea un objeto Usuario para representar al usuario
		            usuario.setId_Usuario(idParaBorrar); // Se establece el ID del usuario a borrar en el objeto Usuario
		            
		            usuario.eliminarUsuario();
		            System.out.println("Usuario borrado correctamente de la tabla usuarios"); // Se imprime un mensaje de éxito en la consola
		            
		            //Mando mensaje por ventana emergente a JavaScript
		            response.setContentType("text/plain");
		            response.setCharacterEncoding("UTF-8");
		            response.getWriter().write("Usuario eliminado correctamente");
		            response.sendRedirect("adminUsu.html?mensaje=Usuario+eliminado+correctamente");// Se redirige al usuario a la página 'adminUsu.html' después de borrar el usuario y se muestra mensaje de confirmación
		            
		        } catch (SQLException e) {
		            System.out.println("Error al eliminar el usuario de la tabla usuarios: " + e.getMessage()); // Se imprime un mensaje de error en la consola si ocurre una excepción SQL
		            response.setContentType("text/plain");
		            response.setCharacterEncoding("UTF-8");
		            response.getWriter().write("Error al eliminar el usuario de la base de datos");
		            response.sendRedirect("adminUsu.html?mensaje=Error+al+eliminar+el+usuario+de+la+base+de+datos.");
		        }
		    }
	}
}





