/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import modelo.ConexionBD;
import vista.PanelAnnadir;
import vista.PanelListar;
import vista.PanelModificar;

/**
 *
 * @author DAM-2
 */
public class ControladorPrincipal implements ActionListener {
    private JFrame ventana;
    private PanelAnnadir panel;
    private PanelListar panelListar;
    private PanelModificar panelModificar;
    private ControladorAnnadir controlador;
    private ControladorListar contList;
    private ControladorModificar contMod;
    private ImageIcon imagen;
    private Image image;
    
    public ControladorPrincipal(PanelAnnadir otroPanel, PanelListar otroListar, 
            JFrame otraVentana, ControladorAnnadir otroCont, ControladorListar otroList, 
            PanelModificar otroModificar, ControladorModificar otroMod) {
        
        panel = otroPanel;
        panelListar = otroListar;
        ventana = otraVentana;
        controlador = otroCont;
        contList = otroList;
        panelModificar = otroModificar;
        contMod = otroMod;
        
        imagen = new ImageIcon(getClass().getResource("/imagenes/atleti.png"));
        image = imagen.getImage();
        ventana.setIconImage(image);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String comando = ae.getActionCommand();
        
        switch(comando) {
            case "salir":
            {
                try {
                    ConexionBD.cerrarConexion();
                    
                    System.exit(0);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }                
                break;
            case "annadir":                                
                JDialog ventanaAnnadir = new JDialog(ventana, true);
                ventanaAnnadir.setTitle("Añadir jugadores");
                ventanaAnnadir.setResizable(false);                                
                
                ventanaAnnadir.setIconImage(image);
                
                ventanaAnnadir.addWindowListener(controlador);
                panel.annadirManejador(controlador);
                ventanaAnnadir.setContentPane(panel);
                ventanaAnnadir.setSize(500, 500);
                ventanaAnnadir.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                ventanaAnnadir.setVisible(true);
                
                panel.borrarSeleccion();
                break;
            case "listar":
                JDialog ventanaListar = new JDialog(ventana, true);
                ventanaListar.setTitle("Listar jugadores");
                ventanaListar.setResizable(false);
                
                ventanaListar.setIconImage(image);
                
                ventanaListar.addWindowListener(contList);
                panelListar.annadirManejador(contList);                
                ventanaListar.setContentPane(panelListar);
                ventanaListar.setSize(950, 600);
                ventanaListar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                ventanaListar.setVisible(true);                
                break;
            case "modificar":
                JDialog ventanaModificar = new JDialog(ventana, true);
                ventanaModificar.setTitle("Modificar jugadores");
                ventanaModificar.setResizable(false);
                
                ventanaModificar.setIconImage(image);
                
                ventanaModificar.addWindowListener(contMod);
                panelModificar.annadirManejador(contMod);
                ventanaModificar.setContentPane(panelModificar);
                ventanaModificar.setSize(950, 680);
                ventanaModificar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                ventanaModificar.setVisible(true);
                break;
        }
    }        
    
}
