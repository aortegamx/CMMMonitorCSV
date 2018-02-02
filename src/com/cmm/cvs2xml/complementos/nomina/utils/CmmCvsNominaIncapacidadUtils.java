/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.complementos.nomina.utils;

import com.cmm.cvs2xml.util.StringManage;
import com.cmm.cvs2xml.util.GenericValidator;
import java.math.BigDecimal;
import mx.bigdata.sat.common.nomina12.schema.Nomina;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 13/05/2014 11:28:09 AM
 */
public class CmmCvsNominaIncapacidadUtils {

    public final static String idRegistro = "23";
    public final static String infoRegistro = "INFORMACIÓN PARA INCAPACIDADES DEL TRABAJADOR";
    private final static int noElementosEsperados = 4;
    
    public static Nomina.Incapacidades.Incapacidad fillData(String elementoCfdi){
        Nomina.Incapacidades.Incapacidad incapacidad = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            incapacidad = new Nomina.Incapacidades.Incapacidad();
            
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
                        //Dias de Incapacidad - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DIAS DE INCAPACIDAD NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO DIAS DE INCAPACIDAD INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        incapacidad.setDiasIncapacidad(Integer.valueOf(str));
                        break;
                    case 2:
                        //Tipo de Incapacidad - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TIPO DE INCAPACIDAD NULO O VACIO");
                        }
                        if (!gc.isNumeric(str, 1, 5)){
                            throw new IllegalArgumentException("DATO TIPO DE INCAPACIDAD INCORRECTO, DEBE SER UN ENTERO CON UN MÁXIMO DE 5 DIGITOS.");
                        }
                        incapacidad.setTipoIncapacidad("0"+str);
                        
                        break;
                    case 3:
                        //Descuento - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DESCUENTO POR INCAPACIDAD NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO DESCUENTO POR INCAPACIDAD INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        //incapacidad.setDescuento(new BigDecimal(str));
                        incapacidad.setImporteMonetario(new BigDecimal(str));
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return incapacidad;
    }
    
}
