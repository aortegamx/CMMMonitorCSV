/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.complementos.donat.bean;

/**
 *
 * @author leonardo
 */
public class DonatDatos {
    private LineaDatosDonat lineaDatosDonat;   
    
    public LineaDatosDonat getLineaDatosDonat() {
        if(lineaDatosDonat==null){
            lineaDatosDonat = new LineaDatosDonat();
        }
        return lineaDatosDonat;
    }

    public void setLineaDatosDonat(LineaDatosDonat lineaDatosDonat) {
        this.lineaDatosDonat = lineaDatosDonat;
    }

}
