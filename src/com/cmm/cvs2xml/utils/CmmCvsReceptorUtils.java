/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.utils;

import com.cmm.cvs2xml.util.StringManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.bean.DatosReceptor;
import com.cmm.cvs2xml.bean.LineaDatosCliente;
import mx.bigdata.sat.cfdi.v33.schema.CUsoCFDI;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;
import mx.bigdata.sat.retencion.v1.schema.CPais;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 22/04/2014 02:38:02 PM
 */
public class CmmCvsReceptorUtils {
    
    public final static String idRegistro = "01";
    public final static String infoRegistro = "DATOS DEL CLIENTE/RECEPTOR";
    
    public static LineaDatosCliente fillData(String elementoCfdi){
        LineaDatosCliente lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length>0){
            
            GenericValidator gc = new GenericValidator();
            
            lineaDatos = new LineaDatosCliente();
            DatosReceptor datosReceptor = new DatosReceptor();
            Comprobante.Receptor receptor = new Comprobante.Receptor();
            
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
                        receptor.setNombre(str);
                        break;
                    case 2:
                        //Tipo de Persona - Opcional
                        if (StringManage.getValidString(str).equalsIgnoreCase("física") || 
                                StringManage.getValidString(str).equalsIgnoreCase("fisica")){
                            datosReceptor.setTipoPersona(DatosReceptor.TipoPersona.FISICA);
                        }else{
                            datosReceptor.setTipoPersona(DatosReceptor.TipoPersona.MORAL);
                        }
                        
                        break;
                    case 3:
                        //RFC Destinatario - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE RFC RECEPTOR DESTINATARIO NULO O VACIO");
                        }
                        if (!gc.isRFC(str)){
                            throw new IllegalArgumentException("RFC RECEPTOR INVÁLIDO EN ESTRUCTURA");
                        }
                        receptor.setRfc(str);
                        break;
                    case 4:
                        //Nombres - Opcional
                        datosReceptor.setNombres(str);
                        break;
                    case 5:
                        //Apellidos - Opcional
                        datosReceptor.setApellidos(str);
                        break;
                    case 6:
                        //Email - Opcional
                        datosReceptor.setEmail(str);
                        break;
                    case 7:
                        //Telefono - Opcional
                        datosReceptor.setTelefono(str);
                        break;
                    case 8:
                        //ResidenciaFiscal - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if(!"MEX".equals(StringManage.getValidString(str))){
                                receptor.setResidenciaFiscal(mx.bigdata.sat.cfdi.v33.schema.CPais.fromValue(str));
                            }
                        }
                        break;
                    case 9:
                        //Numero de Regimen tributario - Opcional
                        if(!"".equals(StringManage.getValidString(str))){
                            if(!gc.isValidString(str, 1, 40)){
                                throw new IllegalArgumentException("EL NUMERO DE REGIMEN TRIBUTARIO DEBE CONTENER DE 1 A 40 CARACTERES");
                            }
                            receptor.setNumRegIdTrib(str);
                        }
                        break;
                    case 10:
                        if("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE USO DEL CDFI DESTINATARIO NULO O VACIO");
                        }
                        receptor.setUsoCFDI(CUsoCFDI.fromValue(str));
                        break;
                    case 11:
                        //RFC Emisor - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE RFC EMISOR NULO O VACIO");
                        }
                        if (!gc.isRFC(str)){
                            throw new IllegalArgumentException("RFC EMISOR INVÁLIDO EN ESTRUCTURA");
                        }
                        lineaDatos.setRfcEmisor(str);
                        break;
                    case 12:
                        //Notificar - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DATO NOTIFICAR NULO O VACIO");
                        }
                        datosReceptor.setNotificar(StringManage.getValidString(str).equalsIgnoreCase("SI"));
                        break;
                    case 13:
                        //Referencia1 - Opcional
                        datosReceptor.setReferencia1(str);
                        break;
                    case 14:
                        //Referencia2 - Opcional
                        datosReceptor.setReferencia2(str);
                        break;
                    case 15:
                        //Referencia3 - Opcional
                        datosReceptor.setReferencia3(str);
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
             //Asignamos datos internos de objeto final
            datosReceptor.setReceptor(receptor);
            lineaDatos.setDatosReceptor(datosReceptor);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
        return lineaDatos;
    }

}
