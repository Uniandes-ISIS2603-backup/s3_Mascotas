/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author lemus
 */
@Entity
public class RazaEntity extends BaseEntity implements Serializable{
    
    private String nombre;
    private String imagen;
 //   private Long  especieId;
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    @PodamExclude
    @ManyToOne
    private EspecieEntity especie;
    
    @PodamExclude
    @OneToMany(mappedBy = "raza", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MascotaEntity> mascotas = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<MascotaEntity> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<MascotaEntity> mascotas) {
        this.mascotas = mascotas;
    }
 //   public void setEspecieId(Long especieId)
 //   {
 //       this.especieId = especieId;
 //   }
    
 //   public Long getEspecieId()
 //   {
 //       return especieId;
 //   }
    
    public void setEspecie(EspecieEntity especieEntity){
        this.especie = especieEntity;
    }
    
    public EspecieEntity getEspecie(){
        return especie;
    }
}
