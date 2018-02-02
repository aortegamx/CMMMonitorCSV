/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.bean;
import java.util.ArrayList;
import java.util.List;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;

/**
 *
 * @author user
 */
public class LineaDatosCFDIRelacionado {
 
    private List<Comprobante.CfdiRelacionados.CfdiRelacionado> datosCFDIRelacionado=new ArrayList<Comprobante.CfdiRelacionados.CfdiRelacionado>();
    private String tipoRelacion;

    /**
     * @return the datosCFDIRelacionado
     */
    public List<Comprobante.CfdiRelacionados.CfdiRelacionado> getDatosCFDIRelacionado() {
        return datosCFDIRelacionado;
    }

    /**
     * @param datosCFDIRelacionado the datosCFDIRelacionado to set
     */
    public void setDatosCFDIRelacionado(List<Comprobante.CfdiRelacionados.CfdiRelacionado> datosCFDIRelacionado) {
        this.datosCFDIRelacionado = datosCFDIRelacionado;
    }

    /**
     * @return the tipoRelacion
     */
    public String getTipoRelacion() {
        return tipoRelacion;
    }

    /**
     * @param tipoRelacion the tipoRelacion to set
     */
    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }
}
