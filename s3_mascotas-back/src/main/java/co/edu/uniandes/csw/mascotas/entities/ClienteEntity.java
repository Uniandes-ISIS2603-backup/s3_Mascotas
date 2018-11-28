/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Camilo Pinilla
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable
{
    private String correo;
    private String direccion;
    private String tarjetaCreditoRegistrada;
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CompraEntity> compras = new ArrayList<>();

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
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
    
    @Override
    public boolean equals(Object obj) {
        if (! super.equals(obj)) {
          return false;
        }
        ClienteEntity fobj = (ClienteEntity) obj;
        return fobj.getCorreo().equals(correo);
    }
}
