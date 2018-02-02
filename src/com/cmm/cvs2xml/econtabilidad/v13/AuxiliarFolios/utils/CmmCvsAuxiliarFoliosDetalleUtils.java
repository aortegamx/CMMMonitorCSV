/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.utils;

import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.bean.DatosAuxiliarFoliosDetalle;
import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.bean.DatosGeneralesAuxiliarFolios;
import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.bean.LineaDatosAuxiliarFoliosDetalle;
import com.cmm.cvs2xml.util.DateManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.econtabilidad.v13.schema.folios.RepAuxFol;

/**
 *
 * @author user
 */
public class CmmCvsAuxiliarFoliosDetalleUtils {
    
    public final static String idRegistro = "141";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS GENERALES PARA FORMATO AUXILIAR FOLIOS DEL PERÍODO";
    private final static int noElementosEsperados = 3;
    
    public static LineaDatosAuxiliarFoliosDetalle fillData(String elementoCfdi){
        LineaDatosAuxiliarFoliosDetalle lineaDatosDetalle=null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            lineaDatosDetalle=new LineaDatosAuxiliarFoliosDetalle();
            DatosAuxiliarFoliosDetalle datosGeneralesAuxFoliosDetalle=new DatosAuxiliarFoliosDetalle();
            String formatoFecha = "yyyy-MM-dd";
            
            RepAuxFol.DetAuxFol auxFoliosDetalle=new RepAuxFol.DetAuxFol();
            
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
                        //Tipo - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL NUMERO UNICO DE INDETIFICACION DE PÓLIZA NULO O VACIO");
                        }
                        if(!gc.isValidString(str, 1, 50)){
                            throw new IllegalArgumentException("EL NUMERO UNICO DE INDETIFICACION DE PÓLIZA DEBE CONTENER DE 1 A 50 CARACTERES");
                        }
                        auxFoliosDetalle.setNumUnIdenPol(str);
                        break;
                    case 2:
                        //Fecha - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE FECHA NULO O VACIO");
                        }
                        if (!gc.isDate(str, formatoFecha)){
                            throw new IllegalArgumentException("DATO FECHA INCORRECTO, DEBE TENER EL FORMATO " + formatoFecha);
                        }
                        try{
                            auxFoliosDetalle.setFecha(DateManage.dateToXMLGregorianCalendar2(DateManage.stringToDate(str, formatoFecha)));
                        }catch(Exception ex){
                            throw new IllegalArgumentException("DATO FECHA NO SE PUDO CONVERTIR, REVISÉ A DETALLE: " + ex.toString());
                        }
                        break;
                    
                }
            }
            datosGeneralesAuxFoliosDetalle.setAuxFoliosDetalle(auxFoliosDetalle);
            lineaDatosDetalle.setAuxDatosFoliosDetalle(datosGeneralesAuxFoliosDetalle);
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        return lineaDatosDetalle;
    }
}
