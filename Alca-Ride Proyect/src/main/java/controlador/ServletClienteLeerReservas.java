package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Reserva;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletClienteLeerReservas
 */
public class ServletClienteLeerReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		 // Obtener el id_Login de la URL
	    String idLogin = request.getParameter("id_Login");
	    System.out.println(idLogin);

	    // Verificar si id_Login es válido
	    if (idLogin != null && !idLogin.isEmpty()) {
	        // Llamar al método listarReservasCliente con el id_Login y escribir el resultado en la respuesta
	        try {
				response.getWriter().print(new Reserva().listarReservasCliente(idLogin));
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    } else {
	        // Si no se proporcionó el id_Login en la URL, enviar un mensaje de error
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se proporcionó el id_Login en la URL");
	    }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
