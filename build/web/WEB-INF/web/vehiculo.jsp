<%-- 
    Document   : vehiculos
    Created on : 23/09/2024, 08:46:31 PM
    Author     : MARY SALAZAR
--%>

<%@page import="clases.Vehiculo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String lista = "";
    List<Vehiculo> datos = Vehiculo.getListaEnObjetos("", null);
    for (int i = 0; i < datos.size(); i++) {
        Vehiculo vehiculo = datos.get(i);
        
        lista += "<tr>";  // Inicia la fila
        lista += "<td>" + vehiculo.getPlacaVehiculo() + "</td>";
        lista += "<td>" + vehiculo.getModelo() + "</td>";
        lista += "<td>" + vehiculo.getMarca() + "</td>";
        lista += "<td>" + vehiculo.getCapacidadDeCarga() + "</td>";
        lista += "<td>" + vehiculo.getDescripcion() + "</td>";
        lista += "<td>";
        lista += "<a href='principal.jsp?CONTENIDO=vehiculosFormulario.jsp&accion=Modificar&placaVehiculo=" + vehiculo.getPlacaVehiculo() + "' title='Modificar'><img src='presentacion/imagenes/modificar.png' title='Modificar'></a>";
        lista += "<a href='#' title='Eliminar' onClick='confirmarEliminacion(\"" + vehiculo.getPlacaVehiculo() + "\");'><img src='presentacion/imagenes/eliminar.png'></a>";
        lista += "</td>";
        lista += "</tr>";  // Cierra la fila
    }
%>

<h3>LISTA DE VEHICULOS</h3>
<table border="1">
    <tr>
        <th>Placa del Vehiculo</th>
        <th>Modelo</th>
        <th>Marca</th>
        <th>Capacidad de carga</th>
        <th>Descripcion</th>
        <th><a href="principal.jsp?CONTENIDO=vehiculosFormulario.jsp&accion=Adicionar" title="Adicionar">
                <img src="presentacion/imagenes/adicionar.png">
            </a>
        </th>
    </tr>
    <%= lista %>
</table>

<script type="text/javascript">
    function confirmarEliminacion(placaVehiculo) {
         resultado = confirm("¿Realmente desea eliminar el vehículo con la placa " + placaVehiculo + "?");
        if (resultado) {
            document.location = "principal.jsp?CONTENIDO=vehiculosActualizar.jsp&accion=Eliminar&placaVehiculo=" + placaVehiculo;
        }
    }
</script>
