/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import java.io.Serializable;

/**
 *
 * @author Sebastian Mujica
 */
public class MascotaAdopcionDTO implements Serializable{
    
    private String historia;
    
    private MascotaDTO mascota;

    public MascotaAdopcionDTO() {
    }

    public MascotaAdopcionDTO(MascotaAdopcionEntity mascotaAdopcionEntity) {
        this.historia= mascotaAdopcionEntity.getHistoria();
        if(mascotaAdopcionEntity.getMascota()!=null){
            this.mascota = new MascotaDTO(mascotaAdopcionEntity.getMascota());
        }
        else{
            this.mascota = null;
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
     * @param mascota the mascota to set
     */
    public void setMascota(MascotaDTO mascota) {
        this.mascota = mascota;
    }
    
    public MascotaAdopcionEntity toEntity(){
        MascotaAdopcionEntity entity = new MascotaAdopcionEntity(this.historia);
        if(this.mascota!=null){
            entity.setMascota(this.mascota.toEntity());
        }
        return entity;
    }
    
}
