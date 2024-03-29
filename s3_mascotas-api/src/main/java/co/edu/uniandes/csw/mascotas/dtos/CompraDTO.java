/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;
import co.edu.uniandes.csw.mascotas.entities.CompraEntity;
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
    private ClienteDTO relacionCliente;
    private MascotaDTO mascota;
    
    public ClienteDTO getRelacionCliente() {
        return relacionCliente;
    }

    public void setRelacionCliente(ClienteDTO relacionCliente) {
        this.relacionCliente = relacionCliente;
    }

    public MascotaDTO getMascota() {
        return mascota;
    }

    public void setMascota(MascotaDTO mascota) {
        this.mascota = mascota;
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
            if(compra.getMascota() != null)
                this.mascota = new MascotaDTO(compra.getMascota());
            if(compra.getCliente() != null)
                this.relacionCliente = new ClienteDTO(compra.getCliente());
        }
    }
    /**
     * Atributo que representa el precio de la mascota
     * @return 
     */
    public Long getId() {
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
        if(this.mascota != null)
            compra.setMascota(this.mascota.toEntity());
        if(this.relacionCliente != null)
            compra.setCliente(this.relacionCliente.toEntity());
        return compra;
    }   
    
    
    
    
    
    
    
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
