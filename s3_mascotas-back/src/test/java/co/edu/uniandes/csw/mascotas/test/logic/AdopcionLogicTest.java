/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.AdopcionLogic;
import co.edu.uniandes.csw.mascotas.entities.AdopcionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.AdopcionPersistence;
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
public class AdopcionLogicTest {
     private PodamFactory podam = new PodamFactoryImpl();
    @Inject
    private AdopcionLogic adopcionLogic;
    @PersistenceContext
    private EntityManager em;
    @Inject
    private UserTransaction ut;
    private ArrayList<AdopcionEntity> listaDatos = new ArrayList<>();
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdopcionEntity.class.getPackage())
                .addPackage(AdopcionLogic.class.getPackage())
                .addPackage(AdopcionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    @Before
    public void configTest(){
        try{
            ut.begin();
            clearData();
            insertData();
            ut.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                ut.rollback();
            }
            catch(Exception eu){
                eu.printStackTrace();
            }
        }
    }
    private void clearData(){
        em.createQuery("delete from AdopcionEntity").executeUpdate();
    }
    private void insertData(){
       
        for(int i = 0; i<10;i++){
            
            AdopcionEntity nueva = podam.manufacturePojo(AdopcionEntity.class);
            em.persist(nueva);
            listaDatos.add(nueva);
        }
        
    }
    @Test
    public void crearAdopcionTest()throws BusinessLogicException{
        
        AdopcionEntity nueva = podam.manufacturePojo(AdopcionEntity.class );
        
        AdopcionEntity resultado = adopcionLogic.crearAdopcion(nueva);

        Assert.assertNotNull(resultado);
        AdopcionEntity vamoAComparar = em.find(AdopcionEntity.class, resultado.getId());
        Assert.assertEquals(nueva.getId(), vamoAComparar.getId());
        Assert.assertEquals(nueva.getDocsAdopcion(),vamoAComparar.getDocsAdopcion());
        Assert.assertEquals(nueva.getCalificacion(), vamoAComparar.getCalificacion());
    }
    
    @Test
    public void getComprasTest(){
        List<AdopcionEntity> lista = adopcionLogic.getAdopciones();
        Assert.assertEquals(listaDatos.size(),lista.size());
        boolean mandeloAFail = false;
        
        for(AdopcionEntity c:lista){
            for(AdopcionEntity d:listaDatos){
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
    public void getCompraTest(){
        AdopcionEntity cojamosla = listaDatos.get(0);
        AdopcionEntity busquemosla = adopcionLogic.getAdopcion(cojamosla.getId());
        Assert.assertNotNull(busquemosla);
        Assert.assertEquals(cojamosla.getId(),busquemosla.getId());
        Assert.assertEquals(cojamosla.getDocsAdopcion(),busquemosla.getDocsAdopcion());
        Assert.assertEquals(cojamosla.getCalificacion(), busquemosla.getCalificacion());
    }
    @Test
    public void updateTest()throws BusinessLogicException{
        AdopcionEntity aEditar = listaDatos.get(0);
        AdopcionEntity hagamosUnPollo = podam.manufacturePojo(AdopcionEntity.class);
        hagamosUnPollo.setId(aEditar.getId());
        adopcionLogic.updateAdopcion(hagamosUnPollo.getId(),hagamosUnPollo);
        AdopcionEntity aComparar = em.find(AdopcionEntity.class, aEditar.getId());
        Assert.assertEquals(hagamosUnPollo.getId(), aComparar.getId());
        Assert.assertEquals(hagamosUnPollo.getDocsAdopcion(),aComparar.getDocsAdopcion());
        Assert.assertEquals(hagamosUnPollo.getCalificacion(), aComparar.getCalificacion());
    }
}
