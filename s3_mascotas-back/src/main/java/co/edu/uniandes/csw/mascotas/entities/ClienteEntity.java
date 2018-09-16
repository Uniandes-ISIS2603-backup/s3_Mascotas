/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Camilo Pinilla
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable
{
    private long telefono;
    private String direccion;
    private String tarjetaCreditoRegistrada;
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<CompraEntity> compras = new ArrayList<>();

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
    
    public List<CompraEntity> getCompras() {
        return compras;
    }

    public void setCompras(List<CompraEntity> compras) {
        this.compras = compras;
    }
}
