/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ClientePersistence;
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
public class ClienteLogic {
    
    // Debug logger
    private static final Logger LOOGER = Logger.getLogger(ClienteLogic.class.getName());
    
    @Inject
    private ClientePersistence persistence;
    
    public ClienteEntity createCliente(ClienteEntity entity) throws BusinessLogicException{
        LOOGER.info("Customer creation process begins");
        if(persistence.findByCorreo(entity.getCorreo()) != null){
            throw new BusinessLogicException("El Teléfono ya existe");
        }
        persistence.create(entity);
        LOOGER.info("Customer creation finishes");
        return entity;
    }
    
    public List<ClienteEntity> getClientes(){
        LOOGER.log(Level.INFO, "Searching for customers");
        List<ClienteEntity> clientes = persistence.findAll();
        LOOGER.log(Level.INFO, "Ending search");
        return clientes;
    }
    
    public ClienteEntity getCliente(Long clienteId){
        LOOGER.log(Level.INFO, "Looking for customer with id = {0}", clienteId);
        ClienteEntity clienteEntity = persistence.find(clienteId);
        if (clienteEntity == null) {
            LOOGER.log(Level.SEVERE, "The customer with id = {0} does not exists", clienteId);            
        }
        LOOGER.log(Level.INFO, "Ending search for the customer with id {0}", clienteId);
        return clienteEntity;
    }
    
    public ClienteEntity updateCliente(Long clienteId, ClienteEntity clienteEntity) throws BusinessLogicException{
        LOOGER.log(Level.INFO, "Updating the customer with id = {0}", clienteId);
        
        
        if(persistence.findByCorreo(clienteEntity.getCorreo()) != null){
            throw new BusinessLogicException("El Teléfono ya existe");
        }
        
        ClienteEntity newEntity = persistence.update(clienteEntity);
        LOOGER.log(Level.INFO, "Finished update on customer with id = {0}", clienteEntity.getId());
        return newEntity;
    }
    
    public void deleteCliente(ClienteEntity cliente){
        persistence.delete(cliente);
    }
}
