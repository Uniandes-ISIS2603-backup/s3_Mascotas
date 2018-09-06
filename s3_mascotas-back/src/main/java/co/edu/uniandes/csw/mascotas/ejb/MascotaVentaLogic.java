/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaVentaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaVentaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sebastian Mujica
 */
@Stateless
public class MascotaVentaLogic {
    
    private static final Logger LOOGER = Logger.getLogger(MascotaVentaLogic.class.getName());
    
    @Inject
    private MascotaVentaPersistence persistence;
    
     public MascotaVentaEntity crearMascotaVenta(MascotaVentaEntity entity) throws BusinessLogicException{
        LOOGER.info("MascotaVenta creation process begins");
        persistence.create(entity);
        LOOGER.info("MascotaVenta was succesfully created");
        return entity;
    }
     
     public MascotaVentaEntity getMascotaVenta(Long mascotaVentaId){
        LOOGER.log(Level.INFO, "Looking for pet with id = {0}", mascotaVentaId);
        MascotaVentaEntity mascotaventaEntity= persistence.find(mascotaVentaId);
        if (mascotaventaEntity == null) {
            LOOGER.log(Level.SEVERE, "The pet with id = {0} does not exists", mascotaVentaId);            
        }
        LOOGER.log(Level.INFO, "Ending search for the pet with id {0}", mascotaVentaId);
        return mascotaventaEntity;
     }
     
     public MascotaVentaEntity updateMascotaventa(long mascotaVentaId, MascotaVentaEntity mascotaVentaEntity){
         LOOGER.log(Level.INFO, "Updating the pet with id ={0}", mascotaVentaId);
         MascotaVentaEntity nuevaMascotaVentaEntity = persistence.update(mascotaVentaEntity);
         LOOGER.log(Level.INFO, "Finished update on pet with id ={0}", mascotaVentaEntity.getId());
         return nuevaMascotaVentaEntity;
     }
    
     public void deleteMascotaVenta(MascotaVentaEntity mascotaVentaEntity){
         persistence.delete(mascotaVentaEntity);
     }
    
    
}
