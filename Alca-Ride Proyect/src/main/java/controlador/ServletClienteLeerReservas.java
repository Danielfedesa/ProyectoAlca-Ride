package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Reserva;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletClienteLeerReservas
 */
public class ServletClienteLeerReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Inicializo el objeto Httpsession y la variable como atributo del servlet que voy a utilizar.
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClienteLeerReservas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Control de sesión para CLIENTE
		HttpSession sesion = request.getSession();
		
					
		//Si tengo los datos que necesito, compruebo si son de administrador o de cliente.
		//Si son de cliente le enseño la tabla.
		int idSesion = (Integer) sesion.getAttribute("id");
		boolean isAdmin = (boolean) sesion.getAttribute("admin");

		if (idSesion != 0 && isAdmin == false) {

		//AQUI VA EL RESTO DE CODIGO QUE PERTENECE AL PERMISO DE CLIENTE
			try {
				
				PrintWriter out = response.getWriter();
				Reserva res = new Reserva();
				
				//Le paso como argumento el idSesion para recuperar unicamente las reservas del idSesion iniciada
				String resultado = res.listarReservasCliente(idSesion);
					
					out.print(resultado);
					System.out.println(resultado);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect("login2.html");
				}	
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
