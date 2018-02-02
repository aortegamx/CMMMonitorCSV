/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bean;

import java.math.BigDecimal;
import mx.bigdata.sat.cfdi.v33.schema.CTipoFactor;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 23/04/2014 11:40:09 AM
 */
public class LineaDatosFactura {
    
    private DatosComprobante datosComprobante;
    
    private TipoImpuesto tipoImpuesto;
    private BigDecimal IVAGrupalMonto;
    private String IVAGrupalTasa;
    private BigDecimal IEPSGrupalMonto;
    private String IEPSGrupalTasa;
    private BigDecimal IVARetenidoMonto;
    private BigDecimal ISRRetenidoMonto;
    private BigDecimal totalRetenciones;
    private BigDecimal totalTraslados;
    private CTipoFactor tipoFactorIEPS;
    private CTipoFactor tipoFactorIVA;
    
    public DatosComprobante getDatosComprobante() {
        return datosComprobante;
    }

    public void setDatosComprobante(DatosComprobante datosComprobante) {
        this.datosComprobante = datosComprobante;
    }
    
    public TipoImpuesto getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public BigDecimal getIVAGrupalMonto() {
        return IVAGrupalMonto;
    }

    public void setIVAGrupalMonto(BigDecimal IVAGrupalMonto) {
        this.IVAGrupalMonto = IVAGrupalMonto;
    }

    public String getIVAGrupalTasa() {
        return IVAGrupalTasa;
    }

    public void setIVAGrupalTasa(String IVAGrupalTasa) {
        this.IVAGrupalTasa = IVAGrupalTasa;
    }

    public BigDecimal getIEPSGrupalMonto() {
        return IEPSGrupalMonto;
    }

    public void setIEPSGrupalMonto(BigDecimal IEPSGrupalMonto) {
        this.IEPSGrupalMonto = IEPSGrupalMonto;
    }

    public String getIEPSGrupalTasa() {
        return IEPSGrupalTasa;
    }

    public void setIEPSGrupalTasa(String IEPSGrupalTasa) {
        this.IEPSGrupalTasa = IEPSGrupalTasa;
    }

    public BigDecimal getIVARetenidoMonto() {
        return IVARetenidoMonto;
    }

    public void setIVARetenidoMonto(BigDecimal IVARetenidoMonto) {
        this.IVARetenidoMonto = IVARetenidoMonto;
    }

    public BigDecimal getISRRetenidoMonto() {
        return ISRRetenidoMonto;
    }

    public void setISRRetenidoMonto(BigDecimal ISRRetenidoMonto) {
        this.ISRRetenidoMonto = ISRRetenidoMonto;
    }

    public BigDecimal getTotalRetenciones() {
        return totalRetenciones;
    }

    public void setTotalRetenciones(BigDecimal totalRetenciones) {
        this.totalRetenciones = totalRetenciones;
    }

    public BigDecimal getTotalTraslados() {
        return totalTraslados;
    }

    public void setTotalTraslados(BigDecimal totalTraslados) {
        this.totalTraslados = totalTraslados;
    }
        
    public enum TipoImpuesto{
        INDIVIDUAL, 
        GRUPAL
    }
    
    public void setTipoFactorIEPS(CTipoFactor tipoFactor){
        this.tipoFactorIEPS=tipoFactor;
    }

    public CTipoFactor getTipoFactorIEPS() {
        return tipoFactorIEPS;
    }

    public void setTipoFactorIVA(CTipoFactor tipoFactorIVA) {
        this.tipoFactorIVA = tipoFactorIVA;
    }

    public CTipoFactor getTipoFactorIVA() {
        return tipoFactorIVA;
    }
    
}
