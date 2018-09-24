/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaDTO;
import co.edu.uniandes.csw.mascotas.dtos.RazaDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.RazaLogic;
import co.edu.uniandes.csw.mascotas.ejb.RazaMascotaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lemus
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RazaMascotaResource {
    
    private static final Logger LOGGER = Logger.getLogger(RazaMascotaResource.class.getName());
    
    @Inject
    private RazaMascotaLogic razaMascotaLogic;
    
    @Inject
    private RazaLogic razaLogic;
    
    /**
     * Relaciona una mascota existente con una raza existente
     * @return JSON {@link RazaDetailDTO} - La raza asociada
     */
    @POST
    @Path("/{mascotasId: \\d+}")
    public RazaDetailDTO addMascota(@PathParam("razasId") Long razasId, @PathParam("mascotasId") Long mascotasId)throws BusinessLogicException{
        
        RazaEntity r = razaLogic.getRaza(razasId);
        if (r == null || r.getDeleted()) {
            throw new WebApplicationException("the resource /razas/" + razasId + " doesn't exists.", 404);
        }
        RazaDetailDTO razaDetail = new RazaDetailDTO(razaMascotaLogic.addMascota(razasId, mascotasId));
        return razaDetail;
    }

    /**
     * Retorna todas las mascotas asociadas con la raza especificada
     * @param razasId
     * @return colección de mascotas de la raza
     */
    @GET
    public List<MascotaDTO> getMascotas(@PathParam("razasId") Long razasId){
        return listEntity2DTO(razaMascotaLogic.getMascotas(razasId));
    }
    
    @GET
    @Path("/{mascotasId: \\d+}")
    public MascotaDTO getMascota(@PathParam("razasId") Long razasId, @PathParam("mascotasId") Long mascotasId) throws BusinessLogicException{
        RazaEntity r = razaLogic.getRaza(razasId);
        if (r == null || r.getDeleted()) {
            throw new WebApplicationException("the resource /razas/" + razasId + " doesn't exists.", 404);
        }
        return new MascotaDTO(razaMascotaLogic.getMascota(razasId, mascotasId));
    }
    
    @DELETE
    @Path("/{mascotasId: \\d+}")
    public void removeMascota(@PathParam("razasId") Long razasId, @PathParam("mascotasId") Long mascotasId){
        RazaEntity r = razaLogic.getRaza(razasId);
        if (r == null || r.getDeleted()) {
            throw new WebApplicationException("the resource /razas/" + razasId + " doesn't exists.", 404);
        }
        razaMascotaLogic.removeMascota(razasId, mascotasId);
    }
    
    private List<MascotaDTO> listEntity2DTO(List<MascotaEntity> entityList){
        List<MascotaDTO> list = new ArrayList<>();
        for(MascotaEntity m : entityList){
            list.add(new MascotaDTO(m));
        }
        return list;
    }
}
