/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

/**
 *
 * @author Juan Sebasti{an G{omez
 */
import co.edu.uniandes.csw.mascotas.dtos.CompraDTO;
import co.edu.uniandes.csw.mascotas.ejb.CompraLogic;
import co.edu.uniandes.csw.mascotas.entities.CompraEntity;
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
@Path("compras")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CompraResource {
    @Inject
    private CompraLogic compraLogic;
    
    private static final Logger LOGGER = Logger.getLogger(CompraResource.class.getName());
    
       @GET
    public List<CompraDTO> getCompras(){
        LOGGER.info("CompraResource getCompras: input: void");
        List<CompraDTO> listaCompra = listEntity2DTO(compraLogic.getCompras());
        LOGGER.log(Level.INFO, "CompraResource getCompras: output: {0}", listaCompra.toString());
        return listaCompra;
    }
    @POST
    public CompraDTO crearCompra(CompraDTO compra)throws BusinessLogicException{
        
        LOGGER.info("CompraResource crearCompra: input: "+ compra.toString());
        CompraEntity adopEntity = compra.toEntity();
        CompraEntity nuevoAdopEntity = compraLogic.crearCompra(adopEntity);
        CompraDTO nuevaAdopcionDTO = new CompraDTO(nuevoAdopEntity);
        LOGGER.info("AdopcionResource crearAdopcion: output: "+ nuevaAdopcionDTO.toString());
        return nuevaAdopcionDTO;
    }

    @GET
    @Path("{comprasId: \\d+}")
    public CompraDTO getCompra(@PathParam("comprasId") Long compraId){
        LOGGER.log(Level.INFO, "CompraResource getCompra: input: {0}", compraId);
        CompraEntity compraEntity = compraLogic.getCompra(compraId);
        if(compraEntity == null){
            throw new WebApplicationException("The resource /compras/" + compraId + "doesn't exist.", 404);
        }
        LOGGER.log(Level.INFO, "CompraResource getCompra: output: {0}", compraEntity.toString());
        return new CompraDTO(compraEntity);
    }
    

    
    @PUT
    @Path("{comprasId: \\d+}")
    public CompraDTO updateCompra(@PathParam("comprasId") Long compraId, CompraDTO compra) throws BusinessLogicException{
        compra.setId(compraId);
        if (compraLogic.getCompra(compraId) == null) {
            throw new WebApplicationException("The resource /comrpas/" + compraId + "doesn't exist.", 404);            
        }
        return new CompraDTO(compraLogic.updateCompra(compraId, compra.toEntity()));
    }
     private List<CompraDTO> listEntity2DTO(List<CompraEntity> entityList){
        List<CompraDTO> list = new ArrayList<>();
        for(CompraEntity m : entityList){
            list.add(new CompraDTO(m));
        }
        return list;
    }
     
     @Path("{comprasId: \\d+}/calificaciones")
    public Class<CompraCalificacionResource> getCompraCalificacionResource(@PathParam("comprasId") Long compraId) {
        if (compraLogic.getCompra(compraId) == null) {
            throw new WebApplicationException("El recurso /compras/" + compraId + " no existe.", 404);
        }
        return CompraCalificacionResource.class;
    }
}
