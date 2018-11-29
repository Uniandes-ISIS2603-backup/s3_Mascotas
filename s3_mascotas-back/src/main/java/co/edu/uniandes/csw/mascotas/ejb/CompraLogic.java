/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.CompraEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ClientePersistence;
import co.edu.uniandes.csw.mascotas.persistence.CompraPersistence;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Sebastián Gómez
 */
@Stateless
public class CompraLogic {
    private static final Logger LOGGER = Logger.getLogger(CompraLogic.class.getName());
    @Inject
    private CompraPersistence persistence;
    @Inject
    private MascotaPersistence mascPersistence;
    @Inject
    private ClientePersistence cliPersistence;
    
    public CompraEntity crearCompra(CompraEntity compraEnt)throws BusinessLogicException{
        LOGGER.info("Creacion de compra");
        if(compraEnt == null)
        {
            throw new BusinessLogicException("Compra vacia");
        }
        if(compraEnt.getMascota() == null){
            throw new BusinessLogicException("La mascota ingresada no es valida");
        }
        MascotaEntity masc = mascPersistence.find(compraEnt.getMascota().getId());
        if(masc == null){
            throw new BusinessLogicException("La mascota no existe");
        }
        if(masc.getCompra() != null)
        {
            throw new BusinessLogicException("La mascota ya fue comprada");
        }
        if(compraEnt.getCliente() == null || cliPersistence.find(compraEnt.getCliente().getId()) == null){
            throw new BusinessLogicException("El cliente no existe");
        }
        persistence.create(compraEnt);
        return compraEnt;
    }
    
    public List<CompraEntity> getCompras(){
        LOGGER.log(Level.INFO, "Buscando compras");
        List<CompraEntity> compras = persistence.findAll();
        return compras;
    }
    
    public CompraEntity getCompra(Long compraId){
        LOGGER.log(Level.INFO, "Looking for purchase with id = {0}", compraId);
        CompraEntity compraEntity = persistence.find(compraId);
        if (compraEntity == null) {
            LOGGER.log(Level.SEVERE, "The purchase with id = {0} does not exists", compraId);            
        }
        return compraEntity;
    }
    
    public CompraEntity updateCompra(Long compraId, CompraEntity compraEntity){
        LOGGER.log(Level.INFO, "Updating the pet with id = {0}", compraId);
        CompraEntity newEntity = persistence.update(compraEntity);
        return newEntity;
    }
}
