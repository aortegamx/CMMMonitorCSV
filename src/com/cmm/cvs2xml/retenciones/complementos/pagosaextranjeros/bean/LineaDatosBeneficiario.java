/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.complementos.pagosaextranjeros.bean;

import mx.bigdata.sat.retencion.common.pagosaextranjeros.schema.Pagosaextranjeros;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 25/02/2015 06:38:08 PM
 */
public class LineaDatosBeneficiario {
    
    private Pagosaextranjeros.Beneficiario beneficiario;

    public Pagosaextranjeros.Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Pagosaextranjeros.Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }
            
}
