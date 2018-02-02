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
 * @since 6/02/2015 12:37:49 PM
 */
public class LineaDatosVWParte {

    private Factura.Partes.Parte parte;

    public Factura.Partes.Parte getParte() {
        return parte;
    }

    public void setParte(Factura.Partes.Parte parte) {
        this.parte = parte;
    }
            
}
