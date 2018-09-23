/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaLogic;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.ejb.MascotaVentaToMascotaLogic;
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
 *Clase que implementa el recurso "mascotaVenta/{id]/mascotas"
 * 
 * @author Sebastian Mujica
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MascotaVentaToMascotaResource {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaVentaToMascotaResource.class.getName());
    
    @Inject
    private MascotaVentaToMascotaLogic mascotaVentaToMascotaLogic;
    
    @Inject
    private MascotaLogic mascotaLogic;
    
    
    @POST
    @Path("{mascotaId: \\d+}")
    public MascotaDTO addMascota(@PathParam("mascotaVentaId") Long mascotaVentaId, @PathParam("mascotaId") Long mascotaId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "MascotaVentaMascotaResource addMascota: input: mascotaVentaId: {0}, mascotaId:{1}", new Object[]{mascotaVentaId, mascotaId});
        if(mascotaLogic.getMascota(mascotaId)==null){
            throw  new WebApplicationException("El recurso /mascotas/" + mascotaId + " no existe." , 404);
        }
        MascotaDTO mascotaDTO = new MascotaDTO(mascotaVentaToMascotaLogic.addMascota(mascotaId, mascotaVentaId));
        LOGGER.log(Level.INFO, "MascotaVentaToMascotaResource addMascota: output: {0}", mascotaDTO.toString());
        return mascotaDTO;

    }
    
    
    
    
    /**
     * Busca la mascota con el id asociado dentro de la mascotaVenta con id asociado.
     * @param mascotaVentaId Identificador de la mascotaVenta que se está buscando.
     * @param mascotaId Identificador de la mascota que se está buscando en la mascotaVenta.
     * @return JSON {@link WebApplicationException}-
     * @throws BusinessLogicException {@link  BusinessLogicException} 
     * Error de la lógica que se genera cuando no se encuentra la mascota.
     */
    @GET
    @Path("{mascotaId: \\d+}")
    public MascotaDTO getMascota( @PathParam("mascotaVentaId") Long mascotaVentaId, @PathParam("mascotaId") Long mascotaId)throws  BusinessLogicException{
        LOGGER.log(Level.INFO, "MascotVentaToMascotaResource getMascota: input : mascotaVentaId : {0} , mascotaId. {1}", new Object[]{mascotaVentaId, mascotaId});
        if(mascotaLogic.getMascota(mascotaId)==null){
            throw new WebApplicationException("El recurso/mascotaVenta/" + mascotaVentaId + "/mascotas/" + mascotaId + "no existe.", 404);
            
        }
        MascotaDTO mascotaDTO = new MascotaDTO(mascotaVentaToMascotaLogic.getMascota(mascotaId, mascotaVentaId));
        LOGGER.log(Level.INFO, "MascotaVentaToMascotaResource getMascota : output : {0}" , mascotaDTO.toString() );
        return mascotaDTO;
    }
    
}
