/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.chryslerppy.bean;

import java.util.ArrayList;
import java.util.List;
import mx.bigdata.sat.addenda.chryslerppy.schema.Locacion;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 19/02/2015 01:17:06 PM
 */
public class AddendaChryslerPpyDatos {

    private LineaDatosChryslerGenerales lineaDatosChryslerGenerales;
    //private LineaDatosChryslerCancelaciones lineaDatosChryslerCancelaciones;
    private List<LineaDatosChryslerCancelaciones> listaLineaDatosChryslerCancelaciones;
    private LineaDatosChryslerMoneda lineaDatosChryslerMoneda;
    private Locacion proveedor;
    private Locacion origen;
    private Locacion destino;
    private Locacion receiving;
    private List<String> notas;
    private List<LineaDatosChryslerCargosCreditos> listaLineaDatosChryslerCargosCreditos;
    private List<LineaDatosChryslerOtrosCargos> listaLineaDatosChryslerOtrosCargos;
    private List<LineaDatosChryslerParte> listaLineaDatosChryslerParte;
    private LineaDatosChryslerProyecto lineaDatosChryslerProyecto; 

    public LineaDatosChryslerGenerales getLineaDatosChryslerGenerales() {
        return lineaDatosChryslerGenerales;
    }

    public void setLineaDatosChryslerGenerales(LineaDatosChryslerGenerales lineaDatosChryslerGenerales) {
        this.lineaDatosChryslerGenerales = lineaDatosChryslerGenerales;
    }

    public LineaDatosChryslerMoneda getLineaDatosChryslerMoneda() {
        return lineaDatosChryslerMoneda;
    }

    public void setLineaDatosChryslerMoneda(LineaDatosChryslerMoneda lineaDatosChryslerMoneda) {
        this.lineaDatosChryslerMoneda = lineaDatosChryslerMoneda;
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

    public Locacion getDestino() {
        return destino;
    }

    public void setDestino(Locacion destino) {
        this.destino = destino;
    }

    public Locacion getReceiving() {
        return receiving;
    }

    public void setReceiving(Locacion receiving) {
        this.receiving = receiving;
    }

    public List<String> getNotas() {
        if (notas==null)
            notas = new ArrayList<String>();
        return notas;
    }

    public List<LineaDatosChryslerCargosCreditos> getListaLineaDatosChryslerCargosCreditos() {
        if (listaLineaDatosChryslerCargosCreditos==null)
            listaLineaDatosChryslerCargosCreditos = new ArrayList<LineaDatosChryslerCargosCreditos>();
        return listaLineaDatosChryslerCargosCreditos;
    }

    public List<LineaDatosChryslerOtrosCargos> getListaLineaDatosChryslerOtrosCargos() {
        if (listaLineaDatosChryslerOtrosCargos==null)
            listaLineaDatosChryslerOtrosCargos = new ArrayList<LineaDatosChryslerOtrosCargos>();
        return listaLineaDatosChryslerOtrosCargos;
    }

    public List<LineaDatosChryslerParte> getListaLineaDatosChryslerParte() {
        if (listaLineaDatosChryslerParte==null)
            listaLineaDatosChryslerParte = new ArrayList<LineaDatosChryslerParte>();
        return listaLineaDatosChryslerParte;
    }

    public List<LineaDatosChryslerCancelaciones> getListaLineaDatosChryslerCancelaciones() {
        if (listaLineaDatosChryslerCancelaciones==null)
            listaLineaDatosChryslerCancelaciones = new ArrayList<LineaDatosChryslerCancelaciones>();
        return listaLineaDatosChryslerCancelaciones;
    }

    public LineaDatosChryslerProyecto getLineaDatosChryslerProyecto() {
        return lineaDatosChryslerProyecto;
    }

    public void setLineaDatosChryslerProyecto(LineaDatosChryslerProyecto lineaDatosChryslerProyecto) {
        this.lineaDatosChryslerProyecto = lineaDatosChryslerProyecto;
    }
    
}
