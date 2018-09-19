/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.EspecieLogic;
import co.edu.uniandes.csw.mascotas.entities.EspecieEntity;
import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *  
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class EspecieLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private EspecieLogic especieLogic;
    
     @PersistenceContext
    private EntityManager em;
     
    @Inject
    private UserTransaction utx;
    
    private List<RazaEntity> razaData = new ArrayList<>();
    
    private List<EspecieEntity> especieData = new ArrayList<EspecieEntity>();
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EspecieEntity.class.getPackage())
                .addPackage(EspecieLogic.class.getPackage())
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
        em.createQuery("delete from EspecieEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        for(int i = 0; i < 2; i++){
            RazaEntity razaEntity = factory.manufacturePojo(RazaEntity.class);
            em.persist(razaEntity);
            razaData.add(razaEntity);
        }
        for (int i = 0; i < 3; i++) {
            EspecieEntity especieEntity = factory.manufacturePojo(EspecieEntity.class);
            if(i == 0)
            {
                especieEntity.setRazas(razaData);
            }
            em.persist(especieEntity);
            especieData.add(especieEntity);
        }
    }
    
    /**
     * Prueba para crear una Especie.
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void createEspecieTest() throws BusinessLogicException
    {
        EspecieEntity newEspecie = factory.manufacturePojo(EspecieEntity.class);
        EspecieEntity result = especieLogic.crearEspecie(newEspecie);
        Assert.assertNotNull(result);
        EspecieEntity entity = em.find(EspecieEntity.class, result.getId());
        Assert.assertEquals(newEspecie.getId(), entity.getId());
        Assert.assertEquals(newEspecie.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para crear una Especie con el mismo nombre de una Especie que ya
     * existe.
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createEspecieConMismoNombreTest() throws BusinessLogicException {
        EspecieEntity newEntity = factory.manufacturePojo(EspecieEntity.class);
        newEntity.setNombre(especieData.get(0).getNombre());
        especieLogic.crearEspecie(newEntity);
    }
    
    /**
     * Prueba para consultar la lista de Especies.
     */
    @Test
    public void getEspeciesTest() {
        List<EspecieEntity> list = especieLogic.getEspecies();
        Assert.assertEquals(especieData.size(), list.size());
        for (EspecieEntity entity : list) {
            boolean found = false;
            for (EspecieEntity storedEntity : especieData) {
                if (entity.getId().equals(storedEntity.getId())) {
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
        EspecieEntity entity = especieData.get(0);
        EspecieEntity resultEntity = especieLogic.getSpecies(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }
    
    /**
     * Prueba para actualizar una Especie.
     */
    @Test
    public void updateEspecieTest() {
        EspecieEntity entity = especieData.get(0);
        EspecieEntity pojoEntity = factory.manufacturePojo(EspecieEntity.class);
        pojoEntity.setId(entity.getId());
        especieLogic.updateSpecies(pojoEntity.getId(), pojoEntity);
        EspecieEntity resp = em.find(EspecieEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }
    
    /**
     * Prueba para eliminar una Especie.
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void deleteEspecieTest() throws BusinessLogicException {
        EspecieEntity entity = especieData.get(1);
        System.out.println(entity.getId());
        System.out.println(entity.getRazas().size());
        especieLogic.deleteEspecie(entity.getId());
        EspecieEntity deleted = em.find(EspecieEntity.class, entity.getId());
        Assert.assertTrue(deleted.getDeleted());
    }
    
    /**
     * Prueba para eliminar un Editorial con books asociados.
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    
    @Test(expected = BusinessLogicException.class)
    public void deleteEspecieConRazasAsociadasTest() throws BusinessLogicException {
        EspecieEntity entity = especieData.get(0);
        especieLogic.deleteEspecie(entity.getId());
    }
}
