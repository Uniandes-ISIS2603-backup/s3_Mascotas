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
    private Long id;
    private String foto;
    private String texto;
    
    private MascotaDTO mascota;

    public MascotaDTO getMascota() {
        return mascota;
    }

    public void setMascota(MascotaDTO mascota) {
        this.mascota = mascota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public HistoriaDTO(HistoriaEntity historia){
        if(historia!=null){
            this.id= historia.getId();
            this.foto = historia.getFoto();
            this.texto = historia.getTexto();
            if(historia.getMascota() != null)
                this.mascota = new MascotaDTO(historia.getMascota());
        }
    }
    
    public HistoriaEntity toEntity(){
        HistoriaEntity nueva = new HistoriaEntity();
        nueva.setId(this.id);
        nueva.setFoto(this.foto);
        nueva.setTexto(this.texto);
        if(this.mascota != null)
            nueva.setMascota(this.mascota.toEntity());
        return nueva;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
