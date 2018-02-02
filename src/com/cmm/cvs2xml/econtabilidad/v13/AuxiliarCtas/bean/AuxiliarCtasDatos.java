/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.bean;

import java.util.ArrayList;
import java.util.List;
import mx.bigdata.sat.econtabilidad.v13.schema.ctas.AuxiliarCtas;

/**
 *
 * @author Leonardo
 * 28/04/2017
 */
public class AuxiliarCtasDatos {
    
    private LineaDatosAuxiliarCtas lineaDatosAuxiliarCtas;
    private List<LineaDatosAuxiliarCtasCuenta> lineaDatosAuxiliarCtasCuentas;

    /**
     * @return the lineaDatosAuxiliarCtas
     */
    public LineaDatosAuxiliarCtas getLineaDatosAuxiliarCtas() {
        return lineaDatosAuxiliarCtas;
    }

    /**
     * @param lineaDatosAuxiliarCtas the LineaDatosAuxiliarCtas to set
     */
    public void setLineaDatosAuxiliarCtas(LineaDatosAuxiliarCtas lineaDatosAuxiliarCtas) {
        this.lineaDatosAuxiliarCtas = lineaDatosAuxiliarCtas;
    }

    public List<LineaDatosAuxiliarCtasCuenta> getLineaDatosAuxiliarCtasCuentas() {
        if(lineaDatosAuxiliarCtasCuentas==null)
            lineaDatosAuxiliarCtasCuentas=new ArrayList<LineaDatosAuxiliarCtasCuenta>();
        return lineaDatosAuxiliarCtasCuentas;
    }

    public void setLineaDatosAuxiliarCtasCuentas(List<LineaDatosAuxiliarCtasCuenta> lineaDatosAuxiliarCtasCuentas) {
        this.lineaDatosAuxiliarCtasCuentas = lineaDatosAuxiliarCtasCuentas;
    }

    
   
}
