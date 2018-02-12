/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bean;

import com.cmm.cvs2xml.addendas.chryslerppy.bean.AddendaChryslerPpyDatos;
import com.cmm.cvs2xml.addendas.chryslerpua.bean.AddendaChryslerPuaDatos;
import com.cmm.cvs2xml.addendas.fordfom.bean.AddendaFordFomDatos;
import com.cmm.cvs2xml.addendas.gm.v13.bean.AddendaGMv13Datos;
import com.cmm.cvs2xml.addendas.sanofi.bean.AddendaSanofiDatos;
import com.cmm.cvs2xml.addendas.soriana.bean.AddendaSorianaDatos;
import com.cmm.cvs2xml.addendas.soriana.bean.AddendaSorianaPieDeCamionDatos;
import com.cmm.cvs2xml.addendas.vwpmt.bean.AddendaVwPmtDatos;
import com.cmm.cvs2xml.complementos.donat.bean.DonatDatos;
import com.cmm.cvs2xml.complementos.implocal.bean.ImpLocalDatos;
import com.cmm.cvs2xml.complementos.nomina.bean.NominaDatos;
import com.cmm.cvs2xml.pago10.bean.LineaDatosPago;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/05/2014 10:38:17 AM
 */
public class FacturaDatos {
    
    private LineaDatosFactura lineaDatosFactura;
    private LineaDatosCliente lineaDatosCliente;
    private LineaDatosEmisor lineaDatosEmisor;
    private LineaDatosAdicionales lineaDatosAdicionales;
    private List<LineaDatosConcepto> listaLineaDatosConceptos;
    private LineaDatosCFDIRelacionado listaDatosCFDIRelacionado;
    
    private NominaDatos nominaDatos;
    private ImpLocalDatos impLocalDatos;
    
    private DonatDatos donatDatos;
    
    private LineaDatosAccionPersonalizada lineaDatosAccionPersonalizada;
    private LineaDatosTipoObjetoConcepto lineaDatosTipoObjetoConcepto;
    
    private LineaDatosPago pagos;
    
    private boolean tieneAddendas = false;
    private AddendaSanofiDatos addendaSanofiDatos;
    private AddendaVwPmtDatos addendaVwPmtDatos;
    private AddendaChryslerPuaDatos addendaChryslerPuaDatos;
    private AddendaGMv13Datos addendaGMv13Datos;
    private AddendaChryslerPpyDatos addendaChryslerPpyDatos;
    private AddendaFordFomDatos addendaFordFomDatos;
    private AddendaSorianaDatos addendaSorianaDatos;
    private AddendaSorianaPieDeCamionDatos addendaSorianaPieDeCamionDatos;

    public LineaDatosFactura getLineaDatosFactura() {
        return lineaDatosFactura;
    }

    public void setLineaDatosFactura(LineaDatosFactura lineaDatosFactura) {
        this.lineaDatosFactura = lineaDatosFactura;
    }

    public LineaDatosCliente getLineaDatosCliente() {
        return lineaDatosCliente;
    }

    public void setLineaDatosCliente(LineaDatosCliente lineaDatosCliente) {
        this.lineaDatosCliente = lineaDatosCliente;
    }

    public LineaDatosAdicionales getLineaDatosAdicionales() {
        return lineaDatosAdicionales;
    }

    public void setLineaDatosAdicionales(LineaDatosAdicionales lineaDatosAdicionales) {
        this.lineaDatosAdicionales = lineaDatosAdicionales;
    }

    public List<LineaDatosConcepto> getListaLineaDatosConceptos() {
        if (listaLineaDatosConceptos==null)
            listaLineaDatosConceptos = new ArrayList<LineaDatosConcepto>();
        return listaLineaDatosConceptos;
    }

    public NominaDatos getNominaDatos() {
        return nominaDatos;
    }

    public void setNominaDatos(NominaDatos nominaDatos) {
        this.nominaDatos = nominaDatos;
    }

    public ImpLocalDatos getImpLocalDatos() {
        return impLocalDatos;
    }

    public void setImpLocalDatos(ImpLocalDatos impLocalDatos) {
        this.impLocalDatos = impLocalDatos;
    }

    public LineaDatosAccionPersonalizada getLineaDatosAccionPersonalizada() {
        return lineaDatosAccionPersonalizada;
    }

    public void setLineaDatosAccionPersonalizada(LineaDatosAccionPersonalizada lineaDatosAccionPersonalizada) {
        this.lineaDatosAccionPersonalizada = lineaDatosAccionPersonalizada;
    }

