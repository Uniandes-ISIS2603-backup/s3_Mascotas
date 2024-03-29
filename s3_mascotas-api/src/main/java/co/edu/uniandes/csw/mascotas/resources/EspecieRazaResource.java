/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.RazaDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.EspecieLogic;
import co.edu.uniandes.csw.mascotas.ejb.EspecieRazaLogic;
import co.edu.uniandes.csw.mascotas.ejb.RazaLogic;
import co.edu.uniandes.csw.mascotas.entities.EspecieEntity;
import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
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
    private static final String NA1 = "the resource /especies/";
    private static final String NA3 = "the resource /razas/";
    private static final String NA2 = " doesn't exists.";
    private static final Logger LOGGER = Logger.getLogger(EspecieRazaResource.class.getName());
    
    @Inject 
    private EspecieLogic especieLogic;
    
    @Inject
    private RazaLogic razaLogic; 
   
    @Inject
    private EspecieRazaLogic especieRazaLogic; 

    /**
     * Retorna todas las razas asociadas con la especie especificada
     * @param especiesId
     * @return colección de razas de la especie
     */
    @GET
    public List<RazaDetailDTO> obtenerRazas(@PathParam("especiesId") Long especiesId)
    {
        LOGGER.log(Level.INFO, "Busqueda de razas de la especie: {0}", especiesId);
        EspecieEntity e = especieLogic.getSpecies(especiesId);
        if (e == null || e.getDeleted()) {
            throw new WebApplicationException(NA1 + especiesId + NA2, 404);
        }
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
        LOGGER.log(Level.INFO,"obtener la raza: {0} de la especie: {1}", new Object[]{razasId, especiesId});
        EspecieEntity e = especieLogic.getSpecies(especiesId);
        if (e == null || e.getDeleted()) {
            throw new WebApplicationException(NA1 + especiesId + NA2, 404);
        }
        RazaEntity r = razaLogic.getRaza(razasId);
        if (r == null || r.getDeleted()) {
            throw new WebApplicationException(NA3 + razasId + NA2, 404);
        }
        LOGGER.log(Level.INFO, "FINALIZANDO BUSQUEDA especies/{0}/razas/{1}", new Object[]{especiesId, razasId});
        return new RazaDetailDTO(especieRazaLogic.obtenerRaza(especiesId, razasId));
    }
    
    private List<RazaDetailDTO> listEntity2DTO(List<RazaEntity> entityList){
        List<RazaDetailDTO> list = new ArrayList<>();
        for(RazaEntity r : entityList){
            if(!r.getDeleted())
                list.add(new RazaDetailDTO(r));
        }
        return list;
    }
  
}
