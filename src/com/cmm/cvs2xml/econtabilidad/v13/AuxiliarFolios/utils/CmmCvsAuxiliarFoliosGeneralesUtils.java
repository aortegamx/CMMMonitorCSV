/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.utils;

import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.bean.DatosGeneralesAuxiliarFolios;
import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.bean.LineaDatosAuxiliarFolios;
import static com.cmm.cvs2xml.econtabilidad.v13.polizas.utils.CmmCvsPolizasDatosGeneralesUtils.idRegistro;
import static com.cmm.cvs2xml.econtabilidad.v13.polizas.utils.CmmCvsPolizasDatosGeneralesUtils.infoRegistro;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.econtabilidad.v13.schema.folios.RepAuxFol;

/**
 *
 * @author user
 */
public class CmmCvsAuxiliarFoliosGeneralesUtils {
    
    public final static String idRegistro = "140";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS GENERALES PARA FORMATO AUXILIAR FOLIOS DEL PERÍODO";
    private final static int noElementosEsperados = 10;
    
    public static LineaDatosAuxiliarFolios fillData(String elementoCfdi){
        LineaDatosAuxiliarFolios lineaDatos=null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            lineaDatos=new LineaDatosAuxiliarFolios();
            DatosGeneralesAuxiliarFolios datosGeneralesAuxFolios=new DatosGeneralesAuxiliarFolios();
            
            RepAuxFol auxFolios=new RepAuxFol();
            
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
                        if (!idRegistro.equals(StringManage.getValidString(str))) {
                            throw new IllegalArgumentException("IDENTIFICADOR DE REGISTRO NO VALIDO, DEBE SER ESTRICTAMENTE \""+idRegistro+"\" PARA " + infoRegistro);
                        }
                        break;
                    case 1:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE RFC RELACIONADO CON EL MOVIMIENTO NULO O VACIO");
                        }
                        if (!gc.isRFC(str)){
                            throw new IllegalArgumentException("DATO RFC RELACIONADO CON EL MOVIMIENTO INCORRECTO, NO ES VÁLIDO RESPECTO AL PATRÓN OFICIAL.");
                        }
                        auxFolios.setRFC(str);
                        break;
                    case 2:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL MES NULO O VACIO");
                        }
                        if(!gc.isNumeric(str, 2, 2)){
                            throw new IllegalArgumentException("NO MES ES INVALIDO");
                        }
                        auxFolios.setMes(str);
                        break;
                    case 3:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL AÑO NULO O VACIO");
                        }
                        if(!gc.isNumeric(str, 4, 4)){
                            throw new IllegalArgumentException("NO AÑO ES INVALIDO");
                        }
                        auxFolios.setAnio(Integer.parseInt(str));
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
                        auxFolios.setTipoSolicitud(str);
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
                            auxFolios.setNumOrden(str);
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
                            auxFolios.setNumTramite(str);
                        }
                        break;
                    case 7:
                        auxFolios.setSello(str);
                        break;
                    case 8:
                        if(!gc.isValidString(str, 0, 20)){
                            throw new IllegalArgumentException("EL NUMERO DE CERTIFICADO DEBE TENER UNA LONGITUD MAXIMA DE 14 DIGITOS");
                        }
                        auxFolios.setNoCertificado(str);
                        break;
                    case 9:
                        auxFolios.setCertificado(str);
                        break;
                }
            }
            datosGeneralesAuxFolios.setAuxFolios(auxFolios);
            lineaDatos.setDatosAuxiliarFolios(datosGeneralesAuxFolios);
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        return lineaDatos;
    }
}
