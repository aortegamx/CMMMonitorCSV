/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.utils;

import com.cmm.cvs2xml.retenciones.bean.LineaDatosComprobante;
import com.cmm.cvs2xml.util.StringManage;
import com.cmm.cvs2xml.util.GenericValidator;
import mx.bigdata.sat.retencion.v1.schema.Retenciones;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 18/02/2015 05:08:09 PM
 */
public class CmmCvsRetencionesComprobanteUtils {

    public final static String idRegistro = "00500";
    public final static String infoRegistro = "DATOS GENERALES DE COMPROBANTE DE RETENCIONES";
    
    public static LineaDatosComprobante fillData(String elementoCfdi){
        LineaDatosComprobante lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosComprobante();
            Retenciones comprobante = new Retenciones();
            
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
                        //RFC Emisor - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE RFC EMISOR NULO O VACIO");
                        }
                        if (!gc.isRFC(str)){
                            throw new IllegalArgumentException("RFC EMISOR INVÁLIDO EN ESTRUCTURA");
                        }
                        comprobante.setEmisor(new Retenciones.Emisor());
                        comprobante.getEmisor().setRFCEmisor(str);
                        break;
                    case 2:
                        //CURP emisor - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isCURP(str)){
                                throw new IllegalArgumentException("CURP EMISOR INVÁLIDO EN ESTRUCTURA, DATO OPCIONAL.");
                            }
                            comprobante.getEmisor().setCURPE(str);
                        }
                        break;
                    case 3:
                        //Clave de Retencion - REQUERIDO
                        String valoresValidos = "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25";
                        String regExp = valoresValidos.replaceAll(",", "|");
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CLAVE DE RETENCION NULO O VACIO");
                        }
                        if (!gc.validaConExpresionRegular(str, regExp)){
                            throw new IllegalArgumentException("DATO CLAVE DE RETENCION INVÁLIDO, DEBE PERTENECER A UNO DE: " + valoresValidos);
                        }
                        comprobante.setCveRetenc(str);
                        break;
                    case 4:
                        //Descripcion Retención - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isValidString(str, 1, 100)){
                                throw new IllegalArgumentException("DATO DESCRIPCION DE RETENCION INVÁLIDO, ES OPCIONAL, SI SE EXPRESA DEBE TENER AL MENOS 1 CARACTER, MAXIMO 100");
                            }
                            comprobante.setDescRetenc(str);
                        }
                        break;
                    case 5:
                        //Notificar - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO NOTIFICAR NULO O VACIO");
                        }
                        lineaDatos.setNotificar(StringManage.getValidString(str).equalsIgnoreCase("SI"));
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            lineaDatos.setRetenciones(comprobante);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}