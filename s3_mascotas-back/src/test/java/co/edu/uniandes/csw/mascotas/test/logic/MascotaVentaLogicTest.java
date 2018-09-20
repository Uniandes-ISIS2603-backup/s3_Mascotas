/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.MascotaVentaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaVentaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaVentaPersistence;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)

public class MascotaVentaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private MascotaVentaLogic mascotaVentaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<MascotaVentaEntity> mascotaVentaData = new ArrayList<MascotaVentaEntity>();
    
    private List<MascotaEntity> mascotasData = new ArrayList<MascotaEntity>();
    
    /**
     * @return devuelve el jar que Arquillian va a desplegar en payar emebedio.
     * El jar contiene las clases, el descriptor de la base de datos y el 
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaVentaEntity.class.getPackage())
                .addPackage(MascotaVentaLogic.class.getPackage())
                .addPackage(MascotaVentaPersistence.class.getPackage())
                .addAsManifestResource("META-inf/persistence.xml" , "persistence.xml")
                 .addAsManifestResource("META-inf/beans.xml" , "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest(){
        try{
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            }
            catch(Exception e1){
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
     * Inserta los valores iniciales para la prueba
     */
    private void insertData() {
        for(int i = 0; i<3 ; i++){
            MascotaVentaEntity mascotaVenta = factory.manufacturePojo(MascotaVentaEntity.class);
            em.persist(mascotaVenta);
            mascotaVentaData.add(mascotaVenta);
        }
        for(int i = 0; i<3 ; i++){
            MascotaEntity mascota = factory.manufacturePojo(MascotaEntity.class);
            em.persist(mascota);
            mascotasData.add(mascota);
        }
    }
    
    /**
     * Prueba para crear una MascotaVenta.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createMascotaVentaTest() throws BusinessLogicException {
        MascotaVentaEntity newEntity = factory.manufacturePojo(MascotaVentaEntity.class);
        MascotaVentaEntity result = mascotaVentaLogic.crearMascotaVenta(newEntity);
        Assert.assertNotNull(result);
        MascotaVentaEntity entity = em.find(MascotaVentaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDocumentosPedegree(), entity.getDocumentosPedegree());
    }
    
     /**
     * Prueba para consultar la lista de mascotaVenta.
     */
    @Test
    public void getMascotasVentaTest() {
        List<MascotaVentaEntity> list = mascotaVentaLogic.getMascotasVenta();
        Assert.assertEquals(mascotaVentaData.size(), list.size());
        for (MascotaVentaEntity entity : list) {
            boolean found = false;
            for (MascotaVentaEntity storedEntity : mascotaVentaData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     /**
     * Prueba para consultar una MascotaVenta.
     */
    @Test
    public void getMascotaVentaTest() {
        MascotaVentaEntity entity = mascotaVentaData.get(0);
        MascotaVentaEntity resultEntity = mascotaVentaLogic.getMascotaVenta(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getDocumentosPedegree(), resultEntity.getDocumentosPedegree());
    }
    
    /**
     * Prueba para actualizar una mascotaVenta.
     */
    @Test
    public void updateMascotaVentaTest() throws BusinessLogicException {
        MascotaVentaEntity entity = mascotaVentaData.get(0);
        MascotaVentaEntity pojoEntity = factory.manufacturePojo(MascotaVentaEntity.class);
        pojoEntity.setId(entity.getId());
        mascotaVentaLogic.updateMascotaVenta(pojoEntity.getId(), pojoEntity);
        MascotaVentaEntity resp= em.find(MascotaVentaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertNotEquals(entity.getDocumentosPedegree(), resp.getDocumentosPedegree());
    }
    
    
    /**
     * Prueba para eliminar una MascotaVenta.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteMascotaVentaTest() throws BusinessLogicException {
        MascotaVentaEntity entity = mascotaVentaData.get(1);
        mascotaVentaLogic.deleteMascotaVenta(entity.getId());
        MascotaVentaEntity deleted = em.find(MascotaVentaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
