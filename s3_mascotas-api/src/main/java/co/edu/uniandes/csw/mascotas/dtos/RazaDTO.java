/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import java.io.Serializable;

/**
 *
 * @author lemus
 */
public class RazaDTO implements Serializable{
    
    private Long id;
    private String nombre;
    
    public RazaDTO(){
    }
    
    public RazaDTO(RazaEntity raza){
        this.id = raza.getId();
        this.nombre = raza.getNombre();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public RazaEntity toEntity(){
        return new RazaEntity(nombre);
    }

    
    
}
