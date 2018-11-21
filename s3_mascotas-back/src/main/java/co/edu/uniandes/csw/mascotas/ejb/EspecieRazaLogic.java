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
import co.edu.uniandes.csw.mascotas.persistence.RazaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lemus
 */
@Stateless
public class EspecieRazaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(RazaMascotaLogic.class.getName());
    
    @Inject
    private EspeciePersistence especiePersistence;
    
    @Inject
    private RazaPersistence razaPersistence;
    
    @Inject
    private RazaLogic razaLogic;      
    
        /**
     * Relaciona una raza con su especie (ya existentes)
     * @param especiesId
     * @param mascotasId
     * @return instancia de Especie que fue asociada con la raza
     */
    public EspecieEntity addRaza(Long especiesId, RazaEntity razaEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Creating raza linked with especie id = {0}", especiesId);
        RazaEntity raza = razaLogic.crearRaza(razaEntity);
        EspecieEntity especie = especiePersistence.find(especiesId);
        if(raza == null || raza.getDeleted()){
            throw new BusinessLogicException("The race doesn't exist.");
        }
        especie.getRazas().add(raza);
        raza.setEspecie(especie);
        razaPersistence.update(raza);
        return especie;
    }
    
    /**
     * Retorna la colección de razas asociadas a una especie concreta
     * @param especiesId
     * @return List de RazaEntity
     */
    public List<RazaEntity> obtenerRazas(Long especiesId){
        return especiePersistence.find(especiesId).getRazas();
    }
    
    /**
     * Retorna la raza asociada a la especie identificada por el parámetro
     * @param especiesId
     * @param mascotasId
     * @return Entidad de la raza
     */
    public RazaEntity obtenerRaza(Long especiesId, Long razasId)throws BusinessLogicException{
        List<RazaEntity> razas = obtenerRazas(especiesId);
        RazaEntity razaBuscada = razaPersistence.find(razasId);
        int posicion = razas.indexOf(razaBuscada);
        if (posicion >= 0 || !razaBuscada.getDeleted()) {
            return razas.get(posicion);
        }else{
            throw new BusinessLogicException("La raza no está asociada con la especie");
        }
    }
    
    /**
     * Elimina la relación entre una raza y una especie (ya existentes)
     * @param especiesId
     * @param mascotasId 
     */
    public void removerRaza(Long especiesId, Long razasId){
        RazaEntity r = razaPersistence.find(razasId);
        EspecieEntity e = especiePersistence.find(especiesId);
        e.getRazas().remove(r);
    }
}
