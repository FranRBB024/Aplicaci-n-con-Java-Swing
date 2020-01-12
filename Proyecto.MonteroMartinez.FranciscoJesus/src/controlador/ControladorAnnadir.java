/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Datos;
import modelo.ProyectoDAO;
import vista.PanelAnnadir;

/**
 *
 * @author DAM-2
 */
public class ControladorAnnadir extends WindowAdapter implements ActionListener {
    private PanelAnnadir panel;
    private ProyectoDAO dao;  
    
    public ControladorAnnadir(PanelAnnadir otroPanel) {        
        try {
            panel = otroPanel;
            dao = ProyectoDAO.getDAO();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAnnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String comando = ae.getActionCommand();
        
        switch(comando) {
            case "annadirJug":                                     
                try {
                    if (!panel.getURL().contains(panel.getFotoPorDefecto())) {
                        
                        if (!panel.getNombre().equals("") && panel.getFechaNac() != null) {
                            
                            if (!panel.getDni().equals("         ")) {
                                dao.annadirJugador(panel.getDni(), panel.getNombre(), panel.getFechaNac(), panel.getPosicion(), panel.getFoto());
                                        
                                JOptionPane.showMessageDialog(panel, "Jugador añadido correctamente", "Operación realizada", JOptionPane.INFORMATION_MESSAGE);

                                panel.borrarSeleccion();
                            } else {
                               JOptionPane.showMessageDialog(panel, "Formato de DNI incorrecto", "Error de inserción", JOptionPane.ERROR_MESSAGE); 
                            }                            
                            
                        } else {
                            JOptionPane.showMessageDialog(panel, "Todos los campos son obligatorios", "Error de inserción", JOptionPane.ERROR_MESSAGE);
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(panel, "Tienes que elegir una foto", "Error de inserción", JOptionPane.ERROR_MESSAGE);
                    }                                        
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "El jugador con dni "+panel.getDni()+" ya está registrado en la base de datos.", "Error de inserción", JOptionPane.ERROR_MESSAGE);
                }
        
                break;
            case "borrar":
                panel.borrarSeleccion();
                break;
        }
    }
    
    @Override
    public void windowOpened(WindowEvent we) {
        String[] datos = Datos.getDatosCombo();
        
        panel.annadirDatosACombo(datos);
        
        panel.borrarSeleccion();
    }
    
}
