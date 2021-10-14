/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo
 */
public class Conexion {
    
    // Clase con la que haremos la conexión.
    // Importaremos la biblioteca de conexión.
    
    public static Connection conectar(){
        
        
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");

            
//            return DriverManager.getConnection("jdbc:mysql:/cj/localhost:3306/ecommerce_db?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "luis", "zelda1234");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "luis", "zelda1234");
//            return DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_db", "root", "admin1234");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error de conexión");
        
        }
        return null;
        
    }
    
}
