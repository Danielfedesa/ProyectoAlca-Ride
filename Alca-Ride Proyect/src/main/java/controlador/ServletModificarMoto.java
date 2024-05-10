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

/**
 * Servlet implementation class ServletModificarMoto
 */
public class ServletModificarMoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Inicializo el objeto Httpsession y la variable como atributo del servlet que voy a utilizar.
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificarMoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Control de sesión para ADMINISTRADOR
		HttpSession sesion = request.getSession();
											
		//Si tengo los datos que necesito, compruebo si son de administrador o de cliente.
		//Si son de administrador le enseño la tabla.
		int idSesion = (Integer) sesion.getAttribute("id");
		boolean isAdmin = (boolean) sesion.getAttribute("admin");

		if (idSesion != 0 && isAdmin == true) {
				PrintWriter out = response.getWriter();
				
				//Variable para recibir el id_Moto que me manda el formulario por JavaScript
				int id = Integer.parseInt(request.getParameter("id_Moto"));
				
				//Creo el objeto Moto
				Motocicleta m = new Motocicleta();
				
			
					//Llamo al método actualizarMoto de la clase Motocicleta para generar un objeto Dao
					try {
						m.recuperarMoto(id);
						//Verifico por consola
						System.out.println(m.toString());
						//Llamamos al método dameJson de la clase Usuario
						out.print(m.dameJson());
						System.out.println("Datos recuperados correctamente");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}	
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
			
		
				int id = Integer.parseInt(request.getParameter("id_Moto"));
				String tipo = request.getParameter("tipo");
				String matricula = request.getParameter("matricula");
				int cilindrada = Integer.parseInt(request.getParameter("cilindrada"));
				String tipo_Carnet = request.getParameter("tipo_Carnet");
				String marca = request.getParameter("marca");
				String modelo = request.getParameter("modelo");
				int anio = Integer.parseInt(request.getParameter("anio"));
				double precio_Dia = Double.parseDouble(request.getParameter("precio_Dia"));
				
				Motocicleta moto = new Motocicleta(id, tipo, matricula, cilindrada, tipo_Carnet, marca, modelo, anio, precio_Dia);
		
				try {
					boolean respuesta = moto.actualizarMoto();
					
					//Mando mensaje por ventana emergente a JavaScript
		            response.setContentType("text/plain");
		            response.setCharacterEncoding("UTF-8");
		            response.getWriter().write("Motocileta modificada correctamente");
		            response.sendRedirect("adminPro.html?mensaje=Motocicleta+modificada+correctamente");// Se redirige al usuario a la página 'adminPro.html' después de modificar la moto y se muestra mensaje de confirmación
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
}
