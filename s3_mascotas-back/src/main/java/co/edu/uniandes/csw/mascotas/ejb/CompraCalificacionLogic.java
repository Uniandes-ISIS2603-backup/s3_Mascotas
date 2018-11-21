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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class CompraCalificacionLogic {
       private static final Logger LOGGER = Logger.getLogger(CompraCalificacionLogic.class.getName());
    
    @Inject
    private CompraPersistence compraPersistence;
    
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    @Inject
    private CalificacionLogic calificacionLogic;
    
    public CalificacionEntity addCalificacion(Long compraId, CalificacionEntity calificacion) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inserting score into compra id = {0}", compraId);
        CompraEntity compraEntity = compraPersistence.find(compraId);
        if(compraEntity == null){
            throw new BusinessLogicException("La compra no existe");
        }
        CalificacionEntity calificacionEntity = calificacionLogic.crearCalificacion(calificacion);
        compraEntity.setCalificacion(calificacionEntity);
        compraPersistence.update(compraEntity);
        return calificacionPersistence.find(calificacionEntity.getId());
    }
    
    public CalificacionEntity getCalificacion(Long compraId)
    {
        return compraPersistence.find(compraId).getCalificacion();
    }
    
    public CalificacionEntity replaceCalificacion(Long compraId, CalificacionEntity pCalificacion) throws BusinessLogicException
    {
        CompraEntity compraEntity = compraPersistence.find(compraId);
        if(compraEntity == null)
        {
            throw new BusinessLogicException("La compra no existe");
        }
        if(pCalificacion == null || calificacionPersistence.find(pCalificacion.getId()) == null )
        {
            throw new BusinessLogicException("La calificacion no es valida");
        }
        compraEntity.setCalificacion(pCalificacion);
        return compraPersistence.find(compraId).getCalificacion();
    }
    
    public void removeCalificacion(Long compraId, Long calificacionId)
    {
        LOGGER.log(Level.INFO, "Deleting score from compra id = {0}", compraId);
        CompraEntity compraEntity = compraPersistence.find(compraId);
        calificacionPersistence.delete(calificacionId);
        compraEntity.setCalificacion(null);
    }
}
