/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
import co.edu.uniandes.csw.mascotas.entities.CompraEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ClientePersistence;
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
 * @author Camilo Pinilla
 */
@RunWith(Arquillian.class)
public class ClientePersistenceTest {
    
    PodamFactory fabrica = new PodamFactoryImpl();
    
    /**
     * Inyección de la dependencia a la clase ClientePersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ClientePersistence clientePersistence;
    
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    private UserTransaction utx;
    
    /**
     * Lista que tiene los datos de prueba.
     */
    private List<ClienteEntity> data = new ArrayList<ClienteEntity>();
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Cliente, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompraEntity.class.getPackage())
                .addPackage(CompraPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
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
            catch(Exception ed){
                ed.printStackTrace();
            }
        }
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData(){
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData(){
        for(int i= 0; i<10;i++){
            ClienteEntity nueva = fabrica.manufacturePojo(ClienteEntity.class);
            em.persist(nueva);
            data.add(nueva);
        }
    }
    
    /**
     * Prueba para crear un Cliente.
     */
    @Test
    public void createClienteTest(){
        ClienteEntity newEntity = fabrica.manufacturePojo(ClienteEntity.class);
        ClienteEntity result = clientePersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        ClienteEntity ent = em.find(ClienteEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getTelefono(),ent.getTelefono());
        Assert.assertEquals(newEntity.getDireccion(), ent.getDireccion(),0);
        Assert.assertEquals(newEntity.getTarjetaCreditoRegistrada(),ent.getTarjetaCreditoRegistrada());
    }
    
    /**
     * Prueba para consultar un Cliente por id.
     */
    @Test
    public void getClienteTest(){
        ClienteEntity entity = data.get(0);
        ClienteEntity newEntity = clientePersistence.find(entity.getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(),newEntity.getId());
        Assert.assertEquals(entity.getTelefono(),newEntity.getTelefono(),0);
        Assert.assertEquals(entity.getDireccion(),newEntity.getDireccion());
        Assert.assertEquals(entity.getTarjetaCreditoRegistrada(),newEntity.getTarjetaCreditoRegistrada());
    }
    
    @Test
    public void updateClienteTest()throws BusinessLogicException{
        ClienteEntity entity = data.get(0);
        ClienteEntity newEntity = fabrica.manufacturePojo(ClienteEntity.class);
        
        newEntity.setId(entity.getId());
        clientePersistence.update(newEntity);
        
        ClienteEntity compareEntity = em.find(ClienteEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getId(), compareEntity.getId());
        Assert.assertEquals(newEntity.getTelefono(),compareEntity.getTelefono(),0);
        Assert.assertEquals(newEntity.getDireccion(),compareEntity.getDireccion());
        Assert.assertEquals(newEntity.getTarjetaCreditoRegistrada(),compareEntity.getTarjetaCreditoRegistrada());
    }
}
