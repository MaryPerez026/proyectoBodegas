<%@page import="clases.Municipio"%>
<%
    String accion = request.getParameter("accion");
    String id = "Auto Generado"; // Valor por defecto para el ID
    String nombre = ""; // Inicializar nombre
    String cardinalidadGeografica = ""; // Inicializar cardinalidad
    String idDepartamento = ""; // Inicializar id de departamento

    // Si se está modificando un municipio, cargar sus datos
    if (accion != null && accion.equals("Modificar")) {
        id = request.getParameter("id");
        Municipio municipio = new Municipio(id); // Crear objeto municipio con el ID
        nombre = municipio.getNombre(); // Obtener nombre
        cardinalidadGeografica = municipio.getCardinalidadGeografica(); // Obtener cardinalidad geográfica
        idDepartamento = municipio.getIdDepartamento(); // Obtener id de departamento
    }
%>

<h3><%= accion != null ? accion.toUpperCase() : "ACCION DESCONOCIDA" %> MUNICIPIO</h3>
<form name="formulario" method="post" action="principal.jsp?CONTENIDO=municipiosActualizar.jsp">
    <table border="0">
        <tr>
            <th>Id</th>
            <td><%= id %></td> <!-- Mostrar ID -->
        </tr>
        <tr>
            <th>Nombre</th>
            <td><input type="text" name="nombre" value="<%= nombre %>" required></td> <!-- Campo de texto para nombre -->
        </tr>
        <tr>
            <th>Cardinalidad Geográfica</th>
            <td><input type="text" name="cardinalidadGeografica" value="<%= cardinalidadGeografica %>" required></td> <!-- Campo de texto para cardinalidad -->
        </tr>
        <tr>
            <th>Id Departamento</th>
            <td><input type="text" name="idDepartamento" value="<%= idDepartamento %>" required></td> <!-- Campo de texto para id del departamento -->
        </tr>
    </table>
    <input type="hidden" name="id" value="<%= id %>"> <!-- ID oculto para el envío -->
    <input type="submit" name="accion" value="<%= accion != null ? accion : "Agregar" %>"> <!-- Botón de envío -->
    <input type="button" value="Cancelar" onClick="window.history.back()"> <!-- Botón de cancelar -->
</form>
