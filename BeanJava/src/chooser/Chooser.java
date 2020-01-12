package chooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author DAM-2
 */
public class Chooser extends JPanel implements Serializable {
    private JButton boton;
    private JLabel lbFoto;
    //Propiedades
    private String textoBoton;
    private String URLPorDefecto;
    protected static String foto;
    
    ////Constructor////
    public Chooser() {
        textoBoton = "Buscar foto:";
        URLPorDefecto = "/imagenes/monigote.png";
        foto = URLPorDefecto;
        
        boton = new JButton(textoBoton);
        add(boton);        
                
        lbFoto = new JLabel(new ImageIcon(getClass().getResource(URLPorDefecto)));        
        add(lbFoto);
        lbFoto.setPreferredSize(new Dimension(250, 200));
        
        Controlador controlador = new Controlador(this);
        addManejador(controlador);                
    }
    
    ////Manejadores////
    
    public void addManejador(ActionListener al) {
        boton.addActionListener(al);
    }
    
    ////Métodos get y set de las propiedades////
    public String getTextoBoton() {
        return textoBoton;
    }
    
    public void setTextoBoton(String texto) {
        textoBoton = texto;
        boton.setText(textoBoton);
    }
    
    public String getURLPorDefecto() {
        return URLPorDefecto;
    }        
    
    public void setURLPorDefecto(String url) {        
        try {                    
            URLPorDefecto = url;
            foto = URLPorDefecto;
            
            Icon img = new ImageIcon(new ImageIcon(getClass().getResource(url)).getImage());
            lbFoto.setIcon(img);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "La ruta por defecto del bean es incorrecta", "Error en la ruta por defecto", JOptionPane.ERROR_MESSAGE); 
            System.exit(0);
        }
        
    }
    
    public void setURLFoto(String URL) {
        foto = URL;
            
        Icon img = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(getWidthFoto(), getHeightFoto(), Image.SCALE_DEFAULT));
        //Icon img = new ImageIcon(new ImageIcon(URL).getImage());
        lbFoto.setIcon(img);
    }
    
    ////Métodos propios////
        
    public String getURLFoto() {        
        return lbFoto.getIcon().toString();
    }
    
    public String getURL() {
        return foto;
    }
    
    protected int getHeightFoto() {
        return lbFoto.getHeight();
    }    
    
    protected int getWidthFoto() {
        return lbFoto.getWidth();
    }
    
    protected String getFoto() {        
        return lbFoto.getIcon().toString();
    }
    
    protected void setFoto(Icon img) {
        lbFoto.setIcon(img);
    }

    public void reiniciarFotoPorDefecto() {        
        try {
            setFoto(new ImageIcon(getClass().getResource(URLPorDefecto)));
            foto = URLPorDefecto;
        } catch (NullPointerException e) {
            throw new NullPointerException("Ruta de la imagen por defecto incorrecta");
        }
        
    }
        
}    