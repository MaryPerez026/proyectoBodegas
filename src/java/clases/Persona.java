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
public class Persona {
    private String identificacion;
    private String tipoDocumento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String celular;          //falta acomodar los getter y setter con la validacion de nulidad
    private String email;
    private String direccion;
    private String genero;
    private String rol;
    private String clave;
    private String codigoRecuperacion;
    private String fechaExpedicion;
    private String fechaNacimiento;
    private String foto;
    
    public Persona(){
        
    }
    
    public Persona(String identificacion) {
        String cadenaSQL="select tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido,     celular, email, direccion, genero, rol, clave,codigoRecuperacion, fechaExpedicion,fechaNacimiento, foto from Persona where identificacion="+identificacion;
        ResultSet resultado= ConectorBD.consultar(cadenaSQL);
        try {
            if(resultado.next()){
                this.identificacion=identificacion;
                tipoDocumento=resultado.getString("tipoDocumento");
                primerNombre=resultado.getString("primerNombre");
                segundoNombre=resultado.getString("segundoNombre");
                primerApellido=resultado.getString("primerApellido");
                segundoApellido=resultado.getString("segundoApellido");
                celular=resultado.getString("celular");
                email=resultado.getString("email");
                direccion=resultado.getString("direccion");
                genero=resultado.getString("genero");
                rol=resultado.getString("rol");
                clave=resultado.getString("clave");
                codigoRecuperacion=resultado.getString("codigoRecuperacion");
                fechaExpedicion=resultado.getString("fechaExpedicion");
                fechaNacimiento=resultado.getString("fechaNacimiento");
                foto=resultado.getString("foto");
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la identificacion"+ex.getMessage());
        }
    }

    public String getIdentificacion() {
        String resultado=identificacion;
        if(identificacion==null) resultado="";
        return resultado;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /*public String getTipoDocumento() {
        String resultado=tipoDocumento;
        if(tipoDocumento==null) resultado="";
        return resultado;
    }*/
    
    public TipoDocumento getTipoDocumentoEnObjeto(){
        return new TipoDocumento(tipoDocumento);
    }
    
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    public String getPrimerNombre() {
        String resultado=primerNombre;
        if(primerNombre==null) resultado="";
        return resultado;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        String resultado=segundoNombre;
        if(segundoApellido==null) resultado="";
        return resultado;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        String resultado=primerApellido;
        if(primerApellido==null) resultado="";
        return resultado;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        String resultado=segundoApellido;
        if(segundoApellido==null) resultado="";
        return resultado;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCelular() {
        String resultado=celular;
        if(celular==null) resultado="";
        return resultado;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        String resultado=email;
        if(email==null) resultado="";
        return resultado;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        String resultado=direccion;
        if(direccion==null) resultado="";
        return resultado;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /*public String getGenero() {
        String resultado=genero;
        if(genero==null) resultado="";
        return resultado;
    }*/

    public void setGenero(String genero) {
        this.genero = genero;
    }

    /*public String getRol() {
        String resultado=rol;
        if(rol==null) resultado="";
        return resultado;
    }*/
    
    public RolPersona getRolEnObjeto(){
        return new RolPersona(rol);
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getClave() {
        String resultado=clave;
        if(clave==null) resultado="";
        return resultado;
    }

    /*public void setClave(String clave) {
        this.clave = clave;
    }*/
    
    public void setClave(String clave) {
        if (clave==null || clave.trim().length()==0) clave=identificacion;
        if (clave.length()<32) {
            this.clave="md5 ('"+clave+"')";
        } else {
            this.clave=" '"+clave+"' ";
        }
    }

    public String getCodigoRecuperacion() {
        String resultado=codigoRecuperacion;
        if(codigoRecuperacion==null) resultado="";
        return resultado;
    }

    public void setCodigoRecuperacion(String codigoRecuperacion) {
        this.codigoRecuperacion = codigoRecuperacion;
    }

    public String getFechaExpedicion() {
        String resultado=fechaExpedicion;
        if(fechaExpedicion==null) resultado="";
        return resultado;
    }

    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getFechaNacimiento() {
        String resultado=fechaNacimiento;
        if(fechaNacimiento==null) resultado="";
        return resultado;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFoto() {
        String resultado=foto;
        if(foto==null) resultado="";
        return resultado;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
   
    @Override
    public String toString(){
        String datos="";
        if (identificacion!=null){
            datos=identificacion+" - "+primerNombre+" - "+ primerApellido;
        }
        return datos;
    }
    
    public boolean grabar() {
    String cadenaSQL = "INSERT INTO Persona(identificacion, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido, celular, email, direccion, genero, rol, clave, fechaExpedicion, fechaNacimiento, foto) " +
            "VALUES ('" + identificacion + "', '" + tipoDocumento + "', '" + primerNombre + "', '" + segundoNombre + "', '" + primerApellido + "', '" + segundoApellido + "', '" + celular + "', '" + email + "', '" + direccion + "', '" + genero + "', " + (rol == null ? "NULL" : "'" + rol + "'") + ", " + clave + ", '" + fechaExpedicion + "', '" + fechaNacimiento + "', " + (foto == null ? "NULL" : "'" + foto + "'") + ")";
    
    System.out.println("Consulta SQL para insertar: " + cadenaSQL); // Imprimir la consulta
    
    return ConectorBD.ejecutarQuery(cadenaSQL);
}


        public boolean modificar(String identificacionAnterior){
        String cadenaSQL = "update Persona set identificacion='"+identificacion+"' ,tipoDocumento='"+tipoDocumento+"',  primerNombre='"+primerNombre+"', segundoNombre='"+segundoNombre+"', primerApellido='"+primerApellido+"', segundoApellido='"+segundoApellido+"', "
                + " celular='"+celular+"',email='"+email+"', direccion='"+direccion+"', genero='"+genero+"', rol='"+rol+"', clave="+clave+",  fechaExpedicion='"+fechaExpedicion+"', fechaNacimiento='"+fechaNacimiento+"', foto='"+foto+"' where identificacion=" + identificacionAnterior;
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar(){
        String cadenaSQL ="delete from Persona where identificacion="+identificacion;
        System.out.println("cadenaSQL "+cadenaSQL);
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista(String filtro, String orden){
        if(filtro!=null && filtro !="") filtro= " where " + filtro;
        else filtro=" ";
        if(orden!=null && orden!="") orden=" order by  "+ orden;
        else orden=" ";
        String cadenaSQL="select identificacion, tipoDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido,     celular, email, direccion, genero, rol, clave, fechaExpedicion,fechaNacimiento, foto from Persona a "+ filtro + orden;
          //System.out.println(cadenaSQL); prueba para revisar como esta iniciando en sesion 
        return ConectorBD.consultar(cadenaSQL);
    }

    public static List<Persona> getListaEnObjetos(String filtro, String orden){
        List<Persona> lista= new ArrayList<>();
        ResultSet datos = Persona.getLista(filtro, orden);
        if(datos!=null){
            try {
                while(datos.next()){
                Persona persona = new Persona();
                persona.setIdentificacion(datos.getString("identificacion"));
                persona.setTipoDocumento(datos.getString("tipoDocumento"));
                persona.setPrimerNombre(datos.getString("primerNombre"));
                persona.setSegundoNombre(datos.getString("segundoNombre"));
                persona.setPrimerApellido(datos.getString("primerApellido"));
                persona.setSegundoNombre(datos.getString("segundoNombre"));
                persona.setCelular(datos.getString("celular"));
                persona.setEmail(datos.getString("email"));
                persona.setDireccion(datos.getString("direccion"));
                persona.setGenero(datos.getString("genero"));
                persona.setRol(datos.getString("rol"));
                persona.setClave(datos.getString("clave"));
                //persona.setCodigoRecuperacion(datos.getString("codigoRecuperacion"));
                persona.setFechaExpedicion(datos.getString("fechaExpedicion"));
                persona.setFechaNacimiento(datos.getString("fechaNacimiento"));
                persona.setFoto(datos.getString("foto"));
                lista.add(persona);
                }
             } catch (SQLException ex) {
                Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
    public GeneroPersona getGeneroPersona(){
        return new GeneroPersona(genero);//cambie
    }
    
    public static Persona validar(String identificacion, String clave){
        Persona persona= null;
        List<Persona>  lista= Persona.getListaEnObjetos(" identificacion='"+identificacion+
                "' and clave=md5('"+clave+"')", null);
        if(lista.size()>0){
            persona=lista.get(0); // get devuelve el primer elemento de la lista
        }
       return persona;
    }
}
