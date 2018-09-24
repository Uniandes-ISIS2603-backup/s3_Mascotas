/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.RazaPersistence;
import java.lang.reflect.InvocationTargetException;
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
public class RazaLogic {
    
    private static final Logger LOOGER = Logger.getLogger(RazaLogic.class.getName());

    @Inject
    RazaPersistence persistence;
    
    public RazaEntity crearRaza(RazaEntity entity) throws BusinessLogicException{
        LOOGER.info("Race creation process begins");
        if (verificarNombreRepetido(entity)) {
            throw new BusinessLogicException("The race with the name" + entity.getNombre() + "already exist.");
        }
        persistence.create(entity);
        LOOGER.info("Race creation finishes");
        return entity;
    }
    
    public RazaEntity getRaza(Long razasId){
        RazaEntity razaEntity = persistence.find(razasId);
        if (razaEntity == null || razaEntity.getDeleted()) {
            LOOGER.log(Level.SEVERE, "The race with id = {0} does not exists", razaEntity);       
            return null;
        }
        return razaEntity;
    }
    
    public void deleteRaza(RazaEntity raza){
        persistence.delete(raza);
    }
    
    public RazaEntity updateRaza(RazaEntity razaOriginal, RazaEntity razaCambios){
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

            RazaEntity resultingEntity = new RazaEntity();
            copiarEntreClases.copyProperties(resultingEntity, razaOriginal);
            copiarEntreClases.copyProperties(resultingEntity, razaCambios);
            persistence.update(resultingEntity);
            return resultingEntity;
        } catch (IllegalAccessException | InvocationTargetException ex ) {
            Logger.getLogger(MascotaLogic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<RazaEntity> getRazas(){
        LOOGER.log(Level.INFO, "Searching for races");
        List<RazaEntity> razas = persistence.findAll();
        LOOGER.log(Level.INFO, "Ending search");
        return razas;
    }
    
    /**
     * Verifica si el nombre de la raza ya existe
     * @param raza 
     * @return true si ya existe, false de lo contrario
     */
    private boolean verificarNombreRepetido(RazaEntity raza){
        List<RazaEntity> razas = getRazas();
        for (RazaEntity r : razas){
            if (raza.getNombre().equals(r.getNombre())) {
                return true;
            }
        }
        return false;
    }
}
