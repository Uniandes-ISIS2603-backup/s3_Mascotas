/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.RazaDTO;
import co.edu.uniandes.csw.mascotas.dtos.RazaDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.RazaLogic;
import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author lemus
 */
@Path("razas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class RazaResource {
    
    @Inject
    RazaLogic razaLogic;
    
    private static final Logger LOGGER = Logger.getLogger(RazaResource.class.getName());
    
    @GET
    @Path("{razasId: \\d+}")
    public RazaDetailDTO getRaza(@PathParam("razasId") Long razasId){
        RazaEntity razaEntity = razaLogic.getRaza(razasId);
        if (razaEntity == null || razaEntity.getDeleted()) {
            throw new WebApplicationException("The resource /razas/" + razasId + "doesn't exist.", 404);
        }
        return new RazaDetailDTO(razaEntity);
    }
    
    @DELETE
    @Path("{razasId: \\d+}")
    public void deleteRaza(@PathParam("razasId") Long razasId){
        RazaEntity razaEntity = razaLogic.getRaza(razasId);
        if (razaEntity == null || razaEntity.getDeleted()) {
            throw new WebApplicationException("The resource /razas/" + razasId + "doesn't exist.", 404);
        }
        razaLogic.deleteRaza(razaEntity);
    }
    
    @PUT
    @Path("{razasId: \\d+}")
    public RazaDetailDTO updateRaza(@PathParam("razasId") Long razasId, RazaDTO raza) throws BusinessLogicException{
        raza.setId(razasId);
        RazaEntity razaOriginal = razaLogic.getRaza(razasId);
        if (razaOriginal == null || razaOriginal.getDeleted()) {
            throw new WebApplicationException("The resource /razas/" + razasId + "doesn't exist.", 404);            
        }
        return new RazaDetailDTO(razaLogic.updateRaza(razaOriginal, raza.toEntity()));
    }
    
    @GET
    public List<RazaDetailDTO> getRazas(){
        LOGGER.info("RazaResource getRazas: input: void");
        List<RazaDetailDTO> listaRazas = listEntity2DTO(razaLogic.getRazas());
        LOGGER.log(Level.INFO, "RazaResource getRazas: output: {0}", listaRazas.toString());
        return listaRazas;
    }
    
    @Path("{razasId: \\d+}/mascotas")
    public Class<RazaMascotaResource> getRazaMascotaResource(@PathParam("razasId") Long razasId){
        RazaEntity r = razaLogic.getRaza(razasId);
        if (r == null || r.getDeleted()) {
            throw new WebApplicationException("The resource /razas/" + razasId + "doesn't exist.", 404);            
        }
        return RazaMascotaResource.class;
    }

    private List<RazaDetailDTO> listEntity2DTO(List<RazaEntity> razas) {
        List<RazaDetailDTO> list = new ArrayList<>();
        for(RazaEntity m : razas){
            list.add(new RazaDetailDTO(m));
        }
        return list;
    }
}
