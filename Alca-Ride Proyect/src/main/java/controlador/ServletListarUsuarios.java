package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletListarUsuarios
 */
public class ServletListarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Inicializo el objeto Httpsession y la variable como atributo del servlet que voy a utilizar.
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarUsuarios() {
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

			//AQUI VA EL RESTO DE CODIGO QUE PERTENECE AL PERMISO DE ADMINISTRADOR
				try {
					//Me devuelve el PrintWriter del response que me sirve para enviar cosas
					PrintWriter out = response.getWriter();
					
					
					//Genero un objeto DaoUsuario llamado usuario
					Usuario usuario = new Usuario();
					
					//Hago un string del resultado
					String resultado = usuario.listarUsuarios();
					
					//Imprimimos el resultado
					out.print(resultado);
					System.out.println(resultado);
								
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

			//Redirijo a página de inicio
				response.sendRedirect("index2.html");
			}
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
	}

}
