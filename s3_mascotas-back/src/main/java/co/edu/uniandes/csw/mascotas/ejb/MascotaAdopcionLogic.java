/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaAdopcionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sebastian Mujica
 */
@Stateless
public class MascotaAdopcionLogic {
    
    private static final Logger LOOGER = Logger.getLogger(MascotaAdopcionLogic.class.getName());
    
    @Inject
    private MascotaAdopcionPersistence persistence;
    
    public MascotaAdopcionEntity crearMascotaAdopcion(MascotaAdopcionEntity entity) throws BusinessLogicException{
        LOOGER.info("MascotaAdopcion creation process begins");
        persistence.create(entity);
        LOOGER.info("MascotaAdopcion was succesfully created");
        return entity;
    }
    
    public MascotaAdopcionEntity getMascotaAdopcion(Long mascotaAdopcionId){
        LOOGER.log(Level.INFO, "Looking for pet with id = {0}", mascotaAdopcionId);
        MascotaAdopcionEntity mascotaAdopcionEntity= persistence.find(mascotaAdopcionId);
        if (mascotaAdopcionEntity == null) {
            LOOGER.log(Level.SEVERE, "The pet with id = {0} does not exists", mascotaAdopcionId);            
        }
        LOOGER.log(Level.INFO, "Ending search for the pet with id {0}", mascotaAdopcionId);
        return mascotaAdopcionEntity;
     }
     
     public MascotaAdopcionEntity updateMascotaAdopcion(long mascotaAdopcionId, MascotaAdopcionEntity mascotaAdopcionEntity){
         LOOGER.log(Level.INFO, "Updating the pet with id ={0}", mascotaAdopcionId);
         MascotaAdopcionEntity nuevaMascotaAdopcionEntity = persistence.update(mascotaAdopcionEntity);
         LOOGER.log(Level.INFO, "Finished update on pet with id ={0}", mascotaAdopcionEntity.getId());
         return nuevaMascotaAdopcionEntity;
     }
    
     public void deleteMascotaAdopcion(Long mascotaAdopcionId){
         persistence.delete(mascotaAdopcionId);
     }
    
     public List<MascotaAdopcionEntity> getMascotasAdopcion(){
         LOOGER.log(Level.INFO, "Inicia proceso de consultar todas las MascotasAdopcion");
         List<MascotaAdopcionEntity> mascotasAdopcion= persistence.findAll();
         LOOGER.log(Level.INFO, "Termina proceso de consultar todas las MascotasAdopcion");
         return mascotasAdopcion;
     }
    
}
