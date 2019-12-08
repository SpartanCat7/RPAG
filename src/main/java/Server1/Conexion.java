/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author spart
 */
public class Conexion {
    private Connection conn;
    private static final String driver ="com.mysql.cj.jdbc.Driver";
    private static final String user="root";
    private static final String password ="";
    private static final String url="jdbc:mysql://localhost:3306/rpag_db?autoReconnect=true&useSSL=false&maxReconnects=3&serverTimezone=UTC";
    //private static final String url="jdbc:mysql://localhost:3306/rpag_db";
    public int contcon=0;
    public Conexion() throws SQLException, ClassNotFoundException
    {
        //System.out.println ("Se hizo conexion "+contcon);
        conn=null;
        try{
            
            Class.forName(driver);
            System.out.println("Estableciendo Conexion a DB...");
            conn=DriverManager.getConnection(url, user, password);
            System.out.println("Conexion a DB establecida");
            if(conn!=null)
            {
                //System.out.println(" conec establecida");contcon++;
            }          
                
        }
        catch(/*ClassNotFoundException | */SQLException e)
        {
            System.out.println("error" +e);
            System.exit(0);
        }
        
    }
    public Connection getConnection()
    {
        return conn;
    }
    public void disconnect()
    {
        conn =null;
        if(conn==null)
        {
            System.out.println("conexion terminada");
        }
    }
}
