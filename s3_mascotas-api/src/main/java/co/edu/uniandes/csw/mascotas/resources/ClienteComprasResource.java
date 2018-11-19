/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.CompraDTO;
import co.edu.uniandes.csw.mascotas.ejb.ClienteComprasLogic;
import co.edu.uniandes.csw.mascotas.ejb.CompraLogic;
import co.edu.uniandes.csw.mascotas.entities.CompraEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Juan Sebastian Gomez, Camilo Pinilla
 */@Path("clientes/(clienteId :\\d+)/compras")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteComprasResource {
    private static final Logger LOGGER = Logger.getLogger(ClienteComprasResource.class.getName());
    
    @Inject
    private ClienteComprasLogic clienteCompraLogic;
    
    @Inject
    private CompraLogic compraLogic;
    
    @POST
    @Path("/{compraId: \\d+}")
    public CompraDTO addCompra(@PathParam("clienteId") Long clienteId, @PathParam("compraId") Long compraId) throws BusinessLogicException
    {
        if(compraLogic.getCompra(compraId) == null){
            throw new WebApplicationException("El recurso /compras/" + compraId + " no existe.", 404);
        }
        CompraDTO compraDTO = new CompraDTO(clienteCompraLogic.addCompra(compraId, clienteId));
        LOGGER.log(Level.INFO, "ClienteCompraResource addCompra: output: {0}", compraDTO.toString());
        return compraDTO;
    }    
    @GET
    public List<CompraDTO> getCompras(@PathParam("clienteId") Long clienteId)
    {
        LOGGER.log(Level.INFO, "ClienteCompraResource getCompras(): input: {0}", clienteId);
        List<CompraEntity> comprasAs =clienteCompraLogic.getCompras(clienteId);
        List<CompraDTO> compras = new ArrayList<>();
        for(CompraEntity c: comprasAs){
            compras.add(new CompraDTO(c));
        }
        LOGGER.log(Level.INFO, "ClienteComprasResource getCompras: output: {0}", compras.toString());
        return compras;
    }
}
