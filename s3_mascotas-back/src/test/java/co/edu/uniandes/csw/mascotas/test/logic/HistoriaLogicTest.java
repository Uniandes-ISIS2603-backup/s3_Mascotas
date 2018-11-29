/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;


import co.edu.uniandes.csw.mascotas.ejb.HistoriaLogic;
import co.edu.uniandes.csw.mascotas.entities.HistoriaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.HistoriaPersistence;
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
 * @author cc.pena
 */
@RunWith(Arquillian.class)
public class HistoriaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private HistoriaLogic historiaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction ut;
    
    private List<HistoriaEntity> listaDatos = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HistoriaEntity.class.getPackage())
                .addPackage(HistoriaLogic.class.getPackage())
                .addPackage(HistoriaPersistence.class.getPackage())
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
        em.createQuery("delete from HistoriaEntity").executeUpdate();
    }
    
    private void insertData(){
        for(int i= 0; i<10;i++){
            HistoriaEntity nueva = factory.manufacturePojo(HistoriaEntity.class);
            nueva.setDeleted(Boolean.FALSE);
            em.persist(nueva);
            listaDatos.add(nueva);
        }
    }
    
    @Test
    public void createHistoriaTest() throws BusinessLogicException {
        HistoriaEntity newEntity = factory.manufacturePojo(HistoriaEntity.class);
        HistoriaEntity result = historiaLogic.createHistoria(newEntity);
        Assert.assertNotNull(result);
        HistoriaEntity entity = em.find(HistoriaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTexto(), entity.getTexto());
    }

    @Test
    public void getHistoriasTest() {
        List<HistoriaEntity> list = historiaLogic.getHistorias();
        Assert.assertEquals(listaDatos.size(), list.size());
        for (HistoriaEntity entity : list) {
            boolean found = false;
            for (HistoriaEntity storedEntity : listaDatos) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void updateHistoriaTest()throws BusinessLogicException{
        HistoriaEntity entity = listaDatos.get(0);
        HistoriaEntity pojoEntity = factory.manufacturePojo(HistoriaEntity.class);
        pojoEntity.setId(entity.getId());
        historiaLogic.updateHistoria(entity.getId(), pojoEntity);
        HistoriaEntity resp= em.find(HistoriaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getTexto(), resp.getTexto());
    }
}
