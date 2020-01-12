/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import chooser.Chooser;
import com.toedter.calendar.JDateChooser;
import controlador.ControladorModificar;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author francisco
 */
public class PanelModificar extends JPanel {
    private JPanel panelSuperior, panelCentro, panelTabla, panelModificar, panelFoto, panelDatos, panelBotones;
    private JLabel lbDni, lbNombre, lbPosicion, lbFechaNac;
    private JLabel lbBuscar;
    private JTextField txtNombre;
    private JComboBox combo1;
    private JFormattedTextField txtDni;
    private MaskFormatter mascaraDni;
    private JComboBox comboBuscar;    
    private JButton btBuscar, btEliminar, btModificar;
    private JTable tabla;
    private ModeloPropio modelo;
    private JScrollPane scrollTabla;
    private Chooser foto;
    private JDateChooser calendario;
    
    public PanelModificar() {        
        //Panel superior
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
        
        add(panelSuperior);
        
        comboBuscar = new JComboBox();
        
        panelSuperior.add(comboBuscar);
        
        btBuscar = new JButton("Buscar");
        
        panelSuperior.add(btBuscar);
        
        //Panel del centro
        panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(1,2));
        
        add(panelCentro);
        
        //Panel de la tabla
        panelTabla = new JPanel();
        panelTabla.setLayout(new GridLayout(1,1));
        
        panelCentro.add(panelTabla);                
        
        modelo = new ModeloPropio();
        tabla = new JTable(modelo);
        
        scrollTabla = new JScrollPane(tabla);
        
        panelTabla.add(scrollTabla);
        
        //Panel del jugador
        panelDatos = new JPanel();
        panelCentro.add(panelDatos);
        panelDatos.setLayout(new GridLayout(3, 1, 0, 5));
        
        //Panel de la foto
        panelFoto = new JPanel();
        
        panelDatos.add(panelFoto);
        
        panelFoto.setLayout(new FlowLayout(FlowLayout.RIGHT));
        foto = new Chooser();
        panelFoto.add(foto);               
        
        //Panel de los datos del jugador
        panelModificar = new JPanel();        
        panelModificar.setLayout(new GridLayout(4, 2, 0, 5));
        
        panelDatos.add(panelModificar);
        
        lbDni = new JLabel("Dni:", JLabel.RIGHT);
        panelModificar.add(lbDni);
        
        try {
            mascaraDni = new MaskFormatter("########?");
        } catch (ParseException ex) {
            Logger.getLogger(PanelAnnadir.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        txtDni = new JFormattedTextField(mascaraDni);
        panelModificar.add(txtDni);
        
        lbNombre = new JLabel("Nombre:", JLabel.RIGHT);
        panelModificar.add(lbNombre);
        txtNombre = new JTextField();
        panelModificar.add(txtNombre);                                       
        
        lbPosicion = new JLabel("Posición:", JLabel.RIGHT);
        panelModificar.add(lbPosicion);
        
        combo1 = new JComboBox();
        panelModificar.add(combo1);                                        
        
        lbFechaNac = new JLabel("Fecha de nacimiento:", JLabel.RIGHT);
        panelModificar.add(lbFechaNac);                 
        
        calendario = new JDateChooser("yyyy/MM/dd","####/##/##",'_');        
        panelModificar.add(calendario);                               
                                
        //Panel botones
        panelBotones = new JPanel();        
        panelDatos.add(panelBotones);
        panelBotones.setLayout(new FlowLayout());
        
        btEliminar = new JButton("Eliminar jugador");
        panelBotones.add(btEliminar);
        btModificar = new JButton("Modificar jugador");
        panelBotones.add(btModificar);
        
    }
    
    public void limpiarVentana() {
        txtDni.setText("");
        txtNombre.setText("");
        combo1.setSelectedIndex(0);
        calendario.setCalendar(null);
        foto.reiniciarFotoPorDefecto();
    }
    
    //Métodos set necesarios  
    public void setFotoPorDefecto(String URL){
        foto.setURLPorDefecto(URL);
    }
    
    public void setURLFoto(String URL){
        foto.setURLFoto(URL);
    }
    
    public String getDni() {
        return txtDni.getText();
    }
    
    public void setDni(String dni) {
        txtDni.setText(dni);
    }
    
    public String getNombre() {
        return txtNombre.getText();
    }
    
    public void setNombre(String nombre) {
        txtNombre.setText(nombre.toUpperCase());
    }
    
    public java.sql.Date getFechaNac() {  
        java.util.Date utilDate = calendario.getDate();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        
        return date;
    }
    
    public void setFecha(String fecha) throws ParseException {  
        fecha = fecha.replaceAll("-", "/");
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date fechaDate = formato.parse(fecha);
        calendario.setDate(fechaDate);   
    }
    
    public String getPosicion() {
        return combo1.getModel().getSelectedItem().toString();
    }
    
    public void setPosicion(String posicion) {                
        combo1.setSelectedItem(posicion);
    }
    
    public String getFotoPorDefecto() {
        return foto.getURLPorDefecto();
    }
    
    public String getFoto() {
        return foto.getURLFoto();
    }
    
    public String getURL() {
        return foto.getURL();
    }
    
    //Métodos para cargar el panel    
    public void annadirTablaCompleta(Object[][] fila, Object[] columnas) {
        modelo.setDataVector(fila, columnas);
    }
    
    public void annadirDatosACombo (Object[] datos) throws SQLException {        
        comboBuscar.setModel(new DefaultComboBoxModel(datos));
    }
    
    public void annadirDatosAComboPosiciones (Object[] datos) throws SQLException {        
        combo1.setModel(new DefaultComboBoxModel(datos));
    }
    
     //Métodos propios
    public String getDniBuscado(){
        return comboBuscar.getModel().getSelectedItem().toString();
    }
    
    public int getNumeroFilas () {
       return tabla.getRowCount();
    }
    
    public int getNumeroColumnas () {
        return tabla.getColumnCount();
    }
    
    public Object getValorCelda(int i, int j) {
       return modelo.getValueAt(i, j);
    }
    
    //////////////////////////Fila seleccionada/////////////////////////////////    
    public int getFilaSeleccionada () {        
        return tabla.getSelectedRow();
    } 
    
    public void cambiarElementoSeleccionado (int i, int j, boolean b, boolean b0) { 
        tabla.changeSelection(i, j, b, b0);
    } 
    
    //Controlador
    public void annadirManejador(ControladorModificar cl) {
        if (!btBuscar.getActionCommand().equals("buscar")) {
            btBuscar.addActionListener(cl);
            btBuscar.setActionCommand("buscar");
            btEliminar.addActionListener(cl);
            btEliminar.setActionCommand("eliminar");
            btModificar.addActionListener(cl);
            btModificar.setActionCommand("modificar");
            tabla.addMouseListener(cl); 
        }               
    }
}
