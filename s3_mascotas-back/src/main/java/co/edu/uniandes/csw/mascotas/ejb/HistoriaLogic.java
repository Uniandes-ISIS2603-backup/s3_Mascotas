/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.HistoriaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.HistoriaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Camilo Pinilla
 */
@Stateless
public class HistoriaLogic {
    
    // Debug logger
    private static final Logger LOOGER = Logger.getLogger(HistoriaLogic.class.getName());
    
    @Inject
    private HistoriaPersistence persistence;
    
    public HistoriaEntity createHistoria(HistoriaEntity entity) throws BusinessLogicException{
        LOOGER.info("History creation process begins");
        // missing verifications
        persistence.create(entity);
        LOOGER.info("History creation finishes");
        return entity;
    }
    
    public List<HistoriaEntity> getHistorias(){
        LOOGER.log(Level.INFO, "Searching for histories");
        List<HistoriaEntity> historias = persistence.findAll();
        LOOGER.log(Level.INFO, "Ending search");
        return historias;
    }
    
    public HistoriaEntity getHistoria(Long historiaId){
        LOOGER.log(Level.INFO, "Looking for history with id = {0}", historiaId);
        HistoriaEntity historiaEntity = persistence.find(historiaId);
        if (historiaEntity == null) {
            LOOGER.log(Level.SEVERE, "The customer with id = {0} does not exists", historiaId);            
        }
        LOOGER.log(Level.INFO, "Ending search for the customer with id {0}", historiaId);
        return historiaEntity;
    }
    
    public HistoriaEntity updateHistoria(Long historiaId, HistoriaEntity historiaEntity) throws BusinessLogicException{
        LOOGER.log(Level.INFO, "Updating the customer with id = {0}", historiaId);
        HistoriaEntity newEntity = persistence.update(historiaEntity);
        LOOGER.log(Level.INFO, "Finished update on customer with id = {0}", historiaEntity.getId());
        return newEntity;
    }
    
    public void deleteCliente(HistoriaEntity historia){
        persistence.delete(historia);
    }
}
