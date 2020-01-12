/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author DAM-2
 */
public class BarraMenus extends JMenuBar {
    private JMenu archivo, operaciones;
    private JMenuItem salir, annadir, listar, modificar;
    
    public BarraMenus() {
        archivo = new JMenu("Archivo");
        operaciones = new JMenu("Operaciones");
        
        add(archivo);
        add(operaciones);
        
        salir = new JMenuItem("Salir");
        annadir = new JMenuItem("Añadir jugador");
        listar = new JMenuItem("Listar jugadores");
        modificar = new JMenuItem("Modificar jugadores");
        
        archivo.add(salir);
        operaciones.add(annadir);
        operaciones.add(listar);
        operaciones.add(modificar);
    }
    
    public void annadirManejador(ActionListener al) {
        salir.addActionListener(al);
        salir.setActionCommand("salir");
        annadir.addActionListener(al);
        annadir.setActionCommand("annadir");
        listar.addActionListener(al);
        listar.setActionCommand("listar");
        modificar.addActionListener(al);
        modificar.setActionCommand("modificar");
    }
}
