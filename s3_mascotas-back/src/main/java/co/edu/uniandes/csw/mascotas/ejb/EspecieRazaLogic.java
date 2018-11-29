/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

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
     * @param razasId
     * @return Entidad de la raza
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    public RazaEntity obtenerRaza(Long especiesId, Long razasId)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Consultando la raza id:{1} de la especie id: {0}", new Object[]{especiesId, razasId});
        List<RazaEntity> razas = obtenerRazas(especiesId);
        RazaEntity razaBuscada = razaPersistence.find(razasId);
        int posicion = razas.indexOf(razaBuscada);
        if (posicion >= 0 || !razaBuscada.getDeleted()) {
            return razas.get(posicion);
        }else{
            throw new BusinessLogicException("La raza no está asociada con la especie");
        }
    }
}
