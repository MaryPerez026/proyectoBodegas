/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author sena
 */
public class TipoDocumento {
    
    private String codigo;

    public TipoDocumento(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
               return codigo;

    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre(){
        String nombre=null;
        switch(codigo){
            case "C": nombre="Cedula Ciudadania"; break;
            case "E": nombre="Cedula Extranjeria"; break;
            case "P": nombre="Pasaporte"; break;
            default: nombre="Desconocido"; break;
        }
        return nombre;
    }

    @Override
    public String toString() {
        return getNombre();
    }
    
    
    
    public String getListaEnOptions() {
     String lista="";
        if(codigo==null) codigo="";
        switch(codigo){
        case "C":
            
            lista = "<input type='radio' name='tipoDocumento' value='C' checked>Cédula de Ciudadanía"
                  + "<input type='radio' name='tipoDocumento' value='E'>Cédula de Extranjería"
                  + "<input type='radio' name='tipoDocumento' value='P'>Pasaporte";
            break;
        case "E":
            lista = "<input type='radio' name='tipoDocumento' value='C'>Cédula de Ciudadanía"
                  + "<input type='radio' name='tipoDocumento' value='E' checked>Cédula de Extranjería"
                  + "<input type='radio' name='tipoDocumento' value='P'>Pasaporte";
            break;
        case "P":
            lista = "<input type='radio' name='tipoDocumento' value='C'>Cédula de Ciudadanía"
                  + "<input type='radio' name='tipoDocumento' value='E'>Cédula de Extranjería"
                  + "<input type='radio' name='tipoDocumento' value='P' checked>Pasaporte";
            break;
        default:
            lista = "<input type='radio' name='tipoDocumento' value='C'>Cédula de Ciudadanía"
                  + "<input type='radio' name='tipoDocumento' value='E'>Cédula de Extranjería"
                  + "<input type='radio' name='tipoDocumento' value='P'>Pasaporte";
            break;
    }
    
    return lista;
}
}