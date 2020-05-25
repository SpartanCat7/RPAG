/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.rpagv2;

import java.util.ArrayList;

/**
 *
 * @author SpartanCat7
 */
public class PackDatos implements java.io.Serializable {
    public double latitude, longitude;
    
    public ArrayList<DatosAlerta> listaDatosAlertas;
    public ArrayList<Confirmacion> listaConfirmaciones;
    public ArrayList<Reporte> listaReportes;
    public ArrayList<Comentario> listaComentarios;
    public ArrayList<Imagen> listaImagenes;
}
