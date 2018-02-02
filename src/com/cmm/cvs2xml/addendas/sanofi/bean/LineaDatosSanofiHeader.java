/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.sanofi.bean;

import mx.bigdata.sat.addenda.sanofi.schema.Sanofi;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 15/01/2015 01:26:45 PM
 */
public class LineaDatosSanofiHeader {

    private Sanofi.Documento.Header sanofiDocHeader;

    public Sanofi.Documento.Header getSanofiDocHeader() {
        return sanofiDocHeader;
    }

    public void setSanofiDocHeader(Sanofi.Documento.Header sanofiDocHeader) {
        this.sanofiDocHeader = sanofiDocHeader;
    }
    
}
