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
 * @since 13/05/2014 11:08:09 AM
 */
public class CmmCvsNominaDeduccionUtils {

    public final static String IDREGISTRO = "22";
    public final static String INFO_REGISTRO = "CONCEPTOS DE DEDUCCIONES POR NÓMINA";
    private final static int NO_ELEMENTOS_ESPERADOS = 6;
    
    public static Nomina.Deducciones.Deduccion fillData(String elementoCfdi){
        Nomina.Deducciones.Deduccion deduccion = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != NO_ELEMENTOS_ESPERADOS){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + NO_ELEMENTOS_ESPERADOS + " elementos, para el identificador " + IDREGISTRO  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            deduccion = new Nomina.Deducciones.Deduccion();
            
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
                        if (!IDREGISTRO.equals(StringManage.getValidString(str))) {
                            throw new IllegalArgumentException("IDENTIFICADOR DE REGISTRO NO VALIDO, DEBE SER ESTRICTAMENTE \""+IDREGISTRO+"\" PARA " + INFO_REGISTRO);
                        }
                        break;
                    case 1:
                        //Tipo de Deduccion - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TIPO DE DEDUCCIÓN NULO O VACIO");
                        }
                        if (!gc.validaConExpresionRegular(str, "^([0-9]{3})$")){
                            throw new IllegalArgumentException("DATO TIPO DE DEDUCCIÓN INCORRECTO, DEBE SER UN ENTERO DE 3 DIGITOS CONFORME AL CATÁLOGO DEL SAT APLICABLE.");
                        }
                        deduccion.setTipoDeduccion(str);
                        break;
                    case 2:
                        //Codigo de Deduccion - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CODIGO DE DEDUCCIÓN NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 3, 15)){
                            throw new IllegalArgumentException("DATO CÓDIGO DE DEDUCCIÓN INCORRECTO, DEBE TENER MÍNIMO 3 CARACTERES Y MÁXIMO 15.");
                        }
                        deduccion.setClave(str);
                        break;
                    case 3:
                        //Descripción - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DESCRIPCIÓN DEDUCCIÓN NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 100)){
                            throw new IllegalArgumentException("DATO DESCRIPCIÓN DEDUCCIÓN INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 100 CARACTERES.");
                        }
                        deduccion.setConcepto(str);
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
                        deduccion.setImporte(new BigDecimal(str));
                        
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
                       // deduccion.setImporteExento(new BigDecimal(str));
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + IDREGISTRO);
        }
        
        return deduccion;
    }
    
}
