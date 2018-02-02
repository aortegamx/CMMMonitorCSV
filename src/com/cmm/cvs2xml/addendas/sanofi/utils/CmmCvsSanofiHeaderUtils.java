/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.sanofi.utils;

import com.cmm.cvs2xml.addendas.sanofi.bean.LineaDatosSanofiHeader;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.addenda.sanofi.schema.Sanofi;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 16/01/2015 10:36:56 AM
 */
public class CmmCvsSanofiHeaderUtils {

    public final static String idRegistro = "00200";
    public final static String infoRegistro = "INFORMACIÓN DE ELEMENTO 'HEADER' PARA ADDENDA SANOFI V1.0";
    //private final static int noElementosEsperados = 7;
    
    public static LineaDatosSanofiHeader fillData(String elementoCfdi){
        LineaDatosSanofiHeader lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        /*
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }*/
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosSanofiHeader();
            Sanofi.Documento.Header sanofidoDocHeader = new Sanofi.Documento.Header();
            
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
                            //Tipo Documento - REQUERIDO
                            if (!gc.isValidString(str, 1, 2)){
                                throw new IllegalArgumentException("DATO TIPO DOCUMENTO INCORRECTO, REQUERIDO, MÁXIMO 2 CARACTERES.");
                            }
                            sanofidoDocHeader.setTIPODOCTO(str);
                            break;
                        case 2:
                            //Numero de Orden - REQUERIDO
                            //if (!gc.validaConExpresionRegular(str, "^([0-9]{10})$")){
                                //throw new IllegalArgumentException("DATO NUMERO DE ORDEN INCORRECTO, DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN [0-9]{10}.");
                            if (!gc.isValidString(str, 10, 10)){
                                throw new IllegalArgumentException("DATO NUMERO DE ORDEN INCORRECTO, DEBE SER UN VALOR DE 10 CARACTERES.");
                            }
                            sanofidoDocHeader.setNUMORDEN(str);
                            break;
                        case 3:
                            //Numero de Proveedor - REQUERIDO
                            if (!gc.validaConExpresionRegular(str, "^([0-9]{10})$")){
                                throw new IllegalArgumentException("DATO NUMERO DE PROVEEDOR INCORRECTO, DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN [0-9]{10}.");
                            }
                            sanofidoDocHeader.setNUMPROVEEDOR(str);
                            break;
                        case 4:
                            //FCTCONV - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                maxDecimales = 4; 
                                if (!gc.isDecimal(str, 1, 6, 0, maxDecimales)){
                                    throw new IllegalArgumentException("DATO FCTCONV INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                                }
                                sanofidoDocHeader.setFCTCONV(new BigDecimal(str));
                            }
                            break;
                        case 5:
                            //Moneda - REQUERIDO
                            if (!gc.isValidString(str, 1, 10)){
                                throw new IllegalArgumentException("DATO MONEDA INCORRECTO, REQUERIDO, MÁXIMO 10 CARACTERES.");
                            }
                            sanofidoDocHeader.setMONEDA(str);
                            break;
                        case 6:
                            //Importe Retención - REQUERIDO
                            maxDecimales = 2; 
                            if ("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE IMPORTE RETENCION NULO O VACIO");
                            }
                            if (!gc.isDecimal(str, 1, 17, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO IMPORTE RETENCION INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            sanofidoDocHeader.setIMPRETENCION(new BigDecimal(str));
                            break;
                        case 7:
                            //Importe Total - REQUERIDO
                            maxDecimales = 2; 
                            if ("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE IMPORTE TOTAL NULO O VACIO");
                            }
                            if (!gc.isDecimal(str, 1, 17, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO IMPORTE TOTAL INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            sanofidoDocHeader.setIMPTOTAL(new BigDecimal(str));
                            break;
                        case 8:
                            //Observaciones - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.isValidString(str, 1, 50)){
                                    throw new IllegalArgumentException("DATO OBSERVACIONES INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 50 CARACTERES.");
                                }
                                sanofidoDocHeader.setOBSERVACIONES(str);
                            }
                            break;
                        case 9:
                            //Cuenta de Correo - OPCIONAL
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.validaConExpresionRegular(str, "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*([,;]\\s*\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)*")){
                                    throw new IllegalArgumentException("DATO CUENTA DE CORREO INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE CUMPLIR CON EL PATRÓN \\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*([,;]\\s*\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)*");
                                }
                                sanofidoDocHeader.setCTACORREO(str);
                            }
                            break;
                        case 10:
                            //Disponible 1 - OPCIONAL
                            maxDecimales = 2;
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.isDecimal(str, 1, 17, 0, maxDecimales)){
                                    throw new IllegalArgumentException("DATO DISPONIBLE 1 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                                }
                                sanofidoDocHeader.setDISPONIBLE1(new BigDecimal(str));
                            }
                            break;
                        case 11:
                            //Disponible 2 - OPCIONAL
                            maxDecimales = 2;
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.isDecimal(str, 1, 17, 0, maxDecimales)){
                                    throw new IllegalArgumentException("DATO DISPONIBLE 2 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                                }
                                sanofidoDocHeader.setDISPONIBLE2(new BigDecimal(str));
                            }
                            break;
                        case 12:
                            //Disponible 3 - OPCIONAL
                            maxDecimales = 2;
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.isDecimal(str, 1, 17, 0, maxDecimales)){
                                    throw new IllegalArgumentException("DATO DISPONIBLE 3 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                                }
                                sanofidoDocHeader.setDISPONIBLE3(new BigDecimal(str));
                            }
                            break;
                        case 13:
                            //Disponible 4 - OPCIONAL
                            maxDecimales = 2;
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.isDecimal(str, 1, 17, 0, maxDecimales)){
                                    throw new IllegalArgumentException("DATO DISPONIBLE 4 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                                }
                                sanofidoDocHeader.setDISPONIBLE4(new BigDecimal(str));
                            }
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setSanofiDocHeader(sanofidoDocHeader);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
