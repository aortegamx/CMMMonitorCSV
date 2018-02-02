/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.complementos.nomina.utils;

import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.common.nomina12.schema.Nomina;

/**
 *
 * @author leonardo
 */
public class CmmCvsNominaOtrosPagosUtils {
    
    public final static String IDREGISTRO = "25";
    public final static String INFO_REGISTRO = "CONCEPTOS DE OTROS PAGOS POR NÓMINA";
    private final static int NO_ELEMENTOS_ESPERADOS = 6;
    
    public static Nomina.OtrosPagos.OtroPago fillData(String elementoCfdi){
        Nomina.OtrosPagos.OtroPago otroPago = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != NO_ELEMENTOS_ESPERADOS){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + NO_ELEMENTOS_ESPERADOS + " elementos, para el identificador " + IDREGISTRO  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            otroPago = new Nomina.OtrosPagos.OtroPago();
            
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
                        if (!IDREGISTRO.equals(StringManage.getValidString(str))) {
                            throw new IllegalArgumentException("IDENTIFICADOR DE REGISTRO NO VALIDO, DEBE SER ESTRICTAMENTE \""+IDREGISTRO+"\" PARA " + INFO_REGISTRO);
                        }
                        break;
                    case 1:
                        //Tipo de Otro Pago - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TIPO DE OTRO PAGO NULO O VACIO");
                        }
                        if (!gc.validaConExpresionRegular(str, "^([0-9]{3})$")){
                            throw new IllegalArgumentException("DATO TIPO DE OTRO PAGO INCORRECTO, DEBE SER UN ENTERO DE 3 DIGITOS CONFORME AL CATÁLOGO DEL SAT APLICABLE.");
                        }
                        otroPago.setTipoOtroPago(str);
                        break;
                    case 2:
                        //Codigo de Otro Pago - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CODIGO DE OTRO PAGO NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 3, 15)){
                            throw new IllegalArgumentException("DATO CÓDIGO DE OTRO PAGO INCORRECTO, DEBE TENER MÍNIMO 3 CARACTERES Y MÁXIMO 15.");
                        }
                        otroPago.setClave(str);
                        break;
                    case 3:
                        //Descripción - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DESCRIPCIÓN OTRO PAGO NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 100)){
                            throw new IllegalArgumentException("DATO DESCRIPCIÓN OTRO PAGO INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 100 CARACTERES.");
                        }
                        otroPago.setConcepto(str);
                        break;
                    case 4:
                        //Importe Gravado - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE IMPORTE OTRO PAGO NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO IMPORTE OTRO PAGO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        otroPago.setImporte(new BigDecimal(str));
                        
                        break;
                    case 5:
                        //Importe Exento - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE IMPORTE SUBSIDIO AL EMPLEO NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO IMPORTE SUBSIDIO AL EMPLEO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                       // deduccion.setImporteExento(new BigDecimal(str));
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + IDREGISTRO);
        }
        
        return otroPago;
    }
    
}
