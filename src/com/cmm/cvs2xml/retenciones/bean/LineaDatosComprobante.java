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
 * @since 18/02/2015 01:54:39 PM
 */
public class LineaDatosComprobante {

    private Retenciones retenciones;
    private boolean notificar;

    public Retenciones getRetenciones() {
        return retenciones;
    }

    public void setRetenciones(Retenciones retenciones) {
        this.retenciones = retenciones;
    }

    public boolean isNotificar() {
        return notificar;
    }

    public void setNotificar(boolean notificar) {
        this.notificar = notificar;
    }
    
}
