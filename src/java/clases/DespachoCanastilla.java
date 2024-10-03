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
public class DespachoCanastilla {
    private String idDespachoCanastilla;
    private String idProducto; //foranea de inventario (id)
    private String cantidad;
    private String idDespacho; // foranea de despacho (id)
    private String idInstitucion; // foranea de institucion (id)
    
    public DespachoCanastilla(){
        
    }
    
    public DespachoCanastilla(String idDespachoCanastilla) {
        String cadenaSQL="select idProducto, cantidad,idDespacho,idInstitucion from despachoCanastilla where id="+idDespachoCanastilla;
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.idDespachoCanastilla=idDespachoCanastilla;
                idProducto=resultado.getString("idProducto");
                cantidad=resultado.getString("cantidad");
                idDespacho=resultado.getString("idDespacho");
                idInstitucion=resultado.getString("idInstitucion");
            }
        } catch (SQLException ex) {
            System.out.println("Error al consulta el idDespachoCanastilla "+ ex.getMessage());
        }
    }

    public String getIdDespachoCanastilla() {
        String resultado=idDespachoCanastilla;
        if(idDespachoCanastilla==null) resultado="";
        return resultado;
    }

    public void setIdDespachoCanastilla(String idDespachoCanastilla) {
        this.idDespachoCanastilla = idDespachoCanastilla;
    }

    public String getIdProducto() {
        String resultado=idProducto;
        if(idProducto==null) resultado="";
        return resultado;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getCantidad() {
        String resultado=cantidad;
        if(cantidad==null) resultado="";
        return resultado;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getIdDespacho() {
        String resultado=idDespacho;
        if(idDespacho==null) resultado="";
        return resultado;
    }

    public void setIdDespacho(String idDespacho) {
        this.idDespacho = idDespacho;
    }

    public String getIdInstitucion() {
        String resultado=idInstitucion;
        if(idInstitucion==null) resultado="";
        return resultado;
    }

    public void setIdInstitucion(String idInstitucion) {
        this.idInstitucion = idInstitucion;
    }
    
    //llave foranea de la clase inventario
    public Inventario getInventario(){
        return new Inventario(idProducto);
    }
    
    //llave foranea de la clase despacho
    public Despacho getDespacho(){
        return new Despacho(idDespacho);
    }
    
    //llave foranea de la clase institucion 
    public Institucion getInstitucion(){
        return new Institucion(idInstitucion);
    }
    
    public boolean grabar(){
        String cadenaSQL="insert into despachoCanastilla (idDespachoCanastilla, idProducto, cantidad, idDespacho, idInstitucion) "
                + "values ('"+idDespachoCanastilla+"','"+idProducto+"','"+cantidad+"','"+idDespacho+"','"+idInstitucion+"')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(String idDespachoAnterior){
        String cadenaSQL = "update despachoCanastilla set idDespachoCanastilla='"+idDespachoCanastilla+"', idProducto='"+idProducto+"', cantidad='"+cantidad+"', idDespacho='"+idDespacho+"', idInstitucion='"+idInstitucion+"' "
                + "where idDespachoCanastilla= "+idDespachoAnterior;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar(){
        String cadenaSQL="delete from  despachoCanastilla where idDespachoCanastilla="+idDespachoCanastilla;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista(String filtro, String orden){
        if(filtro!=null && filtro !="") filtro= " where " + filtro;
        else filtro=" ";
        if(orden!=null && orden!="") orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL= "select idDespachoCanastilla,idProducto, cantidad,idDespacho,idInstitucion from despachoCanastilla " 
                           + " inner join inventario on id.inventario=idProducto.despachoCanastilla "
                           + " inner join despacho on id.despacho=idDespacho.despachoCanastilla "
                           + " inner join institucion "
                           + " on idInstitucion.despachoCanastilla=id.institucion"+filtro + orden;
        return ConectorBD.consultar(cadenaSQL);
    }

        public static List<DespachoCanastilla> getListaEnObjetos(String filtro, String orden){
        List<DespachoCanastilla> lista= new ArrayList<>();
        ResultSet datos = DespachoCanastilla.getLista(filtro, orden);
        if(datos!=null){
            try {
                while(datos.next()){
                DespachoCanastilla despacharCanasta = new DespachoCanastilla();
                despacharCanasta.setIdDespachoCanastilla(datos.getString("idDespachoCanastilla"));
                despacharCanasta.setIdProducto(datos.getString("idProducto"));
                despacharCanasta.setCantidad(datos.getString("cantidad"));
                despacharCanasta.setIdDespacho(datos.getString("idDespacho"));
                despacharCanasta.setIdInstitucion(datos.getString("idInstitucion"));
                lista.add(despacharCanasta);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DespachoCanastilla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
}
