/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ClientePersistence;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Camilo Pinilla
 */
@Stateless
public class ClienteMascotaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteMascotaLogic.class.getName());

    @Inject
    private ClientePersistence clientePersistence;
    
    @Inject
    private MascotaPersistence mascotaPersistence;
    
    /**
     * Relaciona un cliente con su mascota (ya existentes)
     * @param clienteId
     * @param mascotasId
     * @return instancia de clienteEntity que fue asociada con la mascota
     */
    public ClienteEntity addMascota(Long clienteId, Long mascotasId) throws BusinessLogicException{
        
        ClienteEntity cliente = clientePersistence.find(clienteId);
        MascotaEntity mascota = mascotaPersistence.find(mascotasId);
        
        if(mascota == null || mascota.getDeleted()){
            throw new BusinessLogicException("The pet doesn't exist.");
        }
        cliente.getMascotas().add(mascota);
        mascota.setCliente(cliente);
        clientePersistence.update(cliente);
        
        return clientePersistence.find(clienteId);
    }
    
    /**
     * Retorna la colección de mascotas asociadas a un cliente
     * @param clienteId
     * @return List de MascotaEntity
     */
    public List<MascotaEntity> getMascotas(Long clienteId){
        return clientePersistence.find(clienteId).getMascotas();
    }
    
    /**
     * Retorna la mascota asociada a un cliente identificado por el parámetro
     * @param clienteId
     * @param mascotasId
     * @return Entidad de la mascota
     */
    public MascotaEntity getMascota(Long clienteId, Long mascotasId)throws BusinessLogicException{
        List<MascotaEntity> mascotas = getMascotas(clienteId);
        MascotaEntity mascotaBuscada = mascotaPersistence.find(mascotasId);
        int posicion = mascotas.indexOf(mascotaBuscada);
        if (posicion >= 0 || !mascotaBuscada.getDeleted()) {
            return mascotas.get(posicion);
        }else{
            throw new BusinessLogicException("La mascota no está asociada con el cliente");
        }
    }
    
    /**
     * Elimina la relación entre una mascota y un cliente (ya existentes)
     * @param clienteId
     * @param mascotasId 
     */
    public void removeMascota(Long clienteId, Long mascotasId){
        ClienteEntity c = clientePersistence.find(clienteId);
        MascotaEntity m = mascotaPersistence.find(mascotasId);
        c.getMascotas().remove(m);
    }
    
}
