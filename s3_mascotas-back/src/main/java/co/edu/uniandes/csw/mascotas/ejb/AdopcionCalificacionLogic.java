/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.entities.AdopcionEntity;
import co.edu.uniandes.csw.mascotas.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.mascotas.persistence.AdopcionPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estudiante
 */
@Stateless
public class AdopcionCalificacionLogic {
    private static final Logger LOGGER = Logger.getLogger(AdopcionCalificacionLogic.class.getName());
    
    @Inject
    private AdopcionPersistence adopcionPersistence;
    
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    public CalificacionEntity addCalificacion(Long adopcionId, Long calificacionId)
    {
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionId);
        AdopcionEntity adopcionEntity = adopcionPersistence.find(adopcionId);
        adopcionEntity.setCalificacion(calificacionEntity);
        return calificacionPersistence.find(calificacionId);
    }
    
    public CalificacionEntity getCalificacion(Long adopcionId)
    {
        return adopcionPersistence.find(adopcionId).getCalificacion();
    }
    
    public CalificacionEntity replaceCalificacion(Long adopcionId, CalificacionEntity pCalificacion)
    {
        AdopcionEntity adopcionEntity = adopcionPersistence.find(adopcionId);
        adopcionEntity.setCalificacion(pCalificacion);
        return adopcionPersistence.find(adopcionId).getCalificacion();
    }
    
    public void removeCalificacion(Long adopcionId, Long calificacionId)
    {
        AdopcionEntity adopcionEntity = adopcionPersistence.find(adopcionId);
        calificacionPersistence.delete(calificacionId);
        adopcionEntity.setCalificacion(null);
    }
}
