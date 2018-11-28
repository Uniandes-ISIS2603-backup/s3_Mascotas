/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.EspecieEntity;
import co.edu.uniandes.csw.mascotas.persistence.EspeciePersistence;
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
public class EspeciePersistenceTest {
    
    @Inject
    private EspeciePersistence especiePersistence;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<EspecieEntity> data = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EspecieEntity.class.getPackage())
                .addPackage(EspeciePersistence.class.getPackage())
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
        em.createQuery("delete u from EspecieEntity u").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EspecieEntity entity = factory.manufacturePojo(EspecieEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una Especie.
     */
    @Test
    public void createEspecieTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EspecieEntity newEntity = factory.manufacturePojo(EspecieEntity.class);
        EspecieEntity result = especiePersistence.create(newEntity);
        Assert.assertNotNull(result);
        EspecieEntity entity = em.find(EspecieEntity.class, result.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para consultar la lista de Especies.
     */
    @Test
    public void getEspeciesTest() {
        List<EspecieEntity> list = especiePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EspecieEntity especie : list) 
        {
            boolean found = false;
            for (EspecieEntity entity : data) 
            {
                if (especie.getId().equals(entity.getId()))
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una Especie.
     */
    @Test
    public void getEspecieTest() {
        EspecieEntity entity = data.get(0);
        EspecieEntity newEntity = especiePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getRazas().size(), newEntity.getRazas().size());
    }
    
    /**
     * Prueba para actualizar una Especie.
     */
    @Test
    public void updateEspecieTest() {
        EspecieEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EspecieEntity newEntity = factory.manufacturePojo(EspecieEntity.class);

        newEntity.setId(entity.getId());

        especiePersistence.update(newEntity);

        EspecieEntity resp = em.find(EspecieEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
    
    /**
     * Prueba para eliminar una Especie.
     */
    @Test
    public void deleteEspecieTest() {
        EspecieEntity entity = data.get(0);
        especiePersistence.delete(entity.getId());
        EspecieEntity deleted = em.find(EspecieEntity.class, entity.getId());
        Assert.assertEquals(Boolean.TRUE, deleted.getDeleted());
    }
}
