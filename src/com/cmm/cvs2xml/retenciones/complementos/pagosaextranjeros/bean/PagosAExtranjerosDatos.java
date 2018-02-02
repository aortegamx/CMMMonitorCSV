/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.complementos.pagosaextranjeros.bean;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 25/02/2015 06:29:19 PM
 */
public class PagosAExtranjerosDatos {

    private LineaDatosPagosAExtranjerosGenerales lineaDatosPagosAExtranjerosGenerales;
    private LineaDatosBeneficiario lineaDatosBeneficiario;
    private LineaDatosNoBeneficiario lineaDatosNoBeneficiario;

    public LineaDatosPagosAExtranjerosGenerales getLineaDatosPagosAExtranjerosGenerales() {
        return lineaDatosPagosAExtranjerosGenerales;
    }

    public void setLineaDatosPagosAExtranjerosGenerales(LineaDatosPagosAExtranjerosGenerales lineaDatosPagosAExtranjerosGenerales) {
        this.lineaDatosPagosAExtranjerosGenerales = lineaDatosPagosAExtranjerosGenerales;
    }

    public LineaDatosBeneficiario getLineaDatosBeneficiario() {
        return lineaDatosBeneficiario;
    }

    public void setLineaDatosBeneficiario(LineaDatosBeneficiario lineaDatosBeneficiario) {
        this.lineaDatosBeneficiario = lineaDatosBeneficiario;
    }

    public LineaDatosNoBeneficiario getLineaDatosNoBeneficiario() {
        return lineaDatosNoBeneficiario;
    }

    public void setLineaDatosNoBeneficiario(LineaDatosNoBeneficiario lineaDatosNoBeneficiario) {
        this.lineaDatosNoBeneficiario = lineaDatosNoBeneficiario;
    }
    
}
