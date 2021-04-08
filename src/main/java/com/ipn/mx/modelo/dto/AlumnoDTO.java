/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Alumno;
import java.io.Serializable;

/**
 *
 * @author Sergio
 */
public class AlumnoDTO implements Serializable {
    private Alumno entidad;

    public AlumnoDTO() {
        entidad = new Alumno();
    }

    public Alumno getEntidad() {
        return entidad;
    }

    public void setEntidad(Alumno entidad) {
        this.entidad = entidad;
    }
    
    
    
}
