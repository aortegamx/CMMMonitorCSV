/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.complementos.pagosaextranjeros.utils;

import com.cmm.cvs2xml.retenciones.complementos.pagosaextranjeros.bean.PagosAExtranjerosDatos;
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
                
                //Registro 00513 - Complemento Pagos a Extranjeros, datos generales
                if (line.startsWith(CmmCvsPagosAExtranjerosGenerales.idRegistro)){

                    retencionesDatos.setPagosAExtranjerosDatos(new PagosAExtranjerosDatos());
                    retencionesDatos.getPagosAExtranjerosDatos().setLineaDatosPagosAExtranjerosGenerales(CmmCvsPagosAExtranjerosGenerales.fillData(line));
                }

                //Solo si el objeto actual ya paso anteriormente por un registro
                // que lleno su atributo Addenda Chrysler PUA
                if (retencionesDatos.getPagosAExtranjerosDatos()!=null){
                    if (retencionesDatos.getPagosAExtranjerosDatos().getLineaDatosPagosAExtranjerosGenerales()!=null){

                        //Registro 00514
                        if (line.startsWith(CmmCvsPagosAExtranjerosNoBeneficiario.idRegistro)){
                            retencionesDatos.getPagosAExtranjerosDatos().setLineaDatosNoBeneficiario(CmmCvsPagosAExtranjerosNoBeneficiario.fillData(line));
                        }
                        
                        //Registro 00515
                        if (line.startsWith(CmmCvsPagosAExtranjerosBeneficiario.idRegistro)){
                            retencionesDatos.getPagosAExtranjerosDatos().setLineaDatosBeneficiario(CmmCvsPagosAExtranjerosBeneficiario.fillData(line));
                        }
                            
                    }
                }
            }catch (Exception ex){
                throw new Exception(" [Error Complemento PagosAExtranjeros v1.0] :" + ex.getMessage());
            }
        }
        
    }
    
}
