/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.mascotas.persistence.CompraPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Cristhian Peña
 */
@Stateless
public class CalificacionLogic 
{
    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());
    
    @Inject
    private CalificacionPersistence persistence;
    @Inject
    private CompraPersistence compraPersistence;
    
    public CalificacionEntity crearCalificacion(CalificacionEntity entrada) throws BusinessLogicException
    {
        LOGGER.info("A new score is being made");
        if(entrada == null)
        {
            throw new BusinessLogicException("Invalid score");
        }
        if(entrada.getCompra() == null || compraPersistence.find(entrada.getCompra().getId()) == null)
        {
            throw new BusinessLogicException("La compra es invalida o no existe");
        }
        if(entrada.getPuntaje() < 1 || entrada.getPuntaje() > 5)
        {
            throw new BusinessLogicException("El puntaje no es valido");
        }
        persistence.create(entrada);
        LOGGER.info("Score creation end.");
        return entrada;
    }
    
    public List<CalificacionEntity> getCalificaciones(){
        LOGGER.log(Level.INFO, "Searching for scores");
        List<CalificacionEntity> calificaciones = persistence.findAll();
        LOGGER.log(Level.INFO, "Ending search");
        return calificaciones;
    }
    
    public CalificacionEntity getCalificacion(Long calificacionId)
    {
        LOGGER.log(Level.INFO, "Looking for score with id = {0}", calificacionId);
        CalificacionEntity calificacionEntity = persistence.find(calificacionId);
        if(calificacionEntity == null){
            LOGGER.log(Level.SEVERE, "La calificacion con el id = {0} no existe", calificacionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la calificacion con id = {0}", calificacionId);
        return calificacionEntity;
    }
    
    public CalificacionEntity updateCalificacion(Long califId, CalificacionEntity califEntity) throws BusinessLogicException{
        if(persistence.find(califId) == null){
            throw new BusinessLogicException("No existe la calificacion");
        }
        if(califEntity == null){
            throw new BusinessLogicException("Calificacion invalida");
        }
        if(califEntity.getPuntaje() < 1 || califEntity.getPuntaje() > 5)
        {
            throw new BusinessLogicException("El puntaje no es valido");
        }
        LOGGER.log(Level.INFO, "Updating the score with id = {0}", califId);
        CalificacionEntity newEntity = persistence.update(califEntity);
        LOGGER.log(Level.INFO, "Finished update on score with id = {0}", newEntity.getId());
        return newEntity;
    }
    
    public void deleteCalificacion(Long califId) throws BusinessLogicException{
        if(califId == null || persistence.find(califId) == null)
            throw new BusinessLogicException("calificacion invalida al eliminar");
        persistence.delete(califId);
    }
}
