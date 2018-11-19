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
import java.util.List;
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
    
    @Inject
    private HistoriaPersistence historiaPersistence;
    
    @Inject
    private HistoriaLogic historiaLogic;
    
    /**
     * Relaciona una historia con una mascota ya existente
     * @param mascotaId
     * @param HistoriaEntity
     * @return instancia de Mascota que fue asociada con la historia
     */
    public MascotaEntity añadirHistoria(Long mascotaId, HistoriaEntity historiaEntity) throws BusinessLogicException
    {
        LOGGER.info("Creating historiaEntity related with mascotaId=" + mascotaId);
        MascotaEntity mascota = mascotaPersistence.find(mascotaId);
        if(mascota.getHistoria() != null)
        {
            throw new BusinessLogicException("La mascota ya tiene historia");
        }
        HistoriaEntity historia = historiaLogic.createHistoria(historiaEntity);
        mascota.setHistoria(historia);
        historia.setMascota(mascota);
        historiaPersistence.update(historiaEntity);
        LOGGER.info("Finishing creating historiaEntity");
        return mascota;
    }
    
    /**
     * Metodo que retorna la historia de una mascota dada
     * @param mascotaId mascota dada
     * @return HistoriaEntity de la mascota dada
     */
    public HistoriaEntity obtenerHistoria(Long mascotaId)
    {
        return mascotaPersistence.find(mascotaId).getHistoria();
    }
    
    /**
     * Metodo que elimina la historia de una mascota
     * @param mascotaId id de la mascota
     */
    public void removeHistoria(Long mascotaId)
    {
        MascotaEntity mascota = mascotaPersistence.find(mascotaId);
        mascota.setHistoria(null);
    }
}
