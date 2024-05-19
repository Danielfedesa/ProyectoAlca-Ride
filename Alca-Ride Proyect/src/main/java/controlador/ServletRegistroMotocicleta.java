package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Motocicleta;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletMotocicleta
 */
@MultipartConfig // Añado la anotación @MultipartConfig para poder insertar fotografias. Importo
public class ServletRegistroMotocicleta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletRegistroMotocicleta() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	    // Obtiene los parámetros del formulario enviados en la solicitud
		String tipo = request.getParameter("tipo");
		String matricula = request.getParameter("matricula");
		int cilindrada = Integer.parseInt(request.getParameter("cilindrada"));
		String tipo_Carnet = request.getParameter("tipo_Carnet");
		String marca = request.getParameter("marca");
		String modelo = request.getParameter("modelo");
		int anio = Integer.parseInt(request.getParameter("anio"));
		double precio_Dia = Integer.parseInt(request.getParameter("precio_Dia"));

		// Crea un objeto Motocicleta utilizando el constructor sin id
		// ya que generará uno nuevo autoincremental al insertarse en bse de datos
		Motocicleta m1 = new Motocicleta(tipo, matricula, cilindrada, tipo_Carnet, marca, modelo, anio, precio_Dia);
																														
		
		try {
	        // Llama al método crearMotocicleta() para insertar los datos de la moto en la base de datos
			m1.crearMotocicleta();
			// Redirige a adminPro.html
			response.sendRedirect("adminPro.html");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Si falla la inserción, redirige a errorRegistro.html
			response.sendRedirect("errorRegistro.html");
		}
	}

}
