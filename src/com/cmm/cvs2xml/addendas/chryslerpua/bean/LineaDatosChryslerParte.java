/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.chryslerpua.bean;

import mx.bigdata.sat.addenda.chryslerpua.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 6/02/2015 12:37:49 PM
 */
public class LineaDatosChryslerParte {

    private Factura.Partes.Part parte;
    private Factura.Partes.Part.OtrosCargos otrosCargos1;
    private Factura.Partes.Part.OtrosCargos otrosCargos2;
    private Factura.Partes.Part.OtrosCargos otrosCargos3;

    public Factura.Partes.Part getParte() {
        return parte;
    }

    public void setParte(Factura.Partes.Part parte) {
        this.parte = parte;
    }

    public Factura.Partes.Part.OtrosCargos getOtrosCargos1() {
        return otrosCargos1;
    }

    public void setOtrosCargos1(Factura.Partes.Part.OtrosCargos otrosCargos1) {
        this.otrosCargos1 = otrosCargos1;
    }

    public Factura.Partes.Part.OtrosCargos getOtrosCargos2() {
        return otrosCargos2;
    }

    public void setOtrosCargos2(Factura.Partes.Part.OtrosCargos otrosCargos2) {
        this.otrosCargos2 = otrosCargos2;
    }

    public Factura.Partes.Part.OtrosCargos getOtrosCargos3() {
        return otrosCargos3;
    }

    public void setOtrosCargos3(Factura.Partes.Part.OtrosCargos otrosCargos3) {
        this.otrosCargos3 = otrosCargos3;
    }

    
      
}
