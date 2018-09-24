/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
import co.edu.uniandes.csw.mascotas.entities.HistoriaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ClientePersistence;
import co.edu.uniandes.csw.mascotas.persistence.HistoriaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Camilo Pinilla
 */
@Stateless
public class ClienteHistoriaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteHistoriaLogic.class.getName());

    @Inject
    private ClientePersistence clientePersistence;
    
    @Inject
    private HistoriaPersistence historiaPersistence;
    
    /**
     * Relaciona una historia con su mascota (ya existentes)
     * @param clienteId
     * @param historiasId
     * @return instancia de clienteEntity que fue asociada con la mascota
     */
    public ClienteEntity addHistoria(Long clienteId, Long historiasId) throws BusinessLogicException{
        
        ClienteEntity cliente = clientePersistence.find(clienteId);
        HistoriaEntity historia = historiaPersistence.find(historiasId);
        
        if(historia == null || historia.getDeleted()){
            throw new BusinessLogicException("The pet doesn't exist.");
        }
        cliente.getHistorias().add(historia);
        
        return clientePersistence.find(clienteId);
    }
    
    /**
     * Retorna la colección de historias asociadas a un cliente
     * @param historiaId
     * @return List de HistoriaEntity
     */
    public List<HistoriaEntity> getHistorias(Long historiaId){
        return clientePersistence.find(historiaId).getHistorias();
    }
    
    /**
     * Retorna la historia asociada a un cliente identificado por el parámetro
     * @param clienteId
     * @param historiaId
     * @return Entidad de la historia
     */
    public HistoriaEntity getHistoria(Long clienteId, Long historiaId)throws BusinessLogicException{
        List<HistoriaEntity> historias = getHistorias(clienteId);
        HistoriaEntity historiaBuscada = historiaPersistence.find(historiaId);
        int posicion = historias.indexOf(historiaBuscada);
        if (posicion >= 0 || !historiaBuscada.getDeleted()) {
            return historias.get(posicion);
        }else{
            throw new BusinessLogicException("La historia no está asociada con el cliente");
        }
    }
    
    /**
     * Elimina la relación entre una mascota y un cliente (ya existentes)
     * @param clienteId
     * @param mascotasId 
     */
    public void removeMascota(Long clienteId, Long mascotasId){
        ClienteEntity c = clientePersistence.find(clienteId);
        HistoriaEntity m = historiaPersistence.find(mascotasId);
        c.getHistorias().remove(m);
    }
    
}
