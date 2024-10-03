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
public class DetalleDevolucion {
    private String  id;
    private String idProducto; // foranea de inventario (id)
    private String cantidad; 
    private String idUnidadMedida; // foranea de unidadDeMedida (id) 
    private String  codigoDevolucion; // foranea de devolucion (id)
    private String codigoDespacho; // foranea de despachoCanastilla (idDespachoCanastilla)
    
    public DetalleDevolucion(){
        
    }
    
    public DetalleDevolucion(String id) {
            String cadenaSQL="select idProducto, cantidad,idUnidadMedida,codigoDevolucion, codigoDespacho from detalleDevolucion where id="+id;
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.id=id;
                idProducto=resultado.getString("idProducto");
                idUnidadMedida=resultado.getString("idUnidadMedida");
                codigoDevolucion=resultado.getString("codigoDevolucion");
                codigoDespacho=resultado.getString("codigoDespacho");
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

    public String getIdUnidadMedida() {
        String resultado=idUnidadMedida;
        if(idUnidadMedida==null) resultado="";
        return resultado;
    }

    public void setIdUnidadMedida(String idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public String getCodigoDevolucion() {
        String resultado=codigoDevolucion;
        if(codigoDevolucion==null) resultado="";
        return resultado;
    }

    public void setCodigoDevolucion(String codigoDevolucion) {
        this.codigoDevolucion = codigoDevolucion;
    }

    public String getCodigoDespacho() {
        String resultado=codigoDespacho;
        if(codigoDespacho==null) resultado="";
        return resultado;
    }

    public void setCodigoDespacho(String codigoDespacho) {
        this.codigoDespacho = codigoDespacho;
    }
    
    // llave foranea de la clase inventario 
    public Inventario getInventario(){
        return new Inventario(idProducto);
    }
    // llave foranea de la clase unidadMedidad
    public UnidadDeMedida getUnidadDeMedida(){
        return new UnidadDeMedida(idUnidadMedida);
    }
    // llave foranea de la clase devolucion 
    public Devolucion getDevolucion(){
        return new Devolucion(codigoDevolucion);
    }
    // llave foranea despacho despachoCanastilla
    public DespachoCanastilla getDespachoCanastilla(){
        return new DespachoCanastilla(codigoDespacho);
    }
    
    public boolean grabar(){
        String cadenaSQL="insert into detalleDevolucion (idProducto, cantidad, idUnidadMedida, codigoDevolucion, codigoDespacho) "
                + "values ('"+idProducto+"','"+cantidad+"','"+idUnidadMedida+"','"+codigoDevolucion+"','"+codigoDespacho+"')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(){
        String cadenaSQL = "update detalleDevolucion set idProducto='"+idProducto+"', cantidad='"+cantidad+"', idUnidadMedida='"+idUnidadMedida+"', codigoDevolucion='"+codigoDevolucion+"', codigoDespacho='"+codigoDespacho+"' "
                + "where id= "+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar(){
        String cadenaSQL="delete from  detalleDevolucion where id= "+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista(String filtro, String orden){
        if(filtro!=null && filtro !="") filtro= " where " + filtro;
        else filtro=" ";
        if(orden!=null && orden!="") orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL= "select id, idProducto, cantidad,idUnidadMedida,codigoDevolucion, codigoDespacho from detalleDevolucion " 
                           + " inner join inventario on id.inventario=idProducto.detalleDevolucion "
                           + " inner join unidadDeMedida on id.unidadDeMedida=idUnidadMedida.detalleDevolucion "
                           + " inner join devolucion on id.devolucion=codigoDevolucion.detalleDevolucion "
                           + " inner join depachoCanastilla on codigoDespacho.detalleDevolucion=idDespachoCanastilla.despachoCanastilla"+filtro + orden;
        return ConectorBD.consultar(cadenaSQL);
    }

        public static List<DetalleDevolucion> getListaEnObjetos(String filtro, String orden){
        List<DetalleDevolucion> lista= new ArrayList<>();
        ResultSet datos = DetalleDevolucion.getLista(filtro, orden);
        if(datos!=null){
            try {
                while(datos.next()){
                DetalleDevolucion devolucionDetallle = new DetalleDevolucion();
                devolucionDetallle.setId(datos.getString("id"));
                devolucionDetallle.setIdProducto(datos.getString("idProducto"));
                devolucionDetallle.setCantidad(datos.getString("cantidad"));
                devolucionDetallle.setIdUnidadMedida(datos.getString("idUnidadMedida"));
                devolucionDetallle.setCodigoDevolucion(datos.getString("codigoDevolucion"));
                devolucionDetallle.setCodigoDespacho(datos.getString("codigoDespacho"));
                lista.add(devolucionDetallle);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DetalleDevolucion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
}
