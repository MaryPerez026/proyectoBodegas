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
public class PlanRuta {
    private String id;
    private String canastillas;
    private String fecha;
    private String idRuta; // foranea de ruta (id)
    private String idPlacaVehiculo; // foranea de vehiculo (placaVehiculo)
    private String identificacionConductor; // conductor empleado de la bodega // foranea de persona (dientificacion)
    
    public PlanRuta(){
        
    }
    
    public PlanRuta(String id) {
        String cadenaSQL="select canastillas, fecha,idRuta,idPlacaVehiculo,identificacionConductor"+
                          "from planRuta where id="+id;
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.id=id;
                canastillas=resultado.getString("canastillas");
                fecha=resultado.getString("fecha");
                idRuta=resultado.getString("idRuta");
                idPlacaVehiculo=resultado.getString("idPlacaVehiculo");
                identificacionConductor=resultado.getString("identificacionConductor");
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

    public String getCanastillas() {
        String resultado=canastillas;
        if(canastillas==null) resultado="";
        return resultado;
    }

    public void setCanastillas(String canastillas) {
        this.canastillas = canastillas;
    }

    public String getFecha() {
        String resultado=fecha;
        if(fecha==null) resultado="";
        return resultado;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdRuta() {
         String resultado=idRuta;
        if(idRuta==null) resultado="";
        return resultado;
    }

    public void setIdRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public String getIdPlacaVehiculo() {
        String resultado=idPlacaVehiculo;
        if(idPlacaVehiculo==null) resultado="";
        return resultado;
    }

    public void setIdPlacaVehiculo(String idPlacaVehiculo) {
        this.idPlacaVehiculo = idPlacaVehiculo;
    }

    public String getIdentificacionConductor() {
        String resultado=identificacionConductor;
        if(identificacionConductor==null) resultado="";
        return resultado;
    }

    public void setIdentificacionConductor(String identificacionConductor) {
        this.identificacionConductor = identificacionConductor;
    }
   
    // llave foranea de la clase ruta 
    public Ruta getRuta(){
        return new Ruta(idRuta);
    }
    //llave foranea de la clase vehiculo 
    public Vehiculo getVehiculo(){
        return new Vehiculo(idPlacaVehiculo);
    }
    //llave foranea de la clase persona
    public Persona getPersona(){
        return new Persona(identificacionConductor);
    }
   public boolean grabar(){
        String cadenaSQL="insert into planRuta (canastillas, fecha, idRuta, idPlacaVehiculo,identificacionConductor ) "
                + "values ('"+canastillas+"','"+fecha+"','"+idRuta+"','"+idPlacaVehiculo+"','"+identificacionConductor+"')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(){
        String cadenaSQL = "update planRuta set canastillas='"+canastillas+"', fecha='"+fecha+"', idRuta='"+idRuta+"', idPlacaVehiculo='"+idPlacaVehiculo+"', identificacionConductor='"+identificacionConductor+"' "
                + "where id= "+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar(){
        String cadenaSQL="delete from  planRuta where id="+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista(String filtro, String orden){
        if(filtro!=null && filtro !="") filtro= " where " + filtro;
        else filtro=" ";
        if(orden!=null && orden!="") orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL= "select id,canastillas, fecha,idRuta,idPlacaVehiculo,identificacionConductor from planRuta" 
                           + " inner join ruta on id.ruta=idRuta.planRuta "
                           + " inner join vehiculo on placaVehiculo.vehiculo=idPlacaVehiculo=planRuta"
                           + " inner join persona "
                           + " on identificacionConductor.planRuta=identificacion.persona"+filtro + orden;
        return ConectorBD.consultar(cadenaSQL);
    }

        public static List<PlanRuta> getListaEnObjetos(String filtro, String orden){
        List<PlanRuta> lista= new ArrayList<>();
        ResultSet datos = PlanRuta.getLista(filtro, orden);
        if(datos!=null){
            try {
                while(datos.next()){
                PlanRuta rutaPlan = new PlanRuta();
                rutaPlan.setId(datos.getString("id"));
                rutaPlan.setCanastillas(datos.getString("canastillas"));
                rutaPlan.setFecha(datos.getString("fecha"));
                rutaPlan.setIdRuta(datos.getString("idRuta"));
                rutaPlan.setIdPlacaVehiculo(datos.getString("idPlacaVehiculo"));
                rutaPlan.setIdentificacionConductor(datos.getString("identificacionConductor"));
                lista.add(rutaPlan);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PlanRuta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
}
