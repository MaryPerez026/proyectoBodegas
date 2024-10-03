<%-- 
    Document   : CategoriasActualizar
    Created on : 2/10/2024, 10:44:09 PM
    Author     : MARY SALAZAR
--%>

<html>
<head>
    <meta charset="UTF-8">
    <title>categorias</title>
    <link rel="stylesheet" type="text/css" href="presentacion/EstiloInicio.css">
</head>
</html>

<%@page import="clases.Categoria"%>
<%
    
    String accion=request.getParameter("accion");
    String id=request.getParameter("id");

    Categoria categorias = new Categoria();
    categorias.setId(request.getParameter("id"));
    categorias.setNombre(request.getParameter("nombre"));
    categorias.setReferencia(request.getParameter("referencia"));
    categorias.setDescripcion(request.getParameter("descripcion"));
    switch(accion){
        case "Adicionar":
            categorias.grabar();
            break;
        case "Modificar":
            categorias.setId(request.getParameter("id"));
            categorias.modificar();
            break;
        case "Eliminar":
            categorias.setId(request.getParameter("id"));
            categorias.eliminar();
            break;
    }
 //response.sendRedirect("principal.jsp?CONTENIDO=categorias.jsp");
%>
<script type="text/javascript">
    document.location="principal.jsp?CONTENIDO=categorias.jsp";
</script>
