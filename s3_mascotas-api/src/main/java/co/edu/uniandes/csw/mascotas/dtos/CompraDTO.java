/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

/**
 *
 * @author Juan Sebastián Gómez
 */
public class CompraDTO {

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipoDePago() {
        return tipoDePago;
    }

    public void setTipoDePago(String tipoDePago) {
        this.tipoDePago = tipoDePago;
    }

    public CompraDTO(double precio, String tipoDePago) {
        this.precio = precio;
        this.tipoDePago = tipoDePago;
    }
    /**
     * Atributo que representa el precio de la mascota
     */
        private double precio;
        private String tipoDePago;
        
        
}
