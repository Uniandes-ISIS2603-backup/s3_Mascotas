/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools |
Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.EspecieEntity;
import java.io.Serializable;

/**
 *
 * @author Cristhian Peña
 */
public class EspecieDTO implements Serializable
{
    private Long id;   
    private String nombre;
    private String imagen;
    public EspecieDTO() {
    }

    public EspecieDTO(EspecieEntity especieEntity) 
    {
        if(especieEntity != null)
        {
            this.nombre=especieEntity.getNombre();
            this.id=especieEntity.getId();
            this.imagen=especieEntity.getImagen();
        }
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * @return id de la especie
     */
    public Long getId() 
    {
        return id;
    }

    /**
     * @param id id de la especie
     */
    public void setId(Long id) 
    {
        this.id = id;
    }

    /**
     * @return nombre de la especie
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre nombre de la especie
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public EspecieEntity toEntity(){
        EspecieEntity especieEntity = new EspecieEntity();
        especieEntity.setId(id);
        especieEntity.setNombre(nombre);
        especieEntity.setImagen(imagen);
        return especieEntity;
    }
    
}
