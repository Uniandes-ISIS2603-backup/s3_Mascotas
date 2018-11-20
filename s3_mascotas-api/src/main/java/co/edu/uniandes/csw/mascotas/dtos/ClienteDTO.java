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
    
    private Long id;
    private String correo;
    private String direccion;
    private String tarjetaCreditoRegistrada;
    public ClienteDTO() {
        
    }

    public ClienteDTO(ClienteEntity cliente){
        if(cliente != null){
            this.id= cliente.getId();
            this.correo = cliente.getCorreo();
            this.direccion = cliente.getDireccion();
            this.tarjetaCreditoRegistrada = cliente.getTarjetaCreditoRegistrada();
        }
    }
   
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
    
    public ClienteEntity toEntity(){
        ClienteEntity nueva = new ClienteEntity();
        
        nueva.setId(id);
        nueva.setCorreo(correo);
        nueva.setDireccion(direccion);
        nueva.setTarjetaCreditoRegistrada(tarjetaCreditoRegistrada);
        return nueva;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
