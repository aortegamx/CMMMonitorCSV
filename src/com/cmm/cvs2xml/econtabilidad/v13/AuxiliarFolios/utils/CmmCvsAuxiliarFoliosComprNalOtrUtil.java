/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.utils;

import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import java.math.BigInteger;
import mx.bigdata.sat.econtabilidad.v13.schema.folios.CMoneda;
import mx.bigdata.sat.econtabilidad.v13.schema.folios.RepAuxFol;

/**
 *
 * @author user
 */
public class CmmCvsAuxiliarFoliosComprNalOtrUtil {
    
    public final static String idRegistro = "143";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS GENERALES PARA FORMATO AUXILIAR FOLIOS DEL PERÍODO";
    private final static int noElementosEsperados = 8;
    
    public static RepAuxFol.DetAuxFol fillData(String elementoCfdi, RepAuxFol.DetAuxFol auxFolioDetalle) throws Exception{
        if (auxFolioDetalle==null){
            throw new Exception("El objeto RepAuxFol.DetAuxFol recibido para completar su " + infoRegistro + " tiene un valor nulo.");
        }
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            
            boolean tipoCambioReq=false;
            RepAuxFol.DetAuxFol.ComprNalOtr auxFolioComp=new RepAuxFol.DetAuxFol.ComprNalOtr();
            
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
                        if (!"".equals(StringManage.getValidString(str))){
                            if(!gc.validaConExpresionRegular(str, "[A-Z]+")){
                                throw new IllegalArgumentException("DATO CFD_CBB SERIE INCORRECTO");
                            }
                            if(!gc.isValidString(str, 1, 10)){
                                throw new IllegalArgumentException("DATO CFD_CBB SERIE INCORRECTO, DEBE CONTENER DE 1 A 10 CARACTERES");
                            }
                            auxFolioComp.setCFDCBBSerie(str);
                        }
                        break;
                    case 2:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CFD_CBB NUMERO FOLIO NULO O VACIO");
                        }
                        if(!gc.isNumeric(str, 1, 20)){
                            throw new IllegalArgumentException("DATO CFD_CBB NUMERO FOLIO INCORRECTO, DEBE CONTENER DE 1 A 20 DIGITOS");
                        }
                        auxFolioComp.setCFDCBBNumFol(new BigInteger(str));
                        break;
                    case 3:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL MONTO TOTAL NULO O VACIO");
                        }
                        auxFolioComp.setMontoTotal(new BigDecimal(str));
                        break;
                    case 4:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE RFC NULO O VACIO");
                        }
                        if(!gc.isRFC(str)){
                            throw new IllegalArgumentException("DATO RFC INCORRECTO");
                        }
                        auxFolioComp.setRFC(str);
                        break;
                    case 5:
                        if(!"".equals(StringManage.getValidString(str))){
                            if (!gc.isValidString(str, 2, 2)){
                                throw new IllegalArgumentException("DATO METODO DE PAGO INCORRECTO, DEBE TENER 2 CARACTERES.");
                            }
                            if (!gc.validaConExpresionRegular(str, "^([0-9]{2})$")){
                                throw new IllegalArgumentException("DATO METODO DE PAGO INCORRECTO, NO CORRESPONDE A LAS ESPECIFICACIONES DEL SAT, PATRÓN: [0-9]{2}.");
                            }
                            auxFolioComp.setMetPagoAux(str);
                        }
                        break;
                    case 6:
                        if(!"".equals(StringManage.getValidString(str))){
                           if(CMoneda.valueOf(str).equals("MXN")){
                               tipoCambioReq=true;
                           }
                           auxFolioComp.setMoneda(CMoneda.fromValue(str));
                        }
                        break;
                    case 7:
                        if(tipoCambioReq){
                            if("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("EL TIPO DE CAMBIO ES REQUERIDO POR EL TIPO DE MONEDA");
                            }
                            if(!gc.isDecimal(str, 0, 19, 0, 2)){
                                throw new IllegalArgumentException("DATO 'TIPO CAMBIO' INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO 19 DECIMALES.");
                            }
                            auxFolioComp.setTipCamb(new BigDecimal(str));
                        }
                        break;
                }
            }
            auxFolioDetalle.getComprNalOtr().add(auxFolioComp);
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        return auxFolioDetalle;
    }
}
