/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bo;

import com.cmm.cvs2xml.econtabilidad.v13.balanza.bean.BalanzaDatos;
import mx.bigdata.sat.econtabilidad.v13.schema.balanza.Balanza;
import mx.bigdata.sat.econtabilidad.v13.schema.balanza.ObjectFactory;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 8/09/2014 06:00:30 PM
 */
public class BalanzaDatosBO {

    private BalanzaDatos balanzaDatos;

    public BalanzaDatosBO() {
    }

    public BalanzaDatosBO(BalanzaDatos balanzaDatos) {
        this.balanzaDatos = balanzaDatos;
    }
    
    /**
     * Reune todo los datos del objeto BalanzaDatos
     * y los une en un solo objeto de Balanza, el cual 
     * no incluira datos adicionales que no correspondan al esquema
     * oficial publicado por el SAT.
     * @return objeto Balanza
     */
    public Balanza compilarFormato(){
        if (balanzaDatos==null)
            return null;
       
        ObjectFactory of = new ObjectFactory();
        Balanza balanza = balanzaDatos.getLineaDatosBalanza().getDatosGeneralesBalanza().getBalanza();
        balanza.setVersion("1.3");
        
        for (Balanza.Ctas cta : balanzaDatos.getListaCuentas()){
            balanza.getCtas().add(cta);
        }
        
        return balanza;
    }
    
    public BalanzaDatos getBalanzaDatos() {
        return balanzaDatos;
    }

    public void setBalanzaDatos(BalanzaDatos balanzaDatos) {
        this.balanzaDatos = balanzaDatos;
    }
    
    
}
