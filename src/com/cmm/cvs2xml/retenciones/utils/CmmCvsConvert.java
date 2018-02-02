/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.utils;

import com.cmm.cvs2xml.retenciones.bean.*;
import com.cmm.cvs2xml.utils.CmmCvsDatosAccionPersonalizadaUtils;
import com.cmm.cvs2xml.utils.CmmCvsDatosAdicionalesUtils;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 19/02/2015 05:38:43 PM
 */
public class CmmCvsConvert {

    public static RetencionesDatos convert(RetencionesDatos retencionesDatos, String line) throws Exception{
        
        try{

            //Registro 00500 - Comprobante Retenciones, datos generales
            if (line.startsWith(CmmCvsRetencionesComprobanteUtils.idRegistro)){

                if (retencionesDatos==null)
                    retencionesDatos = new RetencionesDatos();
                
                retencionesDatos.setLineaDatosComprobante(CmmCvsRetencionesComprobanteUtils.fillData(line));
            }

            if (retencionesDatos!=null){
                //Solo si el objeto actual ya paso anteriormente por un registro de inicio para Retenciones
                if (retencionesDatos.getLineaDatosComprobante()!=null){
                    if (retencionesDatos.getLineaDatosComprobante().getRetenciones()!=null){

                        //Registro 00501 - Datos Receptor
                        if (line.startsWith(CmmCvsRetencionesReceptorUtils.idRegistro)){
                            retencionesDatos.setLineaDatosReceptor(CmmCvsRetencionesReceptorUtils.fillData(line));
                        }
                        
                        //Registro 00502 - Datos Periodo y totales
                        if (line.startsWith(CmmCvsRetencionesPeriodoTotalesUtils.idRegistro)){
                            retencionesDatos.setLineaDatosPeriodoTotales(CmmCvsRetencionesPeriodoTotalesUtils.fillData(line));
                        }
                        
                        //Registro 00503 - Datos Detalle de Impuestos retenidos
                        if (line.startsWith(CmmCvsRetencionesImpuestoRetenidoUtils.idRegistro)){
                            retencionesDatos.getListaLineaDatosImpuestoRetenidos().add(CmmCvsRetencionesImpuestoRetenidoUtils.fillData(line));
                        }
                        
                        //Registro 98 - Datos acciones personalizadas
                        if (line.startsWith(CmmCvsDatosAccionPersonalizadaUtils.idRegistro)){
                            retencionesDatos.setLineaDatosAccionPersonalizada(CmmCvsDatosAccionPersonalizadaUtils.fillData(line));
                        }

                        //Registro 99 - Datos adicionales
                        if (line.startsWith(CmmCvsDatosAdicionalesUtils.idRegistro)){
                            retencionesDatos.setLineaDatosAdicionales(CmmCvsDatosAdicionalesUtils.fillData(line));
                        }
                        
                                
                    }
                }
            }
        }catch (Exception ex){
            throw new Exception(" Error en conversión Comprobante Retenciones v1.0: " + ex.getMessage());
        }
        
        return retencionesDatos;
    }
    
}