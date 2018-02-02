/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bo.retenciones;

import com.cmm.cvs2xml.retenciones.complementos.arrendamientoenfideicomiso.bean.ArrendamientoEnFideicomisoDatos;
import mx.bigdata.sat.retencion.common.arrendamientoenfideicomiso.schema.Arrendamientoenfideicomiso;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 27/02/2015 06:58:01 PM
 */
public class ArrendamientoEnFideicomisoDatosBO {

    public static Arrendamientoenfideicomiso compilarComplemento(ArrendamientoEnFideicomisoDatos arrendamientoEnFideicomisoDatos){
        Arrendamientoenfideicomiso schemaBean = null;
        
        if (arrendamientoEnFideicomisoDatos!=null){
            if (arrendamientoEnFideicomisoDatos.getLineaDatosArrendamientoEnFideicomisoGeneral()!=null){
                
                schemaBean = arrendamientoEnFideicomisoDatos.getLineaDatosArrendamientoEnFideicomisoGeneral().getArrendamientoenfideicomiso();
                
                if (schemaBean!=null){
                    schemaBean.setVersion("1.0");
                    
                }
                
            }
        }
        
        return schemaBean;
    }
    
}
