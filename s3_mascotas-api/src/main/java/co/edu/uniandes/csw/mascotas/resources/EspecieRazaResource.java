/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.ejb.EspecieLogic;
import co.edu.uniandes.csw.mascotas.ejb.RazaLogic;
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
 * Clase que implementa el recurso "books/{id}/authors".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("especies/{especiesId : \\d+}/razas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EspecieRazaResource 
{
    private static final Logger LOGGER = Logger.getLogger(EspecieRazaResource.class.getName());
    
    @Inject 
    private EspecieLogic especieLogic;
    
    @Inject
    private RazaLogic razaLogic;
 

  
}
