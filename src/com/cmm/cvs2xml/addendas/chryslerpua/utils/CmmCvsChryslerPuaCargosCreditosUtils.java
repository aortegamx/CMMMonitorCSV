/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.chryslerpua.utils;

import com.cmm.cvs2xml.addendas.chryslerpua.bean.LineaDatosChryslerCargosCreditos;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.addenda.chryslerpua.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/02/2015 09:50:11 AM
 */
public class CmmCvsChryslerPuaCargosCreditosUtils {

    public final static String idRegistro = "00229";
    public final static String infoRegistro = "INFORMACIÓN DE CARGOS CRETIDOS PARA ADDENDA CHRYSLER PUA V1.0";
    //private final static int noElementosEsperados = 4;
    
    public static LineaDatosChryslerCargosCreditos fillData(String elementoCfdi){
        LineaDatosChryslerCargosCreditos lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosChryslerCargosCreditos();
            Factura.CargosCreditos cargosCreditos = new Factura.CargosCreditos();
            
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
                            //Referencia Chrysler - REQUERIDO
                            if (!gc.isValidString(str, 1,15)){
                                throw new IllegalArgumentException("DATO REFERENCIA CHRYSLER INCORRECTO, REQUERIDO, MÍNIMO 1 CARACTERES, MÁXIMO 15.");
                            }
                            cargosCreditos.setReferenciaChrysler(str);
                            break;
                        case 2:
                            //Consecutivo - REQUERIDO
                            if (!gc.isValidString(str, 1,14)){
                                throw new IllegalArgumentException("DATO CONSECUTIVO INCORRECTO, REQUERIDO, MÍNIMO 1 CARACTERES, MÁXIMO 14.");
                            }
                            cargosCreditos.setConsecutivo(str);
                            break;
                        case 3:
                            //Monto de Linea - REQUERIDO
                            maxDecimales = 2;
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales) ){
                                throw new IllegalArgumentException("DATO MONTO DE LINEA INCORRECTO, REQUERIDO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            cargosCreditos.setMontoLinea(new BigDecimal(str));
                            break;
                        case 4:
                            //Factura - OPCIONAL
                            if (StringManage.getValidString(str).length()>0){
                                if (!gc.isValidString(str, 1,15)){
                                    throw new IllegalArgumentException("DATO FACTURA INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 15.");
                                }
                                cargosCreditos.setFactura(str);
                            }
                            break;
                        case 5:
                            //Archivo - OPCIONAL
                            if (StringManage.getValidString(str).length()>0){
                                cargosCreditos.setArchivo(StringManage.getValidString(str));
                            }
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setCargosCreditos(cargosCreditos);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
