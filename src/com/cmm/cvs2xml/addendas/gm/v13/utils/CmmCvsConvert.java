/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.gm.v13.utils;

import com.cmm.cvs2xml.addendas.gm.v13.bean.AddendaGMv13Datos;
import com.cmm.cvs2xml.bean.FacturaDatos;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 28/12/2015 01:24:18 PM
 */
public class CmmCvsConvert {

    public static void convert(FacturaDatos facturaDatos, String line) throws Exception{
        
        if (facturaDatos!=null){
            try{
                
                //Registro 00235 - Addenda GM v1.3, datos generales, nodo Header
                if (line.startsWith(CmmCvsGMv13GeneralUtils.idRegistro)){

                    facturaDatos.setAddendaGMv13Datos(new AddendaGMv13Datos());
                    facturaDatos.getAddendaGMv13Datos().setLineaDatosGMv13Generales(CmmCvsGMv13GeneralUtils.fillData(line));
                }

                //Solo si el objeto actual ya paso anteriormente por un registro
                // que lleno su atributo Addenda Sanofi
                if (facturaDatos.getAddendaGMv13Datos()!=null){
                    if (facturaDatos.getAddendaGMv13Datos().getLineaDatosGMv13Generales()!=null){
                        if (facturaDatos.getAddendaGMv13Datos().getLineaDatosGMv13Generales().getHeader()!=null){
                        
                            facturaDatos.setTieneAddendas(true);
                            
                            //Registro 00236 - Addenda GM v1.3, nodos ITEM
                            if (line.startsWith(CmmCvsGMv13ItemUtils.idRegistro)){
                                facturaDatos.getAddendaGMv13Datos().getListaLineaDatosGMv13Items().add(CmmCvsGMv13ItemUtils.fillData(line));
                            }

                        }
                    }
                }
            }catch (Exception ex){
                throw new Exception(" [Error Addenda GM v1.3] :" + ex.getMessage());
            }
        }
        
    }
    
}
