/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.AdopcionDTO;
import co.edu.uniandes.csw.mascotas.dtos.CompraDTO;
import co.edu.uniandes.csw.mascotas.ejb.AdopcionLogic;
import co.edu.uniandes.csw.mascotas.ejb.ClienteAdopcionLogic;
import co.edu.uniandes.csw.mascotas.ejb.CompraLogic;
import co.edu.uniandes.csw.mascotas.entities.AdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.CompraEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Juan Sebastian Gomez, Camilo Pinilla
 */
@Path("clientes/(clienteId :\\d)/adopciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteAdopcionResource {
    private static final Logger LOGGER = Logger.getLogger(ClienteAdopcionResource.class.getName());
    
    @Inject
    private ClienteAdopcionLogic clienteAdopcionLogic;
    
    @Inject
    private AdopcionLogic adopcionLogic;
    
    @POST
    @Path("/{adopcionId: \\d+}")
    public AdopcionDTO addAdopcion(@PathParam("clienteId") Long clienteId, @PathParam("adopcionId") Long adopcionId) throws BusinessLogicException
    {
        if(adopcionLogic.getAdopcion(adopcionId) == null){
            throw new WebApplicationException("El recurso /adopciones/" + adopcionId + " no existe.", 404);
        }
        
        AdopcionDTO adopcionDTO = new AdopcionDTO(clienteAdopcionLogic.addAdopcion(adopcionId, clienteId));
        LOGGER.log(Level.INFO, "ClienteAdopcionResource addAdopcion: output: {0}", adopcionDTO.toString());
        
        return adopcionDTO;
    }    
    @GET
    public List<AdopcionDTO> getAdopciones(@PathParam("clienteId") Long clienteId)
    {
        LOGGER.log(Level.INFO, "ClienteAdopcionResource getAdopciones(): input: {0}", clienteId);
        
        List<AdopcionEntity> adopcionEntityList = clienteAdopcionLogic.getAdopciones(clienteId);
        List<AdopcionDTO> adopciones = new ArrayList<>();
        
        for(AdopcionEntity a: adopcionEntityList){
            adopciones.add(new AdopcionDTO(a));
        }
        LOGGER.log(Level.INFO, "ClienteAdopcionesResource getAdopciones: output: {0}", adopciones.toString());
        return adopciones;
    }
}
