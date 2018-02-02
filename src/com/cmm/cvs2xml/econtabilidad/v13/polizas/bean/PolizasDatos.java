/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.v13.polizas.bean;

import com.cmm.cvs2xml.econtabilidad.polizas.bean.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/09/2014 12:47:36 PM
 */
public class PolizasDatos {

    private LineaDatosPolizas lineaDatosPolizas;
    private List<LineaDatosPoliza> lineaDatosPolizaDetalle;

    public LineaDatosPolizas getLineaDatosPolizas() {
        return lineaDatosPolizas;
    }

    public void setLineaDatosPolizas(LineaDatosPolizas lineaDatosPolizas) {
        this.lineaDatosPolizas = lineaDatosPolizas;
    }

    public List<LineaDatosPoliza> getLineaDatosPolizaDetalle() {
        if (lineaDatosPolizaDetalle==null)
            lineaDatosPolizaDetalle = new ArrayList<LineaDatosPoliza>();
        return lineaDatosPolizaDetalle;
    }
    
}
