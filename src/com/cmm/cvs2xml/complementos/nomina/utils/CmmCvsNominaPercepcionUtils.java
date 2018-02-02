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
 * @since 13/05/2014 09:08:09 AM
 */
public class CmmCvsNominaPercepcionUtils {

    public final static String idRegistro = "21";
    public final static String infoRegistro = "CONCEPTOS DE PERCEPCIONES POR NÓMINA";
    private final static int noElementosEsperados = 6;
    
    public static Nomina.Percepciones.Percepcion fillData(String elementoCfdi){
        Nomina.Percepciones.Percepcion percepcion = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            percepcion = new Nomina.Percepciones.Percepcion();
            
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
                        //Tipo de Percepcion - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TIPO DE PERCEPCIÓN NULO O VACIO");
                        }
                        if (!gc.validaConExpresionRegular(str, "^([0-9]{3})$")){
                            throw new IllegalArgumentException("DATO TIPO DE PERCEPCIÓN INCORRECTO, DEBE SER UN ENTERO DE 3 DIGITOS CONFORME AL CATÁLOGO DEL SAT APLICABLE.");
                        }
                        percepcion.setTipoPercepcion(str);
                        break;
                    case 2:
                        //Codigo de Percepcion - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CODIGO DE PERCEPCIÓN NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 3, 15)){
                            throw new IllegalArgumentException("DATO CÓDIGO DE PERCEPCIÓN INCORRECTO, DEBE TENER MÍNIMO 3 CARACTERES Y MÁXIMO 15.");
                        }
                        percepcion.setClave(str);
                        break;
                    case 3:
                        //Descripción - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DESCRIPCIÓN PERCEPCIÓN NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 100)){
                            throw new IllegalArgumentException("DATO DESCRIPCIÓN PERCEPCIÓN INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 100 CARACTERES.");
                        }
                        percepcion.setConcepto(str);
                        break;
                    case 4:
                        //Importe Gravado - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE IMPORTE GRAVADO NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO IMPORTE GRAVADO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        percepcion.setImporteGravado(new BigDecimal(str));
                        break;
                    case 5:
                        //Importe Exento - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE IMPORTE EXENTO NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO IMPORTE EXENTO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        percepcion.setImporteExento(new BigDecimal(str));
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return percepcion;
    }
    
}
