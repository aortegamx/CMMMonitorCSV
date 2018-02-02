/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.bo;

import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.bean.AuxiliarFoliosDatos;
import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.bean.LineaDatosAuxiliarFoliosDetalle;
import mx.bigdata.sat.econtabilidad.v13.schema.folios.RepAuxFol;

/**
 *
 * @author user
 */
public class AuxiliarFoliosDatosBO {
    
    private AuxiliarFoliosDatos auxFoliosDatos;
    
    public AuxiliarFoliosDatosBO(){}
    
    public AuxiliarFoliosDatosBO(AuxiliarFoliosDatos auxFoliosDatos){
        this.auxFoliosDatos=auxFoliosDatos;
    }
    
    public RepAuxFol compilarFormato(){
        if(getAuxFoliosDatos()==null)
            return null;
        
        RepAuxFol auxFolio=getAuxFoliosDatos().getLineaDatosAuxiliarFolios().getDatosAuxiliarFolios().getAuxFolios();
        auxFolio.setVersion("1.3");
                
        for(LineaDatosAuxiliarFoliosDetalle item:getAuxFoliosDatos().getLineaDatosAuxiliarFoliosDetalle()){
            auxFolio.getDetAuxFol().add(item.getAuxDatosFoliosDetalle().getAuxFoliosDetalle());
        }
        
        return auxFolio;
    }

    /**
     * @return the auxFoliosDatos
     */
    public AuxiliarFoliosDatos getAuxFoliosDatos() {
        return auxFoliosDatos;
    }

    /**
     * @param auxFoliosDatos the auxFoliosDatos to set
     */
    public void setAuxFoliosDatos(AuxiliarFoliosDatos auxFoliosDatos) {
        this.auxFoliosDatos = auxFoliosDatos;
    }
}
