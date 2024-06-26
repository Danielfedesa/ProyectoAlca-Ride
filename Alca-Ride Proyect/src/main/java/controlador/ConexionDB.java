package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase con los atributos para la conexion con la base de datos.
 */
public class ConexionDB {

	/*
	 * Para hacer la conexión deberemos importar las librerías externas. Por un lado
	 * botón derecho sobre el proyecto-build path-configure buildpath-proyect
	 * facets-runtimes-seleccionamos el servidor apache Por otro lado botón derecho
	 * sobre el proyecto-buildpath-configure buildpath-libraries-classpath-add
	 * external JARs- seleccionamos el conector de java
	 * mysql-connector-java-8.0.24.jar Finalmente debemos meter el conector
	 * mysql-connector-java-8.0.24.jar en la carpeta del servidor que tenemos
	 * creado, para ello copiamos el archivo y lo metemos en la carpeta del servidor
	 * que en mi caso tenemos creada en C:JAVA en el directorio lib.
	 */

	/*
	 * Para insertar este objeto que hemos creado en la base de datos no podemos
	 * hacerlo directamente con un conexionado desde la clase a la base de datos,
	 * sino que tenemos que pasarla por la clase DAO. El DAO tendrá el método de
	 * insertar, listar, conectar, desconectar, etc. y está directamente relacionado
	 * con todo lo relacionado con la base de datos. Tendremos un objeto genérico
	 * para la conexión, y desde cualquier lugar instanciaremos ese objeto para
	 * conectar, así no tenemos que estar llamando al método conectar y desconectar
	 * constantemente desde cada clase.
	 * 
	 * Para ello creamos un nuevo paquete llamado DAO dentro de java, al mismo nivel
	 * que modelo, vista y controlador. Dentro del DAO hacemos una nueva clase de
	 * nombre DBConection. En esta clase pondremos todos los atributos y variables y
	 * su función será que cuando desde cualquier punto del programa instanciemos el
	 * objeto DBConnection él nos devuelva un objeto conexión a la base de datos a
	 * la ruta que le hemos indicado "jdbc:mysql://localhost:3360/nombreBD;
	 */

	
	// static significa que se extiende fuera del objeto, siendo común para todos
	// los objetos instanciados de ese tipo.
	// Si cambiamos el atributo static al objeto persona 2 se lo cambio también a la persona 5
	// final significa que es una constante y no lo podemos modificar.
	/**
     * URL de conexión a la base de datos.
     */
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/alcaride_bd";
	
	// Creamos el atributo estatico tipo Connection, que es un tipo de conexión que
	// me da el driver sql.
	/**
     * Atributo estatico tipo Connection, que es un tipo de conexion que
     * proporciona el driver SQL.
     */
	public static Connection instance = null;

	// Creamos un método público estático para que desde cualquier punto del
	// programa cuando lo instanciemos nos devuelva
	// la conexión a la ruta.
	 /**
     * Método publico estatico para obtener la conexion a la base de datos.
     * @return Connection objeto de conexion a la base de datos.
     * @throws SQLException si ocurre un error al establecer la conexion.
     */
	public static Connection getConexion() throws SQLException {
		// Debemos meterlo en un if por si llamamos a la clase por segunda vez y ya está
		// conectada.
		if (instance == null) {

			// Queremos que este método se conecte a
			// "jdbc:mysql://localhost:3360/alcaride_bd" y me lo devuelva generando una instancia.
			instance = DriverManager.getConnection(JDBC_URL, "Daniel", "1234");
		}
		return instance;
	}

	/*
	 * Método para cerrar la conexión public static void desconectar() throws
	 * SQLException { if (instance != null && instance.isClosed()) {
	 * instance.close(); instance = null;// Limpiar la instancia para que la próxima
	 * vez se cree una nueva conexión } }
	 */
}
