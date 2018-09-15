/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Juan Sebastian Gomez
 */
@Entity
public class AdopcionEntity extends BaseEntity implements Serializable{
    private String docsAdopcion;
    @PodamExclude
    @OneToOne
    private CalificacionEntity calificacion;
    
    public String getDocsAdopcion() {
        return docsAdopcion;
    }
    
    public CalificacionEntity getCalificacion()
    {
        return calificacion;
    }
    
    public void setCalificacion(CalificacionEntity pCalificacion)
    {
       calificacion = pCalificacion;
    }

    public void setDocsAdopcion(String docsAdopcion) {
        this.docsAdopcion = docsAdopcion;
    }
    
}
