/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.v13.polizas.utils;

import com.cmm.cvs2xml.econtabilidad.v13.polizas.utils.*;
import com.cmm.cvs2xml.econtabilidad.v13.polizas.bean.DatosPoliza;
import com.cmm.cvs2xml.econtabilidad.v13.polizas.bean.LineaDatosPoliza;
import com.cmm.cvs2xml.util.DateManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.econtabilidad.v13.schema.polizas.Polizas;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/09/2014 02:20:49 PM
 */
public class CmmCvsPolizasPolizaUtils {

    public final static String idRegistro = "121";
    public final static String infoRegistro = "INFORMACIÓN PARA DETALLE DE PÓLIZA";
    private final static int noElementosEsperados = 4;
    
    public static LineaDatosPoliza fillData(String elementoCfdi){
        LineaDatosPoliza lineaDatosPoliza = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            String formatoFecha = "yyyy-MM-dd";
            
            lineaDatosPoliza = new LineaDatosPoliza();
            DatosPoliza datosPoliza = new DatosPoliza();
            Polizas.Poliza poliza = new Polizas.Poliza();
            
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
                        //Tipo - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL NUMERO UNICO DE INDETIFICACION DE PÓLIZA NULO O VACIO");
                        }
                        if(!gc.isValidString(str, 1, 50)){
                            throw new IllegalArgumentException("EL NUMERO UNICO DE INDETIFICACION DE PÓLIZA DEBE CONTENER DE 1 A 50 CARACTERES");
                        }
                        poliza.setNumUnIdenPol(str);
                        break;
                    case 2:
                        //Fecha - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE FECHA NULO O VACIO");
                        }
                        if (!gc.isDate(str, formatoFecha)){
                            throw new IllegalArgumentException("DATO FECHA INCORRECTO, DEBE TENER EL FORMATO " + formatoFecha);
                        }
                        try{
                            poliza.setFecha(DateManage.dateToXMLGregorianCalendar2(DateManage.stringToDate(str, formatoFecha)));
                        }catch(Exception ex){
                            throw new IllegalArgumentException("DATO FECHA NO SE PUDO CONVERTIR, REVISÉ A DETALLE: " + ex.toString());
                        }
                        break;
                    case 3:
                        //Concepto - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CONCEPTO NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 300)){
                            throw new IllegalArgumentException("DATO CONCEPTO INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 300 CARACTERES.");
                        }
                        poliza.setConcepto(str);
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            datosPoliza.setPoliza(poliza);
            lineaDatosPoliza.setDatosPoliza(datosPoliza);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatosPoliza;
    }
    
}