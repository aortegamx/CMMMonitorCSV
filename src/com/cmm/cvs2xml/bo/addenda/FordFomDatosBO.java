/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.bo.addenda;

import com.cmm.cvs2xml.addendas.fordfom.bean.AddendaFordFomDatos;
import mx.bigdata.sat.addenda.fordfom.schema.Addenda;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 08/02/2016 06:41:13 PM
 */
public class FordFomDatosBO {

    public static Addenda compilarAddenda(AddendaFordFomDatos addendaDatos) {
        Addenda addenda = null;

        if (addendaDatos != null) {
            if (addendaDatos.getLineaDatosFordFom()!= null) {

                addenda = new Addenda();

                addenda.setFOMASN(addendaDatos.getLineaDatosFordFom().getFomasn());
                if (addenda.getFOMASN()!=null)
                    addenda.getFOMASN().setVersion("1.0");

            }
        }

        return addenda;
    }

}
