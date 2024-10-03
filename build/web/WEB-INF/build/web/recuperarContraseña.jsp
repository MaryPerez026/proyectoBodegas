<%-- 
    Document   : recuperarContraseña
    Created on : 11 sep. 2024, 11:29:49
    Author     : sena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mensaje=" ";
    if(request.getParameter("error") !=null) {
        switch(request.getParameter("error")) {
            case "1": mensaje="Usuario o contraseña no valida"; break;
            case "2": mensaje="Acceso denegado"; break;
            default: mensaje= "Error desconocido"; break;
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Software de ventas</title>
        <%--<link rel="stylesheet" href="presentacion/estiloIndex2.css"> --%>
    </head>
    <body>
        <h2 id="titulo">DaiWebLive</h2>
        <div class="formulariorecuperacion">
            <h3>Recuperar Contraseña</h3>
            <form method="post" action="recuperar.jsp">
                <div class="correo">
                    <br><label>ingrese su correo electronico</label>
                    <input type="email" name="email" required>
                </div>
                <div class="codigoR">
                    <input type="text" name="codigoR" required>
                    <label>ingrese el codigo de recuperacion enviado al correo</label>
                </div>
            </form>
        </div>
        <div class="Formulario">
            <h3>Inicio de sesion</h3>
            <form method="post" action="validar.jsp">
                <div class="usuario">
                    <input type="text" name="identificacion" required>
                    <label>Usuario</label>
                </div>
                <div class="usuario">
                    <input type="password" name="clave" required>
                    <label>Contraseña</label>
                </div>
                <div class="recordar"><a href="recuperarContraseña.jsp">¿Olvido su Contraseña?</a></div>
                <p>
                <p id="Error"><%=mensaje%></p>
                <input type="submit" name="Iniciar" value="Iniciar">
                <div class="registro">
                    Quiero hacer el <a href="#">registro</a>
                </div>
            </form>
        </div>
       
    </body>
</html>