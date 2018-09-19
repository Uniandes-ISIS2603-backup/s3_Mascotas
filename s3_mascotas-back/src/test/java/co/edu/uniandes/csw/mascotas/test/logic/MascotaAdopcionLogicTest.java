/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionLogic;
import org.jboss.arquillian.junit.Arquillian;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MascotaAdopcionPersistence;
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


import org.junit.runner.RunWith;

/**
 *Pruebas de lógica de MascotaAdopcion
 * 
 * @author Sebastian Mujica
 */
@RunWith(Arquillian.class)
public class MascotaAdopcionLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private MascotaAdopcionLogic mascotaAdopcionLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<MascotaAdopcionEntity> mascotaAdopcionData = new ArrayList<MascotaAdopcionEntity>();
    
    private List<MascotaEntity> mascotasData = new ArrayList<MascotaEntity>();
    
    /**
     * @return devuelve el jar que Arquillian va a desplegar en payar emebedio.
     * El jar contiene las clases, el descriptor de la base de datos y el 
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaAdopcionEntity.class.getPackage())
                .addPackage(MascotaAdopcionLogic.class.getPackage())
                .addPackage(MascotaAdopcionPersistence.class.getPackage())
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
        em.createQuery("delete from MascotaAdopcionEntity").executeUpdate();
        em.createQuery("delete from MascotaEntity").executeUpdate();
    }

    /**
     * Inserta los valores iniciales para la prueba
     */
    private void insertData() {
        for(int i = 0; i<3 ; i++){
            MascotaAdopcionEntity mascotaAdopcion = factory.manufacturePojo(MascotaAdopcionEntity.class);
            em.persist(mascotaAdopcion);
            mascotaAdopcionData.add(mascotaAdopcion);
        }
        for(int i = 0; i<3 ; i++){
            MascotaEntity mascota = factory.manufacturePojo(MascotaEntity.class);
            em.persist(mascota);
            mascotasData.add(mascota);
        }
    }
    
    /**
     * Prueba para crear una MascotaAdopcion.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createMascotaAdopcionTest() throws BusinessLogicException {
        MascotaAdopcionEntity newEntity = factory.manufacturePojo(MascotaAdopcionEntity.class);
        MascotaAdopcionEntity result = mascotaAdopcionLogic.crearMascotaAdopcion(newEntity);
        Assert.assertNotNull(result);
        MascotaAdopcionEntity entity = em.find(MascotaAdopcionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getHistoria(), entity.getHistoria());
    }
    
     /**
     * Prueba para consultar la lista de mascotasAdopcion.
     */
    @Test
    public void getMascotasAdopcionTest() {
        List<MascotaAdopcionEntity> list = mascotaAdopcionLogic.getMascotasAdopcion();
        Assert.assertEquals(mascotaAdopcionData.size(), list.size());
        for (MascotaAdopcionEntity entity : list) {
            boolean found = false;
            for (MascotaAdopcionEntity storedEntity : mascotaAdopcionData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     /**
     * Prueba para consultar un MascotaAdopcion.
     */
    @Test
    public void getMascotaAdopcionTest() {
        MascotaAdopcionEntity entity = mascotaAdopcionData.get(0);
        MascotaAdopcionEntity resultEntity = mascotaAdopcionLogic.getMascotaAdopcion(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getHistoria(), resultEntity.getHistoria());
    }
    
    /**
     * Prueba para actualizar una MascotaAdopcion.
     */
    @Test
    public void updateMascotaAdopcionTest() {
        MascotaAdopcionEntity entity = mascotaAdopcionData.get(0);
        MascotaAdopcionEntity pojoEntity = factory.manufacturePojo(MascotaAdopcionEntity.class);
        pojoEntity.setId(entity.getId());
        mascotaAdopcionLogic.updateMascotaAdopcion(pojoEntity.getId(), pojoEntity);
        MascotaAdopcionEntity resp= em.find(MascotaAdopcionEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertNotEquals(entity.getHistoria(), resp.getHistoria());
    }
    
    
    /**
     * Prueba para eliminar una MascotaAdopcion.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteMascotaAdopcionTest() throws BusinessLogicException {
        MascotaAdopcionEntity entity = mascotaAdopcionData.get(1);
        mascotaAdopcionLogic.deleteMascotaAdopcion(entity.getId());
        MascotaAdopcionEntity deleted = em.find(MascotaAdopcionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}




    
