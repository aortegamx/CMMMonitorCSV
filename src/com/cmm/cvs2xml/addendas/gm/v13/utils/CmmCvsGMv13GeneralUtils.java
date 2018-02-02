/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.gm.v13.utils;

import com.cmm.cvs2xml.addendas.gm.v13.bean.LineaDatosGMv13Generales;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigInteger;
import mx.bigdata.sat.addenda.gm.v13.schema.ADDENDAGM;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 28/12/2015 10:36:56 AM
 */
public class CmmCvsGMv13GeneralUtils {

    public final static String idRegistro = "00235";
    public final static String infoRegistro = "INFORMACIÓN GENERAL DE ELEMENTO 'HEADER' PARA ADDENDA GM V1.3";
    //private final static int noElementosEsperados = 7;
    
    public static LineaDatosGMv13Generales fillData(String elementoCfdi){
        LineaDatosGMv13Generales lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        /*
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }*/
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosGMv13Generales();
            ADDENDAGM.HEADER docHeader = new ADDENDAGM.HEADER();
            
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
                            //Numero Remision - Opciona
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.validaConExpresionRegular(str, "^([A-Za-z0-9]{1,9})$")){
                                    throw new IllegalArgumentException("DATO NUMERO REMISION, INCORRECTO, DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN [A-Za-z0-9]{1,9}.");
                                }
                                docHeader.setNUMEROREMISION(str);
                            }
                            break;
                        case 2:
                            //Fecha del Recibo - Opcional
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.validaConExpresionRegular(str, "((0[1-9])|([12][0-9])|(3[01]))/((0[1-9])|(1[012]))/((000[1-9])|(00[1-9][0-9])|(0[1-9][0-9]{2})|([1-9][0-9]{3}))")){
                                    throw new IllegalArgumentException("DATO FECHA DEL RECIBO, INCORRECTO, DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN ((0[1-9])|([12][0-9])|(3[01]))/((0[1-9])|(1[012]))/((000[1-9])|(00[1-9][0-9])|(0[1-9][0-9]{2})|([1-9][0-9]{3})).");
                                }
                                docHeader.setFECHARECIBO(str);
                            }
                            break;
                        case 3:
                            //Folio interno - Opcional
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.validaConExpresionRegular(str, "^([A-Za-z0-9]{1,8})$")){
                                    throw new IllegalArgumentException("DATO FOLIO INTERNO, INCORRECTO, DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN [A-Za-z0-9]{1,8}.");
                                }
                                docHeader.setFOLIOINTERNO(str);
                            }
                            break;
                        case 4:
                            //Moneda - Opcional
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.isNumeric(str, 1, 1)){
                                    throw new IllegalArgumentException("DATO MONEDA, INCORRECTO, DEBE SER UN VALOR ENTERO.");
                                }else{
                                    int integer = Integer.parseInt(str);
                                    if (integer<1 || integer>5){
                                        throw new IllegalArgumentException("DATO MONEDA, INCORRECTO, DEBE SER UNO DE LOS SIGUIENTES VALORES 1,2,3,4,5.");
                                    }
                                }
                                docHeader.setMONEDA(new BigInteger(str));
                            }
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setHeader(docHeader);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
