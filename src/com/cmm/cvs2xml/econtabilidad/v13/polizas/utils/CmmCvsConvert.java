/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.v13.polizas.utils;

import com.cmm.cvs2xml.econtabilidad.v13.polizas.utils.*;
import com.cmm.cvs2xml.econtabilidad.v13.polizas.bean.LineaDatosPoliza;
import com.cmm.cvs2xml.econtabilidad.v13.polizas.bean.PolizasDatos;
import mx.bigdata.sat.econtabilidad.v13.schema.polizas.Polizas;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 17/09/2014 05:38:43 PM
 */
public class CmmCvsConvert {
    
    public static PolizasDatos convert(PolizasDatos polizasDatos, String line) throws Exception{
        
        try{

            //Registro 120 - Polizas de Comprobación, datos generales
            if (line.startsWith(CmmCvsPolizasDatosGeneralesUtils.idRegistro)){

                if (polizasDatos==null)
                    polizasDatos = new PolizasDatos();
                
                polizasDatos.setLineaDatosPolizas(CmmCvsPolizasDatosGeneralesUtils.fillData(line));
            }

            if (polizasDatos!=null){
                //Solo si el objeto actual ya paso anteriormente por un registro de inicio para Polizas
                if (polizasDatos.getLineaDatosPolizas()!=null){
                    if (polizasDatos.getLineaDatosPolizas().getDatosGeneralesPolizas()!=null){
                        if (polizasDatos.getLineaDatosPolizas().getDatosGeneralesPolizas().getPolizas()!=null){

                                //Registro 121 - Detalles de Cuentas de Polizas
                                if (line.startsWith(CmmCvsPolizasPolizaUtils.idRegistro)){                                    
                                    LineaDatosPoliza ldatosPoliza = CmmCvsPolizasPolizaUtils.fillData(line);
                                    
                                    polizasDatos.getLineaDatosPolizaDetalle().add(ldatosPoliza);
                                }
                                
                                int tamanoListaPolizaDetalles = polizasDatos.getLineaDatosPolizaDetalle().size();
                                int tamanoListaTransaccion = 0;
                                Polizas.Poliza ultimaPoliza = null;
                                Polizas.Poliza.Transaccion ultimaTransaccion = null;
                                if(tamanoListaPolizaDetalles>0){
                                    ultimaPoliza = polizasDatos.getLineaDatosPolizaDetalle().get(tamanoListaPolizaDetalles - 1).getDatosPoliza().getPoliza();
                                    
                                    tamanoListaTransaccion = ultimaPoliza.getTransaccion().size();
                                    if (tamanoListaTransaccion>0)
                                        ultimaTransaccion = ultimaPoliza.getTransaccion().get(tamanoListaTransaccion - 1);
                                }
                                
                                //Registro 122 - Transaccion
                                if (line.startsWith(CmmCvsPolizasTransaccionUtil.idRegistro)){
                                    if (ultimaPoliza!=null){
                                        ultimaPoliza = CmmCvsPolizasTransaccionUtil.fillData(line, ultimaPoliza);
                                    }
                                }
                                
                                if (ultimaPoliza!=null && ultimaTransaccion!=null){
                                    //Registro 123 - CompNal 
                                    if(line.startsWith(CmmCvsPolizasCompNalUtil.idRegistro)){
                                        ultimaTransaccion = CmmCvsPolizasCompNalUtil.fillData(line, ultimaTransaccion);
                                    }
                                    //Registro 124 - CompNalOtr
                                    if(line.startsWith(CmmCvsPolizasCompNalOtrUtil.idRegistro)){
                                        ultimaTransaccion = CmmCvsPolizasCompNalOtrUtil.fillData(line, ultimaTransaccion);
                                    }
                                    //Registro 125 - CompExt
                                    if(line.startsWith(CmmCvsPolizasCompExtUtil.idRegistro)){
                                        ultimaTransaccion = CmmCvsPolizasCompExtUtil.fillData(line, ultimaTransaccion);
                                    }
                                    
                                    //Registro 126 - Cheque
                                    if (line.startsWith(CmmCvsPolizasChequeUtil.idRegistro)){
                                        ultimaTransaccion = CmmCvsPolizasChequeUtil.fillData(line, ultimaTransaccion);
                                    }
                                    
                                    //Registro 127 - Transferencia
                                    if (line.startsWith(CmmCvsPolizasTransferenciaUtil.idRegistro)){
                                        ultimaTransaccion = CmmCvsPolizasTransferenciaUtil.fillData(line, ultimaTransaccion);
                                    }
                                    
                                    //Registro 128 - OtrMetodoPago
                                    if (line.startsWith(CmmCvsPolizasOtrMetodoPagoUtil.idRegistro)){
                                        ultimaTransaccion = CmmCvsPolizasOtrMetodoPagoUtil.fillData(line, ultimaTransaccion);
                                    }
                                    
                                    ultimaPoliza.getTransaccion().set(tamanoListaTransaccion - 1, ultimaTransaccion);
                                }
                                
                                if (ultimaPoliza!=null){
                                    polizasDatos.getLineaDatosPolizaDetalle().get(tamanoListaPolizaDetalles - 1).getDatosPoliza().setPoliza(ultimaPoliza);
                                }
                                
                        }
                    }
                }
            }
        }catch (Exception ex){
            throw new Exception(" Error en conversión Formato Polizas de Comprobación :" + ex.getMessage());
        }
        
        return polizasDatos;
    }

}