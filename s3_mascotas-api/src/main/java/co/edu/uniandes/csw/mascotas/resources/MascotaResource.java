/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
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
 * @author lemus
 */
@Path("mascotas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MascotaResource {
    
    @Inject
    MascotaLogic mascotaLogic;
    
    @Inject
    RazaMascotaResource razaMascotaResource;
    
    private static final Logger LOGGER = Logger.getLogger(MascotaResource.class.getName());

    /**
     * Crea una nueva mascota con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param mascota {@link MascotaDTO} - La mascota que se desea guardar.
     * @return JSON {@link MascotaDTO} - La mascota guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la mascota o es invalida
     */
    @POST
    public MascotaDTO createMascota(MascotaDTO mascota) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MascotaResource createMascota: input: {0}", mascota.toString());
        MascotaDTO nuevaMascotaDTO = new MascotaDTO(mascotaLogic.crearMascota(mascota.toEntity()));
        LOGGER.log(Level.INFO, "MascotaResource createMascota: output: {0}", nuevaMascotaDTO.toString());
        return nuevaMascotaDTO;
    }
    
    @GET
    @Path("{mascotasId: \\d+}")
    public MascotaDTO getMascota(@PathParam("mascotasId") Long mascotasId){
        LOGGER.log(Level.INFO, "MascotaResource getMascota: input: {0}", mascotasId);
        MascotaEntity mascotaEntity = mascotaLogic.getMascota(mascotasId);
        if(mascotaEntity == null){
            throw new WebApplicationException("The resource /mascotas/" + mascotasId + "doesn't exist.", 404);
        }
        LOGGER.log(Level.INFO, "MascotaResource getMascota: output: {0}", mascotaEntity.toString());
        return new MascotaDTO(mascotaEntity);
    }
    
    @GET
    public List<MascotaDTO> getMascotas(){
        LOGGER.info("MascotaResource getMascotas: input: void");
        List<MascotaDTO> listaMascotas = listEntity2DTO(mascotaLogic.getMascotas());
        LOGGER.log(Level.INFO, "MascotaResource getMascotas: output: {0}", listaMascotas.toString());
        return listaMascotas;
    }
    
    @PUT
    @Path("{mascotasId: \\d+}")
    public MascotaDTO updateMascota(@PathParam("mascotasId") Long mascotasId, MascotaDTO mascota) throws BusinessLogicException{
        mascota.setId(mascotasId);
        MascotaEntity mascotaOriginal = mascotaLogic.getMascota(mascotasId);
        if (mascotaOriginal == null) {
            throw new WebApplicationException("The resource /mascotas/" + mascotasId + "doesn't exist.", 404);            
        }
        return new MascotaDTO(mascotaLogic.updateMascota(mascotaOriginal, mascota.toEntity()));
    }
    
    @DELETE
    @Path("{mascotasId: \\d+}")
    public void deleteMascota(@PathParam("mascotasId") Long mascotasId) throws BusinessLogicException{
        MascotaEntity mascotaEntity = mascotaLogic.getMascota(mascotasId);
        if (mascotaEntity == null) {
            throw new WebApplicationException("The resource /mascotas/" + mascotasId + "doesn't exist.", 404);            
        }
        mascotaLogic.deleteMascota(mascotaEntity);
    }
    
    private List<MascotaDTO> listEntity2DTO(List<MascotaEntity> entityList){
        List<MascotaDTO> list = new ArrayList<>();
        for(MascotaEntity m : entityList){
            list.add(new MascotaDTO(m));
        }
        return list;
    }
    
    @Path("{mascotasId: \\d+}/raza")
    public Class<RazaMascotaResource> getRazaMascotaResource(@PathParam("mascotasId") Long mascotasId){
        MascotaEntity m = mascotaLogic.getMascota(mascotasId);
        if(m == null){
            throw new WebApplicationException("The resource /mascotas/" + mascotasId + "doesn't exist.", 404);            
        }
        return RazaMascotaResource.class;
    }
    
    @Path("{mascotasId: \\d+}/historias")
    public Class<MascotaHistoriaResource> getMascotaHistoriaResource(@PathParam("mascotasId") Long mascotasId){
        MascotaEntity m = mascotaLogic.getMascota(mascotasId);
        if(m == null){
            throw new WebApplicationException("The resource /mascotas/" + mascotasId + "doesn't exist.", 404);            
        }
        return MascotaHistoriaResource.class;
    }
}
