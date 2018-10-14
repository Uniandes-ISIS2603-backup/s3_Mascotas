/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Sebastian Mujica
 */
@Entity
public class MascotaAdopcionEntity extends BaseEntity implements Serializable{

    /**
     * representa la historia de una mascota de adopción de tipo Entity.
     */
    private String historia;
    
    
    @PodamExclude
    @OneToOne
    //@OneToOne(mappedBy="mascotaAdopcion" , fetch = FetchType.EAGER)
    private MascotaEntity mascota;

   

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
     * @return the mascotaEntity
     */
    public MascotaEntity getMascota() {
        return mascota;
    }

    /**
     * @param mascota the mascotaEntity to set
     */
    public void setMascota(MascotaEntity mascota) {
        this.mascota = mascota;
    }
}
