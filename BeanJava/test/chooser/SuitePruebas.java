package chooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author francisco
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ChooserTest.class,
    TestParametrizado.class
})
public class SuitePruebas {

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("Inicio del suite de pruebas");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("Fin del suite de pruebas");
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
