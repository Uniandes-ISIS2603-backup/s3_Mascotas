/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.CompraEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.CompraPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Juan Sebastián Gómez
 */
public class CompraLogic {
    private static final Logger LOGGER = Logger.getLogger(CompraLogic.class.getName());
    @Inject
    private CompraPersistence persistence;
    public CompraEntity crearCompra(CompraEntity compraEnt)throws BusinessLogicException{
        LOGGER.info("Creacion de compra");
        persistence.create(compraEnt);
        return compraEnt;
    }
        public List<CompraEntity> getCompras(){
        LOGGER.log(Level.INFO, "Buscando compras");
        List<CompraEntity> compras = persistence.findAll();
        LOGGER.log(Level.INFO, "Ending search");
        return compras;
    }
    
    public CompraEntity getCompra(Long compraId){
        LOGGER.log(Level.INFO, "Looking for purchase with id = {0}", compraId);
        CompraEntity compraEntity = persistence.find(compraId);
        if (compraEntity == null) {
            LOGGER.log(Level.SEVERE, "The purchase with id = {0} does not exists", compraId);            
        }
        LOGGER.log(Level.INFO, "Ending search for the pet with id {0}", compraId);
        return compraEntity;
    }
    
    public CompraEntity updateCompra(Long compraId, CompraEntity compraEntity) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Updating the pet with id = {0}", compraId);
        CompraEntity newEntity = persistence.update(compraEntity);
        LOGGER.log(Level.INFO, "Finished update on pet with id = {0}", compraEntity.getId());
        return newEntity;
    }
}
