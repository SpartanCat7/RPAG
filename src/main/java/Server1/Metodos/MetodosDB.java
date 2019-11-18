/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server1.Metodos;

import Server1.Conexion;
import com.example.rpagv2.DatosAlerta;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author spart
 */
public class MetodosDB {

    public static int IngresarAlerta(DatosAlerta alerta) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int res = 0;
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
            PS=CON.getConnection().prepareStatement(SQL_InsertarAlerta);
            //PS=CON.getConnection().prepareStatement(SQL_INSERTAR, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date sqlDate = new java.sql.Date(alerta.fecha.getTime()); //PS=(PreparedStatement) CON.getConnection().prepareStatement(SQL_INSERTAR);
            
            PS.setInt(1, alerta.id);
            PS.setInt(2, alerta.id_usuario);
            PS.setDouble(3, alerta.latitud);
            PS.setDouble(4, alerta.longitud);
            PS.setInt(5, alerta.clase_id); 
            PS.setDate(6, sqlDate);
            res = PS.executeUpdate();
            
            /* Obtiene los id de las alertas generadas en la DB, innecesario por ahora
            ResultSet rs = PS.getGeneratedKeys();
            int newId;
            if (rs.next()) {
                newId = rs.getInt(1);
            }*/
            
            if(res > 0){
                JOptionPane.showMessageDialog(null,"registro guardado con exito");
            }
            
        }catch(HeadlessException|SQLException e){
            System.err.println("error al guardar datos: "+e.getMessage());
        }finally{
            PS=null;
            CON.disconnect();
        }
        return res;
    }

    
    
    public static ArrayList<DatosAlerta> RecuperarListaAlertas(){
        
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
                alerta.fecha = RS.getDate(6);
                
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
    
    
    
}
