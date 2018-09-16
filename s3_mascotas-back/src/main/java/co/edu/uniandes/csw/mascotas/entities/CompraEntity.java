/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;
import java.io.Serializable;
//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Juan Sebastián Gómez
 */
@Entity
public class CompraEntity extends BaseEntity implements Serializable{
    public double precio;
    public String tipoDePago;
    @PodamExclude
    @OneToOne
    private CalificacionEntity calificacion;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public CalificacionEntity getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(CalificacionEntity calificacion) {
        this.calificacion = calificacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipoDePago() {
        return tipoDePago;
    }

    public void setTipoDePago(String tipoDePago) {
        this.tipoDePago = tipoDePago;
    }
    
}
