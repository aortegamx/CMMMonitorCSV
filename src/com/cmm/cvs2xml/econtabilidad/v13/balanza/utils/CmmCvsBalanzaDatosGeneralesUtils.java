/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.v13.balanza.utils;

import com.cmm.cvs2xml.econtabilidad.v13.balanza.utils.*;
import com.cmm.cvs2xml.econtabilidad.v13.balanza.bean.*;
import com.cmm.cvs2xml.util.DateManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.econtabilidad.v13.schema.balanza.Balanza;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/09/2014 02:19:43 PM
 */
public class CmmCvsBalanzaDatosGeneralesUtils {

    public final static String idRegistro = "110";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS GENERALES PARA FORMATO BALANZA DE COMPROBACIÓN";
    private final static int noElementosEsperados = 9;
    
    public static LineaDatosBalanza fillData(String elementoCfdi){
        LineaDatosBalanza lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            String formatoFecha = "yyyy-MM-dd";
            lineaDatos = new LineaDatosBalanza();
            DatosGeneralesBalanza datosGeneralesBalanza = new DatosGeneralesBalanza();
            Balanza balanza = new Balanza();
            boolean complemento=false;
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
                        //Mes - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE MES NULO O VACIO");
                        }
                        if (!gc.validaConExpresionRegular(str,"^(0[1-9]|1[012])$")){
                            throw new IllegalArgumentException("DATO MES INCORRECTO, DEBE TENER UN VALOR ENTRE 01 Y 12.");
                        }
                        balanza.setMes(str);
                        break;
                    case 3:
                        //Año - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE AÑO NULO O VACIO");
                        }
                        if (!gc.isNumeric(str, 1, 4)){
                            throw new IllegalArgumentException("DATO AÑO INCORRECTO, DEBE SER UN ENTERO.");
                        }
                        int anio = Integer.parseInt(str);
                        if (anio<2015 || anio>2099){
                            throw new IllegalArgumentException("DATO AÑO INCORRECTO, DEBE SER UN VALOR ENTRE 2015 Y 2099.");
                        }
                        balanza.setAnio(anio);
                        break;
                    case 4:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL TIPO DE ENVIO NULO O VACIO");
                        }
                        if(!gc.validaConExpresionRegular(str, "[NC]")){
                            throw new IllegalArgumentException("DATO TIPO DE ENVIO INCORRECTO, DEBE SER UN VALOR N O C");
                        }
                        if(str.toUpperCase().equals("C")){
                            complemento=true;
                        }
                        balanza.setTipoEnvio(str);
                        break;
                    case 5:
                        if (complemento){
                            if("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE FECHA DE LA ULTIMA MODIFICACION NULO O VACIO");
                            }
                            if (!gc.isDate(str, formatoFecha)){
                                throw new IllegalArgumentException("DATO FECHA INCORRECTO, DEBE TENER EL FORMATO " + formatoFecha);
                            }
                            try{
                                balanza.setFechaModBal(DateManage.dateToXMLGregorianCalendar2(DateManage.stringToDate(str, formatoFecha)));
                            }catch(Exception ex){
                                throw new IllegalArgumentException("DATO FECHA NO SE PUDO CONVERTIR, REVISÉ A DETALLE: " + ex.toString());
                            }
                        }
                        break;
                    case 6:
                        balanza.setSello(str);
                        break;
                    case 7:
                        if(!gc.isValidString(str, 0, 20)){
                            throw new IllegalArgumentException("EL NUMERO DE CERTIFICADO DEBE TENER UNA LONGITUD MAXIMA DE 14 DIGITOS");
                        }
                        balanza.setNoCertificado(str);
                        break;
                    case 8:
                        balanza.setCertificado(str);
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
