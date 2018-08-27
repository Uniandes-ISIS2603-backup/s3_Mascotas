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
public class AdopcionDTO {
    private String docsAdopcion;

    public String getDocsAdopcion() {
        return docsAdopcion;
    }

    public void setDocsAdopcion(String docsAdopcion) {
        this.docsAdopcion = docsAdopcion;
    }

    public AdopcionDTO(String docsAdopcion) {
        this.docsAdopcion = docsAdopcion;
    }
}
