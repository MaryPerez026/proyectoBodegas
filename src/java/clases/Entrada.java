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
public class Entrada {
    private String id;
    private String fecha;
    private String numeroDocumento; //documento proveedor  // foranea de persona (identificacion)
   
    
    public Entrada(){
        
    }
    
    public Entrada(String id) {
        String cadenaSQL="select fecha, numeroDocumento from categoria where id="+id;
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.id=id;
                fecha=resultado.getString("fecha");
                numeroDocumento=resultado.getString("numeroDocumento");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getFecha() {
        String resultado=fecha;
        if(fecha==null) resultado="";
        return resultado;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumeroDocumento() {
        String resultado=numeroDocumento;
        if(numeroDocumento==null) resultado="";
        return resultado;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    //llave foranea de la clase persona
    public Persona getPersona(){
        return new Persona(numeroDocumento);
    }
    
     public boolean grabar(){
        String cadenaSQL="insert into entrada (fecha, numeroDocumento)"
                + "values ('"+fecha+"', '"+numeroDocumento+"')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(){
        String cadenaSQL = "update entrada set fecha='"+fecha+"', numeroDocumento='"+numeroDocumento+"' where id= "+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar(){
        String cadenaSQL="delete from entrada where id="+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista(String filtro, String orden){
        if(filtro!=null && filtro !="") filtro= " where " + filtro;
        else filtro=" ";
        if(orden!=null && orden!="") orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL= "select id.entrada, fecha.entrada numeroDocumento.entrada from entrada "
                + "inner join persona "
                + "on  persona.identificacion=numeroDocumento.entrada"+ filtro + orden;
        return ConectorBD.consultar(cadenaSQL);
    }

        public static List<Entrada> getListaEnObjetos(String filtro, String orden){
        List<Entrada> lista= new ArrayList<>();
        ResultSet datos = Entrada.getLista(filtro, orden);
        if(datos!=null){
            try {
                while(datos.next()){
                Entrada entrada = new Entrada();
                entrada.setId(datos.getString("id"));
                entrada.setFecha(datos.getString("fecha"));
                entrada.setNumeroDocumento(datos.getString("numeroDocumento"));
                lista.add(entrada);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
}
