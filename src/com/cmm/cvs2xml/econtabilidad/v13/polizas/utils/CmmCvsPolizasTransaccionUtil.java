/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.v13.polizas.utils;

import com.cmm.cvs2xml.econtabilidad.v13.polizas.utils.*;
import com.cmm.cvs2xml.util.DateManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.econtabilidad.v13.schema.polizas.Polizas;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/09/2014 02:20:49 PM
 */
public class CmmCvsPolizasTransaccionUtil {

    public final static String idRegistro = "122";
    public final static String infoRegistro = "INFORMACIÓN PARA DETALLE DE TRANSACCIÓN DENTRO DE LA PÓLIZA";
    private final static int noElementosEsperados = 6;
    
    public static Polizas.Poliza fillData(String elementoCfdi, Polizas.Poliza poliza) throws Exception{
        if (poliza==null){
            throw new Exception("El objeto Polizas.Poliza recibido para completar su " + infoRegistro + " tiene un valor nulo.");
        }
        //Polizas.Poliza.Transaccion transaccion = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length < noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban al menos " + noElementosEsperados + " elementos requeridos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            Polizas.Poliza.Transaccion transaccion = new Polizas.Poliza.Transaccion();
            
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
                        //Clave o Número de Cuenta - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CLAVE O NÚMERO DE CUENTA NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 100)){
                            throw new IllegalArgumentException("DATO CLAVE O NÚMERO DE CUENTA INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 100 CARACTERES.");
                        }
                        transaccion.setNumCta(str);
                        break;
                    case 2:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE LA DESCIPCION DE LA TRANSACCION NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 200)){
                            throw new IllegalArgumentException("LA DESCIPCION DE LA TRANSACCION  INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 100 CARACTERES.");
                        }
                        transaccion.setDesCta(str);
                        break;
                    case 3:
                        //Concepto - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CONCEPTO NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 300)){
                            throw new IllegalArgumentException("DATO CONCEPTO INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 300 CARACTERES.");
                        }
                        transaccion.setConcepto(str);
                        break;
                    case 4:
                        //Debe - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE 'DEBE' NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 14, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO 'DEBE' INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        transaccion.setDebe(new BigDecimal(str));
                        break;
                    case 5:
                        //Haber - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE 'HABER' NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 14, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO 'HABER' INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        transaccion.setHaber(new BigDecimal(str));
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            poliza.getTransaccion().add(transaccion);
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return poliza;
    }
    
}