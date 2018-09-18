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
import java.util.List;
import javax.ejb.Stateless;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;


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
    public MascotaEntity addMascota(Long mascotaId, Long mascotaAdopcionId){
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una mascota a la mascotaAdopcion con id ={0}", mascotaAdopcionId);
        MascotaAdopcionEntity mascotaAdopcionEntity = mascotaAdopcionPersistence.find(mascotaAdopcionId);
        MascotaEntity mascotaEntity = mascotaPersistence.find(mascotaId);
        mascotaAdopcionEntity.setMascota(mascotaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una mascota a la mascotaAdopcion con id={0]", mascotaAdopcionId);
        return mascotaEntity;
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
        throw new BusinessLogicException("El libro no está asociado a la editorial");
    }
    
    
    
}
