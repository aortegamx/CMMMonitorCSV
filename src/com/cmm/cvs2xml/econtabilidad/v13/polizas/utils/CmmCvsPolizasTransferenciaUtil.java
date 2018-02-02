/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.v13.polizas.utils;

import com.cmm.cvs2xml.econtabilidad.v13.polizas.utils.*;
import com.cmm.cvs2xml.util.DateManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.econtabilidad.v13.schema.polizas.CMoneda;
import mx.bigdata.sat.econtabilidad.v13.schema.polizas.Polizas;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/09/2014 02:20:49 PM
 */
public class CmmCvsPolizasTransferenciaUtil {

    public final static String idRegistro = "127";
    public final static String infoRegistro = "INFORMACIÓN PARA DETALLE DE TRANSFERENCIA BANCARIA QUE CORRESPONDE A TRANSACCIÓN";
    private final static int noElementosEsperados = 13;
    
    public static Polizas.Poliza.Transaccion fillData(String elementoCfdi, Polizas.Poliza.Transaccion transaccion) throws Exception{
        if (transaccion==null){
            throw new Exception("El objeto Polizas.Poliza.Transaccion recibido para completar su " + infoRegistro + " tiene un valor nulo.");
        }
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            String formatoFecha = "yyyy-MM-dd";
            int maxDecimales;
            
            Polizas.Poliza.Transaccion.Transferencia transferencia = new Polizas.Poliza.Transaccion.Transferencia();
            boolean tipoCambioReq=false;
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
                            if (!gc.isValidString(str, 1, 50)){
                                throw new IllegalArgumentException("DATO CUENTA ORIGEN INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 50 CARACTERES.");
                            }
                            transferencia.setCtaOri(str);
                        }
                        break;
                    case 2:
                        //Banco Origen - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE BANCO ORIGEN NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 3)){
                            throw new IllegalArgumentException("DATO BANCO ORIGEN INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 3 CARACTERES.");
                        }
                        if (!gc.validaConExpresionRegular(str, "^([0-9]{3})$")){
                            throw new IllegalArgumentException("DATO BANCO ORIGEN INCORRECTO, NO CORRESPONDE A LAS ESPECIFICACIONES DEL SAT, PATRÓN: [0-9]{3}.");
                        }
                        transferencia.setBancoOriNal(str);
                        break;
                    case 3:
                        if (!"".equals(StringManage.getValidString(str))){
                            if(!gc.isValidString(str, 0, 150)){
                                throw new IllegalArgumentException("DATO NOMBRE DEL BANCO EXTRANJERO EMISOR INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 150 CARACTERES.");
                            }
                            transferencia.setBancoOriExt(str);
                        }
                        break;
                    case 4:
                        //Cuenta Destino - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CUENTA DESTINO NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 50)){
                            throw new IllegalArgumentException("DATO CUENTA DESTINO INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 50 CARACTERES.");
                        }
                        transferencia.setCtaDest(str);
                        break;
                    case 5:
                        //Banco Destino - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE BANCO DESTINO NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 3)){
                            throw new IllegalArgumentException("DATO BANCO DESTINO INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 3 CARACTERES.");
                        }
                        if (!gc.validaConExpresionRegular(str, "^([0-9]{3})$")){
                            throw new IllegalArgumentException("DATO BANCO DESTINO INCORRECTO, NO CORRESPONDE A LAS ESPECIFICACIONES DEL SAT, PATRÓN: [0-9]{3}.");
                        }
                        transferencia.setBancoDestNal(str);
                        break;
                    case 6:
                        if (!"".equals(StringManage.getValidString(str))){
                            if(!gc.isValidString(str, 0, 150)){
                                throw new IllegalArgumentException("DATO NOMBRE DEL BANCO DESTINO EXTRANJERO EMISOR INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 150 CARACTERES.");
                            }
                            transferencia.setBancoDestExt(str);
                        }
                        break;
                    case 7:
                        //Fecha - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE FECHA NULO O VACIO");
                        }
                        if (!gc.isDate(str, formatoFecha)){
                            throw new IllegalArgumentException("DATO FECHA INCORRECTO, DEBE TENER EL FORMATO " + formatoFecha);
                        }
                        try{
                            transferencia.setFecha(DateManage.dateToXMLGregorianCalendar2(DateManage.stringToDate(str, formatoFecha)));
                        }catch(Exception ex){
                            throw new IllegalArgumentException("DATO FECHA NO SE PUDO CONVERTIR, REVISÉ A DETALLE: " + ex.toString());
                        }
                        break;
                    case 8:
                        //Nombre de Beneficiario - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE NOMBRE DE BENEFICIARIO NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 300)){
                            throw new IllegalArgumentException("DATO NOMBRE DE BENEFICIARIO INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 300 CARACTERES.");
                        }
                        transferencia.setBenef(str);
                        break;
                    case 9:
                        //RFC relacionado con el movimiento - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE RFC RELACIONADO CON EL MOVIMIENTO NULO O VACIO");
                        }
                        if (!gc.isRFC(str)){
                            throw new IllegalArgumentException("DATO RFC RELACIONADO CON EL MOVIMIENTO INCORRECTO, NO ES VÁLIDO RESPECTO AL PATRÓN OFICIAL.");
                        }
                        transferencia.setRFC(str);
                        break;
                    case 10:
                        //Monto - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE 'MONTO' NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 14, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO 'MONTO' INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        transferencia.setMonto(new BigDecimal(str));
                        break;
                    case 11:
                        if(!"".equals(StringManage.getValidString(str))){
                           if(CMoneda.valueOf(str).equals("MXN")){
                               tipoCambioReq=true;
                           }
                           transferencia.setMoneda(CMoneda.fromValue(str));
                        }
                        break;
                    case 12:
                        if(tipoCambioReq){
                            if("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("EL TIPO DE CAMBIO ES REQUERIDO POR EL TIPO DE MONEDA");
                            }
                            if(!gc.isDecimal(str, 0, 19, 0, 2)){
                                throw new IllegalArgumentException("DATO 'TIPO CAMBIO' INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO 19 DECIMALES.");
                            }
                            transferencia.setTipCamb(new BigDecimal(str));
                        }
                        break;
                    
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            transaccion.getTransferencia().add(transferencia);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return transaccion;
    }
    
}