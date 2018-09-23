/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaVentaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia de una mascota de venta.
 *
 * @author Sebastian Mujica
 */
@Stateless
public class MascotaVentaPersistence {
    
        private static final Logger LOGGER = Logger.getLogger(MascotaVentaPersistence.class.getName());
        
         @PersistenceContext(unitName = "PetsUniandesPU")
         protected EntityManager em;
         
         /**
          * se crea una mascota de venta en la BD.
          * @param mascotaEntity
          * @return 
          */
         public MascotaVentaEntity create (MascotaVentaEntity mascotaEntity){
            LOGGER.log(Level.INFO, "creating new MascotaVenta");
            em.persist(mascotaEntity);
            LOGGER.log(Level.INFO, "The new MascotaVenta was created");
            return mascotaEntity;
         }
         
         /**
          * Se encuentra una mascota de venta en la BD.
          * @param mascotaVentaId
          * @return 
          */
         public MascotaVentaEntity find (Long  mascotaVentaId){
            return em.find(MascotaVentaEntity.class, mascotaVentaId);
         }
         
         /**
          * se actualiza una mascota de venta en la BD.
          * @param mascotaVentaEntity
          * @return 
          */
        public MascotaVentaEntity update(MascotaVentaEntity mascotaVentaEntity){
        LOGGER.log(Level.INFO, "The mascotaVenta is updated,  with id={0}", mascotaVentaEntity.getId());
        return em.merge(mascotaVentaEntity);
    }
        
        /*
        public void delete(Long mascotaVentaId){
            LOGGER.log(Level.INFO, "Borrando mascotaVenta con id = {0}", mascotaVentaId);
            MascotaVentaEntity entity = em.find(MascotaVentaEntity.class, mascotaVentaId);
            //em.remove(entity);
            entity.setDeleted(Boolean.TRUE);
            em.merge(entity);
            LOGGER.log(Level.INFO, "saliendo de borrar la mascotaVenta con id = {0}", mascotaVentaId);
        }
        */
        
        /**
         * Se  elimina una mascota de venta en la BD.
         * @param mascotaVentaId 
         */
        public void delete(Long mascotaVentaId){
            LOGGER.log(Level.INFO, "Borrando mascotaVenta con id = {0}", mascotaVentaId);
            MascotaVentaEntity entity = em.find(MascotaVentaEntity.class, mascotaVentaId);
            em.remove(entity);
            LOGGER.log(Level.INFO, "saliendo de borrar la mascotaVenta con id = {0}", mascotaVentaId);
        }

        /**
         * Se encuentran todas las mascotas de venta en la BD.
         * @return 
         */
        public List<MascotaVentaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las mascotasVenta");
        TypedQuery query = em.createQuery("select u from MascotaVentaEntity u", MascotaVentaEntity.class);
        return query.getResultList();
        }

}
