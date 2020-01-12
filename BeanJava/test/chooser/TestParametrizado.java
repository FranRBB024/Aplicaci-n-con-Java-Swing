/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chooser;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author DAM-2
 */
@RunWith(Parameterized.class)
public class TestParametrizado {
    private String URL;
    
    public TestParametrizado(String URL) {
        this.URL = URL;        
    }
    
    @Test
    public void test() {        
        Chooser instance = new Chooser();        
        instance.setURLPorDefecto(URL);            
        String result = instance.getURLPorDefecto();
                
        assertEquals(this.URL, result);        
    }
    
    @Parameters
    public static Collection<Object[]> urls() {
        return Arrays.asList(new Object[][] {
            {"/imagenes/monigote.png"}, {"/imagenes/buscar.png"}, {"/imagenes/buscar.pn"}      
        });
    }
}
