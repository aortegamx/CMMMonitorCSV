/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.complementos.implocal.utils;

import com.cmm.cvs2xml.complementos.implocal.bean.DatosGeneralesImpLocal;
import com.cmm.cvs2xml.complementos.implocal.bean.LineaDatosImpLocal;
import com.cmm.cvs2xml.util.StringManage;
import com.cmm.cvs2xml.util.GenericValidator;
import java.math.BigDecimal;
import mx.bigdata.sat.common.implocal.schema.ImpuestosLocales;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 15/05/2014 05:52:09 PM
 */
public class CmmCvsImpLocalDatosGeneralesUtils {

    public final static String idRegistro = "30";
    public final static String infoRegistro = "INFORMACIÓN GENERAL PARA IMPUESTOS LOCALES";
    private final static int noElementosEsperados = 3;
    
    public static LineaDatosImpLocal fillData(String elementoCfdi){
        LineaDatosImpLocal lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosImpLocal();
            DatosGeneralesImpLocal datosGeneralesImpLocal = new DatosGeneralesImpLocal();
            ImpuestosLocales implocal = new ImpuestosLocales();
            
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
                            //Total de Retenciones - REQUERIDO
                            maxDecimales = 2; 
                            if ("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE TOTAL DE RETENCIONES NULO O VACIO");
                            }
                            if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO TOTAL DE RETENCIONES INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            implocal.setTotaldeRetenciones(new BigDecimal(str));
                            lineaDatos.setTotalRetenciones(new BigDecimal(str));
                            break;
                        case 2:
                            //Total de Traslados - REQUERIDO
                            maxDecimales = 2; 
                            if ("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE TOTAL DE TRASLADOS NULO O VACIO");
                            }
                            if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO TOTAL DE TRASLADOS INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            implocal.setTotaldeTraslados(new BigDecimal(str));
                            lineaDatos.setTotalTraslados(new BigDecimal(str));
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                datosGeneralesImpLocal.setImpuestosLocales(implocal);
                lineaDatos.setDatosGeneralesImpLocal(datosGeneralesImpLocal);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
