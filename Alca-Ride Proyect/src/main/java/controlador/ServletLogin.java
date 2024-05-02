package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Login;
import modelo.LoginCliente;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			respuesta = milogin.iniciarSesion();
			//Comprobamos si el objeto está vacío por no haberse encontrado en la base de datos por el Dao.
			if(respuesta.getNombre_Usuario()=="") {
				response.sendRedirect("errorRegistro.html");
			}
			//Si el objeto trae parámetros es porque los ha encontrado correctamente en la BDD.
			else
			{
			
				//Comprobamos el is_Admin para
				if(respuesta.isIs_Admin()==true)
				{
					response.sendRedirect("adminArea.html");
				}
				else
				{
					//le decimos que el login no es correcto
					response.sendRedirect("clientArea.html");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
