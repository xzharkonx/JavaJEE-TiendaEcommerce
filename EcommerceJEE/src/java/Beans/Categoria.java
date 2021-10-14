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
public class Categoria implements Serializable, Comparable<Categoria> {
    
     // Agregamos el siguiente atributo, así con esto la Clase Identidad Marca
    // ya esta implementando la interface de Serializable.
    // Es para poder convertir el objeto a bytes por que se envia por la red o 
    // entre servidores, para poderlo transmitir entonces se convierte a bytes, 
    // para ello se necesita serializar.
    private static final long serialVersionUID = 1L;
    
    private Integer idCategoria;
    private String nombreCategoria;
    private Boolean visible;
    private Integer categoriaSuperior; 

    public Categoria() {
    }

    public Categoria(Integer idCategoria, String nombreCategoria, Boolean visible, Integer categoriaSuperior) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.visible = visible;
        this.categoriaSuperior = categoriaSuperior;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getCategoriaSuperior() {
        return categoriaSuperior;
    }

    public void setCategoriaSuperior(Integer categoriaSuperior) {
        this.categoriaSuperior = categoriaSuperior;
    }

    @Override
    public String toString() {
        return "Categoria{" + "idCategoria=" + idCategoria + ", nombreCategoria=" + nombreCategoria + ", visible=" + visible + ", categoriaSuperior=" + categoriaSuperior + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Categoria other = (Categoria) obj;
        if ((this.nombreCategoria == null) ? (other.nombreCategoria != null) : !this.nombreCategoria.equals(other.nombreCategoria)) {
            return false;
        }
        if (this.idCategoria != other.idCategoria && (this.idCategoria == null || !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        if (this.visible != other.visible && (this.visible == null || !this.visible.equals(other.visible))) {
            return false;
        }
        if (this.categoriaSuperior != other.categoriaSuperior && (this.categoriaSuperior == null || !this.categoriaSuperior.equals(other.categoriaSuperior))) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Categoria c) {
        return this.nombreCategoria.compareToIgnoreCase(c.getNombreCategoria());
    }
    
}
