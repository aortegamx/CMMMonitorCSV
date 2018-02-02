/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.catalogo.utils;

import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.econtabilidad.v1.catalogo.schema.Catalogo;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 8/09/2014 02:20:49 PM
 */
public class CmmCvsCatalogoCuentaUtils {

    public final static String idRegistro = "101";
    public final static String infoRegistro = "INFORMACIÓN PARA DETALLE DE CUENTA EN CATÁLOGO";
    private final static int noElementosEsperados = 7;
    
    public static Catalogo.Ctas fillData(String elementoCfdi){
        Catalogo.Ctas ctas = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length != noElementosEsperados){
            throw new IllegalArgumentException("Renglon inválido. Se esperaban " + noElementosEsperados + " elementos, para el identificador " + idRegistro  + ".");
        }
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            
            ctas = new Catalogo.Ctas();
            
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
                        //Código agrupador de cuentas - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CÓDIGO AGRUPADOR DE CUENTAS NULO O VACIO");
                        }
                        if (!gc.validaConExpresionRegular(str, "^([0.-9]{1,12})$")){
                            throw new IllegalArgumentException("DATO CÓDIGO AGRUPADOR DE CUENTAS INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 12 CARACTERES, Y CUMPLIR CON EL PATRÓN: [0.-9]{1,12}");
                        }
                        ctas.setCodAgrup(str);
                        break;
                    case 2:
                        //Clave o número de cuenta - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CLAVE O NÚMERO DE CUENTA NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 100)){
                            throw new IllegalArgumentException("DATO CLAVE O NÚMERO DE CUENTA INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 100 CARACTERES.");
                        }
                        ctas.setNumCta(str);
                        break;
                    case 3:
                        //Nombre o descripción de la cuenta - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE NOMBRE O DESCRIPCIÓN DE CUENTA NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 200)){
                            throw new IllegalArgumentException("DATO NOMBRE O DESCRIPCIÓN DE CUENTA INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 200 CARACTERES.");
                        }
                        ctas.setDesc(str);
                        break;
                    case 4:
                        //Clave de la Cuenta padre - opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isValidString(str, 1, 100)){
                                throw new IllegalArgumentException("DATO CLAVE DE LA CUENTA PADRE (SubCtaDe) INCORRECTO, DEBE TENER MÍNIMO 1 Y MÁXIMO 100 CARACTERES. ES UN DATO OPCIONAL.");
                            }
                            ctas.setSubCtaDe(str);
                        }
                        break;
                    case 5:
                        //Nivel en el catálogo - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE NIVEL EN EL CATÁLOGO NULO O VACIO");
                        }
                        if (!gc.isNumeric(str, 1, 5)){
                            throw new IllegalArgumentException("DATO NIVEL EN EL CATÁLOGO INCORRECTO, DEBE SER UN ENTERO.");
                        }
                        ctas.setNivel(Integer.parseInt(str));
                        break;
                    case 6:
                        //Naturaleza de la cuenta - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE NATURALEZA DE LA CUENTA NULO O VACIO");
                        }
                        if (!gc.validaConExpresionRegular(str, "^[DA]$")){
                            throw new IllegalArgumentException("DATO NATURALEZA DE LA CUENTA INCORRECTO, DEBE TENER ALGUNO DE LOS VALORES D, A. (D – Deudora, A – Acreedora).");
                        }
                        ctas.setNatur(str);
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return ctas;
    }
    
}
