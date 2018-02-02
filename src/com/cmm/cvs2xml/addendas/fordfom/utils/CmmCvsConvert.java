/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.fordfom.utils;

import com.cmm.cvs2xml.addendas.fordfom.bean.*;
import com.cmm.cvs2xml.bean.FacturaDatos;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 09/02/2016 01:24:18 PM
 */
public class CmmCvsConvert {

    public static void convert(FacturaDatos facturaDatos, String line) throws Exception{
        
        if (facturaDatos!=null){
            try{
                
                //Registro 00240 - Addenda Ford Fom, datos generales, GSDB
                if (line.startsWith(CmmCvsFordFomGeneralUtils.idRegistro)){

                    facturaDatos.setAddendaFordFomDatos(new AddendaFordFomDatos());
                    facturaDatos.getAddendaFordFomDatos().setLineaDatosFordFom(CmmCvsFordFomGeneralUtils.fillData(line));
                }

                //Solo si el objeto actual ya paso anteriormente por un registro
                // que lleno su atributo Addenda Ford Fom
                if (facturaDatos.getAddendaFordFomDatos()!=null){
                    if (facturaDatos.getAddendaFordFomDatos().getLineaDatosFordFom()!=null){
                        if (facturaDatos.getAddendaFordFomDatos().getLineaDatosFordFom().getFomasn()!=null){
                        
                            facturaDatos.setTieneAddendas(true);
                            
                            //Registro 00241
                            if (line.startsWith(CmmCvsFordFomASNUtils.idRegistro)){
                                facturaDatos.getAddendaFordFomDatos().getLineaDatosFordFom().getFomasn().getASN().add(CmmCvsFordFomASNUtils.fillData(line));
                            }

                        }
                    }
                }
            }catch (Exception ex){
                throw new Exception(" [Error Addenda Ford Fom v1.0] :" + ex.getMessage());
            }
        }
        
    }
    
}
