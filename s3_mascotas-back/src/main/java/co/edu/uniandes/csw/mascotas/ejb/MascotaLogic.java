/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
import co.edu.uniandes.csw.mascotas.persistence.RazaPersistence;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.beanutils.BeanUtilsBean;

/**
 *
 * @author lemus
 */
@Stateless
public class MascotaLogic {
    
    private static final Logger LOOGER = Logger.getLogger(MascotaLogic.class.getName());
    
    @Inject
    private MascotaPersistence persistence;
    
    @Inject
    private RazaPersistence razaPersistence;
    
    public MascotaEntity crearMascota(MascotaEntity entity) throws BusinessLogicException{
        LOOGER.info("Pet creation process begins");
        if (entity.getRaza() == null || razaPersistence.find(entity.getRaza().getId()) == null) {
            throw new BusinessLogicException("La raza es invalida");
        }
        if (entity.getEdad() < 0) {
            throw new BusinessLogicException("age is incorrect");
        }
        persistence.create(entity);
        
        LOOGER.info("Pet creation finishes");
        return entity;
    }
    
    public List<MascotaEntity> getMascotas(){
        LOOGER.log(Level.INFO, "Searching for pets");
        List<MascotaEntity> mascotas = persistence.findAll();
        List<MascotaEntity> aRet = new ArrayList<>();
        for(int i = 0; i < mascotas.size(); i++)
        {
            if(mascotas.get(i).getCompra() == null)
            {
                aRet.add(mascotas.get(i));
            }
        }
        LOOGER.log(Level.INFO, "Ending search");
        return aRet;
    }
    
    public MascotaEntity getMascota(Long mascotasId){
        LOOGER.log(Level.INFO, "Looking for pet with id = {0}", mascotasId);
        MascotaEntity mascotaEntity = persistence.find(mascotasId);
        if (mascotaEntity == null || mascotaEntity.getDeleted()) {
            LOOGER.log(Level.SEVERE, "The pet with id = {0} does not exists", mascotasId);
            return null;
        }
        LOOGER.log(Level.INFO, "Ending search for the pet with id {0}", mascotasId);
        return mascotaEntity;
    }
    
    public MascotaEntity updateMascota(MascotaEntity mascotaOriginal, MascotaEntity mascotaCambios) throws BusinessLogicException{
        LOOGER.log(Level.INFO, "Finished update on pet with id = {0}", mascotaOriginal.getId());
        if(mascotaCambios.getEdad() != null && mascotaCambios.getEdad() < 0){
            throw new BusinessLogicException("age is incorrect");
        }
        try {
            
            BeanUtilsBean copiarEntreClases = new BeanUtilsBean() {
                @Override
                public void copyProperty(Object dest, String name, Object value)
                        throws IllegalAccessException, InvocationTargetException {
                    if(value != null) {
                        super.copyProperty(dest, name, value);
                    }
                }
            };
            
            MascotaEntity resultingEntity = new MascotaEntity();
            copiarEntreClases.copyProperties(resultingEntity, mascotaOriginal);
            copiarEntreClases.copyProperties(resultingEntity, mascotaCambios);
            persistence.update(resultingEntity);
            return resultingEntity;
        } catch (IllegalAccessException | InvocationTargetException ex ) {
            Logger.getLogger(MascotaLogic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void deleteMascota(MascotaEntity mascota) throws BusinessLogicException{
        if(mascota == null || persistence.find(mascota.getId()) == null)
        {
            throw new BusinessLogicException("Mascota invalida");
        }
        persistence.delete(mascota);
    }
}
