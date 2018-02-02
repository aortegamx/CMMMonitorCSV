/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.utils;

import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.bean.DatosAuxiliarCtasCuenta;
import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.bean.LineaDatosAuxiliarCtasCuenta;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.econtabilidad.v13.schema.ctas.AuxiliarCtas;

/**
 *
 * @author leonardo
 */
public class CmmCvsAuxiliarCtasCuentaUtils {
    
    public final static String idRegistro = "131";
    public final static String infoRegistro = "INFORMACIÓN PARA CUENTA DE AUXILIAR CUENTA";
    private final static int noElementosEsperados = 5;
    
    public static LineaDatosAuxiliarCtasCuenta fillData(String elementoCfdi){
        LineaDatosAuxiliarCtasCuenta lineaDatosAuxiliarCtasCuenta = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            int maxDecimales;
            GenericValidator gc = new GenericValidator();
            String formatoFecha = "yyyy-MM-dd";
            
            lineaDatosAuxiliarCtasCuenta = new LineaDatosAuxiliarCtasCuenta();
            DatosAuxiliarCtasCuenta datosAuxiliarCtasCuenta = new DatosAuxiliarCtasCuenta();
            AuxiliarCtas.Cuenta cuenta = new AuxiliarCtas.Cuenta();
            //Polizas.Poliza poliza = new Polizas.Poliza();
            
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
                        //NumCuenta - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE NUMERO DE CUENTA NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 100)){
                            throw new IllegalArgumentException("DATO NUMERO DE CUENTA INCORRECTO, DEBE SER UN CHAR ENTRE 1 Y 100.");
                        }
                        /*if (!gc.validaConExpresionRegular(str, "^[1-3]$")){
                            throw new IllegalArgumentException("DATO TIPO DE PÓLIZA INCORRECTO, DEBE SER ALGUNO DE LOS VALORES: 1- Ingresos, 2 - Egresos o 3 - Diario.");
                        }*/
                        cuenta.setNumCta((str));
                        break;
                    case 2:
                        //DesCta - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DESCRIPCION NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 100)){
                            throw new IllegalArgumentException("DATO DESCRIPCION ES INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 100 CARACTERES.");
                        }
                        cuenta.setDesCta(str);
                        break;
                    case 3:
                        //SaldoIni - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE SALDO INICIAL NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 14, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO SALDO INICIAL INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        cuenta.setSaldoIni(new BigDecimal(str));
                        break;
                    case 4:
                        //SaldoFin - REQUERIDO
                        maxDecimales = 2; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE SALDO FINAL NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 14, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO SALDO FINAL INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        cuenta.setSaldoFin(new BigDecimal(str));                        
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            datosAuxiliarCtasCuenta.setCuenta(cuenta);
            lineaDatosAuxiliarCtasCuenta.setDatosAuxiliarCtasCuenta(datosAuxiliarCtasCuenta);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatosAuxiliarCtasCuenta;
    }
    
}