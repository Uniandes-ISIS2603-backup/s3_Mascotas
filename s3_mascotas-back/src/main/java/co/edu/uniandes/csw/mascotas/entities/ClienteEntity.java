/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;
import java.io.Serializable;
import javax.persistence.Entity;
/**
 *
 * @author Camilo Pinilla
 */
@Entity
public class CLienteEntity extends BaseEntity implements Serializable
{
    private long telefono;
    private String direccion;
    private String tarjetaCreditoRegistrada;

    public long getTelefono()
    {
        return telefono;
    }

    public void setTelefono(long telefono)
    {
        this.telefono = telefono;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getTarjetaCreditoRegistrada()
    {
        return tarjetaCreditoRegistrada;
    }

    public void setTarjetaCreditoRegistrada(String tarjetaCreditoRegistrada)
    {
        this.tarjetaCreditoRegistrada = tarjetaCreditoRegistrada;
    }
    
}
