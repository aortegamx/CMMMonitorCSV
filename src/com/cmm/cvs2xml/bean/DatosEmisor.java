/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.bean;

import mx.bigdata.sat.cfdi.v33.schema.Comprobante;

/**
 *
 * @author leonardo
 */
public class DatosEmisor {
    
    private Comprobante.Emisor emisor;
    
    private TipoPersona tipoPersona;
    private String nombres;
    private String apellidos;
       
    public Comprobante.Emisor getEmisor() {
        return emisor;
    }

    public void setEmisor(Comprobante.Emisor emisor) {
        this.emisor = emisor;
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
    
    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    
    public enum TipoPersona {
        FISICA, 
        MORAL
    }
}
