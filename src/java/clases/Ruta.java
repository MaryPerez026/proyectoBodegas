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
public class Ruta {
    private String id;
    private String nombre;
    private String distancia;
    private String peajes;
    
    public Ruta(){
        
    }
    
    public Ruta(String id) {
        String cadenaSQL="select nombre, distancia,peajes from ruta where id="+id;
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.id=id;
                nombre=resultado.getString("nombre");
                distancia=resultado.getString("distancia");
                peajes=resultado.getString("peajes");
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

    public String getNombre() {
        String resultado=nombre;
        if(nombre==null) resultado="";
        return resultado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDistancia() {
        String resultado=distancia;
        if(distancia==null) resultado="";
        return resultado;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getPeajes() {
        String resultado=peajes;
        if(peajes==null) resultado="";
        return resultado;
    }

    public void setPeajes(String peajes) {
        this.peajes = peajes;
    }
    
    public boolean grabar(){
        String cadenaSQL="insert into ruta (nombre, distancia, peajes)"
                + "values ('"+nombre+"','"+distancia+"','"+peajes+"')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(){
        String cadenaSQL = "update ruta set nombre='"+nombre+"', distancia='"+peajes+"' where id= "+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar(){
        String cadenaSQL="delete from ruta where id="+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista(String filtro, String orden){
        if(filtro!=null && filtro !="") filtro= " where " + filtro;
        else filtro=" ";
        if(orden!=null && orden!="") orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL= "select id, nombre, distancia,peajes from ruta " +filtro + orden;
        return ConectorBD.consultar(cadenaSQL);
    }

        public static List<Ruta> getListaEnObjetos(String filtro, String orden){
        List<Ruta> lista= new ArrayList<>();
        ResultSet datos = Ruta.getLista(filtro, orden);
        if(datos!=null){
            try {
                while(datos.next()){
                Ruta ruta = new Ruta();
                ruta.setId(datos.getString("id"));
                ruta.setNombre(datos.getString("nombre"));
                ruta.setDistancia(datos.getString("distancia"));
                ruta.setPeajes(datos.getString("peajes"));
                lista.add(ruta);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ruta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
}
