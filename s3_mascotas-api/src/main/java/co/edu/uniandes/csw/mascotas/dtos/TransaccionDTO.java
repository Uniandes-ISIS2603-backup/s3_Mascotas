/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools |
Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.TransaccionEntity;
import java.io.Serializable;

/**
 *
 * @author Sebastian Mujica
 */
public class TransaccionDTO implements Serializable{
    
    private Long id;
    
    private MascotaDTO mascota;

    public TransaccionDTO() {
    }

    public TransaccionDTO(TransaccionEntity transaccionEntity) {
        this.id=transaccionEntity.getId();
        if(transaccionEntity.getMascota()!=null){
            this.mascota= new MascotaDTO(transaccionEntity.getMascota());
        }
        else{
            this.mascota=null;
        }
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
    
    public TransaccionEntity toEntity(){
        return new TransaccionEntity(this.id);
    }
    
}
