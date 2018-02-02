/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.complementos.implocal.utils;

import com.cmm.cvs2xml.bean.FacturaDatos;
import com.cmm.cvs2xml.complementos.implocal.bean.ImpLocalDatos;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 13/05/2014 06:08:50 PM
 */
public class CmmCvsConvert {
    
    public static void convert(FacturaDatos facturaDatos, String line) throws Exception{
        
        if (facturaDatos!=null){
            try{
                
                //Registro 30 - Impuestos Locales, datos generales
                if (line.startsWith(CmmCvsImpLocalDatosGeneralesUtils.idRegistro)){

                    facturaDatos.setImpLocalDatos(new ImpLocalDatos());
                    facturaDatos.getImpLocalDatos().setLineaDatosImpLocal(CmmCvsImpLocalDatosGeneralesUtils.fillData(line));
                }

                //Solo si el objeto actual ya paso anteriormente por un registro
                // que lleno su atributo NominaDatos
                if (facturaDatos.getImpLocalDatos()!=null){
                    if (facturaDatos.getImpLocalDatos().getLineaDatosImpLocal()!=null){
                        if (facturaDatos.getImpLocalDatos().getLineaDatosImpLocal().getDatosGeneralesImpLocal().getImpuestosLocales()!=null){
                        
                            //Registro 31 - Retencion
                            if (line.startsWith(CmmCvsImpLocalRetencionUtils.idRegistro)){
                                facturaDatos.getImpLocalDatos().getListaRetencionesLocales().add(CmmCvsImpLocalRetencionUtils.fillData(line));
                            }

                            //Registro 32 - Traslado
                            if (line.startsWith(CmmCvsImpLocalTrasladoUtils.idRegistro)){
                                facturaDatos.getImpLocalDatos().getListaTrasladosLocales().add(CmmCvsImpLocalTrasladoUtils.fillData(line));
                            }
                        }
                    }
                }
            }catch (Exception ex){
                throw new Exception(" [Error Complemento Impuestos Locales] :" + ex.getMessage());
            }
        }
        
    }

}
