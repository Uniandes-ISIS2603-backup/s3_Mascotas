/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.RazaDTO;
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
import javax.ws.rs.POST;
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

    @POST
    public RazaDTO crearRaza(RazaDTO raza) throws BusinessLogicException{
        LOGGER.info("RazaResource crearRaza: input: "+ raza.toString());
        RazaDTO nuevoRazaDTO = 
                new RazaDTO(razaLogic.crearRaza(raza.toEntity()));
        LOGGER.info("RazaResource crearRaza: input: "+ nuevoRazaDTO.toString());
        return nuevoRazaDTO;
    }
    
    @GET
    @Path("{razasId: \\d+}")
    public RazaDTO getRaza(@PathParam("razasId") Long razasId){
        RazaEntity razaEntity = razaLogic.getRaza(razasId);
        if (razaEntity == null) {
            throw new WebApplicationException("The resource /razas/" + razasId + "doesn't exist.", 404);
        }
        return new RazaDTO(razaEntity);
    }
    
    @DELETE
    @Path("{razasId: \\d+}")
    public void deleteRaza(@PathParam("razasId") Long razasId){
        RazaEntity razaEntity = razaLogic.getRaza(razasId);
        if (razaEntity == null) {
            throw new WebApplicationException("The resource /razas/" + razasId + "doesn't exist.", 404);
        }
        razaLogic.deleteRaza(razaEntity);
    }
    
    @PUT
    @Path("{razasId: \\d+}")
    public RazaDTO updateRaza(@PathParam("razasId") Long razasId, RazaDTO raza) throws BusinessLogicException{
        raza.setId(razasId);
        RazaEntity razaOriginal = razaLogic.getRaza(razasId);
        if (razaOriginal == null) {
            throw new WebApplicationException("The resource /razas/" + razasId + "doesn't exist.", 404);            
        }
        return new RazaDTO(razaLogic.updateRaza(razaOriginal, raza.toEntity()));
    }
    
    @GET
    public List<RazaDTO> getRazas(){
        LOGGER.info("RazaResource getRazas: input: void");
        List<RazaDTO> listaRazas = listEntity2DTO(razaLogic.getRazas());
        LOGGER.log(Level.INFO, "RazaResource getRazas: output: {0}", listaRazas.toString());
        return listaRazas;
    }

    private List<RazaDTO> listEntity2DTO(List<RazaEntity> razas) {
        List<RazaDTO> list = new ArrayList<>();
        for(RazaEntity m : razas){
            list.add(new RazaDTO(m));
        }
        return list;
    }
}
