/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.CompraLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaLogic;
import co.edu.uniandes.csw.mascotas.entities.CompraEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.CompraPersistence;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
 * @author Juan Sebastian Gomez (El Gomito)
 */
@RunWith(Arquillian.class)
public class CompraLogicTest {
    private PodamFactory podam = new PodamFactoryImpl();
    @Inject
    private CompraLogic compraLogic;
    @PersistenceContext
    private EntityManager em;
    @Inject
    private UserTransaction ut;
    private ArrayList<CompraEntity> listaDatos = new ArrayList<>();
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompraEntity.class.getPackage())
                .addPackage(MascotaEntity.class.getPackage())
                .addPackage(CompraLogic.class.getPackage())
                .addPackage(CompraPersistence.class.getPackage())
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
        em.createQuery("delete from CompraEntity").executeUpdate();
        em.createQuery("delete from MascotaEntity").executeUpdate();
    }
    private void insertData(){
        for(int i = 0; i<10;i++){
            CompraEntity nueva = podam.manufacturePojo(CompraEntity.class);
            
            MascotaEntity mascota = podam.manufacturePojo(MascotaEntity.class);
            mascota.setDeleted(Boolean.FALSE);
            em.persist(mascota);
            em.persist(nueva);
            listaDatos.add(nueva);
        }
    }
    @Test
    public void crearCompraTest()throws BusinessLogicException{
        CompraEntity nueva = podam.manufacturePojo(CompraEntity.class);
        
        Query q = em.createQuery("select u from MascotaEntity u");
        List<MascotaEntity> m = q.getResultList();
        nueva.setMascota(m.get(0));
        
        CompraEntity resultado = compraLogic.crearCompra(nueva);
        Assert.assertNotNull(resultado);
        CompraEntity vamoAComparar = em.find(CompraEntity.class, resultado.getId());
        Assert.assertEquals(nueva.getId(), vamoAComparar.getId());
        Assert.assertEquals(nueva.getPrecio(),vamoAComparar.getPrecio(),0);
        Assert.assertEquals(nueva.getTipoDePago(),vamoAComparar.getTipoDePago());
    }
    @Test
    public void getComprasTest(){
        List<CompraEntity> lista = compraLogic.getCompras();
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
    public void getCompraTest(){
        CompraEntity cojamosla = listaDatos.get(0);
        CompraEntity busquemosla = compraLogic.getCompra(cojamosla.getId());
        Assert.assertNotNull(busquemosla);
        Assert.assertEquals(cojamosla.getId(),busquemosla.getId());
        Assert.assertEquals(cojamosla.getPrecio(),busquemosla.getPrecio(),0);
        Assert.assertEquals(cojamosla.getTipoDePago(),busquemosla.getTipoDePago());
    }
    @Test
    public void updateTest()throws BusinessLogicException{
        CompraEntity aEditar = listaDatos.get(0);
        CompraEntity hagamosUnPollo = podam.manufacturePojo(CompraEntity.class);
        hagamosUnPollo.setId(aEditar.getId());
        compraLogic.updateCompra(hagamosUnPollo.getId(),hagamosUnPollo);
        CompraEntity aComparar = em.find(CompraEntity.class, aEditar.getId());
        Assert.assertEquals(hagamosUnPollo.getId(), aComparar.getId());
        Assert.assertEquals(hagamosUnPollo.getPrecio(),aComparar.getPrecio(),0);
        Assert.assertEquals(hagamosUnPollo.getTipoDePago(),aComparar.getTipoDePago());
    }
}
