/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Sebastian Lemus
 */
@RunWith(Arquillian.class)
public class MascotaPersistenceTest {
    PodamFactory fabricaDePollos = new PodamFactoryImpl();
    @Inject
    private MascotaPersistence mascotaPersistence;
    @PersistenceContext
    private EntityManager em;
    @Inject
    private UserTransaction ut;
    private List<MascotaEntity> listaDatos= new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaEntity.class.getPackage())
                .addPackage(MascotaPersistence.class.getPackage())
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
        em.createQuery("delete from CompraEntity").executeUpdate();
    }
    private void insertData(){
        for(int i= 0; i<10;i++){
            MascotaEntity nueva = fabricaDePollos.manufacturePojo(MascotaEntity.class);
            em.persist(nueva);
            listaDatos.add(nueva);
        }
    }
    @Test
    public void createMascotaTest(){
        MascotaEntity nueva= fabricaDePollos.manufacturePojo(MascotaEntity.class);
        MascotaEntity result = mascotaPersistence.create(nueva);
        Assert.assertNotNull(result);
        MascotaEntity ent = em.find(MascotaEntity.class, result.getId());
        Assert.assertEquals(nueva.getColor(),ent.getColor());
        Assert.assertEquals(nueva.getEdad(), ent.getEdad(),0 );
        Assert.assertEquals(nueva.getGenero(),ent.getGenero());
        Assert.assertEquals(ent.getPrecio(), nueva.getPrecio(),0);
        Assert.assertEquals(ent.getNombre(),nueva.getNombre());
        
    }
    
    @Test
    public void getMascotaTest(){
        MascotaEntity cojamosla = listaDatos.get(0);
        MascotaEntity busquemosla = mascotaPersistence.find(cojamosla.getId());
        Assert.assertNotNull(busquemosla);
        Assert.assertEquals(cojamosla.getColor(),busquemosla.getColor());
        Assert.assertEquals(cojamosla.getEdad(), busquemosla.getEdad(),0 );
        Assert.assertEquals(cojamosla.getGenero(),busquemosla.getGenero());
        Assert.assertEquals(cojamosla.getPrecio(), busquemosla.getPrecio(),0);
        Assert.assertEquals(cojamosla.getNombre(),busquemosla.getNombre());
    }

    
    @Test
    public void updateTest()throws BusinessLogicException{
        MascotaEntity aEditar = listaDatos.get(0);
        MascotaEntity hagamosUnPollo = fabricaDePollos.manufacturePojo(MascotaEntity.class);
        hagamosUnPollo.setId(aEditar.getId());
        mascotaPersistence.update(hagamosUnPollo);
        MascotaEntity aComparar = em.find(MascotaEntity.class, aEditar.getId());
        Assert.assertEquals(hagamosUnPollo.getColor(),aComparar.getColor());
        Assert.assertEquals(hagamosUnPollo.getEdad(), aComparar.getEdad(),0 );
        Assert.assertEquals(hagamosUnPollo.getGenero(),aComparar.getGenero());
        Assert.assertEquals(hagamosUnPollo.getPrecio(), aComparar.getPrecio(),0);
        Assert.assertEquals(hagamosUnPollo.getNombre(),aComparar.getNombre());
    }
}
