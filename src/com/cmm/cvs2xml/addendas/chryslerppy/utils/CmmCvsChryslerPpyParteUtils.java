/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.chryslerppy.utils;

import com.cmm.cvs2xml.addendas.chryslerppy.bean.LineaDatosChryslerParte;
import com.cmm.cvs2xml.util.DateManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.addenda.chryslerppy.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 19/02/2016 09:50:11 AM
 */
public class CmmCvsChryslerPpyParteUtils {

    public final static String idRegistro = "00256";
    public final static String infoRegistro = "INFORMACIÓN DE PART PARA ADDENDA CHRYSLER PPY V1.0";
    //private final static int noElementosEsperados = 3;
    
    public static LineaDatosChryslerParte fillData(String elementoCfdi) throws Exception{
        LineaDatosChryslerParte lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            String formatoFecha = "yyyy-MM-dd";
            int maxDecimales;
            
            lineaDatos = new LineaDatosChryslerParte();
            Factura.Partes.Part parte = new Factura.Partes.Part();
            
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
                            //Orden de compra Referencia - REQUERIDO
                            if (!gc.isValidString(str, 1, 10) ){
                                throw new IllegalArgumentException("DATO ORDEN DE COMPRA REFERENCIA INCORRECTO, REQUERIDO, DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 10");
                            }
                            Factura.Partes.Part.References referencias = new Factura.Partes.Part.References();
                            referencias.setOrdenCompra(str);
                            parte.setReferences(referencias);
                            break;
                        case 2:
                            //Release requisicion Referencia - REQUERIDO
                            if (!gc.isValidString(str, 1, 11) ){
                                throw new IllegalArgumentException("DATO RELEASE REQUISICION REFERENCIA INCORRECTO, REQUERIDO, DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 11");
                            }
                            if (parte.getReferences()!=null){
                                parte.getReferences().setReleaseRequisicion(str);
                            }
                            break;
                        case 3:
                            //Ammendment Referencia - REQUERIDO
                            if (!gc.isValidString(str, 1, 10) ){
                                throw new IllegalArgumentException("DATO AMMENDMENT REFERENCIA INCORRECTO, REQUERIDO, DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 10");
                            }
                            if (parte.getReferences()!=null){
                                parte.getReferences().setAmmendment(str);
                            }
                            break;
                        case 4:
                            //Packing list Referencia - OPCIONAL
                            if (StringManage.getValidString(str).length()>0){
                                if (!gc.isValidString(str, 1, 15) ){
                                    throw new IllegalArgumentException("DATO PACKING LIST REFERENCIA INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 15");
                                }
                                if (parte.getReferences()!=null){
                                    parte.getReferences().setPackingList(str);
                                }
                            }
                            break;
                        case 5:
                            //Numero - REQUERIDO
                            if (StringManage.getValidString(str).length()>0){
                                if (!gc.isValidString(str, 1, 30) ){
                                    throw new IllegalArgumentException("DATO NUMERO DE PARTE DE LA ORDEN INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 30");
                                }
                                parte.setNumero(str);
                            }
                            break;
                        case 6:
                            //Cantidad - REQUERIDO
                            maxDecimales = 4;
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO CANTIDAD DE PARTES INCORRECTO, REQUERIDO, DEBE SER UN VALOR DECIMAL Y TENER MÁXIMO 4 POSICIONES DESPUÉS DEL PUNTO");
                            }
                            parte.setCantidad(new BigDecimal(str));
                            break;
                        case 7:
                            //Unidad de medida - REQUERIDO
                            if (!gc.isValidString(str, 1, 3) ){
                                throw new IllegalArgumentException("DATO UNIDAD DE MEDIDA INCORRECTO, REQUERIDO, DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 3");
                            }
                            parte.setUnidadDeMedida(str);
                            break;
                        case 8:
                            //Precio unitario - REQUERIDO
                            maxDecimales = 4;
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO PRECIO UNITARIO INCORRECTO, REQUERIDO, DEBE SER UN VALOR DECIMAL Y TENER MÁXIMO 4 POSICIONES DESPUÉS DEL PUNTO");
                            }
                            parte.setPrecioUnitario(new BigDecimal(str));
                            break;
                        case 9:
                            //Monto de linea - REQUERIDO
                            maxDecimales = 2;
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO MONTO DE LINEA INCORRECTO, REQUERIDO, DEBE SER UN VALOR DECIMAL Y TENER MÁXIMO 2 POSICIONES DESPUÉS DEL PUNTO");
                            }
                            parte.setMontoDeLinea(new BigDecimal(str));
                            break;
                        case 10:
                            //Fecha de Recibo - Opcional
                            if (StringManage.getValidString(str).length()>0){
                                if ("".equals(StringManage.getValidString(str))){
                                    throw new IllegalArgumentException("NO SE PERMITE FECHA DE RECIBO NULO O VACIO");
                                }
                                if (!gc.isDate(str, formatoFecha)){
                                    throw new IllegalArgumentException("DATO FECHA DE RECIBO INCORRECTO, DEBE TENER EL FORMATO " + formatoFecha);
                                }
                                parte.setFechaRecibo(DateManage.dateToXMLGregorianCalendar2(DateManage.stringToDate(str, formatoFecha)));
                            }
                            break;
                        case 11:
                            //Nota 1 - Opcional
                            if (StringManage.getValidString(str).length()>0){
                               if (!gc.isValidString(str, 1, 200) ){
                                    throw new IllegalArgumentException("DATO NOTA 1 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 200 CARACTERES");
                                } 
                               parte.getNota().add(str);
                            }
                            break;
                        case 12:
                            //Nota 2 - Opcional
                            if (StringManage.getValidString(str).length()>0){
                               if (!gc.isValidString(str, 1, 200) ){
                                    throw new IllegalArgumentException("DATO NOTA 2 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 200 CARACTERES");
                                } 
                               parte.getNota().add(str);
                            }
                            break;
                        case 13:
                            //Nota 3 - Opcional
                            if (StringManage.getValidString(str).length()>0){
                               if (!gc.isValidString(str, 1, 200) ){
                                    throw new IllegalArgumentException("DATO NOTA 3 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 200 CARACTERES");
                                } 
                               parte.getNota().add(str);
                            }
                            break;
                        case 14:
                            //Nota 4 - Opcional
                            if (StringManage.getValidString(str).length()>0){
                               if (!gc.isValidString(str, 1, 200) ){
                                    throw new IllegalArgumentException("DATO NOTA 4 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 200 CARACTERES");
                                } 
                               parte.getNota().add(str);
                            }
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setParte(parte);
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}