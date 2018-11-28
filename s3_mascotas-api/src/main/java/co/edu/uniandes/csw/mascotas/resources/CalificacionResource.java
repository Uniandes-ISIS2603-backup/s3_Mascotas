/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.CalificacionDTO;
import co.edu.uniandes.csw.mascotas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
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
 * @author Cristhian Peña
 */
@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource {
    
    @Inject
    CalificacionLogic calificacionLogic;
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());
    
    /**
     * Crea una calificacion con la información ingresada como parametro.
     * Retorna una calificacion creada.
     * @param calif
     * @return CalificacionDTO
     * @throws BusinessLogicException 
     */
    @POST
    public CalificacionDTO createCalificacion(CalificacionDTO calif)throws BusinessLogicException{
        
        LOGGER.log(Level.INFO,"CalificacionResource createCalificacion: input: {0}", calif.toString());
        
        CalificacionEntity calificacionEntity = calif.toEntity();
        CalificacionEntity newCalificacionEntity = calificacionLogic.crearCalificacion(calificacionEntity);
        
        CalificacionDTO newCalifDTO = new CalificacionDTO(newCalificacionEntity);
        
        LOGGER.log(Level.INFO,"CalificacionResource createCalificacion: output: {0}", newCalifDTO.toString());
        
        return newCalifDTO;
    }
    
    /**
     * Devuelve una calificacion dada su id.
     * Retorna la calificacion con el id enviado como parametro.
     * @param calificacionId
     * @return CalificacionDTO
     */
    @GET
    @Path("{calificacionId: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("calificacionId") Long calificacionId){
        
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: input: {0}", calificacionId);
        
        CalificacionEntity califEntity = calificacionLogic.getCalificacion(calificacionId);
        
        if(califEntity == null){
            throw new WebApplicationException("The resource /calificacion/" + calificacionId + "doesn't exist.", 404);
        }
        
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: output: {0}", califEntity.toString());
        
        return new CalificacionDTO(califEntity);
    }
    
    /**
     * Crea una nueva calificacion con la información proporcionada en el cuerpo
     * de la petición web. Retorna una calificacion con la informacion igual a la de entrada.
     * @return CalificacionDTO DTO con la misma informaciòn de entrada
     * @throws BusinessLogicException 
     */
    @GET
    public List<CalificacionDTO> darCalificaciones()throws BusinessLogicException{
        
        LOGGER.info("CalificacionResource darCalificaciones: input: void");
        List<CalificacionDTO> lista = convertirLista(calificacionLogic.getCalificaciones());
        LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: output: {0}", "Calificaciones");
        return lista;
    }
    
    /**
     * Actualiza la informacion de una calificacion.
     * Retorna la calificacion actualizada.
     * @param califId
     * @param calif
     * @return CalificacionDTO
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @PUT
    @Path("{calificacionId: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("calificacionId") Long califId, CalificacionDTO calif) throws BusinessLogicException{
        calif.setId(califId);
        if (calificacionLogic.getCalificacion(califId) == null) {
            throw new WebApplicationException("The resource /calificaciones/" + califId + "doesn't exist.", 404);            
        }
        return new CalificacionDTO(calificacionLogic.updateCalificacion(califId, calif.toEntity()));
    }
    
    @DELETE
    @Path("{calificacionId: \\d+}")
    public void deleteCalificacion(@PathParam("calificacionId") Long califId) throws BusinessLogicException{
        CalificacionEntity califEntity = calificacionLogic.getCalificacion(califId);
        if (califEntity == null) {
            throw new WebApplicationException("The resource /calificaciones/" + califId + "doesn't exist.", 404);            
        }
        calificacionLogic.deleteCalificacion(califId);
    }
    
    public List<CalificacionDTO> convertirLista(List<CalificacionEntity> pLista)
    {
        List<CalificacionDTO> lista = new ArrayList<>();
        for (CalificacionEntity tmp : pLista) {
            lista.add(new CalificacionDTO(tmp));
        }
        return lista;
    }
}
