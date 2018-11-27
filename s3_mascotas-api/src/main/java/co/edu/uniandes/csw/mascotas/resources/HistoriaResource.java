/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.HistoriaDTO;
import co.edu.uniandes.csw.mascotas.ejb.HistoriaLogic;
import co.edu.uniandes.csw.mascotas.entities.HistoriaEntity;
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
 * @author Camilo Pinilla
 */
@Path("historias")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class HistoriaResource {
    
    @Inject
    HistoriaLogic historiaLogic;
    
    // Debug logger
    private static final Logger LOGGER = Logger.getLogger(HistoriaResource.class.getName());
    
    /**
     * Crea una historia con la información ingresada como parametro.
     * Retorna una historia creada.
     * @param historia
     * @return HistoriaDTO
     * @throws BusinessLogicException 
     */
    @POST
    public HistoriaDTO createHistoria(HistoriaDTO historia)throws BusinessLogicException{
        
        LOGGER.log(Level.INFO,"HistoriaResource createHistoria: input: {0}", historia.toString());
        
        HistoriaEntity historiaEntity = historia.toEntity();
        HistoriaEntity newHistoriaEntity = historiaLogic.createHistoria(historiaEntity);
        
        HistoriaDTO newHistoriaDTO = new HistoriaDTO(newHistoriaEntity);
        
        LOGGER.log(Level.INFO,"HistoriaResource createHistoria: output: {0}", newHistoriaDTO.toString());
        
        return newHistoriaDTO;
    }

    /**
     * Devuelve una historia dada su id.
     * Retorna la historia con el id enviado como parametro.
     * @param historiaId
     * @return HistoriaDTO
     */
    @GET
    @Path("{historiaId: \\d+}")
    public HistoriaDTO getHistoria(@PathParam("historiaId") Long historiaId){
        
        LOGGER.log(Level.INFO, "HistoriaResource getHistoria: input: {0}", historiaId);
        
        HistoriaEntity historiaEntity = historiaLogic.getHistoria(historiaId);
        
        if(historiaEntity == null){
            throw new WebApplicationException("The resource /historias/" + historiaId + "doesn't exist.", 404);
        }
        
        LOGGER.log(Level.INFO, "HistoriaResource getCliente: output: {0}", historiaEntity.toString());
        
        return new HistoriaDTO(historiaEntity);
    }
    
    /**
     * Devuelve todas las historias creadas.
     * @return List<HistoriaDTO>
     */
    @GET
    public List<HistoriaDTO> getHistorias(){
        
        LOGGER.info("HistoriaResource getHistorias: input: void");
        
        List<HistoriaDTO> listaHistorias = listEntity2DTO(historiaLogic.getHistorias());
        
        LOGGER.log(Level.INFO, "HistoriaResource getHistorias: output: {0}", listaHistorias.toString());
        
        return listaHistorias;
    }
    
    /**
     * Actualiza la informacion de una historia.
     * Retorna la historia actualizada.
     * @param historiaId
     * @param historia
     * @return HistoriaDTO
     */
    @PUT
    @Path("{historiaId: \\d+}")
    public HistoriaDTO updateHistoria(@PathParam("historiaId") Long historiaId, HistoriaDTO historia) throws BusinessLogicException{
        historia.setId(historiaId);
        if (historiaLogic.getHistoria(historiaId) == null) {
            throw new WebApplicationException("The resource /historias/" + historiaId + "doesn't exist.", 404);            
        }
        return new HistoriaDTO(historiaLogic.updateHistoria(historiaId, historia.toEntity()));
    }
    
    @DELETE
    @Path("{historiaId: \\d+}")
    public void deleteHistoria(@PathParam("historiaId") Long historiaId) throws BusinessLogicException{
        HistoriaEntity clienteEntity = historiaLogic.getHistoria(historiaId);
        if (historiaLogic.getHistoria(historiaId) == null) {
            throw new WebApplicationException("The resource /clientes/" + historiaId + "doesn't exist.", 404);            
        }
        historiaLogic.deleteHistoria(clienteEntity);
    }
    
    private List<HistoriaDTO> listEntity2DTO(List<HistoriaEntity> entityList){
        List<HistoriaDTO> list = new ArrayList<>();
        for(HistoriaEntity m : entityList){
            list.add(new HistoriaDTO(m));
        }
        return list;
    }
}
