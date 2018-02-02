/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.complementos.pagosaextranjeros.utils;

import com.cmm.cvs2xml.retenciones.complementos.pagosaextranjeros.bean.LineaDatosBeneficiario;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.retencion.common.pagosaextranjeros.schema.Pagosaextranjeros;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 25/02/2015 06:41:58 PM
 */
public class CmmCvsPagosAExtranjerosBeneficiario {

    public final static String idRegistro = "00515";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS BENEFICIARIO PARA COMPLEMENTO PAGOS A EXTRANJEROS";
    private final static int noElementosEsperados = 6;
    
    public static LineaDatosBeneficiario fillData(String elementoCfdi){
        LineaDatosBeneficiario lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            
            lineaDatos = new LineaDatosBeneficiario();
            Pagosaextranjeros.Beneficiario beneficiario = new Pagosaextranjeros.Beneficiario();
            
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
                            throw new IllegalArgumentException("NO SE PERMITE DATO RFC NULO O VACIO");
                        }
                        if (!gc.isRFC(str)){
                            throw new IllegalArgumentException("DATO RFC INVÁLIDO EN ESTRUCTURA");
                        }
                        beneficiario.setRFC(str);
                        break;
                    case 2:
                        //CURP - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO CURP NULO O VACIO");
                        }
                        if (!gc.isCURP(str)){
                            throw new IllegalArgumentException("DATO CURP INVÁLIDO EN ESTRUCTURA");
                        }
                        beneficiario.setCURP(str);
                        break;
                    case 3:
                        //Nombre o Razon Social - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO NOMBRE O RAZON SOCIAL NULO O VACIO");
                        }
                        if  (!gc.isValidString(str, 1, 300)){
                            throw new IllegalArgumentException("DATO NOMBRE O RAZON SOCIAL INVÁLIDO, DEBE TENER MÍNIMO 1 CARACTER, MÁXIMO 300.");
                        }
                        beneficiario.setNomDenRazSocB(str);
                        break;
                    case 4:
                        //Tipo de Contribuyente - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO TIPO DE CONTRIBUYENTE NULO O VACIO");
                        }
                        String valoresValidos = "1,2,3,4,5,6,7,8,9";
                        String regExp = valoresValidos.replaceAll(",", "|");
                        if (!gc.validaConExpresionRegular(str, regExp)){
                            throw new IllegalArgumentException("DATO TIPO DE CONTRIBUYENTE CONCEPTO PAGO INVÁLIDO, DEBE PERTENECER A UNO DE: " + valoresValidos);
                        }
                        beneficiario.setConceptoPago(str);
                        break;
                    case 5:
                        //Descripción de concepto - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO DESCRIPCION DE CONCEPTO NULO O VACIO");
                        }
                        if  (!gc.isValidString(str, 1, 255)){
                            throw new IllegalArgumentException("DATO DESCRIPCION DE CONCEPTO INVÁLIDO, DEBE TENER MÍNIMO 1 CARACTER, MÁXIMO 255.");
                        }
                        beneficiario.setDescripcionConcepto(str);
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            lineaDatos.setBeneficiario(beneficiario);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
