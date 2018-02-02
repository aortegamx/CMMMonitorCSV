/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.complementos.dividendos.utils;

import com.cmm.cvs2xml.retenciones.complementos.dividendos.bean.DividendosDatos;
import com.cmm.cvs2xml.retenciones.bean.RetencionesDatos;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 03/03/2015 19:24:18 PM
 */
public class CmmCvsConvert {

    public static void convert(RetencionesDatos retencionesDatos, String line) throws Exception{
        
        if (retencionesDatos!=null){
            try{
                
                //Registro 00506 - Dividendos o Utilidades (0 a N ocurrencias)
                if (line.startsWith(CmmCvsDividendosDivOUtil.idRegistro)){

                    if (retencionesDatos.getDividendosDatos()==null)
                        retencionesDatos.setDividendosDatos(new DividendosDatos());
                    
                    retencionesDatos.getDividendosDatos().setLineaDatosDividendoOUtilidad(CmmCvsDividendosDivOUtil.fillData(line));
                }
                
                //Registro 00507 - Remanentes (0 a N ocurrencias)
                if (line.startsWith(CmmCvsDividendosRemanente.idRegistro)){

                    if (retencionesDatos.getDividendosDatos()==null)
                        retencionesDatos.setDividendosDatos(new DividendosDatos());
                    
                    retencionesDatos.getDividendosDatos().setLineaDatosRemanente(CmmCvsDividendosRemanente.fillData(line));
                }

            }catch (Exception ex){
                throw new Exception(" [Error Complemento Dividendos v1.0] :" + ex.getMessage());
            }
        }
        
    }
    
}
