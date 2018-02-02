/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.vwpmt.utils;

import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.addenda.vwpmt.schema.Locacion;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/02/2015 09:50:11 AM
 */
public class CmmCvsVwPmtOrigenUtils {

    public final static String idRegistro = "00214";
    public final static String infoRegistro = "INFORMACIÓN DE ORIGEN PARA ADDENDA VW PMT V1.0";
    //private final static int noElementosEsperados = 4;
    
    public static Locacion fillData(String elementoCfdi){
        Locacion lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new Locacion();
            
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
                            //Código - REQUERIDO
                            if (!gc.validaConExpresionRegular(str, "^([0-9]{10})$")){
                                throw new IllegalArgumentException("DATO CÓDIGO DE ORIGEN INCORRECTO, DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN [0-9]{10}.");
                            }
                            lineaDatos.setCodigo(str);
                            break;
                        case 2:
                            //Nombre - OPCIONAL
                            if (StringManage.getValidString(str).length()>0){
                                if (!gc.isValidString(str, 1,35)){
                                    throw new IllegalArgumentException("DATO NOMBRE DE ORIGEN INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 35.");
                                }
                                lineaDatos.setNombre(str);
                            }
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
