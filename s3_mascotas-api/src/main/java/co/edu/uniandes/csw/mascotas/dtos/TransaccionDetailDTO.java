/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.TransaccionEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Sebastian Mujica
 */
public class TransaccionDetailDTO extends TransaccionDTO implements Serializable{
    
    
    
    private List<TransaccionDTO> transacciones;

    public TransaccionDetailDTO() {
        super();
    }

        
        
    
}
