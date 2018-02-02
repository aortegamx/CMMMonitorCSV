/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.utils;

import com.cmm.cvs2xml.bean.LineaDatosCFDIRelacionado;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.util.ArrayList;
import java.util.List;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.CfdiRelacionados.CfdiRelacionado;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.CfdiRelacionados;

/**
 *
 * @author user
 */
public class CmmCvsCFDIRelacionadoUtils {
    
    public final static String idRegistro = "08";
    public final static String infoRegistro = "DATOS CFDI RELACIONADO";
    
    //Aplica en este caso por que finaliza con un elemento REQUERIDO obligatorio
    private final static int noElementosEsperados = 3; 
    
    public static LineaDatosCFDIRelacionado fillData(String elementoCfdi){
        LineaDatosCFDIRelacionado lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            CfdiRelacionados cfdiRelacionado=new CfdiRelacionados();
            for (x=0; x < data.length; x++){
                String str = data[x];
                if (str!=null){
                    str = str.trim();
                    if ("".equals(str)) str =null;
                }
                
                switch (x) {

                    case 0:
                        //Identificador Registro - REQUERIDO
                        if (!idRegistro.equals(StringManage.getValidString(str))) {
                            throw new IllegalArgumentException("IDENTIFICADOR DE REGISTRO NO VALIDO, DEBE SER ESTRICTAMENTE \""+idRegistro+"\" PARA " + infoRegistro);
                        }
                        break;
                    case 1:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL TIPO DE RELACION NULO O VACIO");
                        }
                        if(str.equals("01")||str.equals("02")||str.equals("03")||str.equals("04")||str.equals("05")||str.equals("06")||str.equals("07")){
                            cfdiRelacionado.setTipoRelacion(str);
                        }else{
                            throw new IllegalArgumentException("EL TIPO DE RELACION ES INVALIDO FAVOR DE COMPROBAR EL CATALOGO");
                        }
                        break;
                    case 2:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE LOS UUID NULO O VACIO");
                        }
                        String uuids[]=str.replaceAll(" ", "").split(",");
                        for(String item:uuids){
                            if(!gc.isUUID(item)){
                                throw new IllegalArgumentException("EL UUID ESCRITO: "+item+" ES INVALIDO");
                            }
                            CfdiRelacionado relacion=new CfdiRelacionado();
                            relacion.setUUID(item);
                            cfdiRelacionado.getCfdiRelacionado().add(relacion);
                        }
                        break;
                }
            }
            lineaDatos=new LineaDatosCFDIRelacionado();
            lineaDatos.setTipoRelacion(cfdiRelacionado.getTipoRelacion());
            lineaDatos.getDatosCFDIRelacionado().addAll(cfdiRelacionado.getCfdiRelacionado());
        }
        
        return lineaDatos;
    }
    
}
