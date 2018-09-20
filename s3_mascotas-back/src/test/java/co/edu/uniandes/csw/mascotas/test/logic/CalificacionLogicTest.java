/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.mascotas.entities.CalificacionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.CalificacionPersistence;
import java.util.ArrayList;
import java.util.List;
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
public class CalificacionLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private CalificacionLogic calificacionLogic;
    
     @PersistenceContext
    private EntityManager em;
     
    @Inject
    private UserTransaction utx;
    
    private List<CalificacionEntity> calificacionData = new ArrayList<>();
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
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
    }
    
     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) {
            CalificacionEntity calificacionEntity = factory.manufacturePojo(CalificacionEntity.class);
            em.persist(calificacionEntity);
            calificacionData.add(calificacionEntity);
        }
    }
    
    /**
     * Prueba para crear una Calificacion.
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void createEspecieTest() throws BusinessLogicException
    {
        CalificacionEntity newEspecie = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity result = calificacionLogic.crearCalificacion(newEspecie);
        Assert.assertNotNull(result);
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        Assert.assertEquals(newEspecie.getId(), entity.getId());
        Assert.assertEquals(newEspecie.getComentarios(), entity.getComentarios());
        
        CalificacionEntity newEspecie2 = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity result2 = calificacionLogic.crearCalificacion(newEspecie2);
        Assert.assertNotNull(result2);
        CalificacionEntity entity2 = em.find(CalificacionEntity.class, result2.getId());
        Assert.assertEquals(newEspecie2.getId(), entity2.getId());
        Assert.assertEquals(newEspecie2.getComentarios(), entity2.getComentarios());
    }
    
    
    /**
     * Prueba para crear una Calificacion.
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void darCalificacionesTest() throws BusinessLogicException
    {
        //Se agregaron dos calificaciones en el test anterior
        List<CalificacionEntity> list = calificacionLogic.getCalificaciones();
        Assert.assertEquals(calificacionData.size(), list.size());
        for (CalificacionEntity entity : list) {
            boolean found = false;
            for (CalificacionEntity storedEntity : calificacionData) {
                if (entity.getComentarios().equals(storedEntity.getComentarios())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para obtener una Calificacion a partir de un id.
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void getCalificacionTest() throws BusinessLogicException
    {
        CalificacionEntity newEspecie = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity result = calificacionLogic.crearCalificacion(newEspecie);
        CalificacionEntity retorno = calificacionLogic.getCalificacion(result.getId());
        Assert.assertEquals(result.getId(), retorno.getId());
        CalificacionEntity retorno2 = calificacionLogic.getCalificacion(Long.MAX_VALUE);
        Assert.assertNull(retorno2);
    }
    
    
}
