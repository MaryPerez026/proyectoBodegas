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
public class Devolucion {
    private String id;
    private String idInstitucion; // foranea de institucion (id)
    private String encargadoInstitucion; // foranea de persona (identificacion) 
    private String encargadoRecibir; // foranea de persona (identificacion)
    private String motivoDevolucion;
    private String fechaDevolucion;
    
    public Devolucion(){
        
    }
    
    public Devolucion(String id) {
        String cadenaSQL="select idInstitucion, encargadoInstitucion,encargadoRecibir,motivoDevolucion, fechaDevolucion from devolucion where id="+id;
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.id=id;
                idInstitucion=resultado.getString("idInstitucion");
                encargadoInstitucion=resultado.getString("encargadoInstitucion");
                encargadoRecibir=resultado.getString("encargadoRecibir");
                motivoDevolucion=resultado.getString("motivoDevolucion");
                fechaDevolucion=resultado.getString("fechaDevolucion");
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

    public String getIdInstitucion() {
        String resultado=idInstitucion;
        if(idInstitucion==null) resultado="";
        return resultado;
    }

    public void setIdInstitucion(String idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getEncargadoInstitucion() {
        String resultado=encargadoInstitucion;
        if(encargadoInstitucion==null) resultado="";
        return resultado;
    }

    public void setEncargadoInstitucion(String encargadoinstitucion) {
        this.encargadoInstitucion = encargadoinstitucion;
    }

    public String getEncargadoRecibir() {
        String resultado=encargadoRecibir;
        if(encargadoRecibir==null) resultado="";
        return resultado;
    }

    public void setEncargadoRecibir(String encargadoRecibir) {
        this.encargadoRecibir = encargadoRecibir;
    }

    public String getMotivoDevolucion() {
        String resultado=motivoDevolucion;
        if(motivoDevolucion==null) resultado="";
        return resultado;
    }

    public void setMotivoDevolucion(String motivoDevolucion) {
        this.motivoDevolucion = motivoDevolucion;
    }

    public String getFechaDevolucion() {
        String resultado=fechaDevolucion;
        if(fechaDevolucion==null) resultado="";
        return resultado;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    //llave foranea de institucion 
    public Institucion getInstitucion(){
        return new Institucion(idInstitucion);
    }
    //llave foranea de persona 
    public Persona getPersona(){
        return new Persona(encargadoInstitucion);
    }
    //llave foranea de persona
    public Persona getPersona1(){  //hacer revisar del profesor
        return new Persona(encargadoRecibir);
    }
    
    public boolean grabar(){
        String cadenaSQL="insert into devolucion (idInstitucion, encargadoInstitucion, encargadoRecibir, motivoDevolucion, fechaDevolucion) "
                + "values ('"+idInstitucion+"','"+encargadoInstitucion+"','"+encargadoRecibir+"','"+motivoDevolucion+"','"+fechaDevolucion+"')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(){
        String cadenaSQL = "update devolucion set idInstitucion='"+idInstitucion+"', encargadoInstitucion='"+encargadoInstitucion+"', encargadoRecibir='"+encargadoRecibir+"', motivoDevolucion='"+motivoDevolucion+"', fechaDevolucion='"+fechaDevolucion+"' "
                + "where id= "+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar(){
        String cadenaSQL="delete from  devolucion where id= "+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista(String filtro, String orden){
        if(filtro!=null && filtro !="") filtro= " where " + filtro;
        else filtro=" ";
        if(orden!=null && orden!="") orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL= "select id, idInstitucion, encargadoInstitucion,encargadoRecibir,motivoDevolucion, fechaDevolucion  from devolucion " 
                           + " inner join persona on identificacion.persona=encargadoInstitucion.devolucion "
                           + " inner join persona on identificacion.persona=encargadoRecibir.devolucion "
                           + " inner join institucion "
                           + " on idInstitucion.devolucion=id.institucion"+filtro + orden;
        return ConectorBD.consultar(cadenaSQL);
    }

        public static List<Devolucion> getListaEnObjetos(String filtro, String orden){
        List<Devolucion> lista= new ArrayList<>();
        ResultSet datos = Devolucion.getLista(filtro, orden);
        if(datos!=null){
            try {
                while(datos.next()){
                Devolucion devolucion = new Devolucion();
                devolucion.setId(datos.getString("id"));
                devolucion.setIdInstitucion(datos.getString("idInstitucion"));
                devolucion.setEncargadoInstitucion(datos.getString("encargadoInstitucion"));
                devolucion.setEncargadoRecibir(datos.getString("encargadoRecibir"));
                devolucion.setMotivoDevolucion(datos.getString("motivoDevolucion"));
                devolucion.setFechaDevolucion(datos.getString("fechaDevolucion"));
                lista.add(devolucion);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Devolucion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
}
