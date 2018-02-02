/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.sanofi.utils;

import com.cmm.cvs2xml.addendas.sanofi.bean.LineaDatosSanofiDetails;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.addenda.sanofi.schema.Sanofi;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 16/01/2015 01:23:48 PM
 */
public class CmmCvsSanofiDetailsUtils {

    public final static String idRegistro = "00201";
    public final static String infoRegistro = "INFORMACIÓN DE ELEMENTO 'DETAILS' PARA ADDENDA SANOFI V1.0";
    //private final static int noElementosEsperados = 7;
    
    public static LineaDatosSanofiDetails fillData(String elementoCfdi){
        LineaDatosSanofiDetails lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        /*
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }*/
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosSanofiDetails();
            Sanofi.Documento.Details sanofidoDocDetail = new Sanofi.Documento.Details();
            
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
                            //Numero de línea - REQUERIDO
                            if (!gc.validaConExpresionRegular(str, "^([0-9]{4})$")){
                                throw new IllegalArgumentException("DATO NUMERO DE LINEA INCORRECTO, DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN [0-9]{4}.");
                            }
                            sanofidoDocDetail.setNUMLINEA(str);
                            break;
                        case 2:
                            //Numero de entrada - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.validaConExpresionRegular(str, "^([0-9]{10})$")){
                                    throw new IllegalArgumentException("DATO NUMERO DE ENTRADA INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN [0-9]{10}.");
                                }
                                sanofidoDocDetail.setNUMENTRADA(str);
                            }
                            break;
                        case 3:
                            //Cuenta Puente - REQUERIDO
                            if (!gc.validaConExpresionRegular(str, "^([0-9]{10})$")){
                                throw new IllegalArgumentException("DATO CUENTA PUENTE INCORRECTO, DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN [0-9]{10}.");
                            }
                            sanofidoDocDetail.setCUENTAPUENTE(str);
                            break;
                        case 4:
                            //Unidades - REQUERIDO
                            maxDecimales = 3; 
                            if ("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE UNIDADES NULO O VACIO");
                            }
                            if (!gc.isDecimal(str, 1, 15, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO UNIDADES INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            sanofidoDocDetail.setUNIDADES(new BigDecimal(str));
                            break;
                        case 5:
                            //Precio unitario - REQUERIDO
                            maxDecimales = 2; 
                            if ("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE PRECIO UNITARIO NULO O VACIO");
                            }
                            if (!gc.isDecimal(str, 1, 17, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO PRECIO UNITARIO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            sanofidoDocDetail.setPRECIOUNITARIO(new BigDecimal(str));
                            break;
                        case 6:
                            //Importe - REQUERIDO
                            maxDecimales = 2; 
                            if ("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE IMPORTE NULO O VACIO");
                            }
                            if (!gc.isDecimal(str, 1, 17, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO IMPORTE INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            sanofidoDocDetail.setIMPORTE(new BigDecimal(str));
                            break;
                        case 7:
                            //Unidad de medida - REQUERIDO
                            if ("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE UNIDAD DE MEDIDA NULO O VACIO");
                            }
                            if (!gc.isValidString(str, 1, 20)){
                                throw new IllegalArgumentException("DATO UNIDAD DE MEDIDA, REQUERIDO, MÁXIMO 20 CARACTERES.");
                            }
                            sanofidoDocDetail.setUNIDADMEDIDA(str);
                            break;
                        case 8:
                            //Tasa de IVA - REQUERIDO
                            maxDecimales = 2; 
                            if ("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE TASA DE IVA NULO O VACIO");
                            }
                            if (!gc.isDecimal(str, 1, 4, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO TASA DE IVA INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            sanofidoDocDetail.setTASAIVA(new BigDecimal(str));
                            break;
                        case 9:
                            //Importe de IVA - REQUERIDO
                            maxDecimales = 2; 
                            if ("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE IMPORTE IVA NULO O VACIO");
                            }
                            if (!gc.isDecimal(str, 1, 17, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO IMPORTE IVA INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            sanofidoDocDetail.setIMPORTEIVA(new BigDecimal(str));
                            break;
                        case 10:
                            //Disponible 1 - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.isValidString(str, 1, 30)){
                                    throw new IllegalArgumentException("DATO DISPONIBLE 1 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 30 CARACTERES.");
                                }
                                sanofidoDocDetail.setDISPONIBLE1(str);
                            }
                            break;
                        case 11:
                            //Disponible 2 - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.isValidString(str, 1, 30)){
                                    throw new IllegalArgumentException("DATO DISPONIBLE 2 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 30 CARACTERES.");
                                }
                                sanofidoDocDetail.setDISPONIBLE2(str);
                            }
                            break;
                        case 12:
                            //Disponible 3 - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.isValidString(str, 1, 30)){
                                    throw new IllegalArgumentException("DATO DISPONIBLE 3 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 30 CARACTERES.");
                                }
                                sanofidoDocDetail.setDISPONIBLE3(str);
                            }
                            break;
                        case 13:
                            //Disponible 4 - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.isValidString(str, 1, 30)){
                                    throw new IllegalArgumentException("DATO DISPONIBLE 4 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 30 CARACTERES.");
                                }
                                sanofidoDocDetail.setDISPONIBLE4(str);
                            }
                            break;
                        case 14:
                            //Disponible 5 - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.isValidString(str, 1, 30)){
                                    throw new IllegalArgumentException("DATO DISPONIBLE 5 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 30 CARACTERES.");
                                }
                                sanofidoDocDetail.setDISPONIBLE5(str);
                            }
                            break;
                        case 15:
                            //Disponible 6 - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.isValidString(str, 1, 30)){
                                    throw new IllegalArgumentException("DATO DISPONIBLE 6 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 30 CARACTERES.");
                                }
                                sanofidoDocDetail.setDISPONIBLE6(str);
                            }
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setDetail(sanofidoDocDetail);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
