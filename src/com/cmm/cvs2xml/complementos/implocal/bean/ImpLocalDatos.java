/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.complementos.implocal.bean;

import java.util.ArrayList;
import java.util.List;
import mx.bigdata.sat.common.implocal.schema.ImpuestosLocales;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 15/05/2014 05:36:09 PM
 */
public class ImpLocalDatos {
    
    private LineaDatosImpLocal lineaDatosImpLocal;
    private List<ImpuestosLocales.RetencionesLocales> listaRetencionesLocales;
    private List<ImpuestosLocales.TrasladosLocales> listaTrasladosLocales;

    public LineaDatosImpLocal getLineaDatosImpLocal() {
        return lineaDatosImpLocal;
    }

    public void setLineaDatosImpLocal(LineaDatosImpLocal lineaDatosImpLocal) {
        this.lineaDatosImpLocal = lineaDatosImpLocal;
    }

    public List<ImpuestosLocales.RetencionesLocales> getListaRetencionesLocales() {
        if (listaRetencionesLocales==null)
            listaRetencionesLocales = new ArrayList<ImpuestosLocales.RetencionesLocales>();
        return listaRetencionesLocales;
    }

    public List<ImpuestosLocales.TrasladosLocales> getListaTrasladosLocales() {
        if (listaTrasladosLocales==null)
            listaTrasladosLocales = new ArrayList<ImpuestosLocales.TrasladosLocales>();
        return listaTrasladosLocales;
    }

}