/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author pena
 */
@Stateless
public class CalificacionPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());
    
    @PersistenceContext(unitName = "TiendaMascotasPU")
    protected EntityManager em;
    
    public CalificacionEntity create(CalificacionEntity calificacionEntity){
        LOGGER.log(Level.INFO, "Creating score");
        em.persist(calificacionEntity);
        LOGGER.log(Level.INFO, "Finishing creating a new score");
        return calificacionEntity;
    }
    
    public List<CalificacionEntity> findAll(){
        LOGGER.log(Level.INFO, "Consulting all scores");
        Query q = em.createQuery("select u from CalificacionEntity u");
        return q.getResultList();
    }
}
