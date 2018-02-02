package com.cmm.cvs2xml.econtabilidad.v13.polizas.utils;


import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.econtabilidad.v13.schema.polizas.CMoneda;
import mx.bigdata.sat.econtabilidad.v13.schema.polizas.Polizas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class CmmCvsPolizasCompNalUtil {
    public final static String idRegistro = "123";
    public final static String infoRegistro = "INFORMACIÓN PARA DETALLE DE TRANSACCIÓN DENTRO DE LA PÓLIZA";
    private final static int noElementosEsperados = 6;
    
    public static Polizas.Poliza.Transaccion fillData(String elementoCfdi, Polizas.Poliza.Transaccion transaccion) throws Exception{
        if (transaccion==null){
            throw new Exception("El objeto Polizas.Poliza.Transaccion recibido para completar su " + infoRegistro + " tiene un valor nulo.");
        }
        //Polizas.Poliza.Transaccion transaccion = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length < noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban al menos " + noElementosEsperados + " elementos requeridos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            boolean tipoCambioReq=false;
            Polizas.Poliza.Transaccion.CompNal comp = new Polizas.Poliza.Transaccion.CompNal();
            
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
                        //Clave o Número de Cuenta - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE UUID NULO O VACIO");
                        }
                        if (!gc.isUUID(str)){
                            throw new IllegalArgumentException("DATO UUID INCORRECTO");
                        }
                        comp.setUUIDCFDI(str);
                        break;
                    case 2:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE RFC NULO O VACIO");
                        }
                        if(!gc.isRFC(str)){
                            throw new IllegalArgumentException("DATO RFC INCORRECTO");
                        }
                        comp.setRFC(str);
                        break;
                    case 3:
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL MONTO TOTAL NULO O VACIO");
                        }
                        comp.setMontoTotal(new BigDecimal(str));
                        break;
                    case 4:
                        if(!"".equals(StringManage.getValidString(str))){
                           if(CMoneda.valueOf(str).equals("MXN")){
                               tipoCambioReq=true;
                           }
                           comp.setMoneda(CMoneda.fromValue(str));
                        }
                        break;
                    case 5:
                        if(tipoCambioReq){
                            if("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("EL TIPO DE CAMBIO ES REQUERIDO POR EL TIPO DE MONEDA");
                            }
                            if(!gc.isDecimal(str, 0, 19, 0, 2)){
                                throw new IllegalArgumentException("DATO 'TIPO CAMBIO' INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO 19 DECIMALES.");
                            }
                            comp.setTipCamb(new BigDecimal(str));
                        }
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            transaccion.getCompNal().add(comp);
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return transaccion;
    }
}
