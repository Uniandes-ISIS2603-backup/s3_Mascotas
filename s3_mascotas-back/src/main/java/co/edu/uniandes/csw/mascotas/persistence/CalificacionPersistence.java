/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
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
    
        public CalificacionEntity find(Long calificacionId){
        return em.find(CalificacionEntity.class, calificacionId);
    }
    
    public MascotaEntity update(MascotaEntity mascotaEntity){
        LOGGER.log(Level.INFO, "Updating pet with id={0}", mascotaEntity.getId());
        return em.merge(mascotaEntity);
    }
    
    public void delete(MascotaEntity mascota){
        em.getTransaction();
        em.remove(mascota);
        em.getTransaction().commit();
    }
}
