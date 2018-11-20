
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
import co.edu.uniandes.csw.mascotas.entities.CompraEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lemus
 */
public class ClienteDetailDTO extends ClienteDTO implements Serializable{
    
    private List<CompraDTO> compras;
    private List<MascotaDTO> mascotas;

    public ClienteDetailDTO() {
        super();
    }
    
    public ClienteDetailDTO(ClienteEntity clienteEntity){
        super(clienteEntity);
        if (clienteEntity!= null) {
            compras = new ArrayList<>();
            mascotas = new ArrayList<>();
            for(CompraEntity c : clienteEntity.getCompras()){
                compras.add(new CompraDTO(c));
            }
            for(MascotaEntity m: clienteEntity.getMascotas()){
                mascotas.add(new MascotaDTO(m));
            }
        }
    }

    public List<MascotaDTO> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<MascotaDTO> mascotas) {
        this.mascotas = mascotas;
    }

    public List<CompraDTO> getCompras() {
        return compras;
    }

    public void setCompras(List<CompraDTO> compras) {
        this.compras = compras;
    }
    
    @Override
    public ClienteEntity toEntity(){
        ClienteEntity clienteEntity = super.toEntity();
        if (compras != null) {
            List<CompraEntity> comprasEntity = new ArrayList<>();
            for (CompraDTO c :compras) {
                comprasEntity.add(c.toEntity());
            }
            
            clienteEntity.setCompras(comprasEntity);
        }
        if(mascotas!=null){
             List<MascotaEntity> mascotaEntity = new ArrayList<>();
            for(MascotaDTO a: mascotas){
                mascotaEntity.add(a.toEntity());
            }
            clienteEntity.setMascotas(mascotaEntity);
        }

        return clienteEntity;
    }
}
