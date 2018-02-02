/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.utils;

import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.bean.AuxiliarFoliosDatos;
import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.bean.LineaDatosAuxiliarFoliosDetalle;
import mx.bigdata.sat.econtabilidad.v13.schema.folios.RepAuxFol;

/**
 *
 * @author user
 */
public class CmmCvsConvert {
    
    public static AuxiliarFoliosDatos convert(AuxiliarFoliosDatos auxFoliosDatos, String line) throws Exception{
        
        try{

            //Registro 140 - AuxiliarFolios de Comprobación, datos generales
            if (line.startsWith(CmmCvsAuxiliarFoliosGeneralesUtils.idRegistro)){

                if (auxFoliosDatos==null)
                    auxFoliosDatos = new AuxiliarFoliosDatos();
                
                auxFoliosDatos.setLineaDatosAuxiliarFolios(CmmCvsAuxiliarFoliosGeneralesUtils.fillData(line));
            }

            if (auxFoliosDatos!=null){
                //Solo si el objeto actual ya paso anteriormente por un registro de inicio para Polizas
                if (auxFoliosDatos.getLineaDatosAuxiliarFolios()!=null){
                    if (auxFoliosDatos.getLineaDatosAuxiliarFolios().getDatosAuxiliarFolios()!=null){
                        if (auxFoliosDatos.getLineaDatosAuxiliarFolios().getDatosAuxiliarFolios().getAuxFolios()!=null){

                            //Registro 141 - Detalles de Auxiliar Folios
                            if (line.startsWith(CmmCvsAuxiliarFoliosDetalleUtils.idRegistro)){                                    
                                LineaDatosAuxiliarFoliosDetalle ldatosAuxFoliosDetalle = CmmCvsAuxiliarFoliosDetalleUtils.fillData(line);

                                auxFoliosDatos.getLineaDatosAuxiliarFoliosDetalle().add(ldatosAuxFoliosDetalle);
                            }

                            int tamanoListaAuxFolioDetalles = auxFoliosDatos.getLineaDatosAuxiliarFoliosDetalle().size();
                            RepAuxFol.DetAuxFol ultimaAuxFolioDetalle = null;

                            if(tamanoListaAuxFolioDetalles>0){
                                ultimaAuxFolioDetalle=auxFoliosDatos.getLineaDatosAuxiliarFoliosDetalle().get(tamanoListaAuxFolioDetalles-1).getAuxDatosFoliosDetalle().getAuxFoliosDetalle();
                            }

                            //Registro 142 - Detalles de Auxiliar Folios ComprNal
                            if(line.startsWith(CmmCvsAuxiliarFoliosComprNalUtil.idRegistro)){
                                if(ultimaAuxFolioDetalle!=null){
                                    ultimaAuxFolioDetalle=CmmCvsAuxiliarFoliosComprNalUtil.fillData(line, ultimaAuxFolioDetalle);
                                }
                            }
                            
                            //Registro 143 - Detalles de Auxiliar Folios ComprNalOtr
                            if(line.startsWith(CmmCvsAuxiliarFoliosComprNalOtrUtil.idRegistro)){
                                if(ultimaAuxFolioDetalle!=null){
                                    ultimaAuxFolioDetalle=CmmCvsAuxiliarFoliosComprNalOtrUtil.fillData(line, ultimaAuxFolioDetalle);
                                }
                            }
                            
                            //
                            if(line.startsWith(CmmCvsAuxiliarFoliosComprExtUtil.idRegistro)){
                                if(ultimaAuxFolioDetalle!=null){
                                    ultimaAuxFolioDetalle=CmmCvsAuxiliarFoliosComprExtUtil.fillData(line, ultimaAuxFolioDetalle);
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(" Error en conversión Formato Auxiliar Folios de Comprobación :" + ex.getMessage());
        }
        
        return auxFoliosDatos;
    }
}
