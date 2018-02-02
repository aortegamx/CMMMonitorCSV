/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.fordfom.utils;

import com.cmm.cvs2xml.addendas.fordfom.bean.LineaDatosFordFom;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.addenda.fordfom.schema.Addenda;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 28/12/2015 10:36:56 AM
 */
public class CmmCvsFordFomGeneralUtils {

    public final static String idRegistro = "00240";
    public final static String infoRegistro = "INFORMACIÓN GENERAL PARA ADDENDA FORD FOM V1.0";
    //private final static int noElementosEsperados = 7;
    
    public static LineaDatosFordFom fillData(String elementoCfdi){
        LineaDatosFordFom lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        /*
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }*/
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosFordFom();
            Addenda.FOMASN fomasn = new Addenda.FOMASN();
            
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
                            //GSDB - Requerido
                            if (!gc.validaConExpresionRegular(str, "^([A-Z0-9]{4,5})$")){
                                throw new IllegalArgumentException("DATO GSDB, INCORRECTO, DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN [A-Z0-9]{4,5}.");
                            }
                            fomasn.setGSDB(str);
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setFomasn(fomasn);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
