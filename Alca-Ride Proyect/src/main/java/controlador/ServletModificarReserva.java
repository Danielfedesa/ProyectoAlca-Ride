package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Reserva;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class ServletModificarReserva
 */
public class ServletModificarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Inicializo el objeto Httpsession y la variable como atributo del servlet que voy a utilizar.
	HttpSession sesion;
       
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
		
		//Control de sesión para ADMINISTRADOR
		HttpSession sesion = request.getSession();
													
		//Si tengo los datos que necesito, compruebo si son de administrador o de cliente.
		//Si son de administrador le enseño la tabla.
		int idSesion = (Integer) sesion.getAttribute("id");
		boolean isAdmin = (boolean) sesion.getAttribute("admin");

		if (idSesion != 0 && isAdmin == true) {		
		
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
					
					int idReserva = Integer.parseInt(request.getParameter("id_Reserva"));
					int idCliente = Integer.parseInt(request.getParameter("id_Cliente"));
					int idMoto = Integer.parseInt(request.getParameter("id_Moto"));
					String fechaRecogidaString = request.getParameter("fecha_Inicio");
					String fechaDevoString = request.getParameter("fecha_Fin");
					String estado = request.getParameter("estado");
			
			        // Parseo de las fechas
			        SimpleDateFormat formatoFecha = new SimpleDateFormat("MMM dd, yyyy");
			        java.util.Date fechaRecogidaUtil = null;
			        java.util.Date fechaDevoUtil = null;
			        try {
			            fechaRecogidaUtil = formatoFecha.parse(fechaRecogidaString);
			            fechaDevoUtil = formatoFecha.parse(fechaDevoString);
			        } catch (ParseException e) {
			            e.printStackTrace();
			            // Manejo de errores en el parseo de fechas
			            response.sendRedirect("error.html");
			            return;
			        }
			
			        // Convertir a java.sql.Date
			        java.sql.Date fechaRecogidaSql = new java.sql.Date(fechaRecogidaUtil.getTime());
			        java.sql.Date fechaDevoSql = new java.sql.Date(fechaDevoUtil.getTime());

						
						
						Reserva res = new Reserva(idReserva, idCliente, idMoto, fechaRecogidaSql, fechaDevoSql, estado);
				
						try {
							boolean respuesta = res.actualizarReserva();
							
							//Mando mensaje por ventana emergente a JavaScript
				            response.setContentType("text/plain");
				            response.setCharacterEncoding("UTF-8");
				            response.getWriter().write("Reserva modificada correctamente");
				            response.sendRedirect("adminRes.html?mensaje=Reserva+modificada+correctamente");// Se redirige al usuario a la página 'adminRes.html' después de modificar la reserva y se muestra mensaje de confirmación
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			}
		}
