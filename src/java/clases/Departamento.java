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
public class Departamento {
    private String id;
    private String nombre;
    
    public Departamento(){
        
    }
    
     public Departamento(String id) {
        String cadenaSQL="select nombre from departamento where id="+id;
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.id=id;
                nombre=resultado.getString("nombre");
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
    
    public boolean grabar(){
        String cadenaSQL="insert into departamento (nombre)"
                + "values ('"+nombre+"')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(){
        String cadenaSQL = "update departamento set nombre='"+nombre+"' where id= "+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar(){
        String cadenaSQL="delete from departamento where id="+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista(String filtro, String orden){
        if(filtro!=null && filtro !="") filtro= " where " + filtro;
        else filtro=" ";
        if(orden!=null && orden!="") orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL= "select id, nombre from departamento " + filtro + orden;
        return ConectorBD.consultar(cadenaSQL);
    }

        public static List<Departamento> getListaEnObjetos(String filtro, String orden){
        List<Departamento> lista= new ArrayList<>();
        ResultSet datos = Departamento.getLista(filtro, orden);
        if(datos!=null){
            try {
                while(datos.next()){
                Departamento departamento = new Departamento();
                departamento.setId(datos.getString("id"));
                departamento.setNombre(datos.getString("nombre"));
                lista.add(departamento);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
}
