/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import clasesGenericas.ConectorBD;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sena
 */
public class Inventario {
    private String id;
    private String nombreProducto;
    private String stock;
    private String idUnidadDeMedida; //foranea unidad de medida (id)
    private String idCategoria; // foranea categoria (id)
    private String observaciones;
    private String foto;
    
    public Inventario(){
        
    }
    
    public Inventario(String id) {
        String cadenaSQL="select nombreProducto, stock, idUnidadDeMedida, idCategoria, observaciones,foto from inventario where id="+id;
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.id=id;
                nombreProducto=resultado.getString("nombreProducto");
                stock=resultado.getString("stock");
                idUnidadDeMedida=resultado.getString("idUnidadDeMedida");
                idCategoria=resultado.getString("idCategoria");
                observaciones=resultado.getString("observaciones");
                foto=resultado.getString("foto");

            }
        } catch (SQLException ex) {
            System.out.println("Error al consulta el id "+ ex.getMessage());
        }
    }

    public String getId() {
        String resultado=id;
        if(id==null) resultado="";
        return resultado;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreProducto() {
        String resultado=nombreProducto;
        if(nombreProducto==null) resultado="";
        return resultado;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getStock() {
        String resultado=stock;
        if(stock==null) resultado="";
        return resultado;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getIdUnidadDeMedida() {
        String resultado=idUnidadDeMedida;
        if(idUnidadDeMedida==null) resultado="";
        return resultado;
    }

    public void setIdUnidadMedida(String idUnidadMedida) {
        this.idUnidadDeMedida = idUnidadMedida;
    }

    public String getIdCategoria() {
        String resultado=idCategoria;
        if(idCategoria==null) resultado="";
        return resultado;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getObservaciones() {
        String resultado=observaciones;
        if(observaciones==null) resultado="";
        return resultado;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFoto() {
        String resultado=foto;
        if(foto==null) resultado="";
        return resultado;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public boolean grabar(){
        String cadenaSQL="insert into inventario (nombreProducto, stock, idUnidadDeMedida, idCategoria, observaciones,foto)"
                + "values ('"+nombreProducto+"', '"+stock+"', '"+idUnidadDeMedida+"', '"+idCategoria+"', '"+observaciones+"', '"+foto+"')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(){
        String cadenaSQL = "update inventario set nombreProducto='"+nombreProducto+"', stock='"+stock+"', idUnidadDeMedida='"+idUnidadDeMedida+"', idCategoria='"+idCategoria+"', observaciones='"+observaciones+"', foto='"+foto+"' where id= "+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar(){
        String cadenaSQL="delete from inventario where id="+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista(String filtro, String orden){
        if(filtro!=null && filtro !="") filtro= " where " + filtro;
        else filtro=" ";
        if(orden!=null && orden!="") orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL= "select nombreProducto, stock, idUnidadDeMedida, idCategoria, observaciones,foto from inventario "
                + "inner join unidadDeMedida "
                + "on  inventario.idUnidadDeMedida=id.unidadDeMedida "
                + "inner join categoria " 
                + "on inventario.idCategoria=id.categoria " + filtro + orden;
        return ConectorBD.consultar(cadenaSQL);
    }

        public static List<Inventario> getListaEnObjetos(String filtro, String orden){
        List<Inventario> lista= new ArrayList<>();
        ResultSet datos = Inventario.getLista(filtro, orden);
        if(datos!=null){
            try {
                while(datos.next()){
                Inventario inventario = new Inventario();
                inventario.setId(datos.getString("id"));
                inventario.setNombreProducto(datos.getString("nombreProducto"));
                inventario.setStock(datos.getString("stock"));
                inventario.setIdUnidadMedida(datos.getString("setIdUnidadDeMedida"));
                inventario.setIdCategoria(datos.getString("idCategoria"));
                inventario.setObservaciones(datos.getString("observaciones"));
                inventario.setFoto(datos.getString("foto"));
                lista.add(inventario);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
}
