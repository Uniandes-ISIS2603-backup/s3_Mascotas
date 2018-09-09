/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaAdopcionDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
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

/**
 *
 * @author Sebastian Mujica
 */
@Path("mascotaAdopcion")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MascotaAdopcionResource {
     
    @Inject
    MascotaAdopcionLogic mascotaAdopcionLogic;
    
    private static final Logger LOGGER = Logger.getLogger(MascotaAdopcionResource.class.getName());
    
    
      /**
     * Crea una nueva mascotaAdopcion con la información proporcionada en el cuerpo
     * de la petición. Retorna un objeto idéntico con un id autogenerado.
     * @param mascotaAdopcionDTO
     * @return
     * @throws BusinessLogicException 
     */
    @POST
    public MascotaAdopcionDTO crearMascotaAdopcion(MascotaAdopcionDTO mascotaAdopcionDTO)throws BusinessLogicException{
        LOGGER.info("mascotaAdopcionResource crearMascota: input: " + mascotaAdopcionDTO.toString());
        MascotaAdopcionEntity mascotaEntity = mascotaAdopcionDTO.toEntity();
        MascotaAdopcionEntity nuevaMascotaEntity = mascotaAdopcionLogic.crearMascotaAdopcion(mascotaEntity);
        MascotaAdopcionDTO nuevaMascotaDTO = new MascotaAdopcionDTO(nuevaMascotaEntity);
        LOGGER.info("MascotaAdopcionResource crearMascota: output: "+ nuevaMascotaDTO.toString());
        return nuevaMascotaDTO;        
    }
    

    @GET
    @Path("{mascotaAdopcionId: \\d+}")
    public MascotaAdopcionDTO getMascota(@PathParam("mascotaAdopcionId") Long mascotaAdopcionId){
        LOGGER.log(Level.INFO, "mascotaAdopcionResource getMascotaAdopcion : input: {0}", mascotaAdopcionId);
        MascotaAdopcionEntity mascotaEntity = mascotaAdopcionLogic.getMascotaAdopcion(mascotaAdopcionId);
        if(mascotaEntity == null){
            throw new WebApplicationException("The resource /mascotaAdopcion/" + mascotaAdopcionId + "doesn't exist.", 404);
        }
        LOGGER.log(Level.INFO, "MascotaAdopcionResource getMascota: output: {0}", mascotaEntity.toString());
        return new MascotaAdopcionDTO(mascotaEntity);
    }
    
    @DELETE
    @Path("{mascotaAdopcionId: \\d+}")
    public void deleteMascotaAdopcion(@PathParam("mascotaAdopcionId") Long mascotaAdopcionId, MascotaAdopcionDTO mascotaAdopcion) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "MascotaAdopcionREsource deleteMascotaAdopcion: input: {0}", mascotaAdopcionId);
        if(mascotaAdopcionLogic.getMascotaAdopcion(mascotaAdopcionId) == null){
            throw  new WebApplicationException("The resource /mascotasVenta/" + mascotaAdopcionId + "doesn't exist.", 404);
        }
        mascotaAdopcionLogic.deleteMascotaAdopcion(mascotaAdopcionId);
        LOGGER.info("MascotaAdopcionResource deleteMascotaAdopcion: output: void");
    }
    
    @PUT
    @Path("{mascotaAdopcionId: \\d+}")
    public MascotaAdopcionDTO updateMascotaAdopcion(@PathParam("mascotaAdopcionId") Long mascotaAdopcionId, MascotaAdopcionDTO mascotaAdopcion)throws  BusinessLogicException{
        mascotaAdopcion.setId(mascotaAdopcionId);
        if(mascotaAdopcionLogic.getMascotaAdopcion(mascotaAdopcionId)== null){
            throw new WebApplicationException("The resource /mascotaAdopcion/" + mascotaAdopcionId + "doesn't exist.", 404);
        }
        return new MascotaAdopcionDTO(mascotaAdopcionLogic.updateMascotaAdopcion(mascotaAdopcionId, mascotaAdopcion.toEntity()));
    }
    
}
