/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.EspecieDTO;
import co.edu.uniandes.csw.mascotas.dtos.EspecieDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.EspecieLogic;
import co.edu.uniandes.csw.mascotas.entities.EspecieEntity;
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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Cristhian Peña
 */
@Path("especies")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EspecieResource 
{
    private static final String NA1 = "El recurso /especies/";
    private static final String NA2= " no existe.";
    private static final Logger LOGGER = Logger.getLogger(EspecieResource.class.getName());
    @Inject
    private EspecieLogic especieLogic;
    
    /**
     * Crea una nueva especie con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param especie {@link EspecieDTO} - La especie que se desea guardar.
     * @return JSON {@link EspecieDTO} - La especie guardada con el atributo id
     * autogenerado.
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @POST
    public EspecieDTO createEspecie(EspecieDTO especie) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EspecieResource crearEspecie: input: {0}", especie);
        EspecieDTO especieDTO = new EspecieDTO(especieLogic.crearEspecie(especie.toEntity()));
        LOGGER.log(Level.INFO, "EspecieResource crearEspecie: output: {0}", especieDTO);
        return especieDTO;
    }
    
    /**
     * Busca y devuelve todas las especies que existen en la aplicacion.
     *
     * @return JSONArray {@link EspecieDetailDTO} - Las especies encontradas en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<EspecieDetailDTO> darEspecies(){
        
        LOGGER.info("EspecieResource darEspecies: input: void");
        List<EspecieDetailDTO> lista = convertirLista(especieLogic.getEspecies());
        LOGGER.log(Level.INFO, "EspecieResource getEspecies: output: {0}", "Especies");
        return lista;
    }
    
    /**
     * Busca la especie con el id asociado recibido en la URL y lo devuelve.
     *
     * @param especieId Identificador de la especie que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link EspecieDetailDTO} - La especie buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la especie.
     */
    @GET
    @Path("{especieId: \\d+}")
    public EspecieDetailDTO getEspecie(@PathParam("especieId") Long especieId) {
        LOGGER.log(Level.INFO, "EspecieResource getSpecies: input: {0}", especieId);
        EspecieEntity especieEntity = especieLogic.getSpecies(especieId);
        if (especieEntity == null) {
            throw new WebApplicationException(NA1 + especieId + NA2, 404);
        }
        EspecieDetailDTO detailDTO = new EspecieDetailDTO(especieEntity);
        LOGGER.log(Level.INFO, "EspecieResource getSpecies: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Actualiza la especie con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param especieId Identificador de la especie que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param especie {@link EspecieDetailDTO} La especie que se desea guardar.
     * @return JSON {@link EspecieDetailDTO} - La especie guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la especie a
     * actualizar.
     */
    @PUT
    @Path("{especieId: \\d+}")
    public EspecieDetailDTO updateEspecie(@PathParam("especieId") Long especieId, EspecieDetailDTO especie) {
        LOGGER.log(Level.INFO, "EspecieResource updateEspecie: input: especieId: {0} , especie: {1}", new Object[]{especieId, especie});
        especie.setId(especieId);
        if (especieLogic.getSpecies(especieId) == null) {
            throw new WebApplicationException(NA1 + especieId + NA2, 404);
        }
        EspecieDetailDTO detailDTO = new EspecieDetailDTO(especieLogic.updateSpecies(especieId, especie.toEntity()));
        LOGGER.log(Level.INFO, "EspecieResource updateEspecie: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Borra la especie con el id asociado recibido en la URL.
     *
     * @param especieId Identificador de la especie que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     * si la especie tiene razas asociadas
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra la especie a borrar.
     */
    @DELETE
    @Path("{especieId: \\d+}")
    public void deleteEspecie(@PathParam("especieId") Long especieId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EspecieResource deleteEspecie: input: {0}", especieId);
        if (especieLogic.getSpecies(especieId) == null) {
            throw new WebApplicationException(NA1 + especieId + NA2, 404);
        }
        especieLogic.deleteEspecie(especieId);
        LOGGER.info("EspecieResource deleteEspecie: output: void");
    }
    public List<EspecieDetailDTO> convertirLista(List<EspecieEntity> pLista)
    {
        List<EspecieDetailDTO> lista = new ArrayList<>();
        for (EspecieEntity tmp : pLista) {
            lista.add(new EspecieDetailDTO(tmp));
        }
        return lista;
    }
    
    @Path("{especieId: \\d+}/razas")
    public Class<EspecieRazaResource> getEspecieRazaResource(@PathParam("especieId") Long especieId) {
        if (especieLogic.getSpecies(especieId) == null) {
            throw new WebApplicationException(NA1 + especieId + NA2, 404);
        }
        return EspecieRazaResource.class;
    }
}
