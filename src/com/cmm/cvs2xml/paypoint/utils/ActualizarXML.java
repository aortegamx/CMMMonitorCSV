/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.paypoint.utils;

import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

/**
 *
 * @author DXN
 */
public class ActualizarXML {
    public void escrituraXML(Document xmlDocument, String rutaEscritura) {
		try{
		// Lectura de fichero_origen.xml
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder = factory.newDocumentBuilder();
//		Document xmlDocument = builder.parse(new File("fichero_origen.xml"));
		
		// Ahora documento es el XML leido en memoria.

		// Escritura de fichero_destino.xml
                System.out.println("Ruta desde transform: "+rutaEscritura);
                System.out.println("Doc: "+xmlDocument.getTextContent());
                System.out.println("Doc: "+xmlDocument.getDocumentURI());
                System.out.println("Doc: "+xmlDocument.getNodeName());
                System.out.println("Doc: "+xmlDocument.getDocumentElement());
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(xmlDocument);		
		StreamResult result = new StreamResult(new File(rutaEscritura));
		transformer.transform(source, result);
		}
		catch (Exception e) {
                   e.printStackTrace();
			// TODO: handle exception
		}	
	}
}
