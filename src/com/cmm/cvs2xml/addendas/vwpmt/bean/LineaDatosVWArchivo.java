/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.vwpmt.bean;

import mx.bigdata.sat.addenda.vwpmt.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 6/02/2015 12:30:30 PM
 */
public class LineaDatosVWArchivo {

    private Factura.Archivo archivo;

    public Factura.Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Factura.Archivo archivo) {
        this.archivo = archivo;
    }
    
}
