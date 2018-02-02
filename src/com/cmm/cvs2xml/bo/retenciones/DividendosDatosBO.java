/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bo.retenciones;

import com.cmm.cvs2xml.retenciones.complementos.dividendos.bean.DividendosDatos;
import mx.bigdata.sat.retencion.common.dividendos.schema.Dividendos;
import mx.bigdata.sat.retencion.common.dividendos.schema.ObjectFactory;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 3/03/2015 11:28:59 AM
 */
public class DividendosDatosBO {

    public static Dividendos compilarComplemento(DividendosDatos dividendosDatos){
        Dividendos schemaBean = null;
        
        if (dividendosDatos!=null){
            if (dividendosDatos.getLineaDatosDividendoOUtilidad()!=null
                    || dividendosDatos.getLineaDatosRemanente()!=null ){
                
                ObjectFactory of = new ObjectFactory();
                schemaBean = of.createDividendos();
                
                if (schemaBean!=null){
                    schemaBean.setVersion("1.0");
                    
                    //Dividendos o Utilidades
                    if (dividendosDatos.getLineaDatosDividendoOUtilidad()!=null){
                        schemaBean.setDividOUtil(dividendosDatos.getLineaDatosDividendoOUtilidad().getDividOUtil());
                    }
                    
                    //Remantentes
                    if (dividendosDatos.getLineaDatosRemanente()!=null){
                        schemaBean.setRemanente(dividendosDatos.getLineaDatosRemanente().getRemanente());
                    }
                    
                }
                
            }
        }
        
        return schemaBean;
    }
    
}
