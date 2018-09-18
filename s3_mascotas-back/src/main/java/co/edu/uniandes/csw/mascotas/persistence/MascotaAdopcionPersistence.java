/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class MascotaAdopcionPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaAdopcionPersistence.class.getName());

            
    @PersistenceContext(unitName = "PetsUniandesPU")
    protected EntityManager em;
    
    public MascotaAdopcionEntity create(MascotaAdopcionEntity mascotaAdopcionEntity){
        LOGGER.log(Level.INFO, "Creating new mascotaAdopcion");
        em.persist(mascotaAdopcionEntity);
        LOGGER.log(Level.INFO, "Finishing creating the new mascotaAdopcion");
        return mascotaAdopcionEntity;
    }
    
    public MascotaAdopcionEntity find(Long mascotaAdopcionId){
        return em.find(MascotaAdopcionEntity.class, mascotaAdopcionId);
    }
    
    public MascotaAdopcionEntity update(MascotaAdopcionEntity mascotaAdopcionEntity){
        LOGGER.log(Level.INFO, "Updating mascotaAdopcion with id={0}", mascotaAdopcionEntity.getId());
        return em.merge(mascotaAdopcionEntity);
    }
    
    public void delete(Long mascotaAdopcionId){
        LOGGER.log(Level.INFO, "Borrando mascotaAdopcion con id = {0}", mascotaAdopcionId);
        MascotaAdopcionEntity entity = em.find(MascotaAdopcionEntity.class, mascotaAdopcionId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la MascotaAdopcion con id = {0}", mascotaAdopcionId);
    }
    
    public List<MascotaAdopcionEntity> findAll(){
        LOGGER.log(Level.INFO, "Consultando todas las mascotasAdopcion");
        TypedQuery query = em.createQuery("select u from MascotaAdopcionEntity u", MascotaAdopcionEntity.class);
        return query.getResultList();
    }
    
    
}
