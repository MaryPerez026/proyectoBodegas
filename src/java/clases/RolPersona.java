/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author sena
 */
public class RolPersona {
    
    private String codigo;

    public RolPersona(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
         return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre(){
        String nombre=null;
        switch(codigo){
            case "A": nombre="Administrador"; break;
            case "I": nombre="Encargado Institucion"; break;
            case "E": nombre="Empleado"; break;
            default: nombre="Desconocido"; break;
        }
        return nombre;
    }

    @Override
    public String toString() {
        return getNombre();
    }
    
    public String getMenu(){
        String menu="<ul>";
        menu+="<li><a href='principal.jsp?CONTENIDO=inicio.jsp'>Inicio</a></li>";
        switch(this.codigo){
            case "A":
                    menu+="<li><a href='principal.jsp?CONTENIDO=usuarios.jsp'>Usuarios</a></li>";
                    menu+="<li><a href='principal.jsp?CONTENIDO=proveedores.jsp'>Proveedores</a></li>";
                    menu+="<li><a href='principal.jsp?CONTENIDO=categorias.jsp'>Categorias</a></li>";
                    menu+="<li><a href='principal.jsp?CONTENIDO=inventario.jsp'>Inventario</a></li>";
                    menu+="<li><a href='principal.jsp?CONTENIDO=entrada.jsp'>Entradas</a></li>";
                    menu+="<li><a href='principal.jsp?CONTENIDO=vehiculo.jsp'>Vehiculo</a></li>";
                    menu+="<li><a href='principal.jsp?CONTENIDO=departamentos.jsp'>Departamentos</a></li>";
                    menu+="<li><a href='principal.jsp?CONTENIDO=municipios.jsp'>Municipios</a></li>";
                    menu+="<li><a href='principal.jsp?CONTENIDO=instituciones.jsp'>Instituciones</a></li>";
                    menu+="<li><a href='principal.jsp?CONTENIDO=rutas.jsp'>Rutas</a></li>";
                    menu+="<li><a href='principal.jsp?CONTENIDO=despacho.jsp'>Despacho</a></li>";
                    menu+="<li><a href='principal.jsp?CONTENIDO=devoluciones.jsp'>Devoluciones</a></li>";
                    menu+="<li>Reportes";
                    menu+="<ul>";
                    menu+="<li><a href='principal.jsp?CONTENIDO=reportes/productosMasVendidos.jsp'>Productos mas vendidos</a></li>";
                    menu+="<li><a href='principal.jsp?CONTENIDO=reportes/productosMenosVendidos.jsp'>Productos menos vendidos</a></li>";
                    menu+="</ul>";
                    menu+="<li>Indicadores";
                    menu+="<ul>";
                    menu+="<li><a href='principal.jsp?CONTENIDO=indicadores/ventas.jsp'>Ventas</a></li>";
                    menu+="</ul>";
                    break;
                    
        }
         menu+="<li> <a href='index.jsp'>Salir</a></li>";
         menu+="</ul>";
         return menu;
        }

    
    public String getListaEnOption(){
        String lista="";
        if(codigo==null) codigo="";
        switch(codigo){
            case "A":
                lista="<option value='A' selected>Administrador </option><option value='E'>Empleado</option><option value='I'>Encargando Institucion</option>";
                break;
            case "E":
                lista="<option value='A' >Administrador</option><option value='E' selected>Empleado</option><option value='I'>Encargando Institucion</option>";
                break;
            case "I":
                lista="<option value='A' >Administrador</option><option value='E' >Empleado</option><option value='I' selected>Encargando Institucion</option>";
                break;
            default:
                lista="<option value='A' selected>Administrador </option><option value='E'>Empleado</option><option value='I'>Encargando Institucion</option>";
                break;
        }
        return lista;
    }
        
    }