/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.complementos.arrendamientoenfideicomiso.utils;

import com.cmm.cvs2xml.retenciones.complementos.arrendamientoenfideicomiso.bean.ArrendamientoEnFideicomisoDatos;
import com.cmm.cvs2xml.retenciones.bean.RetencionesDatos;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 26/02/2015 19:24:18 PM
 */
public class CmmCvsConvert {

    public static void convert(RetencionesDatos retencionesDatos, String line) throws Exception{
        
        if (retencionesDatos!=null){
            try{
                
                //Registro 00505 - Complemento Arrendamiento En Fideicomiso, datos generales
                if (line.startsWith(CmmCvsArrendamientoEnFideicomisoGral.idRegistro)){

                    retencionesDatos.setArrendamientoEnFideicomisoDatos(new ArrendamientoEnFideicomisoDatos());
                    retencionesDatos.getArrendamientoEnFideicomisoDatos().setLineaDatosArrendamientoEnFideicomisoGeneral(CmmCvsArrendamientoEnFideicomisoGral.fillData(line));
                }

            }catch (Exception ex){
                throw new Exception(" [Error Complemento ArrendamientoEnFideicomiso v1.0] :" + ex.getMessage());
            }
        }
        
    }
    
}
