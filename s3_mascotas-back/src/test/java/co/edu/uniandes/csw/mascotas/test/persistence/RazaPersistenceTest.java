/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

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
public class RazaPersistenceTest {
    
    PodamFactory fabricaDePollos = new PodamFactoryImpl();
    
    @Inject
    private RazaPersistence razaPersistence; 
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction ut;
    
    private List<RazaEntity> listaDatos = new ArrayList<>(); 
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RazaEntity.class.getPackage())
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
            catch(Exception ed){
                ed.printStackTrace();
            }
        }
    }
    
    private void clearData(){
        em.createQuery("delete from RazaEntity").executeUpdate();
    }

    private void insertData(){
        for(int i= 0; i<10;i++){
            RazaEntity nueva = fabricaDePollos.manufacturePojo(RazaEntity.class);
            em.persist(nueva);
            listaDatos.add(nueva);
        }
    }
    
    @Test
    public void createRazaTest(){
        RazaEntity nueva= fabricaDePollos.manufacturePojo(RazaEntity.class);
        RazaEntity result = razaPersistence.create(nueva);
        Assert.assertNotNull(result);
        RazaEntity ent = em.find(RazaEntity.class, result.getId());
        Assert.assertEquals(ent.getNombre(),nueva.getNombre());   
    }

    @Test
    public void getRazaTest(){
        RazaEntity cojamosla = listaDatos.get(0);
        RazaEntity busquemosla = razaPersistence.find(cojamosla.getId());
        Assert.assertNotNull(busquemosla);
        Assert.assertEquals(cojamosla.getNombre(),busquemosla.getNombre());
    }

    @Test
    public void updateTest()throws BusinessLogicException{
        RazaEntity aEditar = listaDatos.get(0);
        RazaEntity hagamosUnPollo = fabricaDePollos.manufacturePojo(RazaEntity.class);
        hagamosUnPollo.setId(aEditar.getId());
        razaPersistence.update(hagamosUnPollo);
        RazaEntity aComparar = em.find(RazaEntity.class, aEditar.getId());
        Assert.assertEquals(hagamosUnPollo.getNombre(),aComparar.getNombre());
    }
    
}
