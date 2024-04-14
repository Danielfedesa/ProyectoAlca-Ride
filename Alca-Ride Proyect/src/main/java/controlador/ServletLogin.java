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
		String nombre_Usuario = request.getParameter("nombre_Usuario");
		String pass = request.getParameter("pass");
		
		Login milogin= new Login(nombre_Usuario, pass);
		
		Login respuesta;
		try {
			respuesta = milogin.leerLogin();
			if(respuesta.isIs_Admin())
			{
				//es adminitrador? 
				
				//es cliente?
				
				//comprobamos si es Cliente o Admin
				response.sendRedirect("adminArea.html");
			}
			else
			{
				//le decimos que el login no es correcto
				response.sendRedirect("clientArea.html");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
