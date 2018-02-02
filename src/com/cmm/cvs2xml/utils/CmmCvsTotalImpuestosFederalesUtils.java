/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.utils;

import com.cmm.cvs2xml.util.StringManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.bean.LineaDatosFactura;
import java.math.BigDecimal;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 08/04/2015 05:08:09 PM
 */
public class CmmCvsTotalImpuestosFederalesUtils {

    public final static String idRegistro = "07";
    public final static String infoRegistro = "INFORMACION GENERAL PARA IMPUESTOS FEDERALES";
    
    //private final static int noElementosEsperados = 3;
    
    public static LineaDatosFactura fillData(String elementoCfdi, LineaDatosFactura lineaDatos) throws Exception{
        if (lineaDatos==null){
            throw new Exception("El objeto LineaDatosFactura recibido para completar su " + infoRegistro + " tiene un valor nulo.");
        }
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
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
                        //Total de Retenciones - OPCIONAL
                        if (StringManage.getValidString(str).length()>0){
                            maxDecimales = 6; 
                            //if ("".equals(StringManage.getValidString(str))){
                            //    throw new IllegalArgumentException("NO SE PERMITE TOTAL DE RETENCIONES NULO O VACIO");
                            //}
                            if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO TOTAL DE RETENCIONES INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            lineaDatos.setTotalRetenciones(new BigDecimal(str));
                        }
                        break;
                    case 2:
                        //Total de Traslados - OPCIONAL
                        if (StringManage.getValidString(str).length()>0){
                            maxDecimales = 6; 
                            //if ("".equals(StringManage.getValidString(str))){
                            //    throw new IllegalArgumentException("NO SE PERMITE TOTAL DE TRASLADOS NULO O VACIO");
                            //}
                            if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO TOTAL DE TRASLADOS INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            lineaDatos.setTotalTraslados(new BigDecimal(str));
                        }
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
