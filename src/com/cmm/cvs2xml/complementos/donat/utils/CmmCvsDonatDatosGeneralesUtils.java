/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.complementos.donat.utils;

import com.cmm.cvs2xml.complementos.donat.bean.DatosGeneralesDonat;
import com.cmm.cvs2xml.complementos.donat.bean.LineaDatosDonat;
import com.cmm.cvs2xml.util.DateManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.common.donat.schema.Donatarias;

/**
 *
 * @author leonardo
 */
public class CmmCvsDonatDatosGeneralesUtils {
    public final static String idRegistro = "50";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS GENERALES PARA FORMATO DONATARIAS";
    private final static int noElementosEsperados = 4;
    
    public static LineaDatosDonat fillData(String elementoCfdi){
        LineaDatosDonat lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            
            lineaDatos = new LineaDatosDonat();
            DatosGeneralesDonat datosGeneralesDonat = new DatosGeneralesDonat();
            Donatarias donatarias = new Donatarias();
            
            donatarias.setVersion("1.1");
            
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
                        //NUMERO AUTORIZACION - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE NUMERO AUTORIZACION NULO O VACIO");
                        }                        
                        donatarias.setNoAutorizacion(str);
                        break;
                    case 2:
                        //FECHA AUTORIZACION - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE FECHA AUTORIZACION NULO O VACIO");
                        }
                        if (!gc.isDate2(str)){
                            throw new IllegalArgumentException("DATO FECHA AUTORIZACION ES INCORRECTO, VERIFIQUE EL PATRON.");
                        }    
                        try{
                            donatarias.setFechaAutorizacion(DateManage.dateToXMLGregorianCalendar2(DateManage.Date(str)));
                        }catch(Exception e){e.printStackTrace();}
                        break;
                    case 3:
                        //LEYENDA - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE LEYENDA NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 500)){
                            throw new IllegalArgumentException("DATO LEYENDA INCORRECTO, DEBE TENER UNA LONGITUD DE 1 A 500 CARACTERES.");
                        }
                        donatarias.setLeyenda(str);
                        break;                    
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            datosGeneralesDonat.setDonatarias(donatarias);
            lineaDatos.setDatosGeneralesDonatarias(datosGeneralesDonat);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
