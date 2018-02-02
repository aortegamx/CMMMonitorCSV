/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.complementos.implocal.bean;

import java.math.BigDecimal;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 15/05/2014 05:36:01 PM
 */
public class LineaDatosImpLocal {

    private DatosGeneralesImpLocal datosGeneralesImpLocal;
    
    private BigDecimal totalRetenciones;
    private BigDecimal totalTraslados;

    public DatosGeneralesImpLocal getDatosGeneralesImpLocal() {
        return datosGeneralesImpLocal;
    }

    public void setDatosGeneralesImpLocal(DatosGeneralesImpLocal datosGeneralesImpLocal) {
        this.datosGeneralesImpLocal = datosGeneralesImpLocal;
    }

    public BigDecimal getTotalRetenciones() {
        return totalRetenciones;
    }

    public void setTotalRetenciones(BigDecimal totalRetenciones) {
        this.totalRetenciones = totalRetenciones;
    }

    public BigDecimal getTotalTraslados() {
        return totalTraslados;
    }

    public void setTotalTraslados(BigDecimal totalTraslados) {
        this.totalTraslados = totalTraslados;
    }
    
}
