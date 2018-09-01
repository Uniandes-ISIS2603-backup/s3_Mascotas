/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.EspecieEntity;
import co.edu.uniandes.csw.mascotas.entities.RazaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class EspecieDetailDTO extends EspecieDTO implements Serializable{
    private List<RazaDTO> razas;
    public EspecieDetailDTO(){
        super();
    }
    public EspecieDetailDTO(EspecieEntity especieEntity){
        super(especieEntity);
        if(especieEntity != null){
            razas = new ArrayList<>();
            for(RazaEntity raza : especieEntity.getRazas()){
                razas.add(new RazaDTO(raza));
            }
        }
    }
    
    @Override
    public EspecieEntity toEntity(){
        EspecieEntity especieEntity = super.toEntity();
        if(razas != null){
            List<RazaEntity> razasTmp = new ArrayList<>();
            for(RazaDTO dtoRaza : razas){
                razasTmp.add(dtoRaza.toEntity());
            }
            especieEntity.setRazas(razasTmp);
        }
        return especieEntity;
    }
    
    public List<RazaDTO> getRazas(){
        return razas;
    }
    
    public void setRazas(List<RazaDTO> razas){
        this.razas = razas;
    }
}
