/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.bean;

import mx.bigdata.sat.retencion.v1.schema.Retenciones;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 18/02/2015 01:30:21 PM
 */
public class LineaDatosReceptor {

    private Retenciones.Receptor receptor;
    private String nombres;
    private String apellidos;
    private String email;

    public Retenciones.Receptor getReceptor() {
        return receptor;
    }

    public void setReceptor(Retenciones.Receptor receptor) {
        this.receptor = receptor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
            
}
