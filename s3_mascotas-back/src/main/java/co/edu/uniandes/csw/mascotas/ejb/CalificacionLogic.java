/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author pena
 */
@Stateless
public class CalificacionLogic 
{
    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());
    
    public CalificacionEntity crearCalificacion(CalificacionEntity entrada) throws BusinessLogicException
    {
        LOGGER.info("Se está creando una calificación");
        LOGGER.info("Creacion Finalizada");
        return entrada;
    }
    
}
