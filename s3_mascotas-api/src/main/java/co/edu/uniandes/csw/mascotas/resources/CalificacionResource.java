/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.CalificacionDTO;
import co.edu.uniandes.csw.mascotas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
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
import javax.ws.rs.GET;



/**
 *
 * @author Cristhian Peña
 */
@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource {
    
    @Inject
    CalificacionLogic calificacionLogic;
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());
    
    /**
     * Crea una nueva calificacion con la información proporcionada en el cuerpo
     * de la petición web. Retorna una calificacion con la informacion igual a la de entrada.
     * @return CalificacionDTO DTO con la misma informaciòn de entrada
     * @throws BusinessLogicException 
     */
    @GET
    public List<CalificacionDTO> darCalificaciones()throws BusinessLogicException{
        
        LOGGER.info("CalificacionResource darCalificaciones: input: void");
        List<CalificacionDTO> lista = convertirLista(calificacionLogic.getCalificaciones());
        LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: output: {0}", "Calificaciones");
        return lista;
    }
    
    public List<CalificacionDTO> convertirLista(List<CalificacionEntity> pLista)
    {
        List<CalificacionDTO> lista = new ArrayList<>();
        for (CalificacionEntity tmp : pLista) {
            lista.add(new CalificacionDTO(tmp));
        }
        return lista;
    }
}
