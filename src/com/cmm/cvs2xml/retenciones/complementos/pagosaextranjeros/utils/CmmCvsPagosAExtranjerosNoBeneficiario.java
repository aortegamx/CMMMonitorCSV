/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.complementos.pagosaextranjeros.utils;

import com.cmm.cvs2xml.retenciones.complementos.pagosaextranjeros.bean.LineaDatosNoBeneficiario;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.retencion.common.pagosaextranjeros.schema.CPais;
import mx.bigdata.sat.retencion.common.pagosaextranjeros.schema.Pagosaextranjeros;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 25/02/2015 06:41:58 PM
 */
public class CmmCvsPagosAExtranjerosNoBeneficiario {

    public final static String idRegistro = "00514";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS NO BENEFICIARIO EXTRANJERO PARA COMPLEMENTO PAGOS A EXTRANJEROS";
    private final static int noElementosEsperados = 4;
    
    public static LineaDatosNoBeneficiario fillData(String elementoCfdi){
        LineaDatosNoBeneficiario lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            
            lineaDatos = new LineaDatosNoBeneficiario();
            Pagosaextranjeros.NoBeneficiario noBeneficiario = new Pagosaextranjeros.NoBeneficiario();
            
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
                        //País de Residencia - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO PAIS DE RESIDENCIA NULO O VACIO");
                        }
                        /*
                        String valoresValidos1 = "1,2,3,4,5,6,7,8,9";
                        String regExp1 = valoresValidos1.replaceAll(",", "|");
                        if (!gc.validaConExpresionRegular(str, regExp1)){
                            throw new IllegalArgumentException("DATO PAIS DE RESIDENCIA INVÁLIDO, DEBE PERTENECER A UNO DE: " + valoresValidos1);
                        }
                        */
                        CPais cpais = null;
                        try{
                            cpais = CPais.valueOf(str);
                        }catch(Exception ex){
                            throw new IllegalArgumentException("DATO PAIS DE RESIDENCIA INVÁLIDO, NO CORRESPONDE A NINGUNO DEL CATALOGO." + ex.toString());
                        }
                        noBeneficiario.setPaisDeResidParaEfecFisc(cpais);
                        break;
                    case 2:
                        //Tipo de Contribuyente - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO TIPO DE CONTRIBUYENTE NULO O VACIO");
                        }
                        String valoresValidos = "1,2,3,4,5,6,7,8,9";
                        String regExp = valoresValidos.replaceAll(",", "|");
                        if (!gc.validaConExpresionRegular(str, regExp)){
                            throw new IllegalArgumentException("DATO TIPO DE CONTRIBUYENTE CONCEPTO PAGO INVÁLIDO, DEBE PERTENECER A UNO DE: " + valoresValidos);
                        }
                        noBeneficiario.setConceptoPago(str);
                        break;
                    case 3:
                        //Descripción de concepto - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO DESCRIPCION DE CONCEPTO NULO O VACIO");
                        }
                        if  (!gc.isValidString(str, 1, 255)){
                            throw new IllegalArgumentException("DATO DESCRIPCION DE CONCEPTO INVÁLIDO, DEBE TENER MÍNIMO 1 CARACTER, MÁXIMO 255.");
                        }
                        noBeneficiario.setDescripcionConcepto(str);
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            lineaDatos.setNoBeneficiario(noBeneficiario);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
