/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SpartanCat7
 */
public class ConexionTest {
    
    public ConexionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getConnection method, of class Conexion.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        Conexion instance = null;
        try {
            instance = new Conexion();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection result = instance.getConnection();
        assertTrue(result != null);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of disconnect method, of class Conexion.
     */
    @Test
    public void testDisconnect() {
        System.out.println("disconnect");
        Conexion instance = null;
        try {
            instance = new Conexion();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        instance.disconnect();
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
