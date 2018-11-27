/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import java.io.Serializable;

/**
 *
 * @author lemus
 */
public class MascotaDTO implements Serializable{
    
    private Long id;
    private String nombre;
    private Integer edad;
    private String genero;
    private String color;
    private Double precio;
    private String imagen;
    private RazaDTO raza;


   
    public MascotaDTO() {
    }
    
    public MascotaDTO(MascotaEntity mascotaEntity){
        if(mascotaEntity != null)
        {
            this.id = mascotaEntity.getId();
            this.nombre = mascotaEntity.getNombre();
            this.edad = mascotaEntity.getEdad();
            this.genero = mascotaEntity.getGenero();
            this.color = mascotaEntity.getColor();
            this.precio = mascotaEntity.getPrecio();
            this.imagen = mascotaEntity.getImagen();
            if (mascotaEntity.getRaza() != null) {
                this.raza = new RazaDTO(mascotaEntity.getRaza());
            }else{
                this.raza = null;
            }
            
        }
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public RazaDTO getRaza() {
        return raza;
    }

    public void setRaza(RazaDTO raza) {
        this.raza = raza;
    }
    public String getImagen() {
        return imagen;
    }
    
    public void setImagen(String imagen){
        this.imagen = imagen;
    }
    
    
    public MascotaEntity toEntity(){
        MascotaEntity newEntity = new MascotaEntity();
        newEntity.setNombre(nombre);
        newEntity.setEdad(edad);
        newEntity.setGenero(genero);
        newEntity.setColor(color);
        newEntity.setPrecio(precio);
        newEntity.setId(id);
        newEntity.setImagen(imagen);
        if(this.raza != null)
            newEntity.setRaza(this.raza.toEntity());
       
        return newEntity;
    }
}
