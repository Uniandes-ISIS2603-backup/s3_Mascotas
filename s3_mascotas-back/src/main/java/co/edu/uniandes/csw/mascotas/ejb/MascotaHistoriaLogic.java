/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.HistoriaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.HistoriaPersistence;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Cristhian Peña
 */
@Stateless
public class MascotaHistoriaLogic 
{
    private static final Logger LOGGER = Logger.getLogger(MascotaHistoriaLogic.class.getName());
    
    @Inject
    private MascotaPersistence mascotaPersistence;
    
    /**
     * Metodo que retorna la historia de una mascota dada
     * @param mascotaId mascota dada
     * @return HistoriaEntity de la mascota dada
     */
    public HistoriaEntity obtenerHistoria(Long mascotaId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Getting the story of the pet with id = {0}", mascotaId);
        MascotaEntity mascotaEntity = mascotaPersistence.find(mascotaId);
        if(mascotaEntity == null){
            throw new BusinessLogicException("La mascota no existe");
        }
        HistoriaEntity historiaEntity = mascotaEntity.getHistoria();
        if(historiaEntity == null){
            throw new BusinessLogicException("La mascota no tiene una historia");
        }
        LOGGER.log(Level.INFO, "Finished getting the story with id = {0}", historiaEntity.getId());
        return historiaEntity;
    }
}
