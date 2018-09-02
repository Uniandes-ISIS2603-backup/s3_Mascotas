/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaAdopcionDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionLogic;
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
@Path("mascotaAdopcion")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class MascotaAdopcionResource {
     @Inject
    MascotaAdopcionLogic mascotaAdopcionLogic;
    
    private static final Logger LOGGER = Logger.getLogger(MascotaAdopcionResource.class.getName());
    
    
      /**
     * Crea una nueva mascotaAdopcion con la información proporcionada en el cuerpo
     * de la petición. Retorna un objeto idéntico con un id autogenerado.
     * @param mascotaAdopcion
     * @return
     * @throws BusinessLogicException 
     */
    @POST
    public MascotaAdopcionDTO crearMascotaAdopcion(MascotaAdopcionDTO mascotaAdopcion)throws BusinessLogicException{
        return null;
    }
    

    @GET
    @Path("{mascotaAdopcionId: \\d+}")
    public MascotaAdopcionDTO getMascota(@PathParam("mascotaAdopcionId") Long mascotaId){
        return null;
    }
    
    @DELETE
    @Path("{mascotaAdopcionId: \\d+}")
    public void deleteMascota(@PathParam("mascotaAdopcionId") Long mascotasId, MascotaAdopcionDTO mascotaAdopcion) throws BusinessLogicException{

    }
    
    
}
