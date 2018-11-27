/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.HistoriaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.HistoriaPersistence;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
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
    @Inject
    private MascotaPersistence mascPersistence;
    
    public HistoriaEntity createHistoria(HistoriaEntity entity) throws BusinessLogicException{
        LOOGER.info("History creation process begins");
        if(entity == null){
            throw new BusinessLogicException("Invalid history");
        }
        if(entity.getMascota() == null){
            throw new BusinessLogicException("La mascota ingresada no es valida");
        }
        MascotaEntity masc = mascPersistence.find(entity.getMascota().getId());
        if(masc == null){
            throw new BusinessLogicException("La mascota no existe");
        }
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
            LOOGER.log(Level.SEVERE, "The story with id = {0} does not exists", historiaId);            
        }
        LOOGER.log(Level.INFO, "Ending search for the story with id {0}", historiaId);
        return historiaEntity;
    }
    
    public HistoriaEntity updateHistoria(Long historiaId, HistoriaEntity historiaEntity) throws BusinessLogicException{
        if(persistence.find(historiaId) == null){
            throw new BusinessLogicException("No existe la historia");
        }
        if(historiaEntity == null){
            throw new BusinessLogicException("Historia invalida");
        }
        LOOGER.log(Level.INFO, "Updating the customer with id = {0}", historiaId);
        HistoriaEntity newEntity = persistence.update(historiaEntity);
        LOOGER.log(Level.INFO, "Finished update on customer with id = {0}", newEntity.getId());
        return newEntity;
    }
    
    public void deleteHistoria(HistoriaEntity historia){
        persistence.delete(historia);
    }
}
