/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bo.addenda;

import com.cmm.cvs2xml.addendas.sanofi.bean.AddendaSanofiDatos;
import com.cmm.cvs2xml.addendas.sanofi.bean.LineaDatosSanofiDetails;
import java.math.BigDecimal;
import mx.bigdata.sat.addenda.sanofi.schema.Sanofi;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 21/01/2015 06:41:13 PM
 */
public class SanofiDatosBO {
    
    public static Sanofi compilarAddenda(AddendaSanofiDatos addendaSanofiDatos){
        Sanofi sanofi = null;
        
        if (addendaSanofiDatos!=null){
            if (addendaSanofiDatos.getLineaDatosSanofiHeader()!=null){
                
                sanofi = new Sanofi();
                
                sanofi.setDocumento(new Sanofi.Documento()); 
                sanofi.getDocumento().setHeader(addendaSanofiDatos.getLineaDatosSanofiHeader().getSanofiDocHeader());
                
                if (sanofi.getDocumento()!=null
                        && sanofi.getDocumento().getHeader()!=null){
                    sanofi.setVersion(new BigDecimal("1.0"));
                    
                    //Detalles
                    if (addendaSanofiDatos.getListaLineaDatosSanofiDetails().size()>0){
                        for (LineaDatosSanofiDetails lineaDet : addendaSanofiDatos.getListaLineaDatosSanofiDetails() ){
                            sanofi.getDocumento().getDetails().add(lineaDet.getDetail());
                        }
                    }
                    
                }
                
            }
        }
        
        return sanofi;
    }

}