/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.utils;

import com.cmm.cvs2xml.util.StringManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.bean.DatosConcepto;
import com.cmm.cvs2xml.bean.LineaDatosConcepto;
import java.math.BigDecimal;
import java.math.RoundingMode;
import mx.bigdata.sat.cfdi.v33.schema.CTipoFactor;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.Impuestos;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.Impuestos.Traslados;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.Impuestos.Retenciones;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.Impuestos.Retenciones.Retencion;
/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 23/04/2014 05:08:09 PM
 */
public class CmmCvsConceptoUtils {

    public final static String idRegistro = "03";
    public final static String infoRegistro = "DATOS GENERALES DE CONCEPTO";
    
    //Aplica en este caso por que finaliza con un elemento REQUERIDO obligatorio
    private final static int noElementosEsperados = 26; 
    
    public static LineaDatosConcepto fillData(String elementoCfdi){
        LineaDatosConcepto lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosConcepto();
            DatosConcepto datosConcepto = new DatosConcepto();
            Concepto concepto = new Concepto();
            Impuestos impuesto=new Impuestos();
            Traslados translados=new Traslados();
            Retenciones retenidos=new Retenciones();
            
            Traslado trasladoIVA=new Traslado();
            Traslado trasladoIEPS=new Traslado();
            Retencion retencionIVA=new Retencion();
            Retencion retencionISR=new Retencion();
            
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
                        //Nombre - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE NOMBRE NULO O VACIO");
                        }
                        //datosConcepto.setNombre(str);
                        concepto.setDescripcion(str);
                        break;
                    case 2:
                        //Codigo - Opcional
                        /*if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CODIGO NULO O VACIO");
                        }*/
                        concepto.setNoIdentificacion(str);
                        break;
                    case 3:
                        //Descripcion - Opcional
                        datosConcepto.setNombre(str);
                        break;
                    case 4:
                        //Unidad de Medida - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            concepto.setUnidad(str);
                        }
                        break;
                    case 5:
                        //Precio unitario - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE PRECIO UNITARIO NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO PRECIO UNITARIO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        concepto.setValorUnitario(new BigDecimal(str));
                        break;
                    case 6:
                        //Cantidad - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CANTIDAD NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO CANTIDAD INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        concepto.setCantidad(new BigDecimal(str));
                        break;
                    case 7:
                        //Porcentaje descuento - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE PORCENTAJE DESCUENTO NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 5, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO PORCENTAJE DESCUENTO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        datosConcepto.setPorcentajeDescuento(new BigDecimal(str));
                        break;
                    case 8:
                        //Monto Descuento - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE MONTO DESCUENTO NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO MONTO DESCUENTO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        datosConcepto.setMontoDescuento(new BigDecimal(str));
                        concepto.setDescuento(new BigDecimal(str));
                        break;
                    case 9:
                        //Importe - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE IMPORTE NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO IMPORTE INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        concepto.setImporte(new BigDecimal(str));
                        break;
                    case 10:
                        //IVA porcentaje - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE PORCENTAJE IVA NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 5, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO PORCENTAJE IVA INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        datosConcepto.setIVAporcentaje(new BigDecimal(str));
                        trasladoIVA.setImpuesto("002");
                        break;
                    case 11:
                        //IVA Monto - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE MONTO IVA NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO MONTO IVA INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        datosConcepto.setIVAmonto(new BigDecimal(str));
                        trasladoIVA.setImporte(new BigDecimal(str));
                        
                        try{
                            trasladoIVA.setBase(concepto.getImporte().subtract(concepto.getDescuento()));
                        }catch(Exception e){
                            trasladoIVA.setBase(concepto.getImporte());
                        }
                        
                        break;
                    case 12:
                        //tasa cuota IVA - opcional
                        if(!"".equals(StringManage.getValidString(str))){
                            trasladoIVA.setTasaOCuota(new BigDecimal(str));
                        }
                        break;
                    case 13:
                        //Tipo de factor IEPS - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL TIPO DE FACTOR IVA NULO O VACIO");
                        }
                        trasladoIVA.setTipoFactor(CTipoFactor.fromValue(str));
                        break;
                    case 14:
                        //IEPS porcentaje - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE PORCENTAJE IEPS NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 5, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO PORCENTAJE IEPS INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        datosConcepto.setIEPSporcentaje(new BigDecimal(str));
                        
                        trasladoIEPS.setImpuesto("003");
                        break;
                    case 15:
                        //IEPS monto - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE MONTO IEPS NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO MONTO IEPS INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        datosConcepto.setIEPSmonto(new BigDecimal(str));
                        trasladoIEPS.setImporte(new BigDecimal(str));
                        try{
                            trasladoIEPS.setBase(concepto.getImporte().subtract(concepto.getDescuento()));
                        }catch(Exception e){
                            trasladoIEPS.setBase(concepto.getImporte());
                        }
                        break;
                    case 16:
                        //tasa cuota IEPS - opcional
                        if(!"".equals(StringManage.getValidString(str))){
                            trasladoIEPS.setTasaOCuota(new BigDecimal(str));
                        }
                        break;
                    case 17:
                        //Tipo de factor IEPS - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL TIPO DE FACTOR IVA NULO O VACIO");
                        }
                        trasladoIEPS.setTipoFactor(CTipoFactor.fromValue(str));
                        break;
                    case 18:
                        //IVA Retenido porcentaje - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE PORCENTAJE IVA RETENIDO NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 5, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO PORCENTAJE IVA RETENIDO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        datosConcepto.setIVARetenidoPorcentaje(new BigDecimal(str));
                        
                        retencionIVA.setImpuesto("002");
                        retencionIVA.setTasaOCuota(new BigDecimal("0.160000"));
                        break;
                    case 19:
                        //IVA Retenido monto - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE MONTO IVA RETENIDO NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO MONTO IVA RETENIDO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        datosConcepto.setIVARetenidoMonto(new BigDecimal(str));
                        retencionIVA.setImporte(new BigDecimal(str));
                        try{
                            retencionIVA.setBase(concepto.getImporte().subtract(concepto.getDescuento()));
                        }catch(Exception e){
                            retencionIVA.setBase(concepto.getImporte());
                        }
                        
                        break;
                    case 20:
                        //Tipo de factor retenido IVA - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL TIPO DE FACTOR IVA NULO O VACIO");
                        }
                        retencionIVA.setTipoFactor(CTipoFactor.fromValue(str));
                        break;
                    case 21:
                        //ISR porcentaje - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE PORCENTAJE ISR NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 5, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO PORCENTAJE ISR INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        datosConcepto.setISRPorcentaje(new BigDecimal(str));
                        
                        retencionISR.setImpuesto("001");
                        retencionISR.setTasaOCuota(new BigDecimal("0.000000"));
                        break;
                    case 22:
                        //ISR monto - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE MONTO ISR NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO MONTO ISR INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        datosConcepto.setIVARetenidoMonto(new BigDecimal(str));
                        retencionISR.setImporte(new BigDecimal(str));
                        try{
                            retencionISR.setBase(concepto.getImporte().subtract(concepto.getDescuento()));
                        }catch(Exception e){
                            retencionISR.setBase(concepto.getImporte());
                        }
                        break;
                    case 23:
                        //Tipo de factor retenido IVA - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EL TIPO DE FACTOR IVA NULO O VACIO");
                        }
                        retencionISR.setTipoFactor(CTipoFactor.fromValue(str));
                        break;
                    case 24:
                        // clave del servicio o producto - REQUERIDO
                        if(!"".equals(StringManage.getValidString(str))){
                            String serprod[]=str.split("-");
                            if(serprod.length>1){
                                str=serprod[0].trim();
                                String serpr="";
                                for(int i=1;i<serprod.length;i++){
                                    serpr+=serprod[i];
                                }
                                datosConcepto.setDescripcionServProd(serpr);
                                concepto.setClaveProdServ(str);
                            }else{
                                str=serprod[0].trim();
                                concepto.setClaveProdServ(str);
                            }
                            //throw new IllegalArgumentException("NO SE PERMITE LA CLAVE DEL PRODCUTO O SERVICIO NULO O VACIO");
                        }
                        break;
                    case 25:
                        //clave de la unidad - requerido
                        if(!"".equals(StringManage.getValidString(str))){
                            concepto.setClaveUnidad(str);
                            //throw new IllegalArgumentException("NO SE PERMITE LA CLAVE UNITARIA NULO O VACIO");
                        }
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            translados.getTraslado().add(trasladoIVA);
            translados.getTraslado().add(trasladoIEPS);
            retenidos.getRetencion().add(retencionIVA);
            retenidos.getRetencion().add(retencionISR);
            
            impuesto.setTraslados(translados);
            impuesto.setRetenciones(retenidos);
            
            concepto.setImpuestos(impuesto);
            
            datosConcepto.setConcepto(concepto);
            lineaDatos.setDatosConcepto(datosConcepto);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
