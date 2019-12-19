/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server1.Metodos;

import Server1.Conexion;
import com.example.rpagv2.Comentario;
import com.example.rpagv2.Confirmacion;
import com.example.rpagv2.DatosAlerta;
import com.example.rpagv2.Imagen;
import com.example.rpagv2.Reporte;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author spart
 */
public class MetodosDB {
    
    public static String PIC_DIRECTORIO = "../RPAG_Pics";

    public static int IngresarAlerta(DatosAlerta alerta) {
        System.out.println("IngresarAlerta()");
        int res = 0;
        int newId = 0;
        Conexion CON = null;
        PreparedStatement PS;
        //ResultSet RS;
        String SQL_InsertarAlerta = "INSERT INTO alertas(id,id_usuario,latitud,longitud,clase_id,fecha) VALUES (?,?,?,?,?,?)";
        
        try {
            CON = new Conexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MetodosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            PS=CON.getConnection().prepareStatement(SQL_InsertarAlerta, Statement.RETURN_GENERATED_KEYS);
            //PS=CON.getConnection().prepareStatement(SQL_INSERTAR, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date sqlDate = new java.sql.Date(alerta.fecha.getTime()); //PS=(PreparedStatement) CON.getConnection().prepareStatement(SQL_INSERTAR);
            
            PS.setInt(1, alerta.id);
            PS.setInt(2, alerta.id_usuario);
            PS.setDouble(3, alerta.latitud);
            PS.setDouble(4, alerta.longitud);
            PS.setInt(5, alerta.clase_id); 
            PS.setDate(6, sqlDate);
            res = PS.executeUpdate();
            
            // Obtiene los id de las alertas generadas en la DB
            ResultSet rs = PS.getGeneratedKeys();
            
            if (rs.next()) {
                newId = rs.getInt(1);
            }
            
            if(res > 0){
                System.out.println("Comentario guardado con exito");
            }
            
        }catch(HeadlessException|SQLException e){
            System.err.println("error al guardar datos: "+e.getMessage());
        }finally{
            PS=null;
            CON.disconnect();
        }
        return newId;
    }

    
    
    public static ArrayList<DatosAlerta> RecuperarListaAlertas(){
        
        System.out.println("RecuperarListaAlertas()");
        ArrayList<DatosAlerta> listaAlertas = new ArrayList<>();
        
        Conexion CON = null;
        PreparedStatement PS;
        ResultSet RS;
        String SQL_MostrarAlertas = "SELECT * FROM alertas";
        
        try {
            CON = new Conexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MetodosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            PS = CON.getConnection().prepareStatement(SQL_MostrarAlertas);
            RS = PS.executeQuery();

            while(RS.next()){
                DatosAlerta alerta = new DatosAlerta();
                alerta.id = RS.getInt(1);
                alerta.id_usuario = RS.getInt(2);
                alerta.latitud = RS.getDouble(3);
                alerta.longitud = RS.getDouble(4);
                alerta.clase_id = RS.getInt(5);
                alerta.fecha = new Date(RS.getDate(6).getTime());
                
                listaAlertas.add(alerta);
            }
        }
        catch(SQLException e){
            System.err.println("Error: " + e.getMessage());
        }
        finally{
            PS = null;
            RS = null;
            CON.disconnect();
        }
        
        return listaAlertas;
    }

    public static int IngresarConfirmacion(Confirmacion confirmacion) {
        System.out.println("IngresarConfirmacion()");

        int res = 0;
        Conexion CON = null;
        PreparedStatement PS;
        //ResultSet RS;
        String SQL_InsertarAlerta = "INSERT INTO confirmacion(id_alerta,id_usuario,fecha) VALUES (?,?,?)";
        
        try {
            CON = new Conexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MetodosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            PS=CON.getConnection().prepareStatement(SQL_InsertarAlerta);
            //PS=CON.getConnection().prepareStatement(SQL_INSERTAR, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date sqlDate = new java.sql.Date(confirmacion.fecha.getTime()); //PS=(PreparedStatement) CON.getConnection().prepareStatement(SQL_INSERTAR);
            
            PS.setInt(1, confirmacion.id_alerta);
            PS.setInt(2, confirmacion.id_usuario);
            PS.setDate(3, sqlDate);
            res = PS.executeUpdate();
            
            /* Obtiene los id de las alertas generadas en la DB, innecesario por ahora
            ResultSet rs = PS.getGeneratedKeys();
            int newId;
            if (rs.next()) {
                newId = rs.getInt(1);
            }*/
            
            if(res > 0){
                System.out.println("Comentario guardado con exito");
            }
            
        }catch(HeadlessException|SQLException e){
            System.err.println("error al guardar datos: "+e.getMessage());
        }finally{
            PS=null;
            CON.disconnect();
        }
        return res;
    }
    
