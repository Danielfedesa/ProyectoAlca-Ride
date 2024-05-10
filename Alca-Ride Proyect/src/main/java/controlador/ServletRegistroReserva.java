package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Reserva;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class ServletRegistroReserva
 */
public class ServletRegistroReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Inicializo el objeto Httpsession y la variable como atributo del servlet que voy a utilizar.
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistroReserva() {
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
    	
    
    	// Obtengo la sesión actual. Si no existe, no se crea una nueva (por eso el false).
    			sesion = request.getSession(false);
    			
    			// Compruebo si la sesión es null o si no contiene un atributo "id".
    			if (sesion == null || sesion.getAttribute("id") == null) {
    				// Si alguna de estas condiciones es verdadera, redirijo al usuario a la página de login.
    				response.sendRedirect("login2.html");
    			} else {
    				// Si ambas condiciones son falsas, puedo acceder de manera segura al valor del atributo "id".
    				//Le paso el parámetro que necesite para la verificación de los que tengo almacenados en la sesion
    		    	//En este caso el id
    				int idSesion = (int) sesion.getAttribute("id");
    				
 
    	//Condiciono la página a que el id sea diferente a 0, si es así continúa y si no le saco al login.
    	if (idSesion != 0) {
    			
		    	// Recibo los parámetros del formulario
		        int id_Moto = Integer.parseInt(request.getParameter("id_Moto"));
		        String fechaRecogidaString = request.getParameter("fecha_Inicio");
		        String fechaEntregaString = request.getParameter("fecha_Fin");
		
		        // Parseo de las fechas
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        java.util.Date fechaRecogidaUtil = null;
		        java.util.Date fechaEntregaUtil = null;
		        try {
		            fechaRecogidaUtil = dateFormat.parse(fechaRecogidaString);
		            fechaEntregaUtil = dateFormat.parse(fechaEntregaString);
		        } catch (ParseException e) {
		            e.printStackTrace();
		            // Manejo de errores en el parseo de fechas
		            response.sendRedirect("error.html");
		            return;
		        }
		
		        // Convertir a java.sql.Date
		        java.sql.Date fechaRecogidaSql = new java.sql.Date(fechaRecogidaUtil.getTime());
		        java.sql.Date fechaEntregaSql = new java.sql.Date(fechaEntregaUtil.getTime());
		
		        // Fecha actual
		        java.sql.Date fechaRealiza = new java.sql.Date(System.currentTimeMillis());
		
		     // Creación de la reserva con la fecha actual
		        Reserva res = new Reserva(id_Moto, idSesion, fechaRealiza, fechaRecogidaSql, fechaEntregaSql);
		
		        try {
		            if (res.crearReserva()) { // Utiliza la misma instancia de Reserva creada anteriormente
		                // Reserva insertada correctamente
		                response.sendRedirect("confReserva.html");
		            } else {
		                // No se pudo insertar la reserva debido a conflictos de fechas
		            	 System.out.println("Conflicto de vehiculo y fechas");
		                 response.setContentType("text/plain");
		                 response.setCharacterEncoding("UTF-8");
		                 response.getWriter().write("El vehículo no está disponible en las fechas seleccionadas");
		                 response.sendRedirect("formInsertarReserva.html?mensaje=Fechas+no+disponibles+para+el+vehiculo+seleccionado.+ Por+favor,+seleccione+otra+fecha.");
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		            // Problemas en la base de datos
		            response.sendRedirect("error.html");
		        }
		    	
		    } else {
		    	// Redirijo al login
		        response.sendRedirect("login2.html");
		    }
    }
   }
}