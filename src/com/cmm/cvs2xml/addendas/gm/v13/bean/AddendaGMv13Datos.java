/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.addendas.gm.v13.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 15/01/2015 01:28:31 PM
 */
public class AddendaGMv13Datos {

    private LineaDatosGMv13Generales lineaDatosGMv13Generales;
    private List<LineaDatosGMv13Item> listaLineaDatosGMv13Items;

    public LineaDatosGMv13Generales getLineaDatosGMv13Generales() {
        return lineaDatosGMv13Generales;
    }

    public void setLineaDatosGMv13Generales(LineaDatosGMv13Generales lineaDatosGMv13Generales) {
        this.lineaDatosGMv13Generales = lineaDatosGMv13Generales;
    }

    public List<LineaDatosGMv13Item> getListaLineaDatosGMv13Items() {
        if (listaLineaDatosGMv13Items==null)
            listaLineaDatosGMv13Items = new ArrayList<LineaDatosGMv13Item>();
        return listaLineaDatosGMv13Items;
    }
    
}
