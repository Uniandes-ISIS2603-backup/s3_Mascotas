/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaVentaEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author estudiante
 */
@Stateless
public class MascotaVentaPersistence {
    
        private static final Logger LOGGER = Logger.getLogger(MascotaVentaPersistence.class.getName());
        
         @PersistenceContext(unitName = "PetsUniandesPU")
         protected EntityManager em;
         
         public MascotaVentaEntity create (MascotaVentaEntity mascotaEntity){
            LOGGER.log(Level.INFO, "creating new MascotaVenta");
            em.persist(mascotaEntity);
            LOGGER.log(Level.INFO, "The new MascotaVenta was created");
            return mascotaEntity;
         }
         
         public MascotaVentaEntity find (Long  mascotaVentaId){
            return em.find(MascotaVentaEntity.class, mascotaVentaId);
         }
         
        public MascotaVentaEntity update(MascotaVentaEntity mascotaVentaEntity){
        LOGGER.log(Level.INFO, "Updating mascotaVenta with id={0}", mascotaVentaEntity.getId());
        return em.merge(mascotaVentaEntity);
    }
        
        public void delete(MascotaVentaEntity mascota){
            em.getTransaction();
            em.remove(mascota);
            em.getTransaction().commit();
        }

}
