/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Camilo Pinilla
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable
{
    private String correo;
    private String direccion;
    private String tarjetaCreditoRegistrada;
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<CompraEntity> compras = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy="cliente")
    private List<AdopcionEntity> adopciones = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy="cliente")
    private List<MascotaEntity> mascotas = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy="cliente")
    private List<HistoriaEntity> historias = new ArrayList<>();

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getTarjetaCreditoRegistrada()
    {
        return tarjetaCreditoRegistrada;
    }

    public void setTarjetaCreditoRegistrada(String tarjetaCreditoRegistrada)
    {
        this.tarjetaCreditoRegistrada = tarjetaCreditoRegistrada;
    }
    
    public List<CompraEntity> getCompras() {
        return compras;
    }

    public void setCompras(List<CompraEntity> compras) {
        this.compras = compras;
    }
    
    public List<AdopcionEntity> getAdopciones() {
        return adopciones;
    }

    public void setAdopciones(List<AdopcionEntity> adopciones) {
        this.adopciones = adopciones;
    }
    
    public List<MascotaEntity> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<MascotaEntity> mascotas) {
        this.mascotas = mascotas;
    }
    
    public List<HistoriaEntity> getHistorias() {
        return historias;
    }

    public void setHistorias(List<HistoriaEntity> historias) {
        this.historias = historias;
    }
}
