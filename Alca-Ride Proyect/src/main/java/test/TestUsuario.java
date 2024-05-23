package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

import modelo.Usuario;

class TestUsuario {

	/**
	 * Test de Junit para probar el método crearUsuario de la clase Usuario
	 * y que este inserta correctamente en la base de datos.
	 */
    @Test
    void testCrearUsuario() {
        boolean resultado;

        // Crear un nuevo usuario con los datos de prueba
        Usuario u = new Usuario("ntest", "a1test", "a2test", 123456789, "test@email.com", "98765432A", "A2", "testDireccion");

        boolean valorEsperado = true;

        try {
            // Intenta crear el usuario en la base de datos y obtener el ID del usuario recién creado
            int idCreado = u.crearUsuario();
            // Verifica si el ID del usuario creado es mayor a 0. Si es mayor es que lo ha insertado
            resultado = idCreado > 0;
        } catch (SQLException e) {
            // En caso de excepción, establece el resultado como falso
            resultado = false;
            e.printStackTrace();
        }
        // Verificar si el resultado es el valor esperado
        assertEquals(valorEsperado, resultado);
    }
    
    /**
	 * Test de Junit para probar el método eliminarUsuario de la clase Usuario
	 * y que este elimina correctamente en la base de datos.
	 * En primer lugar realiza una inserción para eliminarla posteriormente.
	 * Si esta operación es correcta sale por "fail" y genera un error
	 * al no recuperar el usuario insertado puesto que ha sido eliminado.
	 */
    @Test
    void testEliminarUsuario() {
        // Crear un nuevo usuario con los datos de prueba
        Usuario u = new Usuario("nelimiar", "a1elimiar", "a2eliminar", 123456789, "test@email.com", "98765432A", "A2", "testDireccion");

        try {
            // Crear el usuario en la base de datos y obtener el ID del usuario creado
            int idCreado = u.crearUsuario();
            // Establecer el ID del usuario creado en el objeto Usuario
            u.setId_Usuario(idCreado);

            // Verificar que el usuario fue creado correctamente
            assertEquals(true, idCreado > 0);

            // Intentar eliminar el usuario
            u.eliminarUsuario();

            // Crear un objeto Usuario para intentar recuperar el usuario eliminado
            Usuario usuarioRecuperado = new Usuario();
            try {
                // Intentar recuperar el usuario usando el ID
                usuarioRecuperado.recuperarUsuario(idCreado);
                // Si el usuario se recupera, significa que no fue eliminado correctamente
                fail("El usuario debería haber sido eliminado");
            } catch (SQLException e) {
                // Se espera una excepción si el usuario fue eliminado correctamente
                assertEquals("No se encontró el usuario", e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}