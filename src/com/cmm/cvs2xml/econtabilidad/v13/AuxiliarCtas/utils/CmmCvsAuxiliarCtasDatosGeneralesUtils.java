/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.utils;

import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.bean.DatosGeneralesAuxiliarCtas;
import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.bean.LineaDatosAuxiliarCtas;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.econtabilidad.v13.schema.ctas.AuxiliarCtas;

/**
 *
 * @author user
 */
public class CmmCvsAuxiliarCtasDatosGeneralesUtils {
    
    public final static String idRegistro = "130";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS GENERALES PARA FORMATO AUXILIAR CUENTAS";
    private final static int noElementosEsperados = 10;
    
    public static LineaDatosAuxiliarCtas fillData(String elementoCfdi){
        LineaDatosAuxiliarCtas lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            
            lineaDatos = new LineaDatosAuxiliarCtas();
            DatosGeneralesAuxiliarCtas datosGeneralesAuxiliarCtas = new DatosGeneralesAuxiliarCtas();
            AuxiliarCtas auxiliarCtas = new AuxiliarCtas();
            
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
                        auxiliarCtas.setRFC(str);
                        break;
                    case 2:
                        //Mes - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE MES NULO O VACIO");
                        }
                        if (!gc.validaConExpresionRegular(str,"^(0[1-9]|1[012])$")){
                            throw new IllegalArgumentException("DATO MES INCORRECTO, DEBE TENER UN VALOR ENTRE 01 Y 12.");
                        }
                        auxiliarCtas.setMes(str);
                        break;
                    case 3:
                        //Año - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE AÑO NULO O VACIO");
                        }
                        if (!gc.isNumeric(str, 4, 4)){
                            throw new IllegalArgumentException("DATO AÑO INCORRECTO, DEBE SER UN ENTERO DE LONGITUD 4.");
                        }
                        int anio = Integer.parseInt(str);
                        if (anio<2015 || anio>2099){
                            throw new IllegalArgumentException("DATO AÑO INCORRECTO, DEBE SER UN VALOR ENTRE 2015 Y 2099.");
                        }
                        auxiliarCtas.setAnio(anio);
                        break;
                    case 4:
                        //Tipo Solicitud - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TIPO DE SOLICITUD NULO O VACIO");
                        }
                        if (!gc.validaConExpresionRegular(str, "^(AF|FC|DE|CO)$")) {
                            throw new IllegalArgumentException("EL DATO TIPO DE SOLICITUD ES INCORRECTO, SOLO SE PERMITEN LOS VALORES FIJOS 'AF', 'FC', 'DE', 'CO'.");
                        }
                        auxiliarCtas.setTipoSolicitud(str);
                        break;
                    case 5:
                        //Numero de Orden - OPCIONAL
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.validaConExpresionRegular(str, "^([A-Z]{3}[0-6][0-9][0-9]{5}(/)[0-9]{2})$")){
                                throw new IllegalArgumentException("DATO NUMERO DE ORDEN INCORRECTO, DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN [A-Z]{3}[0-6][0-9][0-9]{5}(/)[0-9]{2}.");
                            }
                            auxiliarCtas.setNumOrden(str);
                        }
                        break;
                    case 6:
                        //Numero de Tramite - OPCIONAL
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.validaConExpresionRegular(str, "^([A-Z]{2}[0-9]{12})$")){
                                throw new IllegalArgumentException("DATO NUMERO DE TRAMITE INCORRECTO, DEBE SER UN VALOR QUE CORRESPONDA AL PATRÓN [0-9]{10}.");
                            }
                            auxiliarCtas.setNumTramite(str);
                        }
                        break;
                    case 7:
                        //Sello - OPCIONAL
                        if (!"".equals(StringManage.getValidString(str))){                            
                            auxiliarCtas.setSello(str);
                        }
                        break;
                    case 8:
                        //Num Certificado - OPCIONAL
                        if (!"".equals(StringManage.getValidString(str))){                            
                            auxiliarCtas.setNoCertificado(str);
                        }
                        break;
                    case 9:
                        //Certificado - OPCIONAL
                        if (!"".equals(StringManage.getValidString(str))){                            
                            auxiliarCtas.setCertificado(str);
                        }
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            datosGeneralesAuxiliarCtas.setAuxiliarCtas(auxiliarCtas);
            lineaDatos.setDatosGeneralesAuxiliarCtas(datosGeneralesAuxiliarCtas);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
}
