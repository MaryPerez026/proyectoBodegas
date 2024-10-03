package clases;

import clasesGenericas.ConectorBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Categoria {
    private String id; 
    private String nombre; 
    private String referencia; 
    private String descripcion; 

    public Categoria() {
    }

    public Categoria(String id) {
        this.id = id; 
        String cadenaSQL = "select nombre, referencia, descripcion from categoria where id='" + id + "'";
        ResultSet resultado = ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.nombre = resultado.getString("nombre");
                this.referencia = resultado.getString("referencia");
                this.descripcion = resultado.getString("descripcion");
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener la categoría: " + ex.getMessage());
        }
    }

    public ReferenciaCategoria getReferenciaEnObjeto() {
        return new ReferenciaCategoria(referencia);
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getId() {
        String resultado=id;
        if(id==null) resultado="";
        return resultado;  
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
     String resultado=nombre;
        if(nombre==null) resultado="";
        return resultado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    public String getDescripcion() {
        String resultado=descripcion;
        if(descripcion==null) resultado="";
        return resultado; 
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 

    public boolean grabar() {
        String cadenaSQL = "insert into Categoria(nombre, referencia, descripcion) "
                + "values ('" + nombre + "', '" + referencia + "', '" + descripcion + "')";
        return ConectorBD.ejecutarQuery(cadenaSQL); 
    }

    public boolean modificar() {
    String cadenaSQL = "update categoria set nombre='" + nombre + "', "
        + "referencia=" + (referencia == null ? "null" : "'" + referencia + "'") + ", "
        + "descripcion=" + (descripcion == null ? "null" : "'" + descripcion + "'") + " "
        + "where id='" + id + "'";

        return ConectorBD.ejecutarQuery(cadenaSQL);  }

    public boolean eliminar() {
        String cadenaSQL = "delete from categoria where id='" + id + "'";
        return ConectorBD.ejecutarQuery(cadenaSQL); 
    }

    public static ResultSet getLista(String filtro, String orden) {
        if (filtro != null && !filtro.trim().isEmpty()) {
            filtro = " where " + filtro;
        } else {
            filtro = ""; 
        }
        if (orden != null && !orden.trim().isEmpty()) {
            orden = " order by " + orden;
        } else {
            orden = ""; 
        }
        String cadenaSQL = "select id, nombre, referencia, descripcion from categoria " + filtro + orden;
        return ConectorBD.consultar(cadenaSQL); 
    }

    public static List<Categoria> getListaEnObjetos(String filtro, String orden) {
        List<Categoria> lista = new ArrayList<>(); 
        ResultSet datos = Categoria.getLista(filtro, orden); 
        if (datos != null) {
            try {
                // Recorrer los resultados y crear objetos de categoría
                while (datos.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(datos.getString("id"));
                    categoria.setNombre(datos.getString("nombre"));
                    categoria.setReferencia(datos.getString("referencia"));
                    categoria.setDescripcion(datos.getString("descripcion"));
                    lista.add(categoria); }
            } catch (SQLException ex) {
                Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }
        return lista; 
    }
}
