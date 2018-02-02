/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bean;

import java.math.BigDecimal;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 30/04/2014 05:37:56 PM
 */
public class DatosConcepto {
    
    private Comprobante.Conceptos.Concepto concepto;
    
    private String nombre;
    private BigDecimal porcentajeDescuento;
    private BigDecimal montoDescuento;
    private BigDecimal IVAporcentaje;
    private BigDecimal IVAmonto;
    private BigDecimal IEPSporcentaje;
    private BigDecimal IEPSmonto;
    private BigDecimal IVARetenidoPorcentaje;
    private BigDecimal IVARetenidoMonto;
    private BigDecimal ISRPorcentaje;
    private BigDecimal ISRMonto;
    private String descripcionServProd;

    public Comprobante.Conceptos.Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Comprobante.Conceptos.Concepto concepto) {
        this.concepto = concepto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public BigDecimal getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(BigDecimal montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    public BigDecimal getIVAporcentaje() {
        return IVAporcentaje;
    }

    public void setIVAporcentaje(BigDecimal IVAporcentaje) {
        this.IVAporcentaje = IVAporcentaje;
    }

    public BigDecimal getIVAmonto() {
        return IVAmonto;
    }

    public void setIVAmonto(BigDecimal IVAmonto) {
        this.IVAmonto = IVAmonto;
    }

    public BigDecimal getIEPSporcentaje() {
        return IEPSporcentaje;
    }

    public void setIEPSporcentaje(BigDecimal IEPSporcentaje) {
        this.IEPSporcentaje = IEPSporcentaje;
    }

    public BigDecimal getIEPSmonto() {
        return IEPSmonto;
    }

    public void setIEPSmonto(BigDecimal IEPSmonto) {
        this.IEPSmonto = IEPSmonto;
    }

    public BigDecimal getIVARetenidoPorcentaje() {
        return IVARetenidoPorcentaje;
    }

    public void setIVARetenidoPorcentaje(BigDecimal IVARetenidoPorcentaje) {
        this.IVARetenidoPorcentaje = IVARetenidoPorcentaje;
    }

    public BigDecimal getIVARetenidoMonto() {
        return IVARetenidoMonto;
    }

    public void setIVARetenidoMonto(BigDecimal IVARetenidoMonto) {
        this.IVARetenidoMonto = IVARetenidoMonto;
    }

    public BigDecimal getISRPorcentaje() {
        return ISRPorcentaje;
    }

    public void setISRPorcentaje(BigDecimal ISRPorcentaje) {
        this.ISRPorcentaje = ISRPorcentaje;
    }

    public BigDecimal getISRMonto() {
        return ISRMonto;
    }

    public void setISRMonto(BigDecimal ISRMonto) {
        this.ISRMonto = ISRMonto;
    }

    /**
     * @return the descripcionServProd
     */
    public String getDescripcionServProd() {
        return descripcionServProd;
    }

    /**
     * @param descripcionServProd the descripcionServProd to set
     */
    public void setDescripcionServProd(String descripcionServProd) {
        this.descripcionServProd = descripcionServProd;
    }

}
