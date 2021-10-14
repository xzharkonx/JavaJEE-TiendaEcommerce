/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Marca;
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
public class MarcaDao {
    
    public static Connection c = null;  
    
    public static ArrayList<Marca> listarTodoDeMarcas() {
        
        try{
            
            c = Conexion.conectar();
            
            // Hacemos la llamada SQL al STORE PROCEDURE (procedimiento almacenado).
            String sql = "{call sp_listarTodoDeMarca()}";

            // Utilizamos CallableStatement para ejecutar la sentencia.
            CallableStatement cs = c.prepareCall(sql);
            
            // Ejecutamos el Statement del SP.
            cs.execute();
            
            // executeQuery nos devolverá un resultado que necesitamos.
            ResultSet rs = cs.getResultSet();

            ArrayList<Marca> listarMarcas = new ArrayList<Marca>();

            while (rs.next()) {

                Marca m = new Marca();
                m.setIdMarca(rs.getInt(1));
                m.setNombreMarca(rs.getString(2));
                
                System.out.print(rs.getInt(1)+" ");
                System.out.println(rs.getString(2));
                listarMarcas.add(m);

            }
            
            cs.close();
            
            if (c != null)
                    c.close();
		
            return listarMarcas;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static Integer contarMarcas(Integer coM) {
        
        try{
            
            c = Conexion.conectar();
            
            // Hacemos la llamada SQL al STORE PROCEDURE (procedimiento almacenado).
            String sql = "{call sp_contarProductosMarca(?)}";

            // Utilizamos CallableStatement para ejecutar la sentencia.
            CallableStatement cs = c.prepareCall(sql);
            
            // Enviamos el parametro.
            cs.setInt(1, coM);
            
            // Ejecutamos el Statement del SP.
            cs.execute();
            
            // executeQuery nos devolverá un resultado que necesitamos.
            ResultSet rs = cs.getResultSet();
            rs.next();
            Integer codigoMarca  = rs.getInt(1);
            
            cs.close();
            
            if (c != null)
                    c.close();
		
            return codigoMarca;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
}
