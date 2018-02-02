/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.complementos.nomina.bean;

import java.util.ArrayList;
import java.util.List;
import mx.bigdata.sat.common.nomina12.schema.Nomina;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 12/05/2014 04:21:53 PM
 */
public class NominaDatos {

    private LineaDatosNomina lineaDatosNomina;
    private List<Nomina.Percepciones.Percepcion> listaPercepciones;
    private List<Nomina.Deducciones.Deduccion> listaDeducciones;
    private List<Nomina.Incapacidades.Incapacidad> listaIncapacidades;
    private List<Nomina.Percepciones.Percepcion.HorasExtra> listaHorasExtras;
    private List<Nomina.OtrosPagos.OtroPago> listaOtrosPagos;

    public LineaDatosNomina getLineaDatosNomina() {
        return lineaDatosNomina;
    }

    public void setLineaDatosNomina(LineaDatosNomina lineaDatosNomina) {
        this.lineaDatosNomina = lineaDatosNomina;
    }

    public List<Nomina.Percepciones.Percepcion> getListaPercepciones() {
        if (listaPercepciones==null)
            listaPercepciones = new ArrayList<>();
        return listaPercepciones;
    }

    public List<Nomina.Deducciones.Deduccion> getListaDeducciones() {
        if (listaDeducciones==null)
            listaDeducciones = new ArrayList<>();
        return listaDeducciones;
    }

    public List<Nomina.Incapacidades.Incapacidad> getListaIncapacidades() {
        if (listaIncapacidades==null)
            listaIncapacidades = new ArrayList<>();
        return listaIncapacidades;
    }

    public List<Nomina.Percepciones.Percepcion.HorasExtra> getListaHorasExtras() {
        if (listaHorasExtras==null)
            listaHorasExtras = new ArrayList<>();
        return listaHorasExtras;
    }

    public List<Nomina.OtrosPagos.OtroPago> getListaOtrosPagos() {
         if (listaOtrosPagos==null)
            listaOtrosPagos = new ArrayList<>();
        return listaOtrosPagos;
    }
    
    
}
