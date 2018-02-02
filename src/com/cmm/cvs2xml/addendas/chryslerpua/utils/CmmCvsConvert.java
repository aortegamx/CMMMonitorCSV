/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.chryslerpua.utils;

import com.cmm.cvs2xml.addendas.chryslerpua.bean.AddendaChryslerPuaDatos;
import com.cmm.cvs2xml.bean.FacturaDatos;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 10/02/2015 19:24:18 PM
 */
public class CmmCvsConvert {

    public static void convert(FacturaDatos facturaDatos, String line) throws Exception{
        
        if (facturaDatos!=null){
            try{
                
                //Registro 00221 - Addenda Chrysler PUA, datos generales
                if (line.startsWith(CmmCvsChryslerPuaGeneralUtils.idRegistro)){

                    facturaDatos.setAddendaChryslerPuaDatos(new AddendaChryslerPuaDatos());
                    facturaDatos.getAddendaChryslerPuaDatos().setLineaDatosChryslerGenerales(CmmCvsChryslerPuaGeneralUtils.fillData(line));
                }

                //Solo si el objeto actual ya paso anteriormente por un registro
                // que lleno su atributo Addenda Chrysler PUA
                if (facturaDatos.getAddendaChryslerPuaDatos()!=null){
                    if (facturaDatos.getAddendaChryslerPuaDatos().getLineaDatosChryslerGenerales()!=null){
                        
                        facturaDatos.setTieneAddendas(true);

                        //Registro 00222
                        if (line.startsWith(CmmCvsChryslerPuaCancelacionesUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPuaDatos().getListaLineaDatosChryslerCancelaciones().add(CmmCvsChryslerPuaCancelacionesUtils.fillData(line));
                        }
                        
                        //Registro 00223
                        if (line.startsWith(CmmCvsChryslerPuaMonedaUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPuaDatos().setLineaDatosChryslerMoneda(CmmCvsChryslerPuaMonedaUtils.fillData(line));
                        }
                        
                        //Registro 00224
                        if (line.startsWith(CmmCvsChryslerPuaProveedorUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPuaDatos().setProveedor(CmmCvsChryslerPuaProveedorUtils.fillData(line));
                        }
                        
                        //Registro 00225
                        if (line.startsWith(CmmCvsChryslerPuaOrigenUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPuaDatos().setOrigen(CmmCvsChryslerPuaOrigenUtils.fillData(line));
                        }
                        
                        //Registro 00226
                        if (line.startsWith(CmmCvsChryslerPuaDestinoUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPuaDatos().setDestino(CmmCvsChryslerPuaDestinoUtils.fillData(line));
                        }
                        
                        //Registro 00227
                        if (line.startsWith(CmmCvsChryslerPuaReceivingUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPuaDatos().setReceiving(CmmCvsChryslerPuaReceivingUtils.fillData(line));
                        }
                        
                        //Registro 00228
                        if (line.startsWith(CmmCvsChryslerPuaNotaUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPuaDatos().getNotas().add(CmmCvsChryslerPuaNotaUtils.fillData(line));
                        }
                        
                        //Registro 00229
                        if (line.startsWith(CmmCvsChryslerPuaCargosCreditosUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPuaDatos().getListaLineaDatosChryslerCargosCreditos().add(CmmCvsChryslerPuaCargosCreditosUtils.fillData(line));
                        }
                        
                        //Registro 00230
                        if (line.startsWith(CmmCvsChryslerPuaOtrosCargosUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPuaDatos().getListaLineaDatosChryslerOtrosCargos().add(CmmCvsChryslerPuaOtrosCargosUtils.fillData(line));
                        }
                        
                        //Registro 00231
                        if (line.startsWith(CmmCvsChryslerPuaParteUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPuaDatos().getListaLineaDatosChryslerParte().add(CmmCvsChryslerPuaParteUtils.fillData(line));
                        }
                            
                    }
                }
            }catch (Exception ex){
                throw new Exception(" [Error Addenda Chrysler PUA v1.0] :" + ex.getMessage());
            }
        }
        
    }
    
}
