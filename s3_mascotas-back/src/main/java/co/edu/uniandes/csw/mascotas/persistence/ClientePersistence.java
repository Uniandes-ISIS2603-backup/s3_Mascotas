/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Camilo Pinilla
 */
@Stateless
public class ClientePersistence {
    
    // Debug logger
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());
    
    @PersistenceContext(unitName = "PetsUniandesPU")
    protected EntityManager em;
    
    public ClienteEntity create(ClienteEntity clienteEntity){
        LOGGER.log(Level.INFO, "Creating new Customer");
        em.persist(clienteEntity);
        LOGGER.log(Level.INFO, "Finishing creating the new Customer");
        return clienteEntity;
    }
    
    public List<ClienteEntity> findAll(){
        LOGGER.log(Level.INFO, "Consulting all customers");
        Query q = em.createQuery("select u from ClienteEntity u");
        return q.getResultList();
    }
    
    public ClienteEntity find(Long clienteId){
        return em.find(ClienteEntity.class, clienteId);
    }
    
    public ClienteEntity update(ClienteEntity clienteEntity){
        LOGGER.log(Level.INFO, "Updating customer with id={0}", clienteEntity.getId());
        return em.merge(clienteEntity);
    }
    
    public void delete(ClienteEntity cliente){
        em.getTransaction();
        em.remove(cliente);
        em.getTransaction().commit();
    }
}
