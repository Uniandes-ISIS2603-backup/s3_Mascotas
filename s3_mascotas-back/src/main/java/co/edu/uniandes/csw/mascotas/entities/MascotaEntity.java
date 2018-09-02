/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author lemus
 */
@Entity
public class MascotaEntity extends BaseEntity implements Serializable{
    
    private String nombre;
    private Integer edad;
    private String genero;
    private String color;
    private Double precio;
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private RazaEntity raza;

    public MascotaEntity(String nombre, Integer edad, String genero, String color, Double precio) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.color = color;
        this.precio = precio;
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

    public RazaEntity getRaza() {
        return raza;
    }

    public void setRaza(RazaEntity raza) {
        this.raza = raza;
    }
    
    
}
