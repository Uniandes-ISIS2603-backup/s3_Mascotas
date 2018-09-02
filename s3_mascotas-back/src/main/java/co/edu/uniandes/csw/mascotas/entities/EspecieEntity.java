/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author pena
 */
@Entity
public class EspecieEntity extends BaseEntity implements Serializable{
    
    private String nombre;
    
    @PodamExclude
    @OneToMany
    private List<RazaEntity> razas;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public List<RazaEntity> getRazas(){
        return razas;
    }
    
    public void setRazas(List razas){
        this.razas = razas;
    }
}

