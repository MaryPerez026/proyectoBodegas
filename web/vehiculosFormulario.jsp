
<html>
<head>
    <meta charset="UTF-8">
    <title>Vehículos</title>
    <link rel="stylesheet" type="text/css" href="presentacion/EstiloInicio.css">
</head>
</html>

<%@page import="clases.Vehiculo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String accion = request.getParameter("accion");
    String placaVehiculo = request.getParameter("placaVehiculo");
    Vehiculo vehiculo = new Vehiculo();
    if ("Modificar".equals(accion)) {
        vehiculo = new Vehiculo(placaVehiculo);
    }
%>

<h3>VEHÍCULO</h3>
<form name="formulario" method="post" action="principal.jsp?CONTENIDO=vehiculosActualizar.jsp">
    <table border="0">
        <tr>
            <th>Placa del Vehículo</th>
            <td><input type="text" name="placaVehiculo" maxlength="10" value="<%= vehiculo.getPlacaVehiculo() %>" required></td>
        </tr>
        <tr>
            <th>Modelo</th>
            <td><input type="text" name="modelo" value="<%= vehiculo.getModelo() %>" size="50" maxlength="50" required></td>
        </tr>
        <tr>
            <th>Marca</th>
            <td><input type="text" name="marca" value="<%= vehiculo.getMarca() %>" size="50" maxlength="50" required></td>
        </tr>
        <tr>
            <th>Capacidad de Carga</th>
            <td><input type="text" name="capacidadDeCarga" value="<%= vehiculo.getCapacidadDeCarga() %>" required></td>
        </tr>
        <tr>
            <th>Descripción</th>
            <td><textarea name="descripcion" required><%= vehiculo.getDescripcion() %></textarea></td>
        </tr>
    </table>
    <input type="hidden" name="placaAnterior" value="<%= placaVehiculo %>">
    <p><input type="submit" name="accion" value="<%= accion %>"></p>
</form>
