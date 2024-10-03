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
public class DetalleEntrada {
    private String id;
    private String idEntrada; // foranea de entrada (id)
    private String idInventario; // foranea de inventario (id) 
    private String cantidad;
    
    public DetalleEntrada(){
        
    }
    
    public DetalleEntrada(String id) {
        String cadenaSQL="select idEntrada, idInventario, cantidad from detalleEntrada where id="+id;
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.id=id;
                idEntrada=resultado.getString("idEntrada");
                idInventario=resultado.getString("idInventario");
                cantidad=resultado.getString("cantidad");
                
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

    public String getIdEntrada() {
        String resultado=idEntrada;
        if(idEntrada==null) resultado="";
        return resultado;
    }

    public void setIdEntrada(String idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getIdInventario() {
        String resultado=idInventario;
        if(idInventario==null) resultado="";
        return resultado;
    }

    public void setIdInventario(String idInventario) {
        this.idInventario = idInventario;
    }

    public String getCantidad() {
        String resultado=cantidad;
        if(cantidad==null) resultado="";
        return resultado;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
    public boolean grabar(){
        String cadenaSQL="insert into detalleEntrada (idEntrada, idInventario, cantidad)"
                + "values ('"+idEntrada+"', '"+idInventario+"', '"+cantidad+"')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(){
        String cadenaSQL = "update detalleEntrada set idEntrada='"+idEntrada+"', idInventario='"+idInventario+"', cantidad='"+cantidad+"' where id= "+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar(){
        String cadenaSQL="delete from detalleEntrada where id="+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista(String filtro, String orden){
        if(filtro!=null && filtro !="") filtro= " where " + filtro;
        else filtro=" ";
        if(orden!=null && orden!="") orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL= "select id, idEntrada, idInventario, cantidad from detalleEntrada "
                + "inner join Entrada "
                + "on  Entrada.id=idEntrada.detalleEntrada "
                + "inner join Inventario " 
                + "on inventario.id=idInventario.detalleEntrada " + filtro + orden;
        return ConectorBD.consultar(cadenaSQL);
    }

        public static List<DetalleEntrada> getListaEnObjetos(String filtro, String orden){
        List<DetalleEntrada> lista= new ArrayList<>();
        ResultSet datos = DetalleEntrada.getLista(filtro, orden);
        if(datos!=null){
            try {
                while(datos.next()){
                DetalleEntrada entradaDetalle = new DetalleEntrada();
                entradaDetalle.setId(datos.getString("id"));
                entradaDetalle.setIdEntrada(datos.getString("idEntrada"));
                entradaDetalle.setIdInventario(datos.getString("idInventario"));
                entradaDetalle.setCantidad(datos.getString("cantidad"));
                lista.add(entradaDetalle);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DetalleEntrada.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
}
