package chooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author DAM-2
 */
public class Controlador implements ActionListener {
    private Chooser jchooser;
    private ImageIcon imagen;
    private Icon img;
    
    public Controlador(Chooser otroChooser) {
        jchooser = otroChooser;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        JFileChooser chooser = new JFileChooser();
        String ruta;
        
        int seleccion = chooser.showOpenDialog(jchooser);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            
            File abrir = chooser.getSelectedFile();
            
            ruta = abrir.getPath();
            
            Chooser.foto = ruta;
            
            imagen = new ImageIcon(ruta);
            img = new ImageIcon(imagen.getImage().getScaledInstance(jchooser.getWidthFoto(), jchooser.getHeightFoto(), Image.SCALE_DEFAULT));
            
            jchooser.setFoto(img);                        
            
        }
    }
    
}
