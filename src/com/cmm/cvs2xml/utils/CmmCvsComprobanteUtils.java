/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.utils;

import com.cmm.cvs2xml.util.StringManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.bean.DatosComprobante;
import com.cmm.cvs2xml.bean.LineaDatosFactura;
import java.math.BigDecimal;
import mx.bigdata.sat.cfdi.v33.schema.CMetodoPago;
import mx.bigdata.sat.cfdi.v33.schema.CMoneda;
import mx.bigdata.sat.cfdi.v33.schema.CTipoDeComprobante;
import mx.bigdata.sat.cfdi.v33.schema.CTipoFactor;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 23/04/2014 05:08:09 PM
 */
public class CmmCvsComprobanteUtils {

    public final static String idRegistro = "02";
    public final static String infoRegistro = "DATOS DE LA FACTURA";
    
    public static LineaDatosFactura fillData(String elementoCfdi){
        LineaDatosFactura lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosFactura();
            DatosComprobante datosComprobante = new DatosComprobante();
            Comprobante comprobante = new Comprobante();
            boolean tipoCambio=false;
            
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
                        //Subtotal - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE SUBTOTAL NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO SUBTOTAL INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        comprobante.setSubTotal(new BigDecimal(str));
                        break;
                    case 2:
                        //Descuento - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DESCUENTO NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO DESCUENTO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        comprobante.setDescuento(new BigDecimal(str));
                        break;
                    case 3:
                        //Porcentaje Descuento - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE PORCENTAJE DESCUENTO NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO PORCENTAJE DESCUENTO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        datosComprobante.setPorcentajeDescuento(new BigDecimal(str));
                        break;
                    case 4:
                        //Total - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TOTAL NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO TOTAL INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        comprobante.setTotal(new BigDecimal(str));
                        break;
                    case 5:
                        //Forma de Pago - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE FORMA DE PAGO NULO O VACIO");
                        }
                        comprobante.setFormaPago(str);
                        break;
                    case 6:
                        //Asunto - Opcional
                        datosComprobante.setAsunto(str);
                        break;
                    case 7:
                        //Tipo de Comprobante - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("DATO TIPO DE COMPROBANTE INCORRECTO, SOLO SE PERMITE: \"Ingreso\", \"Egreso\" o \"Traslado\" ");
                        }
                        comprobante.setTipoDeComprobante(CTipoDeComprobante.fromValue(str));
                        break;
                    case 8:
                        //Observaciones - Opcional
                        datosComprobante.setObservaciones(str);
                        break;
                    case 9:
                        //Metodo de Pago - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE METODO DE PAGO NULO O VACIO");
                        }
                        comprobante.setMetodoPago(CMetodoPago.fromValue(str));
                        break;
                    case 10:
                        //Tipo de IVA - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TIPO DE IVA NULO O VACIO");
                        }
                        if ("Individual".equalsIgnoreCase(StringManage.getValidString(str))){
                            lineaDatos.setTipoImpuesto(LineaDatosFactura.TipoImpuesto.INDIVIDUAL);
                        }else if ("Grupal".equalsIgnoreCase(StringManage.getValidString(str))){
                            lineaDatos.setTipoImpuesto(LineaDatosFactura.TipoImpuesto.GRUPAL);
                        }else {
                            throw new IllegalArgumentException("NO SE PERMITE TIPO DE IVA CON VALOR DIFERENTE A \"Individual\" o \"Grupal\"");
                        }
                        break;
                    case 11:
                        //Lugar de Expedicion - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE LUGAR DE EXPEDICION NULO O VACIO");
                        }
                        comprobante.setLugarExpedicion(str);
                        break;
                    case 12:
                        //Moneda - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO MONEDA NULO O VACIO");
                        }
                        if(!str.toUpperCase().equals("MXN")&&!str.toUpperCase().equals("XXX")){
                            tipoCambio=true;
                        }
                        comprobante.setMoneda(CMoneda.fromValue(str));
                        break;
                    case 13:
                        //Tipo de Cambio - Opcional
                        if(tipoCambio){
                            if("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE EL TIPO DE CAMBIO NULO O VACIO POR EL TIPO DE MODENA "+String.valueOf(comprobante.getMoneda()));
                            }
                            System.out.println(str);
                            comprobante.setTipoCambio(new BigDecimal(str));
                        }
                        break;
                    case 14:
                        //Monto IVA Grupal - Opcional
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE MONTO DE IVA DE CAMBIO NULO O VACIO");
                        }
                        maxDecimales = 6;
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO MONTO IVA GRUPAL INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        lineaDatos.setIVAGrupalMonto(new BigDecimal(str));
                        break;
                    case 15:
                        //Tipo factor IVA - Condicional
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TIPO DE FACTOR IVA DE CAMBIO NULO O VACIO");
                        }
                        lineaDatos.setTipoFactorIVA(CTipoFactor.fromValue(str));
                        break;
                    case 16:
                        //Tasa IVA Grupal - Opcional
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TASA DE IVA DE CAMBIO NULO O VACIO");
                        }
                        lineaDatos.setIVAGrupalTasa(str);
                        break;
                    case 17:
                        //Monto IEPS Grupal - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE MONTO DE IEPS DE CAMBIO NULO O VACIO");
                        }
                        maxDecimales = 6;
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO MONTO IEPS GRUPAL INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        lineaDatos.setIEPSGrupalMonto(new BigDecimal(str));
                        break;
                    case 18:
                        //Tipo factor IEPS - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TIPO DE FACTOR IEPS DE CAMBIO NULO O VACIO");
                        }
                        lineaDatos.setTipoFactorIEPS(CTipoFactor.fromValue(str));
                        break;
                    case 19:
                        //Tasa IEPS Grupal - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TASA DE IEPS DE CAMBIO NULO O VACIO");
                        }
                        lineaDatos.setIEPSGrupalTasa(str);
                        break;
                    case 20:
                        //Monto IVA Retenido - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            maxDecimales = 6;
                            if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO MONTO IVA RETENIDO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            lineaDatos.setIVARetenidoMonto(new BigDecimal(str));
                        }
                        break;
                    case 21:
                        //Monto ISR - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            maxDecimales = 6;
                            if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO MONTO ISR INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            lineaDatos.setISRRetenidoMonto(new BigDecimal(str));
                        }
                        break;
                    case 22:
                        //Serie del Comprobante - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (gc.isValidString(str, 1, 25)){                            
                                comprobante.setSerie(str);
                            }else{
                                throw new IllegalArgumentException("EL DATO SERIE DEBE SER IGUAL O MAYOR A 1 CARACTER Y MENOR O IGUAL A 25 CARACTERES.");                        
                            }
                        }
                        break;
                    case 23:
                        //Folio del Comprobante - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (gc.isNumeric(str, 1, 20)){
                                comprobante.setFolio(str);
                            }else{
                                throw new IllegalArgumentException("EL DATO FOLIO DEBE SER IGUAL O MAYOR A 1 CARACTER Y MENOR O IGUAL A 20 CARACTERES, ASI COMO SOLO DEBE SER NUMERICO.");                        
                            }
                        }
                        break;
                    case 24:
                        //Conficiones de pago - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if(!gc.validaConExpresionRegular(str, "{1,1000}")){
                                comprobante.setCondicionesDePago(str);
                            }else{
                                throw new IllegalArgumentException("EL DATO CONDICIONES DE PAGO DEBE SER IGUAL 1 Y MAYOR A 1000 CARACTERES");                        
                            }
                        }
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            datosComprobante.setComprobante(comprobante);
            lineaDatos.setDatosComprobante(datosComprobante);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
