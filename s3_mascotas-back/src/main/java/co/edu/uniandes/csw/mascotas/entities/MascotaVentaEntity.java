/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Sebastian Mujica
 */
@Entity
public class MascotaVentaEntity extends BaseEntity implements Serializable{
    
    private String documentosPedegree;
    
    private Double precio;
    
    @PodamExclude
    @OneToOne
    //@OneToOne( mappedBy = "mascotaVenta", fetch = FetchType.EAGER)
    private MascotaEntity mascota;


    /**
     * @return the documentosPedegree
     */
    public String getDocumentosPedegree() {
        return documentosPedegree;
    }

    /**
     * @param documentosPedegree the documentosPedegree to set
     */
    public void setDocumentosPedegree(String documentosPedegree) {
        this.documentosPedegree = documentosPedegree;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the mascota
     */
    public MascotaEntity getMascota() {
        return mascota;
    }

    /**
     * @param mascota the mascota to set
     */
    public void setMascota(MascotaEntity mascota) {
        this.mascota = mascota;
    }
    
    
    
}
