/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionToMascotaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaAdopcionPersistence;
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
 * @author Sebastian Mujica
 */
@RunWith(Arquillian.class)
public class MascotaAdopcionToMascotaLogicTest {
    
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private MascotaAdopcionToMascotaLogic mascotaAdopcionToMascotaLogic;
    
    @Inject
    private MascotaAdopcionLogic mascotaAdopcionLogic;
    
     @Inject
    private MascotaLogic mascotaLogic;
    
     @PersistenceContext
    private EntityManager em;
     
    @Inject
    private UserTransaction utx;
    
    private MascotaAdopcionEntity mascotaAdopcionA;
    
    private MascotaEntity mascotaB;
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaAdopcionEntity.class.getPackage())
                .addPackage(MascotaAdopcionToMascotaLogic.class.getPackage())
                .addPackage(MascotaAdopcionPersistence.class.getPackage())
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
        em.createQuery("delete from MascotaAdopcionEntity").executeUpdate();
        em.createQuery("delete from MascotaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        MascotaAdopcionEntity entityC = factory.manufacturePojo(MascotaAdopcionEntity.class);
        MascotaEntity entityA = factory.manufacturePojo(MascotaEntity.class);
        while (entityA.getEdad() < 0) {
            entityA = factory.manufacturePojo(MascotaEntity.class);
        }
        try 
        {
            mascotaAdopcionA = mascotaAdopcionLogic.crearMascotaAdopcion(entityC);
            mascotaB = mascotaLogic.crearMascota(entityA);
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(AdopcionCalificacionLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para crear la relacion entre MascotaAdopcion y Mascota.
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void addMascotaTest() throws BusinessLogicException
    {
        //El metodo ConfigTest no funciona correctamente en mi maquina local, debido a esto llamo al metodo insertData directamente
        //insertData();
        MascotaEntity respuesta = mascotaAdopcionToMascotaLogic.addMascota(mascotaB.getId(), mascotaAdopcionA.getId());
        MascotaAdopcionEntity rtaFinal = mascotaAdopcionLogic.getMascotaAdopcion(mascotaAdopcionA.getId());
        Assert.assertTrue(rtaFinal.getMascota().getNombre().equals(respuesta.getNombre()));
    }
    
     /**
     * Prueba para obtener la mascota a partir del id de la MascotaAdopcion que la contiene
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void getCalificacionTest() throws BusinessLogicException
    {
        //insertData();
        mascotaAdopcionToMascotaLogic.addMascota(mascotaB.getId(), mascotaAdopcionA.getId());
        Assert.assertEquals(mascotaB.getNombre(), mascotaAdopcionToMascotaLogic.getMascota(mascotaB.getId(), mascotaAdopcionA.getId()).getNombre());
    }
    
    
    /**
     * Prueba para reemplazar la calificacion de una adopcion
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void replaceMascotaTest() throws BusinessLogicException
    {
        //El metodo ConfigTest no funciona correctamente en mi maquina local, debido a esto llamo al metodo insertData directamente
        //insertData();
        mascotaAdopcionToMascotaLogic.addMascota(mascotaB.getId(), mascotaAdopcionA.getId());
        mascotaB.setColor("dorado");
        try{
            mascotaAdopcionToMascotaLogic.replaceMascota(mascotaAdopcionA.getId(), null);
            Assert.fail("Debio generar error");
        }
        catch(BusinessLogicException e){
            //Debe llegar aquí
        }
        Assert.assertEquals("dorado", mascotaAdopcionToMascotaLogic.replaceMascota(mascotaAdopcionA.getId(), mascotaB).getColor());
    }
    
    @Test
    public void removeMascotaTest() throws BusinessLogicException
    {
        //El metodo ConfigTest no funciona correctamente en mi maquina local, debido a esto llamo al metodo insertData directamente
        //insertData();
        mascotaAdopcionToMascotaLogic.addMascota(mascotaB.getId(), mascotaAdopcionA.getId());
        mascotaAdopcionToMascotaLogic.removeMascota(mascotaAdopcionA.getId(), mascotaB.getId());
        Assert.assertNull(mascotaLogic.getMascota(mascotaB.getId()));
        Assert.assertTrue(mascotaAdopcionLogic.getMascotaAdopcion(mascotaAdopcionA.getId()).getMascota() == null);
    }
    
}
