/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.vwpmt.bean;

import java.util.ArrayList;
import java.util.List;
import mx.bigdata.sat.addenda.vwpmt.schema.Locacion;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 6/02/2015 12:15:30 PM
 */
public class AddendaVwPmtDatos {

    private LineaDatosVWGenerales lineaDatosVWGenerales;
    private LineaDatosVWCancelaciones lineaDatosVWCancelaciones;
    private LineaDatosVWMoneda lineaDatosVWMoneda;
    private Locacion proveedor;
    private Locacion origen;
    private LineaDatosVWDestino lineaDatosVWDestino;
    private LineaDatosVWMedidas lineaDatosVWMedidas;
    private LineaDatosVWReferencias lineaDatosVWReferencias;
    private List<String> notas;
    private List<LineaDatosVWArchivo> listaLineaDatosVWArchivo;
    private List<LineaDatosVWParte> listaLineaDatosVWParte;

    public LineaDatosVWGenerales getLineaDatosVWGenerales() {
        return lineaDatosVWGenerales;
    }

    public void setLineaDatosVWGenerales(LineaDatosVWGenerales lineaDatosVWGenerales) {
        this.lineaDatosVWGenerales = lineaDatosVWGenerales;
    }

    public LineaDatosVWCancelaciones getLineaDatosVWCancelaciones() {
        return lineaDatosVWCancelaciones;
    }

    public void setLineaDatosVWCancelaciones(LineaDatosVWCancelaciones lineaDatosVWCancelaciones) {
        this.lineaDatosVWCancelaciones = lineaDatosVWCancelaciones;
    }

    public LineaDatosVWMoneda getLineaDatosVWMoneda() {
        return lineaDatosVWMoneda;
    }

    public void setLineaDatosVWMoneda(LineaDatosVWMoneda lineaDatosVWMoneda) {
        this.lineaDatosVWMoneda = lineaDatosVWMoneda;
    }

    public Locacion getProveedor() {
        return proveedor;
    }

    public void setProveedor(Locacion proveedor) {
        this.proveedor = proveedor;
    }

    public Locacion getOrigen() {
        return origen;
    }

    public void setOrigen(Locacion origen) {
        this.origen = origen;
    }

    public LineaDatosVWDestino getLineaDatosVWDestino() {
        return lineaDatosVWDestino;
    }

    public void setLineaDatosVWDestino(LineaDatosVWDestino lineaDatosVWDestino) {
        this.lineaDatosVWDestino = lineaDatosVWDestino;
    }

    public LineaDatosVWMedidas getLineaDatosVWMedidas() {
        return lineaDatosVWMedidas;
    }

    public void setLineaDatosVWMedidas(LineaDatosVWMedidas lineaDatosVWMedidas) {
        this.lineaDatosVWMedidas = lineaDatosVWMedidas;
    }

    public LineaDatosVWReferencias getLineaDatosVWReferencias() {
        return lineaDatosVWReferencias;
    }

    public void setLineaDatosVWReferencias(LineaDatosVWReferencias lineaDatosVWReferencias) {
        this.lineaDatosVWReferencias = lineaDatosVWReferencias;
    }

    public List<String> getNotas() {
        if (notas==null)
            notas = new ArrayList<String>();
        return notas;
    }

    public List<LineaDatosVWArchivo> getListaLineaDatosVWArchivo() {
        if (listaLineaDatosVWArchivo==null)
            listaLineaDatosVWArchivo = new ArrayList<LineaDatosVWArchivo>();
        return listaLineaDatosVWArchivo;
    }

    public List<LineaDatosVWParte> getListaLineaDatosVWParte() {
        if (listaLineaDatosVWParte==null)
            listaLineaDatosVWParte = new ArrayList<LineaDatosVWParte>();
        return listaLineaDatosVWParte;
    }
    
}
