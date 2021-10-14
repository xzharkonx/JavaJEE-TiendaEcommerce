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
public class ProductoMoneda implements Serializable {
    
     // Agregamos el siguiente atributo, así con esto la Clase Identidad Marca
    // ya esta implementando la interface de Serializable.
    // Es para poder convertir el objeto a bytes por que se envia por la red o 
    // entre servidores, para poderlo transmitir entonces se convierte a bytes, 
    // para ello se necesita serializar.
    private static final long serialVersionUID = 1L;
    
    // La moneda se guardara de tipo CHAR en la DB pero aquí lo podemos 
    // manejar como String para mayor comodidad.
    private String moneda;
    private float precio;
    private float precioNuevo;
    private Integer idProducto;

    public ProductoMoneda() {
    }

    public ProductoMoneda(String moneda, float precio, float precioNuevo, Integer idProducto) {
        this.moneda = moneda;
        this.precio = precio;
        this.precioNuevo = precioNuevo;
        this.idProducto = idProducto;
    }

    // Se creo este tercer Constructor porque el id se pasa por el Procedimiento almacenado
    // ocea que no necesitamos enviarlo y se usa de esta forma para ahorrar codigo.
    public ProductoMoneda(String moneda, float precio, float precioNuevo) {
        this.moneda = moneda;
        this.precio = precio;
        this.precioNuevo = precioNuevo;
    }
    
    
    
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
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

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.moneda != null ? this.moneda.hashCode() : 0);
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
        final ProductoMoneda other = (ProductoMoneda) obj;
        if ((this.moneda == null) ? (other.moneda != null) : !this.moneda.equals(other.moneda)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "ProductoMoneda{" + "moneda=" + moneda + ", precio=" + precio + ", precioNuevo=" + precioNuevo + ", idProducto=" + idProducto + '}';
    }
    
    
    
}
