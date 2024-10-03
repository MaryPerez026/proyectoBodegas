<%@page import="java.util.List"%>
<%@page import="clases.Categoria"%>
<%
    String lista = ""; 
    List<Categoria> datos = Categoria.getListaEnObjetos(null, null); 

 
    for (int i = 0; i < datos.size(); i++) {
        Categoria categoria = datos.get(i);
        lista += "<tr>";
        lista += "<td align='right'>" + categoria.getId() + "</td>"; 
        lista += "<td>" + categoria.getNombre() + "</td>"; 
        lista += "<td>" + categoria.getReferenciaEnObjeto()+ "</td>"; 
        lista += "<td>" + categoria.getDescripcion() + "</td>"; 
        lista += "<td>";
        lista += "<a href='principal.jsp?CONTENIDO=categoriasFormulario.jsp&accion=Modificar&id=" + categoria.getId() + "' title='Modificar'><img src='iconos/editar.png'/></a>"; 
        lista+="<img src='iconos/borrar.png'  title='Eliminar' onClick='eliminar("+ categoria.getId()+")'> ";
        lista+="</td>";
        lista += "</tr>";
    }
%>

<h3>LISTA DE CATEGORIAS</h3>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Nombre</th>
        <th>Referencia</th>
        <th>Descripcion</th>
        <th><a href="principal.jsp?CONTENIDO=categoriasFormulario.jsp&accion=Adicionar" title="Adicionar">
                    <img src="iconos/agregar.png"></a></th>
    </tr>
    <%= lista %> 
</table>

<script type="text/javascript">
    function eliminar(id) {
        respuesta = confirm("Realmente desea eliminar el registro de categoria?");
        if (respuesta) {
            document.location = "principal.jsp?CONTENIDO=categoriasActualizar.jsp&accion=Eliminar&id="+id; 
        }
    }
</script>

