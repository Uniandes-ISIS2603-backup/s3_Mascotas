/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaVentaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaVentaLogic;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.entities.MascotaVentaEntity;
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
 * Clase que implementa el recurso "mascotaVenta"
 *
 * @author Sebastian Mujica
 */

@Path("mascotaVenta")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped


public class MascotaVentaResource {
    
    /**
     * Injeción de dependencias que se realiza para manejar la lógica de una 
     * mascota de venta.
     */
    @Inject
    MascotaVentaLogic mascotaVentaLogic;
    
    
    /**
     * Logger que se utiliza para registrar información en la consola del 
     * programa durante la ejecución de este, Además, para guardar los
     * registros.
     */
    private static final Logger LOGGER = Logger.getLogger(MascotaVentaResource.class.getName());
    
    
      /**
     * Crea una nueva mascotaVenta con la información proporcionada en el cuerpo
     * de la petición. Retorna un objeto idéntico con un id autogenerado.
     * @param mascotaVenta
     * @return MascotaVenta que se creó con un id asignado por la BD.
     * @throws BusinessLogicException 
     */
    @POST
    public MascotaVentaDTO crearMascotaVenta(MascotaVentaDTO mascotaVenta)throws BusinessLogicException{
        LOGGER.info("mascotaAdopcionResource crearMascota: input: " + mascotaVenta.toString());
        MascotaVentaEntity mascotaVentaEntity = mascotaVenta.toEntity();
        MascotaVentaEntity nuevaMascotaVentaEntity = mascotaVentaLogic.crearMascotaVenta(mascotaVentaEntity);
        MascotaVentaDTO nuevaMascotaDTO = new MascotaVentaDTO(nuevaMascotaVentaEntity);
        LOGGER.info("MascotaAdopcionResource crearMascota: output: "+ nuevaMascotaDTO.toString());
        return nuevaMascotaDTO;
    }
    
    /**
     * Actualiza una  mascota venta con la información proporcionada en el 
     * cuerpo y el id proporcionado por el parámetro.
     * @param mascotaVentaId el id de la mascota de venta a actualizar.
     * @param mascotaVenta la información de la mascota que se va a actualizar.
     * @return MascotaVenta actualizada.
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{mascotaVentaId: \\d+}")
    public MascotaVentaDTO updateMascotaVenta(@PathParam("mascotaVentaId") Long mascotaVentaId, MascotaVentaDTO mascotaVenta)throws  BusinessLogicException{
        MascotaVentaEntity mascotaVentaEntity = mascotaVenta.toEntity();
        mascotaVentaEntity.setId(mascotaVentaId);
        MascotaVentaEntity buscado = mascotaVentaLogic.getMascotaVenta(mascotaVentaId);
        if(buscado== null){
            throw new WebApplicationException("The resource /mascotaVenta/" + mascotaVentaId + "doesn't exist.", 404);
        }
        return new MascotaVentaDTO(mascotaVentaLogic.updateMascotaVenta(mascotaVentaId, mascotaVentaEntity));
    }
    

    /**
     * Obtiene una mascota de venta con el id proporcionado por el usuario.
     * @param mascotaVentaId la mascota de venta que se busca.
     * @return la mascota de venta buscada.
     */
    @GET
    @Path("{mascotaVentaId: \\d+}")
    public MascotaVentaDTO getMascota(@PathParam("mascotaVentaId") Long mascotaVentaId){
        LOGGER.log(Level.INFO, "mascotaVentaResource getMascotaVenta : input: {0}", mascotaVentaId);
        MascotaVentaEntity mascotaEntity = mascotaVentaLogic.getMascotaVenta(mascotaVentaId);
        if(mascotaEntity == null){
            throw new WebApplicationException("The resource /mascotas/" + mascotaVentaId + "doesn't exist.", 404);
        }
        LOGGER.log(Level.INFO, "MascotaVentaResource getMascota: output: {0}", mascotaEntity.toString());
        return new MascotaVentaDTO(mascotaEntity);
    }
    
    /**
     * Elimina una mascota de venta con el id proporcionado.
     * @param mascotaVentaId
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{mascotaVentaId: \\d+}")
    public void deleteMascotaVenta(@PathParam("mascotaVentaId") Long mascotaVentaId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "MascotaVentaResource deleteMascotaVenta: input: {0}", mascotaVentaId);
        MascotaVentaEntity buscado = mascotaVentaLogic.getMascotaVenta(mascotaVentaId);
        if(buscado == null){
            throw  new WebApplicationException("The resource /mascotasVenta/" + mascotaVentaId + "doesn't exist.", 404);
        }
        mascotaVentaLogic.deleteMascotaVenta(mascotaVentaId);
        LOGGER.info("MascotaVenta deleteMascotaVenta: output: void");
    }
    
     /**
     * Conexión con el servicio de Mascota para una mascota Venta
     * {@link MascotaVentaToMascotaResource}
     * 
     * Este método conecte la ruta de /mascotasVenta con las rutas de /mascotas 
     * que dependen de la mascotaAdopcion, es una redirección al servicio que maneja
     * el segmento de la URL que se encarga de la mascota de una MascotaVenta
     * 
     * @param mascotaVentaId El Id de la masctoa con respecto a la cual se accede
     * al servicio
     * @return El servicio de mascota para esta mascotaVenta en particular
     * @throws WebApplicationException {@link WebApplicationException} 
     * error de la lógica que se genera cuando no se encuentra la mascotaVenta
     */
    @Path("{mascotaVentaId: \\d+}/mascotas")
    public Class<MascotaVentaToMascotaResource> getMascotaVentaToMascotaResource( @PathParam("mascotaVentaId") Long mascotaVentaId){
        if(mascotaVentaLogic.getMascotaVenta(mascotaVentaId)==null){
            throw new WebApplicationException("El recurso/MascotaVenta/" + mascotaVentaId + "no existe", 404);
        }
        return MascotaVentaToMascotaResource.class;
    }    
    

    
    /**
     * obtiene la lista de todas las mascotas de venta en la tienda.
     * @return JSONArray {@link mascotaVentaDTO} - Las mascotas de venta
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<MascotaVentaDTO> getMascotasVenta(){
        LOGGER.info("MascotaVentaResource getMascotasVenta : input : void");
        List<MascotaVentaDTO> listaMascotasVenta = listEntityToDTO(mascotaVentaLogic.getMascotasVenta());
        LOGGER.log(Level.INFO, "MascotaVentaResource getMascotasVenta: output: {0}", listaMascotasVenta.toString());
        return listaMascotasVenta;
    }

    
    
    /**
     * Permite convertir una lista de mascotas de venta tipo Entity a una lista
     * de mascotas de venta a tipo DTO
     * @param entityList la lista de mascotas de venta entity a convertir.
     * @return JSONArray {@link mascotaVentaDTO} - Las mascotas de venta
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía. 
     */
    private List<MascotaVentaDTO> listEntityToDTO(List<MascotaVentaEntity> entityList){
        List<MascotaVentaDTO> list = new ArrayList<>();
        for(MascotaVentaEntity entity : entityList) {
            list.add(new MascotaVentaDTO(entity));
        }
        return list;
    }
    
}
