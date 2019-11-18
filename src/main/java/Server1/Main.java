/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server1;

import Server1.Metodos.MetodosDB;
import com.example.rpagv2.ClaseAlerta;
import com.example.rpagv2.DatosAlerta;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.crypto.Data;


/**
 *
 * @author spart
 */
public class Main {

    public static final int PORT_NUMBER = 6809;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Main listener = new Main();
        listener.IniciarEscucha();
    }
    
    public ArrayList<DatosAlerta> listaAlertas = new ArrayList();
    
    void IniciarEscucha() {
        System.out.println("Server1.Main.IniciarEscucha()");
        ServerSocket server = null;
        VariablesTemporales();
        
        try {
            server = new ServerSocket(PORT_NUMBER);
            while (true) {
                /**
                 * create a new {@link SocketServer} object for each connection
                 * this will allow multiple client connections
                 */
                new SocketServer(server.accept(), this);
            }
        } catch (IOException ex) {
            System.out.println("Unable to start server.");
        } finally {
            try {
                if (server != null)
                    server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    ClaseAlerta claseAccidente = new ClaseAlerta(1, "Accidente", 11, "icon_accidente");
    ClaseAlerta claseIncendio = new ClaseAlerta(2, "Incendio", 22, "icon_incendio");
    ClaseAlerta claseHerido = new ClaseAlerta(3, "Persona Herida", 33, "icon_herido");
    ClaseAlerta claseBloqueo = new ClaseAlerta(4, "Bloqueo", 44, "icon_bloqueo");
    ClaseAlerta claseCongestionamiento = new ClaseAlerta(5, "Congestionamiento", 55, "icon_congestionamiento");
    ClaseAlerta claseMarchas = new ClaseAlerta(6, "Marchas", 66, "icon_marchas");
    ClaseAlerta claseCalleDanada = new ClaseAlerta(7, "Calle Dañada", 77, "icon_calle");
    ClaseAlerta claseCorte = new ClaseAlerta(8, "Corte Electrico", 88, "icon_corte");
    
    void VariablesTemporales() {
        
        //listaAlertas.add(new DatosAlerta(1002, 2002, -17.3775, -66.1600, claseAccidente.id, new Date()));
        MetodosDB.IngresarAlerta(new DatosAlerta(1002, 2002, -17.3775, -66.1600, claseAccidente.id, new Date()));
    }
    
    
    
}
