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
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    private MascotaHistoriaLogic mascotaHistoriaLogic;
    
    /**
     * Retorna todas las razas asociadas con la especie especificada
     * @param mascotaId mascota
     * @return colección de razas de la especie
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @GET
    public HistoriaDTO obtenerHistoria(@PathParam("mascotasId") Long mascotaId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Begins query of story related to pet id = {0}", mascotaId);
        return new HistoriaDTO(mascotaHistoriaLogic.obtenerHistoria(mascotaId));
    }
}
