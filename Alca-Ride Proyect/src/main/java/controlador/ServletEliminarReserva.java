package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Reserva;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Servlet implementation class ServletEliminarReserva
 */
public class ServletEliminarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminarReserva() {
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
		// Se obtiene el ID de la moto que se va a borrar desde los parámetros de la solicitud
        int idParaBorrar = Integer.parseInt(request.getParameter("id_Reserva"));

        // Eliminar reserva de la tabla reservas'
        try {
            // Crear una instancia de Reserva y establecer el ID para borrar
            Reserva reserva = new Reserva();
            reserva.setId_Reserva(idParaBorrar);

            // Llamar al método eliminarReserva() del objeto Reserva
            reserva.eliminarReserva();

            // Manejar la respuesta al cliente
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Reserva eliminada correctamente");
            response.sendRedirect("adminRes.html?mensaje=Reserva+eliminada+correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar la reserva de la base de datos: " + e.getMessage());
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Error al eliminar la reserva de la base de datos");
            response.sendRedirect("adminRes.html?mensaje=Error+al+eliminar+la+reserva.");
        }
    }

}