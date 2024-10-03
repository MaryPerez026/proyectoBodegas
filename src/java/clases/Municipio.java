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
public class Municipio {
    private String id;
    private String nombre;
    private String cardinalidadGeografica;
    private String idDepartamento; //es foranea de departamento (id)
    
    public Municipio(){
        
    }
    
    public Municipio(String id) {
        String cadenaSQL="select nombre, idDepartamento,cardinalidadGeografica from municipio where id="+id;
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.id=id;
                nombre=resultado.getString("nombre");
                idDepartamento=resultado.getString("idDepartamento");
                cardinalidadGeografica=resultado.getString("cardinalidadGeografica");
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

    public String getCardinalidadGeografica() {
        String resultado=cardinalidadGeografica;
        if(cardinalidadGeografica==null) resultado="";
        return resultado;
    }

    public void setCardinalidadGeografica(String cardinalidadGeografica) {
        this.cardinalidadGeografica = cardinalidadGeografica;
    }

    public String getIdDepartamento() {
        String resultado=idDepartamento;
        if(idDepartamento==null) resultado="";
        return resultado;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
    //llave foranea de la clase departamento 
    public Departamento getDepartamento(){
        return new Departamento(idDepartamento);
    }
    
    
    public boolean grabar(){
        String cadenaSQL="insert into municipio (nombre, idDepartamento, cardinalidadGeografica)"
                + "values ('"+nombre+"')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(){
        String cadenaSQL = "update municipio set nombre='"+nombre+"', idDepartamento='"+idDepartamento+"', cardinalidadGeografica='"+cardinalidadGeografica+"' where id= "+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar(){
        String cadenaSQL="delete from municipio where id="+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista(String filtro, String orden){
        if(filtro!=null && filtro !="") filtro= " where " + filtro;
        else filtro=" ";
        if(orden!=null && orden!="") orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL= "select id, nombre, idDepartamento,cardinalidadGeografica from municipio "
         + " inner join departamento" 
         + " on idDepartamento.municipio=id.departamento" + filtro + orden;
        return ConectorBD.consultar(cadenaSQL);
    }

        public static List<Municipio> getListaEnObjetos(String filtro, String orden){
        List<Municipio> lista= new ArrayList<>();
        ResultSet datos = Municipio.getLista(filtro, orden);
        if(datos!=null){
            try {
                while(datos.next()){
                Municipio municipio = new Municipio();
                municipio.setId(datos.getString("id"));
                municipio.setNombre(datos.getString("nombre"));
                municipio.setIdDepartamento(datos.getString("idDepartamento"));
                municipio.setCardinalidadGeografica(datos.getString("cardinalidadGeografica"));
                lista.add(municipio);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Municipio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
}
