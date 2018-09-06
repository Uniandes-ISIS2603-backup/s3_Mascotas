/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;
import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
/**
 *
 * @author Camilo Pinilla
 */
public class ClienteDTO implements Serializable{
    private long id;
    private long telefono;
    private String direccion;
    private String tarjetaCreditoRegistrada;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTarjetaCreditoRegistrada() {
        return tarjetaCreditoRegistrada;
    }

    public void setTarjetaCreditoRegistrada(String tarjetaCreditoRegistrada) {
        this.tarjetaCreditoRegistrada = tarjetaCreditoRegistrada;
    }

    public ClienteDTO() {
        
    }

    public ClienteDTO(ClienteEntity Cliente){
        if(Cliente!=null){
            this.id= Cliente.getId();
            this.telefono = Cliente.getTelefono();
            this.direccion = Cliente.getDireccion();
            this.tarjetaCreditoRegistrada = Cliente.getTarjetaCreditoRegistrada();
        }
    }
    
    public ClienteEntity toEntity(){
        ClienteEntity nueva = new ClienteEntity();
        
        nueva.setId(this.id);
        nueva.setTelefono(this.telefono);
        nueva.setDireccion(this.direccion);
        nueva.setTarjetaCreditoRegistrada(this.tarjetaCreditoRegistrada);
        
        return nueva;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