    public AddendaSanofiDatos getAddendaSanofiDatos() {
        return addendaSanofiDatos;
    }

    public void setAddendaSanofiDatos(AddendaSanofiDatos addendaSanofiDatos) {
        this.addendaSanofiDatos = addendaSanofiDatos;
    }

    public boolean isTieneAddendas() {
        return tieneAddendas;
    }

    public void setTieneAddendas(boolean tieneAddendas) {
        this.tieneAddendas = tieneAddendas;
    }

    public AddendaVwPmtDatos getAddendaVwPmtDatos() {
        return addendaVwPmtDatos;
    }

    public void setAddendaVwPmtDatos(AddendaVwPmtDatos addendaVwPmtDatos) {
        this.addendaVwPmtDatos = addendaVwPmtDatos;
    }

    public AddendaChryslerPuaDatos getAddendaChryslerPuaDatos() {
        return addendaChryslerPuaDatos;
    }

    public void setAddendaChryslerPuaDatos(AddendaChryslerPuaDatos addendaChryslerPuaDatos) {
        this.addendaChryslerPuaDatos = addendaChryslerPuaDatos;
    }

    public AddendaGMv13Datos getAddendaGMv13Datos() {
        return addendaGMv13Datos;
    }

    public void setAddendaGMv13Datos(AddendaGMv13Datos addendaGMv13Datos) {
        this.addendaGMv13Datos = addendaGMv13Datos;
    }

    public LineaDatosTipoObjetoConcepto getLineaDatosTipoObjetoConcepto() {
        return lineaDatosTipoObjetoConcepto;
    }

    public void setLineaDatosTipoObjetoConcepto(LineaDatosTipoObjetoConcepto lineaDatosTipoObjetoConcepto) {
        this.lineaDatosTipoObjetoConcepto = lineaDatosTipoObjetoConcepto;
    }

    public AddendaFordFomDatos getAddendaFordFomDatos() {
        return addendaFordFomDatos;
    }

    public void setAddendaFordFomDatos(AddendaFordFomDatos addendaFordFomDatos) {
        this.addendaFordFomDatos = addendaFordFomDatos;
    }

    public AddendaChryslerPpyDatos getAddendaChryslerPpyDatos() {
        return addendaChryslerPpyDatos;
    }

    public void setAddendaChryslerPpyDatos(AddendaChryslerPpyDatos addendaChryslerPpyDatos) {
        this.addendaChryslerPpyDatos = addendaChryslerPpyDatos;
    }

    /**
     * @return the pagos
     */
    public LineaDatosPago getPagos() {
        return pagos;
    }

    /**
     * @param pagos the pagos to set
     */
    public void setPagos(LineaDatosPago pagos) {
        this.pagos = pagos;
    }

    public DonatDatos getDonatDatos() {
        return donatDatos;
    }

    public void setDonatDatos(DonatDatos donatDatos) {
        this.donatDatos = donatDatos;
    }

    public LineaDatosEmisor getLineaDatosEmisor() {
        return lineaDatosEmisor;
    }

    public void setLineaDatosEmisor(LineaDatosEmisor lineaDatosEmisor) {
        this.lineaDatosEmisor = lineaDatosEmisor;
    }

    /**
     * @return the listaDatosCFDIRelacionado
     */
    public LineaDatosCFDIRelacionado getListaDatosCFDIRelacionado() {
        return listaDatosCFDIRelacionado;
    }

    /**
     * @param listaDatosCFDIRelacionado the listaDatosCFDIRelacionado to set
     */
    public void setListaDatosCFDIRelacionado(LineaDatosCFDIRelacionado listaDatosCFDIRelacionado) {
        this.listaDatosCFDIRelacionado = listaDatosCFDIRelacionado;
    }
    
    public AddendaSorianaDatos getAddendaSorianaDatos() {
        return addendaSorianaDatos;
    }

    public void setAddendaSorianaDatos(AddendaSorianaDatos addendaSorianaDatos) {
        this.addendaSorianaDatos = addendaSorianaDatos;
    }

    public AddendaSorianaPieDeCamionDatos getAddendaSorianaPieDeCamionDatos() {
        return addendaSorianaPieDeCamionDatos;
    }

    public void setAddendaSorianaPieDeCamionDatos(AddendaSorianaPieDeCamionDatos addendaSorianaPieDeCamionDatos) {
        this.addendaSorianaPieDeCamionDatos = addendaSorianaPieDeCamionDatos;
    }
}
