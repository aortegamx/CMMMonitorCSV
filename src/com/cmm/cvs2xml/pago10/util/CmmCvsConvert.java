/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.pago10.util;

import com.cmm.cvs2xml.bean.FacturaDatos;
import com.cmm.cvs2xml.pago10.bean.DatosPago;
import com.cmm.cvs2xml.pago10.bean.LineaDatosPago;

/**
 *
 * @author user
 */
public class CmmCvsConvert {
    
    public static void convert(FacturaDatos facturaDatos, String line) throws Exception{
        
        if (facturaDatos!=null){
            try{
                //Registro 40 - Complemento de pago
                if (line.startsWith(CmmCvsPago10DatosGeneralesUtils.idRegistro)){
                    if(facturaDatos.getPagos()!=null){
                        facturaDatos.getPagos().getPago10().add(CmmCvsPago10DatosGeneralesUtils.fillData(line));
                    }else{
                       LineaDatosPago lineaDatosPago=new LineaDatosPago();
                       lineaDatosPago.getPago10().add(CmmCvsPago10DatosGeneralesUtils.fillData(line));
                       if(lineaDatosPago!=null){
                           facturaDatos.setPagos(lineaDatosPago);
                       } 
                    }
                }

                if(facturaDatos.getPagos()!=null){
                    if(facturaDatos.getPagos().getPago10()!=null){
                        //Registro 41 - documentos relacionados
                        if (line.startsWith(CmmCvsPago10DocRelacionadoUtil.idRegistro)){                                    
                            facturaDatos.getPagos().getPago10().get(facturaDatos.getPagos().getPago10().size()-1).getDocRelacionado().add(CmmCvsPago10DocRelacionadoUtil.fillData(line));
                        }
                    }
                }
            }catch(Exception ex){
                ex.printStackTrace();
                throw new Exception(" Error en conversión Formato Complemento de Comprobación :" + ex.getCause());
            }
        }
    }
}
