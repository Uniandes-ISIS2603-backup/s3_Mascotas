/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaVentaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;

import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
import co.edu.uniandes.csw.mascotas.persistence.MascotaVentaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * 
 * Clase que implementa la conexión con la persistencia para la relación
 * entre la entidad Mascotaventa y Mascota
 *
 * @author Sebastian Mujica
 */
@Stateless
public class MascotaVentaToMascotaLogic {
    private static final Logger LOGGER = Logger.getLogger(MascotaAdopcionToMascotaLogic.class.getName());
    
    @Inject
    private MascotaPersistence mascotaPersistence;
    
    @Inject
    private MascotaVentaPersistence mascotaVentaPersistence;
    
    
    /**
     * Agregar una Mascota a MascotaVenta
     * 
     * @param mascotaVentaId El id de la mascotaVenta en la que se va a guardar la mascota.
     * @param  mascotaId El id de la mascota a guardar.
     * @return La Mascota creada.
     */
    public MascotaEntity addMascota(Long mascotaId, Long mascotaVentaId){
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una mascota a la mascotaVenta con id ={0}", mascotaVentaId);
        MascotaVentaEntity mascotaVentaEntity = mascotaVentaPersistence.find(mascotaVentaId);
        MascotaEntity mascotaEntity = mascotaPersistence.find(mascotaId);
        mascotaVentaEntity.setMascota(mascotaEntity);
        LOGGER.log(Level.INFO, "Termina el proceso de agregarle una mascota a la mascotaVenta con id={0}", mascotaVentaId);
        return mascotaEntity;
    }
    
    
    /**
     * Retorna una Mascota asociada a una mascotaVenta
     * 
     * @param mascotaId es el id de la mascota a buscar.
     * @param mascotaVentaId es el id de la mascotaVenta a buscar.
     * @return
     * @throws BusinessLogicException Si la mascota no se encuentra en la mascotaVenta
     */
    public MascotaEntity getMascota(Long mascotaId, Long mascotaVentaId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar la mascota con id ={0} de la mascotaVenta con id =" + mascotaVentaId, mascotaId);
        MascotaEntity mascotaEntity = mascotaVentaPersistence.find(mascotaVentaId).getMascota();
        if(mascotaEntity!=null){
            return mascotaEntity;
        }
        throw new BusinessLogicException("La mascota no está asociada a la mascotaVenta");
    }


}
