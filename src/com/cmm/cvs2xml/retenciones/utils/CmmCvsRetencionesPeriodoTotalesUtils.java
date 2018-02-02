/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.utils;

import com.cmm.cvs2xml.retenciones.bean.LineaDatosPeriodoTotales;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import mx.bigdata.sat.retencion.v1.schema.Retenciones;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 19/02/2015 10:36:56 AM
 */
public class CmmCvsRetencionesPeriodoTotalesUtils {

    public final static String idRegistro = "00502";
    public final static String infoRegistro = "INFORMACIÓN DE PERIODOS Y TOTALES PARA RETENCIONES";
    private final static int noElementosEsperados = 8;
    
    public static LineaDatosPeriodoTotales fillData(String elementoCfdi){
        LineaDatosPeriodoTotales lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales = 6;
            
            lineaDatos = new LineaDatosPeriodoTotales();
            Retenciones.Periodo periodo = new Retenciones.Periodo();
            Retenciones.Totales totales = new Retenciones.Totales();
            
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
                            //Mes inicial - REQUERIDO
                            if ("".equals(StringManage.getValidString(str))) {
                                throw new IllegalArgumentException("NO SE PERMITE MES INICIAL NULO O VACIO");
                            }
                            if (!gc.isNumeric(str, 1, 2)){
                                throw new IllegalArgumentException("DATO MES INICIAL INCORRECTO, DEBE SER UN VALOR ENTERO DE 2 POSICIONES MÁXIMO.");
                            }
                            int mesIni = Integer.parseInt(str);
                            if (mesIni<1 || mesIni>12){
                                throw new IllegalArgumentException("DATO MES INICIAL INCORRECTO, DEBE SER UN NUMERO VÁLIDO DE MES (1-12).");
                            }
                            periodo.setMesIni(mesIni);
                            break;
                        case 2:
                            //Mes final - REQUERIDO
                            if ("".equals(StringManage.getValidString(str))) {
                                throw new IllegalArgumentException("NO SE PERMITE MES FINAL NULO O VACIO");
                            }
                            if (!gc.isNumeric(str, 1, 2)){
                                throw new IllegalArgumentException("DATO MES FINAL INCORRECTO, DEBE SER UN VALOR ENTERO DE 2 POSICIONES MÁXIMO.");
                            }
                            int mesFin = Integer.parseInt(str);
                            if (mesFin<1 || mesFin>12){
                                throw new IllegalArgumentException("DATO MES FINAL INCORRECTO, DEBE SER UN NUMERO VÁLIDO DE MES (1-12).");
                            }
                            periodo.setMesFin(mesFin);
                            break;
                        case 3:
                            //Ejercicio Fiscal - REQUERIDO
                            if ("".equals(StringManage.getValidString(str))) {
                                throw new IllegalArgumentException("NO SE PERMITE EJERCICIO FISCAL NULO O VACIO");
                            }
                            if (!gc.isNumeric(str, 1, 4)){
                                throw new IllegalArgumentException("DATO EJERCICIO FISCAL INCORRECTO, DEBE SER UN VALOR ENTERO DE 2 POSICIONES MÁXIMO.");
                            }
                            int ejercicioFiscal = Integer.parseInt(str);
                            if (ejercicioFiscal<2004 || ejercicioFiscal>2024){
                                throw new IllegalArgumentException("DATO EJERCICIO FISCAL INCORRECTO, DEBE SER UN NUMERO VÁLIDO DE AÑO DE 2004 A 2024.");
                            }
                            periodo.setEjerc(ejercicioFiscal);
                            break;
                        case 4:
                            //Monto total Operacion - REQUERIDO
                            if ("".equals(StringManage.getValidString(str))) {
                                throw new IllegalArgumentException("NO SE PERMITE MONTO TOTAL OPERACION NULO O VACIO");
                            }
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO MONTO TOTAL OPERACION INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            totales.setMontoTotOperacion(new BigDecimal(str));
                            break;
                        case 5:
                            //Monto total Gravado - REQUERIDO
                            if ("".equals(StringManage.getValidString(str))) {
                                throw new IllegalArgumentException("NO SE PERMITE MONTO TOTAL GRAVADO NULO O VACIO");
                            }
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO MONTO TOTAL GRAVADO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            totales.setMontoTotGrav(new BigDecimal(str));
                            break;
                        case 6:
                            //Monto total Exento - REQUERIDO
                            if ("".equals(StringManage.getValidString(str))) {
                                throw new IllegalArgumentException("NO SE PERMITE MONTO TOTAL EXENTO NULO O VACIO");
                            }
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO MONTO TOTAL EXENTO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            totales.setMontoTotExent(new BigDecimal(str));
                            break;
                        case 7:
                            //Monto total Retenido - REQUERIDO
                            if ("".equals(StringManage.getValidString(str))) {
                                throw new IllegalArgumentException("NO SE PERMITE MONTO TOTAL RETENIDO NULO O VACIO");
                            }
                            if (!gc.isDecimal(str, 1, 16, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO MONTO TOTAL RETENIDO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            totales.setMontoTotRet(new BigDecimal(str));
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setPeriodo(periodo);
                lineaDatos.setTotales(totales);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
