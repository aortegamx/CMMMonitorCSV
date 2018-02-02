/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.paypoint.utils;

import com.cmm.cvs2xml.paypoint.bean.AtributosPayPoint;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DXN
 */
public class LineaDatoPayPointUtilidad {
    
    private AtributosPayPoint atributosPayPoint;
    
    private List<AtributosPayPoint> atributosPayPoints = new ArrayList<AtributosPayPoint>();

    public AtributosPayPoint getAtributosPayPoint() {
        return atributosPayPoint;
    }

    public void setAtributosPayPointl(AtributosPayPoint atributosPayPoint) {
        this.atributosPayPoint = atributosPayPoint;
    }

    public List<AtributosPayPoint> getAtributosPayPoints() {
        return atributosPayPoints;
    }

    public void setAtributosPayPoint(AtributosPayPoint atributosPayPoint) {
        this.atributosPayPoint = atributosPayPoint;
    }
    
    
}
