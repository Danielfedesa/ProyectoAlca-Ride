package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.LoginCliente;
import modelo.Usuario;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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

	// Código para cifrar password
	/**
	 * Metodo para cifrar una contrasenia utilizando el algoritmo MD5.
	 *
	 * @param input La cadena de texto a cifrar.
	 * @return La cadena cifrada en formato hexadecimal.
	 * @throws RuntimeException si ocurre un error al obtener la instancia del algoritmo MD5.
	 */
	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
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
		String nombre = request.getParameter("nombre");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		String email = request.getParameter("email");
		String dni = request.getParameter("dni");
		String carnet = request.getParameter("carnet");
		String direccion = request.getParameter("direccion");

	    // Obtiene los parámetros para el login del usuario
		// getMD5 para cifrar pass
		String nombre_Usuario = request.getParameter("nombre_Usuario");
		String pass = getMD5(request.getParameter("pass"));

	    // Crea un objeto Usuario utilizando el constructor sin id, ya que generará uno nuevo autoincremental
		Usuario u1 = new Usuario(nombre, apellido1, apellido2, telefono, email, dni, carnet, direccion);
																											
		try {
	        // Llama al método crearUsuario() que inserta el usuario en la base de datos y devuelve el id del nuevo usuario
			int idUsuarioCreado = u1.crearUsuario();
													
	        // Crea un objeto LoginCliente utilizando el nombre de usuario, contraseña y el id del usuario recién creado
			LoginCliente l1 = new LoginCliente(nombre_Usuario, pass, idUsuarioCreado);
																																									
	        // Llama al método crearLogin() para insertar los datos del login en la base de datos
			l1.crearLogin();

	        // Redirige al usuario a la página de login
			response.sendRedirect("login3.html");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        // Redirige al usuario a la página de error
			response.sendRedirect("errorRegistro.html");

		}
	}

}
