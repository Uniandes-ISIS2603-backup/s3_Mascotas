/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ClientePersistence;
import java.util.ArrayList;
import java.util.Collections;
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
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
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
        for(int i= 0; i<3;i++){
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
        
        Assert.assertEquals(newEntity.getCorreo(),ent.getCorreo());
        Assert.assertEquals(newEntity.getDireccion(), ent.getDireccion());
        Assert.assertEquals(newEntity.getTarjetaCreditoRegistrada(),ent.getTarjetaCreditoRegistrada());
    }
    
    /**
     * Prueba para consultar todos los clientes
     */
    @Test
    public void getClientesTest(){
        List<ClienteEntity> clientes = clientePersistence.findAll();
        Assert.assertEquals(clientes.size(),data.size());
        for(int i = 0; i < clientes.size(); i++){
            for(int j = 0; j < data.size(); j++){
                if(clientes.get(i).getId().compareTo(data.get(j).getId()) == 0){
                    Assert.assertEquals(clientes.get(i).getCorreo(),data.get(j).getCorreo());
                    Assert.assertEquals(clientes.get(i).getDireccion(), data.get(j).getDireccion());
                    Assert.assertEquals(clientes.get(i).getTarjetaCreditoRegistrada(),data.get(j).getTarjetaCreditoRegistrada());
                }
            }
        }
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
        Assert.assertEquals(entity.getCorreo(),newEntity.getCorreo());
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
        Assert.assertEquals(newEntity.getCorreo(),compareEntity.getCorreo());
        Assert.assertEquals(newEntity.getDireccion(),compareEntity.getDireccion());
        Assert.assertEquals(newEntity.getTarjetaCreditoRegistrada(),compareEntity.getTarjetaCreditoRegistrada());
    }
    
    @Test
    public void deleteClienteTest(){
        ClienteEntity cliente = data.get(data.size()-1);
        clientePersistence.delete(cliente.getId());
        
        Assert.assertEquals(clientePersistence.findAll().size(), data.size()-1);
    }
    
    @Test
    public void findCorreoTest(){
        ClienteEntity cliente = data.get(0);
        ClienteEntity rta = clientePersistence.findByCorreo(cliente.getCorreo());
        Assert.assertNotNull(rta);
        ClienteEntity cliente2 = fabrica.manufacturePojo(ClienteEntity.class);
        ClienteEntity rta2 = clientePersistence.findByCorreo(cliente2.getCorreo());
        Assert.assertNull(rta2);
    }
}
