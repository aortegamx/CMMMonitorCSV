/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.bo.addenda;

import com.cmm.cvs2xml.addendas.gm.v13.bean.AddendaGMv13Datos;
import com.cmm.cvs2xml.addendas.gm.v13.bean.*;
import mx.bigdata.sat.addenda.gm.v13.schema.ADDENDAGM;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 28/12/2015 06:41:13 PM
 */
public class GMv13DatosBO {

    public static ADDENDAGM compilarAddenda(AddendaGMv13Datos addendaADDENDAGMDatos) {
        ADDENDAGM addenda = null;

        if (addendaADDENDAGMDatos != null) {
            if (addendaADDENDAGMDatos.getLineaDatosGMv13Generales() != null) {

                addenda = new ADDENDAGM();

                addenda.setHEADER(addendaADDENDAGMDatos.getLineaDatosGMv13Generales().getHeader());

                if (addenda.getHEADER() != null) {

                    //Detalles
                    if (addendaADDENDAGMDatos.getListaLineaDatosGMv13Items().size() > 0) {
                        for (LineaDatosGMv13Item lineaItem : addendaADDENDAGMDatos.getListaLineaDatosGMv13Items()) {
                            addenda.getHEADER().getITEM().add(lineaItem.getItem());
                        }
                    }

                }

            }
        }

        return addenda;
    }

}
