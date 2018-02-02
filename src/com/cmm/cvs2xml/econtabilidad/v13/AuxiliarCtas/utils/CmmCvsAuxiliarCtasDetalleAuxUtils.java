/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.utils;

import com.cmm.cvs2xml.util.DateManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.econtabilidad.v13.schema.ctas.AuxiliarCtas;

/**
 *
 * @author leonardo
 */
public class CmmCvsAuxiliarCtasDetalleAuxUtils {
    
    public final static String idRegistro = "132";
    public final static String infoRegistro = "INFORMACIÓN PARA DETALLE DE MOVIMIENTOS DENTRO DE LA CUENTA";
    private final static int noElementosEsperados = 6;
    
    public static AuxiliarCtas.Cuenta fillData(String elementoCfdi, AuxiliarCtas.Cuenta cuenta) throws Exception{
        if (cuenta==null){
            throw new Exception("El objeto AuxiliarCtas.Cuenta recibido para completar su " + infoRegistro + " tiene un valor nulo.");
        }
        
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length < noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban al menos " + noElementosEsperados + " elementos requeridos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            String formatoFecha = "yyyy-MM-dd";
            
            AuxiliarCtas.Cuenta.DetalleAux detalleAux = new AuxiliarCtas.Cuenta.DetalleAux();
            
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
                        //Fecha - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE FECHA NULO O VACIO");
                        }
                        if (!gc.isDate(str, formatoFecha)){
                            throw new IllegalArgumentException("DATO FECHA INCORRECTO, DEBE TENER EL FORMATO " + formatoFecha);
                        }
                        try{
                            detalleAux.setFecha(DateManage.dateToXMLGregorianCalendar2(DateManage.stringToDate(str, formatoFecha)));
                        }catch(Exception ex){
                            throw new IllegalArgumentException("DATO FECHA NO SE PUDO CONVERTIR, REVISÉ A DETALLE: " + ex.toString());
                        }
                        break;
                    case 2:
                        //NumUnIdenPol - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE NUMERO UNICO DE IDENTIFICACION NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 50)){
                            throw new IllegalArgumentException("DATO NUMERO UNICO DE IDENTIFICACION INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 50 CARACTERES.");
                        }
                        detalleAux.setNumUnIdenPol(str);
                        break;
                    case 3:
                        //Concepto - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CONCEPTO DE IDENTIFICACION NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 200)){
                            throw new IllegalArgumentException("DATO CONCEPTO INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 200 CARACTERES.");
                        }
                        detalleAux.setConcepto(str);
                        break;  
                    case 4:
                        //Debe - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DEBE NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 14, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO DEBE INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        detalleAux.setDebe(new BigDecimal(str));
                        break;
                    case 5:
                        //Haber - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE 'HABER' NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 14, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO 'HABER' INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        detalleAux.setHaber(new BigDecimal(str));
                        break;                    
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            cuenta.getDetalleAux().add(detalleAux);
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return cuenta;
    }
    
}