    public static ArrayList<Confirmacion> RecuperarListaConfirmaciones(){
        System.out.println("RecuperarListaConfirmaciones()");
        
        ArrayList<Confirmacion> listaConfirmaciones = new ArrayList<>();
        
        Conexion CON = null;
        PreparedStatement PS;
        ResultSet RS;
        String SQL_MostrarAlertas = "SELECT * FROM confirmacion";
        
        try {
            CON = new Conexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MetodosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            PS = CON.getConnection().prepareStatement(SQL_MostrarAlertas);
            RS = PS.executeQuery();

            while(RS.next()){
                Confirmacion confirmacion = new Confirmacion();
                confirmacion.id = RS.getInt(1);
                confirmacion.id_alerta = RS.getInt(2);
                confirmacion.id_usuario = RS.getInt(3);
                confirmacion.fecha = new Date(RS.getDate(4).getTime());
                
                listaConfirmaciones.add(confirmacion);
            }
        }
        catch(SQLException e){
            System.err.println("Error: " + e.getMessage());
        }
        finally{
            PS = null;
            RS = null;
            CON.disconnect();
        }
        
        return listaConfirmaciones;
    }

    public static int IngresarReporte(Reporte reporte) {
        System.out.println("IngresarReporte()");
        int res = 0;
        Conexion CON = null;
        PreparedStatement PS;
        //ResultSet RS;
        String SQL_InsertarAlerta = "INSERT INTO reportes(id_alerta,id_usuario,fecha) VALUES (?,?,?)";
        
        try {
            CON = new Conexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MetodosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            PS=CON.getConnection().prepareStatement(SQL_InsertarAlerta);
            //PS=CON.getConnection().prepareStatement(SQL_INSERTAR, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date sqlDate = new java.sql.Date(reporte.fecha.getTime()); //PS=(PreparedStatement) CON.getConnection().prepareStatement(SQL_INSERTAR);
            
            PS.setInt(1, reporte.id_alerta);
            PS.setInt(2, reporte.id_usuario);
            PS.setDate(3, sqlDate);
            res = PS.executeUpdate();
            
            /* Obtiene los id de las alertas generadas en la DB, innecesario por ahora
            ResultSet rs = PS.getGeneratedKeys();
            int newId;
            if (rs.next()) {
                newId = rs.getInt(1);
            }*/
            
            if(res > 0){
                System.out.println("Reporte guardado con exito");
            }
            
        }catch(HeadlessException|SQLException e){
            System.err.println("error al guardar datos: "+e.getMessage());
        }finally{
            PS=null;
            CON.disconnect();
        }
        return res;
    }
    
    public static ArrayList<Reporte> RecuperarListaReportes(){
        System.out.println("RecuperarListaReportes()");

        ArrayList<Reporte> listaReportes = new ArrayList<>();
        
        Conexion CON = null;
        PreparedStatement PS;
        ResultSet RS;
        String SQL_MostrarAlertas = "SELECT * FROM reportes";
        
        try {
            CON = new Conexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MetodosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            PS = CON.getConnection().prepareStatement(SQL_MostrarAlertas);
            RS = PS.executeQuery();

            while(RS.next()){
                Reporte reporte = new Reporte();
                reporte.id = RS.getInt(1);
                reporte.id_alerta = RS.getInt(2);
                reporte.id_usuario = RS.getInt(3);
                reporte.fecha = new Date(RS.getDate(4).getTime());
                
                listaReportes.add(reporte);
            }
        }
        catch(SQLException e){
            System.err.println("Error: " + e.getMessage());
        }
        finally{
            PS = null;
            RS = null;
            CON.disconnect();
        }
        
        return listaReportes;
    }
    
    public static int IngresarComentario(Comentario comentario) {
        System.out.println("IngresarConfirmacion()");

        int res = 0;
        Conexion CON = null;
        PreparedStatement PS;
        //ResultSet RS;
        String SQL_InsertarAlerta = "INSERT INTO comentarios(id_alerta,id_usuario,fecha,texto) VALUES (?,?,?,?)";
        
        try {
            CON = new Conexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MetodosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            PS=CON.getConnection().prepareStatement(SQL_InsertarAlerta);
            //PS=CON.getConnection().prepareStatement(SQL_INSERTAR, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date sqlDate = new java.sql.Date(comentario.fecha.getTime()); //PS=(PreparedStatement) CON.getConnection().prepareStatement(SQL_INSERTAR);
            
            PS.setInt(1, comentario.id_alerta);
            PS.setInt(2, comentario.id_usuario);
            PS.setDate(3, sqlDate);
            PS.setString(4, comentario.texto);
            
            res = PS.executeUpdate();
            
            /* Obtiene los id de las entradas generadas en la DB, innecesario por ahora
            ResultSet rs = PS.getGeneratedKeys();
            int newId;
            if (rs.next()) {
                newId = rs.getInt(1);
            }*/
            
            if(res > 0){
                System.out.println("Comentario guardado con exito");
                //JOptionPane.showMessageDialog(null,"comentario guardado con exito");
            }
            
        }catch(HeadlessException|SQLException e){
            System.err.println("error al guardar datos: " + e.getMessage());
        }finally{
            PS=null;
            CON.disconnect();
        }
        return res;
    }
    
