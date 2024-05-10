package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.LoginCliente;
import modelo.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DaoUsuario;

/**
 * Servlet implementation class ServletRegistroUsuario
 */
public class ServletRegistroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistroUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}
		
		/*
		//Enlazo con el método listar del DaoUsuario.
		//Hago que el DaoUsuario me devuelkva el arraylist que está generando
		// Crear una instancia del DaoUsuario
	    DaoUsuario daoUsuario;
	    try {
	        daoUsuario = new DaoUsuario();
	        
	        // Obtener la lista de usuarios
	        ArrayList<Usuario> listaUsuarios = daoUsuario.listar();
	        
	        // Aquí puedes hacer lo que necesites con la lista de usuarios
	        // Por ejemplo, agregarla al request para pasarla a una vista JSP
	        for(Usuario a : listaUsuarios) {
	        	System.out.println(a.toString());
	        }
	        //request.setAttribute("usuarios", listaUsuarios);
	        
	        // Redirigir a una vista JSP donde puedas mostrar la lista de usuarios
	        //request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
	        
	    } catch (SQLException e) {
	        // Manejo de excepciones
	        e.printStackTrace();
	        response.sendRedirect("error.html");
	    }
	
	*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("nombre");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		String email = request.getParameter("email");
		String dni = request.getParameter("dni");
		String carnet = request.getParameter("carnet");
		String direccion = request.getParameter("direccion");
		
		String nombre_Usuario = request.getParameter("nombre_Usuario");
		String pass = request.getParameter("pass");
	
		//Creamos un objeto con el constructor que no tiene id.
		Usuario u1 = new Usuario(nombre, apellido1, apellido2, telefono, email, dni, carnet, direccion); //Importo Modelo.Usuario
		try {
			int idUsuarioCreado = u1.crearUsuario();//Creamos el objeto usuario y nos devuelve el id_Usuario recien creado
			
			LoginCliente l1 = new LoginCliente(nombre_Usuario, pass, idUsuarioCreado); //Creamos el objeto LoginCliente con el id_Usuario recien creado + los parametros
			
			l1.crearLogin();
			
			//redirigimos al menú login.
			response.sendRedirect("login3.html");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error de inserción en la base de datos.");
            response.sendRedirect("errorRegistro.html");

		}		
	}
	
	

}
