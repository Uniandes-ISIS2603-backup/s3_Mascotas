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
    //Entero con el puntaje de la calificación
    private Integer puntaje;   
    //Cadena de caracteres que contiene los comentarios de la calificacion
    private String comentarios;
    //Identificador unico de la calificacion
    private Long id;
    
    private CompraDTO compra;

    public CalificacionDTO() {
    }

    /**
     * Constructor de un objeto DTO, creado a partir de un objeto entity entrado por parámetro
     * @param calificacionEntity objeto entity a partir del cual se creará el objeto
     */
    public CalificacionDTO(CalificacionEntity calificacionEntity) 
    {
        if(calificacionEntity != null){
            this.id=calificacionEntity.getId();
            this.puntaje=calificacionEntity.getPuntaje();
            this.comentarios=calificacionEntity.getComentarios();
            if(calificacionEntity.getCompra() != null)
                this.compra = new CompraDTO(calificacionEntity.getCompra());
        }
    }

    public CompraDTO getCompra() {
        return compra;
    }

    public void setCompra(CompraDTO compra) {
        this.compra = compra;
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
    
    /**
     * Método que convierte de DTO a objeto Entity, para enviarlo a la capa de la lógica
     * @return calificacionEntity objeto entity ya convertido.
     */
    public CalificacionEntity toEntity(){
        CalificacionEntity calificacionEntity = new CalificacionEntity();
        calificacionEntity.setId(id);
        calificacionEntity.setComentarios(comentarios);
        calificacionEntity.setPuntaje(puntaje);
        if(this.compra != null)
            calificacionEntity.setCompra(this.compra.toEntity());
        return calificacionEntity;
    }
    
}
