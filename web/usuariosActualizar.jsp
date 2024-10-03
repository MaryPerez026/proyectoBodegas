<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="clases.Persona"%>
<%@page import="org.apache.tomcat.util.http.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
boolean subioArchivo = false;
Map<String, String> variables = new HashMap<String, String>(); 
boolean isMultipart = ServletFileUpload.isMultipartContent(request); 

if (!isMultipart) { 
    variables.put("accion", request.getParameter("accion"));  
    variables.put("identificacion", request.getParameter("identificacion"));
} else { 
    String rutaActual = getServletContext().getRealPath("/"); 
    File destino = new File(rutaActual + "/imagenes/");
    DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 1024, destino); 
    ServletFileUpload upload = new ServletFileUpload(factory);
    
    ServletRequestContext origen = new ServletRequestContext(request); 
    List elementosFormulario = upload.parseRequest(origen);
    Iterator iterador = elementosFormulario.iterator();
    
    while (iterador.hasNext()) {
        FileItem elemento = (FileItem) iterador.next();
        if (elemento.isFormField()) {
            variables.put(elemento.getFieldName(), elemento.getString());
        } else {
            if (!elemento.getName().equals("")) {
                subioArchivo = true;
                elemento.write(new File(destino, elemento.getName()));
                variables.put(elemento.getFieldName(), elemento.getName());
            }
        }
    }
}

Persona usuario = new Persona();
usuario.setIdentificacion(variables.get("identificacion"));
usuario.setTipoDocumento(variables.get("tipoDocumento"));
usuario.setPrimerNombre(variables.get("primerNombre"));
usuario.setSegundoNombre(variables.get("segundoNombre"));
usuario.setPrimerApellido(variables.get("primerApellido"));
usuario.setSegundoApellido(variables.get("segundoApellido"));
usuario.setCelular(variables.get("celular"));
usuario.setEmail(variables.get("email"));
usuario.setDireccion(variables.get("direccion"));
usuario.setGenero(variables.get("genero"));
usuario.setRol(variables.get("rol"));

// Verifica el rol antes de asignarlo
/*String rol = variables.get("rol");
if (rol == null || rol.isEmpty()) {
    out.print("Error: el rol no puede ser nulo.");
    return; // Termina la ejecución si el rol es nulo
}
usuario.setRol(rol);*/

usuario.setClave(variables.get("clave"));
usuario.setFechaExpedicion(variables.get("fechaExpedicion"));
usuario.setFechaNacimiento(variables.get("fechaNacimiento"));

switch (variables.get("accion")) {
    case "Adicionar":
        usuario.grabar();
        break;
    
    case "Modificar":
        if (!subioArchivo) {
            Persona auxiliar = new Persona(variables.get("identificacionAnterior"));
            usuario.setFoto(auxiliar.getFoto());
        }
        usuario.modificar(variables.get("identificacionAnterior"));
        break;

    case "Eliminar":
        usuario.eliminar();
        break;
}

%>

<script type="text/javascript">
    document.location = "principal.jsp?CONTENIDO=usuarios.jsp";
</script>
