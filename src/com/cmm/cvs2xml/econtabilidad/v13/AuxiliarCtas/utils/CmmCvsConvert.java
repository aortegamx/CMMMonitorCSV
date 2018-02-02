/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.utils;

import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.bean.AuxiliarCtasDatos;
import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.bean.LineaDatosAuxiliarCtasCuenta;
import mx.bigdata.sat.econtabilidad.v13.schema.ctas.AuxiliarCtas;

/**
 *
 * @author leonardo
 */
public class CmmCvsConvert {
    
    public static AuxiliarCtasDatos convert(AuxiliarCtasDatos auxiliarCtasDatos, String line) throws Exception{
        
        try{

            //Registro 130 - Auxiliar de cuentas, datos generales
            if (line.startsWith(CmmCvsAuxiliarCtasDatosGeneralesUtils.idRegistro)){

                if (auxiliarCtasDatos==null)
                    auxiliarCtasDatos = new AuxiliarCtasDatos();
                
                auxiliarCtasDatos.setLineaDatosAuxiliarCtas(CmmCvsAuxiliarCtasDatosGeneralesUtils.fillData(line));
            }

            if (auxiliarCtasDatos!=null){
                //Solo si el objeto actual ya paso anteriormente por un registro de inicio para AuxiliarCtas
                if (auxiliarCtasDatos.getLineaDatosAuxiliarCtas()!=null){
                    if (auxiliarCtasDatos.getLineaDatosAuxiliarCtas().getDatosGeneralesAuxiliarCtas()!=null){
                        if (auxiliarCtasDatos.getLineaDatosAuxiliarCtas().getDatosGeneralesAuxiliarCtas().getAuxiliarCtas()!=null){

                                //Registro 131 - Detalles de Cuentas de AuxiliarCtas
                                if (line.startsWith(CmmCvsAuxiliarCtasCuentaUtils.idRegistro)){
                                    LineaDatosAuxiliarCtasCuenta lineaDatosAuxiliarCtasCuenta = CmmCvsAuxiliarCtasCuentaUtils.fillData(line);
                                    
                                    //auxiliarCtasDatos.getCuentas().add(lineaDatosAuxiliarCtasCuenta.getDatosAuxiliarCtasCuenta());
                                    auxiliarCtasDatos.getLineaDatosAuxiliarCtasCuentas().add(lineaDatosAuxiliarCtasCuenta);
                                    ///selecciona
                                }
                                
                                int tamanoListaPolizaDetalles = auxiliarCtasDatos.getLineaDatosAuxiliarCtasCuentas().size();
                                int tamanoListaTransaccion = 0;
                                AuxiliarCtas.Cuenta ultimaCuenta = null;
                                AuxiliarCtas.Cuenta.DetalleAux ultimaDetalle = null;
                                
                                if(tamanoListaPolizaDetalles>0){
                                    ultimaCuenta = auxiliarCtasDatos.getLineaDatosAuxiliarCtasCuentas().get(tamanoListaPolizaDetalles - 1).getDatosAuxiliarCtasCuenta().getCuenta();
                                    
                                    tamanoListaTransaccion = ultimaCuenta.getDetalleAux().size();
                                    if (tamanoListaTransaccion>0)
                                        ultimaDetalle = ultimaCuenta.getDetalleAux().get(tamanoListaTransaccion - 1);
                                }
                                                                
                                //Registro 132 - Detalles de DetalleAux de AuxiliarCtas
                                if (line.startsWith(CmmCvsAuxiliarCtasDetalleAuxUtils.idRegistro)){
                                    if (ultimaCuenta!=null){
                                        ultimaCuenta = CmmCvsAuxiliarCtasDetalleAuxUtils.fillData(line, ultimaCuenta);
                                    }
                                    
                                }
                                
                                if (ultimaCuenta!=null){
                                    auxiliarCtasDatos.getLineaDatosAuxiliarCtasCuentas().get(tamanoListaPolizaDetalles - 1).getDatosAuxiliarCtasCuenta().setCuenta(ultimaCuenta);
                                }
                                
                        }
                    }
                }
            }
        }catch (Exception ex){
            throw new Exception(" Error en conversión Formato Auxiliar cuentas de Comprobación :" + ex.getMessage());
        }
        
        return auxiliarCtasDatos;
    }
    
}
