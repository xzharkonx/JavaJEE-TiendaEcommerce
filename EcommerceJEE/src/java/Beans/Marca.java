/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;

/**
 *
 * @author Eduardo
 */
public class Marca implements Serializable {
    
    // Agregamos el siguiente atributo, así con esto la Clase Identidad Marca
    // ya esta implementando la interface de Serializable.
    // Es para poder convertir el objeto a bytes por que se envia por la red o 
    // entre servidores, para poderlo transmitir entonces se convierte a bytes, 
    // para ello se necesita serializar.
    private static final long serialVersionUID = 1L;
    
    private Integer idMarca;
    private String nombreMarca;
    private Boolean visible;

    public Marca() {
    }

    public Marca(Integer idMarca, String nombreMarca, Boolean visible) {
        this.idMarca = idMarca;
        this.nombreMarca = nombreMarca;
        this.visible = visible;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "Marca{" + "idMarca=" + idMarca + ", nombreMarca=" + nombreMarca + ", visible=" + visible + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.nombreMarca != null ? this.nombreMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Marca other = (Marca) obj;
        if ((this.nombreMarca == null) ? (other.nombreMarca != null) : !this.nombreMarca.equals(other.nombreMarca)) {
            return false;
        }
        if (this.idMarca != other.idMarca && (this.idMarca == null || !this.idMarca.equals(other.idMarca))) {
            return false;
        }
        if (this.visible != other.visible && (this.visible == null || !this.visible.equals(other.visible))) {
            return false;
        }
        return true;
    }
    
    
    
}
