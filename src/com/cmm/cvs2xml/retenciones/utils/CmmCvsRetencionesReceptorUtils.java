/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.utils;

import com.cmm.cvs2xml.retenciones.bean.LineaDatosReceptor;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.retencion.v1.schema.Retenciones;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 18/02/2015 10:36:56 AM
 */
public class CmmCvsRetencionesReceptorUtils {

    public final static String idRegistro = "00501";
    public final static String infoRegistro = "INFORMACIÓN DE CLIENTE RECEPTOR PARA RETENCIONES";
    //private final static int noElementosEsperados = 4;
    
    public static LineaDatosReceptor fillData(String elementoCfdi){
        LineaDatosReceptor lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        //if (data.length != noElementosEsperados){
        //    throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        //}
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales;
            
            lineaDatos = new LineaDatosReceptor();
            Retenciones.Receptor receptor = new Retenciones.Receptor();
            
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
                            //Nacionalidad - REQUERIDO
                            if ("".equals(StringManage.getValidString(str))) {
                                throw new IllegalArgumentException("NO SE PERMITE NACIONALIDAD DE RECEPTOR NULO O VACIO");
                            }
                            if (!gc.validaConExpresionRegular(str, "Nacional|Extranjero")){
                                throw new IllegalArgumentException("DATO NACIONALIDAD INCORRECTO, REQUERIDO, DEBE CORRESPONDER A UNO DE Nacional, Extranjero.");
                            }
                            receptor.setNacionalidad(str);
                            if (str.equals("Nacional"))
                                receptor.setNacional(new Retenciones.Receptor.Nacional());
                            if (str.equals("Extranjero"))
                                receptor.setExtranjero(new Retenciones.Receptor.Extranjero());
                            break;
                        case 2:
                            //Nombre o Razon Social Receptor - REQUERIDO
                            if ("".equals(StringManage.getValidString(str))) {
                                throw new IllegalArgumentException("NO SE PERMITE RAZON SOCIAL O NOMBRE DE RECEPTOR NULO O VACIO");
                            }
                            if (!gc.isValidString(str, 1, 300)){
                                throw new IllegalArgumentException("DATO RAZON SOCIAL O NOMBRE DE RECEPTOR INVÁLIDO, DEBE TENER MÍNIMO 1 CARACTER, MÁXIMO 300");
                            }
                            if (receptor.getNacional()!=null){
                                receptor.getNacional().setNomDenRazSocR(str);
                            }else if (receptor.getExtranjero()!=null){
                                receptor.getExtranjero().setNomDenRazSocR(str);
                            }
                            break;
                        case 3:
                            //RFC receptor - REQUERIDO
                            if ("".equals(StringManage.getValidString(str))) {
                                throw new IllegalArgumentException("NO SE PERMITE RFC DE RECEPTOR NULO O VACIO");
                            }
                             if (!gc.isRFC(str)){
                                throw new IllegalArgumentException("RFC RECEPTOR INVÁLIDO EN ESTRUCTURA");
                            }
                            if (receptor.getNacional()!=null)
                                receptor.getNacional().setRFCRecep(str);
                            break;
                        case 4:
                            //CURP receptor - Opcional
                            if (!"".equals(StringManage.getValidString(str))) {
                                if (!gc.isCURP(str)){
                                    throw new IllegalArgumentException("CURP RECEPTOR INVÁLIDO EN ESTRUCTURA, DATO OPCIONAL");
                                }
                                if (receptor.getNacional()!=null)
                                    receptor.getNacional().setCURPR(str);
                            }
                            break;
                        case 5:
                            //NRIF receptor - Opcional
                            if (!"".equals(StringManage.getValidString(str))) {
                                if (!gc.isValidString(str, 1, 20)){
                                    throw new IllegalArgumentException("DATO NÚMERO DE REGISTRO DE IDENTIFICACIÓN FISCAL DEL RECEPTOR INVÁLIDO, ES OPCIONAL, SI SE EXPRESA DEBE TENER MÍNIMO 1 CARACTER, MÁXIMO 20");
                                }
                                if (receptor.getExtranjero()!=null)
                                    receptor.getExtranjero().setNumRegIdTrib(str);
                            }
                            break;
                        case 6:
                            //Nombres receptor - Opcional
                            lineaDatos.setNombres(str);
                            break;
                        case 7:
                            //Apellidos receptor - Opcional
                            lineaDatos.setApellidos(str);
                            break;
                        case 8:
                            //Email - Opcional
                            if (!"".equals(StringManage.getValidString(str))) {
                                if (!gc.isEmail(str)){
                                    throw new IllegalArgumentException("DATO EMAIL RECEPTOR INVÁLIDO, ES OPCIONAL, SI SE EXPRESA DEBE SER UNA DIRECCIÓN DE CORREO ELECTRÓNICO VALIDA.");
                                }
                                lineaDatos.setEmail(str);
                            }
                            break;
                    }
                    /*
                     * SWITCH X
                     */
                
                }
            
                lineaDatos.setReceptor(receptor);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
    
}
