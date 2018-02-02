/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.vwpmt.utils;

import com.cmm.cvs2xml.addendas.vwpmt.bean.LineaDatosVWGenerales;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.addenda.vwpmt.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 06/02/2015 10:36:56 AM
 */
public class CmmCvsVwPmtGeneralUtils {

    public final static String idRegistro = "00210";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS GENERALES PARA ADDENDA VW PMT V1.0";
    private final static int noElementosEsperados = 4;
    
    public static LineaDatosVWGenerales fillData(String elementoCfdi){
        LineaDatosVWGenerales lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosVWGenerales();
            Factura factura = new Factura();
            
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
                            //Tipo Documento Fiscal - REQUERIDO
                            if (!gc.validaConExpresionRegular(str, "FA|CA|CR")){
                                throw new IllegalArgumentException("DATO TIPO DOCUMENTO FISCAL INCORRECTO, REQUERIDO, DEBE CORRESPONDER A UNO DE FA, CA, CR.");
                            }
                            factura.setTipoDocumentoFiscal(str);
                            break;
                        case 2:
                            //Tipo Documento VWM - REQUERIDO
                            if (!gc.validaConExpresionRegular(str, "PMT")){
                                throw new IllegalArgumentException("DATO TIPO DOCUMENTO VWM INCORRECTO, REQUERIDO, DEBE CORRESPONDER A UNO DE PMT.");
                            }
                            factura.setTipoDocumentoVWM(str);
                            break;
                        case 3:
                            //División - REQUERIDO
                            if (!gc.validaConExpresionRegular(str, "VW|CT|INFODE|VWSP")){
                                throw new IllegalArgumentException("DATO DIVISION INCORRECTO, REQUERIDO, DEBE CORRESPONDER A UNO DE VW, CT, INFODE, VWSP.");
                            }
                            factura.setDivision(str);
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setFacturaVW(factura);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
