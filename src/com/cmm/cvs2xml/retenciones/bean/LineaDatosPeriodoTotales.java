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
 * @since 18/02/2015 01:42:13 PM
 */
public class LineaDatosPeriodoTotales {

    private Retenciones.Periodo periodo;
    private Retenciones.Totales totales;

    public Retenciones.Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Retenciones.Periodo periodo) {
        this.periodo = periodo;
    }

    public Retenciones.Totales getTotales() {
        return totales;
    }

    public void setTotales(Retenciones.Totales totales) {
        this.totales = totales;
    }
    
    
}
