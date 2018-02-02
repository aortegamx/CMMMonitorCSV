/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.bo;

import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.bean.AuxiliarCtasDatos;
import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.bean.LineaDatosAuxiliarCtasCuenta;
import mx.bigdata.sat.econtabilidad.v13.schema.ctas.AuxiliarCtas;

/**
 *
 * @author user
 */
public class AuxiliarCtasDatosBO {
    
    private AuxiliarCtasDatos auxCtasDatos;
    
    public AuxiliarCtasDatosBO(){}
    
    public AuxiliarCtasDatosBO(AuxiliarCtasDatos auxCtasDatos){
        this.auxCtasDatos=auxCtasDatos;
    }
    
    public AuxiliarCtas compilarFormato(){
        if(auxCtasDatos==null)
            return null;
        AuxiliarCtas auxCtas=auxCtasDatos.getLineaDatosAuxiliarCtas().getDatosGeneralesAuxiliarCtas().getAuxiliarCtas();
        auxCtas.setVersion("1.3");
        
        for(LineaDatosAuxiliarCtasCuenta item:auxCtasDatos.getLineaDatosAuxiliarCtasCuentas()){
            auxCtas.getCuenta().add(item.getDatosAuxiliarCtasCuenta().getCuenta());
        }
        
        return auxCtas;
    }

    /**
     * @return the auxCtasDatos
     */
    public AuxiliarCtasDatos getAuxCtasDatos() {
        return auxCtasDatos;
    }

    /**
     * @param auxCtasDatos the auxCtasDatos to set
     */
    public void setAuxCtasDatos(AuxiliarCtasDatos auxCtasDatos) {
        this.auxCtasDatos = auxCtasDatos;
    }
}
