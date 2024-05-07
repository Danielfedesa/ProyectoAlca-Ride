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
 * Servlet implementation class ServletModificarReserva
 */
public class ServletModificarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificarReserva() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Llego al servlet");
		PrintWriter out = response.getWriter();
		
		//Variable para recibir el id_Reserva que me manda el formulario por JavaScript
		int id = Integer.parseInt(request.getParameter("id_Reserva"));
		
		//Creo el objeto Reserva
		Reserva r = new Reserva();
		
	
			//Llamo al método recuperarReserva de la clase Reserva para generar un objeto Dao
			try {
				r.recuperarReserva(id);
				//Verifico por consola
				System.out.println(r.toString());
				//Llamamos al método dameJson de la clase Usuario
				out.print(r.dameJson());
				System.out.println("Datos recuperados correctamente");
				System.out.println(r);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

}
