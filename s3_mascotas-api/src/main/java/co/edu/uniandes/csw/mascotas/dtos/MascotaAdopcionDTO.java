/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import java.io.Serializable;

/**
 * Esta clase representa una mascotaADopcion en el objeto de transferencia de
 * archvios DTO model.
 *
 * @author Sebastian Mujica
 */
public class MascotaAdopcionDTO implements Serializable{
    
    
    /**
     * Atributo que representa el id de una masdcota de adopción.
     */
    private Long id;
    
    
    /**
     * Atributo que representa la hsitoria de una mascota de adopción.
     */
    private String historia;
    
    /**
     * Atributo que representa una relación a uno de una mascota de 
     * adopción con una mascota.
     */
    private MascotaDTO mascota;

    
    /**
     * Método constructor que permite construir una mascota de adopcion
     * de manera vacía.
     */
    public MascotaAdopcionDTO() {
    }

    
    /**
     * Método constructor que construye una mascota de adopcion DTO a partir
     * de una mascota de adopcíón entity.
     * @param mascotaAdopcionEntity La mascota De adopción entity que se 
     * transforma a DTO
     */
    public MascotaAdopcionDTO(MascotaAdopcionEntity mascotaAdopcionEntity) {
         this.historia= mascotaAdopcionEntity.getHistoria();
         this.id = mascotaAdopcionEntity.getId();
         if(mascotaAdopcionEntity.getMascota()!=null){
           this.mascota= new MascotaDTO(mascotaAdopcionEntity.getMascota());
         }
         else{
             this.mascota=null;
         }
    }
        
    

    /**
     * @return the historia
     */
    public String getHistoria() {
        return historia;
    }

    /**
     * @param historia the historia to set
     */
    public void setHistoria(String historia) {
        this.historia = historia;
    }

    /**
     * @return the mascota
     */
    public MascotaDTO getMascota() {
        return mascota;
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
     * @param mascota the mascota to set
     */
    public void setMascota(MascotaDTO mascota) {
        this.mascota = mascota;
    }
    
    
    /**
     * Método que permite transformar una mascota de adopción DTO a una mascota
     * de adopción Entity.
     * @return Mascota de adopción entity.
     */
    public MascotaAdopcionEntity toEntity(){
        MascotaAdopcionEntity entity = new MascotaAdopcionEntity();
        entity.setHistoria(this.historia);
        entity.setId(this.id);
        return entity;
    }
    
}
