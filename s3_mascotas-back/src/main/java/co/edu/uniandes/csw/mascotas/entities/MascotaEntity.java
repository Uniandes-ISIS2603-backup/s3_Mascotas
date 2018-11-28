/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import javax.persistence.*;
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
    private String imagen;
    
        
    @PodamExclude
    @ManyToOne
    private RazaEntity raza;
    
    @PodamExclude
    @OneToOne(mappedBy="mascota", fetch = FetchType.LAZY)
    private HistoriaEntity historia;
    
    @PodamExclude
    @OneToOne(mappedBy = "mascota", fetch = FetchType.LAZY)
    private CompraEntity compra;
    
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
    
    @Override
    public String toString() {
        return "MascotaEntity{" + "nombre=" + nombre + ", edad=" + edad + ", genero=" + genero + ", color=" + color + ", precio=" + precio + ", raza=" + raza + '}';
    }
    
    /**
     * @return the compra
     */
    public CompraEntity getCompra() {
        return compra;
    }

    /**
     * @param compra the compra to set
     */
    public void setCompra(CompraEntity compra) {
        this.compra = compra;
    }
    public String getImagen() {
        return imagen;
    }
    
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public HistoriaEntity getHistoria() {
        return historia;
    }

    public void setHistoria(HistoriaEntity historia) {
        this.historia = historia;
    }
}
