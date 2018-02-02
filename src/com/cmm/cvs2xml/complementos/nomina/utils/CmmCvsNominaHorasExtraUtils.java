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
public class CmmCvsNominaHorasExtraUtils {

    public final static String idRegistro = "24";
    public final static String infoRegistro = "INFORMACIÓN PARA HORAS EXTRAS LABORADAS";
    private final static int noElementosEsperados = 5;
    
    public static Nomina.Percepciones.Percepcion.HorasExtra fillData(String elementoCfdi){
        Nomina.Percepciones.Percepcion.HorasExtra horasExtra = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            horasExtra = new Nomina.Percepciones.Percepcion.HorasExtra();
            
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
                        //Dias de Trabajo - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DIAS DE TRABAJO NULO O VACIO");
                        }
                        if (!gc.isNumeric(str, 1, 5)){
                            throw new IllegalArgumentException("DATO DIAS DE TRABAJO INCORRECTO, DEBE SER UN ENTERO CON UN MÁXIMO DE 5 DIGITOS.");
                        }
                        horasExtra.setDias(Integer.parseInt(str));
                        break;
                    case 2:
                        //Tipo de Horas - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TIPO DE HORAS NULO O VACIO");
                        }
                        if (!gc.validaConExpresionRegular(str, "^(01|02|03)$")) {
                            throw new IllegalArgumentException("EL DATO TIPO DE HORAS ES INCORRECTO, SOLO SE PERMITEN LOS VALORES FIJOS '01', '02', '03'.");
                        }
                        horasExtra.setTipoHoras(str);
                        break;
                    case 3:
                        //Horas - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE HORAS EXTRA NULO O VACIO");
                        }
                        if (!gc.isNumeric(str, 1, 5)){
                            throw new IllegalArgumentException("DATO HORAS EXTRA INCORRECTO, DEBE SER UN ENTERO CON UN MÁXIMO DE 5 DIGITOS.");
                        }
                        horasExtra.setHorasExtra(Integer.parseInt(str));
                        break;
                    case 4:
                        //Importe - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE IMPORTE PAGADO NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO IMPORTE PAGADO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        horasExtra.setImportePagado(new BigDecimal(str));
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return horasExtra;
    }
    
}
