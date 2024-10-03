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
public class Despacho {
    private String id;
    private String fechaSalida;
    private String cantidadCanastillas; // canastillas que alista el despachador
    private String idPlanDeRuta;  //foranea de planRuta (id)
    private String observacion;
    private String identificacionDespachador; // foranea de persona (identificacion)
    
    public Despacho(){
        
    }
    
    public Despacho(String id) {
        String cadenaSQL="select fechaSalida, cantidadCanastillas,idPlanRuta,observacion,identificacionDespachador "
                + "from despacho where id="+id;
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.id=id;
                fechaSalida=resultado.getString("fechaSalida");
                cantidadCanastillas=resultado.getString("cantidadCanastillas");
                idPlanDeRuta=resultado.getString("idPlanRuta");
                observacion=resultado.getString("observacion");
                identificacionDespachador=resultado.getString("identificacionDespachador");
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

    public String getFechaSalida() {
        String resultado=fechaSalida;
        if(fechaSalida==null) resultado="";
        return resultado;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getCantidadCanastillas() {
        String resultado=cantidadCanastillas;
        if(cantidadCanastillas==null) resultado="";
        return resultado;
    }

    public void setCantidadCanastillas(String cantidadCanastillas) {
        this.cantidadCanastillas = cantidadCanastillas;
    }

    public String getIdPlanDeRuta() {
        String resultado=idPlanDeRuta;
        if(idPlanDeRuta==null) resultado="";
        return resultado;
    }

    public void setIdPlanDeRuta(String idPlanDeRuta) {
        this.idPlanDeRuta = idPlanDeRuta;
    }

    public String getObservacion() {
        String resultado=observacion;
        if(observacion==null) resultado="";
        return resultado;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getIdentificacionDespachador() {
        String resultado=identificacionDespachador;
        if(identificacionDespachador==null) resultado="";
        return resultado;
    }

    public void setIdentificacionDespachador(String identificacionDespachador) {
        this.identificacionDespachador = identificacionDespachador;
    }
    
    //llave foranea de la clase PlanRuta
    public PlanRuta getPlanRuta(){
        return new PlanRuta(idPlanDeRuta);
    }
    // llave foranea de la clase Persona
    public Persona getPersona() {
        return new Persona(identificacionDespachador);
    }
    
    public boolean grabar(){
        String cadenaSQL="insert into despacho (fechaSalida, cantidadCanastillas, idPlanRuta, observacion,identificacionDespachador ) "
                + "values ('"+fechaSalida+"','"+cantidadCanastillas+"','"+idPlanDeRuta+"','"+observacion+"','"+identificacionDespachador+"')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(){
        String cadenaSQL = "update despacho set fechaSalida='"+fechaSalida+"', cantidadCanastillas='"+cantidadCanastillas+"', idPlanRuta='"+idPlanDeRuta+"', observacion='"+observacion+"', identificacionDespachador='"+identificacionDespachador+"' "
                + "where id= "+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar(){
        String cadenaSQL="delete from  despacho where id="+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista(String filtro, String orden){
        if(filtro!=null && filtro !="") filtro= " where " + filtro;
        else filtro=" ";
        if(orden!=null && orden!="") orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL= "select id,fechaSalida, cantidadCanastillas,idPlanRuta,observacion,identificacionDespachador "
                           + "from despacho " 
                           + " inner join planRuta on id.planRuta=idPlanRuta.despacho "
                           + " inner join persona "
                           + " on identificacionDespachador.despacho=identificacion.persona"+filtro + orden;
        return ConectorBD.consultar(cadenaSQL);
    }

        public static List<Despacho> getListaEnObjetos(String filtro, String orden){
        List<Despacho> lista= new ArrayList<>();
        ResultSet datos = Despacho.getLista(filtro, orden);
        if(datos!=null){
            try {
                while(datos.next()){
                Despacho despacho = new Despacho();
                despacho.setId(datos.getString("id"));
                despacho.setFechaSalida(datos.getString("fechaSalida"));
                despacho.setCantidadCanastillas(datos.getString("cantidadCanastillas"));
                despacho.setIdPlanDeRuta(datos.getString("idPlanRuta"));
                despacho.setObservacion(datos.getString("observacion"));
                despacho.setIdentificacionDespachador(datos.getString("identificacionDespachador"));
                lista.add(despacho);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Despacho.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
}
