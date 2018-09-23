/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaAdopcionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clae que permite manejar la base de datos y administrar la información 
 * de una mascota de adopción e implementar las reglas de negocio.
 *
 * @author Sebastian Mujica
 */
@Stateless
public class MascotaAdopcionLogic {
    
    private static final Logger LOOGER = Logger.getLogger(MascotaAdopcionLogic.class.getName());
    
    @Inject
    private MascotaAdopcionPersistence persistence;
    
    /**
     * Se crea una nueva mascota de adopción.
     * @param entity
     * @return MascotaAdopcionEntity, la mascota de adopcion creada.
     * @throws BusinessLogicException 
     */
    public MascotaAdopcionEntity crearMascotaAdopcion(MascotaAdopcionEntity entity) throws BusinessLogicException{
        LOOGER.info("MascotaAdopcion creation process begins");
        persistence.create(entity);
        LOOGER.info("MascotaAdopcion was succesfully created");
        return entity;
    }
    
    /**
     * Se obtiene la mascota de adopción
     * @param mascotaAdopcionId el id de la mascota de adopcion buscada.
     * @return 
     */
    public MascotaAdopcionEntity getMascotaAdopcion(Long mascotaAdopcionId){
        LOOGER.log(Level.INFO, "Looking for pet with id = {0}", mascotaAdopcionId);
        MascotaAdopcionEntity mascotaAdopcionEntity= persistence.find(mascotaAdopcionId);
        if (mascotaAdopcionEntity == null) {
            LOOGER.log(Level.SEVERE, "The pet with id = {0} does not exists", mascotaAdopcionId);            
        }
        LOOGER.log(Level.INFO, "Ending search for the pet with id {0}", mascotaAdopcionId);
        return mascotaAdopcionEntity;
     }
     
    /**
     * Se actualiza una mascota de adopcion.
     * @param mascotaAdopcionId el id de la mascota que se busca actualizar.
     * @param mascotaAdopcionEntity la información que se desea actualizar de la
     * mascota de adopción.
     * @return la mascota de adopción actualizada.
     */
     public MascotaAdopcionEntity updateMascotaAdopcion(long mascotaAdopcionId, MascotaAdopcionEntity mascotaAdopcionEntity){
         LOOGER.log(Level.INFO, "Updating the pet with id ={0}", mascotaAdopcionId);
         MascotaAdopcionEntity nuevaMascotaAdopcionEntity = persistence.update(mascotaAdopcionEntity);
         LOOGER.log(Level.INFO, "Finished update on pet with id ={0}", mascotaAdopcionEntity.getId());
         return nuevaMascotaAdopcionEntity;
     }
    
     /**
      * se elimina una mascota de adopción.
      * @param mascotaAdopcionId el id de la mascota de adopción que se desea
      * eliminar.
      * @throws BusinessLogicException, En caso de que la mascota de adopción 
      * tenga una mascota asociada.
      */
     public void deleteMascotaAdopcion(Long mascotaAdopcionId) throws BusinessLogicException{
         MascotaAdopcionEntity entity = persistence.find(mascotaAdopcionId);
         if(entity!=null){
             throw new BusinessLogicException("No se puede eliminar la Mascota adopcion ya que tiene una mascota asociada");
         }
         persistence.delete(mascotaAdopcionId);
     }
    
     /**
      * se obtienen todas las mascotas de adopcipon de la aplicación.
      * @return una lsita con todas las mascotas de adopción de la tienda
      */
     public List<MascotaAdopcionEntity> getMascotasAdopcion(){
         LOOGER.log(Level.INFO, "Inicia proceso de consultar todas las MascotasAdopcion");
         List<MascotaAdopcionEntity> mascotasAdopcion= persistence.findAll();
         LOOGER.log(Level.INFO, "Termina proceso de consultar todas las MascotasAdopcion");
         return mascotasAdopcion;
     }
    
}
