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
 * @author Cristhian Peña
 */
public class EspecieDetailDTO extends EspecieDTO implements Serializable{
    //Lista de razas
    private List<RazaDTO> razas;
    
    public EspecieDetailDTO(){
        super();
    }
    
    /**
     * Crea un objeto EspecieDetailDTO a partir de un objeto EspecieEntity
     * incluyendo los atributos de EspecieDTO.
     *
     * @param especieEntity Entidad EspecieEntity desde la cual se va a crear el
     * nuevo objeto.
     */
    public EspecieDetailDTO(EspecieEntity especieEntity){
        super(especieEntity);
        if(especieEntity != null){
            razas = new ArrayList<>();
            for(RazaEntity raza : especieEntity.getRazas()){
                razas.add(new RazaDTO(raza));
            }
        }
    }
    
    /**
     * Convierte el objeto de tipo DTO en objeto Entity para la lógica
     *
     * @return EspecieEntity objeto convertido
     *
     */
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
    
    /**
     * Metodo que retorna la lista de razas de la especie
     * @return List de RazasDTO 
     */
    public List<RazaDTO> getRazas(){
        return razas;
    }
    
    /**
     * Método que cambia la lista de razas a la que entra por parámetro
     * @param razas lista de razas a cambiar
     */
    public void setRazas(List<RazaDTO> razas){
        this.razas = razas;
    }
}
