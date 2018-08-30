/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaVentaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaVentaLogic;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Sebastian Mujica
 */

@Path("mascotaVenta")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped


public class MascotaVentaResource {
    
    @Inject
    MascotaVentaLogic mascotaAdopcionLogic;
    
    
    
    private static final Logger LOGGER = Logger.getLogger(MascotaVentaResource.class.getName());
    
    
      /**
     * Crea una nueva mascotaVenta con la información proporcionada en el cuerpo
     * de la petición. Retorna un objeto idéntico con un id autogenerado.
     * @param mascotaVenta
     * @return
     * @throws BusinessLogicException 
     */
    @POST
    public MascotaVentaDTO crearMascotaVenta(MascotaVentaDTO mascotaVenta)throws BusinessLogicException{
        return null;
    }
    

    @GET
    @Path("{mascotaVentaId: \\d+}")
    public MascotaVentaDTO getMascota(@PathParam("mascotaAdopcionId") Long mascotaVentaId){
        return null;
    }
    
    @DELETE
    @Path("{mascotaAdopcionId: \\d+}")
    public void deleteMascotaVenta(@PathParam("mascotaAdopcionId") Long mascotaVentaId, MascotaVentaDTO mascotaVenta) throws BusinessLogicException{
    }
    
}
