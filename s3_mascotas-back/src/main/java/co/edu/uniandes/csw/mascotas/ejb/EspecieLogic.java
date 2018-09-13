/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.EspecieEntity;
import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.EspeciePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author estudiante
 */
@Stateless
public class EspecieLogic 
{
    private static final Logger LOGGER = Logger.getLogger(EspecieLogic.class.getName());
    
    @Inject
    private EspeciePersistence persistence;
    
    public EspecieEntity crearEspecie(EspecieEntity entity) throws BusinessLogicException{
        LOGGER.info("Species creation process begins");
        // missing verifications
        EspecieEntity nuevaEntity = persistence.create(entity);
        LOGGER.info("Species creation finishes");
        return nuevaEntity;
    }
    
    public List<EspecieEntity> getEspecies() {
        LOGGER.info("Listing all species");
        List<EspecieEntity> lista = persistence.findAll();
        LOGGER.info("Species list created");
        return lista;
    }
    
    public EspecieEntity getSpecies(Long speciesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la especie con id = {0}", speciesId);
        EspecieEntity especieEntity = persistence.find(speciesId);
        if (especieEntity == null) {
            LOGGER.log(Level.SEVERE, "La especie con el id = {0} no existe", speciesId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la especie con id = {0}", speciesId);
        return especieEntity;
    }
    
    public EspecieEntity updateSpecies(Long speciesId, EspecieEntity especieEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la especie con id = {0}", speciesId);
        EspecieEntity newEspecieEntity = persistence.update(especieEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la especie con id = {0}", speciesId);
        return newEspecieEntity;
    }
    
    public void deleteEspecie(Long speciesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la especie con id = {0}", speciesId);
        List<RazaEntity> razas = getSpecies(speciesId).getRazas();
        if (razas != null && !razas.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar la especie con id = " + speciesId + " porque tiene razas asociadas");
        }
        persistence.delete(speciesId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la especie con id = {0}", speciesId);
    }
}
