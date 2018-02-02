/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.complementos.dividendos.utils;

import com.cmm.cvs2xml.retenciones.complementos.dividendos.bean.LineaDatosDividendoOUtilidad;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.retencion.common.dividendos.schema.Dividendos;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 3/03/2015 10:55:30 AM
 */
public class CmmCvsDividendosDivOUtil {

    public final static String idRegistro = "00506";
    public final static String infoRegistro = "INFORMACIÓN DE DIVIDENDOS O UTILIDADES PARA COMPLEMENTO DIVIDENDOS";
    //private final static int noElementosEsperados = 6;
    
    public static LineaDatosDividendoOUtilidad fillData(String elementoCfdi){
        LineaDatosDividendoOUtilidad lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales = 6;
            
            lineaDatos = new LineaDatosDividendoOUtilidad();
            Dividendos.DividOUtil dividOUtil = new Dividendos.DividOUtil();
            
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
                        //CLAVE - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO CLAVE NULO O VACIO");
                        }
                        String valoresValidos = "01,02,03,04,05,06";
                        String regExp = valoresValidos.replaceAll(",", "|");
                        if (!gc.validaConExpresionRegular(str, regExp)){
                            throw new IllegalArgumentException("DATO CLAVE INVÁLIDO, DEBE PERTENECER A UNO DE: " + valoresValidos);
                        }
                        dividOUtil.setCveTipDivOUtil(str);
                        break;
                    case 2:
                        //Importe ISR Nacional - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO IMPORTE ISR NACIONAL NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO IMPORTE ISR NACIONAL INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        dividOUtil.setMontISRAcredRetMexico(new BigDecimal(str));
                        break;
                    case 3:
                        //Importe ISR Extranjero - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO IMPORTE ISR EXTRANJERO NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO IMPORTE ISR EXTRANJERO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        dividOUtil.setMontISRAcredRetExtranjero(new BigDecimal(str));
                        break;
                    case 4:
                        //Monto Retención Extranjero - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO MONTO RETENCIÓN EXTRANJERO INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            dividOUtil.setMontRetExtDivExt(new BigDecimal(str));
                        }
                        break;
                    case 5:
                        //Tipo Sociedad - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TIPO DE SOCIEDAD NULO O VACIO");
                        }
                        String valoresValidos2 = "Sociedad Nacional,Sociedad Extranjera";
                        String regExp2 = valoresValidos2.replaceAll(",", "|");
                        if (!gc.validaConExpresionRegular(str, regExp2)){
                            throw new IllegalArgumentException("DATO TIPO DE SOCIEDAD INVÁLIDO, DEBE PERTENECER A UNO DE: " + valoresValidos2);
                        }
                        dividOUtil.setTipoSocDistrDiv(str);
                        break;
                    case 6:
                        //Monto ISR Nacional - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO MONTO ISR NACIONAL INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            dividOUtil.setMontISRAcredNal(new BigDecimal(str));
                        }
                        break;
                    case 7:
                        //Dividendo Acumulable Nacional - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO DIVIDENDO ACUMULABLE NACIONAL INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            dividOUtil.setMontDivAcumNal(new BigDecimal(str));
                        }
                        break;
                    case 8:
                        //Dividendo Acumulable Extranjero - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO DIVIDENDO ACUMULABLE EXTRANJERO INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            dividOUtil.setMontDivAcumExt(new BigDecimal(str));
                        }
                        break;
                        
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            lineaDatos.setDividOUtil(dividOUtil);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
