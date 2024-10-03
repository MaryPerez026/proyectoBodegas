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
public class Vehiculo {
    private String placaVehiculo;
    private String modelo;
    private String marca;
    private String capacidadDeCarga;
    private String descripcion;
    
    public Vehiculo(){
        
    }
    
    public Vehiculo(String placaVehiculo) {
        String cadenaSQL="select modelo, marca, capacidadDeCarga, descripcion from vehiculo where placaVehiculo='" + placaVehiculo;
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.placaVehiculo=placaVehiculo;
                modelo=resultado.getString("modelo");
                marca=resultado.getString("marca");
                capacidadDeCarga=resultado.getString("capacidadDeCarga");
                descripcion=resultado.getString("descripcion"); // Agrege descripción
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar el id "+ ex.getMessage());
        }
    }

    public String getPlacaVehiculo() {
        String resultado=placaVehiculo;
        if(placaVehiculo==null) resultado="";
        return resultado;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getModelo() {
        String resultado=modelo;
        if(modelo==null) resultado="";
        return resultado;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        String resultado=marca;
        if(marca==null) resultado="";
        return resultado;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCapacidadDeCarga() {
        String resultado=capacidadDeCarga;
        if(capacidadDeCarga==null) resultado="";
        return resultado;
    }

    public void setCapacidadDeCarga(String capacidadDeCarga) {
        this.capacidadDeCarga = capacidadDeCarga;
    }

    public String getDescripcion() {
        String resultado=descripcion;
        if(descripcion==null) resultado="";
        return resultado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean grabar(){
        String cadenaSQL="insert into vehiculo (placaVehiculo, modelo, marca, capacidadDeCarga, descripcion) "
                + "values ('"+placaVehiculo+"','"+modelo+"','"+marca+"','"+capacidadDeCarga+"','"+descripcion+"')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(String placaAnterior){
        String cadenaSQL = "update vehiculo set placaVehiculo='"+placaVehiculo+"', modelo='"+modelo+"', marca='"+marca+"', capacidadDeCarga='"+capacidadDeCarga+"', descripcion='"+descripcion+"'"
                + " where placaVehiculo='"+ placaAnterior; 
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }
    
    public boolean eliminar() {
    String cadenaSQL = "DELETE FROM vehiculo WHERE placaVehiculo='" + placaVehiculo + "'";
    System.out.println("Consulta de eliminación: " + cadenaSQL);
    boolean resultado = ConectorBD.ejecutarQuery(cadenaSQL);
    
    if (!resultado) {
        System.out.println("Error: No se pudo eliminar el vehículo con placa " + placaVehiculo);
    } else {
        System.out.println("Vehículo con placa " + placaVehiculo + " eliminado correctamente.");
    }//agregue
    
    return resultado;
}


    public static ResultSet getLista(String filtro, String orden){
        if(filtro != null && !filtro.isEmpty()) filtro= " where " + filtro;
        else filtro=" ";
        if(orden != null && !orden.isEmpty()) orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL= "select placaVehiculo, modelo, marca, capacidadDeCarga, descripcion "+
                           "from vehiculo " + filtro + orden;
        return ConectorBD.consultar(cadenaSQL);
    }

    public static List<Vehiculo> getListaEnObjetos(String filtro, String orden){
        List<Vehiculo> lista = new ArrayList<>();
        ResultSet datos = Vehiculo.getLista(filtro, orden);
        if(datos != null){
            try {
                while(datos.next()){
                    Vehiculo vehiculo = new Vehiculo();
                    vehiculo.setPlacaVehiculo(datos.getString("placaVehiculo"));
                    vehiculo.setModelo(datos.getString("modelo"));
                    vehiculo.setMarca(datos.getString("marca"));
                    vehiculo.setCapacidadDeCarga(datos.getString("capacidadDeCarga"));
                    vehiculo.setDescripcion(datos.getString("descripcion"));
                    lista.add(vehiculo);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
}

