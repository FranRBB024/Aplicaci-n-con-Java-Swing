/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DAM-2
 */
public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost/JUGADORES";
    private static final String USUARIO = "root";
    private static final String CONTRASENNA = "";
    private static Connection conexion = null;
    
    private ConexionBD() throws SQLException {
        conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENNA);
    }
    
    public static Connection getConexion() throws SQLException {
        if (conexion == null) {
            new ConexionBD();
        }
        
        return conexion;
    }
    
    public static void cerrarConexion() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}
