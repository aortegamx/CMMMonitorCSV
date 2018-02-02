/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.vwpmt.utils;

import com.cmm.cvs2xml.addendas.vwpmt.bean.LineaDatosVWParte;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import java.math.BigInteger;
import mx.bigdata.sat.addenda.vwpmt.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/02/2015 09:50:11 AM
 */
public class CmmCvsVwPmtParteUtils {

    public final static String idRegistro = "00220";
    public final static String infoRegistro = "INFORMACIÓN DE PARTE PARA ADDENDA VW PMT V1.0";
    //private final static int noElementosEsperados = 3;
    
    public static LineaDatosVWParte fillData(String elementoCfdi){
        LineaDatosVWParte lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosVWParte();
            Factura.Partes.Parte parte = new Factura.Partes.Parte();
            
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
                            if (!gc.isValidString(str, 10, 12) ){
                                throw new IllegalArgumentException("DATO ORDEN DE COMPRA REFERENCIA INCORRECTO, REQUERIDO, DEBE TENER MÍNIMO 10 CARACTERES, MÁXIMO 12");
                            }
                            Factura.Partes.Parte.Referencias referencias = new Factura.Partes.Parte.Referencias();
                            referencias.setOrdenCompra(str);
                            parte.setReferencias(referencias);
                            break;
                        case 2:
                            //Posicion - Opcional
                            if (StringManage.getValidString(str).length()>0){
                                if (!gc.isNumeric(str, 0, 16) ){
                                    throw new IllegalArgumentException("DATO POSICION INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE SER UN VALOR NUMERICO ENTERO.");
                                }
                                parte.setPosicion(new BigInteger(str));
                            }
                            break;
                        case 3:
                            //Numero de material - REQUERIDO
                            if (!gc.isValidString(str, 1, 18) ){
                                throw new IllegalArgumentException("DATO NUMERO DE MATERIAL INCORRECTO, REQUERIDO, DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 18");
                            }
                            parte.setNumeroMaterial(str);
                            break;
                        case 4:
                            //Descripcion de material - REQUERIDO
                            if (!gc.isValidString(str, 1, 40) ){
                                throw new IllegalArgumentException("DATO DESCRIPCION DE MATERIAL INCORRECTO, REQUERIDO, DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 40");
                            }
                            parte.setDescripcionMaterial(str);
                            break;
                        case 5:
                            //Cantidad de material - REQUERIDO
                            maxDecimales = 4;
                            if (!gc.isDecimal(str, 1, 17, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO CANTIDAD DE MATERIAL INCORRECTO, REQUERIDO, DEBE SER UN VALOR DECIMAL Y TENER MÁXIMO 4 POSICIONES DESPUÉS DEL PUNTO");
                            }
                            parte.setCantidadMaterial(new BigDecimal(str));
                            break;
                        case 6:
                            //Unidad de medida - REQUERIDO
                            if (!gc.isValidString(str, 1, 3) ){
                                throw new IllegalArgumentException("DATO UNIDAD DE MEDIDA INCORRECTO, REQUERIDO, DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 3");
                            }
                            parte.setUnidadMedida(str);
                            break;
                        case 7:
                            //Precio unitario - REQUERIDO
                            maxDecimales = 5;
                            if (!gc.isDecimal(str, 1, 20, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO PRECIO UNITARIO INCORRECTO, REQUERIDO, DEBE SER UN VALOR DECIMAL Y TENER MÁXIMO 5 POSICIONES DESPUÉS DEL PUNTO");
                            }
                            parte.setPrecioUnitario(new BigDecimal(str));
                            break;
                        case 8:
                            //Monto de linea - REQUERIDO
                            maxDecimales = 2;
                            if (!gc.isDecimal(str, 1, 20, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO MONTO DE LINEA INCORRECTO, REQUERIDO, DEBE SER UN VALOR DECIMAL Y TENER MÁXIMO 2 POSICIONES DESPUÉS DEL PUNTO");
                            }
                            parte.setMontoLinea(new BigDecimal(str));
                            break;
                        case 9:
                            //Peso bruto - Opcional
                            if (StringManage.getValidString(str).length()>0){
                                maxDecimales = 2;
                                if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                                    throw new IllegalArgumentException("DATO PESO BRUTO INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE SER UN VALOR DECIMAL Y TENER MÁXIMO 2 POSICIONES DESPUÉS DEL PUNTO");
                                }
                                parte.setPesoBruto(new BigDecimal(str));
                            }
                            break;
                        case 10:
                            //Peso neto - Opcional
                            if (StringManage.getValidString(str).length()>0){
                                maxDecimales = 2;
                                if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                                    throw new IllegalArgumentException("DATO PESO NETO INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE SER UN VALOR DECIMAL Y TENER MÁXIMO 2 POSICIONES DESPUÉS DEL PUNTO");
                                }
                                parte.setPesoNeto(new BigDecimal(str));
                            }
                            break;
                        case 11:
                            //Nota 1 - Opcional
                            if (StringManage.getValidString(str).length()>0){
                               if (!gc.isValidString(str, 1, 254) ){
                                    throw new IllegalArgumentException("DATO NOTA 1 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 254 CARACTERES");
                                } 
                               parte.getNota().add(str);
                            }
                            break;
                        case 12:
                            //Nota 2 - Opcional
                            if (StringManage.getValidString(str).length()>0){
                               if (!gc.isValidString(str, 1, 254) ){
                                    throw new IllegalArgumentException("DATO NOTA 2 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 254 CARACTERES");
                                } 
                               parte.getNota().add(str);
                            }
                            break;
                        case 13:
                            //Nota 3 - Opcional
                            if (StringManage.getValidString(str).length()>0){
                               if (!gc.isValidString(str, 1, 254) ){
                                    throw new IllegalArgumentException("DATO NOTA 3 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 254 CARACTERES");
                                } 
                               parte.getNota().add(str);
                            }
                            break;
                        case 14:
                            //Nota 4 - Opcional
                            if (StringManage.getValidString(str).length()>0){
                               if (!gc.isValidString(str, 1, 254) ){
                                    throw new IllegalArgumentException("DATO NOTA 4 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 254 CARACTERES");
                                } 
                               parte.getNota().add(str);
                            }
                            break;
                        case 15:
                            //Nota 5 - Opcional
                            if (StringManage.getValidString(str).length()>0){
                               if (!gc.isValidString(str, 1, 254) ){
                                    throw new IllegalArgumentException("DATO NOTA 5 INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÁXIMO 254 CARACTERES");
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
