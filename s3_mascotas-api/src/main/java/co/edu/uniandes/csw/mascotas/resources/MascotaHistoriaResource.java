/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.HistoriaDTO;
import co.edu.uniandes.csw.mascotas.dtos.MascotaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaHistoriaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Cristhian Peña
 */
@Path("mascotas/{mascotasId : \\d+}/historias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MascotaHistoriaResource 
{
    private static final Logger LOGGER = Logger.getLogger(MascotaHistoriaResource.class.getName());
    @Inject
    private MascotaLogic mascotaLogic;
    @Inject
    private MascotaHistoriaLogic mascotaHistoriaLogic;
    
    /**
     * Crea una historia y la relaciona con una mascota existente
     * @param mascotaId
     * @param historia
     * @return Instancia de la mascota visible al usuario
     * @throws BusinessLogicException 
     */
    @POST
    public MascotaDTO añadirHistoria(@PathParam("mascotasId") Long mascotaId, HistoriaDTO historia) throws BusinessLogicException
    {
        MascotaEntity mascota = mascotaLogic.getMascota(mascotaId);
        if(mascota == null || mascota.getDeleted()) {
            throw new WebApplicationException("the resource /mascotas/" + mascotaId + " doesn't exists.", 404);
        }
        MascotaDTO mascotaRetorno = new MascotaDTO(mascotaHistoriaLogic.añadirHistoria(mascotaId, historia.toEntity()));
        return mascotaRetorno;
    }
}
