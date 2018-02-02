/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.utils;

import com.cmm.cvs2xml.util.StringManage;
import com.cmm.cvs2xml.bean.LineaDatosAdicionales;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 08/05/2014 05:08:09 PM
 */
public class CmmCvsDatosAdicionalesUtils {

    public final static String idRegistro = "99";
    public final static String infoRegistro = "DATOS ADICIONALES A LA FACTURA";
    
    public static LineaDatosAdicionales fillData(String elementoCfdi) {
        LineaDatosAdicionales lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length>0){
            
            lineaDatos = new LineaDatosAdicionales();
            
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
                    default:
                        //Nombre - REQUERIDO
                        lineaDatos.getCamposAdicionales().add(str);
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
