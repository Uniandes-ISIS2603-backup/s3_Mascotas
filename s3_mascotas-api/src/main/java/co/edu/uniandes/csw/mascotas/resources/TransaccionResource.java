/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.TransaccionDTO;
import co.edu.uniandes.csw.mascotas.ejb.TransaccionLogic;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.entities.TransaccionEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Sebastian Mujica
 */
@Path("transaccion")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TransaccionResource {
    
    @Inject
    TransaccionLogic transaccionLogic;
    
    private static final Logger LOGGER = Logger.getLogger(TransaccionResource.class.getName());
    
    
    /**
     * Crea una nueva transaccion con la información proporcionada en el cuerpo
     * de la petición. Retorna un objeto idéntico con un id autogenerado.
     * @param transaccion
     * @return
     * @throws BusinessLogicException 
     */
    @POST
    public TransaccionDTO crearTransaccion(TransaccionDTO transaccion)throws BusinessLogicException{
        LOGGER.info("TransaccionResource crearTransaccion: input: "+ transaccion.toString());
        TransaccionEntity transaccionEntity = transaccion.toEntity();
        TransaccionEntity nuevoTransaccionEntity = transaccionLogic.crearTransaccion(transaccionEntity);
        TransaccionDTO nuevoTransaccionDTO = new TransaccionDTO(nuevoTransaccionEntity);
        LOGGER.info("TransaccionResource crearTransaccion: output: "+ nuevoTransaccionDTO.toString());
        return nuevoTransaccionDTO;
    }
    
}
