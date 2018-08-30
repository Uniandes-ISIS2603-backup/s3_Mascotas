/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;
import java.io.Serializable;
import javax.persistence.Entity;
/**
 *
 * @author Juan Sebastian Gomez
 */
@Entity
public class AdopcionEntity extends BaseEntity implements Serializable{
    private String docsAdopcion;

    public String getDocsAdopcion() {
        return docsAdopcion;
    }

    public void setDocsAdopcion(String docsAdopcion) {
        this.docsAdopcion = docsAdopcion;
    }
    
}
