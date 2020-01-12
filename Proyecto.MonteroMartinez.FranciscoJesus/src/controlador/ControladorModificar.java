/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Datos;
import modelo.ProyectoDAO;
import vista.PanelModificar;

/**
 *
 * @author francisco
 */
public class ControladorModificar extends WindowAdapter implements ActionListener, MouseListener {
    private PanelModificar panel;
    private ProyectoDAO dao;
    private String URLFoto;
    
    public ControladorModificar(PanelModificar otroPanel) {
        try {
            panel = otroPanel;
            dao = ProyectoDAO.getDAO();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorListar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void windowOpened(WindowEvent e) {
        try {            
            //Datos del combo de posición
            String[] datos = Datos.getDatosCombo();
        
            panel.annadirDatosAComboPosiciones(datos);
            
            //Tabla y combo de Dni
            ProyectoDAO dao = ProyectoDAO.getDAO();
            
            Object[][] jugadores = dao.listarJugadores();
            Object[] columnas = {"Dni", "Nombre", "Posición"};
            
            panel.annadirTablaCompleta(jugadores, columnas);  
            panel.annadirDatosACombo(dao.listarDni());
            
            panel.limpiarVentana();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorListar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     //Métodos de ActionListener
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String comando = arg0.getActionCommand();        
        String dni;
        
        switch(comando) {
            case "buscar": //Mostrar los datos de un jugador por su dni                
                dni = panel.getDniBuscado();
                
                if(!dni.equals("")) {
                    for (int i = 0; i < panel.getNumeroFilas(); i++) {
                        if (panel.getValorCelda(i, 0).equals(dni)) {
                            panel.cambiarElementoSeleccionado(i, 0, false, false);                
                        }
                    }                
        
                    try {
                        Object[] datos = dao.buscarPorDni(dni);

                        panel.setDni((String) datos[0]);
                        panel.setNombre((String) datos[1]);
                        panel.setPosicion((String) datos[2]);
                        panel.setFecha((String) datos[3]);
                        panel.setURLFoto((String) datos[4]);
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorListar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorModificar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Debe seleccionar un DNI para mostrar al jugador", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
                
                
            break;
            
            case "eliminar": //Eliminar un jugador
            {
                if(panel.getFilaSeleccionada() >= 0) {
                    try {
                        boolean encontrado;
                        
                        dni = panel.getDni();
                        String nombre = panel.getNombre();
                        Date fecha = panel.getFechaNac();
                        String posicion = panel.getPosicion();
                        String foto = panel.getURL();
                                                
                        encontrado = dao.comprobarSiExisteJugador(dni, nombre, posicion, fecha.toString(), foto);
                        
                        if (encontrado) {
                            dao.borrarJugadorPorDni(dni);

                            JOptionPane.showMessageDialog(panel, "Se ha eliminado correctamente al jugador", "Jugador borrado", JOptionPane.INFORMATION_MESSAGE);

                            //Actualizamos inmediatamente la tabla
                            Object[][] jugadores = dao.listarJugadores();
                            Object[] columnas = {"Dni", "Nombre", "Posición"};

                            panel.annadirTablaCompleta(jugadores, columnas);  
                            panel.annadirDatosACombo(dao.listarDni());

                            panel.limpiarVentana();
                        } else {
                            JOptionPane.showMessageDialog(panel, "No puedes borrar un jugador si lo estás modificando", "Error de borrado", JOptionPane.ERROR_MESSAGE);
                        }

                        
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(panel, "No se ha podido borrar correctamente al jugador", "Error de borrado", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            break;

            case "modificar": //Actualizar los datos de un jugador
            {
                if(panel.getFilaSeleccionada() >= 0) {
                    dni = (String) panel.getValorCelda(panel.getFilaSeleccionada(), 0);
                    String dniNuevo = panel.getDni();
                    String nombre = panel.getNombre();
                    Date fecha = panel.getFechaNac();
                    String posicion = panel.getPosicion();
                    String foto = panel.getURL();                

                    try {
                        
                        dao.actualizarJugador(dni, dniNuevo, nombre, fecha, posicion, foto);

                        JOptionPane.showMessageDialog(panel, "Se ha actualizado correctamente al jugador", "Jugador actualizado", JOptionPane.INFORMATION_MESSAGE);

                        //Actualizamos inmediatamente la tabla
                        Object[][] jugadores = dao.listarJugadores();
                        Object[] columnas = {"Dni", "Nombre", "Posición"};

                        panel.annadirTablaCompleta(jugadores, columnas);  
                        panel.annadirDatosACombo(dao.listarDni());

                        panel.limpiarVentana();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(panel, "No se han podido actualizar los datos del jugador seleccionado", "Error de actualización", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            break;

        }                
                        
    }

    //Métodos de MouseListener
    @Override
    public void mouseClicked(MouseEvent arg0) {              
        String dni = (String) panel.getValorCelda(panel.getFilaSeleccionada(), 0);

        try {
            Object[] datos = dao.buscarPorDni(dni);
            
            panel.setDni((String) datos[0]);
            panel.setNombre((String) datos[1]);
            panel.setPosicion((String) datos[2]);
            panel.setFecha((String) datos[3]);   
            URLFoto = (String) datos[4];
            panel.setURLFoto(URLFoto);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorListar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
