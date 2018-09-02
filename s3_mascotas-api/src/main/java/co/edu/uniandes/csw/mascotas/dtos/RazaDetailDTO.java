/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lemus
 */
public class RazaDetailDTO extends RazaDTO implements Serializable{
    
    private List<MascotaDTO> mascotas;

    public RazaDetailDTO() {
        super();
    }
    
    public RazaDetailDTO(RazaEntity razaEntity){
        super(razaEntity);
        if (razaEntity.getMascotas() != null) {
            mascotas = new ArrayList<>();
            for(MascotaEntity m : razaEntity.getMascotas()){
                mascotas.add(new MascotaDTO(m));
            }
        }
    }

    public List<MascotaDTO> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<MascotaDTO> mascotas) {
        this.mascotas = mascotas;
    }
    
    @Override
    public RazaEntity toEntity(){
        RazaEntity razaEntity = super.toEntity();
        if (mascotas != null) {
            List<MascotaEntity> mascotasEntity = new ArrayList<>();
            for (MascotaDTO m : getMascotas()) {
                mascotasEntity.add(m.toEntity());
            }
            razaEntity.setMascotas(mascotasEntity);
        }
        return razaEntity;
    }
    
}
