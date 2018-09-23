/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools |
Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import java.io.Serializable;

/**
 *
 * @author Cristhian Peña
 */
public class CalificacionDTO implements Serializable
{
    private Integer puntaje;   
    private String comentarios;
    private Long id;

    public CalificacionDTO() {
    }

    public CalificacionDTO(CalificacionEntity calificacionEntity) 
    {
        if(calificacionEntity != null){
            this.id=calificacionEntity.getId();
            this.puntaje=calificacionEntity.getPuntaje();
            this.comentarios=calificacionEntity.getComentarios();
        }
    }

    /**
     * @return puntaje de la calificacion
     */
    public Integer getPuntaje() 
    {
        return puntaje;
    }

    /**
     * @param puntaje puntaje de calificacion
     */
    public void setPuntaje(Integer puntaje) 
    {
        this.puntaje = puntaje;
    }

    /**
     * @return comentarios del cliente
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios los comentarios de la calificacion
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public CalificacionEntity toEntity(){
        CalificacionEntity calificacionEntity = new CalificacionEntity();
        calificacionEntity.setId(id);
        calificacionEntity.setComentarios(comentarios);
        calificacionEntity.setPuntaje(puntaje);
        return calificacionEntity;
    }
    
}
