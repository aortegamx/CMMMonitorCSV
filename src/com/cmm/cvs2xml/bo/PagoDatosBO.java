/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.bo;

import com.cmm.cvs2xml.pago10.bean.DatosPago;
import com.cmm.cvs2xml.pago10.bean.LineaDatosPago;

/**
 *
 * @author user
 */
public class PagoDatosBO {
    
    public mx.bigdata.sat.cfdi.v33.schema.Pagos compilarPago(LineaDatosPago datosPago){
        mx.bigdata.sat.cfdi.v33.schema.Pagos pagos=new mx.bigdata.sat.cfdi.v33.schema.Pagos();
        pagos.setVersion("1.0");
        for(DatosPago item:datosPago.getPago10()){
            mx.bigdata.sat.cfdi.v33.schema.Pagos.Pago pago=item.getPagos();
            pago.getDoctoRelacionado().addAll(item.getDocRelacionado());
            pagos.getPago().add(pago);
        }
        return pagos;
    }
}
