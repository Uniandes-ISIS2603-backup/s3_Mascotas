/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.AdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
import co.edu.uniandes.csw.mascotas.entities.CompraEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.AdopcionPersistence;
import co.edu.uniandes.csw.mascotas.persistence.ClientePersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Sebastian Gomez, Camilo Pinilla
 * 
 */
@Stateless
public class ClienteAdopcionLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteAdopcionLogic.class.getName());
    
    @Inject
    private ClientePersistence clientePersistence;
    
    @Inject
    private AdopcionPersistence adopcionPersistence;
    
    public AdopcionEntity addAdopcion(Long adopcionId, Long clienteId) throws BusinessLogicException
    {
        AdopcionEntity adopcionEntity = adopcionPersistence.find(adopcionId);
        
        if(adopcionEntity == null){
            throw new BusinessLogicException("La adopcion no existe");
        }
        
        ClienteEntity clienteEntity = clientePersistence.find(clienteId);
        
        if(clienteEntity == null){
            throw new BusinessLogicException("El cliente no existe");
        }
        clienteEntity.getAdopciones().add(adopcionEntity);
        adopcionEntity.setCliente(clienteEntity);
        clientePersistence.update(clienteEntity);
        return adopcionPersistence.find(adopcionId);
    }
    
    public List<AdopcionEntity> getAdopciones(Long clienteId)
    {
        return clientePersistence.find(clienteId).getAdopciones();
    }   
    
}
