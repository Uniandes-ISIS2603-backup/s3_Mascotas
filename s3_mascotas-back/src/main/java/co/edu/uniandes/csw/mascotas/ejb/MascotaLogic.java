/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.uniandes.csw.mascotas.persistence.MascotaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lemus
 */
@Stateless
public class MascotaLogic {
    
    private static final Logger LOOGER = Logger.getLogger(MascotaLogic.class.getName());
    
    @Inject
    private MascotaPersistence persistence;
    
    public MascotaEntity crearMascota(MascotaEntity entity) throws BusinessLogicException{
        LOOGER.info("Pet creation process begins");
        // missing verifications
        persistence.create(entity);
        LOOGER.info("Pet creation finishes");
        return entity;
    }
    
    public List<MascotaEntity> getMascotas(){
        LOOGER.log(Level.INFO, "Searching for pets");
        List<MascotaEntity> mascotas = persistence.findAll();
        LOOGER.log(Level.INFO, "Ending search");
        return mascotas;
    }
    
    public MascotaEntity getMascota(Long mascotasId){
        LOOGER.log(Level.INFO, "Looking for pet with id = {0}", mascotasId);
        MascotaEntity mascotaEntity = persistence.find(mascotasId);
        if (mascotaEntity == null) {
            LOOGER.log(Level.SEVERE, "The pet with id = {0} does not exists", mascotasId);            
        }
        LOOGER.log(Level.INFO, "Ending search for the pet with id {0}", mascotasId);
        return mascotaEntity;
    }
    
    public MascotaEntity updateMascota(Long mascotasId, MascotaEntity mascotaEntity) throws BusinessLogicException{
        LOOGER.log(Level.INFO, "Updating the pet with id = {0}", mascotasId);
        MascotaEntity newEntity = persistence.update(mascotaEntity);
        LOOGER.log(Level.INFO, "Finished update on pet with id = {0}", mascotaEntity.getId());
        return newEntity;
    }
    
    public void deleteMascota(Long mascotasId){
        persistence.delete(mascotasId);
    }
}
