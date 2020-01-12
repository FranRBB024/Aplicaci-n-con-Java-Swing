/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 *
 * @author DAM-2
 */
public class PanelPrincipal extends JPanel {
    private JTextPane panelImagen;
    private JLabel titulo;
    private ImageIcon img;
    
    public PanelPrincipal() {
        setLayout(new FlowLayout());
        
        panelImagen = new JTextPane();
        img = new ImageIcon(getClass().getResource("/imagenes/atleti.png"));
        
        panelImagen.insertIcon(img);
        add(panelImagen);
        
        panelImagen.setEditable(false);        
        
        titulo = new JLabel("<html>ATLÉTICO DE MADRID <br/> ALEVÍN B</html>");
        add(titulo);
        
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setVerticalAlignment(JLabel.CENTER);   
    }   
    
}
