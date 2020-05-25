/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server1;

import Server1.Metodos.MetodosDB;
import com.example.rpagv2.Comentario;
import com.example.rpagv2.Confirmacion;
import com.example.rpagv2.DatosAlerta;
import com.example.rpagv2.Imagen;
import com.example.rpagv2.PackDatos;
import com.example.rpagv2.Reporte;
import java.io.BufferedReader;
import java.io.File;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
//import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
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
                    case "Confirmacion": {
                        System.out.println("Metodo RecibirConfirmacion()");
                        RecibirConfirmacion();
                        break;
                    }
                    case "Reporte": {
                        System.out.println("Metodo RecibirReporte()");
                        RecibirReporte();
                        break;
                    }
                    case "Comentario": {
                        System.out.println("Metodo RecibirReporte()");
                        RecibirComentario();
                        break;
                    }
                    case "AlertaImagen": {
                        System.out.println("Metodo RecibirImagen()");
                        RecibirImagen();
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
                if(objectInputStream != null) {
                    objectInputStream.close();
                }
                if(objectOutputStream != null) {
                    objectOutputStream.close();
                }
                in.close();
                out.close();
                if(!socket.isClosed()){
                    socket.close();
                }
                
                System.out.println("Conexion cerrada");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    void DevolverLista() {
        try {
            //objectInputStream = new ObjectInputStream(in);
            //System.out.println("objectInputStream listo");
            PackDatos packDatos = new PackDatos();
            
            packDatos.listaDatosAlertas = MetodosDB.RecuperarListaAlertas();
            System.out.println("listaDatosAlertas recuperada");
            packDatos.listaConfirmaciones = MetodosDB.RecuperarListaConfirmaciones();
            System.out.println("listaConfirmaciones recuperada");
            packDatos.listaReportes = MetodosDB.RecuperarListaReportes();
            System.out.println("listaReportes recuperada");
            packDatos.listaComentarios = MetodosDB.RecuperarListaComentarios();
            System.out.println("listaComentarios recuperada");
            packDatos.listaImagenes = MetodosDB.RecuperarListaImagenes();
            System.out.println("listaImagenes recuperada");
            
            objectOutputStream = new ObjectOutputStream(out);
            System.out.println("objectOutputStream listo");
            objectOutputStream.writeObject(packDatos);
            objectOutputStream.flush();
            System.out.println("Listas Enviadas");
        } catch(IOException e) {
            System.err.println(e);
        }
    }
    
    void RecibirAlerta() {
        try {
            System.out.println("RecibirAlerta()");
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
    
    void RecibirConfirmacion() {
        try {
            //System.out.println("RecibirConfirmacion()");
            objectInputStream = new ObjectInputStream(in);
            System.out.println("objectInputStream listo");
            objectOutputStream = new ObjectOutputStream(out);
            System.out.println("objectOutputStream listo");
            
            Confirmacion confirmacion = (Confirmacion) objectInputStream.readObject();
            System.out.println("Recibido: " + confirmacion);
            
            MetodosDB.IngresarConfirmacion(confirmacion);
            
        } catch (IOException e){
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void RecibirReporte() {
        try {
            //System.out.println("RecibirReporte()");
            objectInputStream = new ObjectInputStream(in);
            System.out.println("objectInputStream listo");
            objectOutputStream = new ObjectOutputStream(out);
            System.out.println("objectOutputStream listo");
            
            Reporte reporte = (Reporte) objectInputStream.readObject();
            System.out.println("Recibido: " + reporte);
            
            MetodosDB.IngresarReporte(reporte);
            
        } catch (IOException e){
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void RecibirComentario() {
        try {
            //System.out.println("RecibirComentario()");
            objectInputStream = new ObjectInputStream(in);
            System.out.println("objectInputStream listo");
            objectOutputStream = new ObjectOutputStream(out);
            System.out.println("objectOutputStream listo");
            
            Comentario comentario = (Comentario) objectInputStream.readObject();
            System.out.println("Recibido: " + comentario);
            
            MetodosDB.IngresarComentario(comentario);
            
        } catch (IOException e){
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void RecibirImagen() {
        try {
            System.out.println("RecibirImagen()");
            objectInputStream = new ObjectInputStream(in);
            System.out.println("objectInputStream listo");
            objectOutputStream = new ObjectOutputStream(out);
            System.out.println("objectOutputStream listo");
            
            Imagen imagen = (Imagen) objectInputStream.readObject();
            System.out.println("Imagen Recibida");
            
            DatosAlerta datosAlerta = imagen.alerta;
            
            int id_alerta = MetodosDB.IngresarAlerta(datosAlerta); // IngresarAlerta() retorna el ID de la nueva alerta
            imagen.id_alerta = id_alerta;
            MetodosDB.IngresarImagen(imagen);
            
        } catch (IOException e){
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     
}
