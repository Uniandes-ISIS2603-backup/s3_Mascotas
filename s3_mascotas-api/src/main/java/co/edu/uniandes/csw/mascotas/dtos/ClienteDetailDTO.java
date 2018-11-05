/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.AdopcionEntity;
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
    private List<AdopcionDTO> adopciones;

    public ClienteDetailDTO() {
        super();
    }
    
    public ClienteDetailDTO(ClienteEntity clienteEntity){
        super(clienteEntity);
        System.out.println("Entramos al detail");
        if (clienteEntity!= null) {
            compras = new ArrayList<>();
            adopciones = new ArrayList<>();
            for(CompraEntity c : clienteEntity.getCompras()){
                compras.add(new CompraDTO(c));
            }
            for(AdopcionEntity a: clienteEntity.getAdopciones()){
                adopciones.add(new AdopcionDTO(a));
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
        System.out.println("Entra a esta vaina");
        if (compras != null) {
            List<CompraEntity> comprasEntity = new ArrayList<>();
            //List<AdopcionEntity> adopcionEntity= new ArrayList<>();
            for (CompraDTO c :compras) {
                comprasEntity.add(c.toEntity());
            }
            
            clienteEntity.setCompras(comprasEntity);
            }
        if(adopciones!=null){
            List<AdopcionEntity> adopcionEntity = new ArrayList<>();
            for(AdopcionDTO a: adopciones){
                adopcionEntity.add(a.toEntity());
            }
            clienteEntity.setAdopciones(adopcionEntity);
        }

        return clienteEntity;
    }

    public List<AdopcionDTO> getAdopciones() {
        return adopciones;
    }

    public void setAdopciones(List<AdopcionDTO> adopciones) {
        this.adopciones = adopciones;
    }
    
}
