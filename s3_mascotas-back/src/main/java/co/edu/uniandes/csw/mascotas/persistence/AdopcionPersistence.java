/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.AdopcionEntity;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Sebastian Gomez
 */
@Stateless
public class AdopcionPersistence {
    private static final Logger LOGGER = Logger.getLogger(AdopcionPersistence.class.getName());
    @PersistenceContext(unitName = "TiendaMascotasPU")
    protected EntityManager em;
    
}
