/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bo;

import com.cmm.cvs2xml.econtabilidad.v13.polizas.bean.LineaDatosPoliza;
import com.cmm.cvs2xml.econtabilidad.v13.polizas.bean.PolizasDatos;
import mx.bigdata.sat.econtabilidad.v13.schema.polizas.Polizas;
import mx.bigdata.sat.econtabilidad.v13.schema.polizas.ObjectFactory;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 8/09/2014 06:00:30 PM
 */
public class PolizasDatosBO {

    private PolizasDatos polizasDatos;

    public PolizasDatosBO() {
    }

    public PolizasDatosBO(PolizasDatos polizasDatos) {
        this.polizasDatos = polizasDatos;
    }
    
    /**
     * Reune todo los datos del objeto PolizasDatos
     * y los une en un solo objeto de Polizas, el cual 
     * no incluira datos adicionales que no correspondan al esquema
     * oficial publicado por el SAT.
     * @return objeto Polizas
     */
    public Polizas compilarFormato(){
        if (polizasDatos==null)
            return null;
       
        ObjectFactory of = new ObjectFactory();
        Polizas polizas = polizasDatos.getLineaDatosPolizas().getDatosGeneralesPolizas().getPolizas();
        polizas.setVersion("1.3");
        
        for (LineaDatosPoliza item : polizasDatos.getLineaDatosPolizaDetalle()){
            polizas.getPoliza().add(item.getDatosPoliza().getPoliza());
        }
        
        return polizas;
    }
    
    public PolizasDatos getPolizasDatos() {
        return polizasDatos;
    }

    public void setPolizasDatos(PolizasDatos polizasDatos) {
        this.polizasDatos = polizasDatos;
    }
    
    
}
