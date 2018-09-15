/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.AdopcionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.AdopcionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Sebastian Gomez
 */
@Stateless
public class AdopcionLogic {
      private static final Logger LOGGER = Logger.getLogger(CompraLogic.class.getName());
    @Inject
    private AdopcionPersistence persistence;
    public AdopcionEntity crearAdopcion(AdopcionEntity adopEnt)throws BusinessLogicException{
        LOGGER.info("Creacion de adopcion");
        persistence.create(adopEnt);
        
        return adopEnt;
    }
        public List<AdopcionEntity> getAdopciones(){
        LOGGER.log(Level.INFO, "Buscando adopciones");
            List<AdopcionEntity> adopciones = persistence.findAll();
        LOGGER.log(Level.INFO, "Ending search");
        return adopciones;
    }
    
    public AdopcionEntity getAdopcion(Long adopcionId){
        LOGGER.log(Level.INFO, "Looking for purchase with id = {0}", adopcionId);
        AdopcionEntity adopEntity = persistence.find(adopcionId);
        if (adopEntity == null) {
            LOGGER.log(Level.SEVERE, "The purchase with id = {0} does not exists", adopcionId);            
        }
        LOGGER.log(Level.INFO, "Ending search for the pet with id {0}", adopcionId);
        return adopEntity;
    }
    
    public AdopcionEntity updateAdopcion(Long adopId, AdopcionEntity adopEntity) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Updating the pet with id = {0}", adopId);
        AdopcionEntity newEntity = persistence.update(adopEntity);
        LOGGER.log(Level.INFO, "Finished update on pet with id = {0}", adopEntity.getId());
        return newEntity;
    }
}
