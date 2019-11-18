/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server1;

import Server1.Metodos.MetodosDB;
import com.example.rpagv2.DatosAlerta;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author spart
 */
public class SocketServer extends Thread{
    
    protected Socket socket;

    InputStream in = null;
    OutputStream out = null;
    //DataInputStream dataInputStream;
    //DataOutputStream dataOutputStream;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    BufferedReader br;
    
    Main main;
    
    public SocketServer(Socket socket, Main main) {
        this.socket = socket;
        this.main = main;
        System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
        start();
    }

    public void run() {
        
        
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            System.out.println("in y out establecidos");
            
            //dataInputStream = new DataInputStream(in);
            //dataOutputStream = new DataOutputStream(out);
            
            
            System.out.println("Streams establecidos");
            
            
            br = new BufferedReader(new InputStreamReader(in));
            System.out.println("br establecido");
            
            String request;
            
            //recibiendo tipo de pedido
            while ((request = br.readLine()) != null) {
                System.out.println("Message received: '" + request + "'");
                
                
                switch(request) {
                    case "Actualizar": {
                        System.out.println("Metodo DevolverLista()");
                        DevolverLista();
                        break;
                    }
                    case "Alerta": {
                        System.out.println("Metodo NuevaAlerta()");
                        RecibirAlerta();
                        break;
                    }
                }
            }
            
        } catch (IOException ex) {
            System.out.println("Unable to get streams from client");
            System.err.println(ex);
            
        } /*catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } */finally {
            try {
                System.out.println("Cerrando conexion");
                in.close();
                out.close();
                //dataInputStream.close();
                //dataOutputStream.close();
                if(objectInputStream != null) {
                    objectInputStream.close();
                }
                
                objectOutputStream.close();
                socket.close();
                System.out.println("Conexion cerrada");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    void DevolverLista() {
        try {
            
            objectOutputStream = new ObjectOutputStream(out);
            System.out.println("objectOutputStream listo");
            //objectInputStream = new ObjectInputStream(in);
            //System.out.println("objectInputStream listo");
            
            objectOutputStream.writeObject(MetodosDB.RecuperarListaAlertas());
            objectOutputStream.flush();
            System.out.println("Lista Enviada");
        } catch(IOException e) {
            System.err.println(e);
        }
    }
    
    void RecibirAlerta() {
        try {
            
            objectInputStream = new ObjectInputStream(in);
            System.out.println("objectInputStream listo");
            objectOutputStream = new ObjectOutputStream(out);
            System.out.println("objectOutputStream listo");
            
            DatosAlerta alerta = (DatosAlerta) objectInputStream.readObject();
            System.out.println("Recibido: " + alerta);
            
            MetodosDB.IngresarAlerta(alerta);
            
        } catch (IOException e){
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
}
