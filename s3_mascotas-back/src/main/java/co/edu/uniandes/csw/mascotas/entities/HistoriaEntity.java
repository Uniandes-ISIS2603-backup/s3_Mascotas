/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Camilo Pinilla
 */
@Entity
public class HistoriaEntity extends BaseEntity implements Serializable
{
    @PodamExclude
    @OneToOne
   // @OneToOne(mappedBy="mascotaAdopcion" , fetch = FetchType.EAGER)
    private ClienteEntity cliente;
    
    private String foto;
    private String texto;

    public String getFoto()
    {
        return foto;
    }
    
    public String getTexto()
    {
        return texto;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }

    public void setTexto(String texto)
    {
        this.texto = texto;
    }
    
    /**
     * @return the mascotaEntity
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * @param cliente the mascotaEntity to set
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
}
