/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.complementos.arrendamientoenfideicomiso.utils;

import com.cmm.cvs2xml.retenciones.complementos.arrendamientoenfideicomiso.bean.LineaDatosArrendamientoEnFideicomisoGeneral;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.retencion.common.arrendamientoenfideicomiso.schema.Arrendamientoenfideicomiso;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 27/02/2015 01:20:10 PM
 */
public class CmmCvsArrendamientoEnFideicomisoGral {

    public final static String idRegistro = "00505";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS GENERALES COMPLEMENTO ARRENDAMIENTO EN FIDEICOMISO";
    //private final static int noElementosEsperados = 2;
    
    public static LineaDatosArrendamientoEnFideicomisoGeneral fillData(String elementoCfdi){
        LineaDatosArrendamientoEnFideicomisoGeneral lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales = 6;
            
            lineaDatos = new LineaDatosArrendamientoEnFideicomisoGeneral();
            Arrendamientoenfideicomiso arrendamientoenfideicomiso = new Arrendamientoenfideicomiso();
            
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
                        //Importe Pago Fiduciario - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO IMPORTE PAGO FIDUCIARIO NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO IMPORTE PAGO FIDUCIARIO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        arrendamientoenfideicomiso.setPagProvEfecPorFiduc(new BigDecimal(str));
                        break;
                    case 2:
                        //Importe Rendimientos - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO IMPORTE RENDIMIENTOS NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO IMPORTE RENDIMIENTOS INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        arrendamientoenfideicomiso.setRendimFideicom(new BigDecimal(str));
                        break;
                    case 3:
                        //Importe Deducciones - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO IMPORTE DEDUCCIONES NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO IMPORTE DEDUCCIONES INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        arrendamientoenfideicomiso.setDeduccCorresp(new BigDecimal(str));
                        break;
                    case 4:
                        //Monto Total Retención - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO MONTO TOTAL RETENCION INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            arrendamientoenfideicomiso.setMontTotRet(new BigDecimal(str));
                        }
                        break;
                    case 5:
                        //Monto Resultado FIBRAS - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO MONTO RESULTADO FIBRAS INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            arrendamientoenfideicomiso.setMontResFiscDistFibras(new BigDecimal(str));
                        }
                        break;
                    case 6:
                        // Monto Otros Conceptos - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO MONTO OTROS CONCEPTOS INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            arrendamientoenfideicomiso.setMontOtrosConceptDistr(new BigDecimal(str));
                        }
                        break;
                    case 7:
                        // Descripción Otros Conceptos - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isValidString(str, 1, 500)){
                                throw new IllegalArgumentException("DATO DESCRIPCIÓN OTROS CONCEPTOS INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE TENER AL MENOS 1 CARACTER, MÁXIMO 500.");
                            }
                            arrendamientoenfideicomiso.setDescrMontOtrosConceptDistr(str);
                        }
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            lineaDatos.setArrendamientoenfideicomiso(arrendamientoenfideicomiso);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
