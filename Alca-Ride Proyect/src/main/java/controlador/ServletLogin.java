package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Login;
import modelo.LoginCliente;

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
		//Comprobamos los datos introducidos por respuesta a través de Login
		try {
			System.out.println("Antes de iniciar sesión: " + milogin);
			respuesta = milogin.iniciarSesion();
			System.out.println("Después de iniciar sesión: " + respuesta);
			//Comprobamos si el objeto está vacío por no haberse encontrado en la base de datos por el Dao.
			if(respuesta.getNombre_Usuario()=="") {
				response.sendRedirect("errorRegistro.html");
			}
			//Si el objeto trae parámetros es porque los ha encontrado correctamente en la BDD.
			else {
				
				//AQUÍ INICIAMOS EL MÉTODO PARA GUARDAR LOS DATOS EN LA SESION
				sesion = request.getSession();
				
				//Meto los datos que necesito dentro de la sesion, en este caso el id_Login y el is_Admin
				sesion.setAttribute("id", respuesta.getId_Login());
				sesion.setAttribute("admin", respuesta.isIs_Admin());
				//FINALIZO EL METODO PARA GUARDAR LA SESION
				System.out.println(respuesta.getId_Login());
				//Comprobamos el is_Admin para redirigir a un sitio u otro y pasarle el id_Login
				if(respuesta.isIs_Admin()==true)
				{
					response.sendRedirect("adminArea.html?idLogin=" + respuesta.getId_Login());
				}
				else
				{
					//Si no es admin le mando a la página de cliente
					response.sendRedirect("clientArea.html?idLogin=" + respuesta.getId_Login());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

