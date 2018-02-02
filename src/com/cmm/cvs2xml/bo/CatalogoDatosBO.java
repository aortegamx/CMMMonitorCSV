/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bo;

import com.cmm.cvs2xml.econtabilidad.v13.catalogo.bean.CatalogoDatos;
import mx.bigdata.sat.econtabilidad.v13.schema.cuentas.Catalogo;
import mx.bigdata.sat.econtabilidad.v13.schema.cuentas.ObjectFactory;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 8/09/2014 06:00:30 PM
 */
public class CatalogoDatosBO {

    private CatalogoDatos catalogoDatos;

    public CatalogoDatosBO() {
    }

    public CatalogoDatosBO(CatalogoDatos catalogoDatos) {
        this.catalogoDatos = catalogoDatos;
    }
    
    /**
     * Reune todo los datos del objeto CatalogoDatos
     * y los une en un solo objeto de Catalogo, el cual 
     * no incluira datos adicionales que no correspondan al esquema
     * oficial publicado por el SAT.
     * @return objeto Catalogo
     */
    public Catalogo compilarFormato(){
        if (catalogoDatos==null)
            return null;
       
        ObjectFactory of = new ObjectFactory();
        Catalogo catalogo = catalogoDatos.getLineaDatosCatalogo().getDatosGeneralesCatalogo().getCatalogo();
        catalogo.setVersion("1.3");
        
        for (Catalogo.Ctas cta : catalogoDatos.getListaCuentas()){
            catalogo.getCtas().add(cta);
        }
        
        return catalogo;
    }
    
    public CatalogoDatos getCatalogoDatos() {
        return catalogoDatos;
    }

    public void setCatalogoDatos(CatalogoDatos catalogoDatos) {
        this.catalogoDatos = catalogoDatos;
    }
    
    
}
