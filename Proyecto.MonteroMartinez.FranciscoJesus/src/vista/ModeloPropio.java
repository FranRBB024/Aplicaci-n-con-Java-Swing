/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author francisco
 */
public class ModeloPropio extends DefaultTableModel {
    
    public Class[] tipoColumn = {String.class, String.class, String.class, String.class};

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return tipoColumn[columnIndex];
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }
    
}
