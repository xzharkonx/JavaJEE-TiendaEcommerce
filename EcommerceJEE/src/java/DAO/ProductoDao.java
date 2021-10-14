/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Producto;
import Beans.ProductoMoneda;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo
 */
public class ProductoDao {
        
        public static Connection c = null;
    
        public static Boolean registrarProducto(Producto p, ProductoMoneda usd, ProductoMoneda cop, ProductoMoneda pen) {
        
        try{
            
            c = Conexion.conectar();
            
            // Hacemos la llamada SQL al STORE PROCEDURE (procedimiento almacenado).
            String sql = "{call sp_registrarProducto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

            // Utilizamos CallableStatement para ejecutar la sentencia.
            CallableStatement cs = c.prepareCall(sql);
            // Le pasamos el parametro de la categoria al SP.
            
            cs.setString(1, p.getNombreProducto());
            cs.setFloat(2, p.getPrecio());
            cs.setFloat(3, p.getPrecioNuevo());
            cs.setInt(4, p.getStock());
            cs.setBoolean(5, p.getNuevo());
            cs.setBoolean(6, p.getRecomendado());
            cs.setString(7, p.getDescripcion());
            cs.setBoolean(8, p.getVisible());
            cs.setString(9, p.getImg());
            cs.setInt(10, p.getIdMarca());
            cs.setInt(11, p.getIdCategoria());
            
            cs.setString(12, usd.getMoneda());
            cs.setFloat(13, usd.getPrecio());
            cs.setFloat(14, usd.getPrecioNuevo());
            
            cs.setString(15, cop.getMoneda());
            cs.setFloat(16, cop.getPrecio());
            cs.setFloat(17, cop.getPrecioNuevo());
            
            cs.setString(18, pen.getMoneda());
            cs.setFloat(19, pen.getPrecio());
            cs.setFloat(20, pen.getPrecioNuevo());
            
            System.out.println("A punto");
            Boolean ok = cs.executeUpdate() > 0;
            if (ok != null) {System.out.println(ok);}else{System.out.println("Retorno NULO");}
            cs.close();
            
            if (c != null)
                    c.close();
		
            return ok;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null,"ERROR OBTENIDO :/ :"+ ex);
        }
        
