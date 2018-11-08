/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.ClienteDTO;
import co.edu.uniandes.csw.mascotas.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.mascotas.dtos.MascotaDTO;
import co.edu.uniandes.csw.mascotas.dtos.RazaDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.ClienteLogic;
import co.edu.uniandes.csw.mascotas.ejb.ClienteMascotaLogic;
import co.edu.uniandes.csw.mascotas.ejb.RazaLogic;
import co.edu.uniandes.csw.mascotas.ejb.RazaMascotaLogic;
import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
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
@Path("clientes/(clienteId :\\d+)/mascotas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteMascotaResource {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteMascotaResource.class.getName());
    
    @Inject
    private ClienteMascotaLogic clienteMascotaLogic;
    
    @Inject
    private ClienteLogic clienteLogic;
    
    /**
     * Relaciona una mascota existente con una raza existente
     * @return JSON {@link RazaDetailDTO} - La raza asociada
     */
    @POST
    @Path("{mascotasId: \\d+}")
    public MascotaDTO addMascota(ClienteDTO cliente, @PathParam("mascotasId") Long mascotasId)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "ClienteMascotaResource addMascota: input: mascotaID:{0}:");
        MascotaDTO mascotaDTO = new MascotaDTO(clienteMascotaLogic.addMascota(cliente.getId(), mascotasId));
        return mascotaDTO;
    }

    /**
     * Retorna todas las mascotas asociadas con un cliente
     * @param clienteId
     * @return colección de mascotas del cliente
     */
    @GET
    public List<MascotaDTO> getMascotas(@PathParam("clienteId") Long clienteId){
        return listEntity2DTO(clienteMascotaLogic.getMascotas(clienteId));
    }
    
    @GET
    @Path("/{mascotasId: \\d+}")
    public MascotaDTO getMascota(@PathParam("clienteId") Long clienteId, @PathParam("mascotasId") Long mascotasId) throws BusinessLogicException{
        ClienteEntity c = clienteLogic.getCliente(clienteId);
        if (c == null || c.getDeleted()) {
            throw new WebApplicationException("the resource /clientes/" + clienteId + " doesn't exists.", 404);
        }
        return new MascotaDTO(clienteMascotaLogic.getMascota(clienteId, mascotasId));
    }
    
    @DELETE
    @Path("{mascotasId: \\d+}")
    public void removeMascota(@PathParam("clienteId") Long clienteId, @PathParam("mascotasId") Long mascotasId){
        ClienteEntity c = clienteLogic.getCliente(clienteId);
        if (c == null || c.getDeleted()) {
            throw new WebApplicationException("the resource /clientes/" + clienteId + " doesn't exists.", 404);
        }
        clienteMascotaLogic.removeMascota(clienteId, mascotasId);
    }
    
    private List<MascotaDTO> listEntity2DTO(List<MascotaEntity> entityList){
        List<MascotaDTO> list = new ArrayList<>();
        for(MascotaEntity m : entityList){
            list.add(new MascotaDTO(m));
        }
        return list;
    }
}
