
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
public class UnidadDeMedida {
    private String id;
    private String formato;
    private String nomenclatura;
    private String idUnidadPadre; // foranea de unidad de medida (id)
    private String factorDeConversion;
    
    public UnidadDeMedida(){
        
    }
    
    public UnidadDeMedida(String id) {
        String cadenaSQL="select formato, nomenclatura, idUnidadPadre, factorDeConversion from unidadDeMedida where id="+id;
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.id=id;
                formato=resultado.getString("formato");
                nomenclatura=resultado.getString("nomenclatura");
                idUnidadPadre=resultado.getString("idUnidadPadre");
                factorDeConversion=resultado.getString("factorDeConversion");

            }
        } catch (SQLException ex) {
            //Logger.getLogger(unidadDeMedida.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getFormato() {
        String resultado=formato;
        if(formato==null) resultado="";
        return resultado;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getNomenclatura() {
        String resultado=nomenclatura;
        if(nomenclatura==null) resultado="";
        return resultado;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public String getIdUnidadPadre() {
        String resultado=idUnidadPadre;
        if(idUnidadPadre==null) resultado="";
        return resultado;
    }

    public void setIdUnidadPadre(String idUnidadPadre) {
        this.idUnidadPadre = idUnidadPadre;
    }

    public String getFactorDeConversion() {
        String resultado=factorDeConversion;
        if(factorDeConversion==null) resultado="";
        return resultado;
    }

    public void setFactorDeConversion(String factorConversion) {
        this.factorDeConversion = factorConversion;
    }
    
    // llave foranea de la clase unidad de medida
    public UnidadDeMedida getUnidadDeMedida(){
        return new UnidadDeMedida(idUnidadPadre);
    }
    
    public boolean grabar(){
        String cadenaSQL="insert into unidadDeMedida (formato, nomenclatura, idUnidadPadre, factorDeConversion)"
                + "values ('"+formato+"', '"+nomenclatura+"', '"+idUnidadPadre+"', '"+factorDeConversion+"')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(){
        String cadenaSQL = "update unidadDeMedida set formato='"+formato+"', nomenclatura='"+nomenclatura+"', idUnidadPadre='"+idUnidadPadre+"', factorDeConversion='"+factorDeConversion+"' where id= "+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar(){
        String cadenaSQL="delete from unidadDeMedida where id="+id;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista(String filtro, String orden){
        if(filtro!=null && filtro !="") filtro= " where " + filtro;
        else filtro=" ";
        if(orden!=null && orden!="") orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL= "select id, formato, nomenclatura, idUnidadPadre, factorDeConversion from unidadDeMedida "
                + "inner join unidadDeMedida "
                + "on  unidadDeMedida.idUnidadPadre=id.unidadDeMedida"+ filtro + orden;
        return ConectorBD.consultar(cadenaSQL);
    }

        public static List<UnidadDeMedida> getListaEnObjetos(String filtro, String orden){
        List<UnidadDeMedida> lista= new ArrayList<>();
        ResultSet datos = UnidadDeMedida.getLista(filtro, orden);
        if(datos!=null){
            try {
                while(datos.next()){
                UnidadDeMedida medida = new UnidadDeMedida();
                medida.setId(datos.getString("id"));
                medida.setFormato(datos.getString("formato"));
                medida.setNomenclatura(datos.getString("nomenclatura"));
                medida.setIdUnidadPadre(datos.getString("idUnidadPadre"));
                lista.add(medida);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UnidadDeMedida.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
    
}
