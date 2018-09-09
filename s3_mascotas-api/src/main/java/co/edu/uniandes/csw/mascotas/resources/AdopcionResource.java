/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

/**
 *
 * @author Juan Sebasti{an G{omez
 */
import co.edu.uniandes.csw.mascotas.dtos.AdopcionDTO;
import co.edu.uniandes.csw.mascotas.ejb.AdopcionLogic;
import co.edu.uniandes.csw.mascotas.entities.AdopcionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
@Path("adopciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AdopcionResource {
    @Inject
    AdopcionLogic adopcionLogic;
    
    private static final Logger LOGGER = Logger.getLogger(CompraResource.class.getName());
    
   
    @POST
    public AdopcionDTO crearAdopcion(AdopcionDTO adop)throws BusinessLogicException{
        
        LOGGER.info("AdopcionResource crearAdopcion: input: "+ adop.toString());
        AdopcionEntity adopEntity = adop.toEntity();
        AdopcionEntity nuevoAdopEntity = adopcionLogic.crearAdopcion(adopEntity);
        AdopcionDTO nuevaAdopcionDTO = new AdopcionDTO(nuevoAdopEntity);
        LOGGER.info("AdopcionResource crearAdopcion: output: "+ nuevaAdopcionDTO.toString());
        return nuevaAdopcionDTO;
    }

    @GET
    @Path("{adopcionesId: \\d+}")
    public AdopcionDTO getAdopcion(@PathParam("adopcionesId") Long adopId){
        LOGGER.log(Level.INFO, "AdopcionResource getAdopcion: input: {0}", adopId);
        AdopcionEntity adopEntity = adopcionLogic.getAdopcion(adopId);
        if(adopEntity == null){
            throw new WebApplicationException("The resource /adopciones/" + adopId + "doesn't exist.", 404);
        }
        LOGGER.log(Level.INFO, "AdopcionResource getAdopcion: output: {0}", adopEntity.toString());
        return new AdopcionDTO(adopEntity);
    }
    
    @GET
    public List<AdopcionDTO> getAdopciones(){
        LOGGER.info("AdopcionResource getAdopciones: input: void");
        List<AdopcionDTO> listaAdop = listEntity2DTO(adopcionLogic.getAdopciones());
        LOGGER.log(Level.INFO, "AdopcionResource getADopciones: output: {0}", listaAdop.toString());
        return listaAdop;
    }
    
    @PUT
    @Path("{adopcionesId: \\d+}")
    public AdopcionDTO updateMascota(@PathParam("adopcionesId") Long adopId, AdopcionDTO adop) throws BusinessLogicException{
        adop.setId(adopId);
        if (adopcionLogic.getAdopcion(adopId) == null) {
            throw new WebApplicationException("The resource /adopciones/" + adopId + "doesn't exist.", 404);            
        }
        return new AdopcionDTO(adopcionLogic.updateAdopcion(adopId, adop.toEntity()));
    }
     private List<AdopcionDTO> listEntity2DTO(List<AdopcionEntity> entityList){
        List<AdopcionDTO> list = new ArrayList<>();
        for(AdopcionEntity m : entityList){
            list.add(new AdopcionDTO(m));
        }
        return list;
    }
}
