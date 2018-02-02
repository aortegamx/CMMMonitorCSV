/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.chryslerppy.utils;

import com.cmm.cvs2xml.addendas.chryslerppy.bean.LineaDatosChryslerMoneda;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.addenda.chryslerppy.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/02/2015 09:50:11 AM
 */
public class CmmCvsChryslerPpyMonedaUtils {

    public final static String idRegistro = "00247";
    public final static String infoRegistro = "INFORMACIÓN DE MONEDA PARA ADDENDA CHRYSLER PPY V1.0";
    //private final static int noElementosEsperados = 4;
    
    public static LineaDatosChryslerMoneda fillData(String elementoCfdi){
        LineaDatosChryslerMoneda lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosChryslerMoneda();
            Factura.Moneda moneda = new Factura.Moneda();
            
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
                            //Tipo de Moneda - REQUERIDO
                            if (!gc.isValidString(str, 1,3)){
                                throw new IllegalArgumentException("DATO TIPO DE MONEDA INCORRECTO, REQUERIDO, MÍNIMO 1 CARACTERES, MÁXIMO 3.");
                            }
                            moneda.setTipoMoneda(str);
                            break;
                        case 2:
                            //Tipo de Cambio VWM - OPCIONAL
                            if (StringManage.getValidString(str).length()>0){
                                maxDecimales = 4;
                                if (!gc.isDecimal(str, 1, 16, 0, maxDecimales) ){
                                    throw new IllegalArgumentException("DATO TIPO DE CAMBIO INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                                }
                                moneda.setTipoCambio(new BigDecimal(str));
                            }
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setMoneda(moneda);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
