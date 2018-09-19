/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Camilo Pinilla
 */
@Stateless
public class ClientePersistence {
    
    // Debug logger
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());
    
    @PersistenceContext(unitName = "PetsUniandesPU")
    protected EntityManager em;
    
    public ClienteEntity create(ClienteEntity clienteEntity){
        LOGGER.log(Level.INFO, "Creating new Customer");
        em.persist(clienteEntity);
        LOGGER.log(Level.INFO, "Finishing creating the new Customer");
        return clienteEntity;
    }
    
    public List<ClienteEntity> findAll(){
        LOGGER.log(Level.INFO, "Consulting all customers");
        Query q = em.createQuery("select u from ClienteEntity u");
        return q.getResultList();
    }
    
    public ClienteEntity find(Long clienteId){
        return em.find(ClienteEntity.class, clienteId);
    }
    
    public ClienteEntity update(ClienteEntity clienteEntity){
        LOGGER.log(Level.INFO, "Updating customer with id={0}", clienteEntity.getId());
        return em.merge(clienteEntity);
    }
    
    public void delete(ClienteEntity cliente){
        em.getTransaction();
        em.remove(cliente);
        em.getTransaction().commit();
    }
    
    /**
     * Busca si hay algun cliente con el teléfono que se envía de argumento
     *
     * @param telefono: Teléfono del cliente que se está buscando
     * @return null si no existe ningun cliente con el teléfono del argumento. Si
     * existe alguno devuelve el primero.
     */
    public ClienteEntity findByTelefono(long telefono) {
        LOGGER.log(Level.INFO, "Consultando clientes por teléfono ", telefono);
        // Se crea un query para buscar clientes con el teléfono que recibe el método como argumento. ":telefono" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ClienteEntity e where e.telefono = :telefono", ClienteEntity.class);
        // Se remplaza el placeholder ":telefono" con el valor del argumento 
        query = query.setParameter("telefono", telefono);
        // Se invoca el query se obtiene la lista resultado
        List<ClienteEntity> sameTelefono = query.getResultList();
        ClienteEntity result;
        if (sameTelefono == null) {
            result = null;
        } else if (sameTelefono.isEmpty()) {
            result = null;
        } else {
            result = sameTelefono.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar clientes por teléfono ", telefono);
        return result;
    }
}
