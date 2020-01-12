/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorListar;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author DAM-2
 */
public class PanelListar extends JPanel{
    private JPanel panelSuperior, panelCentro, panelTabla, panelJugador, panelFoto, panelDatos;
    private JLabel lbBuscar, lbDni, lbNombre, lbPosicion, lbFecha, lbFoto;
    private JComboBox comboBuscar;
    private JButton btBuscar;
    private JTable tabla;
    private ModeloPropio modelo;
    private JScrollPane scrollTabla;
    
    public PanelListar() {
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
        
        //Panel de la información del jugador
        panelJugador = new JPanel();
        panelJugador.setLayout(new GridLayout(2, 1));
        
        panelCentro.add(panelJugador);                
        
        //Panel de la foto dentro del panel del jugador
        panelFoto = new JPanel();
        panelFoto.setLayout(new FlowLayout());
        
        panelJugador.add(panelFoto);
        
        lbFoto = new JLabel("Aquí se verán los datos del jugador que selecciones...");
        lbFoto.setPreferredSize(new Dimension(370, 250));
        
        panelFoto.add(lbFoto);
        lbFoto.setHorizontalAlignment(JLabel.CENTER);
        
        
        //Panel de los datos
        panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(4, 1));
        
        panelJugador.add(panelDatos);
        
        lbDni = new JLabel();
        panelDatos.add(lbDni);
        lbDni.setHorizontalAlignment(JLabel.CENTER);
        
        lbNombre = new JLabel();
        panelDatos.add(lbNombre);
        lbNombre.setHorizontalAlignment(JLabel.CENTER);
        
        lbPosicion = new JLabel();
        panelDatos.add(lbPosicion);
        lbPosicion.setHorizontalAlignment(JLabel.CENTER);
        
        lbFecha = new JLabel();
        panelDatos.add(lbFecha);
        lbFecha.setHorizontalAlignment(JLabel.CENTER);
        
    }
    
    public void limpiarVentana() {
        lbDni.setText("");
        lbNombre.setText("");
        lbPosicion.setText("");
        lbFecha.setText("");
        lbFoto.setIcon(null);
        lbFoto.setText("Aquí se verán los datos del jugador que selecciones...");
    }
    
    //Métodos set necesarios
    public void setFoto(ImageIcon img) {
        lbFoto.setText("");
        Icon icono = new ImageIcon(img.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
        lbFoto.setIcon(icono);
    }
    
    public void setDni(String dni) {
        lbDni.setText(dni);
    }
    
    public void setNombre(String nombre) {
        lbNombre.setText(nombre.toUpperCase());
    }
    
    public void setFecha(String fecha) {
        lbFecha.setText(fecha);
    }
    
    public void setPosicion(String posicion) {                
        lbPosicion.setText(posicion);
    }
    
    //Métodos para cargar el panel
    
    public void annadirTablaCompleta(Object[][] fila, Object[] columnas) {
        modelo.setDataVector(fila, columnas);
    }
    
    public void annadirDatosACombo (Object[] datos) throws SQLException {        
        comboBuscar.setModel(new DefaultComboBoxModel(datos));
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
    public void annadirManejador(ControladorListar cl) {
        if (!btBuscar.getActionCommand().equals("buscar")) {
            btBuscar.addActionListener(cl);
            btBuscar.setActionCommand("buscar");
            tabla.addMouseListener(cl);
        }        
    }
    
}
