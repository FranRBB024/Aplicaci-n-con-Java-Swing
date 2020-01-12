/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 *
 * @author DAM-2
 */
public class BarraHerramientas extends JToolBar {
    private JButton annadir, listar, modificar;
    private ImageIcon imgAnnadir, imgListar, imgModificar;
    
    public BarraHerramientas() {        
        
        imgAnnadir = new ImageIcon(getClass().getResource("/imagenes/mas.png"));
        imgListar = new ImageIcon(getClass().getResource("/imagenes/listar.png"));
        imgModificar = new ImageIcon(getClass().getResource("/imagenes/modificar.png"));
        
        annadir = new JButton(imgAnnadir);
        listar = new JButton(imgListar);
        modificar = new JButton(imgModificar);
        
        add(annadir);
        add(listar);
        add(modificar);
        
        annadir.setToolTipText("Añadir");
        listar.setToolTipText("Listar");
        modificar.setToolTipText("Modificar");
            
    }
    
    public void annadirManejador(ActionListener al) {
        annadir.addActionListener(al);
        annadir.setActionCommand("annadir");
        listar.addActionListener(al);
        listar.setActionCommand("listar");
        modificar.addActionListener(al);
        modificar.setActionCommand("modificar");
    }
}
