package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Motocicleta;
import modelo.Reserva;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Servlet implementation class ServletEliminarMoto
 */
public class ServletEliminarMoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Inicializo el objeto Httpsession y la variable como atributo del servlet que voy a utilizar.
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminarMoto() {
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
		
		//Control de sesión para ADMINISTRADOR
		HttpSession sesion = request.getSession();
							
		//Si tengo los datos que necesito, compruebo si son de administrador o de cliente.
		//Si son de administrador le enseño la tabla.
		int idSesion = (Integer) sesion.getAttribute("id");
		boolean isAdmin = (boolean) sesion.getAttribute("admin");

		if (idSesion != 0 && isAdmin == true) {
		
				// Se obtiene el ID de la moto que se va a borrar desde los parámetros de la solicitud
		        int idParaBorrar = Integer.parseInt(request.getParameter("id_Moto"));
		        
		        // Eliminar moto de la tabla motocicletas'
		        try {
		            // Crear una instancia de Motocicleta y establecer el ID para borrar
		            Motocicleta moto = new Motocicleta();
		            moto.setId_Moto(idParaBorrar);
		
		            // Llamar al método eliminarReserva() del objeto Reserva
		            moto.eliminarMotocicleta();
		
		            // Manejar la respuesta al cliente
		            response.setContentType("text/plain");
		            response.setCharacterEncoding("UTF-8");
		            response.getWriter().write("Motocicleta eliminada correctamente");
		            response.sendRedirect("adminPro.html?mensaje=Motocicleta+eliminada+correctamente");
		        } catch (SQLException e) {
		            System.out.println("Error al eliminar la motocicleta de la base de datos: " + e.getMessage());
		            response.setContentType("text/plain");
		            response.setCharacterEncoding("UTF-8");
		            response.getWriter().write("Error al eliminar la motocicleta de la base de datos");
		            response.sendRedirect("adminPro.html?mensaje=Error+al+eliminar+el+vehiculo+al+existir+reservas+en+la+base+de+datos.");
		        }
		    }
	}

}