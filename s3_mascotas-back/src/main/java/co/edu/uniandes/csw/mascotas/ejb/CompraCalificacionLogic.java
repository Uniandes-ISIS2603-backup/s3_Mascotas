/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.entities.CompraEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.mascotas.persistence.CompraPersistence;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class CompraCalificacionLogic {
       private static final Logger LOGGER = Logger.getLogger(AdopcionCalificacionLogic.class.getName());
    
    @Inject
    private CompraPersistence compraPersistence;
    
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    public CalificacionEntity addCalificacion(Long compraId, Long calificacionId) throws BusinessLogicException
    {
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionId);
        if(calificacionEntity == null){
            throw new BusinessLogicException("La calificacion no existe");
        }
        CompraEntity compraEntity = compraPersistence.find(compraId);
        if(compraEntity == null){
            throw new BusinessLogicException("La compra no existe");
        }
        compraEntity.setCalificacion(calificacionEntity);
        return calificacionPersistence.find(calificacionId);
    }
    
    public CalificacionEntity getCalificacion(Long adopcionId)
    {
        return compraPersistence.find(adopcionId).getCalificacion();
    }
    
    public CalificacionEntity replaceCalificacion(Long adopcionId, CalificacionEntity pCalificacion) throws BusinessLogicException
    {
        CompraEntity compraEntity = compraPersistence.find(adopcionId);
        if(compraEntity == null)
        {
            throw new BusinessLogicException("La adopcion no existe");
        }
        if(pCalificacion == null || calificacionPersistence.find(pCalificacion.getId()) == null )
        {
            throw new BusinessLogicException("La calificacion no es valida");
        }
        compraEntity.setCalificacion(pCalificacion);
        return compraPersistence.find(adopcionId).getCalificacion();
    }
    
    public void removeCalificacion(Long adopcionId, Long calificacionId)
    {
        CompraEntity compraEntity = compraPersistence.find(adopcionId);
        calificacionPersistence.delete(calificacionId);
        compraEntity.setCalificacion(null);
    }
}