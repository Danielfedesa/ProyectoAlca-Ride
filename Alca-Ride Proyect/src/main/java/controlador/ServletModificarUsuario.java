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
	//Inicializo el objeto Httpsession y la variable como atributo del servlet que voy a utilizar.
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Control de sesión para ADMINISTRADOR
		HttpSession sesion = request.getSession();
											
		//Si tengo los datos que necesito, compruebo si son de administrador o de cliente.
		//Si son de administrador le enseño la tabla.
		int idSesion = (Integer) sesion.getAttribute("id");
		boolean isAdmin = (boolean) sesion.getAttribute("admin");

		if (idSesion != 0 && isAdmin == true) {
				PrintWriter out = response.getWriter();
				
				//Variable para recibir el id_Usuario que me manda el formulario por JavaScript
				int id = Integer.parseInt(request.getParameter("id_Usuario"));
				
				//Creo el objeto Usuario
				Usuario u = new Usuario();
				
			
					//Llamo al método actualizarUsuario de la clase Usuario para generar un objeto Dao
					try {
						u.recuperarUsuario(id);
						//Verifico por consola
						System.out.println(u.toString());
						//Llamamos al método dameJson de la clase Usuario
						out.print(u.dameJson());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		}
	
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Control de sesión para ADMINISTRADOR
		HttpSession sesion = request.getSession();
											
		//Si tengo los datos que necesito, compruebo si son de administrador o de cliente.
		//Si son de administrador le enseño la tabla.
		int idSesion = (Integer) sesion.getAttribute("id");
		boolean isAdmin = (boolean) sesion.getAttribute("admin");

		if (idSesion != 0 && isAdmin == true) {

				int id = Integer.parseInt(request.getParameter("id_Usuario"));
				String nombre = request.getParameter("nombre");
				String apellido1 = request.getParameter("apellido1");
				String apellido2 = request.getParameter("apellido2");
				int telefono = Integer.parseInt(request.getParameter("telefono"));
				String email = request.getParameter("email");
				String dni = request.getParameter("dni");
				String carnet = request.getParameter("carnet");
				String direccion = request.getParameter("direccion");
				
				Usuario user = new Usuario(id, nombre, apellido1, apellido2, telefono, email, dni, carnet, direccion);
		
				try {
					boolean respuesta = user.actualizarUsuario();
					
					//Mando mensaje por ventana emergente a JavaScript
		            response.setContentType("text/plain");
		            response.setCharacterEncoding("UTF-8");
		            response.getWriter().write("Usuario modificado correctamente");
		            response.sendRedirect("adminUsu.html?mensaje=Usuario+modificado+correctamente");// Se redirige al usuario a la página 'adminUsu.html' después de modificar el usuario y se muestra mensaje de confirmación
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
}
