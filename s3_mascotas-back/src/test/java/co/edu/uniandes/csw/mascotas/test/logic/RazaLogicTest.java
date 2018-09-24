/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;


import co.edu.uniandes.csw.mascotas.ejb.RazaLogic;
import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.RazaPersistence;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author lemus
 */
@RunWith(Arquillian.class)
public class RazaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private RazaLogic razaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction ut;
    
    private List<RazaEntity> listaDatos = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RazaEntity.class.getPackage())
                .addPackage(RazaLogic.class.getPackage())
                .addPackage(RazaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Before
    public void configTest(){
        try{
            ut.begin();
            em.joinTransaction();
            clearData();
            insertData();
            ut.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                ut.rollback();
            }
            catch(Exception e1){
                e1.printStackTrace();
            }
        }
    }

    private void clearData(){
        em.createQuery("delete from RazaEntity").executeUpdate();
    }
    
    private void insertData(){
        for(int i= 0; i<10;i++){
            RazaEntity nueva = factory.manufacturePojo(RazaEntity.class);
            nueva.setDeleted(Boolean.FALSE);
            em.persist(nueva);
            listaDatos.add(nueva);
        }
    }
    
    @Test
    public void createRazaTest() throws BusinessLogicException {
        RazaEntity newEntity = factory.manufacturePojo(RazaEntity.class);
        RazaEntity result = razaLogic.crearRaza(newEntity);
        Assert.assertNotNull(result);
        RazaEntity entity = em.find(RazaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    @Test
    public void getRazasTest() {
        List<RazaEntity> list = razaLogic.getRazas();
        Assert.assertEquals(listaDatos.size(), list.size());
        for (RazaEntity entity : list) {
            boolean found = false;
            for (RazaEntity storedEntity : listaDatos) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void updateMascotaVentaTest()throws BusinessLogicException{
        RazaEntity entity = listaDatos.get(0);
        RazaEntity pojoEntity = factory.manufacturePojo(RazaEntity.class);
        pojoEntity.setId(entity.getId());
        razaLogic.updateRaza(entity, pojoEntity);
        RazaEntity resp= em.find(RazaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }
}
