<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alca-Ride | Formulario de registro</title>
    <link rel="stylesheet" href="styles/formularios.css">
</head>

  <!-- Creo una clase para el body, para la cual aplicaré 
    en su página de estilos un grid y le daré forma responsive.
    Divido el body en 4 apartados principales:
        header
        navbar
        contenido
        footer
    -->
<body class="gridContainer">

    <header class="header">
        <!--Pongo este div para que el enlace se ajuste exactamente al logo-->
        <div>
        <a href="index.html"><img src="images/logo1.jpg" alt="Logo Alca-Ride"></a>
        </div>
    </header>

    <nav class="navbar">
        <a href="index.html">Inicio</a>
        <a href="catalogo.html">Catálogo</a>
        <a href="condic.html">Condiciones</a>
        <a href="contacto.html">Contacto</a>
        <a href="login.html">Iniciar Sesión</a>
    </nav>
    
    <article class="contenido">
        <div class="formRegistro">			
            <h1>Iniciar sesión</h1>  

            <form action="ServletLogin" method="post">

            

            <p>
                <label for="nombre_Usuario" class="colocar_nombre_Usuario">Nombre de usuario
                <span class="obligatorio">*</span>
                </label>
                <input type="nombre_Usuario" name="nombre_Usuario" id="nombre_Usuario" required="obligatorio" placeholder="Escribe tu nombre de usuario">
            </p>

            <p>
                <label for="nombre_pass" class="colocar_pass">Contraseña
                <span class="obligatorio">*</span>
                </label>
                <input type="password" name="pass" id="pass" required="obligatorio" placeholder="Escribe tu contraseña">
            </p>

            

            <button type="submit" name="enviar_formulario" id="enviar"><p>Enviar</p></button>
				    
            <p style="font-size: 18px;">
                ¿Aún no estás registrado?
            </p>

        </form>

        <div class="registrateAqui">
            <a href="formRegistro.html">Regístrate aquí</a>
        </div>

        </div>
    </article>

    <footer class="footer">
        <a href="privacidad.html" style="color: white;">Política de privacidad</a>
        <a href="legal.html" style="color: white;">Aviso legal</a>
        <p style="color: white;">Copyright Alca-Ride 2024 - Developed by Daniel Fernández</p>
    </footer>

</body>

</html>