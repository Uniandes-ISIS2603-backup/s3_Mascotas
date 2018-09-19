/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.CalificacionDTO;
import co.edu.uniandes.csw.mascotas.ejb.AdopcionCalificacionLogic;
import co.edu.uniandes.csw.mascotas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.mappers.WebApplicationExceptionMapper;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author estudiante
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdopcionCalificacionResource 
{
    private static final Logger LOGGER = Logger.getLogger(AdopcionCalificacionResource.class.getName());
    
    @Inject
    private AdopcionCalificacionLogic adopcionCalificacionLogic;
    
    @Inject
    private CalificacionLogic calificacionLogic;
    
    @POST
    @Path("/{calificacionId: \\d+}")
    public CalificacionDTO addCalificacion(@PathParam("adopcionId") Long adopcionId, @PathParam("calificacionId") Long calificacionId) throws BusinessLogicException
    {
        if(calificacionLogic.getCalificacion(calificacionId) == null){
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionId + " no existe.", 404);
        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(adopcionCalificacionLogic.addCalificacion(adopcionId, calificacionId));
        LOGGER.log(Level.INFO, "AdopcionCalificacionResource addCalificacion: output: {0}", calificacionDTO.toString());
        return calificacionDTO;
    }
    
    @GET
    public CalificacionDTO getCalificacion(@PathParam("adopcionId") Long adopcionId)
    {
        LOGGER.log(Level.INFO, "AdopcionCalificacionResource getCalificacion: input: {0}", adopcionId);
        CalificacionDTO calificacion = new CalificacionDTO(adopcionCalificacionLogic.getCalificacion(adopcionId));
        if(calificacion.getComentarios() == null)
        {
            throw new WebApplicationException("El recurso /adopciones/" + adopcionId + " no tiene una calificacion asociada.", 404);
        }
        LOGGER.log(Level.INFO, "AdopcionCalificacionResource getCalificacion: output: {0}", calificacion.toString());
        return calificacion;
    }
    
    @PUT
    public CalificacionDTO replaceCalificacion(@PathParam("adopcionId") Long adopcionId, CalificacionDTO calificacion) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "AdopcionCalificacionResource replaceCalificacion: input: {0}", adopcionId);
        if(calificacionLogic.getCalificacion(calificacion.getId()) == null){
            throw new WebApplicationException("El recurso /calificacion/" + calificacion.getId() + " no existe.", 404);
        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(adopcionCalificacionLogic.replaceCalificacion(adopcionId, calificacion.toEntity()));
        LOGGER.log(Level.INFO, "AdopcionCalificacionResource replaceCalificacion: output:{0}", calificacionDTO.toString());
        return calificacionDTO;
    }
    
    @DELETE
    @Path("{calificacionId: \\d+}")
    public void removeAuthor(@PathParam("adopcionId") Long adopcionId, @PathParam("calificacionId") Long calificacionId) {
        LOGGER.log(Level.INFO, "AdopcionCalificacionResource removeCalificacion: input: adopcionId {0} , calificacionId {1}", new Object[]{adopcionId, calificacionId});
        if (calificacionLogic.getCalificacion(calificacionId) == null) {
            throw new WebApplicationException("El recurso /calificacion/" + calificacionId + " no existe.", 404);
        }
        adopcionCalificacionLogic.removeCalificacion(adopcionId, calificacionId);
        LOGGER.info("AdopcionCalificacionResource removeCalificacion: output: void");
    }
}