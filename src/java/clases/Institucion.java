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
public class Institucion {
    private String id;
    private String nombreSede;
    private String cupo;
    private String idRuta; // foranea de ruta (id)
    private String idMunicipio; // foranea de municipio (id)
    private String identificacionEncargado; //nombre del encargado de la isntitucion  // foranea de persona (identificacion)
    private String orden;
    private String direccion;
    private String direccionComplementaria;
    private String telefono;
    
    public Institucion(){
        
    }
    
    public Institucion(String id) {
        String cadenaSQL="select nombreSede, cupo,idRuta,idMunicipio, identificacionEncargado,orden, direccion, direccionComplementaria, telefono from institucion where id="+id;
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.id=id;
                nombreSede=resultado.getString("nombreSede");
                cupo=resultado.getString("cupo");
                idRuta=resultado.getString("idRuta");
                idMunicipio=resultado.getString("idMunicipio");
                identificacionEncargado=resultado.getString("identificacionEncargado");
                orden=resultado.getString("orden");
                direccion=resultado.getString("direccion");
                direccionComplementaria=resultado.getString("direccionComplementaria");
                telefono=resultado.getString("telefono");
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

    public String getNombreSede() {
        String resultado=nombreSede;
        if(nombreSede==null) resultado="";
        return resultado;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getCupo() {
        String resultado=cupo;
        if(cupo==null) resultado="";
        return resultado;
    }

    public void setCupo(String cupo) {
        this.cupo = cupo;
    }

    public String getIdRuta() {
        String resultado=idRuta;
        if(idRuta==null) resultado="";
        return resultado;
    }

    public void setIdRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public String getIdMunicipio() {
        String resultado=idMunicipio;
        if(idMunicipio==null) resultado="";
        return resultado;
    }

    public void setIdMunicipio(String idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getIdentificacionEncargado() {
        String resultado=identificacionEncargado;
        if(identificacionEncargado==null) resultado="";
        return resultado;
    }

    public void setIdentificacionEncargado(String identificacionEncargado) {
        this.identificacionEncargado = identificacionEncargado;
    }

    public String getOrden() {
        String resultado=orden;
        if(orden==null) resultado="";
        return resultado;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getDireccion() {
        String resultado=direccion;
        if(direccion==null) resultado="";
        return resultado;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccionComplementaria() {
        String resultado=direccionComplementaria;
        if(direccionComplementaria==null) resultado="";
        return resultado;
    }

    public void setDireccionComplementaria(String direccionComplementaria) {
        this.direccionComplementaria = direccionComplementaria;
    }

    public String getTelefono() {
        String resultado=telefono;
        if(telefono==null) resultado="";
        return resultado;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    //llave foranea de la clase ruta 
    public Ruta getRuta(){
        return new Ruta(idRuta);
    }
    // llave foranea de la clase municipio 
    public Municipio getMunicipio(){
        return new Municipio(idMunicipio);
    }
    //llave foranea de la clase persona 
    public Persona getPersona(){
        return new Persona(identificacionEncargado);
    }
    
    public boolean grabar(){
        String cadenaSQL="insert into institucion (nombreSede, cupo, idRuta, idMunicipio, identificacionEncargado, orden,direccion,direccionComplementaria,telefono)"
                + "values ('"+nombreSede+"','"+cupo+"','"+idRuta+"','"+idMunicipio+"','"+identificacionEncargado+"', '"+orden+"','"+direccion+"','"+direccionComplementaria+"','"+telefono+"')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(){
        String cadenaSQL = "update institucion set nombreSede='"+nombreSede+"', cupo='"+cupo+"', idRuta='"+idRuta+"', idMunicipio='"+idMunicipio+"', identificacionEncargado='"+identificacionEncargado+"', orden='"+orden+"', direccion='"+direccion+"', direccionComplementaria='"+direccionComplementaria+"', telefono='"+telefono+"' where id= "+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar(){
        String cadenaSQL="delete from institucion where id="+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista(String filtro, String orden){
        if(filtro!=null && filtro !="") filtro= " where " + filtro;
        else filtro=" ";
        if(orden!=null && orden!="") orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL= "select id, nombreSede, cupo,idRuta,idMunicipio, identificacionEncargado,orden, direccion, direccionComplementaria, telefono from institucion "
         + " inner join ruta" 
         + " on institucion.idRuta=id.ruta "
         + " inner join municipio"
         + " on idMunicipio.institucion=id.municipio"
         + " inner join persona" 
         + " on identificacionEncargado.institucion=identificacion.persona" +filtro + orden;
        return ConectorBD.consultar(cadenaSQL);
    }

        public static List<Institucion> getListaEnObjetos(String filtro, String orden){
        List<Institucion> lista= new ArrayList<>();
        ResultSet datos = Institucion.getLista(filtro, orden);
        if(datos!=null){
            try {
                while(datos.next()){
                Institucion institucion = new Institucion();
                institucion.setId(datos.getString("id"));
                institucion.setNombreSede(datos.getString("nombreSede"));
                institucion.setCupo(datos.getString("cupo"));
                institucion.setIdRuta(datos.getString("idRuta"));
                institucion.setIdMunicipio(datos.getString("idMunicipio"));
                institucion.setIdentificacionEncargado(datos.getString("identificacionEncargado"));
                institucion.setOrden(datos.getString("orden"));
                institucion.setDireccion(datos.getString("direccion"));
                institucion.setDireccionComplementaria(datos.getString("direccionComplementaria"));
                institucion.setTelefono(datos.getString("telefono"));
                lista.add(institucion);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Institucion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
}
