/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.chryslerpua.utils;

import com.cmm.cvs2xml.addendas.chryslerpua.bean.LineaDatosChryslerOtrosCargos;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.addenda.chryslerpua.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/02/2015 09:50:11 AM
 */
public class CmmCvsChryslerPuaOtrosCargosUtils {

    public final static String idRegistro = "00230";
    public final static String infoRegistro = "INFORMACIÓN DE OTROS CARGOS PARA ADDENDA CHRYSLER PUA V1.0";
    private final static int noElementosEsperados = 3;
    
    public static LineaDatosChryslerOtrosCargos fillData(String elementoCfdi){
        LineaDatosChryslerOtrosCargos lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosChryslerOtrosCargos();
            Factura.OtrosCargos otrosCargos = new Factura.OtrosCargos();
            
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
                            //Código - REQUERIDO
                            if (!gc.validaConExpresionRegular(str, "V0|V1|V4|V6")){
                                throw new IllegalArgumentException("DATO CODIGO INCORRECTO, REQUERIDO, DEBE CORRESPONDER A UNO DE V0, V1, V4, V6.");
                            }
                            otrosCargos.setCodigo(str);
                            break;
                        case 2:
                            //Monto - REQUERIDO
                            maxDecimales = 2;
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales) ){
                                throw new IllegalArgumentException("DATO MONTO INCORRECTO, REQUERIDO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            otrosCargos.setMonto(new BigDecimal(str));
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setOtrosCargos(otrosCargos);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
