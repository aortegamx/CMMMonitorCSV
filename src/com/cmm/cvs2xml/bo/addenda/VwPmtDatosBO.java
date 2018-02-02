/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bo.addenda;

import com.cmm.cvs2xml.addendas.vwpmt.bean.*;
import mx.bigdata.sat.addenda.vwpmt.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 09/02/2015 06:41:13 PM
 */
public class VwPmtDatosBO {
    
    public static Factura compilarAddenda(AddendaVwPmtDatos addendaDatos){
        Factura factura = null;
        
        if (addendaDatos!=null){
            if (addendaDatos.getLineaDatosVWGenerales()!=null){
                 
                factura = addendaDatos.getLineaDatosVWGenerales().getFacturaVW();
                
                if (factura != null){
                    factura.setVersion("1.0");
                    
                    if (addendaDatos.getLineaDatosVWCancelaciones()!=null)
                        factura.setCancelaciones(addendaDatos.getLineaDatosVWCancelaciones().getCancelaciones());
                    if (addendaDatos.getLineaDatosVWMoneda()!=null)
                        factura.setMoneda(addendaDatos.getLineaDatosVWMoneda().getMoneda());
                    factura.setProveedor(addendaDatos.getProveedor());
                    factura.setOrigen(addendaDatos.getOrigen());
                    if (addendaDatos.getLineaDatosVWDestino()!=null)
                        factura.setDestino(addendaDatos.getLineaDatosVWDestino().getDestino());
                    if (addendaDatos.getLineaDatosVWMedidas()!=null)
                        factura.setMedidas(addendaDatos.getLineaDatosVWMedidas().getMedidas());
                    if (addendaDatos.getLineaDatosVWReferencias()!=null)
                        factura.setReferencias(addendaDatos.getLineaDatosVWReferencias().getReferencias());
                    if (addendaDatos.getNotas().size()>0){
                        factura.getNota().addAll(addendaDatos.getNotas());
                    }
                    if (addendaDatos.getListaLineaDatosVWArchivo().size()>0){
                        for (LineaDatosVWArchivo lineaArchivo :  addendaDatos.getListaLineaDatosVWArchivo()){
                            factura.getArchivo().add(lineaArchivo.getArchivo());
                        }
                    }
                    if (addendaDatos.getListaLineaDatosVWParte().size()>0){
                        factura.setPartes(new Factura.Partes());
                        for (LineaDatosVWParte lineaParte : addendaDatos.getListaLineaDatosVWParte()){
                            factura.getPartes().getParte().add(lineaParte.getParte());
                        }
                    }
                    
                }
                
            }
        }
        
        return factura;
    }

}