/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;

/**
 *
 * @author darkdestiny
 */
public class Carrera implements Serializable{
    private int idCarrera;
    private String nombreCarrera;
    private int duracion;

    public Carrera() {
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idCarrera = ").append(idCarrera).append("\n");
        sb.append("nombreCarrera = ").append(nombreCarrera).append("\n");
        sb.append("duracion = ").append(duracion).append("\n");
        return sb.toString();
    }
    
    
}
