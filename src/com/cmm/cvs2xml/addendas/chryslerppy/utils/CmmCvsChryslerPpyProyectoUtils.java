/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.chryslerppy.utils;

import com.cmm.cvs2xml.addendas.chryslerppy.bean.LineaDatosChryslerProyecto;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.addenda.chryslerppy.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 19/02/2016 09:50:11 AM
 */
public class CmmCvsChryslerPpyProyectoUtils {

    public final static String idRegistro = "00252";
    public final static String infoRegistro = "INFORMACIÓN DE PROYECTO PARA ADDENDA CHRYSLER PPY V1.0";
    //private final static int noElementosEsperados = 4;
    
    public static LineaDatosChryslerProyecto fillData(String elementoCfdi){
        LineaDatosChryslerProyecto lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosChryslerProyecto();
            Factura.Proyecto proyecto = new Factura.Proyecto();
            
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
                            //Numero - REQUERIDO
                            if (!gc.isValidString(str, 1,15)){
                                throw new IllegalArgumentException("DATO NUMERO DE PROYECTO INCORRECTO, REQUERIDO, MÍNIMO 1 CARACTERES, MÁXIMO 15.");
                            }
                            proyecto.setNumero(str);
                            break;
                        case 2:
                            //Numero de Trabajo - REQUERIDO
                            if (!gc.isValidString(str, 1,15)){
                                throw new IllegalArgumentException("DATO NUMERO DE TRABAJO INCORRECTO, REQUERIDO, MÍNIMO 1 CARACTERES, MÁXIMO 15.");
                            }
                            proyecto.setNumeroTrabajo(str);
                            break;
                        case 3:
                            //Charge Unit - OPCIONAL
                            if (StringManage.getValidString(str).length()>0){
                                if (!gc.isValidString(str, 1,15)){
                                    throw new IllegalArgumentException("DATO CHARGE UNIT INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 15.");
                                }
                                proyecto.setChargeUnit(str);
                            }
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setProyecto(proyecto);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
