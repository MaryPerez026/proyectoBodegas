<%@page import="java.util.List"%>
<%@page import="clases.Municipio"%>
<%
    // Obtener la lista de municipios
    List<Municipio> datos = Municipio.getListaEnObjetos(null, null);
    String lista = ""; // String para construir la tabla

    // Construir la tabla con los municipios
    for (Municipio municipio : datos) {
        lista += "<tr>";
        lista += "<td>" + municipio.getId() + "</td>";
        lista += "<td>" + municipio.getNombre() + "</td>";
        lista += "<td>" + municipio.getIdDepartamento() + "</td>";
        lista += "<td>" + municipio.getCardinalidadGeografica() + "</td>";
        lista += "<td>";
        lista += "<a href='principal.jsp?CONTENIDO=municipiosFormulario.jsp&accion=Modificar&id=" + municipio.getId() + "' title='Modificar'>Modificar</a> ";
        lista += "<img src='imagenes/eliminar.png' title='Eliminar' onClick='eliminar(" + municipio.getId() + ")'> "; // Cambiar la imagen
        lista += "</td>";
        lista += "</tr>";
    }
%>

<h3>LISTA DE MUNICIPIOS</h3>
<table border="1">
    <tr>
        <th>Id</th><th>Nombre</th><th>Id Departamento</th><th>Cardinalidad Geográfica</th><th>Acciones</th>
    </tr>
    <%= lista %>
</table>

<script type="text/javascript">
    function eliminar(id) {
        let respuesta = confirm("¿Realmente desea eliminar el registro?");
        if (respuesta) {
            document.location = "principal.jsp?CONTENIDO=municipiosActualizar.jsp&accion=Eliminar&id=" + id;
        }
    }
</script>

