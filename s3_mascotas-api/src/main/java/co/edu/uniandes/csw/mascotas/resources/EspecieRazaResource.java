/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.EspecieDetailDTO;
import co.edu.uniandes.csw.mascotas.dtos.RazaDTO;
import co.edu.uniandes.csw.mascotas.dtos.RazaDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.EspecieLogic;
import co.edu.uniandes.csw.mascotas.ejb.EspecieRazaLogic;
import co.edu.uniandes.csw.mascotas.entities.EspecieEntity;
import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "books/{id}/authors".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("especies/{especiesId : \\d+}/razas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EspecieRazaResource 
{
    private static final Logger LOGGER = Logger.getLogger(EspecieRazaResource.class.getName());
    
    @Inject 
    private EspecieLogic especieLogic;
    
    @Inject
    private EspecieRazaLogic especieRazaLogic;
 
    /**
     * Relaciona una raza existente con una especie existente
     * @return JSON {@link EspecieDetailDTO} - La raza asociada
     */
    @POST
    public EspecieDetailDTO añadirRaza(@PathParam("especiesId") Long especiesId, RazaDTO raza)throws BusinessLogicException{
        
        EspecieEntity e = especieLogic.getSpecies(especiesId);
        if (e == null || e.getDeleted()) {
            throw new WebApplicationException("the resource /especies/" + especiesId + " doesn't exists.", 404);
        }
        EspecieDetailDTO especieDetail = new EspecieDetailDTO(especieRazaLogic.addRaza(especiesId, raza.toEntity()));
        return especieDetail;
    }

    /**
     * Retorna todas las razas asociadas con la especie especificada
     * @param razasId
     * @return colección de razas de la especie
     */
    @GET
    public List<RazaDetailDTO> obtenerRazas(@PathParam("especiesId") Long especiesId){
        return listEntity2DTO(especieRazaLogic.obtenerRazas(especiesId));
    }
    
    /**
     * Retorna la raza asociada a la especie específica dados por parámetro
     * @param especiesId
     * @param razasId
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    @Path("/{razasId: \\d+}")
    public RazaDetailDTO obtenerRaza(@PathParam("especiesId") Long especiesId, @PathParam("razasId") Long razasId) throws BusinessLogicException{
        EspecieEntity e = especieLogic.getSpecies(razasId);
        if (e == null || e.getDeleted()) {
            throw new WebApplicationException("the resource /especies/" + especiesId + " doesn't exists.", 404);
        }
        return new RazaDetailDTO(especieRazaLogic.obtenerRaza(especiesId, razasId));
    }
    
    /**
     * Elimina la relación entre una raza y una especie (ya existentes)
     * @param especiesId
     * @param razasId 
     */
    @DELETE
    @Path("/{razasId: \\d+}")
    public void removerRaza(@PathParam("especiesId") Long especiesId, @PathParam("razasId") Long razasId){
        EspecieEntity e = especieLogic.getSpecies(razasId);
        if (e == null || e.getDeleted()) {
            throw new WebApplicationException("the resource /especies/" + especiesId + " doesn't exists.", 404);
        }
        especieRazaLogic.removerRaza(especiesId, razasId);
    }
    
    private List<RazaDetailDTO> listEntity2DTO(List<RazaEntity> entityList){
        List<RazaDetailDTO> list = new ArrayList<>();
        for(RazaEntity r : entityList){
            list.add(new RazaDetailDTO(r));
        }
        return list;
    }
  
}
