/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.vwpmt.utils;

import com.cmm.cvs2xml.addendas.vwpmt.bean.LineaDatosVWCancelaciones;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.addenda.vwpmt.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 06/02/2015 10:36:56 AM
 */
public class CmmCvsVwPmtCancelacionesUtils {

    public final static String idRegistro = "00211";
    public final static String infoRegistro = "INFORMACIÓN DE CANCELACIONES - REFACTURACION PARA ADDENDA VW PMT V1.0";
    private final static int noElementosEsperados = 2;
    
    public static LineaDatosVWCancelaciones fillData(String elementoCfdi){
        LineaDatosVWCancelaciones lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosVWCancelaciones();
            Factura.Cancelaciones cancelaciones = new Factura.Cancelaciones();
            
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
                            //Numero de Factura Cancela - REQUERIDO
                            if (!gc.isValidString(str, 1, 16)){
                                throw new IllegalArgumentException("DATO NUMERO DE FACTURA CANCELA INCORRECTO, REQUERIDO, MÍNIMO 1 CARÁCTER, MÁXIMO 16.");
                            }
                            cancelaciones.setCancelaSustituye(str);
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setCancelaciones(cancelaciones);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
