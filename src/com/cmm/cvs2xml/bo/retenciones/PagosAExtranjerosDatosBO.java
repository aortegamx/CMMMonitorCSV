/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bo.retenciones;

import com.cmm.cvs2xml.retenciones.complementos.pagosaextranjeros.bean.PagosAExtranjerosDatos;
import mx.bigdata.sat.retencion.common.pagosaextranjeros.schema.Pagosaextranjeros;
import mx.bigdata.sat.retencion.common.pagosaextranjeros.schema.ObjectFactory;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 26/02/2015 11:42:07 AM
 */
public class PagosAExtranjerosDatosBO {

    public static Pagosaextranjeros compilarComplemento(PagosAExtranjerosDatos pagosAExtranjerosDatos){
        Pagosaextranjeros schemaBean = null;
        
        if (pagosAExtranjerosDatos!=null){
            if (pagosAExtranjerosDatos.getLineaDatosPagosAExtranjerosGenerales()!=null){
                
                schemaBean = pagosAExtranjerosDatos.getLineaDatosPagosAExtranjerosGenerales().getPagosaextranjeros();
                
                if (schemaBean!=null){
                    schemaBean.setVersion("1.0");
                    ObjectFactory of = new ObjectFactory();
                    
                    //NoBeneficiario
                    if (pagosAExtranjerosDatos.getLineaDatosNoBeneficiario()!=null){
                        schemaBean.setNoBeneficiario(pagosAExtranjerosDatos.getLineaDatosNoBeneficiario().getNoBeneficiario());
                    }
                    
                    //Beneficiario
                     if (pagosAExtranjerosDatos.getLineaDatosBeneficiario()!=null){
                        schemaBean.setBeneficiario(pagosAExtranjerosDatos.getLineaDatosBeneficiario().getBeneficiario());
                    }
                    
                }
                
            }
        }
        
        return schemaBean;
    }
    
}
