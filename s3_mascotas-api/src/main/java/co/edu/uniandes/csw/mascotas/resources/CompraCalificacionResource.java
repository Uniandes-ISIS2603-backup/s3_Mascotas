/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.CalificacionDTO;
import co.edu.uniandes.csw.mascotas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.mascotas.ejb.CompraCalificacionLogic;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Juan Sebastian Gomez, Cristhian Peña
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CompraCalificacionResource {
    private static final Logger LOGGER = Logger.getLogger(CompraCalificacionResource.class.getName());
    @Inject
    private CompraCalificacionLogic compraCalificacionLogic;
    @Inject 
    private CalificacionLogic calificacionLogic;
    @POST
    @Path("(calificacion: \\d+)")
    public CalificacionDTO addCalificacion(@PathParam("calificacionID") Long califId, @PathParam("compraId") Long compraId)throws BusinessLogicException{
                LOGGER.log(Level.INFO, "CompraCalificacionResource addCalifcacion: input: compraID: {0} , calificacionId: {1}", new Object[]{compraId, califId});
        if (calificacionLogic.getCalificacion(califId) == null) {
            throw new WebApplicationException("El recurso /calificacion/" + califId + " no existe.", 404);
        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(compraCalificacionLogic.addCalificacion(compraId, califId));
        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: output: {0}", calificacionDTO.toString());
        return calificacionDTO;
    }
    
    @GET
    public CalificacionDTO getCalificacion(@PathParam("compraId") Long compraId)
    {
        LOGGER.log(Level.INFO, "CompraCalificacionResource getCalificacion: input: {0}", compraId);
        CalificacionDTO calificacion = new CalificacionDTO(compraCalificacionLogic.getCalificacion(compraId));
        LOGGER.log(Level.INFO, "CompraCalificacionResource getCalificacion: output: {0}", calificacion.toString());
        return calificacion;
    }
    
    @PUT
    public CalificacionDTO replaceCalificacion(@PathParam("adopcionId") Long compraId, CalificacionDTO calificacion) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CompraCalificacionResource replaceCalificacion: input: {0}", compraId);
        if(calificacionLogic.getCalificacion(calificacion.getId()) == null){
            throw new WebApplicationException("El recurso /calificacion/" + calificacion.getId() + " no existe.", 404);
        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(compraCalificacionLogic.replaceCalificacion(compraId, calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CompraCalificacionResource replaceCalificacion: output:{0}", calificacionDTO.toString());
        return calificacionDTO;
    }
    
    @DELETE
    @Path("{calificacionId: \\d+}")
    public void removeAuthor(@PathParam("compraId") Long compraId, @PathParam("calificacionId") Long calificacionId) {
        LOGGER.log(Level.INFO, "CompraCalificacionResource removeCalificacion: input: compraId {0} , calificacionId {1}", new Object[]{compraId, calificacionId});
        if (calificacionLogic.getCalificacion(calificacionId) == null) {
            throw new WebApplicationException("El recurso /calificacion/" + calificacionId + " no existe.", 404);
        }
        compraCalificacionLogic.removeCalificacion(compraId, calificacionId);
        LOGGER.info("CompraCalificacionResource removeCalificacion: output: void");
    }
}
