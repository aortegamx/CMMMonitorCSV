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
 * @since 18/02/2015 01:44:28 PM
 */
public class LineaDatosImpuestoRetenido {

    private Retenciones.Totales.ImpRetenidos impuestoRetenido;

    public Retenciones.Totales.ImpRetenidos getImpuestoRetenido() {
        return impuestoRetenido;
    }

    public void setImpuestoRetenido(Retenciones.Totales.ImpRetenidos impuestoRetenido) {
        this.impuestoRetenido = impuestoRetenido;
    }
    
}
