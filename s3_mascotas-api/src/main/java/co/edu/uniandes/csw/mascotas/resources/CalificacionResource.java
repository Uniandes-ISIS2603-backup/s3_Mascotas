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
 * @author pena
 */
@Path("calificacion")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource {
    
    @Inject
    CalificacionLogic calificacionLogic;
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());
    
    /**
     * Crea una nueva calificacion con la información proporcionada en el cuerpo
     * de la petición web. Retorna una calificacion con la informacion igual a la de entrada.
     * @param calificacion
     * @return CalificacionDTO DTO con la misma informaciòn de entrada
     * @throws BusinessLogicException 
     */
    @POST
    public CalificacionDTO crearMascota(CalificacionDTO calificacion)throws BusinessLogicException{
        
        LOGGER.info("CalificacionResource crearCalificacion: input: "+ calificacion.toString());
        CalificacionEntity calificacionEntity = calificacion.toEntity();
        CalificacionEntity nuevaCalificacionEntity = calificacionLogic.crearCalificacion(calificacionEntity);
        CalificacionDTO DTONuevaCalificacion = new CalificacionDTO(nuevaCalificacionEntity);
        LOGGER.info("CalificacionResource crearCalificacion: output: "+ DTONuevaCalificacion.toString());
        return DTONuevaCalificacion;
    }

//    @GET
//    @Path("{mascotasId: \\d+}")
//    public MascotaDTO getMascota(@PathParam("mascotasId") Long mascotasId){
//        LOGGER.log(Level.INFO, "MascotaResource getMascota: input: {0}", mascotasId);
//        MascotaEntity mascotaEntity = mascotaLogic.getMascota(mascotasId);
//        if(mascotaEntity == null){
//            throw new WebApplicationException("The resource /mascotas/" + mascotasId + "doesn't exist.", 404);
//        }
//        LOGGER.log(Level.INFO, "MascotaResource getMascota: output: {0}", mascotaEntity.toString());
//        return new MascotaDTO(mascotaEntity);
//    }
//    
//    @GET
//    public List<MascotaDTO> getMascotas(){
//        LOGGER.info("MascotaResource getMascotas: input: void");
//        List<MascotaDTO> listaMascotas = listEntity2DTO(mascotaLogic.getMascotas());
//        LOGGER.log(Level.INFO, "MascotaResource getMascotas: output: {0}", listaMascotas.toString());
//        return listaMascotas;
//    }
//    
//    @PUT
//    @Path("{mascotasId: \\d+}")
//    public MascotaDTO updateMascota(@PathParam("mascotasId") Long mascotasId, MascotaDTO mascota) throws BusinessLogicException{
//        mascota.setId(mascotasId);
//        if (mascotaLogic.getMascota(mascotasId) == null) {
//            throw new WebApplicationException("The resource /mascotas/" + mascotasId + "doesn't exist.", 404);            
//        }
//        return new MascotaDTO(mascotaLogic.updateMascota(mascotasId, mascota.toEntity()));
//    }
//    
//    @DELETE
//    @Path("{mascotasId: \\d+}")
//    public void deleteMascota(@PathParam("mascotasId") Long mascotasId, MascotaDTO mascota) throws BusinessLogicException{
//        MascotaEntity mascotaEntity = mascotaLogic.getMascota(mascotasId);
//        if (mascotaLogic.getMascota(mascotasId) == null) {
//            throw new WebApplicationException("The resource /mascotas/" + mascotasId + "doesn't exist.", 404);            
//        }
//        mascotaLogic.deleteMascota(mascotasId);
//    }
//    
//    private List<MascotaDTO> listEntity2DTO(List<MascotaEntity> entityList){
//        List<MascotaDTO> list = new ArrayList<>();
//        for(MascotaEntity m : entityList){
//            list.add(new MascotaDTO(m));
//        }
//        return list;
//    }
}
