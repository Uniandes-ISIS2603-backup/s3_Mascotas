/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaVentaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaVentaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sebastian Mujica
 */
@Stateless
public class MascotaVentaLogic {
    
    private static final Logger LOOGER = Logger.getLogger(MascotaVentaLogic.class.getName());
    
    @Inject
    private MascotaVentaPersistence persistence;
    
     public MascotaVentaEntity crearMascotaVenta(MascotaVentaEntity entity) throws BusinessLogicException{
        LOOGER.info("MascotaVenta creation process begins");
        //if(persistence.find(entity.getId())!=null){
        //    throw new BusinessLogicException("No se puede crear una especie con los mismos documentos pedegree");
        //}
        persistence.create(entity);
        LOOGER.info("MascotaVenta was succesfully created");
        return entity;
    }
     
     public MascotaVentaEntity getMascotaVenta(Long mascotaVentaId){
        LOOGER.log(Level.INFO, "Looking for pet with id = {0}", mascotaVentaId);
        MascotaVentaEntity mascotaventaEntity= persistence.find(mascotaVentaId);
        if (mascotaventaEntity == null) {
            LOOGER.log(Level.SEVERE, "The pet with id = {0} does not exists", mascotaVentaId);            
        }
        LOOGER.log(Level.INFO, "Ending search for the pet with id {0}", mascotaVentaId);
        return mascotaventaEntity;
     }
     
     public MascotaVentaEntity updateMascotaVenta(long mascotaVentaId, MascotaVentaEntity mascotaVentaEntity) throws BusinessLogicException{
         LOOGER.log(Level.INFO, "Updating the pet with id ={0}", mascotaVentaId);
         if(persistence.find(mascotaVentaId).getDocumentosPedegree().equalsIgnoreCase(mascotaVentaEntity.getDocumentosPedegree())){
             throw new BusinessLogicException("No se puede actualizar los documentos, ya existe una mascota con estos documentos");
         }
         MascotaVentaEntity nuevaMascotaVentaEntity = persistence.update(mascotaVentaEntity);
         LOOGER.log(Level.INFO, "Finished update on pet with id ={0}", mascotaVentaEntity.getId());
         return nuevaMascotaVentaEntity;
     }
    
     public void deleteMascotaVenta(Long mascotaVentaId) throws BusinessLogicException{
         MascotaVentaEntity entity = persistence.find(mascotaVentaId);
         if(entity.getMascota()!=null)
         {
             throw new BusinessLogicException("No se puede eliminar la mascotaVenta, ya que tiene una mascota asociada");
         }
         persistence.delete(mascotaVentaId);
     }
    
     public List<MascotaVentaEntity> getMascotasVenta(){
         LOOGER.log(Level.INFO, "Inicia proceso de consultar todas las MascotasVenta");
         List<MascotaVentaEntity> mascotasVenta= persistence.findAll();
         LOOGER.log(Level.INFO, "Termina proceso de consultar todas las MascotasVenta");
         return mascotasVenta;
     }
    
}
