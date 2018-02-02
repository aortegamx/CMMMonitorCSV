/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class AuxiliarFoliosDatos {
    
    private LineaDatosAuxiliarFolios lineaDatosAuxiliarFolios;
    private List<LineaDatosAuxiliarFoliosDetalle> lineaDatosAuxiliarFoliosDetalle;

    /**
     * @return the lineaDatosAuxiliarFolios
     */
    public LineaDatosAuxiliarFolios getLineaDatosAuxiliarFolios() {
        return lineaDatosAuxiliarFolios;
    }

    /**
     * @param lineaDatosAuxiliarFolios the lineaDatosAuxiliarFolios to set
     */
    public void setLineaDatosAuxiliarFolios(LineaDatosAuxiliarFolios lineaDatosAuxiliarFolios) {
        this.lineaDatosAuxiliarFolios = lineaDatosAuxiliarFolios;
    }

    /**
     * @return the lineaDatosAuxiliarFoliosDetalle
     */
    public List<LineaDatosAuxiliarFoliosDetalle> getLineaDatosAuxiliarFoliosDetalle() {
        if(lineaDatosAuxiliarFoliosDetalle==null)
            lineaDatosAuxiliarFoliosDetalle=new ArrayList<LineaDatosAuxiliarFoliosDetalle>();
        return lineaDatosAuxiliarFoliosDetalle;
    }

    /**
     * @param lineaDatosAuxiliarFoliosDetalle the lineaDatosAuxiliarFoliosDetalle to set
     */
    public void setLineaDatosAuxiliarFoliosDetalle(List<LineaDatosAuxiliarFoliosDetalle> lineaDatosAuxiliarFoliosDetalle) {
        this.lineaDatosAuxiliarFoliosDetalle = lineaDatosAuxiliarFoliosDetalle;
    }
    
}
