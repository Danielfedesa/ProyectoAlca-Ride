package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Login;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Inicializo el objeto Httpsession y la variable como atributo del servlet que voy a utilizar.
		HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Le pasamos los parámetros por el formulario
		String nombre_Usuario = request.getParameter("nombre_Usuario");
		String pass = request.getParameter("pass");
		
		//Creamos objeto miLogin con los parámetros del formulario
		Login milogin= new Login(nombre_Usuario, pass);
		
		//Variable respuesta que contiene el resultado devuelto de la clase Login
		Login respuesta;
		
		//Comprobamos los datos introducidos por respuesta a través de Login para autenticar al usuario
		try {
	        // Llama al método iniciarSesion para autenticar al usuario
			respuesta = milogin.iniciarSesion();

			// Comprobamos si el objeto está vacío por no haberse encontrado en la base de datos por el Dao.
			// Si está vacío redirijo a error
			if(respuesta.getNombre_Usuario()=="") {
				response.sendRedirect("errorRegistro.html");
			}
			//Si el objeto trae parámetros es porque los ha encontrado correctamente en la BDD.
			else {
				
				//AQUÍ INICIAMOS EL MÉTODO PARA GUARDAR LOS DATOS EN LA SESION
				// Obtiene la sesión actual o crea una nueva si no existe
				sesion = request.getSession();
				
				//Meto los datos que necesito dentro de la sesion, en este caso el id_Login y el is_Admin
				sesion.setAttribute("id", respuesta.getId_Login());
				sesion.setAttribute("admin", respuesta.isIs_Admin());
				//FINALIZO EL METODO PARA GUARDAR LA SESION
				
				//Comprobamos el is_Admin para redirigir a un sitio u otro y pasarle el id_Login
				if(respuesta.isIs_Admin()==true) {
	                // Si es administrador redirige a la página del área de administrador con el ID de inicio de sesión
					response.sendRedirect("adminArea.html?idLogin=" + respuesta.getId_Login());
				} else {
	                // Si no es administrador redirige a la página del área de cliente con el ID de inicio de sesión
					response.sendRedirect("clientArea.html?idLogin=" + respuesta.getId_Login());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}