    public static ArrayList<Comentario> RecuperarListaComentarios(){
        System.out.println("RecuperarListaReportes()");

        ArrayList<Comentario> listaComentarios = new ArrayList<>();
        
        Conexion CON = null;
        PreparedStatement PS;
        ResultSet RS;
        String SQL_MostrarAlertas = "SELECT * FROM comentarios";
        
        try {
            CON = new Conexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MetodosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            PS = CON.getConnection().prepareStatement(SQL_MostrarAlertas);
            RS = PS.executeQuery();

            while(RS.next()){
                Comentario comentario = new Comentario();
                comentario.id = RS.getInt(1);
                comentario.id_alerta = RS.getInt(2);
                comentario.id_usuario = RS.getInt(3);
                comentario.fecha = new Date(RS.getDate(4).getTime());
                comentario.texto = RS.getString(5);
                
                listaComentarios.add(comentario);
            }
        }
        catch(SQLException e){
            System.err.println("Error: " + e.getMessage());
        }
        finally{
            PS = null;
            RS = null;
            CON.disconnect();
        }
        
        return listaComentarios;
    }
    
    public static int IngresarImagen(Imagen imagen) throws IOException {
        System.out.println("IngresarImagen()");

        System.out.println("Recibido: " + imagen.nombre);
        System.out.println("Bitmap: " + imagen.bitmap.length);
            
        File directorio = new File(PIC_DIRECTORIO);
        if(directorio.mkdirs()){
            System.out.println("Directorio creado");
        }
        
        
        
        File file = new File(directorio, imagen.nombre);
        if(file.createNewFile()){
            FileUtils.writeByteArrayToFile(file, imagen.bitmap);
        }
        
        int res = 0;
        Conexion CON = null;
        PreparedStatement PS;
        //ResultSet RS;
        String SQL_InsertarAlerta = "INSERT INTO imagenes(nombre,id_alerta,id_usuario,fecha,bitmap) VALUES (?,?,?,?,?)";
        
        try {
            CON = new Conexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MetodosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            PS=CON.getConnection().prepareStatement(SQL_InsertarAlerta);
            //PS=CON.getConnection().prepareStatement(SQL_INSERTAR, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date sqlDate = new java.sql.Date(imagen.fecha.getTime()); //PS=(PreparedStatement) CON.getConnection().prepareStatement(SQL_INSERTAR);
            
            PS.setString(1, imagen.nombre);
            PS.setInt(2, imagen.id_alerta);
            PS.setInt(3, imagen.id_usuario);
            PS.setDate(4, sqlDate);
            PS.setInt(5, 0);
            
            System.out.println("Query: " + PS.toString());
            
            res = PS.executeUpdate();
            
            /* Obtiene los id de las entradas generadas en la DB, innecesario por ahora
            ResultSet rs = PS.getGeneratedKeys();
            int newId;
            if (rs.next()) {
                newId = rs.getInt(1);
            }*/
            
            if(res > 0){
                System.out.println("Comentario guardado con exito");
                //JOptionPane.showMessageDialog(null,"comentario guardado con exito");
            }
            
        }catch(HeadlessException|SQLException e){
            System.err.println("error al guardar datos: " + e.getMessage());
        }finally{
            PS=null;
            CON.disconnect();
        }
        return res;
    }
    
    public static ArrayList<Imagen> RecuperarListaImagenes(){
        System.out.println("RecuperarListaImagenes()");

        ArrayList<Imagen> listaImagenes = new ArrayList<>();
        
        Conexion CON = null;
        PreparedStatement PS;
        ResultSet RS;
        String SQL_MostrarAlertas = "SELECT * FROM imagenes";
        
        try {
            CON = new Conexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MetodosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            PS = CON.getConnection().prepareStatement(SQL_MostrarAlertas);
            RS = PS.executeQuery();

            while(RS.next()){
                Imagen imagen = new Imagen();
                imagen.id = RS.getInt(1);
                imagen.nombre = RS.getString(2);
                imagen.id_alerta = RS.getInt(3);
                imagen.id_usuario = RS.getInt(4);
                imagen.fecha = new Date(RS.getDate(5).getTime());
                //imagen.bitmap = null;
                imagen.bitmap = RecuperarImagen(imagen.nombre);
                
                listaImagenes.add(imagen);
            }
        }
        catch(SQLException e){
            System.err.println("Error: " + e.getMessage());
        }
        finally{
            PS = null;
            RS = null;
            CON.disconnect();
        }
        
        return listaImagenes;
    }
    
    public static byte[] RecuperarImagen(String nombre){
        byte[] imagen = null;
        try {
            File file = new File(PIC_DIRECTORIO,nombre);
            imagen = FileUtils.readFileToByteArray(file);
        } catch (IOException ex) {
            Logger.getLogger(MetodosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return imagen;
    }
}
