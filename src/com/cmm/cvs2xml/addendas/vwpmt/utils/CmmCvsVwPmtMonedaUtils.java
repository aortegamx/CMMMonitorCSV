/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.vwpmt.utils;

import com.cmm.cvs2xml.addendas.vwpmt.bean.LineaDatosVWMoneda;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.addenda.vwpmt.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/02/2015 09:50:11 AM
 */
public class CmmCvsVwPmtMonedaUtils {

    public final static String idRegistro = "00212";
    public final static String infoRegistro = "INFORMACIÓN DE MONEDA PARA ADDENDA VW PMT V1.0";
    //private final static int noElementosEsperados = 4;
    
    public static LineaDatosVWMoneda fillData(String elementoCfdi){
        LineaDatosVWMoneda lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosVWMoneda();
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
                            if (!gc.isValidString(str, 3,5)){
                                throw new IllegalArgumentException("DATO TIPO DE MONEDA INCORRECTO, REQUERIDO, MÍNIMO 3 CARACTERES, MÁXIMO 5.");
                            }
                            moneda.setTipoMoneda(str);
                            break;
                        case 2:
                            //Tipo de Cambio VWM - OPCIONAL
                            if (StringManage.getValidString(str).length()>0){
                                maxDecimales = 4;
                                if (!gc.isDecimal(str, 1, 14, 0, maxDecimales) ){
                                    throw new IllegalArgumentException("DATO TIPO DE CAMBIO INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                                }
                                moneda.setTipoCambio(new BigDecimal(str));
                            }
                            break;
                        case 3:
                            //Código de impuesto - REQUERIDO
                            if (!gc.isValidString(str, 1, 2)){
                                throw new IllegalArgumentException("DATO CÓDIGO DE IMPUESTO INCORRECTO, REQUERIDO, MÁXIMO 2 CARACTERES.");
                            }
                            moneda.setCodigoImpuesto(str);
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
