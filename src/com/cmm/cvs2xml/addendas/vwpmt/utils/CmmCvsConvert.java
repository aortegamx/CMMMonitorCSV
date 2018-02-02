/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.vwpmt.utils;

import com.cmm.cvs2xml.addendas.vwpmt.bean.AddendaVwPmtDatos;
import com.cmm.cvs2xml.bean.FacturaDatos;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 09/02/2015 01:24:18 PM
 */
public class CmmCvsConvert {

    public static void convert(FacturaDatos facturaDatos, String line) throws Exception{
        
        if (facturaDatos!=null){
            try{
                
                //Registro 00210 - Addenda VW PMT, datos generales
                if (line.startsWith(CmmCvsVwPmtGeneralUtils.idRegistro)){

                    facturaDatos.setAddendaVwPmtDatos(new AddendaVwPmtDatos());
                    facturaDatos.getAddendaVwPmtDatos().setLineaDatosVWGenerales(CmmCvsVwPmtGeneralUtils.fillData(line));
                }

                //Solo si el objeto actual ya paso anteriormente por un registro
                // que lleno su atributo Addenda VW PMT
                if (facturaDatos.getAddendaVwPmtDatos()!=null){
                    if (facturaDatos.getAddendaVwPmtDatos().getLineaDatosVWGenerales()!=null){
                        
                        facturaDatos.setTieneAddendas(true);

                        //Registro 00211
                        if (line.startsWith(CmmCvsVwPmtCancelacionesUtils.idRegistro)){
                            facturaDatos.getAddendaVwPmtDatos().setLineaDatosVWCancelaciones(CmmCvsVwPmtCancelacionesUtils.fillData(line));
                        }
                        
                        //Registro 00212
                        if (line.startsWith(CmmCvsVwPmtMonedaUtils.idRegistro)){
                            facturaDatos.getAddendaVwPmtDatos().setLineaDatosVWMoneda(CmmCvsVwPmtMonedaUtils.fillData(line));
                        }
                        
                        //Registro 00213
                        if (line.startsWith(CmmCvsVwPmtProveedorUtils.idRegistro)){
                            facturaDatos.getAddendaVwPmtDatos().setProveedor(CmmCvsVwPmtProveedorUtils.fillData(line));
                        }
                        
                        //Registro 00214
                        if (line.startsWith(CmmCvsVwPmtOrigenUtils.idRegistro)){
                            facturaDatos.getAddendaVwPmtDatos().setOrigen(CmmCvsVwPmtOrigenUtils.fillData(line));
                        }
                        
                        //Registro 00215
                        if (line.startsWith(CmmCvsVwPmtDestinoUtils.idRegistro)){
                            facturaDatos.getAddendaVwPmtDatos().setLineaDatosVWDestino(CmmCvsVwPmtDestinoUtils.fillData(line));
                        }
                        
                        //Registro 00216
                        if (line.startsWith(CmmCvsVwPmtMedidasUtils.idRegistro)){
                            facturaDatos.getAddendaVwPmtDatos().setLineaDatosVWMedidas(CmmCvsVwPmtMedidasUtils.fillData(line));
                        }
                        
                        //Registro 00217
                        if (line.startsWith(CmmCvsVwPmtReferenciasUtils.idRegistro)){
                            facturaDatos.getAddendaVwPmtDatos().setLineaDatosVWReferencias(CmmCvsVwPmtReferenciasUtils.fillData(line));
                        }
                        
                        //Registro 00218
                        if (line.startsWith(CmmCvsVwPmtNotaUtils.idRegistro)){
                            facturaDatos.getAddendaVwPmtDatos().getNotas().add(CmmCvsVwPmtNotaUtils.fillData(line));
                        }
                        
                        //Registro 00219
                        if (line.startsWith(CmmCvsVwPmtArchivoUtils.idRegistro)){
                            facturaDatos.getAddendaVwPmtDatos().getListaLineaDatosVWArchivo().add(CmmCvsVwPmtArchivoUtils.fillData(line));
                        }
                        
                        //Registro 00220
                        if (line.startsWith(CmmCvsVwPmtParteUtils.idRegistro)){
                            facturaDatos.getAddendaVwPmtDatos().getListaLineaDatosVWParte().add(CmmCvsVwPmtParteUtils.fillData(line));
                        }
                            
                    }
                }
            }catch (Exception ex){
                throw new Exception(" [Error Addenda VW PMT v1.0] :" + ex.getMessage());
            }
        }
        
    }
    
}
