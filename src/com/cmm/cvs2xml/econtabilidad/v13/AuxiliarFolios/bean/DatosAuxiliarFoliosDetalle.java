/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.bean;

import mx.bigdata.sat.econtabilidad.v13.schema.folios.RepAuxFol;

/**
 *
 * @author user
 */
public class DatosAuxiliarFoliosDetalle {
    
    private RepAuxFol.DetAuxFol auxFoliosDetalle;

    /**
     * @return the auxFoliosDetalle
     */
    public RepAuxFol.DetAuxFol getAuxFoliosDetalle() {
        return auxFoliosDetalle;
    }

    /**
     * @param auxFoliosDetalle the auxFoliosDetalle to set
     */
    public void setAuxFoliosDetalle(RepAuxFol.DetAuxFol auxFoliosDetalle) {
        this.auxFoliosDetalle = auxFoliosDetalle;
    }
    
}
