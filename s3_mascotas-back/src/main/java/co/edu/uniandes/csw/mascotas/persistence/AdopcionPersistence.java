/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.AdopcionEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Sebastian Gomez
 */
@Stateless
public class AdopcionPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(AdopcionPersistence.class.getName());
    @PersistenceContext(unitName = "PetsUniandesPU")
    protected EntityManager em;
    public AdopcionEntity create(AdopcionEntity adopEn){
        LOGGER.log(Level.INFO, "Crear una adopción nueva");
        em.persist(adopEn);
        LOGGER.log(Level.INFO, "Saliendo de crear una adopción nueva");
        return adopEn;
    }
    
    public AdopcionEntity find(Long adopcionId){
        return em.find(AdopcionEntity.class, adopcionId);
    }
    
}
