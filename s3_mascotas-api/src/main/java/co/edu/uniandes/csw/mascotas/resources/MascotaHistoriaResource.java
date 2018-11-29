/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.HistoriaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaHistoriaLogic;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Cristhian Peña
 */
@Path("mascotas/{mascotasId : \\d+}/historias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MascotaHistoriaResource {
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
        return new HistoriaDTO(mascotaHistoriaLogic.obtenerHistoria(mascotaId));
    }
}
