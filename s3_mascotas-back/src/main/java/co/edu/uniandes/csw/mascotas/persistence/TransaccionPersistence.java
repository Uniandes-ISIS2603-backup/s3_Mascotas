/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.TransaccionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author Sebastian Mujica
 */
@Stateless
public class TransaccionPersistence {
    
    
    private static final Logger LOGGER = Logger.getLogger(TransaccionPersistence.class.getName());
    
    @PersistenceContext(unitName = "TiendaMascotasPU")
    protected EntityManager em;
    
    public TransaccionEntity create(TransaccionEntity transaccionEntity){
        LOGGER.log(Level.INFO, "Creating a new transacction");
        em.persist(transaccionEntity);
        LOGGER.log(Level.INFO, "Finishing the creation of the transacction");
        return transaccionEntity;
    }

    public List<TransaccionEntity> findAll() {
        LOGGER.log(Level.INFO, "Looking up for all transacctions");
        Query q = em.createQuery("select u from TransaccionEntity u");
        return q.getResultList();
    }

    public TransaccionEntity find(Long transaccionId) {
    return em.find(TransaccionEntity.class, transaccionId);
    }

    public void delete(Long transaccionId) {
        em.remove(find(transaccionId));
    }
    
    
    
    
    
}
