/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.HistoriaEntity;
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
public class HistoriaPersistence {
    
    // Debug logger
    private static final Logger LOGGER = Logger.getLogger(HistoriaPersistence.class.getName());
    
    @PersistenceContext(unitName = "PetsUniandesPU")
    protected EntityManager em;
    
    public HistoriaEntity create(HistoriaEntity historyEntity){
        LOGGER.log(Level.INFO, "Creating new History");
        em.persist(historyEntity);
        LOGGER.log(Level.INFO, "Finishing creating the new History");
        return historyEntity;
    }
    
    public List<HistoriaEntity> findAll(){
        LOGGER.log(Level.INFO, "Consulting all histories");
        Query q = em.createQuery("select u from HistoriaEntity u");
        return q.getResultList();
    }
    
    public HistoriaEntity find(Long clienteId){
        return em.find(HistoriaEntity.class, clienteId);
    }
    
    public HistoriaEntity update(HistoriaEntity historiaEntity){
        LOGGER.log(Level.INFO, "Updating customer with id={0}", historiaEntity.getId());
        return em.merge(historiaEntity);
    }
    
    public void delete(HistoriaEntity cliente){
        em.getTransaction();
        em.remove(cliente);
        em.getTransaction().commit();
    }
}
