/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.RazaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lemus
 */
@Stateless
public class RazaLogic {
    
    private static final Logger LOOGER = Logger.getLogger(RazaLogic.class.getName());

    @Inject
    RazaPersistence persistence;
    
    public RazaEntity crearRaza(RazaEntity entity) throws BusinessLogicException{
        LOOGER.info("Race creation process begins");
        // missing verifications
        persistence.create(entity);
        LOOGER.info("Race creation finishes");
        return entity;
    }
    
    public RazaEntity getRaza(Long razasId){
        RazaEntity razaEntity = persistence.find(razasId);
        if (razaEntity == null) {
            LOOGER.log(Level.SEVERE, "The race with id = {0} does not exists", razaEntity);            
        }
        return razaEntity;
    }
    
    public void deleteRaza(Long razasId){
        persistence.delete(razasId);
    }
}
