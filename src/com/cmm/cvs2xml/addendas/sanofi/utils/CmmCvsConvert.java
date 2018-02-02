/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.sanofi.utils;

import com.cmm.cvs2xml.addendas.sanofi.bean.AddendaSanofiDatos;
import com.cmm.cvs2xml.bean.FacturaDatos;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 16/01/2015 01:24:18 PM
 */
public class CmmCvsConvert {

    public static void convert(FacturaDatos facturaDatos, String line) throws Exception{
        
        if (facturaDatos!=null){
            try{
                
                //Registro 200 - Addenda Sanofi, datos generales, nodo Header
                if (line.startsWith(CmmCvsSanofiHeaderUtils.idRegistro)){

                    facturaDatos.setAddendaSanofiDatos(new AddendaSanofiDatos());
                    facturaDatos.getAddendaSanofiDatos().setLineaDatosSanofiHeader(CmmCvsSanofiHeaderUtils.fillData(line));
                }

                //Solo si el objeto actual ya paso anteriormente por un registro
                // que lleno su atributo Addenda Sanofi
                if (facturaDatos.getAddendaSanofiDatos()!=null){
                    if (facturaDatos.getAddendaSanofiDatos().getLineaDatosSanofiHeader()!=null){
                        if (facturaDatos.getAddendaSanofiDatos().getLineaDatosSanofiHeader().getSanofiDocHeader()!=null){
                        
                            facturaDatos.setTieneAddendas(true);
                            
                            //Registro 201
                            if (line.startsWith(CmmCvsSanofiDetailsUtils.idRegistro)){
                                facturaDatos.getAddendaSanofiDatos().getListaLineaDatosSanofiDetails().add(CmmCvsSanofiDetailsUtils.fillData(line));
                            }

                        }
                    }
                }
            }catch (Exception ex){
                throw new Exception(" [Error Addenda Sanofi v1.0] :" + ex.getMessage());
            }
        }
        
    }
    
}
