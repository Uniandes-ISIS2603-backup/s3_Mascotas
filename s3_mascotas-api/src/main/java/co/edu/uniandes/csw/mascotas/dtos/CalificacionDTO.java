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
 * @author Pena
 */
public class CalificacionDTO implements Serializable
{
    private Integer puntaje;   
    private String comentarios;

    public CalificacionDTO() {
    }

    public CalificacionDTO(CalificacionEntity calificacionEntity) 
    {
        this.puntaje=calificacionEntity.getPuntaje();
        this.comentarios=calificacionEntity.getComentarios();
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
    
    public CalificacionEntity toEntity(){
        return new TransaccionEntity(this.puntaje, this.comentarios);
    }
    
}
