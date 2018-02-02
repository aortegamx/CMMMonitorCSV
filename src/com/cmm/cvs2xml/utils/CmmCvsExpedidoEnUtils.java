/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.utils;

import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.cfdi.v32.schema.TUbicacion;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 08/05/2014 02:38:02 PM
 */
public class CmmCvsExpedidoEnUtils {
    
    public final static String idRegistro = "90";
    public final static String infoRegistro = "DATOS DEL DOMICILIO DE EMISIÓN (SUCURSAL) - EXPEDIDO EN";
    
    public static TUbicacion fillData(String elementoCfdi){
        TUbicacion ubicacion = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length>0){
            
            ubicacion = new TUbicacion();
            
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
                        //Calle - Opcional
                        ubicacion.setCalle(str);
                        break;
                    case 2:
                        //No Exterior - Opcional
                        ubicacion.setNoExterior(str);
                        break;
                    case 3:
                        //No Interior - Opcional
                        ubicacion.setNoInterior(str);
                        break;
                    case 4:
                        //Colonia - Opcional
                        ubicacion.setColonia(str);
                        break;
                    case 5:
                        //Localidad - Opcional
                        ubicacion.setLocalidad(str);
                        break;
                    case 6:
                        //Municipio - Opcional
                        ubicacion.setMunicipio(str);
                        break;
                    case 7:
                        //Codigo Postal - Opcional
                        ubicacion.setCodigoPostal(str);
                        break;
                    case 8:
                        //Estado - Opcional
                        ubicacion.setEstado(str);
                        break;
                    case 9:
                        //Pais - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE PAIS NULO O VACIO");
                        }
                        ubicacion.setPais(str);
                        break;
                    case 10:
                        //Referencia - Opcional
                        ubicacion.setReferencia(str);
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return ubicacion;
    }

}
