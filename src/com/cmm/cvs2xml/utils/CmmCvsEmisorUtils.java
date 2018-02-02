/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.utils;

import com.cmm.cvs2xml.bean.DatosEmisor;
import com.cmm.cvs2xml.bean.LineaDatosEmisor;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import mx.bigdata.sat.cfdi.v33.schema.CUsoCFDI;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;

/**
 *
 * @author leonardo
 */
public class CmmCvsEmisorUtils {
    public final static String idRegistro = "00";
    public final static String infoRegistro = "DATOS DEL EMISOR";
    
    public static LineaDatosEmisor fillData(String elementoCfdi){
        LineaDatosEmisor lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length>0){
            
            GenericValidator gc = new GenericValidator();
            
            lineaDatos = new LineaDatosEmisor();
            DatosEmisor datosEmisor = new DatosEmisor();
            Comprobante.Emisor emisor = new Comprobante.Emisor();
            
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
                        //Nombre o Razon Social - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))) {
                            throw new IllegalArgumentException("NO SE PERMITE RAZON SOCIAL O NOMBRE DE RECEPTOR NULO O VACIO");
                        }
                        emisor.setNombre(str);
                        break;
                    case 2:
                        //Tipo de Persona - Opcional
                        if (StringManage.getValidString(str).equalsIgnoreCase("física") || 
                                StringManage.getValidString(str).equalsIgnoreCase("fisica")){
                            datosEmisor.setTipoPersona(DatosEmisor.TipoPersona.FISICA);
                        }else{
                            datosEmisor.setTipoPersona(DatosEmisor.TipoPersona.MORAL);
                        }
                        
                        break;
                    case 3:
                        //RFC Emisor - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE RFC EMISOR  NULO O VACIO");
                        }
                        if (!gc.isRFC(str)){
                            throw new IllegalArgumentException("RFC EMISOR INVÁLIDO EN ESTRUCTURA");
                        }
                        emisor.setRfc(str);
                        lineaDatos.setRfcEmisor(str);
                        break;
                    case 4:
                        //Nombres - Opcional
                        datosEmisor.setNombres(str);
                        break;
                    case 5:
                        //Apellidos - Opcional
                        datosEmisor.setApellidos(str);
                        break;                    
                    case 6:
                        //Numero de Regimen Fiscal- REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE REGIMEN FISCAL DE EMISOR NULO O VACIO");
                        }
                        if(!"".equals(StringManage.getValidString(str))){
                            if(!gc.isValidString(str, 3, 3)){
                                throw new IllegalArgumentException("EL NUMERO DE REGIMEN FISCAL DEBE CONTENER 3 CARACTERES");
                            }
                            emisor.setRegimenFiscal(str);
                        }
                        break;                                       
                }
                /*
                 * SWITCH X
                 */
                
            }
            
             //Asignamos datos internos de objeto final
            datosEmisor.setEmisor(emisor);
            lineaDatos.setDatosEmisor(datosEmisor);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }
}
