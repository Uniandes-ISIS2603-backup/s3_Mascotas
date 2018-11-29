/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

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
 * @author Camilo Pinilla
 */
@RunWith(Arquillian.class)
public class HistoriaPersistenceTest {

    PodamFactory fabrica = new PodamFactoryImpl();

    /**
     * Inyección de la dependencia a la clase ClientePersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private HistoriaPersistence historiaPersistence;

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
    private List<HistoriaEntity> data = new ArrayList<HistoriaEntity>();

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Historia, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HistoriaEntity.class.getPackage())
                .addPackage(HistoriaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception ed) {
                ed.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from HistoriaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 10; i++) {
            HistoriaEntity nueva = fabrica.manufacturePojo(HistoriaEntity.class);
            em.persist(nueva);
            data.add(nueva);
        }
    }

    /**
     * Prueba para crear una Historia.
     */
    @Test
    public void createHistoriaTest() {
        HistoriaEntity newEntity = fabrica.manufacturePojo(HistoriaEntity.class);
        HistoriaEntity result = historiaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        HistoriaEntity ent = em.find(HistoriaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getFoto(), ent.getFoto());
        Assert.assertEquals(newEntity.getTexto(), ent.getTexto());
    }

    /**
     * Prueba para consultar un Cliente por id.
     */
    @Test
    public void getHistoriaTest() {
        HistoriaEntity entity = data.get(0);
        HistoriaEntity newEntity = historiaPersistence.find(entity.getId());

        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getFoto(), newEntity.getFoto());
    }
    
    /**
     * Prueba para consultar todas las historias
     */
    @Test
    public void getHistoriasTest(){
        List<HistoriaEntity> historias = historiaPersistence.findAll();
        Assert.assertEquals(historias.size(),data.size());
        for(int i = 0; i < historias.size(); i++){
            for(int j = 0; j < data.size(); j++){
                if(historias.get(i).getId().compareTo(data.get(j).getId()) == 0){
                    Assert.assertEquals(historias.get(i).getFoto(),data.get(j).getFoto());
                    Assert.assertEquals(historias.get(i).getTexto(), data.get(j).getTexto());
                }
            }
        }
    }

    @Test
    public void updateHistoriaTest() throws BusinessLogicException {
        HistoriaEntity entity = data.get(0);
        HistoriaEntity newEntity = fabrica.manufacturePojo(HistoriaEntity.class);

        newEntity.setId(entity.getId());
        historiaPersistence.update(newEntity);

        HistoriaEntity compareEntity = em.find(HistoriaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), compareEntity.getId());
        Assert.assertEquals(newEntity.getTexto(), compareEntity.getTexto());
        Assert.assertEquals(newEntity.getFoto(), compareEntity.getFoto());
    }
    
     @Test
    public void deleteHistoriaTest(){
        HistoriaEntity historia = data.get(data.size()-1);
        historiaPersistence.delete(historia.getId());
        
        Assert.assertEquals(historiaPersistence.findAll().size(), data.size()-1);
    }
}