        return null;
    }
        
        public static ArrayList<Producto> listarProductosRecomendados(String moneda) {
        
            System.out.println("Moneda de referencia: " + moneda);
        try{
            
            c = Conexion.conectar();
            
            // Hacemos la llamada SQL al STORE PROCEDURE (procedimiento almacenado).
            String sql = "{call sp_listarRecomendados(?)}";

            // Utilizamos CallableStatement para ejecutar la sentencia.
            CallableStatement cs = c.prepareCall(sql);
            // Le pasamos el parametro de la categoria al SP.
            
            cs.setString(1, moneda);
            
            ResultSet rs = cs.executeQuery();
            ArrayList<Producto> listaProducto = new ArrayList<Producto>();
            
            while(rs.next()) {
                
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombreProducto(rs.getString("nombreProducto"));
                System.out.println("NOMBREEEE: "+rs.getString("nombreProducto"));
                p.setImg(rs.getString("img"));
                p.setStock(rs.getInt("stock"));
                p.setNuevo(rs.getBoolean("nuevo"));
                if(!moneda.equalsIgnoreCase("MXN")){
                    p.setPrecio(rs.getFloat("precio2"));
                    p.setPrecioNuevo(rs.getFloat("precion2"));
                }else {
                    p.setPrecio(rs.getFloat("precio"));
                    p.setPrecioNuevo(rs.getFloat("precioNuevo"));
                }
                System.out.println(p.toString());
                listaProducto.add(p);
                
            }
            
            for (Producto pr: listaProducto){
                System.out.println(pr.getNombreProducto());
            }
            
//            if (c != null)
//                    c.close();
            
            System.out.println("Cantidad de productos consultados por default: "+listaProducto.size());
            return listaProducto;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null,"ERROR OBTENIDO :/ :"+ ex);
            
        }
        
        return null;
    }
        
    
    public static ArrayList<Producto> listarProductosPorCategoria(String moneda, Integer cat) {
        
            System.out.println("Moneda de referencia: " + moneda);
            System.out.println("Categoria de referencia: " + cat);
        try{
            
            c = Conexion.conectar();
            
            // Hacemos la llamada SQL al STORE PROCEDURE (procedimiento almacenado).
            String sql = "{call sp_listarPorCategoria(?,?)}";

            // Utilizamos CallableStatement para ejecutar la sentencia.
            CallableStatement cs = c.prepareCall(sql);
            // Le pasamos el parametro de la categoria al SP.
            
            cs.setString(1, moneda);
            cs.setInt(2, cat);
            
            ResultSet rs = cs.executeQuery();
            ArrayList<Producto> listaProducto = new ArrayList<Producto>();
            
            while(rs.next()) {
                
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombreProducto(rs.getString("nombreProducto"));
                System.out.println("NOMBREEEE: "+rs.getString("nombreProducto"));
                p.setImg(rs.getString("img"));
                p.setStock(rs.getInt("stock"));
                p.setNuevo(rs.getBoolean("nuevo"));
                if(!moneda.equalsIgnoreCase("MXN")){
                    p.setPrecio(rs.getFloat("precio2"));
                    p.setPrecioNuevo(rs.getFloat("precion2"));
                }else {
                    p.setPrecio(rs.getFloat("precio"));
                    p.setPrecioNuevo(rs.getFloat("precioNuevo"));
                }
                System.out.println(p.toString());
                listaProducto.add(p);
                
            }
            
            for (Producto pr: listaProducto){
                System.out.println(pr.getNombreProducto());
            }
            
//            if (c != null)
//                    c.close();
            
            System.out.println("Cantidad de productos consultados por la categoria: "+cat+" No. ("+listaProducto.size()+")");
            return listaProducto;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null,"ERROR OBTENIDO :/ :"+ ex);
            
        }
        
        return null;
    }
    
    
    public static ArrayList<Producto> listarProductosPorMarca(String moneda, Integer mar) {
        
            System.out.println("Moneda de referencia: " + moneda);
            System.out.println("Marca de referencia: " + mar);
            
        try{
            
            c = Conexion.conectar();
            
            // Hacemos la llamada SQL al STORE PROCEDURE (procedimiento almacenado).
            String sql = "{call sp_listarPorMarca(?,?)}";

            // Utilizamos CallableStatement para ejecutar la sentencia.
            CallableStatement cs = c.prepareCall(sql);
            // Le pasamos el parametro de la categoria al SP.
            
            cs.setString(1, moneda);
            cs.setInt(2, mar);
            
            ResultSet rs = cs.executeQuery();
            ArrayList<Producto> listaProducto = new ArrayList<Producto>();
            
            while(rs.next()) {
                
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombreProducto(rs.getString("nombreProducto"));
                System.out.println("NOMBREEEE: "+rs.getString("nombreProducto"));
                p.setImg(rs.getString("img"));
                p.setStock(rs.getInt("stock"));
                p.setNuevo(rs.getBoolean("nuevo"));
                if(!moneda.equalsIgnoreCase("MXN")){
                    p.setPrecio(rs.getFloat("precio2"));
                    p.setPrecioNuevo(rs.getFloat("precion2"));
                }else {
                    p.setPrecio(rs.getFloat("precio"));
                    p.setPrecioNuevo(rs.getFloat("precioNuevo"));
                }
                System.out.println(p.toString());
                listaProducto.add(p);
                
            }
            
            for (Producto pr: listaProducto){
                System.out.println(pr.getNombreProducto());
            }
            
//            if (c != null)
//                    c.close();
            
            System.out.println("Cantidad de productos consultados por la marca: "+mar+" No. ("+listaProducto.size()+")");
            return listaProducto;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null,"ERROR OBTENIDO :/ :"+ ex);
            
        }
        
        return null;
    }
    
    public static Producto consultarProducto(String moneda, Integer idProducto) {
        
            System.out.println("Moneda de referencia: " + moneda);
            System.out.println("id del Prodcuto de referencia: " + idProducto);
            
        try{
            
            c = Conexion.conectar();
            
            // Hacemos la llamada SQL al STORE PROCEDURE (procedimiento almacenado).
            String sql = "{call sp_consultarProducto(?,?)}";

            // Utilizamos CallableStatement para ejecutar la sentencia.
            CallableStatement cs = c.prepareCall(sql);
            // Le pasamos el parametro de la categoria al SP.
            
            cs.setString(1, moneda);
            cs.setInt(2, idProducto);
            
            ResultSet rs = cs.executeQuery();
            
            
                
            Producto p = null;
            if(rs.next()) {
                p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombreProducto(rs.getString("nombreProducto"));
                System.out.println("NOMBREEEE: "+rs.getString("nombreProducto"));
                p.setImg(rs.getString("img"));
                p.setStock(rs.getInt("stock"));
                p.setNuevo(rs.getBoolean("nuevo"));
                if(!moneda.equalsIgnoreCase("MXN")){
                    p.setPrecio(rs.getFloat("precio2"));
                    p.setPrecioNuevo(rs.getFloat("precion2"));
                }else {
                    p.setPrecio(rs.getFloat("precio"));
                    p.setPrecioNuevo(rs.getFloat("precioNuevo"));
                }
                System.out.println(p.toString());                
            }
            

//            if (c != null)
//                    c.close();
            
            return p;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null,"ERROR OBTENIDO :/ :"+ ex);
            
        }
        
        return null;
    }
    
}
