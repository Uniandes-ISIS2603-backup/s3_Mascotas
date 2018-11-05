/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;
import co.edu.uniandes.csw.mascotas.entities.CompraEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 *
 * @author Juan Sebastián Gómez
 */
public class CompraDTO implements Serializable {

     private Double precio;
     private String tipoDePago;
     private Long id;
     private CalificacionDTO relacionCalificacion;
     private ClienteDTO relacionCliente;
     private MascotaDTO relacionMascota;

    public ClienteDTO getRelacionCliente() {
        return relacionCliente;
    }

    public void setRelacionCliente(ClienteDTO relacionCliente) {
        this.relacionCliente = relacionCliente;
    }
    
    public MascotaDTO getRelacionMascota()
    {
        return relacionMascota;
    }
    
    public void seRelacionMascota(MascotaDTO relacionMascota)
    {
        this.relacionMascota = relacionMascota;
    }
    
     public CalificacionDTO getRelacionCalificacion() {
        return relacionCalificacion;
    }

    public void setRelacionCalificacion(CalificacionDTO relacionCalificacion) {
        this.relacionCalificacion = relacionCalificacion;
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
    public CompraDTO(){
        super();
        
    }
    public CompraDTO(CompraEntity compra){
        
       if(compra!=null){
           this.id =compra.getId();
           this.precio=compra.getPrecio();
           this.tipoDePago=compra.getTipoDePago();
           this.relacionCalificacion = new CalificacionDTO(compra.getCalificacion());
           this.relacionMascota = new MascotaDTO(compra.getMascota());
//           this.relacionCliente = new ClienteDTO(compra.getCliente());
       }
    }
    /**
     * Atributo que representa el precio de la mascota
     */
       

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public CompraEntity toEntity(){
        CompraEntity compra = new CompraEntity();
        compra.setId(this.id);
        compra.setPrecio(this.precio);
        compra.setTipoDePago(this.tipoDePago);
        return compra;
    }   
        @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
