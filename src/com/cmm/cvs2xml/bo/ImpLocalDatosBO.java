/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bo;

import com.cmm.cvs2xml.complementos.implocal.bean.ImpLocalDatos;
import mx.bigdata.sat.common.implocal.schema.ImpuestosLocales;
import mx.bigdata.sat.common.nomina12.schema.ObjectFactory;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 15/05/2014 06:41:13 PM
 */
public class ImpLocalDatosBO {
    
    public static ImpuestosLocales compilarComplemento(ImpLocalDatos impLocalDatos){
        ImpuestosLocales implocal = null;
        
        if (impLocalDatos!=null){
            if (impLocalDatos.getLineaDatosImpLocal()!=null){
                
                implocal = impLocalDatos.getLineaDatosImpLocal().getDatosGeneralesImpLocal().getImpuestosLocales();
                
                if (implocal!=null){
                    implocal.setVersion("1.0");
                    ObjectFactory of = new ObjectFactory();
                    
                    //Percepciones
                    if (impLocalDatos.getListaRetencionesLocales().size()>0){
                        implocal.getRetencionesLocalesAndTrasladosLocales().addAll(impLocalDatos.getListaRetencionesLocales());
                    }
                    
                    //Deducciones
                    if (impLocalDatos.getListaTrasladosLocales().size()>0){
                        implocal.getRetencionesLocalesAndTrasladosLocales().addAll(impLocalDatos.getListaTrasladosLocales());
                    }
                    
                }
                
            }
        }
        
        return implocal;
    }

}
