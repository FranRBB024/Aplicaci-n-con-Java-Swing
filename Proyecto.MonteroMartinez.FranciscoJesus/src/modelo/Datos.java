/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


/**
 *
 * @author DAM-2
 */
public class Datos {
    private static String[] datosCombo = {"PORTERO", "LATERAL IZQUIERDO", "DEFENSA CENTRAL", 
    "LATERAL DERECHO", "MEDIOCENTRO", "MEDIAPUNTA", "EXTREMO IZQUIERDO", "EXTREMO DERECHO", "DELANTERO"};
    
    private static String[] meses28Dias = {"Febrero"};
    private static String[] meses30Dias = {"Abril", "Junio", "Septiembre", "Noviembre"};
    private static String[] meses31Dias = {"Enero", "Marzo", "Mayo", "Julio", "Agosto", "Octubre", "Diciembre"};
    
    public static String[] getDatosCombo() {
        return datosCombo;
    }
    
    public static boolean buscarMesEn28Dias(String mes) {
        boolean encontrado = false;
        
        int i = 0;
        
        while (i < meses28Dias.length || encontrado) {    
            if (meses28Dias[i].equalsIgnoreCase(mes)) {
                encontrado = true;
            }
            
            i++;
        }
        
        return encontrado;
    }
    
    public static boolean buscarMesEn30Dias(String mes) {
        boolean encontrado = false;
        
        int i = 0;
        
        while (i < meses30Dias.length || encontrado) {    
            if (meses30Dias[i].equalsIgnoreCase(mes)) {
                encontrado = true;
            }
            
            i++;
        }
        
        return encontrado;
    }
    
    public static boolean buscarMesEn31Dias(String mes) {
        boolean encontrado = false;
        
        int i = 0;
        
        while (i < meses31Dias.length || encontrado) {    
            if (meses31Dias[i].equalsIgnoreCase(mes)) {
                encontrado = true;
            }
            
            i++;
        }
        
        return encontrado;
    }
            

  
}
