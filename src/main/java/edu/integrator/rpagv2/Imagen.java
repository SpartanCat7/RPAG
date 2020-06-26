/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.integrator.rpagv2;

import java.util.Date;

/**
 *
 * @author SpartanCat7
 */
public class Imagen implements java.io.Serializable {
    public int id;
    public String nombre;
    public int id_alerta;
    public int id_usuario;
    public Date fecha;
    public byte[] bitmap;
    
    public DatosAlerta alerta; //solo usado al momento de enviar al servidor
}