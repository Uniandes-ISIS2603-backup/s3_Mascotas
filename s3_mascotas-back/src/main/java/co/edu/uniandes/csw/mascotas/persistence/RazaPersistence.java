/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lemus
 */
@Stateless
public class RazaPersistence {
    
    private static final Logger LOGGER = 
            Logger.getLogger(RazaPersistence.class.getName());
    
    @PersistenceContext(unitName = "TiendaMascotasPU")
    protected EntityManager em;
    
    public RazaEntity create(RazaEntity razaEntity){
        LOGGER.log(Level.INFO, "Creating new Race");
        em.persist(razaEntity);
        LOGGER.log(Level.INFO, "Finishing creating the new Race");
        return razaEntity;
    }
    
    public RazaEntity find(Long razasId){
        LOGGER.log(Level.INFO, "Looking for race with id={0}", razasId);
        return em.find(RazaEntity.class, razasId);
    }
    
    public void delete(Long razasId){
        em.remove(find(razasId));
    }
}
