/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.ClienteDTO;
import co.edu.uniandes.csw.mascotas.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.ClienteLogic;
import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
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
@Path("clientes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ClienteResource {
    
    @Inject
    private ClienteLogic clienteLogic;
    
    // Debug logger
    private static final Logger LOGGER = Logger.getLogger(ClienteResource.class.getName());
    
    /**
     * Crea un cliente con la información ingresada como parametro.
     * Retorna un cliente creado.
     * @param cliente
     * @return ClienteDTO
     * @throws BusinessLogicException 
     */
    @POST
    public ClienteDTO createCliente(ClienteDTO cliente)throws BusinessLogicException{
        
        LOGGER.info("ClienteResource createMascota: input: "+ cliente.toString());
        
        ClienteEntity clienteEntity = cliente.toEntity();
        ClienteEntity newClienteEntity = clienteLogic.createCliente(clienteEntity);
        
        ClienteDTO newClienteDTO = new ClienteDTO(newClienteEntity);
        
        LOGGER.info("ClienteResource createMascota: output: "+ newClienteDTO.toString());
        
        return newClienteDTO;
    }

    /**
     * Devuelve un cliente dado su id.
     * Retorna el cliente con el id enviado como parametro.
     * @param clienteId
     * @return ClienteDTO
     */
    @GET
    @Path("{clienteId: \\d+}")
    public ClienteDetailDTO getCliente(@PathParam("clienteId") Long clienteId){
        
        LOGGER.log(Level.INFO, "ClienteResource getCliente: input: {0}", clienteId);
        
        ClienteEntity clienteEntity = clienteLogic.getCliente(clienteId);
        
        if(clienteEntity == null){
            throw new WebApplicationException("The resource /clientes/" + clienteId + "doesn't exist.", 404);
        }
        
        LOGGER.log(Level.INFO, "CLienteResource getCliente: output: {0}", clienteEntity.toString());
        
        return new ClienteDetailDTO(clienteEntity);
    }
    
    /**
     * Devuelve todos los clientes creados.
     * @return List<ClienteDTO>
     */
    @GET
    public List<ClienteDetailDTO> getClientes(){
        
        LOGGER.info("ClienteResource getClientes: input: void");
        
        List<ClienteDetailDTO> listaClientes = listEntity2DTO(clienteLogic.getClientes());
        
        LOGGER.log(Level.INFO, "MascotaResource getMascotas: output: {0}", listaClientes.toString());
        
        return listaClientes;
    }
    
    /**
     * Actualiza la informacion de un cliente.
     * Retorna el cliente actualizado.
     * @param clienteId
     * @param cliente
     * @return ClienteDTO
     */
    @PUT
    @Path("{clienteId: \\d+}")
    public ClienteDTO updateCliente(@PathParam("clienteId") Long clienteId, ClienteDTO cliente) throws BusinessLogicException{
        cliente.setId(clienteId);
        if (clienteLogic.getCliente(clienteId) == null) {
            throw new WebApplicationException("The resource /clientes/" + clienteId + "doesn't exist.", 404);            
        }
        return new ClienteDTO(clienteLogic.updateCliente(clienteId, cliente.toEntity()));
    }
    
    @DELETE
    @Path("{clienteId: \\d+}")
    public void deleteCliente(@PathParam("clienteId") Long clienteId) throws BusinessLogicException{
        ClienteEntity clienteEntity = clienteLogic.getCliente(clienteId);
        if (clienteEntity == null) {
            throw new WebApplicationException("The resource /clientes/" + clienteId + "doesn't exist.", 404);            
        }
        clienteLogic.deleteCliente(clienteId);
    }
    
    private List<ClienteDetailDTO> listEntity2DTO(List<ClienteEntity> entityList){
        List<ClienteDetailDTO> list = new ArrayList<>();
        for(ClienteEntity m : entityList){
            list.add(new ClienteDetailDTO(m));
        }
        return list;
    }
    
     @Path("{clienteId: \\d+}/compras")
    public Class<ClienteComprasResource> getClienteComprasResource(@PathParam("clienteId") Long clienteId) {
        if (clienteLogic.getCliente(clienteId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clienteId + " no existe.", 404);
        }
        return ClienteComprasResource.class;
    }
}
