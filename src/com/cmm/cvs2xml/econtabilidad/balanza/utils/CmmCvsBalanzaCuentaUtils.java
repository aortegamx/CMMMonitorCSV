/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.balanza.utils;

import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.econtabilidad.v1.balanza.schema.Balanza;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/09/2014 02:20:49 PM
 */
public class CmmCvsBalanzaCuentaUtils {

    public final static String idRegistro = "111";
    public final static String infoRegistro = "INFORMACIÓN PARA DETALLE DE CUENTA EN BALANZA DE COMPROBACIÓN";
    private final static int noElementosEsperados = 6;
    
    public static Balanza.Ctas fillData(String elementoCfdi){
        Balanza.Ctas ctas = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            ctas = new Balanza.Ctas();
            
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
                        //Clave o número de cuenta - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CLAVE O NÚMERO DE CUENTA NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 100)){
                            throw new IllegalArgumentException("DATO CLAVE O NÚMERO DE CUENTA INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 100 CARACTERES.");
                        }
                        ctas.setNumCta(str);
                        break;
                    case 2:
                        //Saldo Inicial - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE SALDO INICIAL NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 14, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO SALDO INICIAL INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        ctas.setSaldoIni(new BigDecimal(str));
                        break;
                    case 3:
                        //Debe - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE 'DEBE' NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 14, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO 'DEBE' INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        ctas.setDebe(new BigDecimal(str));
                        break;
                    case 4:
                        //Haber - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE 'HABER' NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 14, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO 'HABER' INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        ctas.setHaber(new BigDecimal(str));
                        break;
                    case 5:
                        //Saldo Final - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE SALDO FINAL NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 14, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO SALDO FINAL INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        ctas.setSaldoFin(new BigDecimal(str));
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return ctas;
    }
    
}