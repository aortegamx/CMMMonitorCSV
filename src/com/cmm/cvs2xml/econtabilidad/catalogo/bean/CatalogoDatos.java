/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.econtabilidad.catalogo.bean;

import java.util.ArrayList;
import java.util.List;
import mx.bigdata.sat.econtabilidad.v1.catalogo.schema.Catalogo;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 8/09/2014 02:15:51 PM
 */
public class CatalogoDatos {

    private LineaDatosCatalogo lineaDatosCatalogo;
    private List<Catalogo.Ctas> listaCuentas;

    public LineaDatosCatalogo getLineaDatosCatalogo() {
        return lineaDatosCatalogo;
    }

    public void setLineaDatosCatalogo(LineaDatosCatalogo lineaDatosCatalogo) {
        this.lineaDatosCatalogo = lineaDatosCatalogo;
    }

    public List<Catalogo.Ctas> getListaCuentas() {
        if (this.listaCuentas==null)
            this.listaCuentas = new ArrayList<Catalogo.Ctas>();
        return listaCuentas;
    }  
    
}
