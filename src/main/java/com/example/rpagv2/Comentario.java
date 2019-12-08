/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.rpagv2;

import java.util.Date;

/**
 *
 * @author SpartanCat7
 */
public class Comentario implements java.io.Serializable {
    public int id;
    public int id_alerta;
    public int id_usuario;
    public Date fecha;
    
    public String texto;
}
