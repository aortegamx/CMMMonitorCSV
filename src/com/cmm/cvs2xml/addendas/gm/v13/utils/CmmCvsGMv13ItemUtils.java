/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.gm.v13.utils;

import com.cmm.cvs2xml.addendas.gm.v13.bean.LineaDatosGMv13Item;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import java.math.BigInteger;
import mx.bigdata.sat.addenda.gm.v13.schema.ADDENDAGM;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 28/12/2015 01:23:48 PM
 */
public class CmmCvsGMv13ItemUtils {

    public final static String idRegistro = "00236";
    public final static String infoRegistro = "INFORMACIÓN DE ELEMENTO 'ITEM' PARA ADDENDA GM V1.3";
    //private final static int noElementosEsperados = 7;
    
    public static LineaDatosGMv13Item fillData(String elementoCfdi){
        LineaDatosGMv13Item lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        /*
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }*/
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosGMv13Item();
            ADDENDAGM.HEADER.ITEM gmv13Item = new ADDENDAGM.HEADER.ITEM();
            
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
                            //Folio de Orden de Compra - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.validaConExpresionRegular(str, "^([A-Za-z0-9]{1,8})$")){
                                    throw new IllegalArgumentException("DATO FOLIO ORDEN DE COMPRA INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN [A-Za-z0-9]{1,8}.");
                                }
                                gmv13Item.setORDENCOMPRA(str);
                            }
                            break;
                        case 2:
                            //Numero de Parte - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.validaConExpresionRegular(str, "^([A-Za-z0-9]{1,8})$")){
                                    throw new IllegalArgumentException("DATO NUMERO DE PARTE INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN [A-Za-z0-9]{1,8}.");
                                }
                                gmv13Item.setNUMEROPARTE(str);
                            }
                            break;
                        case 3:
                            //Tipo de Material - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.isNumeric(str, 1, 1)){
                                    throw new IllegalArgumentException("DATO TIPO DE MATERIAL INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER UN VALOR ENTERO.");
                                }else{
                                    int integer = Integer.parseInt(str);
                                    if (integer<1 || integer>3){
                                        throw new IllegalArgumentException("DATO TIPO DE MATERIAL INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER UNO DE LOS SIGUIENTES VALORES 1,2,3.");
                                    }
                                }
                                gmv13Item.setMATERIAL(new BigInteger(str));
                            }
                            break;
                        case 4:
                            //Cantidad - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                maxDecimales = 5; 
                                if (!gc.isDecimal(str, 1, 7, 0, maxDecimales)){
                                    throw new IllegalArgumentException("DATO CANTIDAD INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                                }
                                gmv13Item.setCANTIDAD(new BigDecimal(str));
                            }
                            break;
                        case 5:
                            //Precio unitario - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                maxDecimales = 5; 
                                if (!gc.isDecimal(str, 1, 9, 0, maxDecimales)){
                                    throw new IllegalArgumentException("DATO PRECIO UNITARIO INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                                }
                                gmv13Item.setPRECIOUNITARIO(new BigDecimal(str));
                            }
                            break;
                        case 6:
                            //Descripción - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                if ( !(StringManage.getValidString(str).length()>1)){
                                    throw new IllegalArgumentException("DATO DESCRIPCION INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE TENER MÍNIMO 1 CARACTERES.");
                                }
                                gmv13Item.setDESCRIPCION(str);
                            }
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setItem(gmv13Item);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
