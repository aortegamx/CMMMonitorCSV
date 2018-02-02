/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.pago10.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class LineaDatosPago {
    private List<DatosPago> pago10=new ArrayList<DatosPago>();

    /**
     * @return the pago10
     */
    public List<DatosPago> getPago10() {
        return pago10;
    }

    /**
     * @param pago10 the pago10 to set
     */
    public void setPago10(List<DatosPago> pago10) {
        this.pago10 = pago10;
    }

}
