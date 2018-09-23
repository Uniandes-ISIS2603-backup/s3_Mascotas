/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaAdopcionDTO;
import co.edu.uniandes.csw.mascotas.dtos.MascotaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionToMascotaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaLogic;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
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
 * Clase que implementa el recuro "mascotaAdopcion/{id}/mascotas".
 *
 * @author Sebastian Mujica
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MascotaAdopcionToMascotaResource {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaAdopcionToMascotaResource.class.getName());
    
    @Inject
    private MascotaAdopcionToMascotaLogic mascotaAdopcionToMascotaLogic;
    
    @Inject
    private MascotaLogic mascotaLogic;
    
    
    /**
     * Asocia la mascota de adopcion con la mascota de acuerdo a los id's 
     * correspondientes.
     * @param mascotaAdopcionId
     * @param mascotaId
     * @return
     * @throws BusinessLogicException 
     */
    @POST
    @Path("{mascotaId: \\d+}")
    public MascotaDTO addMascota(@PathParam("mascotaAdopcionId") Long mascotaAdopcionId, @PathParam("mascotaId") Long mascotaId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "MascotaAdopcionMascotaResource addMascota: input: mascotaAdopcionId: {0}, mascotaId:{1}", new Object[]{mascotaAdopcionId, mascotaId});
        if(mascotaLogic.getMascota(mascotaId)==null){
            throw  new WebApplicationException("El recurso /mascotas/" + mascotaId + " no existe." , 404);
        }
        MascotaDTO mascotaDTO = new MascotaDTO(mascotaAdopcionToMascotaLogic.addMascota(mascotaId, mascotaAdopcionId));
        LOGGER.log(Level.INFO, "MascotaAdopcionMascotaResource addMascota: output: {0}", mascotaDTO.toString());
        return mascotaDTO;
    }
    
    
    
    
    /**
     * Busca la mascota con el id asociado dentro de la mascota adopcion con id asociado.
     * @param mascotaAdopcionId Identificador de la mascotaAdopcion que se está buscando.
     * @param mascotaId Identificador de la mascota que se está buscando en la mascotaAdopción.
     * @return JSON {@link WebApplicationException}-
     * @throws BusinessLogicException {@link  BusinessLogicException} 
     * Error de la lógica que se genera cuando no se encuentra la mascota.
     */
    @GET
    @Path("{mascotaId: \\d+}")
    public MascotaDTO getMascota( @PathParam("mascotaAdopcionId") Long mascotaAdopcionId, @PathParam("mascotaId") Long mascotaId)throws  BusinessLogicException{
        LOGGER.log(Level.INFO, "MascotaAdopcionMascotaResource getMascota: input : mascotaAdopcionI : {0} , mascotaId. {1}", new Object[]{mascotaAdopcionId, mascotaId});
        if(mascotaLogic.getMascota(mascotaId)==null){
            throw new WebApplicationException("El recurso/mascotaAdopcion/" + mascotaAdopcionId + "/mascotas/" + mascotaId + " no existe.", 404);
        }
        MascotaDTO mascotaDTO = new MascotaDTO(mascotaAdopcionToMascotaLogic.getMascota(mascotaId, mascotaAdopcionId));
        LOGGER.log(Level.INFO, "MascotaAdopcionMascotaResource getMascota : output : {0}" , mascotaDTO.toString() );
        return mascotaDTO;
    }
    
}
