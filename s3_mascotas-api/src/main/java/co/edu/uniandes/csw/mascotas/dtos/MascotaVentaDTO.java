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
 *Esta clase representa una MascotaVenta en el objeto de transferencia de archivos.
 * 
 * @author Sebastian Mujica
 */
public class MascotaVentaDTO implements Serializable{
    
    /**
     * Atributo que representa el id de una mascotaVenta
     */
    private Long id;
    
    /**
     * Atributo que representa los documentos Pedegree de unas mascotaVenta
     */
    private String documentosPedegree;
    
    /**
     * Atributo que representa el precio de venta de una mascota venta.
     */
    private Double precio;
    
    
    /**
     * Atributo que representa una relación a uno de una mascotaVentaDTO
     * con una mascotaDTO 
     */
    private MascotaDTO mascota;

 

 
    /**
     * Constructor vacío de la clase MascotaVentaDTO
     */
    public MascotaVentaDTO() {
    }
    
    /**
     * Método constructor que construye una mascota de venta  DTO a partir
     * de una mascota de venta Entity.
     * @param mascotaVenta la Entity que se utiliza para construir la mascota
     * de venta DTO
     */
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
    
    /**
     * Metodo que transforma un objeto DTO a una MascotaVentaEntity
     * @return MascotaVentaEntity
     */
    public MascotaVentaEntity toEntity(){
        MascotaVentaEntity entity = new MascotaVentaEntity();
        entity.setDocumentosPedegree(this.documentosPedegree);
        entity.setPrecio(this.precio);
        if (this.mascota!=null) {
            entity.setMascota(this.mascota.toEntity());
        }
        entity.setId(this.id);
        return entity;
    }
    
}
