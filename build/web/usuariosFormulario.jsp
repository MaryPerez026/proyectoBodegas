
<%@page import="clases.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
String accion = request.getParameter("accion");
String identificacion = request.getParameter("identificacion");
Persona usuario = new Persona();

if (accion != null && accion.equals("Modificar") && identificacion != null) {
    usuario = new Persona(identificacion);
}
%>

<h3><%=accion.toUpperCase() %> USUARIO</h3>
<form name="formulario" method="post" action="principal.jsp?CONTENIDO=usuariosActualizar.jsp" enctype="multipart/form-data">
    <table border="0">
        <tr>
            <th>Identificación</th>
            <td><input type="text" name="identificacion" value="<%= usuario.getIdentificacion() %>" size="50" maxlength="50"></td>
        </tr>
       
        <tr>
            <th>Tipo Documento</label></th>
            <th><%=usuario.getTipoDocumentoEnObjeto().getListaEnOptions()%></th>   
        </tr>
        
        <tr>
            <th>Primer Nombre</th>
            <td><input type="text" name="primerNombre" value="<%= usuario.getPrimerNombre() %>" size="50" maxlength="50"></td>
        </tr>
        <tr>
            <th>Segundo Nombre</th>
            <td><input type="text" name="segundoNombre" value="<%= usuario.getSegundoNombre() %>" size="50" maxlength="50"></td>
        </tr>
        <tr>
            <th>Primer Apellido</th>
            <td><input type="text" name="primerApellido" value="<%= usuario.getPrimerApellido() %>" size="50" maxlength="50"></td>
        </tr>
        <tr>
            <th>Segundo Apellido</th>
            <td><input type="text" name="segundoApellido" value="<%= usuario.getSegundoApellido() %>" size="50" maxlength="50"></td>
        </tr>
        <tr>
            <th><label for="telefono">Celular</label></th>
            <td><input type="tel" id="telefono" name="celular" value="<%= usuario.getCelular() %>" maxlength="12"></td> 
        </tr>
        <tr>
            <th><label for="email">Correo Electrónico</label></th>
            <td><input type="email" id="email" name="email" value="<%= usuario.getEmail() %>" maxlength="80" required></td> 
        </tr>
        <tr>
            <th>Dirección</th>
            <td><input type="text" name="direccion" value="<%= usuario.getDireccion() %>" maxlength="100"></td>
        </tr>
        <tr>
            <th>Género</th>
        <th><%=usuario.getGeneroPersona().getRadioButtons() %></th>   
        </tr>
        
        <tr>
            <th>Rol</th>
       <td><select name="rol"><%=usuario.getRolEnObjeto().getListaEnOption()%></select></td>
        </tr>
        <tr>
            <th>Clave</th>
            <td><input type="password" name="clave" maxlength="100"></td> 
        </tr>
        <tr>
            <th><label for="fechaExpedicion">Fecha De Expedición</label></th>
            <td><input type="date" id="fechaExpedicion" name="fechaExpedicion" value="<%= usuario.getFechaExpedicion() %>"></td> 
        <tr>
            <th><label for="fechaNacimiento">Fecha De Nacimiento</label></th>
            <td><input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%= usuario.getFechaNacimiento() %>"></td> 
        <tr>
            <th>Foto</th>
            <td><input type="file" name="foto" accept="image/*" onchange="mostrarFoto();"></td>
        </tr>
    </table>
    <input type="hidden" name="identificacionAnterior" value="<%= identificacion %>">
    <p>
        <input id="addCatg" type="submit" name="accion" value="<%= accion != null ? accion : "Guardar" %>">
        <input id="regresar" type="button" value="Cancelar" onClick="window.history.back()">
    </p>
</form>

<td><img src="imagenes/<%= usuario.getFoto() %>" id="foto" width="300" height="350"></td>

<script type="text/javascript">
function mostrarFoto(){
        var lector=new FileReader();
        lector.readAsDataURL(document.formulario.foto.files[0]);
        lector.onloadend =function(){
            doment.getElementById("foto").src=lector.result;

        }
    }
</script>
