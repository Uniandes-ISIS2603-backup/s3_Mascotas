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
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException, 
     * en caso de que existan problemas para asociar la mascota, puede tenerse el
     * caso de que la mascota no ecsita, la mascota de adopción no exista, o que 
     * la mascota de adopción ya tenga una mascota asociada.
     */
    public MascotaEntity addMascota(Long mascotaId, Long mascotaVentaId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una mascota a la mascotaVenta con id ={0}", mascotaVentaId);
        MascotaVentaEntity mascotaVentaEntity = mascotaVentaPersistence.find(mascotaVentaId);
        if(mascotaVentaEntity==null){
            throw new BusinessLogicException("no existe la mascotaVenta");
        }
        MascotaEntity mascotaEntity = mascotaPersistence.find(mascotaId);
        if(mascotaEntity==null){
            throw new BusinessLogicException("la mascota que se intenta asociar no existe");
        }
        if(mascotaVentaEntity.getMascota()!=null){
            throw new BusinessLogicException("la mascotaVenta ya tiene una mascota asociada");
        }
        mascotaVentaEntity.setMascota(mascotaEntity);
        LOGGER.log(Level.INFO, "Termina el proceso de agregarle una mascota a la mascotaVenta con id={0}", mascotaVentaId);
        return mascotaPersistence.find(mascotaId);
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
    
    
    
    /**
     * Se reemplaza una mascota en una mascota de venta.
     * @param mascotaVentaId el ide de la mascota de venta.
     * @param pMascotaEntity el id de la mascota que se va a actualizar en la 
     * mascota de venta.
     * @return
     * @throws BusinessLogicException, en caso de que la mascota n oexista,
     * o que no sea válida.
     */
    public MascotaEntity replaceMascota(Long mascotaVentaId, MascotaEntity pMascotaEntity) throws BusinessLogicException
    {
        MascotaVentaEntity ventaEntity = mascotaVentaPersistence.find(mascotaVentaId);
        if(ventaEntity == null)
        {
            throw new BusinessLogicException("La MascotaVenta no existe");
        }
        if(pMascotaEntity == null || mascotaPersistence.find(pMascotaEntity.getId()) == null )
        {
            throw new BusinessLogicException("La mascota no es valida");
        }
        ventaEntity.setMascota(pMascotaEntity);
        return mascotaVentaPersistence.find(mascotaVentaId).getMascota();
    }
    
    /**
     * Se elimina una mascota de una mascota de venta.
     * @param mascotaVentaId el ide de la mascota de venta.
     * @param mascotaId el id de la mascota que va a ser eliminada.
     * @throws BusinessLogicException, en casod e que la mascota de venta
     * no tenga una mascota asociada, o que la mascota asociada no corersponda 
     * a la mascota que se deseaa eliminar.
     */
    public void removeMascota(Long mascotaVentaId, Long mascotaId) throws BusinessLogicException
    {
        MascotaVentaEntity mascotaVentaEntity = mascotaVentaPersistence.find(mascotaVentaId);
        if(mascotaVentaEntity.getMascota()==null){
            throw new BusinessLogicException("La MascotaVenta no tiene una mascota asociada");
        }
        if(mascotaPersistence.find(mascotaId).equals(mascotaVentaEntity.getMascota())){
            mascotaPersistence.find(mascotaId).setDeleted(Boolean.TRUE);
            mascotaVentaEntity.setMascota(null);
        }
        

    }


}
