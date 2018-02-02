/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.utils;

import com.cmm.cvs2xml.retenciones.bean.LineaDatosImpuestoRetenido;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.retencion.v1.schema.Retenciones;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 19/02/2015 10:36:56 AM
 */
public class CmmCvsRetencionesImpuestoRetenidoUtils {

    public final static String idRegistro = "00503";
    public final static String infoRegistro = "INFORMACIÓN DE DETALLE DE IMPUESTOS RETENIDOS";
    //private final static int noElementosEsperados = 8;
    
    public static LineaDatosImpuestoRetenido fillData(String elementoCfdi){
        LineaDatosImpuestoRetenido lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales = 6;
            
            lineaDatos = new LineaDatosImpuestoRetenido();
            Retenciones.Totales.ImpRetenidos impRetenidos = new Retenciones.Totales.ImpRetenidos();
            
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
                            //Base Retencion - Opcional
                            if (!"".equals(StringManage.getValidString(str))) {
                                if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                    throw new IllegalArgumentException("DATO BASE RETENCION INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                                }
                                impRetenidos.setBaseRet(new BigDecimal(str));
                            }
                            break;
                        case 2:
                            //Tipo de Impuesto - Opcional
                            String valoresValidos = "01,02,03";
                            String regExp = valoresValidos.replaceAll(",", "|");
                            if (!"".equals(StringManage.getValidString(str))){
                                if (!gc.validaConExpresionRegular(str, regExp)){
                                    throw new IllegalArgumentException("DATO TIPO DE IMPUESTO INVÁLIDO, DEBE PERTENECER A UNO DE: " + valoresValidos);
                                }
                                impRetenidos.setImpuesto(str);
                            }
                            break;
                        case 3:
                            //Monto retenido - REQUERIDO
                            if ("".equals(StringManage.getValidString(str))) {
                                throw new IllegalArgumentException("NO SE PERMITE MONTO RETENIDO NULO O VACIO");
                            }
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO MONTO RETENIDO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            impRetenidos.setMontoRet(new BigDecimal(str));
                            break;
                        case 4:
                            //tipo de pago - REQUERIDO
                            if ("".equals(StringManage.getValidString(str))) {
                                throw new IllegalArgumentException("NO SE PERMITE TIPO DE PAGO NULO O VACIO");
                            }
                            String valoresValidos2 = "Pago definitivo,Pago provisional";
                            String regExp2 = valoresValidos2.replaceAll(",", "|");
                            if (!gc.validaConExpresionRegular(str, regExp2)){
                                throw new IllegalArgumentException("DATO TIPO DE PAGO INVÁLIDO, DEBE PERTENECER A UNO DE: " + valoresValidos2);
                            }
                            impRetenidos.setTipoPagoRet(str);
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setImpuestoRetenido(impRetenidos);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
