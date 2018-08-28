/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.TransaccionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.TransaccionPersistence;
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
public class TransaccionLogic {

    
    @Inject
    private TransaccionPersistence persistence;
    
    private static final Logger LOOGER = Logger.getLogger(TransaccionLogic.class.getName());

    
    public TransaccionEntity crearTransaccion(TransaccionEntity entity)  throws BusinessLogicException{
        LOOGER.info("Transaction creation process begins");
        // missing verifications
        persistence.create(entity);
        LOOGER.info("Transaction creation finishes");
        return entity;
    }
 
    
    public List<TransaccionEntity> getTransacciones(){
        LOOGER.log(Level.INFO, "Searching for transactions");
        List<TransaccionEntity> transacciones = persistence.findAll();
        LOOGER.log(Level.INFO, "Ending search");
        return transacciones;
    }
    
    public TransaccionEntity getTransaccion(Long transaccionId){
        LOOGER.log(Level.INFO, "Looking for transaction with id = {0}", transaccionId);
        TransaccionEntity transaccionEntity = persistence.find(transaccionId);
        if (transaccionEntity == null) {
            LOOGER.log(Level.SEVERE, "The transaction with id = {0} does not exists", transaccionId);            
        }
        LOOGER.log(Level.INFO, "Ending search for the transaccion with id {0}", transaccionId);
        return transaccionEntity;
    }
   
    public void deleteTransaccion(Long transaccionId){
        persistence.delete(transaccionId);
    }
    
}
