/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.CompraEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.CompraPersistence;
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
 * @author Juan Sebastian Gomez
 */
@RunWith(Arquillian.class)
public class CompraPersistenceTest {
    PodamFactory fabricaDePollos = new PodamFactoryImpl();
    @Inject
    private CompraPersistence compraPersistence;
    @PersistenceContext
    private EntityManager em;
    @Inject
    private UserTransaction ut;
    private List<CompraEntity> listaDatos= new ArrayList<CompraEntity>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompraEntity.class.getPackage())
                .addPackage(CompraPersistence.class.getPackage())
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
            CompraEntity nueva = fabricaDePollos.manufacturePojo(CompraEntity.class);
            em.persist(nueva);
            listaDatos.add(nueva);
        }
    }
    @Test
    public void createCompraTest(){
        CompraEntity nueva= fabricaDePollos.manufacturePojo(CompraEntity.class);
        CompraEntity result = compraPersistence.create(nueva);
        Assert.assertNotNull(result);
        CompraEntity ent = em.find(CompraEntity.class, result.getId());
        Assert.assertEquals(nueva.getCalificacion(),ent.getCalificacion());
        Assert.assertEquals(nueva.getPrecio(), ent.getPrecio(),0);
        Assert.assertEquals(nueva.getTipoDePago(),ent.getTipoDePago());
        
    }
    
    @Test
    public void getCompraTest(){
        CompraEntity cojamosla = listaDatos.get(0);
        CompraEntity busquemosla = compraPersistence.find(cojamosla.getId());
        Assert.assertNotNull(busquemosla);
        Assert.assertEquals(cojamosla.getId(),busquemosla.getId());
        Assert.assertEquals(cojamosla.getPrecio(),busquemosla.getPrecio(),0);
        Assert.assertEquals(cojamosla.getTipoDePago(),busquemosla.getTipoDePago());
    }
    
    @Test
    public void getComprasTest(){
        List<CompraEntity> lista = compraPersistence.findAll();
        Assert.assertEquals(listaDatos.size(),lista.size());
        boolean mandeloAFail = false;
        
        for(CompraEntity c:lista){
            for(CompraEntity d:listaDatos){
                if(c.equals(d)){
                    break;
                }
                if(d.equals(listaDatos.get(listaDatos.size()-1))){
                    mandeloAFail = true;
                }
            }
            if(mandeloAFail){
                Assert.fail("No encontró");
            }
        }
    }
    
    @Test
    public void updateTest()throws BusinessLogicException{
        CompraEntity aEditar = listaDatos.get(0);
        CompraEntity hagamosUnPollo = fabricaDePollos.manufacturePojo(CompraEntity.class);
        hagamosUnPollo.setId(aEditar.getId());
        compraPersistence.update(hagamosUnPollo);
        CompraEntity aComparar = em.find(CompraEntity.class, aEditar.getId());
        Assert.assertEquals(hagamosUnPollo.getId(), aComparar.getId());
        Assert.assertEquals(hagamosUnPollo.getPrecio(),aComparar.getPrecio(),0);
        Assert.assertEquals(hagamosUnPollo.getTipoDePago(),aComparar.getTipoDePago());
    }
}
