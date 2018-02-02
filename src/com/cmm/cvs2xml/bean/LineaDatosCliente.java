/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bean;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 22/04/2014 06:48:50 PM
 */
public class LineaDatosCliente {
    
    private DatosReceptor datosReceptor;
    
    private String rfcEmisor;

    public DatosReceptor getDatosReceptor() {
        return datosReceptor;
    }

    public void setDatosReceptor(DatosReceptor datosReceptor) {
        this.datosReceptor = datosReceptor;
    }

    public String getRfcEmisor() {
        return rfcEmisor;
    }

    public void setRfcEmisor(String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }
    
    

}
