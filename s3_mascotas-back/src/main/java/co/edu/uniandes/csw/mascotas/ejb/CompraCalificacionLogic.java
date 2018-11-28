/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
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
    
    public CalificacionEntity getCalificacion(Long compraId)
    {
        LOGGER.log(Level.INFO, "Retornando calificacion de la compra con id: {0}", compraId);
        return compraPersistence.find(compraId).getCalificacion();
    }
}
