/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.EspecieEntity;
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
public class EspeciePersistence 
{
    private static final Logger LOGGER = Logger.getLogger(EspeciePersistence.class.getName());
    
    @PersistenceContext(unitName = "PetsUniandesPU")
    protected EntityManager em;
    
    public EspecieEntity create(EspecieEntity especieEntity){
        LOGGER.log(Level.INFO, "Creating new species");
        em.persist(especieEntity);
        LOGGER.log(Level.INFO, "Finishing creating new species");
        return especieEntity;
    }
    
    public List<EspecieEntity> findAll(){
        LOGGER.log(Level.INFO, "Consulting all species");
        Query q = em.createQuery("select u from EspecieEntity u");
        return q.getResultList();
    }
    
    public EspecieEntity find(Long especieId)
    {
        return em.find(EspecieEntity.class, especieId);
    }
    
    public EspecieEntity update(EspecieEntity especieEntity)
    {
        LOGGER.log(Level.INFO, "Updating species with id={0}", especieEntity.getId());
        return em.merge(especieEntity);
    }
    
    public void delete(Long especieId){
        EspecieEntity especieEntity = em.find(EspecieEntity.class, especieId);
        especieEntity.setDeleted(Boolean.TRUE);
        em.merge(especieEntity);
    }
}
