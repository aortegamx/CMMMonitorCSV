/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.retenciones.bean;

import com.cmm.cvs2xml.bean.LineaDatosAccionPersonalizada;
import com.cmm.cvs2xml.bean.LineaDatosAdicionales;
import com.cmm.cvs2xml.retenciones.complementos.arrendamientoenfideicomiso.bean.ArrendamientoEnFideicomisoDatos;
import com.cmm.cvs2xml.retenciones.complementos.dividendos.bean.DividendosDatos;
import com.cmm.cvs2xml.retenciones.complementos.pagosaextranjeros.bean.PagosAExtranjerosDatos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 18/02/2015 12:47:50 PM
 */
public class RetencionesDatos {

    private LineaDatosComprobante lineaDatosComprobante;
    private LineaDatosReceptor lineaDatosReceptor;
    private LineaDatosPeriodoTotales lineaDatosPeriodoTotales;
    private List<LineaDatosImpuestoRetenido> listaLineaDatosImpuestoRetenidos;
    
    private LineaDatosAdicionales lineaDatosAdicionales;
    private LineaDatosAccionPersonalizada lineaDatosAccionPersonalizada;
    
    //Datos Complementos
    private ArrendamientoEnFideicomisoDatos arrendamientoEnFideicomisoDatos;
    private DividendosDatos dividendosDatos;
    private PagosAExtranjerosDatos pagosAExtranjerosDatos;
    
    private boolean tieneAddendas = false;
    
    //Datos Addendas

    public LineaDatosComprobante getLineaDatosComprobante() {
        return lineaDatosComprobante;
    }

    public void setLineaDatosComprobante(LineaDatosComprobante lineaDatosComprobante) {
        this.lineaDatosComprobante = lineaDatosComprobante;
    }
    
    public LineaDatosReceptor getLineaDatosReceptor() {
        return lineaDatosReceptor;
    }

    public void setLineaDatosReceptor(LineaDatosReceptor lineaDatosReceptor) {
        this.lineaDatosReceptor = lineaDatosReceptor;
    }

    public LineaDatosPeriodoTotales getLineaDatosPeriodoTotales() {
        return lineaDatosPeriodoTotales;
    }

    public void setLineaDatosPeriodoTotales(LineaDatosPeriodoTotales lineaDatosPeriodoTotales) {
        this.lineaDatosPeriodoTotales = lineaDatosPeriodoTotales;
    }

    public List<LineaDatosImpuestoRetenido> getListaLineaDatosImpuestoRetenidos() {
        if (listaLineaDatosImpuestoRetenidos==null)
            listaLineaDatosImpuestoRetenidos = new ArrayList<LineaDatosImpuestoRetenido>();
        return listaLineaDatosImpuestoRetenidos;
    }

    public LineaDatosAdicionales getLineaDatosAdicionales() {
        return lineaDatosAdicionales;
    }

    public void setLineaDatosAdicionales(LineaDatosAdicionales lineaDatosAdicionales) {
        this.lineaDatosAdicionales = lineaDatosAdicionales;
    }

    public LineaDatosAccionPersonalizada getLineaDatosAccionPersonalizada() {
        return lineaDatosAccionPersonalizada;
    }

    public void setLineaDatosAccionPersonalizada(LineaDatosAccionPersonalizada lineaDatosAccionPersonalizada) {
        this.lineaDatosAccionPersonalizada = lineaDatosAccionPersonalizada;
    }

    public boolean isTieneAddendas() {
        return tieneAddendas;
    }

    public void setTieneAddendas(boolean tieneAddendas) {
        this.tieneAddendas = tieneAddendas;
    }

    public PagosAExtranjerosDatos getPagosAExtranjerosDatos() {
        return pagosAExtranjerosDatos;
    }

    public void setPagosAExtranjerosDatos(PagosAExtranjerosDatos pagosAExtranjerosDatos) {
        this.pagosAExtranjerosDatos = pagosAExtranjerosDatos;
    }

    public ArrendamientoEnFideicomisoDatos getArrendamientoEnFideicomisoDatos() {
        return arrendamientoEnFideicomisoDatos;
    }

    public void setArrendamientoEnFideicomisoDatos(ArrendamientoEnFideicomisoDatos arrendamientoEnFideicomisoDatos) {
        this.arrendamientoEnFideicomisoDatos = arrendamientoEnFideicomisoDatos;
    }

    public DividendosDatos getDividendosDatos() {
        return dividendosDatos;
    }

    public void setDividendosDatos(DividendosDatos dividendosDatos) {
        this.dividendosDatos = dividendosDatos;
    }

}
