/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.MascotaVentaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaVentaToMascotaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaVentaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaVentaPersistence;

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
 * @author estudiante
 */
@RunWith(Arquillian.class)

public class MascotaVentaToMascotaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private MascotaVentaToMascotaLogic mascotaVentaToMascotaLogic;
    
    @Inject
    private MascotaVentaLogic mascotaVentaLogic;
    
     @Inject
    private MascotaLogic mascotaLogic;
    
     @PersistenceContext
    private EntityManager em;
     
    @Inject
    private UserTransaction utx;
    
    private MascotaVentaEntity mascotaVentaA;
    
    private MascotaEntity mascotaB;
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaVentaEntity.class.getPackage())
                .addPackage(MascotaVentaToMascotaLogic.class.getPackage())
                .addPackage(MascotaVentaPersistence.class.getPackage())
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
        em.createQuery("delete from MascotaVentaEntity").executeUpdate();
        em.createQuery("delete from MascotaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        MascotaVentaEntity entityC = factory.manufacturePojo(MascotaVentaEntity.class);
        MascotaEntity entityA = factory.manufacturePojo(MascotaEntity.class);
        try 
        {
            mascotaVentaA = mascotaVentaLogic.crearMascotaVenta(entityC);
            mascotaB = mascotaLogic.crearMascota(entityA);
        } 
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(AdopcionCalificacionLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para crear la relacion entre MascotaVenta y Mascota.
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void addMascotaTest() throws BusinessLogicException
    {
        //El metodo ConfigTest no funciona correctamente en mi maquina local, debido a esto llamo al metodo insertData directamente
        insertData();
        MascotaEntity respuesta = mascotaVentaToMascotaLogic.addMascota(mascotaB.getId(), mascotaVentaA.getId());
        MascotaVentaEntity rtaFinal = mascotaVentaLogic.getMascotaVenta(mascotaVentaA.getId());
        Assert.assertTrue(rtaFinal.getMascota().getNombre().equals(respuesta.getNombre()));
    }
    
     /**
     * Prueba para obtener la mascota a partir del id de la MascotaVenta que la contiene
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void getCalificacionTest() throws BusinessLogicException
    {
        insertData();
        mascotaVentaToMascotaLogic.addMascota(mascotaB.getId(), mascotaVentaA.getId());
        Assert.assertEquals(mascotaB.getNombre(), mascotaVentaToMascotaLogic.getMascota(mascotaB.getId(), mascotaVentaA.getId()).getNombre());
    }
    
    
    /**
     * Prueba para reemplazar la calificacion de una MascotaVenta
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void replaceMascotaTest() throws BusinessLogicException
    {
        //El metodo ConfigTest no funciona correctamente en mi maquina local, debido a esto llamo al metodo insertData directamente
        insertData();
        mascotaVentaToMascotaLogic.addMascota(mascotaB.getId(), mascotaVentaA.getId());
        mascotaB.setColor("dorado");
        try{
            mascotaVentaToMascotaLogic.replaceMascota(mascotaVentaA.getId(), null);
            Assert.fail("Debio generar error");
        }
        catch(BusinessLogicException e){
            //Debe llegar aquí
        }
        Assert.assertEquals("dorado", mascotaVentaToMascotaLogic.replaceMascota(mascotaVentaA.getId(), mascotaB).getColor());
    }
    
    @Test
    public void removeMascotaTest() throws BusinessLogicException
    {
        //El metodo ConfigTest no funciona correctamente en mi maquina local, debido a esto llamo al metodo insertData directamente
        insertData();
        mascotaVentaToMascotaLogic.addMascota(mascotaB.getId(), mascotaVentaA.getId());
        mascotaVentaToMascotaLogic.removeMascota(mascotaVentaA.getId(), mascotaB.getId());
        Assert.assertNull(mascotaLogic.getMascota(mascotaB.getId()));
        Assert.assertTrue(mascotaVentaLogic.getMascotaVenta(mascotaVentaA.getId()).getMascota() == null);
    }
    
}
