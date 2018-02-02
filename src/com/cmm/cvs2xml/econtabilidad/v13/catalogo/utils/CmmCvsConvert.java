/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.v13.catalogo.utils;

import com.cmm.cvs2xml.econtabilidad.v13.catalogo.utils.*;
import com.cmm.cvs2xml.econtabilidad.v13.catalogo.bean.CatalogoDatos;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 8/09/2014 05:38:43 PM
 */
public class CmmCvsConvert {

    public static CatalogoDatos convert(CatalogoDatos catalogoDatos, String line) throws Exception{
        
        try{

            //Registro 100 - Catalogo de Cuentas, datos generales
            if (line.startsWith(CmmCvsCatalogoDatosGeneralesUtils.idRegistro)){

                if (catalogoDatos==null)
                    catalogoDatos = new CatalogoDatos();
                
                catalogoDatos.setLineaDatosCatalogo(CmmCvsCatalogoDatosGeneralesUtils.fillData(line));
            }

            if (catalogoDatos!=null){
                //Solo si el objeto actual ya paso anteriormente por un registro de inicio para Catalogo
                if (catalogoDatos.getLineaDatosCatalogo()!=null){
                    if (catalogoDatos.getLineaDatosCatalogo().getDatosGeneralesCatalogo()!=null){
                        if (catalogoDatos.getLineaDatosCatalogo().getDatosGeneralesCatalogo().getCatalogo()!=null){

                                //Registro 101 - Detalles de Cuentas
                                if (line.startsWith(CmmCvsCatalogoCuentaUtils.idRegistro)){
                                    catalogoDatos.getListaCuentas().add(CmmCvsCatalogoCuentaUtils.fillData(line));
                                }
                                
                        }
                    }
                }
            }
        }catch (Exception ex){
            throw new Exception(" Error en conversión Formato Catalogo de Cuentas :" + ex.getMessage());
        }
        
        return catalogoDatos;
    }
    
}
