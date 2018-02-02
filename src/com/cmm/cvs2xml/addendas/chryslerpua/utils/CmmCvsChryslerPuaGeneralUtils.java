/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.chryslerpua.utils;

import com.cmm.cvs2xml.addendas.chryslerpua.bean.LineaDatosChryslerGenerales;
import com.cmm.cvs2xml.util.DateManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.addenda.chryslerpua.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 06/02/2015 10:36:56 AM
 */
public class CmmCvsChryslerPuaGeneralUtils {

    public final static String idRegistro = "00221";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS GENERALES PARA ADDENDA CHRYSLER PUA V1.0";
    //private final static int noElementosEsperados = 4;
    
    public static LineaDatosChryslerGenerales fillData(String elementoCfdi) throws Exception{
        LineaDatosChryslerGenerales lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            String formatoFecha = "yyyy-MM-dd";
            int maxDecimales;
            
            lineaDatos = new LineaDatosChryslerGenerales();
            Factura factura = new Factura();
            
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
                            //Tipo Documento - REQUERIDO
                            if (!gc.validaConExpresionRegular(str, "PUA")){
                                throw new IllegalArgumentException("DATO TIPO DOCUMENTO INCORRECTO, REQUERIDO, DEBE CORRESPONDER A UNO DE PUA.");
                            }
                            factura.setTipoDocumento(str);
                            break;
                        case 2:
                            //Tipo Documento Fiscal - REQUERIDO
                            if (!gc.validaConExpresionRegular(str, "FA|CA|CR")){
                                throw new IllegalArgumentException("DATO TIPO DOCUMENTO FISCAL INCORRECTO, REQUERIDO, DEBE CORRESPONDER A UNO DE FA, CA, CR.");
                            }
                            factura.setTipoDocumentoFiscal(str);
                            break;
                        case 3:
                            //Serie - Opcional
                            if (StringManage.getValidString(str).length()>0){
                                if (!gc.isValidString(str, 1, 15)){
                                    throw new IllegalArgumentException("DATO SERIE INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MAXIMO 15 CARACTERES.");
                                }
                                factura.setSerie(str);
                            }
                            break;
                        case 4:
                            //FOLIO FISCAL - REQUERIDO
                            if (!gc.isValidString(str, 1, 15)){
                                throw new IllegalArgumentException("DATO FOLIO FISCAL INCORRECTO, REQUERIDO, DEBE TENER MAXIMO 15 CARACTERES.");
                            }
                            factura.setFolioFiscal(str);
                            break;
                        case 5:
                            //FECHA - REQUERIDO
                            if ("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE FECHA NULO O VACIO");
                            }
                            if (!gc.isDate(str, formatoFecha)){
                                throw new IllegalArgumentException("DATO FECHA INCORRECTO, DEBE TENER EL FORMATO " + formatoFecha);
                            }
                            factura.setFecha(DateManage.dateToXMLGregorianCalendar2(DateManage.stringToDate(str, formatoFecha)));
                            break;
                        case 6:
                            //MONTO TOTAL - REQUERIDO
                            maxDecimales = 2;
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales) ){
                                throw new IllegalArgumentException("DATO MONTO TOTAL INCORRECTO, REQUERIDO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            factura.setMontoTotal(new BigDecimal(str));
                            break;
                        case 7:
                            //REFERENCIA DE PROVEEDOR - REQUERIDO
                            if (StringManage.getValidString(str).length()>0){
                                if (!gc.isValidString(str, 1, 15)){
                                    throw new IllegalArgumentException("DATO REFERENCIA PROVEEDOR INCORRECTO, OPCIONAL, DEBE TENER MAXIMO 15 CARACTERES.");
                                }
                                factura.setReferenciaProveedor(str);
                            }
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setFacturaChrysler(factura);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
