/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Categoria;
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
public class CategoriaDao {

    public static Connection c = null;    
    
    public static ArrayList<Categoria> listar() {
        
        try{
            
            c = Conexion.conectar();
            
            // Hacemos la llamada SQL al STORE PROCEDURE (procedimiento almacenado).
            String sql = "{call sp_listarCategoriaSuperior()}";

            // Utilizamos CallableStatement para ejecutar la sentencia.
            CallableStatement cs = c.prepareCall(sql);
            cs.execute();
            
            // executeQuery nos devolverá un resultado que necesitamos.
            ResultSet rs = cs.getResultSet();

            ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();

            while (rs.next()) {

                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt(1));
                cat.setNombreCategoria(rs.getString(2));
                
                System.out.print(rs.getInt(1)+" ");
                System.out.println(rs.getString(2));
                listaCategorias.add(cat);

            }
            
            cs.close();
            
            if (c != null)
                    c.close();
		
            return listaCategorias;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static ArrayList<Categoria> listarSubCategorias(int catSuperior) {
        
        try{
            
            c = Conexion.conectar();
            
            // Hacemos la llamada SQL al STORE PROCEDURE (procedimiento almacenado).
            String sql = "{call sp_listarSubCategoria(?)}";

            // Utilizamos CallableStatement para ejecutar la sentencia.
            CallableStatement cs = c.prepareCall(sql);
            // Le pasamos el parametro de la categoria al SP.
            cs.setInt(1, catSuperior);
            cs.execute();
            
            // executeQuery nos devolverá un resultado que necesitamos.
            ResultSet rs = cs.getResultSet();

            ArrayList<Categoria> listaSubCategorias = new ArrayList<Categoria>();

            while (rs.next()) {

                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt(1));
                cat.setNombreCategoria(rs.getString(2));
                
                System.out.print(rs.getInt(1)+" ");
                System.out.println(rs.getString(2));
                listaSubCategorias.add(cat);

            }
            
            cs.close();
            
            if (c != null)
                    c.close();
		
            return listaSubCategorias;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    public static Integer numSubCategorias(int catSuperior) {
        
        try{
            
            c = Conexion.conectar();
            
            // Hacemos la llamada SQL al STORE PROCEDURE (procedimiento almacenado).
            String sql = "{call sp_contarSubCategorias(?)}";

            // Utilizamos CallableStatement para ejecutar la sentencia.
            CallableStatement cs = c.prepareCall(sql);
            // Le pasamos el parametro de la categoria al SP.
            cs.setInt(1, catSuperior);
            cs.execute();
            
            // executeQuery nos devolverá un resultado que necesitamos.
            ResultSet rs = cs.getResultSet();
            rs.next();
            
            // Guardaremos cada nuevo resultado de la candidad de cada Subcategorias
            // que tiene una Categoria en esta variable para que cuando cierre
            // la conexión no se pierda el valor.
            Integer cantidad = rs.getInt("cantidad");
            
            cs.close();
            
            if (c != null)
                    c.close();
            
            // Obtenemos el dato mediante el nombre de la columna.
            // Este lo asignamos en el procedimiento almacenado.
            return cantidad;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    public static ArrayList<Categoria> listarTodoDeCategorias() {
        
        try{
            
            c = Conexion.conectar();
            
            // Hacemos la llamada SQL al STORE PROCEDURE (procedimiento almacenado).
            String sql = "{call sp_listarTodoDeCategoria()}";

            // Utilizamos CallableStatement para ejecutar la sentencia.
            CallableStatement cs = c.prepareCall(sql);
            
            // Ejecutamos el Statement del SP.
            cs.execute();
            
            // executeQuery nos devolverá un resultado que necesitamos.
            ResultSet rs = cs.getResultSet();

            ArrayList<Categoria> listarCategorias = new ArrayList<Categoria>();

            while (rs.next()) {

                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt(1));
                cat.setNombreCategoria(rs.getString(2));
                
                System.out.print(rs.getInt(1)+" ");
                System.out.println(rs.getString(2));
                listarCategorias.add(cat);

            }
            
            cs.close();
            
            if (c != null)
                    c.close();
		
            return listarCategorias;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
