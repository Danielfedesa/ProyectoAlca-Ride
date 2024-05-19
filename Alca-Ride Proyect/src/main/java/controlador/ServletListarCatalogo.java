package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Motocicleta;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletListarCatalogo
 */
public class ServletListarCatalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListarCatalogo() {
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

		// Este servlet muestra los datos de las motocicletas del catálogo en la vista pública
		try {
	        // Obtiene el objeto PrintWriter para enviar la respuesta al cliente
			PrintWriter out = response.getWriter();

	        // Crea una instancia de la clase Motocicleta
			Motocicleta moto = new Motocicleta();

	        // Llama al método listarMotos() para obtener una lista de todas las motocicletas en formato String
			String resultado = moto.listarMotos();

	        // Imprime el resultado en la respuesta HTTP para que el cliente lo reciba
			out.print(resultado);
			System.out.println(resultado);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
