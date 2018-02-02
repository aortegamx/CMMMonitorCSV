/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.v13.polizas.utils;

import com.cmm.cvs2xml.econtabilidad.v13.polizas.utils.*;
import com.cmm.cvs2xml.econtabilidad.v13.polizas.bean.*;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.econtabilidad.v13.schema.polizas.Polizas;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/09/2014 02:19:43 PM
 */
public class CmmCvsPolizasDatosGeneralesUtils {

    public final static String idRegistro = "120";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS GENERALES PARA FORMATO PÓLIZAS DEL PERÍODO";
    private final static int noElementosEsperados = 10;
    
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
            
            boolean numOrdenReq=false;
            boolean numTramiteReq=false;
            
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
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL RFC NULO O VACIO");
                        }
                        if(!gc.isRFC(str)){
                            throw new IllegalArgumentException("EL RFC ES INVALIDO");
                        }
                        polizas.setRFC(str);
                        break;
                    case 2:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL MES NULO O VACIO");
                        }
                        if(!gc.isNumeric(str, 2, 2)){
                            throw new IllegalArgumentException("NO MES ES INVALIDO");
                        }
                        polizas.setMes(str);
                        break;
                    case 3:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL AÑO NULO O VACIO");
                        }
                        if(!gc.isNumeric(str, 4, 4)){
                            throw new IllegalArgumentException("NO AÑO ES INVALIDO");
                        }
                        polizas.setAnio(Integer.parseInt(str));
                        break;
                    case 4:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL TIPO DE SOLICITUD NULO O VACIO");
                        }
                        if(!gc.validaConExpresionRegular(str, "AF|FC|DE|CO")){
                            throw new IllegalArgumentException("EL TIPO DE SOLICITUD ES INCORRECTO, DEBE SER ENTRE (AF - Acto de Fiscalización; FC - Fiscalización Compulsa; DE - Devolución; CO - Compensación)");
                        }
                        if(str.toUpperCase().equals("AF")||str.toUpperCase().equals("FC")){
                            numOrdenReq=true;
                        }
                        if(str.toUpperCase().equals("DE")||str.toUpperCase().equals("CO")){
                            numTramiteReq=true;
                        }
                        polizas.setTipoSolicitud(str);
                        break;
                    case 5:
                        if(numOrdenReq){
                            if ("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE EL NUMERO DE ORDEN NULO O VACIO YA QUE EL TIPO DE SOLICITUD ES AF O FC");
                            }
                            if(!gc.isValidString(str, 0, 13)){
                                throw new IllegalArgumentException("EL NUMERO DE ORDEN DEBE TENER UNA LONGITUD MAXIMA DE 13 DIGITOS");
                            }
                            if(!gc.validaConExpresionRegular(str, "[A-Z]{3}[0-9]{7}(/)[0-9]{2}")){
                                throw new IllegalArgumentException("El NUMERO DE CUENTA INCORRECTO, DEBE AL PATRON [A-Z]{3}[0-9]{7}(/)[0-9]{2}");
                            }
                            polizas.setNumOrden(str);
                        }
                        break;
                    case 6:
                        if(numTramiteReq){
                            if ("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE EL NUMERO DE TRAMITE NULO O VACIO YA QUE EL TIPO DE SOLICITUD ES DE O CO");
                            }
                            if(!gc.isValidString(str, 0, 14)){
                                throw new IllegalArgumentException("EL NUMERO DE TRAMITE DEBE TENER UNA LONGITUD MAXIMA DE 14 DIGITOS");
                            }
                            if(!gc.validaConExpresionRegular(str, "[A-Z]{2}[0-9]{12}")){

                            }
                            polizas.setNumTramite(str);
                        }
                        break;
                    case 7:
                        polizas.setSello(str);
                        break;
                    case 8:
                        if(!gc.isValidString(str, 0, 20)){
                            throw new IllegalArgumentException("EL NUMERO DE CERTIFICADO DEBE TENER UNA LONGITUD MAXIMA DE 14 DIGITOS");
                        }
                        polizas.setNoCertificado(str);
                        break;
                    case 9:
                        polizas.setCertificado(str);
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
