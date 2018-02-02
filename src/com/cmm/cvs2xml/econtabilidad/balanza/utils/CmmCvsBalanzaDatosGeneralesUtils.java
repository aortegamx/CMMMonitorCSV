/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.balanza.utils;

import com.cmm.cvs2xml.econtabilidad.balanza.bean.*;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.econtabilidad.v1.balanza.schema.Balanza;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/09/2014 02:19:43 PM
 */
public class CmmCvsBalanzaDatosGeneralesUtils {

    public final static String idRegistro = "110";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS GENERALES PARA FORMATO BALANZA DE COMPROBACIÓN";
    private final static int noElementosEsperados = 5;
    
    public static LineaDatosBalanza fillData(String elementoCfdi){
        LineaDatosBalanza lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            
            lineaDatos = new LineaDatosBalanza();
            DatosGeneralesBalanza datosGeneralesBalanza = new DatosGeneralesBalanza();
            Balanza balanza = new Balanza();
            
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
                        //RFC - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE RFC DEL CONTRIBUYENTE NULO O VACIO");
                        }
                        if (!gc.isRFC(str)){
                            throw new IllegalArgumentException("DATO RFC DEL CONTRIBUYENTE INCORRECTO, NO ES VÁLIDO RESPECTO AL PATRÓN OFICIAL.");
                        }
                        balanza.setRFC(str);
                        break;
                    case 2:
                        //Total de Cuentas - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TOTAL DE CUENTAS NULO O VACIO");
                        }
                        if (!gc.isNumeric(str, 1, 5)){
                            throw new IllegalArgumentException("DATO TOTAL DE CUENTAS INCORRECTO, DEBE SER UN ENTERO.");
                        }
                        int totalCuentas = Integer.parseInt(str);
                        if (totalCuentas<2){
                            throw new IllegalArgumentException("DATO TOTAL DE CUENTAS INCORRECTO, DEBE SER UN VALOR IGUAL O MAYOR A 2.");
                        }
                        balanza.setTotalCtas(totalCuentas);
                        break;
                    case 3:
                        //Mes - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE MES NULO O VACIO");
                        }
                        if (!gc.validaConExpresionRegular(str,"^(0[1-9]|1[012])$")){
                            throw new IllegalArgumentException("DATO MES INCORRECTO, DEBE TENER UN VALOR ENTRE 01 Y 12.");
                        }
                        balanza.setMes(str);
                        break;
                    case 4:
                        //Año - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE AÑO NULO O VACIO");
                        }
                        if (!gc.isNumeric(str, 1, 4)){
                            throw new IllegalArgumentException("DATO AÑO INCORRECTO, DEBE SER UN ENTERO.");
                        }
                        int anio = Integer.parseInt(str);
                        if (anio<2014 || anio>2099){
                            throw new IllegalArgumentException("DATO AÑO INCORRECTO, DEBE SER UN VALOR ENTRE 2014 Y 2099.");
                        }
                        balanza.setAno(anio);
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            datosGeneralesBalanza.setBalanza(balanza);
            lineaDatos.setDatosGeneralesBalanza(datosGeneralesBalanza);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
