/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.balanza.utils;

import com.cmm.cvs2xml.econtabilidad.balanza.bean.BalanzaDatos;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/09/2014 05:38:43 PM
 */
public class CmmCvsConvert {

    public static BalanzaDatos convert(BalanzaDatos balanzaDatos, String line) throws Exception{
        
        try{

            //Registro 110 - Balanza de Comprobación, datos generales
            if (line.startsWith(CmmCvsBalanzaDatosGeneralesUtils.idRegistro)){

                if (balanzaDatos==null)
                    balanzaDatos = new BalanzaDatos();
                
                balanzaDatos.setLineaDatosBalanza(CmmCvsBalanzaDatosGeneralesUtils.fillData(line));
            }

            if (balanzaDatos!=null){
                //Solo si el objeto actual ya paso anteriormente por un registro de inicio para Balanza
                if (balanzaDatos.getLineaDatosBalanza()!=null){
                    if (balanzaDatos.getLineaDatosBalanza().getDatosGeneralesBalanza()!=null){
                        if (balanzaDatos.getLineaDatosBalanza().getDatosGeneralesBalanza().getBalanza()!=null){

                                //Registro 111 - Detalles de Cuentas de Balanza
                                if (line.startsWith(CmmCvsBalanzaCuentaUtils.idRegistro)){
                                    balanzaDatos.getListaCuentas().add(CmmCvsBalanzaCuentaUtils.fillData(line));
                                }
                                
                        }
                    }
                }
            }
        }catch (Exception ex){
            throw new Exception(" Error en conversión Formato Balanza de Comprobación :" + ex.getMessage());
        }
        
        return balanzaDatos;
    }
    
}