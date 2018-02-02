/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.polizas.utils;

import com.cmm.cvs2xml.util.DateManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.econtabilidad.v1.polizas.schema.Polizas;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/09/2014 02:20:49 PM
 */
public class CmmCvsPolizasComprobantesUtil {

    public final static String idRegistro = "125";
    public final static String infoRegistro = "INFORMACIÓN PARA DETALLE DE COMPROBANTES DE LAS SUBCUENTAS QUE CORRESPONDEN A TRANSACCIÓN";
    private final static int noElementosEsperados = 4;
    
    public static Polizas.Poliza.Transaccion fillData(String elementoCfdi, Polizas.Poliza.Transaccion transaccion) throws Exception{
        
        if (transaccion==null){
            throw new Exception("El objeto Polizas.Poliza.Transaccion recibido para completar su " + infoRegistro + " tiene un valor nulo.");
        }
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            Polizas.Poliza.Transaccion.Comprobantes comprobantes = new Polizas.Poliza.Transaccion.Comprobantes();
            
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
                        //UUID - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE UUID DE COMPROBANTE NULO O VACIO");
                        }
                        if (!gc.isUUID(str)){
                            throw new IllegalArgumentException("DATO UUID DE COMPROBANTE INCORRECTO, NO CUMPLE CON EL PATRÓN OFICIAL DEL SAT.");
                        }
                        comprobantes.setUUIDCFDI(str);
                        break;
                    case 2:
                        //Monto - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE 'MONTO' NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 14, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO 'MONTO' INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        comprobantes.setMonto(new BigDecimal(str));
                        break;
                    case 3:
                        //RFC relacionado con el movimiento - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE RFC RELACIONADO CON LA TRANSACCIÓN NULO O VACIO");
                        }
                        if (!gc.isRFC(str)){
                            throw new IllegalArgumentException("DATO RFC RELACIONADO CON LA TRANSACCIÓN INCORRECTO, NO ES VÁLIDO RESPECTO AL PATRÓN OFICIAL.");
                        }
                        comprobantes.setRFC(str);
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            transaccion.getComprobantes().add(comprobantes);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return transaccion;
    }
    
}