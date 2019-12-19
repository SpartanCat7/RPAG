/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server1.Metodos;

import com.example.rpagv2.Comentario;
import com.example.rpagv2.Confirmacion;
import com.example.rpagv2.DatosAlerta;
import com.example.rpagv2.Imagen;
import com.example.rpagv2.Reporte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
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
public class MetodosDBTest {
    
    /**
     *
     */
    public MetodosDBTest() {
    }
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
        System.out.println("Iniciando MetodosDBTest");
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of IngresarAlerta method, of class MetodosDB.
     */
    @Test
    public void testIngresarAlerta() {
        System.out.println("IngresarAlerta");
        DatosAlerta alerta = new DatosAlerta(0, -1, 0, 0, 0, new Date());
        
        boolean result = MetodosDB.IngresarAlerta(alerta) > 0;
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
    
    }

    /**
     * Test of RecuperarListaAlertas method, of class MetodosDB.
     */
    @Test
    public void testRecuperarListaAlertas() {
        System.out.println("RecuperarListaAlertas");
        ArrayList<DatosAlerta> result = MetodosDB.RecuperarListaAlertas();
        assertTrue(result.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of IngresarConfirmacion method, of class MetodosDB.
     */
    @Test
    public void testIngresarConfirmacion() {
        System.out.println("IngresarConfirmacion");
        Confirmacion confirmacion = new Confirmacion();
        confirmacion.id = 0; //La id es irrelevante, la DB generará una
        confirmacion.id_alerta = -1; //El confirmacion no pertenece a ninguna alerta
        confirmacion.id_usuario = -1; //El confirmacion no pertenece a ningun usuario
        confirmacion.fecha = new Date();
        int expResult = 1;
        int result = MetodosDB.IngresarConfirmacion(confirmacion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of RecuperarListaConfirmaciones method, of class MetodosDB.
     */
    @Test
    public void testRecuperarListaConfirmaciones() {
        System.out.println("RecuperarListaConfirmaciones");
        ArrayList<Confirmacion> result = MetodosDB.RecuperarListaConfirmaciones();
        assertTrue(result.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of IngresarReporte method, of class MetodosDB.
     */
    @Test
    public void testIngresarReporte() {
        System.out.println("IngresarReporte");
        Reporte reporte = new Reporte();
        reporte.id = 0; //La id es irrelevante, la DB generará una
        reporte.id_alerta = -1;
        reporte.id_usuario = -1;
        reporte.fecha = new Date();
        int expResult = 1;
        int result = MetodosDB.IngresarReporte(reporte);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of RecuperarListaReportes method, of class MetodosDB.
     */
    @Test
    public void testRecuperarListaReportes() {
        System.out.println("RecuperarListaReportes");
        ArrayList<Reporte> result = MetodosDB.RecuperarListaReportes();
        assertTrue(result.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of IngresarComentario method, of class MetodosDB.
     */
    @Test
    public void testIngresarComentario() {
        System.out.println("IngresarComentario");
        Comentario comentario = new Comentario();
        comentario.id = 0; //La id es irrelevante, la DB generará una
        comentario.id_alerta = -1; //El comentario no pertenece a ninguna alerta
        comentario.id_usuario = -1; //El comentario no pertenece a ningun usuario
        comentario.fecha = new Date();
        comentario.texto = "Este es un comentario de testeo";
        
        int result = MetodosDB.IngresarComentario(comentario);
        assertTrue(result > 0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of RecuperarListaComentarios method, of class MetodosDB.
     */
    @Test
    public void testRecuperarListaComentarios() {
        System.out.println("RecuperarListaComentarios");
        ArrayList<Comentario> result = MetodosDB.RecuperarListaComentarios();
        assertTrue(result.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of IngresarImagen method, of class MetodosDB.
     * @throws java.lang.Exception
     */
    @Test
    public void testIngresarImagen() throws Exception {
        System.out.println("IngresarImagen");
        Imagen imagen = new Imagen();
        imagen.id = 0;
        imagen.id_alerta = -1;
        imagen.id_usuario = -1;
        imagen.fecha = new Date();
        imagen.nombre = imagen.fecha.getTime() + "testIngresarImagen.png";
        imagen.bitmap = new byte[1024];
        
        int expResult = 1;
        int result = MetodosDB.IngresarImagen(imagen);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of RecuperarListaImagenes method, of class MetodosDB.
     */
    @Test
    public void testRecuperarListaImagenes() {
        System.out.println("RecuperarListaImagenes");
        ArrayList<Imagen> result = MetodosDB.RecuperarListaImagenes();
        assertTrue(result.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of RecuperarImagen method, of class MetodosDB.
     */
    @Test
    public void testRecuperarImagen() {
        System.out.println("RecuperarImagen");
        String nombre = "testRecuperarImagen.png";
        File file = new File(MetodosDB.PIC_DIRECTORIO, nombre);
        file.getParentFile().mkdirs();
        byte[] data = new byte[1024];
        if(!file.exists()){
            try {
                file.createNewFile();
                FileUtils.writeByteArrayToFile(file, data);
            } catch (IOException ex) {
                Logger.getLogger(MetodosDBTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        byte[] expResult = data;
        byte[] result = MetodosDB.RecuperarImagen(nombre);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
