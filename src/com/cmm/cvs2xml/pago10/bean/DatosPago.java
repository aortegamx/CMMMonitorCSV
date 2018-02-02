/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.pago10.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class DatosPago {
    private mx.bigdata.sat.cfdi.v33.schema.Pagos.Pago pagos;
    private List<mx.bigdata.sat.cfdi.v33.schema.Pagos.Pago.DoctoRelacionado> docRelacionado=new ArrayList<mx.bigdata.sat.cfdi.v33.schema.Pagos.Pago.DoctoRelacionado>();
    /**
     * @return the pagos
     */
    public mx.bigdata.sat.cfdi.v33.schema.Pagos.Pago getPagos() {
        return pagos;
    }

    /**
     * @param pagos the pagos to set
     */
    public void setPagos(mx.bigdata.sat.cfdi.v33.schema.Pagos.Pago pagos) {
        this.pagos = pagos;
    }

    /**
     * @return the docRelacionado
     */
    public List<mx.bigdata.sat.cfdi.v33.schema.Pagos.Pago.DoctoRelacionado> getDocRelacionado() {
        return docRelacionado;
    }
}
