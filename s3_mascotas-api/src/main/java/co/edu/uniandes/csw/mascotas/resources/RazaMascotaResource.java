/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaLogic;
import co.edu.uniandes.csw.mascotas.ejb.RazaLogic;
import co.edu.uniandes.csw.mascotas.ejb.RazaMascotaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
 * @author lemus
 */
@Path("razas/{razasId : \\d+}/mascotas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RazaMascotaResource {
    
    private static final String NA1 = "the resource /razas/";
    private static final String NA2 = " doesn't exists.";
    private static final Logger LOGGER = Logger.getLogger(RazaMascotaResource.class.getName());
    
    @Inject
    private RazaMascotaLogic razaMascotaLogic;
    
    @Inject
    private RazaLogic razaLogic;
    
    @Inject
    private MascotaLogic mascotaLogic;

    /**
     * Retorna todas las mascotas asociadas con la raza especificada
     * @param razasId
     * @return colección de mascotas de la raza
     */
    @GET
    public List<MascotaDTO> getMascotas(@PathParam("razasId") Long razasId){
        LOGGER.log(Level.INFO, "Consultando las mascotas de la raza: {0}", razasId);
        RazaEntity r = razaLogic.getRaza(razasId);
        if (r == null || r.getDeleted()) {
            throw new WebApplicationException(NA1 + razasId + NA2, 404);
        }
        return listEntity2DTO(razaMascotaLogic.getMascotas(razasId));
    }
    
    @GET
    @Path("/{mascotasId: \\d+}")
    public MascotaDTO getMascota(@PathParam("razasId") Long razasId, @PathParam("mascotasId") Long mascotasId) throws BusinessLogicException{
        RazaEntity r = razaLogic.getRaza(razasId);
        if (r == null || r.getDeleted()) {
            throw new WebApplicationException(NA1 + razasId + NA2, 404);
        }
        MascotaEntity m = mascotaLogic.getMascota(mascotasId);
        if (m == null || m.getDeleted()) {
            throw new WebApplicationException("the resource /mascotas/" + mascotasId + NA2, 404);
        }
        return new MascotaDTO(razaMascotaLogic.getMascota(razasId, mascotasId));
    }
    
    private List<MascotaDTO> listEntity2DTO(List<MascotaEntity> entityList){
        List<MascotaDTO> list = new ArrayList<>();
        for(MascotaEntity m : entityList){
            list.add(new MascotaDTO(m));
        }
        return list;
    }
}
