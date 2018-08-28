/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools |
Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.TransaccionEntity;
import java.io.Serializable;

/**
 *
 * @author Sebastian Mujica
 */
public class TransaccionDTO implements Serializable{
    
    private Long id;
    
    private String fecha;
    
    private MascotaDTO mascota;
    
    private ClienteDTO cliente;

    /**
     * Constructor vacío.
     */
    public TransaccionDTO() {
    }

    public TransaccionDTO(TransaccionEntity transaccionEntity) {
        this.id=transaccionEntity.getId();
        this.fecha=transaccionEntity.getFecha();
        if(transaccionEntity.getMascota()!=null){
            this.mascota= new MascotaDTO(transaccionEntity.getMascota());
        }
        else{
            this.mascota=null;
        }
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the mascota
     */
    public MascotaDTO getMascota() {
        return mascota;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @param mascota the mascota to set
     */
    public void setMascota(MascotaDTO mascota) {
        this.mascota = mascota;
    }

    /**
     * @return the cliente
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
    
    public TransaccionEntity toEntity(){
        return new TransaccionEntity(this.id, this.fecha);
    }
    
}
