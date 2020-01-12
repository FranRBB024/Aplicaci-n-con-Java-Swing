/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author francisco
 */
public class ProyectoDAO {
    private static ProyectoDAO dao = null;
    private static Connection conexion = null;
    
    private ProyectoDAO() throws SQLException {
        conexion = ConexionBD.getConexion();
    }
    
    public static ProyectoDAO getDAO() throws SQLException {
        if (dao == null) {
            dao = new ProyectoDAO();
        }
        
        
        return dao;
    }
    
    //Métodos de añadir y borrar
    public void annadirJugador(String dni, String nombre, Date fecha, String posicion, String foto) throws SQLException {
        try(PreparedStatement annadir = conexion.prepareStatement("INSERT INTO jugador"
                + " VALUES (?, ?, ?, ?, ?)")) {
            
            annadir.setString(1, dni);
            annadir.setString(2, nombre);
            annadir.setDate(3, fecha);
            annadir.setString(4, posicion);
            annadir.setString(5, foto);
            
            annadir.executeUpdate();
            
        }
    }
    
    public void borrarJugadorPorDni(String dni) throws SQLException {
        try(PreparedStatement borrar = conexion.prepareStatement("DELETE FROM jugador "
                + "WHERE dni = ?")) {
            
            borrar.setString(1, dni);
            
            borrar.execute();                        
            
        }
    }    
    
    public void actualizarJugador(String dniAntiguo, String dni, String nombre, Date fecha, String posicion, String foto) throws SQLException {
        try(PreparedStatement actualizar = conexion.prepareStatement("UPDATE jugador"
                + " SET dni = ?, nombre = ?, fechaNac = ?, posicion = ?, foto = ? WHERE dni = ?")) {
            
            actualizar.setString(1, dni);
            actualizar.setString(2, nombre);
            actualizar.setDate(3, fecha);
            actualizar.setString(4, posicion);
            actualizar.setString(5, foto);
            actualizar.setString(6, dniAntiguo);
            
            actualizar.execute();
            
        }
    }
    
    public Object[] buscarPorDni(String dni) throws SQLException {
        Object[] datosPersona = new Object[5];
        
        try(PreparedStatement datos = conexion.prepareStatement("SELECT * FROM jugador WHERE dni = ?")) {
            
            datos.setString(1, dni);
            
            ResultSet result = datos.executeQuery();
            
            int i = 0;
            
            if (result.next()) {
                datosPersona[0] = result.getString("dni");
                datosPersona[1] = result.getString("nombre");
                datosPersona[2] = result.getString("posicion");
                datosPersona[3] = result.getString("fechaNac");
                datosPersona[4] = result.getString("foto");
            }
            
        }
        
        return datosPersona;
    }
    
    public Object[] listarDni() throws SQLException {
        Object[] dni = null;
        
        try(PreparedStatement numeroRegistros = conexion.prepareStatement("SELECT count(*) cuenta FROM jugador");
                PreparedStatement listaDni = conexion.prepareStatement("SELECT dni FROM jugador")) {
            
            //Total de valores
            ResultSet numeroValores;
            
            numeroValores = numeroRegistros.executeQuery();
            numeroValores.next();
            int longitud = numeroValores.getInt("cuenta");            
            //
            
            ResultSet result = listaDni.executeQuery();
            
            dni = new Object[longitud+1];
            
            int i = 1;
            
            dni[0] = "";
            
            while (result.next()) {                
                dni[i] = result.getString("dni");
                
                i++;
            }
        }
        
        return dni;
    }
    
    public boolean comprobarSiExisteJugador(String dni, String nombre, String posicion, String fecha, String foto) throws SQLException {
        boolean existe = false;
        
        try (PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM jugador "
                + "WHERE dni = ? AND nombre = ? AND posicion = ? AND fechaNac = ? AND foto = ?")) {
            
            sentencia.setString(1, dni);
            sentencia.setString(2, nombre);
            sentencia.setString(3, posicion);
            sentencia.setString(4, fecha);
            sentencia.setString(5, foto);
                                   
            ResultSet result = sentencia.executeQuery();
            
            if (result.next()) {
                existe = true;
            }
        }
        
        return existe;
    }
    
    public Object[][] listarJugadores() throws SQLException {
        Object[][] jugadores;
        
        try(PreparedStatement numeroRegistros = conexion.prepareStatement("SELECT count(*) cuenta FROM jugador");
                PreparedStatement buscar = conexion.prepareStatement("SELECT * FROM jugador")) {         
            
            //Total de registros de la bd                                              
            ResultSet numeroValores;
            
            numeroValores = numeroRegistros.executeQuery();
            numeroValores.next();
            int longitud = numeroValores.getInt("cuenta");
            
            //Creamos el objeto en el que meteremos los datos            
            ResultSet resul = buscar.executeQuery();  
            
            jugadores = new Object[longitud][3];
            
            int i = 0;
            
            while(resul.next()) {
                jugadores[i][0] = resul.getString("dni");
                jugadores[i][1] = resul.getString("nombre");
                jugadores[i][2] = resul.getString("posicion");
                
                i++;
            }
            
        }
        
        return jugadores;
    }

    public String buscarFoto(String dni) throws SQLException {
        String foto = "";
            
        try (PreparedStatement sentencia = conexion.prepareStatement("SELECT foto FROM jugador "
                + "WHERE dni = ?")) {
            
            sentencia.setString(1, dni);
            
            ResultSet result = sentencia.executeQuery();
            
            if (result.next()) {
                foto = result.getString("foto");
            }                        
            
        } 
        
        return foto;
    }
       
}
