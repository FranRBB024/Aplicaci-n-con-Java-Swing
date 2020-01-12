/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import controlador.ControladorAnnadir;
import controlador.ControladorListar;
import controlador.ControladorModificar;
import controlador.ControladorPrincipal;
import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import vista.BarraHerramientas;
import vista.BarraMenus;
import vista.PanelAnnadir;
import vista.PanelListar;
import vista.PanelModificar;
import vista.PanelPrincipal;

/**
 *
 * @author DAM-2
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Alevín B Atlético de Madrid");
        PanelPrincipal panel = new PanelPrincipal();
        PanelAnnadir panelAnnadir = new PanelAnnadir();        
        PanelListar panelListar = new PanelListar();
        PanelModificar panelModificar = new PanelModificar();
                        
        BarraMenus menu = new BarraMenus();
        BarraHerramientas herramientas = new BarraHerramientas();
        
        ControladorAnnadir controladorSec = new ControladorAnnadir(panelAnnadir);
        ControladorListar controladorList = new ControladorListar(panelListar);
        ControladorModificar controladorMod = new ControladorModificar(panelModificar);
        ControladorPrincipal controlador = new ControladorPrincipal(panelAnnadir, panelListar, ventana, controladorSec, controladorList, panelModificar, controladorMod);                
                
        ventana.setResizable(false);
        
        ventana.setJMenuBar(menu);
        ventana.add(herramientas, BorderLayout.NORTH);
        ventana.add(panel, BorderLayout.CENTER);
        
        menu.annadirManejador(controlador);
        herramientas.annadirManejador(controlador);        
        
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500, 400);
        ventana.setVisible(true);
    }
    
}
