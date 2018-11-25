/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.EspecieEntity;
import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import java.io.Serializable;

/**
 *
 * @author lemus
 */
public class RazaDTO implements Serializable{
    
    private Long id;
    private String nombre;
    private String imagen;
    private EspecieDTO especie;

    public EspecieDTO getEspecie() {
        return especie;
    }

    public void setEspecieId(EspecieDTO especie) {
        this.especie = especie;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public RazaDTO(){
    }
    
    public RazaDTO(RazaEntity raza){
        this.id = raza.getId();
        this.nombre = raza.getNombre();
        this.imagen = raza.getImagen();
        if(raza.getEspecie()!=null)
            this.especie = new EspecieDTO(raza.getEspecie());
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
        RazaEntity newEntity = new RazaEntity();
        newEntity.setNombre(nombre);
        newEntity.setId(id);
        newEntity.setImagen(imagen);
        if(especie != null)
            newEntity.setEspecie(especie.toEntity());
        return newEntity;
    }   
}
