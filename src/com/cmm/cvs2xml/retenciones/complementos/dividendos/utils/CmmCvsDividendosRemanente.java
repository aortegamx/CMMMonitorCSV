/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.complementos.dividendos.utils;

import com.cmm.cvs2xml.retenciones.complementos.dividendos.bean.LineaDatosRemanente;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.retencion.common.dividendos.schema.Dividendos;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 3/03/2015 11:55:30 AM
 */
public class CmmCvsDividendosRemanente {

    public final static String idRegistro = "00507";
    public final static String infoRegistro = "INFORMACIÓN DE REMANENTES PARA COMPLEMENTO DIVIDENDOS";
    private final static int noElementosEsperados = 2;
    
    public static LineaDatosRemanente fillData(String elementoCfdi){
        LineaDatosRemanente lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales = 6;
            
            lineaDatos = new LineaDatosRemanente();
            Dividendos.Remanente remanente = new Dividendos.Remanente();
            
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
                        //Procentaje Remanente - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO PORCENTAJE NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO PORCENTAJE INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        remanente.setProporcionRem(new BigDecimal(str));
                        break;
                        
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            lineaDatos.setRemanente(remanente);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
