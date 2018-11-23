/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
import co.edu.uniandes.csw.mascotas.persistence.RazaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lemus
 */
@Stateless
public class RazaMascotaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(RazaMascotaLogic.class.getName());

    @Inject
    private RazaPersistence razaPersistence;
    
    @Inject
    private MascotaPersistence mascotaPersistence;
    
    /**
     * Relaciona una raza con su mascota (ya existentes)
     * @param razasId
     * @param mascotaIngresada
     * @return instancia de razaEntity que fue asociada con la mascota
     */
    public RazaEntity addMascota(Long razasId, MascotaEntity mascotaIngresada) throws BusinessLogicException{
        RazaEntity raza = razaPersistence.find(razasId);
        
        LOGGER.info("Pet creation process begins");
        if (mascotaIngresada.getEdad() < 0) {
            throw new BusinessLogicException("age is incorrect");
        }
        MascotaEntity mascota = mascotaPersistence.create(mascotaIngresada);
        LOGGER.info("Pet creation finishes");
        
        if(mascota == null || mascota.getDeleted()){
            throw new BusinessLogicException("The pet doesn't exist.");
        }
        raza.getMascotas().add(mascota);
        mascota.setRaza(raza);
        mascotaPersistence.update(mascota);
        razaPersistence.update(raza);
        return razaPersistence.find(razasId);
    }
    
    /**
     * Retorna la colección de mascotas asociadas a una raza concreta
     * @param razasId
     * @return List de MascotaEntity
     */
    public List<MascotaEntity> getMascotas(Long razasId){
        return razaPersistence.find(razasId).getMascotas();
    }
    
    /**
     * Retorna la mascota asociada a la raza identificada por el parámetro
     * @param razasId
     * @param mascotasId
     * @return Entidad de la mascota
     */
    public MascotaEntity getMascota(Long razasId, Long mascotasId)throws BusinessLogicException{
        List<MascotaEntity> mascotas = getMascotas(razasId);
        MascotaEntity mascotaBuscada = mascotaPersistence.find(mascotasId);
        int posicion = mascotas.indexOf(mascotaBuscada);
        if (posicion >= 0 || !mascotaBuscada.getDeleted()) {
            return mascotas.get(posicion);
        }else{
            throw new BusinessLogicException("La mascota no está asociada con la raza");
        }
    }
    
    /**
     * Elimina la relación entre una mascota y una raza (ya existentes)
     * @param razasId
     * @param mascotasId 
     */
    public void removeMascota(Long razasId, Long mascotasId){
        RazaEntity r = razaPersistence.find(razasId);
        MascotaEntity m = mascotaPersistence.find(mascotasId);
        m.setDeleted(Boolean.TRUE);
        r.getMascotas().remove(m);
    }
    
}
