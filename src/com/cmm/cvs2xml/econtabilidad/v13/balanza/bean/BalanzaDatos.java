/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.v13.balanza.bean;

import com.cmm.cvs2xml.econtabilidad.v13.balanza.bean.*;
import java.util.ArrayList;
import java.util.List;
import mx.bigdata.sat.econtabilidad.v13.schema.balanza.Balanza;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/09/2014 10:16:46 AM
 */
public class BalanzaDatos {

    private LineaDatosBalanza lineaDatosBalanza;
    private List<Balanza.Ctas> listaCuentas;
    
    public LineaDatosBalanza getLineaDatosBalanza() {
        return lineaDatosBalanza;
    }

    public void setLineaDatosBalanza(LineaDatosBalanza lineaDatosBalanza) {
        this.lineaDatosBalanza = lineaDatosBalanza;
    }

    public List<Balanza.Ctas> getListaCuentas() {
        if (listaCuentas==null)
            listaCuentas = new ArrayList<Balanza.Ctas>();
        return listaCuentas;
    }
    
}
