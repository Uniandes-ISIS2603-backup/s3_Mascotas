/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaAdopcionPersistence;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;


/**
 * Clase que implementa la conexion con la persistencia para la relación
 * entre la entidad de MascotaAdopcion Y Mascota
 *
 * @author Sebastian Mujica
 */
@Stateless
public class MascotaAdopcionToMascotaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaAdopcionToMascotaLogic.class.getName());
    
    @Inject
    private MascotaPersistence mascotaPersistence;
    
    @Inject
    private MascotaAdopcionPersistence mascotaAdopcionPersistence;
    
    
    /**
     * Agregar una Mascota a AdopcionMascota
     * 
     * @param mascotaAdopcionId El id de la MascotaAdopcion en la que se va a guardar la mascota.
     * @param  mascotaId El id de la mascota a guardar.
     * @return La Mascota creada.
     */
    public MascotaEntity addMascota(Long mascotaId, Long mascotaAdopcionId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una mascota a la mascotaAdopcion con id ={0}", mascotaAdopcionId);
        MascotaEntity mascotaEntity = mascotaPersistence.find(mascotaId);
        if (mascotaEntity==null) {
            throw new BusinessLogicException("La Mascota no existe");
        }
        MascotaAdopcionEntity mascotaAdopcionEntity = mascotaAdopcionPersistence.find(mascotaAdopcionId);
        if(mascotaAdopcionEntity==null){
            throw new BusinessLogicException("La MascotaAdopcion no existe");
        }
        if(mascotaAdopcionEntity.getMascota()!=null){
            throw new BusinessLogicException("La MascotaAdopcion ya tiene una Mascota");
        }
        mascotaAdopcionEntity.setMascota(mascotaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una mascota a la mascotaAdopcion con id={0}", mascotaAdopcionId);
        return mascotaPersistence.find(mascotaId);
    }
    
    
    /**
     * Retorna una Mascota asociada a una mascotaAdopcion
     * 
     * @param mascotaId es el id de la mascota a buscar.
     * @param mascotaAdopcionId es el id de la mascotaAdopcion a buscar.
     * @return
     * @throws BusinessLogicException Si la mascota no se encuentra en la MascotaAdopcion
     */
    public MascotaEntity getMascota(Long mascotaId, Long mascotaAdopcionId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar la mascota con id ={0} de la mascotaAdopcion con id =" + mascotaAdopcionId, mascotaId);
        MascotaEntity mascotaEntity = mascotaAdopcionPersistence.find(mascotaAdopcionId).getMascota();
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar la mascota con id ={0} de la mascotaAdopcion con id =" + mascotaAdopcionId, mascotaId);
        if(mascotaEntity!=null){
            return mascotaEntity;
        }
        throw new BusinessLogicException("La Mascota no está asociada a la MascotaVenta");
    }
    
        public MascotaEntity replaceMascota(Long mascotaAdopcionId, MascotaEntity pMascotaEntity) throws BusinessLogicException
    {
        MascotaAdopcionEntity adopcionEntity = mascotaAdopcionPersistence.find(mascotaAdopcionId);
        if(adopcionEntity == null)
        {
            throw new BusinessLogicException("La MascotaAdopcion no existe");
        }
        if(pMascotaEntity == null || mascotaPersistence.find(pMascotaEntity.getId()) == null )
        {
            throw new BusinessLogicException("La mascota no es valida");
        }
        adopcionEntity.setMascota(pMascotaEntity);
        return mascotaAdopcionPersistence.find(mascotaAdopcionId).getMascota();
    }
    
    public void removeMascota(Long mascotaAdopcionId, Long mascotaId) throws BusinessLogicException
    {
        MascotaAdopcionEntity mascotaADopcionEntity = mascotaAdopcionPersistence.find(mascotaAdopcionId);
        if(mascotaADopcionEntity.getMascota()==null){
            throw new BusinessLogicException("La mascotaAdopcion no tiene una mascota asociada");
        }
        if(mascotaPersistence.find(mascotaId).equals(mascotaADopcionEntity.getMascota())){
            mascotaPersistence.find(mascotaId).setDeleted(Boolean.TRUE);
            mascotaADopcionEntity.setMascota(null);
        }
        

    }
    
}
