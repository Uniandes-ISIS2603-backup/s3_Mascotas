
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.ClienteEntity;
import co.edu.uniandes.csw.mascotas.entities.CompraEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lemus
 */
public class ClienteDetailDTO extends ClienteDTO implements Serializable{
    
    private List<CompraDTO> compras;

    public ClienteDetailDTO() {
        super();
    }
    
    public ClienteDetailDTO(ClienteEntity clienteEntity){
        super(clienteEntity);
        if (clienteEntity!= null) {
            compras = new ArrayList<>();
            for(CompraEntity c : clienteEntity.getCompras()){
                compras.add(new CompraDTO(c));
            }
        }
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

        return clienteEntity;
    }
}
