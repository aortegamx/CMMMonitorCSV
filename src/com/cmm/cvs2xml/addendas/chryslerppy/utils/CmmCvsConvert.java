/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.chryslerppy.utils;

import com.cmm.cvs2xml.addendas.chryslerppy.bean.AddendaChryslerPpyDatos;
import com.cmm.cvs2xml.bean.FacturaDatos;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 19/02/2016 19:24:18 PM
 */
public class CmmCvsConvert {

    public static void convert(FacturaDatos facturaDatos, String line) throws Exception{
        
        if (facturaDatos!=null){
            try{
                
                //Registro 00245 - Addenda Chrysler PPY, datos generales
                if (line.startsWith(CmmCvsChryslerPpyGeneralUtils.idRegistro)){

                    facturaDatos.setAddendaChryslerPpyDatos(new AddendaChryslerPpyDatos());
                    facturaDatos.getAddendaChryslerPpyDatos().setLineaDatosChryslerGenerales(CmmCvsChryslerPpyGeneralUtils.fillData(line));
                }

                //Solo si el objeto actual ya paso anteriormente por un registro
                // que lleno su atributo Addenda Chrysler PPY
                if (facturaDatos.getAddendaChryslerPpyDatos()!=null){
                    if (facturaDatos.getAddendaChryslerPpyDatos().getLineaDatosChryslerGenerales()!=null){
                        
                        facturaDatos.setTieneAddendas(true);

                        //Registro 00246
                        if (line.startsWith(CmmCvsChryslerPpyCancelacionesUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPpyDatos().getListaLineaDatosChryslerCancelaciones().add(CmmCvsChryslerPpyCancelacionesUtils.fillData(line));
                        }
                        
                        //Registro 00247
                        if (line.startsWith(CmmCvsChryslerPpyMonedaUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPpyDatos().setLineaDatosChryslerMoneda(CmmCvsChryslerPpyMonedaUtils.fillData(line));
                        }
                        
                        //Registro 00248
                        if (line.startsWith(CmmCvsChryslerPpyProveedorUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPpyDatos().setProveedor(CmmCvsChryslerPpyProveedorUtils.fillData(line));
                        }
                        
                        //Registro 00249
                        if (line.startsWith(CmmCvsChryslerPpyOrigenUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPpyDatos().setOrigen(CmmCvsChryslerPpyOrigenUtils.fillData(line));
                        }
                        
                        //Registro 00250
                        if (line.startsWith(CmmCvsChryslerPpyDestinoUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPpyDatos().setDestino(CmmCvsChryslerPpyDestinoUtils.fillData(line));
                        }
                        
                        //Registro 00251
                        if (line.startsWith(CmmCvsChryslerPpyReceivingUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPpyDatos().setReceiving(CmmCvsChryslerPpyReceivingUtils.fillData(line));
                        }
                        
                        //Registro 00252
                        if (line.startsWith(CmmCvsChryslerPpyProyectoUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPpyDatos().setLineaDatosChryslerProyecto(CmmCvsChryslerPpyProyectoUtils.fillData(line));
                        }
                        
                        //Registro 00253
                        if (line.startsWith(CmmCvsChryslerPpyNotaUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPpyDatos().getNotas().add(CmmCvsChryslerPpyNotaUtils.fillData(line));
                        }
                        
                        //Registro 00254
                        if (line.startsWith(CmmCvsChryslerPpyCargosCreditosUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPpyDatos().getListaLineaDatosChryslerCargosCreditos().add(CmmCvsChryslerPpyCargosCreditosUtils.fillData(line));
                        }
                        
                        //Registro 00255
                        if (line.startsWith(CmmCvsChryslerPpyOtrosCargosUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPpyDatos().getListaLineaDatosChryslerOtrosCargos().add(CmmCvsChryslerPpyOtrosCargosUtils.fillData(line));
                        }
                        
                        //Registro 00256
                        if (line.startsWith(CmmCvsChryslerPpyParteUtils.idRegistro)){
                            facturaDatos.getAddendaChryslerPpyDatos().getListaLineaDatosChryslerParte().add(CmmCvsChryslerPpyParteUtils.fillData(line));
                        }
                            
                    }
                }
            }catch (Exception ex){
                throw new Exception(" [Error Addenda Chrysler PPY v1.0] :" + ex.getMessage());
            }
        }
        
    }
    
}
