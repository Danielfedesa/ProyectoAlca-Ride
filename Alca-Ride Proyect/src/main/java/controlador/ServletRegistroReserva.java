package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        // Recibo los parámetros del formulario
        int id_Cliente = Integer.parseInt(request.getParameter("id_Cliente"));
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
        Reserva res = new Reserva(id_Moto, id_Cliente, fechaRealiza, fechaRecogidaSql, fechaEntregaSql);

        try {
            if (res.crearReserva()) { // Utiliza la misma instancia de Reserva creada anteriormente
                // Reserva insertada correctamente
                response.sendRedirect("confReserva.html");
            } else {
                // No se pudo insertar la reserva debido a conflictos de fechas
                response.sendRedirect("errorFechas.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Problemas en la base de datos
            response.sendRedirect("error.html");
        }
    }
}