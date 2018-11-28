/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.CalificacionDTO;
import co.edu.uniandes.csw.mascotas.ejb.CompraCalificacionLogic;
import co.edu.uniandes.csw.mascotas.ejb.CompraLogic;
import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Juan Sebastian Gomez, Cristhian Peña
 */
@Path("compras/(comprasId :\\d+)/calificaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CompraCalificacionResource {
    private static final Logger LOGGER = Logger.getLogger(CompraCalificacionResource.class.getName());
    @Inject
    private CompraCalificacionLogic compraCalificacionLogic;
    @Inject 
    private CompraLogic compraLogic;
    
    @GET
    public CalificacionDTO getCalificacion(@PathParam("comprasId") Long compraId)
    {
        LOGGER.log(Level.INFO, "CompraCalificacionResource getCalificacion: input: {0}", compraId);
        if(compraLogic.getCompra(compraId) == null)
            throw new WebApplicationException("El recurso /compras/" + compraId + " no existe.", 404);
        CalificacionEntity calificacion = compraCalificacionLogic.getCalificacion(compraId);
        if(calificacion == null)
            throw new WebApplicationException("El recurso /compras/" + compraId + " no tiene calificacion.", 404);
        else
        {
            LOGGER.log(Level.INFO, "CompraCalificacionResource getCalificacion: output: {0}", calificacion);
        }
        return new CalificacionDTO(calificacion);
    }
}
