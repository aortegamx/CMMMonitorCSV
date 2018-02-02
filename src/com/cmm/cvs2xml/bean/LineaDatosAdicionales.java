/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 8/05/2014 06:07:43 PM
 */
public class LineaDatosAdicionales {

    private List<String> camposAdicionales;
    
    public List<String> getCamposAdicionales(){
        if (camposAdicionales==null)
            camposAdicionales = new ArrayList<String>();
        return camposAdicionales;
    }
    
}
