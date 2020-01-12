/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import chooser.Chooser;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author DAM-2
 */
public class PanelAnnadir extends JPanel {
    private JPanel panelDatos, panelFoto, panelBotones;
    private JLabel lbDni, lbNombre, lbFechaNac, lbPosicion;
    private JTextField txtNombre;
    private JComboBox combo1;
    private JFormattedTextField txtDni;
    private MaskFormatter mascaraDni;
    private JButton btBorrar, btAnnadir;
    private Chooser foto;
    private JDateChooser calendario;
    
    public PanelAnnadir() {
        setLayout(new BorderLayout());
        
        panelDatos = new JPanel();
        add(panelDatos, BorderLayout.CENTER);
        panelDatos.setLayout(new GridLayout(4, 2, 0, 5));
        
        lbDni = new JLabel("Dni:", JLabel.RIGHT);
        panelDatos.add(lbDni);
        
        try {
            mascaraDni = new MaskFormatter("########?");
        } catch (ParseException ex) {
            Logger.getLogger(PanelAnnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        txtDni = new JFormattedTextField(mascaraDni);
        panelDatos.add(txtDni);
        
        lbNombre = new JLabel("Nombre:", JLabel.RIGHT);
        panelDatos.add(lbNombre);
        txtNombre = new JTextField();
        panelDatos.add(txtNombre);                                       
        
        //ComboBox
        lbPosicion = new JLabel("Posición:", JLabel.RIGHT);
        panelDatos.add(lbPosicion);
        
        combo1 = new JComboBox();
        panelDatos.add(combo1);                                        
        
        lbFechaNac = new JLabel("Fecha de nacimiento:", JLabel.RIGHT);
        panelDatos.add(lbFechaNac);                 
        
        calendario = new JDateChooser("yyyy/MM/dd","####/##/##",'_');        
        panelDatos.add(calendario);                
        
        //Panel de la foto
        panelFoto = new JPanel();        
        
        add(panelFoto, BorderLayout.NORTH);
        
        panelFoto.setLayout(new FlowLayout(FlowLayout.RIGHT));
        foto = new Chooser();
        panelFoto.add(foto);          
                                        
        //Panel botones
        panelBotones = new JPanel();        
        add(panelBotones, BorderLayout.SOUTH);
        panelBotones.setLayout(new FlowLayout());
        
        btBorrar = new JButton("Borrar");
        panelBotones.add(btBorrar);
        btAnnadir = new JButton("Añadir");
        panelBotones.add(btAnnadir);
        
    }
    
    //Controlador
    public void annadirManejador(ActionListener al) {
        if (!btAnnadir.getActionCommand().equals("annadirJug")) {
            btAnnadir.addActionListener(al);
            btAnnadir.setActionCommand("annadirJug");
            btBorrar.addActionListener(al);
            btBorrar.setActionCommand("borrar"); 
        }                       
    }
    
    //Métodos propios
    public void annadirDatosACombo (String[] datos) {
        combo1.setModel(new DefaultComboBoxModel(datos));
    }
    
    public void borrarSeleccion() {
        setDni("");
        setNombre("");
        reiniciarPosicionCombo();
        setFechaNac();     
        foto.reiniciarFotoPorDefecto();
    }
    
    //Métodos get y set
    public String getDni() {
        return txtDni.getText();        
    }
    
    public void setDni(String cadena) {
        txtDni.setText(cadena);
        txtDni.setValue(null);
    }
    
    public String getNombre() {
        return txtNombre.getText();
    }
    
    public void setNombre(String cadena) {
        txtNombre.setText(cadena);
    }
    
    public String getPosicion() {
        return combo1.getSelectedItem().toString();
    }
    
    //Set del combobox
    public void reiniciarPosicionCombo() {
        combo1.setSelectedIndex(0);
    }
    
    public String getFoto() {
        return foto.getURLFoto();
    }
    
    public String getURL() {
        return foto.getURL();
    }
    
    public String getFotoPorDefecto() {
        return foto.getURLPorDefecto();
    }
    
    //JDateChooser de fecha
    public Date getFechaNac() {
        java.sql.Date date;
        
        try {
            java.util.Date utilDate = calendario.getDate();
            date = new java.sql.Date(utilDate.getTime());
        } catch (NullPointerException e) {
            date = null;
        }        
        
        return date;
    }
    
    public void setFechaNac() {
        calendario.setCalendar(null);
    }        
    
    
}
