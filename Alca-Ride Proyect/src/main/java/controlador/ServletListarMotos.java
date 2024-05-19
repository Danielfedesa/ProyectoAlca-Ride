package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Motocicleta;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import DAO.DaoMotocicleta;

/**
 * Servlet implementation class ServletListarMotos
 */
@SuppressWarnings("unused")
public class ServletListarMotos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Inicializo el objeto Httpsession y la variable como atributo del servlet que
	// voy a utilizar.
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListarMotos() {
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

		// Control de sesión para ADMINISTRADOR
		// Este listado es el de la zona privada de admin (no el catalogo público)
		HttpSession sesion = request.getSession();

		// Obtiene los atributos de sesión necesarios para verificar los permisos
	    // idSesion es el identificador de la sesión del usuario
		// Si son de administrador le enseño la tabla.
		int idSesion = (Integer) sesion.getAttribute("id");
		boolean isAdmin = (boolean) sesion.getAttribute("admin");

		// Verifica si el usuario ha iniciado sesión y es un administrador
		if (idSesion != 0 && isAdmin == true) {

			// AQUI VA EL RESTO DE CODIGO QUE PERTENECE AL PERMISO DE ADMINISTRADOR
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
