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
public class Producto implements Serializable, Comparable<Producto>{
    
    private static final long serialVersionUID = 1L;
    
    private Integer idProducto;
    private String nombreProducto;
    private float precio;
    private float precioNuevo;
    private Integer stock;
    private Boolean nuevo;
    private Boolean recomendado;
    private String descripcion;
    private Boolean visible;
    private String img;
    private Integer idMarca; 
    private Integer idCategoria; 

    public Producto() {
    }

    public Producto(String nombreProducto, float precio, float precioNuevo, Integer stock, Boolean nuevo, Boolean recomendado, String descripcion, Boolean visible, String img, Integer idMarca, Integer idCategoria) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.precioNuevo = precioNuevo;
        this.stock = stock;
        this.nuevo = nuevo;
        this.recomendado = recomendado;
        this.descripcion = descripcion;
        this.visible = visible;
        this.img = img;
        this.idMarca = idMarca;
        this.idCategoria = idCategoria;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecioNuevo() {
        return precioNuevo;
    }

    public void setPrecioNuevo(float precioNuevo) {
        this.precioNuevo = precioNuevo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getNuevo() {
        return nuevo;
    }

    public void setNuevo(Boolean nuevo) {
        this.nuevo = nuevo;
    }

    public Boolean getRecomendado() {
        return recomendado;
    }

    public void setRecomendado(Boolean recomendado) {
        this.recomendado = recomendado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.nombreProducto != null ? this.nombreProducto.hashCode() : 0);
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
        final Producto other = (Producto) obj;
        if ((this.nombreProducto == null) ? (other.nombreProducto != null) : !this.nombreProducto.equals(other.nombreProducto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", precio=" + precio + ", precioNuevo=" + precioNuevo + ", stock=" + stock + ", nuevo=" + nuevo + ", recomendado=" + recomendado + ", descripcion=" + descripcion + ", visible=" + visible + ", img=" + img + ", idMarca=" + idMarca + ", idCategoria=" + idCategoria + '}';
    }
    
    
    @Override
    public int compareTo(Producto p) {
        return this.nombreProducto.compareToIgnoreCase(p.getNombreProducto());
    }
}
