/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.catalogo.utils;

import com.cmm.cvs2xml.econtabilidad.catalogo.bean.DatosGeneralesCatalogo;
import com.cmm.cvs2xml.econtabilidad.catalogo.bean.LineaDatosCatalogo;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.econtabilidad.v1.catalogo.schema.Catalogo;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 8/09/2014 02:19:43 PM
 */
public class CmmCvsCatalogoDatosGeneralesUtils {

    public final static String idRegistro = "100";
    public final static String infoRegistro = "INFORMACIÓN PARA CATÁLOGO DE CUENTAS";
    private final static int noElementosEsperados = 5;
    
    public static LineaDatosCatalogo fillData(String elementoCfdi){
        LineaDatosCatalogo lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            
            lineaDatos = new LineaDatosCatalogo();
            DatosGeneralesCatalogo datosGeneralesCatalogo = new DatosGeneralesCatalogo();
            Catalogo catalogo = new Catalogo();
            
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
                        //RFC - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE RFC DEL CONTRIBUYENTE NULO O VACIO");
                        }
                        if (!gc.isRFC(str)){
                            throw new IllegalArgumentException("DATO RFC DEL CONTRIBUYENTE INCORRECTO, NO ES VÁLIDO RESPECTO AL PATRÓN OFICIAL.");
                        }
                        catalogo.setRFC(str);
                        break;
                    case 2:
                        //Total de Cuentas - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TOTAL DE CUENTAS NULO O VACIO");
                        }
                        if (!gc.isNumeric(str, 1, 5)){
                            throw new IllegalArgumentException("DATO TOTAL DE CUENTAS INCORRECTO, DEBE SER UN ENTERO.");
                        }
                        int totalCuentas = Integer.parseInt(str);
                        if (totalCuentas<2){
                            throw new IllegalArgumentException("DATO TOTAL DE CUENTAS INCORRECTO, DEBE SER UN VALOR IGUAL O MAYOR A 2.");
                        }
                        catalogo.setTotalCtas(totalCuentas);
                        break;
                    case 3:
                        //Mes - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE MES NULO O VACIO");
                        }
                        if (!gc.validaConExpresionRegular(str,"^(0[1-9]|1[012])$")){
                            throw new IllegalArgumentException("DATO MES INCORRECTO, DEBE TENER UN VALOR ENTRE 01 Y 12.");
                        }
                        catalogo.setMes(str);
                        break;
                    case 4:
                        //Año - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE AÑO NULO O VACIO");
                        }
                        if (!gc.isNumeric(str, 1, 4)){
                            throw new IllegalArgumentException("DATO AÑO INCORRECTO, DEBE SER UN ENTERO.");
                        }
                        int anio = Integer.parseInt(str);
                        if (anio<2014 || anio>2099){
                            throw new IllegalArgumentException("DATO AÑO INCORRECTO, DEBE SER UN VALOR ENTRE 2014 Y 2099.");
                        }
                        catalogo.setAno(anio);
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            datosGeneralesCatalogo.setCatalogo(catalogo);
            lineaDatos.setDatosGeneralesCatalogo(datosGeneralesCatalogo);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
