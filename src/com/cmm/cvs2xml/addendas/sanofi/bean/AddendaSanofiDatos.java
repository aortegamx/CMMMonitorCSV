/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.sanofi.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 15/01/2015 01:28:31 PM
 */
public class AddendaSanofiDatos {

    private LineaDatosSanofiHeader lineaDatosSanofiHeader;
    private List<LineaDatosSanofiDetails> listaLineaDatosSanofiDetails;

    public LineaDatosSanofiHeader getLineaDatosSanofiHeader() {
        return lineaDatosSanofiHeader;
    }

    public void setLineaDatosSanofiHeader(LineaDatosSanofiHeader lineaDatosSanofiHeader) {
        this.lineaDatosSanofiHeader = lineaDatosSanofiHeader;
    }

    public List<LineaDatosSanofiDetails> getListaLineaDatosSanofiDetails() {
        if (listaLineaDatosSanofiDetails==null)
            listaLineaDatosSanofiDetails = new ArrayList<LineaDatosSanofiDetails>();
        return listaLineaDatosSanofiDetails;
    }
    
}
