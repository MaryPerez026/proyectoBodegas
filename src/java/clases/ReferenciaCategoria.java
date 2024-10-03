/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Mary
 */
public class ReferenciaCategoria {
    
    private String codigo;

    public ReferenciaCategoria(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getOpcion(){
        String opcion=null;
        switch(codigo){
            case "P": opcion="Perecedero"; break;
            case "N": opcion="No perecedero"; break;
            default: opcion="No Especificado"; break;
        }
        return opcion;
    }

    @Override
    public String toString() {
        return getOpcion();
    }
    
    public String getRadioButtons(){
    String lista="";
    if(codigo==null) codigo="";
    switch(codigo){
        case "P":
            lista="<input type='radio' name='referencia' value='P' checked>Perecedero"
                    + "<input type='radio' name='referencia' value='N'>No perecedeo";
            break;
        case "N":
            lista="<input type='radio' name='referencia' value='P' >Perecedero"
                    + "<input type='radio' name='referencia' value='N' checked>No perecedeo";
             break;
        default:
            lista="<input type='radio' name='referencia' value='P' checked>Perecedero"
                    + "<input type='radio' name='referencia' value='N'>No perecedeo";
            break;
    }
    return lista;
    }
}
