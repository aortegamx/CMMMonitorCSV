/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.polizas.utils;

import com.cmm.cvs2xml.econtabilidad.polizas.bean.*;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.econtabilidad.v1.polizas.schema.Polizas;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/09/2014 02:19:43 PM
 */
public class CmmCvsPolizasDatosGeneralesUtils {

    public final static String idRegistro = "120";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS GENERALES PARA FORMATO PÓLIZAS DEL PERÍODO";
    private final static int noElementosEsperados = 4;
    
    public static LineaDatosPolizas fillData(String elementoCfdi){
        LineaDatosPolizas lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            
            lineaDatos = new LineaDatosPolizas();
            DatosGeneralesPolizas datosGeneralesPolizas = new DatosGeneralesPolizas();
            Polizas polizas = new Polizas();
            
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
                        polizas.setRFC(str);
                        break;
                    case 2:
                        //Mes - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE MES NULO O VACIO");
                        }
                        if (!gc.validaConExpresionRegular(str,"^(0[1-9]|1[012])$")){
                            throw new IllegalArgumentException("DATO MES INCORRECTO, DEBE TENER UN VALOR ENTRE 01 Y 12.");
                        }
                        polizas.setMes(str);
                        break;
                    case 3:
                        //Año - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE AÑO NULO O VACIO");
                        }
                        if (!gc.isNumeric(str, 4, 4)){
                            throw new IllegalArgumentException("DATO AÑO INCORRECTO, DEBE SER UN ENTERO DE LONGITUD 4.");
                        }
                        int anio = Integer.parseInt(str);
                        if (anio<2014 || anio>2099){
                            throw new IllegalArgumentException("DATO AÑO INCORRECTO, DEBE SER UN VALOR ENTRE 2014 Y 2099.");
                        }
                        polizas.setAno(anio);
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            datosGeneralesPolizas.setPolizas(polizas);
            lineaDatos.setDatosGeneralesPolizas(datosGeneralesPolizas);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
