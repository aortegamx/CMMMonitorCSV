/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.utils;

import com.cmm.cvs2xml.util.StringManage;
import com.cmm.cvs2xml.util.DateManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.bean.LineaDatosConcepto;
import java.math.BigDecimal;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.Parte.InformacionAduanera;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 06/05/2014 05:08:09 PM
 */
public class CmmCvsParteConceptoUtils {

    public final static String idRegistro = "06";
    public final static String infoRegistro = "INFORMACIÓN DE PARTES PARA LOS CONCEPTOS";
    
    public static LineaDatosConcepto fillData(String elementoCfdi, LineaDatosConcepto lineaDatos) throws Exception{
        
        if (lineaDatos==null){
            throw new Exception("El objeto LineaDatosConcepto recibido para completar su " + infoRegistro + " tiene un valor nulo.");
        }
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            String formatoFecha = "yyyy-MM-dd";
            
            Comprobante.Conceptos.Concepto.Parte parteConcepto = new Comprobante.Conceptos.Concepto.Parte();
            InformacionAduanera informacionAduanera = null;
            
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
                        //Cantidad - REQUERIDO
                        maxDecimales = 3; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CANTIDAD NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO CANTIDAD INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        parteConcepto.setCantidad(new BigDecimal(str));
                        break;
                    case 2:
                        //Unidad de Medida - Opcional
                        parteConcepto.setUnidad(str);
                        break;
                    case 3:
                        //Codigo - Opcional
                        parteConcepto.setNoIdentificacion(str);
                        break;
                    case 4:
                        //Descripcion - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DESCRIPCION NULO O VACIO");
                        }
                        parteConcepto.setDescripcion(str);
                        break;
                    case 5:
                        //Precio Unitario - Opcional
                        if (!StringManage.getValidString(str).equals("")){
                            maxDecimales = 6;
                            if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO PRECIO UNITARIO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            parteConcepto.setValorUnitario(new BigDecimal(str));
                        }
                        break;
                    case 6:
                        //Importe - Opcional
                        if (!StringManage.getValidString(str).equals("")){
                            maxDecimales = 6;
                            if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO IMPORTE INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            parteConcepto.setImporte(new BigDecimal(str));
                        }
                        break;
                    case 7:
                        //Clave del producto o servicio
                        if("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE LA CLAVE DEL PRODUCTO O SERVICIO NULO O VACIO");
                        }
                        parteConcepto.setClaveProdServ(str);
                        break;
                    case 8:
                        //Número - REQUERIDO
                        if (!StringManage.getValidString(str).equals("")){
                            informacionAduanera = new InformacionAduanera();
                            if(!gc.validaConExpresionRegular(str, "[0-9]{2}  [0-9]{2}  [0-9]{4}  [0-9]{7}")){
                                throw new IllegalArgumentException("EL NUMERO DE PEDIMENTO DEBE CONTENER 21 DIGITOS");
                            }
                            informacionAduanera.setNumeroPedimento(str);
                       }
                        break;
                    /*case 9:
                        //Fecha - REQUERIDO
                        if (informacionAduanera!=null){
                            if ("".equals(StringManage.getValidString(str))){
                                throw new IllegalArgumentException("NO SE PERMITE FECHA NULO O VACIO");
                            }
                            if (!gc.isDate(str, formatoFecha)){
                                throw new IllegalArgumentException("DATO FECHA INCORRECTO, DEBE TENER EL FORMATO " + formatoFecha);
                            }
                            informacionAduanera.setFecha(DateManage.stringToDate(str, formatoFecha));
                        }
                        break;
                    case 10:
                        //Aduana - Opcional
                        if (informacionAduanera!=null)
                            informacionAduanera.setAduana(str);
                        break;*/
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            if (informacionAduanera!=null){
                parteConcepto.getInformacionAduanera().add(informacionAduanera);
            }
            lineaDatos.getDatosConcepto().getConcepto().getParte().add(parteConcepto);
            
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
