/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.paypoint.bean;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
/**
 *
 * @author DXN
 */
public class CreaPayPoint {
    //public Document documentPayPoint(AtributosPayPoint atributos) {
    public Document documentPayPoint(List<AtributosPayPoint> atributos) {

	try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;
            docBuilder = docFactory.newDocumentBuilder();
            Document xmlDocument = docBuilder.newDocument();
		System.out.println("APP_ID: "+atributos.get(0).getAppID());
                System.out.println("Type: "+atributos.get(0).getType());
            Element rootElement = xmlDocument.createElement("BulkPaymentFile");
            rootElement.setAttribute("Type", atributos.get(0).getType());
            rootElement.setAttribute("AppID", atributos.get(0).getAppID());
	
            Element customDataElement = xmlDocument.createElement("CustomData");
            Element CommentsElement = xmlDocument.createElement("Comments");
            CommentsElement.appendChild(xmlDocument.createTextNode(atributos.get(0).getCustomData()));
            customDataElement.appendChild(CommentsElement);            
            rootElement.appendChild(customDataElement);
            
            for(AtributosPayPoint atributo : atributos){
			
                Element makePaymentRequestElement = xmlDocument.createElement("MakePaymentRequest");
                makePaymentRequestElement.setAttribute("ID", atributo.getID());

                Element paymentAmountElement = xmlDocument.createElement("PaymentAmount");
                paymentAmountElement.appendChild(xmlDocument.createTextNode(atributo.getPaymentAmount()));
                makePaymentRequestElement.appendChild(paymentAmountElement);

                Element referencetElement = xmlDocument.createElement("Reference");
                referencetElement.appendChild(xmlDocument.createTextNode(atributo.getReference()));
                makePaymentRequestElement.appendChild(referencetElement);

                Element paymentInfoElement = xmlDocument.createElement("PaymentInfo");
                Element paymentMediumElement = xmlDocument.createElement("PaymentMedium");
                paymentMediumElement.appendChild(xmlDocument.createTextNode(atributo.getPaymentMedium()));
                paymentInfoElement.appendChild(paymentMediumElement);

                Element paymentInfoCCElement = xmlDocument.createElement("PaymentInfoCC");

                Element cardNumberElement = xmlDocument.createElement("CardNumber");
                cardNumberElement.appendChild(xmlDocument.createTextNode(atributo.getCardNumber()));
                paymentInfoCCElement.appendChild(cardNumberElement);

                Element expirationElement = xmlDocument.createElement("ExpirationDate");
                expirationElement.appendChild(xmlDocument.createTextNode(atributo.getExpirationDate()));
                paymentInfoCCElement.appendChild(expirationElement);

                Element cardStatusElement = xmlDocument.createElement("CardStatusFlag");
                cardStatusElement.appendChild(xmlDocument.createTextNode(atributo.getCardStatusFlag()));
                paymentInfoCCElement.appendChild(cardStatusElement);

                Element installmentElement = xmlDocument.createElement("Installment");
                installmentElement.appendChild(xmlDocument.createTextNode(atributo.getInstallment()));
                paymentInfoCCElement.appendChild(installmentElement);

                Element installmentSequenceElement = xmlDocument.createElement("InstallmentSequence");
                installmentSequenceElement.appendChild(xmlDocument.createTextNode(atributo.getInstallmentSequence()));
                paymentInfoCCElement.appendChild(installmentSequenceElement);

                Element purchaseIDElement = xmlDocument.createElement("PurchaseID");
                purchaseIDElement.appendChild(xmlDocument.createTextNode(atributo.getPurchaseID()));
                paymentInfoCCElement.appendChild(purchaseIDElement);

                Element billingAddresElement = xmlDocument.createElement("BillingAddress");

                Element NameFullElement = xmlDocument.createElement("NameFull");
                
                try{
                    byte ptext[] = atributo.getNameFull().getBytes();
                    String value = new String(ptext, "UTF-8");                
                    NameFullElement.appendChild(xmlDocument.createTextNode(value));
                }catch(Exception e ){
                    System.out.println("------ cargando namne full");
                    NameFullElement.appendChild(xmlDocument.createTextNode(atributo.getNameFull()));
                }
                
                
                billingAddresElement.appendChild(NameFullElement);
                paymentInfoCCElement.appendChild(billingAddresElement);
                //billingAddresElement.appendChild(xmlDocument.createTextNode(atributo.getBillingAddress()));
                //paymentInfoCCElement.appendChild(billingAddresElement);



                paymentInfoElement.appendChild(paymentInfoCCElement);

                makePaymentRequestElement.appendChild(paymentInfoElement);

                Element headerElement = xmlDocument.createElement("Header");

                Element applicationIDElement = xmlDocument.createElement("ApplicationID");
                applicationIDElement.appendChild(xmlDocument.createTextNode(atributo.getApplicationID()));
                headerElement.appendChild(applicationIDElement);

                Element securityKeyElement = xmlDocument.createElement("SecurityKey");
                Node n = securityKeyElement.appendChild(xmlDocument.createTextNode(atributo.getSecurityKey()));
                headerElement.appendChild(securityKeyElement);

                Element paymentChannelElement = xmlDocument.createElement("PaymentChannel");
                paymentChannelElement.appendChild(xmlDocument.createTextNode(atributo.getPaymentChannel()));
                headerElement.appendChild(paymentChannelElement);

                makePaymentRequestElement.appendChild(headerElement);

                rootElement.appendChild(makePaymentRequestElement);
            }
            
            			
            xmlDocument.appendChild(rootElement);            
            System.out.println("generado el XML");            
            return xmlDocument;
	} catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
	}
					
    }
}
