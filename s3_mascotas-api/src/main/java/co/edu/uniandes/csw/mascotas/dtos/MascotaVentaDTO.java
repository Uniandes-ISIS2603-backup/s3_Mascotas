/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaVentaEntity;
import java.io.Serializable;

/**
 *
 * @author Sebastian Mujica
 */
public class MascotaVentaDTO implements Serializable{
    
    private Long id;
    
    private String documentosPedegree;
    
    private Double precio;
    
    private MascotaDTO mascota;

    public MascotaVentaDTO() {
    }
    
    public MascotaVentaDTO( MascotaVentaEntity mascotaVenta) {
        this.documentosPedegree = mascotaVenta.getDocumentosPedegree();
        this.id= mascotaVenta.getId();
        this.precio= mascotaVenta.getPrecio();
        if (mascotaVenta.getMascota()!=null) {
            this.mascota = new MascotaDTO(mascotaVenta.getMascota());
        }
        else{
            this.mascota = null;
        }
    }

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
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
    public MascotaDTO getMascota() {
        return mascota;
    }

    /**
     * @param mascota the mascota to set
     */
    public void setMascota(MascotaDTO mascota) {
        this.mascota = mascota;
    }
    
    public MascotaVentaEntity toEntity(){
        MascotaVentaEntity entity = new MascotaVentaEntity();
        entity.setDocumentosPedegree(this.documentosPedegree);
        entity.setPrecio(this.precio);
        if (this.mascota!=null) {
            entity.setMascota(this.mascota.toEntity());
        }
        return entity;
    }
    
}
