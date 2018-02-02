/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.vwpmt.utils;

import com.cmm.cvs2xml.addendas.vwpmt.bean.LineaDatosVWMedidas;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.addenda.vwpmt.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/02/2015 09:50:11 AM
 */
public class CmmCvsVwPmtMedidasUtils {

    public final static String idRegistro = "00216";
    public final static String infoRegistro = "INFORMACIÓN DE MEDIDAS PARA ADDENDA VW PMT V1.0";
    //private final static int noElementosEsperados = 4;
    
    public static LineaDatosVWMedidas fillData(String elementoCfdi){
        LineaDatosVWMedidas lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosVWMedidas();
            Factura.Medidas medidas = new Factura.Medidas();
            
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
                            //Peso Bruto - OPCIONAL
                            if (StringManage.getValidString(str).length()>0){
                                maxDecimales = 2;
                                if (!gc.isDecimal(str, 1, 10, 0, maxDecimales) ){
                                    throw new IllegalArgumentException("DATO PESO BRUTO INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                                }
                                medidas.setPesoBruto(new BigDecimal(str));
                            }
                            break;
                        case 2:
                            //Peso Neto - OPCIONAL
                            if (StringManage.getValidString(str).length()>0){
                                maxDecimales = 2;
                                if (!gc.isDecimal(str, 1, 10, 0, maxDecimales) ){
                                    throw new IllegalArgumentException("DATO PESO NETO INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                                }
                                medidas.setPesoNeto(new BigDecimal(str));
                            }
                            break;
                        case 3:
                            //Volumen - OPCIONAL
                            if (StringManage.getValidString(str).length()>0){
                                maxDecimales = 2;
                                if (!gc.isDecimal(str, 1, 10, 0, maxDecimales) ){
                                    throw new IllegalArgumentException("DATO VOLUMEN INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                                }
                                medidas.setVolumen(new BigDecimal(str));
                            }
                            break;
                        case 4:
                            //NUMERO DE PIEZAS - OPCIONAL
                            if (StringManage.getValidString(str).length()>0){
                                maxDecimales = 2;
                                if (!gc.isDecimal(str, 1, 10, 0, maxDecimales) ){
                                    throw new IllegalArgumentException("DATO NUMERO DE PIEZAS INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                                }
                                medidas.setNumeroPiezas(new BigDecimal(str));
                            }
                            break;
                        case 5:
                            //Descripcion - Opcional
                            if (StringManage.getValidString(str).length()>0){
                                if (!gc.isValidString(str, 1, 60)){
                                    throw new IllegalArgumentException("DATO DESCRIPCION INCORRECTO, OPCIONAL, SI SE EXPRESA DEBE TENER MÍNIMO 1 CARACTERES, MÁXIMO 60.");
                                }
                                medidas.setDescripcion(str);
                                break;
                            }
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
            lineaDatos.setMedidas(medidas);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
