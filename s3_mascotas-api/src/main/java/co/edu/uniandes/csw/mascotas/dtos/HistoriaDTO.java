/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;
import co.edu.uniandes.csw.mascotas.entities.HistoriaEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
/**
 *
 * @author Camilo Pinilla
 */
public class HistoriaDTO implements Serializable{
    private long id;
    private String foto;
    private String texto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public HistoriaDTO() {
        
    }

    public HistoriaDTO(HistoriaEntity Historia){
        if(Historia!=null){
            this.id= Historia.getId();
            this.foto = Historia.getFoto();
            this.texto = Historia.getTexto();
        }
    }
    
    public HistoriaEntity toEntity(){
        HistoriaEntity nueva = new HistoriaEntity();
        
        nueva.setId(this.id);
        nueva.setFoto(this.foto);
        nueva.setTexto(this.texto);
        return nueva;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
