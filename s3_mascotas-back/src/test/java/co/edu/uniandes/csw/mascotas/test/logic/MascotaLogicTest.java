/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.EspecieLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaLogic;
import co.edu.uniandes.csw.mascotas.entities.EspecieEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.EspeciePersistence;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author lemus
 */
@RunWith(Arquillian.class)
public class MascotaLogicTest {
    

    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private MascotaLogic mascotaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<MascotaEntity> listaDatos = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaEntity.class.getPackage())
                .addPackage(MascotaLogic.class.getPackage())
                .addPackage(MascotaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }

    @Before
    public void configTest(){
        try{
            utx.begin();
            em.joinTransaction();
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

    private void clearData(){
        em.createQuery("delete from MascotaEntity").executeUpdate();
    }

    private void insertData(){
        for(int i= 0; i<10;i++){
            MascotaEntity nueva = factory.manufacturePojo(MascotaEntity.class);
            nueva.setDeleted(Boolean.FALSE);
            em.persist(nueva);
            listaDatos.add(nueva);
        }
    }
    
    @Test
    public void createMascotaTest() throws BusinessLogicException {
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
        int edad = newEntity.getEdad();
        if (edad < 0) {
            newEntity.setEdad((-1) * edad);
        }
        MascotaEntity result = mascotaLogic.crearMascota(newEntity);
        Assert.assertNotNull(result);
        MascotaEntity entity = em.find(MascotaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getColor(), entity.getColor());
        Assert.assertEquals(newEntity.getEdad(), entity.getEdad());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getGenero(), entity.getGenero());
    }
    
    @Test
    public void getMascotaTest() {
        MascotaEntity entity = listaDatos.get(0);
        MascotaEntity resultEntity = mascotaLogic.getMascota(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getColor(), resultEntity.getColor());
        Assert.assertEquals(entity.getEdad(), resultEntity.getEdad());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getGenero(), resultEntity.getGenero());
    }
    
    @Test
    public void getMascotasTest() {
        List<MascotaEntity> list = mascotaLogic.getMascotas();
        Assert.assertEquals(listaDatos.size(), list.size());
        for (MascotaEntity entity : list) {
            boolean found = false;
            for (MascotaEntity storedEntity : listaDatos) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void updateMascotaVentaTest()throws BusinessLogicException{
        MascotaEntity entity = listaDatos.get(0);
        MascotaEntity pojoEntity = factory.manufacturePojo(MascotaEntity.class);
        pojoEntity.setId(entity.getId());
        mascotaLogic.updateMascota(entity, pojoEntity);
        MascotaEntity resp= em.find(MascotaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getEdad(), resp.getEdad());
        Assert.assertEquals(pojoEntity.getColor(), resp.getColor());
        Assert.assertEquals(pojoEntity.getGenero(), resp.getGenero());
        
    }
}
