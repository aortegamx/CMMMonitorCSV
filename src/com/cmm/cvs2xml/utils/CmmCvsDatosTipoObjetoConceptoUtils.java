/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.utils;

import com.cmm.cvs2xml.bean.LineaDatosTipoObjetoConcepto;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;

/**
 *
 * @author ISCesar
 */
public class CmmCvsDatosTipoObjetoConceptoUtils {
    
    public final static String idRegistro = "97";
    public final static String infoRegistro = "DATOS ADICIONALES PARA TIPO DE OBJETO CONCEPTO EN REPRESENTACION IMPRESA";
    
    public static LineaDatosTipoObjetoConcepto fillData(String elementoCfdi) {
        LineaDatosTipoObjetoConcepto lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            
            lineaDatos = new LineaDatosTipoObjetoConcepto();
            
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
                        //Tipo de Objetos Concepto - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TIPO DE OBJETOS NULO O VACIO");
                        }
                        if (!gc.isNumeric(str, 1, 2)){
                            throw new IllegalArgumentException("DATO TIPO DE OBJETOS INCORRECTO, DEBE SER UN VALOR ENTERO.");
                        }
                        lineaDatos.setTipoObjetosConcepto(Integer.parseInt(str));
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
