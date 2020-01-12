/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chooser;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DAM-2
 */
public class ChooserTest {
    
    public ChooserTest() {
    }

    /**
     * Test of setTextoBoton method, of class Chooser.
     */
    @Test
    public void testSetTextoBoton() {
        System.out.println("setTextoBoton");
        String texto = "Buscar información:";
        Chooser instance = new Chooser();
        instance.setTextoBoton(texto);
        String result = instance.getTextoBoton();
        assertEquals(texto, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setURLPorDefecto method, of class Chooser.
     */
    @Test
    public void testSetURLPorDefecto() {
        System.out.println("setURLPorDefecto");
        String url = "/imagenes/buscar.png";
        Chooser instance = new Chooser();
        instance.setURLPorDefecto(url);
        String result = instance.getURLPorDefecto();
        assertEquals(url, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }


    /**
     * Test of setFoto method, of class Chooser.
     */
    @Test
    public void testSetFoto() {
        System.out.println("setFoto");
        Icon icon = new ImageIcon(getClass().getResource("/imagenes/buscar.png"));
        Chooser instance = new Chooser();
        instance.setFoto(icon);
        String result = instance.getFoto();
        assertEquals(icon.toString(), result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
