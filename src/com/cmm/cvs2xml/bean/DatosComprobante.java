/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bean;

import java.math.BigDecimal;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 23/04/2014 11:44:50 AM
 */
public class DatosComprobante {
    
    private Comprobante comprobante;
    
    private BigDecimal porcentajeDescuento;
    private String asunto;
    private String observaciones;

    public Comprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
}
