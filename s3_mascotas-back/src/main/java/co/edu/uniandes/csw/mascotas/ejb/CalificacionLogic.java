/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.CalificacionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author pena
 */
@Stateless
public class CalificacionLogic 
{
    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());
    
    @Inject
    private CalificacionPersistence persistence;
    
    public CalificacionEntity crearCalificacion(CalificacionEntity entrada) throws BusinessLogicException
    {
        LOGGER.info("A new score is being made");
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
}
