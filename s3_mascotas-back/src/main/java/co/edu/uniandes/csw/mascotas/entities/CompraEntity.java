/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Juan Sebastián Gómez
 */
@Entity
public class CompraEntity extends BaseEntity implements Serializable{
    private Double precio;
    private String tipoDePago;
    
    @PodamExclude
    @OneToOne(mappedBy = "compra", fetch = FetchType.LAZY)
    private CalificacionEntity calificacion;
   
    @PodamExclude
    @OneToOne
    private MascotaEntity mascota; 
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public MascotaEntity getMascota() {
        return mascota;
    }

    public void setMascota(MascotaEntity mascota) {
        this.mascota = mascota;
    }
    
    
    public CalificacionEntity getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(CalificacionEntity calificacion) {
        this.calificacion = calificacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTipoDePago() {
        return tipoDePago;
    }

    public void setTipoDePago(String tipoDePago) {
        this.tipoDePago = tipoDePago;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (! super.equals(obj)) {
          return false;
        }
        CompraEntity fobj = (CompraEntity) obj;
        return tipoDePago.equals(fobj.getTipoDePago());
    }
}
