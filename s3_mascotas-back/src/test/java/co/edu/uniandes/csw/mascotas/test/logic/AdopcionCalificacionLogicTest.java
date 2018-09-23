/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.AdopcionCalificacionLogic;
import co.edu.uniandes.csw.mascotas.ejb.AdopcionLogic;
import co.edu.uniandes.csw.mascotas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.mascotas.entities.AdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.CalificacionPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *  
 * @author Cristhian Peña
 */
@RunWith(Arquillian.class)
public class AdopcionCalificacionLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private AdopcionCalificacionLogic adopcionCalificacionLogic;
    
    @Inject
    private AdopcionLogic adopcionLogic;
    
     @Inject
    private CalificacionLogic calificacionLogic;
    
     @PersistenceContext
    private EntityManager em;
     
    @Inject
    private UserTransaction utx;
    
    private CalificacionEntity rtaC;
    
    private AdopcionEntity rtaA;
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(AdopcionCalificacionLogic.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from AdopcionEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        CalificacionEntity entityC = factory.manufacturePojo(CalificacionEntity.class);
        AdopcionEntity entityA = factory.manufacturePojo(AdopcionEntity.class);
        try 
        {
            rtaC = calificacionLogic.crearCalificacion(entityC);
            rtaA = adopcionLogic.crearAdopcion(entityA);
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(AdopcionCalificacionLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para crear la relacion entre Adopcion y Calificacion.
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void addCalificacionTest() throws BusinessLogicException
    {
        //El metodo ConfigTest no funciona correctamente en mi maquina local, debido a esto llamo al metodo insertData directamente
        insertData();
        adopcionCalificacionLogic.addCalificacion(rtaC.getId(), rtaA.getId());
        AdopcionEntity rtaFinal = adopcionLogic.getAdopcion(rtaA.getId());
        Assert.assertTrue(rtaFinal.getCalificacion().getComentarios().equals(rtaC.getComentarios()));
    }
    
    /**
     * Prueba para obtener la calificacion a partir del id de la adopcion que la contiene
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void getCalificacionTest() throws BusinessLogicException
    {
        //El metodo ConfigTest no funciona correctamente en mi maquina local, debido a esto llamo al metodo insertData directamente
        insertData();
        adopcionCalificacionLogic.addCalificacion(rtaC.getId(), rtaA.getId());
        Assert.assertEquals(rtaC.getComentarios(), adopcionCalificacionLogic.getCalificacion(rtaA.getId()).getComentarios());
    }
    
    /**
     * Prueba para reemplazar la calificacion de una adopcion
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void replaceCalificacionTest() throws BusinessLogicException
    {
        //El metodo ConfigTest no funciona correctamente en mi maquina local, debido a esto llamo al metodo insertData directamente
        insertData();
        adopcionCalificacionLogic.addCalificacion(rtaC.getId(), rtaA.getId());
        
        rtaC.setComentarios("Nuevo Comentario");
        rtaC.setPuntaje(4);
        try 
        {
            adopcionCalificacionLogic.replaceCalificacion(rtaA.getId(), null);
            Assert.fail("Debio generar exception");
        }
        catch (BusinessLogicException e) 
        {
            //Debe llegar aca
        }
        
        Assert.assertEquals("Nuevo Comentario", adopcionCalificacionLogic.replaceCalificacion(rtaA.getId(), rtaC).getComentarios());
    }
    
    @Test
    public void removeCalificacionTest() throws BusinessLogicException
    {
        //El metodo ConfigTest no funciona correctamente en mi maquina local, debido a esto llamo al metodo insertData directamente
        insertData();
        adopcionCalificacionLogic.addCalificacion(rtaC.getId(), rtaA.getId());
        adopcionCalificacionLogic.removeCalificacion(rtaA.getId(), rtaC.getId());
        Assert.assertEquals(Boolean.TRUE, calificacionLogic.getCalificacion(rtaC.getId()).getDeleted() );
        Assert.assertTrue(adopcionLogic.getAdopcion(rtaA.getId()).getCalificacion() == null);
    }
}
