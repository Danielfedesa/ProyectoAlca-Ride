package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Login;
import modelo.Motocicleta;

import java.io.IOException;
import java.sql.SQLException;

import DAO.DaoLogin;
import DAO.DaoMotocicleta;

/**
 * Servlet implementation class ServletEliminarMoto
 */
public class ServletEliminarMoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		// Se obtiene el ID de la moto que se va a borrar desde los parámetros de la solicitud
        int idParaBorrar = Integer.parseInt(request.getParameter("id_Moto"));

        // Eliminar usuario de la tabla motocicletas'
        try {
            DaoMotocicleta daoMoto = new DaoMotocicleta(); // Se instancia un objeto DaoLogin para interactuar con la tabla 'login'
            Motocicleta moto = new Motocicleta(); // Se crea un objeto Login para representar al usuario
            moto.setId_Moto(idParaBorrar); // Se establece el ID del usuario a borrar en el objeto Login
            DaoMotocicleta.eliminarMoto(moto); // Se llama al método eliminarLog del objeto DaoLogin para eliminar el usuario de la tabla 'login'
            System.out.println("Motocicleta borrada correctamente de la tabla motocicletas"); // Se imprime un mensaje de éxito en la consola
            
            //Mando mensaje por ventana emergente a JavaScript
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Vehículo eliminado correctamente");
            response.sendRedirect("adminPro.html?mensaje=Vehiculo+eliminado+correctamente");// Se redirige al usuario a la página 'adminPro.html' después de borrar la motocicleta y se muestra mensaje de confirmación
       
        } catch (SQLException e) {
            System.out.println("Error al eliminar el vehiculo de la tabla motocicletas: " + e.getMessage()); // Se imprime un mensaje de error en la consola si ocurre una excepción SQL
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Error al eliminar el vehiculo de la base de datos");
            response.sendRedirect("adminPro.html?mensaje=Error+al+eliminar+el+vehiculo.+Existen+reservas+en+la+base+de+datos.");
        }
	}

}
