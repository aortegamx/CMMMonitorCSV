/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.chryslerppy.utils;

import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.addenda.chryslerppy.schema.Locacion;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/02/2015 09:50:11 AM
 */
public class CmmCvsChryslerPpyOrigenUtils {

    public final static String idRegistro = "00249";
    public final static String infoRegistro = "INFORMACIÓN DE ORIGEN PARA ADDENDA CHRYSLER PPY V1.0";
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
                            if (!gc.isValidString(str, 4, 7)){
                                throw new IllegalArgumentException("DATO CÓDIGO DE ORIGEN INCORRECTO, MÍNIMO 4 CARACTERES, MAXIMO 7.");
                            }
                            lineaDatos.setCodigo(str);
                            break;
                        case 2:
                            //Nombre - REQUERIDO
                            if (!gc.isValidString(str, 1,65)){
                                throw new IllegalArgumentException("DATO NOMBRE DE ORIGEN INCORRECTO, REQUERIDO, DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 65.");
                            }
                            lineaDatos.setNombre(str);
                            break;
                        case 3:
                            //Sufijo - OPCIONAL
                            if (StringManage.getValidString(str).length()>0){
                                if (!gc.isValidString(str, 1,3)){
                                    throw new IllegalArgumentException("DATO SUFIJO INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 3.");
                                }
                                lineaDatos.setSufijo(str);
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
