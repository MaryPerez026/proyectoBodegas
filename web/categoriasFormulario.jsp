<%@page import="clases.Categoria"%>
<%
    String accion = request.getParameter("accion");
    String id = "Sin generar"; 
    String nombre ="";
    
    Categoria categorias = new Categoria();
    if (accion != null && accion.equals("Modificar")) {
        id = request.getParameter("id");
        categorias = new Categoria(id); 
    }
%>

<h3><%=accion.toUpperCase() %>categoria</h3>
<form name="formulario" method="post" action="principal.jsp?CONTENIDO=categoriasActualizar.jsp">
    <table border="0">
        <tr>
            <th>Id</th>
            <td><input type="text" name="Id" maxlength="12" value="<%=id%>" required></td>
        </tr>
        <tr>
            <th>Nombre</th>
            <td><textarea name="nombre" cols="50" rows="5"><%= categorias.getNombre()%></textarea>
                <!-- <select name="nombre" required> 
                    <option value="Frutas" <%= nombre.equals("Frutas") ? "selected" : "" %>>Frutas</option>
                    <option value="Verduras" <%= nombre.equals("Verduras") ? "selected" : "" %>>Verduras</option>
                    <option value="Legumbres" <%= nombre.equals("Legumbres") ? "selected" : "" %>>Legumbres</option>
                </select> -->
            </td>
        </tr>   
        <tr>
            <th>Referencia</th>
            <td><%=categorias.getReferenciaEnObjeto().getRadioButtons() %></td>   
        </tr>
        <tr>
            <th>Descripción</th>
            <td><textarea name="descripcion" cols="50" rows="5"><%= categorias.getDescripcion() %></textarea></td> 
        </tr>
    </table>
    <input type="hidden" name="id" value="<%= id %>"> 
    <input type="submit" name="accion" value="<%= accion != null ? accion : "Agregar" %>"> 
    <input type="button" value="Cancelar" onClick="window.history.back()"> 
</form>
