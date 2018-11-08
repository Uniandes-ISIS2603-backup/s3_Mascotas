/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.ClienteDTO;
import co.edu.uniandes.csw.mascotas.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.mascotas.dtos.HistoriaDTO;
import co.edu.uniandes.csw.mascotas.dtos.MascotaDTO;
import co.edu.uniandes.csw.mascotas.dtos.RazaDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.ClienteHistoriaLogic;
import co.edu.uniandes.csw.mascotas.ejb.ClienteLogic;
import co.edu.uniandes.csw.mascotas.ejb.ClienteMascotaLogic;
import co.edu.uniandes.csw.mascotas.ejb.RazaLogic;
import co.edu.uniandes.csw.mascotas.ejb.RazaMascotaLogic;
import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
import co.edu.uniandes.csw.mascotas.entities.HistoriaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
 * @author Camilo Pinilla
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteHistoriaResource {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteHistoriaResource.class.getName());
    
    @Inject
    private ClienteHistoriaLogic clienteHistoriaLogic;
    
    @Inject
    private ClienteLogic clienteLogic;
    
    /**
     * Relaciona una mascota existente con una raza existente
     * @return JSON {@link RazaDetailDTO} - La raza asociada
     */
    @POST
    @Path("{historiasId: \\d+}")
    public HistoriaDTO addHistoria(ClienteDTO cliente, @PathParam("historiasId") Long historiasId)throws BusinessLogicException{
        
        LOGGER.log(Level.INFO,"ClienteHistoriaResource addHistoria:input:historiaId:{0}:");
        HistoriaDTO historia = new HistoriaDTO(clienteHistoriaLogic.addHistoria(cliente.getId(), historiasId));
        return historia;
    }

    /**
     * Retorna todas las mascotas asociadas con un cliente
     * @param clienteId
     * @return colección de mascotas del cliente
     */
    @GET
    public List<HistoriaDTO> getHistorias(@PathParam("clienteId") Long clienteId){
        return listEntity2DTO(clienteHistoriaLogic.getHistorias(clienteId));
    }
    
    @GET
    @Path("/{historiasId: \\d+}")
    public HistoriaDTO getMascota(@PathParam("clienteId") Long clienteId, @PathParam("mascotasId") Long mascotasId) throws BusinessLogicException{
        ClienteEntity c = clienteLogic.getCliente(clienteId);
        if (c == null || c.getDeleted()) {
            throw new WebApplicationException("the resource /clientes/" + clienteId + " doesn't exists.", 404);
        }
        return new HistoriaDTO(clienteHistoriaLogic.getHistoria(clienteId, mascotasId));
    }
    
    @DELETE
    @Path("{historiasId: \\d+}")
    public void removeHistoria(@PathParam("clienteId") Long clienteId, @PathParam("historiasId") Long historiasId){
        ClienteEntity c = clienteLogic.getCliente(clienteId);
        if (c == null || c.getDeleted()) {
            throw new WebApplicationException("the resource /clientes/" + clienteId + " doesn't exists.", 404);
        }
        clienteHistoriaLogic.removeHistoria(clienteId, historiasId);
    }
    
    private List<HistoriaDTO> listEntity2DTO(List<HistoriaEntity> entityList){
        List<HistoriaDTO> list = new ArrayList<>();
        for(HistoriaEntity m : entityList){
            list.add(new HistoriaDTO(m));
        }
        return list;
    }
}
