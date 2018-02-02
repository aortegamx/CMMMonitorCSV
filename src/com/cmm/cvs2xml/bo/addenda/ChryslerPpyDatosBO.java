/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bo.addenda;

import com.cmm.cvs2xml.addendas.chryslerppy.bean.*;
import mx.bigdata.sat.addenda.chryslerppy.schema.Factura;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 19/02/2016 06:41:13 PM
 */
public class ChryslerPpyDatosBO {
    
    public static Factura compilarAddenda(AddendaChryslerPpyDatos addendaDatos){
        Factura factura = null;
        
        if (addendaDatos!=null){
            if (addendaDatos.getLineaDatosChryslerGenerales()!=null){
                 
                factura = addendaDatos.getLineaDatosChryslerGenerales().getFacturaChrysler();
                
                if (factura != null){
                    factura.setVersion("1.0");
                    
                    if (addendaDatos.getListaLineaDatosChryslerCancelaciones().size()>0){
                        for (LineaDatosChryslerCancelaciones lineaCancelaciones :  addendaDatos.getListaLineaDatosChryslerCancelaciones()){
                            factura.getCancelaciones().add(lineaCancelaciones.getCancelaciones());
                        }
                    }
                    
                    if (addendaDatos.getLineaDatosChryslerMoneda()!=null)
                        factura.setMoneda(addendaDatos.getLineaDatosChryslerMoneda().getMoneda());
                    factura.setProveedor(addendaDatos.getProveedor());
                    factura.setOrigen(addendaDatos.getOrigen());
                    factura.setDestino(addendaDatos.getDestino());
                    factura.setReceiving(addendaDatos.getReceiving());
                    
                    if (addendaDatos.getLineaDatosChryslerProyecto()!=null)
                        factura.setProyecto(addendaDatos.getLineaDatosChryslerProyecto().getProyecto());
                    
                    if (addendaDatos.getNotas().size()>0){
                        factura.getNota().addAll(addendaDatos.getNotas());
                    }
                    if (addendaDatos.getListaLineaDatosChryslerCargosCreditos().size()>0){
                        for (LineaDatosChryslerCargosCreditos linea :  addendaDatos.getListaLineaDatosChryslerCargosCreditos()){
                            factura.getCargosCreditos().add(linea.getCargosCreditos());
                        }
                    }
                    if (addendaDatos.getListaLineaDatosChryslerOtrosCargos().size()>0){
                        for (LineaDatosChryslerOtrosCargos linea :  addendaDatos.getListaLineaDatosChryslerOtrosCargos()){
                            factura.getOtrosCargos().add(linea.getOtrosCargos());
                        }
                    }
                    if (addendaDatos.getListaLineaDatosChryslerParte().size()>0){
                        factura.setPartes(new Factura.Partes());
                        for (LineaDatosChryslerParte lineaParte : addendaDatos.getListaLineaDatosChryslerParte()){
                            Factura.Partes.Part parte = lineaParte.getParte();
                            
                            factura.getPartes().getPart().add(parte);
                        }
                    }
                    
                }
                
            }
        }
        
        return factura;
    }

}