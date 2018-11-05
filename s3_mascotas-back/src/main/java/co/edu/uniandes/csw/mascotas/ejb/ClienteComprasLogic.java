/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
import co.edu.uniandes.csw.mascotas.entities.CompraEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ClientePersistence;
import co.edu.uniandes.csw.mascotas.persistence.CompraPersistence;
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
public class ClienteComprasLogic {
      private static final Logger LOGGER = Logger.getLogger(AdopcionCalificacionLogic.class.getName());
    
    @Inject
    private ClientePersistence clientePersistence;
    
    @Inject
    private CompraPersistence compraPersistence;
    
    public CompraEntity addCompra(Long compraId, Long clienteId) throws BusinessLogicException
    {
        CompraEntity compraEntity = compraPersistence.find(compraId);
        if(compraEntity == null){
            throw new BusinessLogicException("La compra no existe");
        }
        ClienteEntity clienteEntity = clientePersistence.find(clienteId);
        if(clienteEntity == null){
            throw new BusinessLogicException("El cliente no existe");
        }
        clienteEntity.getCompras().add(compraEntity);
        return compraPersistence.find(compraId);
    }
    public List<CompraEntity> getCompras(Long clienteId)
    {
        return clientePersistence.find(clienteId).getCompras();
    }  
    public CompraEntity getCompra(Long cliente, Long compra)throws BusinessLogicException{
        List<CompraEntity> compras = getCompras(cliente);
        CompraEntity c = compraPersistence.find(compra);
        int pos = compras.indexOf(c);
        if(pos>=0){
            return compras.get(pos);
        }
        else{
            throw new BusinessLogicException("Eso no existe.");
        }
    }
    
}
