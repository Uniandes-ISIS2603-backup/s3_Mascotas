/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;
import co.edu.uniandes.csw.mascotas.entities.AdopcionEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
/**
 *
 * @author Juan Sebastián Gómez
 */
public class AdopcionDTO implements Serializable{
    private String docsAdopcion;
    private Long id;
    private CalificacionDTO relacionCalificacion;
    private ClienteDetailDTO relacionClienteDetailDTO;

    public ClienteDetailDTO getRelacionClienteDetailDTO() {
        return relacionClienteDetailDTO;
    }

    public void setRelacionClienteDetailDTO(ClienteDetailDTO relacionClienteDetailDTO) {
        this.relacionClienteDetailDTO = relacionClienteDetailDTO;
    }
    public CalificacionDTO getRelacionCalificacion() {
        return relacionCalificacion;
    }

    public void setRelacionCalificacion(CalificacionDTO relacionCalificacion) {
        this.relacionCalificacion = relacionCalificacion;
    }
    public String getDocsAdopcion() {
        return docsAdopcion;
    }

    public void setDocsAdopcion(String docsAdopcion) {
        this.docsAdopcion = docsAdopcion;
    }

    public AdopcionDTO() {
        
    }
    public AdopcionDTO(AdopcionEntity adopcion){
        if(adopcion!=null){
            this.id= adopcion.getId();
            this.docsAdopcion = adopcion.getDocsAdopcion();
            this.relacionCalificacion = new CalificacionDTO(adopcion.getCalificacion());
            this.relacionClienteDetailDTO = new ClienteDetailDTO(adopcion.getCliente());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public AdopcionEntity toEntity(){
        AdopcionEntity nueva = new AdopcionEntity();
        nueva.setDocsAdopcion(this.docsAdopcion);
        nueva.setId(this.id);
        return nueva;
    }
           @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
