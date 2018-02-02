/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.paypoint.utils;

import com.cmm.cvs2xml.paypoint.bean.AtributosPayPoint;
import com.cmm.cvs2xml.paypoint.bean.CreaPayPoint;
import java.util.List;
import org.w3c.dom.Document;

/**
 *
 * @author DXN
 */
public class GeneraProcesoPayPoint {
    	/*public void payPointXML(String xmlDestino, AtributosPayPoint atributos) {

		//ENVIAMOS EL ARCHIVO PARA QUE SEA LEIDO		
		ActualizarXML actualiza = new ActualizarXML();

		//ENVIAMOS LOS DATOS PARA LA CREACION DEL NODO DE LA ADDENDA
		CreaPayPoint payPoint = new CreaPayPoint();

		//ENVIAMOS EL XML LEIDO PARA ANEXARLE LA ADDENDA
		Document xmlDocumentAddenda = payPoint.documentPayPoint(atributos);
		System.out.println("desde generar: "+atributos.getAppID());
		//MANDAMOS A REESCRIBIR EL XML CON LA ADDENDA
		actualiza.escrituraXML(xmlDocumentAddenda, xmlDestino);	

	}*/
        
        public void payPointXML(String xmlDestino, List<AtributosPayPoint> atributos) {

		//ENVIAMOS EL ARCHIVO PARA QUE SEA LEIDO		
		ActualizarXML actualiza = new ActualizarXML();

		//ENVIAMOS LOS DATOS PARA LA CREACION DEL NODO DE LA ADDENDA
		CreaPayPoint payPoint = new CreaPayPoint();

		//ENVIAMOS EL XML LEIDO PARA ANEXARLE LA ADDENDA
		Document xmlDocumentAddenda = payPoint.documentPayPoint(atributos);
		
		//MANDAMOS A REESCRIBIR EL XML CON LA ADDENDA
		actualiza.escrituraXML(xmlDocumentAddenda, xmlDestino);	

	}
}
