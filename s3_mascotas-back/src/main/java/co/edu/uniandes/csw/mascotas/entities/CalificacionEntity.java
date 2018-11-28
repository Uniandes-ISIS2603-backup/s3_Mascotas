/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Cristhian Peña
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable
{
    
    private String comentarios;
    private Integer puntaje;
    
    @PodamExclude
    @OneToOne
    private CompraEntity compra;
    
    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }
    
    public CompraEntity getCompra(){
        return compra;
    }
    public void setCompra(CompraEntity compra) {
        this.compra = compra;
    }
}

