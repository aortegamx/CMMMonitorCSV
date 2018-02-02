/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.vwpmt.utils;

import com.cmm.cvs2xml.addendas.vwpmt.bean.LineaDatosVWArchivo;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.addenda.vwpmt.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/02/2015 09:50:11 AM
 */
public class CmmCvsVwPmtArchivoUtils {

    public final static String idRegistro = "00219";
    public final static String infoRegistro = "INFORMACIÓN DE ARCHIVOS PARA ADDENDA VW PMT V1.0";
    private final static int noElementosEsperados = 3;
    
    public static LineaDatosVWArchivo fillData(String elementoCfdi){
        LineaDatosVWArchivo lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosVWArchivo();
            Factura.Archivo archivo = new Factura.Archivo();
            
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
                            //Datos - REQUERIDO
                            if (StringManage.getValidString(str).length()<=0){
                                throw new IllegalArgumentException("DATOS DE ARCHIVO INCORRECTO. ES UN CAMPO REQUERIDO EN ESTE IDENTIFICADOR.");
                            }
                            archivo.setDatos(str);
                            break;
                        case 2:
                            //Tipo - REQUERIDO
                            if (!gc.validaConExpresionRegular(str, "PDF|XLS|ZIP") ){
                                throw new IllegalArgumentException("DATO TIPO DE ARCHIVO INCORRECTO, REQUERIDO, DEBE SER UNO DE LOS SIGUIENTES: XLS, PDF, ZIP.");
                            }
                            archivo.setTipo(str);
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setArchivo(archivo);
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
