/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.vwpmt.utils;

import com.cmm.cvs2xml.addendas.vwpmt.bean.LineaDatosVWReferencias;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.addenda.vwpmt.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/02/2015 09:50:11 AM
 */
public class CmmCvsVwPmtReferenciasUtils {

    public final static String idRegistro = "00217";
    public final static String infoRegistro = "INFORMACIÓN DE REFERENCIAS PARA ADDENDA VW PMT V1.0";
    //private final static int noElementosEsperados = 4;
    
    public static LineaDatosVWReferencias fillData(String elementoCfdi){
        LineaDatosVWReferencias lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosVWReferencias();
            Factura.Referencias referencias = new Factura.Referencias();
            
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
                            //Referencia de Proveedor - Opcional
                            if (StringManage.getValidString(str).length()>0){
                                if (!gc.isValidString(str, 1,16)){
                                    throw new IllegalArgumentException("DATO REFERENCIA DE PROVEEDOR INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 16 CARACTERES.");
                                }
                                referencias.setReferenciaProveedor(str);
                            }
                            break;
                        case 2:
                            //REMISION - REQUERIDO
                            if (!gc.isValidString(str, 1,16)){
                                throw new IllegalArgumentException("DATO REMISION INCORRECTO, REQUERIDO, DEBE TENER MÁXIMO 16 CARACTERES.");
                            }
                            referencias.setRemision(str);
                            break;
                        case 3:
                            //Número de ASN - Opcional
                            if (StringManage.getValidString(str).length()>0){
                                if (!gc.isValidString(str, 1,20)){
                                    throw new IllegalArgumentException("DATO NUMERO DE ASN INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 20 CARACTERES.");
                                }
                                referencias.setNumeroASN(str);
                            }
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setReferencias(referencias);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
