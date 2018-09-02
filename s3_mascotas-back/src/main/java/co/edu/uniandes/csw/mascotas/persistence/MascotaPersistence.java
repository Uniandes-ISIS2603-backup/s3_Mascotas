/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

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
 * @author lemus
 */
@Stateless
public class MascotaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaPersistence.class.getName());
    
    @PersistenceContext(unitName = "PetsUniandesPU")
    protected EntityManager em;
    
    public MascotaEntity create(MascotaEntity mascotaEntity){
        LOGGER.log(Level.INFO, "Creating new Pet");
        em.persist(mascotaEntity);
        LOGGER.log(Level.INFO, "Finishing creating the new Pet");
        return mascotaEntity;
    }
    
    public List<MascotaEntity> findAll(){
        LOGGER.log(Level.INFO, "Consulting all pets");
        Query q = em.createQuery("select u from MascotaEntity u");
        return q.getResultList();
    }
    
    public MascotaEntity find(Long mascotasId){
        return em.find(MascotaEntity.class, mascotasId);
    }
    
    public MascotaEntity update(MascotaEntity mascotaEntity){
        LOGGER.log(Level.INFO, "Updating pet with id={0}", mascotaEntity.getId());
        return em.merge(mascotaEntity);
    }
    
    public void delete(Long mascotasId){
        em.remove(find(mascotasId));
    }
}
