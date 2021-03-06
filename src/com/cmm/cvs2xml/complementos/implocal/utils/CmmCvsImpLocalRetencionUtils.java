/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.complementos.implocal.utils;

import com.cmm.cvs2xml.util.StringManage;
import com.cmm.cvs2xml.util.GenericValidator;
import java.math.BigDecimal;
import mx.bigdata.sat.common.implocal.schema.ImpuestosLocales;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 15/05/2014 05:52:09 PM
 */
public class CmmCvsImpLocalRetencionUtils {

    public final static String idRegistro = "31";
    public final static String infoRegistro = "INFORMACIÓN PARA IMPUESTOS LOCALES RETENIDOS";
    private final static int noElementosEsperados = 4;
    
    public static ImpuestosLocales.RetencionesLocales fillData(String elementoCfdi){
        ImpuestosLocales.RetencionesLocales retencion = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            retencion = new ImpuestosLocales.RetencionesLocales();
            
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
                        //Nombre - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE NOMBRE RETENCION LOCAL NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 100)){
                            throw new IllegalArgumentException("DATO NOMBRE RETENCION LOCAL INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 100 CARACTERES.");
                        }
                        retencion.setImpLocRetenido(str);
                        break;
                    case 2:
                        //Tasa - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TASA DE RETENCION LOCAL NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO TASA DE RETENCION LOCAL INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        retencion.setTasadeRetencion(new BigDecimal(str));
                        break;
                    case 3:
                        //Importe - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE IMPORTE DE RETENCION LOCAL NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO IMPORTE DE RETENCION LOCAL INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        retencion.setImporte(new BigDecimal(str));
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return retencion;
    }
    
}
