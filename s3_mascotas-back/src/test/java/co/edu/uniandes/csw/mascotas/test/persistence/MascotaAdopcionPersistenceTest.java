/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.persistence.MascotaAdopcionPersistence;
import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
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
 *Pruebas de persistencia para MascotaAdopcion
 * 
 * @author Sebastian Mujica
 * 
 */
@RunWith(Arquillian.class)
public class MascotaAdopcionPersistenceTest {
    
    
    /**
     * Inyección de la dependencia a la clase MascotaAdopcionPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private MascotaAdopcionPersistence mascotaAdopcionPersistence;
    
    
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     * Lista que tiene los datos de prueba.
     */
    private List<MascotaAdopcionEntity> data = new ArrayList<>();
    

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de MascotaAdopcion, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaAdopcionEntity.class.getPackage())
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
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } 
        catch (Exception e) {
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
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from MascotaAdopcionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            MascotaAdopcionEntity entity = factory.manufacturePojo(MascotaAdopcionEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
     /**
     * Prueba para crear un MascotaAdopcion.
     *
     *
     */
    @Test
    public void createMascotaAdopcionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MascotaAdopcionEntity newEntity = factory.manufacturePojo(MascotaAdopcionEntity.class);
        MascotaAdopcionEntity result = mascotaAdopcionPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MascotaAdopcionEntity entity = em.find(MascotaAdopcionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getHistoria(), entity.getHistoria());
    }
    
    
     /**
     * Prueba para eliminar una mascotaAdopcion.
     *
     *
     */
    @Test
    public void deleteMascotaAdopcionTest() {
        
        MascotaAdopcionEntity entity = data.get(0);
        mascotaAdopcionPersistence.delete(entity.getId());
        MascotaAdopcionEntity deleted = em.find(MascotaAdopcionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para consultar un MascotaAdopcion por nombre.
     *
     *
     */
    @Test
    public void findMascotaAdopcionTest() {
        MascotaAdopcionEntity entity = data.get(0);
        MascotaAdopcionEntity newEntity = mascotaAdopcionPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getHistoria(), newEntity.getHistoria());
    }
    
    /**
     * Prueba para consultar todas las mascotasAdopcion
     */
    @Test
    public void finAllMascotasAdopcionTest(){
        List<MascotaAdopcionEntity> mascotasAdopcion= mascotaAdopcionPersistence.findAll();
        Assert.assertEquals(mascotasAdopcion.size(), data.size());
    }
    
    /**
     * Prueba para Actualizar el estado de una mascota.
     */
    @Test
    public void updateMascotaTest(){
        MascotaAdopcionEntity mascotaAdopcion = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MascotaAdopcionEntity newEntity= factory.manufacturePojo(MascotaAdopcionEntity.class);
        newEntity.setId(mascotaAdopcion.getId());
        mascotaAdopcionPersistence.update(newEntity);
        MascotaAdopcionEntity rta = em.find(MascotaAdopcionEntity.class, mascotaAdopcion.getId());
        Assert.assertEquals(rta.getId(), mascotaAdopcion.getId());
        Assert.assertNotEquals(rta.getHistoria(), mascotaAdopcion.getHistoria());

    }
    
}
