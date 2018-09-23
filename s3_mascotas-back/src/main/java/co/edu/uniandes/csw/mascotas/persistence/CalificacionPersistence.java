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
 * @author Cristhian Peña
 */
@Stateless
public class CalificacionPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());
    
    @PersistenceContext(unitName = "PetsUniandesPU")
    protected EntityManager em;
    
    public CalificacionEntity create(CalificacionEntity calificacionEntity){
        LOGGER.log(Level.INFO, "Creating new score");
        em.persist(calificacionEntity);
        LOGGER.log(Level.INFO, "Finishing creating new score");
        return calificacionEntity;
    }
    
    public List<CalificacionEntity> findAll(){
        LOGGER.log(Level.INFO, "Consulting all scores");
        Query q = em.createQuery("select u from CalificacionEntity u");
        return q.getResultList();
    }
    
    public CalificacionEntity find(Long calificacionId)
    {
        return em.find(CalificacionEntity.class, calificacionId);
    }
    
    public CalificacionEntity update(CalificacionEntity calificacionEntity)
    {
        LOGGER.log(Level.INFO, "Updating score with id={0}", calificacionEntity.getId());
        return em.merge(calificacionEntity);
    }
    
    public void delete(Long calificacionId){
        CalificacionEntity calificacionEntity = em.find(CalificacionEntity.class, calificacionId);
        em.remove(calificacionEntity);
    }
}
