package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.Motocicleta;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletMotocicleta
 */
@MultipartConfig //Añado la anotación @MultipartConfig para poder insertar fotografias. Importo
public class ServletMotocicletaCOPIAFOTOS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Atributo para insertar las fotografias (le decimos la ruta del fichero):
	private String pathFiles = "C:\\Users\\Daniel\\ECLIPSEWORKSPACE\\Alca-Ride Proyect\\src\\main\\webapp\\images";
	//Objeto para que java entienda que le damos una ruta para trabajar con archivos:
	private File uploads = new File(pathFiles);//Importo
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMotocicletaCOPIAFOTOS() {
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
		//En este caso almacenamos los datos del formulario compuesto por varios campos
		//asignando una variable a cada uno y dando el parametro del nombre del formulario individual.
		String tipo = request.getParameter("tipo");
		String matricula = request.getParameter("matricula");
		String cilindrada = request.getParameter("cilindrada");
		String tipo_Carnet = request.getParameter("tipo_Carnet");
		String marca = request.getParameter("marca");
		String modelo = request.getParameter("modelo");
		String anio = request.getParameter("anio");
		double precio_Dia = Integer.parseInt(request.getParameter("precio_Dia"));
		String url_Foto = request.getParameter("url_Foto");
		
		/*Para añadir la foto:
		 * Primero añadimos un atributo a la clase ServletMotocicleta 
		 * Recibimos los datos:
		 * Decidir donde se guardan los archivos en destino.
		 */
		
		//1. Lectura de datos desde el cliente:
		// Generamos un objeto Part y recogemos los datos binarios de la foto.
		Part part = request.getPart("url_Foto");//Importo
		//Conocemos el nombre que tiene el archivo en su origen:
		Path path = Paths.get(part.getSubmittedFileName());//Importo
		//Una vez que tenemos los datos del contenido y del continente
		//convertimos los mismos en String para guardar en modo texto el nombre del archivo
		String fileName = path.getFileName().toString();
		
		//2. Preparamos el camino (buffer) por el que haremos la transmisión de los datos al destino.
		InputStream input = part.getInputStream();//Importo
		
		//3. Donde metemos el copiado de los datos.
		//Creamos un objeto para guardarlo:
		File file = new File(uploads, fileName);
		//Metemos la lectura de los datos en el contenedor file que hemos generado.
		//Lo meto en un try catch para gestionar posibles errores.
		try {
		Files.copy(input, file.toPath());//Importo Files
		}catch (Exception e) {
			System.out.println("Error en la copia del archivo");
			PrintWriter error = response.getWriter();
					error.print("<h3> Se ha producido un error en la carga del archivo. Intentelo de nuevo o contacte con el administrador.</h3>");
		}
		
		//Creamos un objeto con el constructor que no tiene id.
		Motocicleta m1 = new Motocicleta(tipo, matricula, cilindrada, tipo_Carnet, marca, modelo, anio, precio_Dia, fileName); //Importo Modelo.Motocicleta

		//imprimimos el objeto por pantalla.
		System.out.println(m1.toString());
		
		//Método crearMotocicleta de la clase Motocicleta.
		try {
			m1.crearMotocicleta(); //Lo meto en un try catch.
			
			//redireccion si registro corecto//QUITAR
			//vamos a la pagina inforamtiva de registro ok//QUITAR
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al insertar en la base de datos.");
		}
	}

}
