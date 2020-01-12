/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.ProyectoDAO;
import vista.PanelListar;

/**
 *
 * @author francisco
 */
public class ControladorListar extends WindowAdapter implements ActionListener, MouseListener {
    private PanelListar panel;
    private ProyectoDAO dao;
    
    public ControladorListar(PanelListar otroPanel) {
        try {
            panel = otroPanel;
            dao = ProyectoDAO.getDAO();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorListar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void windowOpened(WindowEvent e) {
        try {                        
            ProyectoDAO dao = ProyectoDAO.getDAO();
            
            Object[][] jugadores = dao.listarJugadores();
            Object[] columnas = {"Dni", "Nombre", "Posición"};
            
            panel.annadirTablaCompleta(jugadores, columnas);  
            panel.annadirDatosACombo(dao.listarDni());
            
            panel.limpiarVentana();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorListar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Métodos de ActionListener
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String dni = panel.getDniBuscado();
        
        if(!dni.equals("")) {
            
            for (int i = 0; i < panel.getNumeroFilas(); i++) {
                if (panel.getValorCelda(i, 0).equals(dni)) {
                    panel.cambiarElementoSeleccionado(i, 0, false, false);                
                }
            }
                
        
            try {
                Object[] datos = dao.buscarPorDni(dni);

                panel.setDni((String) datos[0]);
                panel.setNombre((String) datos[1]);
                panel.setPosicion((String) datos[2]);
                panel.setFecha((String) datos[3]);
                panel.setFoto(new ImageIcon((String) datos[4]));
            } catch (SQLException ex) {
                Logger.getLogger(ControladorListar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Debe seleccionar un DNI para mostrar al jugador", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }           
                        
    }

    //Métodos de MouseListener
    @Override
    public void mouseClicked(MouseEvent arg0) {              
        String dni = (String) panel.getValorCelda(panel.getFilaSeleccionada(), 0);

        try {
            Object[] datos = dao.buscarPorDni(dni);
            
            panel.setDni((String) datos[0]);
            panel.setNombre((String) datos[1]);
            panel.setPosicion((String) datos[2]);
            panel.setFecha((String) datos[3]);
            panel.setFoto(new ImageIcon((String) datos[4]));
                                      
        } catch (SQLException ex) {
            Logger.getLogger(ControladorListar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}        