/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.complementos.nomina.bean;

import java.math.BigDecimal;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 12/05/2014 02:00:44 PM
 */
public class LineaDatosNomina {
    
    private DatosGeneralesNomina datosNomina;
    
    private BigDecimal totalPercepcionesGravadas;
    private BigDecimal totalPercepcionesExentas;
    private BigDecimal totalDeduccionesGravadas;
    private BigDecimal totalDeduccionesExentas;
    private BigDecimal totalOtrosPagos;

    public DatosGeneralesNomina getDatosNomina() {
        return datosNomina;
    }

    public void setDatosNomina(DatosGeneralesNomina datosNomina) {
        this.datosNomina = datosNomina;
    }

    public BigDecimal getTotalPercepcionesGravadas() {
        return totalPercepcionesGravadas;
    }

    public void setTotalPercepcionesGravadas(BigDecimal totalPercepcionesGravadas) {
        this.totalPercepcionesGravadas = totalPercepcionesGravadas;
    }

    public BigDecimal getTotalPercepcionesExentas() {
        return totalPercepcionesExentas;
    }

    public void setTotalPercepcionesExentas(BigDecimal totalPercepcionesExentas) {
        this.totalPercepcionesExentas = totalPercepcionesExentas;
    }

    public BigDecimal getTotalDeduccionesGravadas() {
        return totalDeduccionesGravadas;
    }

    public void setTotalDeduccionesGravadas(BigDecimal totalDeduccionesGravadas) {
        this.totalDeduccionesGravadas = totalDeduccionesGravadas;
    }

    public BigDecimal getTotalDeduccionesExentas() {
        return totalDeduccionesExentas;
    }

    public void setTotalDeduccionesExentas(BigDecimal totalDeduccionesExentas) {
        this.totalDeduccionesExentas = totalDeduccionesExentas;
    }

    public BigDecimal getTotalOtrosPagos() {
        return totalOtrosPagos;
    }

    public void setTotalOtrosPagos(BigDecimal totalOtrosPagos) {
        this.totalOtrosPagos = totalOtrosPagos;
    }
    
}
