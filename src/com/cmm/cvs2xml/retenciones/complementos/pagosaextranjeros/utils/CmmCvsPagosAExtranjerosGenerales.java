/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.complementos.pagosaextranjeros.utils;

import com.cmm.cvs2xml.retenciones.complementos.pagosaextranjeros.bean.LineaDatosPagosAExtranjerosGenerales;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.retencion.common.pagosaextranjeros.schema.Pagosaextranjeros;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 25/02/2015 06:41:58 PM
 */
public class CmmCvsPagosAExtranjerosGenerales {

    public final static String idRegistro = "00513";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS GENERALES COMPLEMENTO PAGOS A EXTRANJEROS";
    private final static int noElementosEsperados = 2;
    
    public static LineaDatosPagosAExtranjerosGenerales fillData(String elementoCfdi){
        LineaDatosPagosAExtranjerosGenerales lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            
            lineaDatos = new LineaDatosPagosAExtranjerosGenerales();
            Pagosaextranjeros pagosaextranjeros = new Pagosaextranjeros();
            
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
                        //BENEFICIARIO RETIENE
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO BENEFICIARIO RETIENE NULO O VACIO");
                        }
                        if (!gc.validaConExpresionRegular(str,"SI|NO")){
                            throw new IllegalArgumentException("DATO BENEFICIARIO RETIENE INCORRECTO, DEBE SER UNO DE SI,NO.");
                        }
                        pagosaextranjeros.setEsBenefEfectDelCobro(str);
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            lineaDatos.setPagosaextranjeros(pagosaextranjeros);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
