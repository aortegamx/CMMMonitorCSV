/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.fordfom.utils;

import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 28/12/2015 10:36:56 AM
 */
public class CmmCvsFordFomASNUtils {

    public final static String idRegistro = "00241";
    public final static String infoRegistro = "INFORMACIÓN DETALLES ASN PARA ADDENDA FORD FOM V1.0";
    //private final static int noElementosEsperados = 7;
    
    public static String fillData(String elementoCfdi){
        String asn = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        /*
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }*/
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            for (x=0; x < data.length; x++){
                String str = data[x];
                if (str!=null){
                    str = str.trim();
                    if ("".equals(str)) str =null;
                }
                
                /*
                 * SWITCH X
                 */
                switch (x) {
                        case 0:
                            //Identificador Registro - REQUERIDO
                            if (!idRegistro.equals(StringManage.getValidString(str))) {
                                throw new IllegalArgumentException("IDENTIFICADOR DE REGISTRO NO VALIDO, DEBE SER ESTRICTAMENTE \""+idRegistro+"\" PARA " + infoRegistro);
                            }
                            break;
                        case 1:
                            //ASN - Requerido
                            if (!gc.validaConExpresionRegular(str, "^([0-9]{1,11})$")){
                                throw new IllegalArgumentException("DATO ASN, INCORRECTO, DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN [0-9]{1,11}.");
                            }
                            asn = str;
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                //lineaDatos.setFomasn(fomasn);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return asn;
    }
    
}
