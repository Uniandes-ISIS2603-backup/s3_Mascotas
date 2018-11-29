/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
import co.edu.uniandes.csw.mascotas.persistence.RazaPersistence;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
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
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    public MascotaEntity getMascota(Long razasId, Long mascotasId)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Consultando la mascota id: {0} de la raza id: {1}", new Object[]{mascotasId, razasId});
        List<MascotaEntity> mascotas = getMascotas(razasId);
        MascotaEntity mascotaBuscada = mascotaPersistence.find(mascotasId);
        if(mascotaBuscada == null  || mascotaBuscada.getDeleted()) throw new BusinessLogicException("la mascota no existe");
        for(int i = 0; i < mascotas.size(); i++){
            if(Objects.equals(mascotas.get(i).getId(), mascotaBuscada.getId())) return mascotaBuscada;
        }
        throw new BusinessLogicException("La mascota no está asociada con la raza");
    }
    
}
