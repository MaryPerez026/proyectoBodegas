<%@page import="java.util.List"%>
<%@page import="clases.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String lista="";
List<Persona> datos=Persona.getListaEnObjetos("Rol<>'E' && Rol<>'I'", null);
for (int i = 0; i < datos.size(); i++) {
        Persona usuario = datos.get(i);
        lista+="<tr>";
        lista+="<td>" + usuario.getIdentificacion() + "</td>";
        lista+="<td>" + usuario.getTipoDocumentoEnObjeto()+ "</td>";
        lista+="<td>" + usuario.getPrimerNombre() + "</td>";
        lista+="<td>" + usuario.getPrimerApellido() + "</td>";
        lista+="<td>" + usuario.getCelular()+ "</td>";
        lista+="<td>" + usuario.getEmail()+ "</td>";
        lista+="<td>" + usuario.getGeneroPersona()+ "</td>";
        lista+="<td>" + usuario.getRolEnObjeto()+ "</td>";
        lista+="<td>";
        lista+="<a href='principal.jsp?CONTENIDO=usuariosFormulario.jsp&accion=Modificar&identificacion=" + usuario.getIdentificacion()+
                " 'title='Modificar'><img src='iconos/editar.png'></a>  "; 
        lista+="<img src='iconos/borrar.png'  title='Eliminar' onClick='eliminar("+ usuario.getIdentificacion()+")'> ";
        lista+="<img src='iconos/verDetalles.png' title='Ver Detalles' onClick='verDetalles("+ usuario.getIdentificacion()+")'> ";
        lista+="</td>";
        lista+="</tr>";
  }
%>

<h3>LISTA DE USUARIOS</h3>
<table id="ejemplo02" border="1">
    <tr>
        <th>Identificacion</th><th>Tipo de Documento</th><th>Nombres</th><th>Apellidos</th><th>Celular</th><th>Email</th><th>Genero</th><th>Rol</th>
                <th><a href="principal.jsp?CONTENIDO=usuariosFormulario.jsp&accion=Adicionar" title="Adicionar">
                        + </a></th>
    </tr>
    <%=lista%>
</table>

<script type="text/javascript">
    function eliminar(identificacion){
        respuesta= confirm("Realmente desea eliminar el registro con la identificacion"+identificacion+"?")
        if (respuesta) {
            document.location="principal.jsp?CONTENIDO=usuariosActualizar.jsp&accion=Eliminar&identificacion="+identificacion;
        }
    }

     function verDetalles(identificacion) {
        document.location="principal.jsp?CONTENIDO=usuarioDetalles.jsp&identificacion="+identificacion;
    }
</script>