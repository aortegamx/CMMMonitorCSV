/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.bean;

/**
 *
 * @author leonardo
 */
public class LineaDatosEmisor {
    
    private DatosEmisor datosEmisor;
    
    private String rfcEmisor;

    public DatosEmisor getDatosEmisor() {
        return datosEmisor;
    }

    public void setDatosEmisor(DatosEmisor datosEmisor) {
        this.datosEmisor = datosEmisor;
    }

    public String getRfcEmisor() {
        return rfcEmisor;
    }

    public void setRfcEmisor(String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }
    
}
