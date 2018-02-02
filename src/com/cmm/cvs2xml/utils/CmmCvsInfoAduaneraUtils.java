/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.utils;

import com.cmm.cvs2xml.util.StringManage;
import com.cmm.cvs2xml.util.DateManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.bean.LineaDatosConcepto;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.InformacionAduanera;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 23/04/2014 05:08:09 PM
 */
public class CmmCvsInfoAduaneraUtils {

    public final static String idRegistro = "04";
    public final static String infoRegistro = "INFORMACIÓN ADUANERA";
    
    public static LineaDatosConcepto fillData(String elementoCfdi, LineaDatosConcepto lineaDatos) throws Exception{
        
        if (lineaDatos==null){
            throw new Exception("El objeto LineaDatosConcepto recibido para completar su " + infoRegistro + " tiene un valor nulo.");
        }
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            String formatoFecha = "yyyy-MM-dd";
            
            InformacionAduanera informacionAduanera = new InformacionAduanera();
            
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
                        //Número - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE NUMERO NULO O VACIO");
                        }
                        if(!gc.validaConExpresionRegular(str, "[0-9]{2}  [0-9]{2}  [0-9]{4}  [0-9]{7}")){
                            throw new IllegalArgumentException("EL NUMERO DE PEDIMENTO DEBE CONTENER 21 DIGITOS");
                        }
                        informacionAduanera.setNumeroPedimento(str);
                        break;
                    /*case 2:
                        //Fecha - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE FECHA NULO O VACIO");
                        }
                        if (!gc.isDate(str, formatoFecha)){
                            throw new IllegalArgumentException("DATO FECHA INCORRECTO, DEBE TENER EL FORMATO " + formatoFecha);
                        }
                        informacionAduanera.setFecha(DateManage.stringToDate(str, formatoFecha));
                        break;
                    case 3:
                        //Aduana - Opcional
                        informacionAduanera.setAduana(str);
                        break;*/
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            lineaDatos.getDatosConcepto().getConcepto().getInformacionAduanera().add(informacionAduanera);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
