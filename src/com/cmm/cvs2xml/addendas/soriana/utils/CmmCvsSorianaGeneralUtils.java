package com.cmm.cvs2xml.addendas.soriana.utils;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;

import com.cmm.cvs2xml.addendas.soriana.bean.LineaDatosSoriana;
import com.cmm.cvs2xml.addendas.soriana.bean.LineaDatosSorianaPieDeCamion;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;

import mx.bigdata.sat.addenda.soriana.pie.schema.ResponseDSCargaRemisionProvPieDeCamion;
import mx.bigdata.sat.addenda.soriana.schema.AdditionalInformation;
import mx.bigdata.sat.addenda.soriana.schema.AditionalQuantity;
import mx.bigdata.sat.addenda.soriana.schema.AllowanceCharge;
import mx.bigdata.sat.addenda.soriana.schema.AlternatePartyIdentification;
import mx.bigdata.sat.addenda.soriana.schema.AlternateTradeItemIdentification;
import mx.bigdata.sat.addenda.soriana.schema.Amount;
import mx.bigdata.sat.addenda.soriana.schema.Buyer;
import mx.bigdata.sat.addenda.soriana.schema.ContactInformation;
import mx.bigdata.sat.addenda.soriana.schema.Currency;
import mx.bigdata.sat.addenda.soriana.schema.DeliveryNote;
import mx.bigdata.sat.addenda.soriana.schema.InvoiceCreator;
import mx.bigdata.sat.addenda.soriana.schema.InvoicedQuantity;
import mx.bigdata.sat.addenda.soriana.schema.LineItem;
import mx.bigdata.sat.addenda.soriana.schema.MonetaryAmountOrPercentage;
import mx.bigdata.sat.addenda.soriana.schema.NameAndAddress;
import mx.bigdata.sat.addenda.soriana.schema.NetPayment;
import mx.bigdata.sat.addenda.soriana.schema.ObjectFactory;
import mx.bigdata.sat.addenda.soriana.schema.OrderIdentification;
import mx.bigdata.sat.addenda.soriana.schema.PaymentTerms;
import mx.bigdata.sat.addenda.soriana.schema.PaymentTimePeriod;
import mx.bigdata.sat.addenda.soriana.schema.Rate;
import mx.bigdata.sat.addenda.soriana.schema.ReferenceIdentification;
import mx.bigdata.sat.addenda.soriana.schema.RequestForPaymentIdentification;
import mx.bigdata.sat.addenda.soriana.schema.ResponseDSCargaRemisionProv;
import mx.bigdata.sat.addenda.soriana.schema.ResponseDSCargaRemisionProv.RequestForPayment;
import mx.bigdata.sat.addenda.soriana.schema.Seller;
import mx.bigdata.sat.addenda.soriana.schema.ShipTo;
import mx.bigdata.sat.addenda.soriana.schema.SpecialInstruction;
import mx.bigdata.sat.addenda.soriana.schema.Tax;
import mx.bigdata.sat.addenda.soriana.schema.Text;
import mx.bigdata.sat.addenda.soriana.schema.TimePeriodDue;
import mx.bigdata.sat.addenda.soriana.schema.TotalAllowanceCharge;
import mx.bigdata.sat.addenda.soriana.schema.TotalLineAmount;
import mx.bigdata.sat.addenda.soriana.schema.TradeItemDescriptionInformation;
import mx.bigdata.sat.addenda.soriana.schema.TradeItemIdentification;
import mx.bigdata.sat.addenda.soriana.schema.TradeItemTaxAmount;
import mx.bigdata.sat.addenda.soriana.schema.TradeItemTaxInformation;

public class CmmCvsSorianaGeneralUtils {

	public final static String idRegistro = "00250";
	public final static String infoRegistro = "INFORMACION GENERAL PARA ADDENDA SORIANA V1.0";

	public final static String idRegistroPieDeCamion = "00251";
	public final static String infoRegistroPieDeCamion = "INFORMACION GENERAL PARA ADDENDA SORIANA V1.0";

	public static GenericValidator gc = new GenericValidator();

	public static LineaDatosSoriana fillData(String dataLine) {
		LineaDatosSoriana lineaDatosSoriana = null;
		String[] data = dataLine.split("\\|");
		int x;
		if (data.length > 0) {

			lineaDatosSoriana = new LineaDatosSoriana();

			ObjectFactory factory = new ObjectFactory();
			ResponseDSCargaRemisionProv general = factory.createResponseDSCargaRemisionProv();
			OrderIdentification orderIdentification = new OrderIdentification();
			AdditionalInformation additionalInformation = new AdditionalInformation();
			// Se llena tag </requestForPayment>
			RequestForPayment requestForPayment = new RequestForPayment();

			RequestForPaymentIdentification requestForPaymentIdentification = new RequestForPaymentIdentification();
			SpecialInstruction specialInstruction = new SpecialInstruction();
			ReferenceIdentification r = new ReferenceIdentification();
			List<ReferenceIdentification> referenceIdentification = new LinkedList<>();
			ReferenceIdentification rA = new ReferenceIdentification();
			DeliveryNote deliveryNote = new DeliveryNote();
			ReferenceIdentification rD = new ReferenceIdentification();
			Buyer buyer = new Buyer();
			ContactInformation contactInformation = new ContactInformation();
			Text personOrDepartmentName = new Text();
			Seller seller = new Seller();
			AlternatePartyIdentification alternatePartyIdentification = new AlternatePartyIdentification();
			ShipTo shipTo = new ShipTo();
			NameAndAddress nameAndAddress = new NameAndAddress();
			InvoiceCreator invoiceCreator = new InvoiceCreator();
			AlternatePartyIdentification alternatePartyIdentificationInv = new AlternatePartyIdentification();
			NameAndAddress nameAndAddressInv = new NameAndAddress();
			Currency currency = new Currency();
			PaymentTerms paymentTerms = new PaymentTerms();
			NetPayment netPayment = new NetPayment();
			PaymentTimePeriod paymentTimePeriod = new PaymentTimePeriod();
			TimePeriodDue timePeriodDue = new TimePeriodDue();
			AllowanceCharge allowanceCharge = new AllowanceCharge();
			MonetaryAmountOrPercentage monetaryAmountOrPercentage = new MonetaryAmountOrPercentage();
			Rate rate = new Rate();

			List<LineItem> listLineItem = new LinkedList<>();
			LineItem lineItemA = new LineItem();
			TradeItemIdentification tradeItemIdentificationA = new TradeItemIdentification();
			AlternateTradeItemIdentification alternateTradeItemIdentificationA = new AlternateTradeItemIdentification();
			TradeItemDescriptionInformation tradeItemDescriptionInformationA = new TradeItemDescriptionInformation();
			InvoicedQuantity invoicedQuantityA = new InvoicedQuantity();
			AditionalQuantity aditionalQuantityA = new AditionalQuantity();
			Amount amountA = new Amount();
			Amount amountAA = new Amount();
			AdditionalInformation additionalInformationReference = new AdditionalInformation();
			ReferenceIdentification referenceIdentificationAdditional = new ReferenceIdentification();
			TradeItemTaxInformation tradeItemTaxInformationA = new TradeItemTaxInformation();
			TradeItemTaxAmount tradeItemTaxAmountAA = new TradeItemTaxAmount();
			TotalLineAmount totalLineAmountA = new TotalLineAmount();
			Amount amountAAZ = new Amount();
			Amount amountAAY = new Amount();

			Amount amountTotal = new Amount();
			TotalAllowanceCharge totalAllowanceCharge = new TotalAllowanceCharge();
			Amount amountBase = new Amount();
			Tax taxA = new Tax();
			Amount amountPaya = new Amount();

			for (x = 0; x < data.length; x++) {
				String str = data[x];
				if (str != null) {
					str = str.trim();
					if ("".equals(str))
						str = null;
				}
				int count = x + 1;
				switch (count) {
				case 1:
					// Identificador Registro - REQUERIDO
					if (!idRegistro.equals(StringManage.getValidString(str))) {
						throw new IllegalArgumentException(
								"IDENTIFICADOR DE REGISTRO NO VALIDO, DEBE SER ESTRICTAMENTE \"" + idRegistro
										+ "\" PARA " + infoRegistro);
					}
					break;
				case 2:
					// deliveryDate
					if (!gc.isDate(str, "yyyyMMdd")) {
						throw new IllegalArgumentException(
								"DATO deliveryDate, INCORRECTO, DEBE SER UN VALOR TIPO FECHA: " + str);
					}
					requestForPayment.setDeliveryDate(str);
					break;
				case 3:
					// contentVersion
					if (!gc.isVersion(str, 10)) {
						throw new IllegalArgumentException(
								"DATO contentVersion, INCORRECTO, DEBE SER UN VALOR VERSION: " + str);
					}
					requestForPayment.setContentVersion(str);
					break;
				case 4:
					// documentStatus
					if (!("ORIGINAL".equals(StringManage.getValidString(str))
							|| "DELETE".equals(StringManage.getValidString(str))
							|| "COPY".equals(StringManage.getValidString(str))
							|| "REEMPLAZA".equals(StringManage.getValidString(str)))) {
						throw new IllegalArgumentException(
								"DATO documentStatus, INCORRECTO, DEBE SER ORIGINAL DELETE COPY REEMPLAZA: " + str);
					}
					requestForPayment.setDocumentStatus(str);
					break;
				case 5:
					// documentStructureVersion
					if (!gc.isVersion(str, 10)) {
						throw new IllegalArgumentException(
								"DATO documentStructureVersion, INCORRECTO, DEBE SER UN VALOR TIPO FECHA: " + str);
					}
					requestForPayment.setDocumentStructureVersion(str);
					break;
				case 6:
					// type
					if (null == StringManage.getValidString(str)) {
						throw new IllegalArgumentException(
								"DATO type, INCORRECTO, DEBE SER UN VALOR TIPO LETRA: " + str);
					}
					requestForPayment.setType(str);
					break;
				case 7:
					// requestForPaymentIdentification entityType
					if (!("INVOICE".equals(StringManage.getValidString(str))
							|| "DEBIT_NOTE".equals(StringManage.getValidString(str))
							|| "CREDIT_NOTE".equals(StringManage.getValidString(str))
							|| "LEASE_RECEIPT".equals(StringManage.getValidString(str)))) {
						throw new IllegalArgumentException(
								"DATO requestForPaymentIdentification entityType, INCORRECTO, DEBE SER UN VALOR [ INVOICE = Factura DEBIT_NOTE = Nota de debito CREDIT_NOTE = Nota de cr�dito LEASE_RECEIPT = Recibo de ]: "
										+ str);
					}
					requestForPaymentIdentification.setEntityType(str);
					break;
				case 8:
					// requestForPaymentIdentification uniqueCreatorIdentification
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 7)) {
						throw new IllegalArgumentException(
								"DATO requestForPaymentIdentification uniqueCreatorIdentification, INCORRECTO, DEBE SER UN VALOR DE 1 a 7 CARACTERES: "
										+ str);
					}
					requestForPaymentIdentification.setUniqueCreatorIdentification(str);
					requestForPayment.setRequestForPaymentIdentification(requestForPaymentIdentification);
					break;
				case 9:
					// specialInstruction text
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 15)) {
						throw new IllegalArgumentException(
								"DATO specialInstruction text, INCORRECTO, DEBE SER UN VALOR DE 1 a 15 CARACTERES: "
										+ str);
					}
					specialInstruction.setText(str);
					break;
				case 10:
					// specialInstruction code
					if (!("AAB".equals(StringManage.getValidString(str))
							|| "DUT".equals(StringManage.getValidString(str))
							|| "PUR".equals(StringManage.getValidString(str))
							|| "ZZZ".equals(StringManage.getValidString(str)))) {
						throw new IllegalArgumentException(
								"DATO specialInstruction code, INCORRECTO, DEBE SER UN VALOR [ AAB=Condiciones de pago DUT=Informaci�n de impuestos (Pedimentos) PUR=Informaci�n de compras ZZZ =Importe con letra ]: "
										+ str);
					}
					specialInstruction.setCode(str);
					requestForPayment.setSpecialInstruction(specialInstruction);
					break;
				case 11:
					// orderIdentification referenceIdentification
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 7)) {
						throw new IllegalArgumentException(
								"DATO orderIdentification referenceIdentification, INCORRECTO, DEBE SER UN VALOR DE 1 a 7 CARACTERES: "
										+ str);
					}
					r.setValue(str);
					break;
				case 12:
					// orderIdentification referenceIdentification type
					if (str == null) {
						throw new IllegalArgumentException(
								"DATO orderIdentification referenceIdentification type, INCORRECTO, DEBE SER UN VALOR TIPO LETRA: "
										+ str);
					}
					r.setType(str);
					orderIdentification.setReferenceIdentification(r);
					break;
				case 13:
					// orderIdentification referenceDate
					if (!gc.isDate(str, "yyyyMMdd")) {
						throw new IllegalArgumentException(
								"DATO orderIdentification referenceDate, INCORRECTO, DEBE SER UN VALOR TIPO FECHA: "
										+ str);
					}
					orderIdentification.setReferenceDate(str);
					requestForPayment.setOrderIdentification(orderIdentification);
					break;
				case 14:
					// additionalInformation referenceIdentification
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO additionalInformation referenceIdentification, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					rA.setValue(str);
					break;
				case 15:
					// additionalInformation referenceIdentification type
					if (!("AAE".equals(StringManage.getValidString(str))
							|| "CK".equals(StringManage.getValidString(str))
							|| "ACE".equals(StringManage.getValidString(str))
							|| "ATZ".equals(StringManage.getValidString(str))
							|| "AWR".equals(StringManage.getValidString(str))
							|| "ON".equals(StringManage.getValidString(str))
							|| "DQ".equals(StringManage.getValidString(str))
							|| "IV".equals(StringManage.getValidString(str)))) {
						throw new IllegalArgumentException(
								"DATO additionalInformation referenceIdentification type, INCORRECTO, DEBE SER UN VALOR [AAE= Cuenta predial CK = N�mero de cheque ACE= Numero de documento(Reemisi�n) ATZ = N�mero de aprobaci�n. AWR = Numero de documento que  se reemplaza ON  = N�mero de pedido (comprador) DQ = Folio de recibo de mercanc�as IV = N�mero de Factura]: "
										+ str);
					}
					rA.setType(str);
					referenceIdentification.add(rA);
					additionalInformation.getReferenceIdentification().addAll(referenceIdentification);
					requestForPayment.setAdditionalInformation(additionalInformation);
					break;
				case 16:
					// deliveryNote referenceIdentification
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO deliveryNote referenceIdentification, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					rD.setValue(str);
					deliveryNote.setReferenceIdentification(rD);
					break;
				case 17:
					// deliveryNote referenceDate
					if (!gc.isDate(str, "yyyyMMdd")) {
						throw new IllegalArgumentException(
								"DATO deliveryNote referenceDate, INCORRECTO, DEBE SER UN VALOR TIPO FECHA: " + str);
					}
					deliveryNote.setReferenceDate(str);
					requestForPayment.setDeliveryNote(deliveryNote);
					break;
				case 18:
					// buyer gln
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 13)) {
						throw new IllegalArgumentException(
								"DATO buyer gln, INCORRECTO, DEBE SER UN VALOR DE 1 a 13 CARACTERES: " + str);
					}
					buyer.setGln(str);
					break;
				case 19:
					// buyer contactInformation personOrDepartmentName text
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO buyer contactInformation personOrDepartmentName text, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					personOrDepartmentName.setText(str);
					contactInformation.setPersonOrDepartmentName(personOrDepartmentName);
					buyer.setContactInformation(contactInformation);
					requestForPayment.setBuyer(buyer);
					break;
				case 20:
					// seller gln
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 13)) {
						throw new IllegalArgumentException(
								"DATO seller gln, INCORRECTO, DEBE SER UN VALOR DE 1 a 13 CARACTES: " + str);
					}
					seller.setGln(str);
					break;
				case 21:
					// seller alternatePartyIdentification
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO seller alternatePartyIdentification, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTES: "
										+ str);
					}
					alternatePartyIdentification.setContent(str);
					break;
				case 22:
					// seller alternatePartyIdentification type
					if (!("SELLER_ASSIGNED_IDENTIFIER_FOR_A_PARTY".equals(StringManage.getValidString(str))
							|| "IEPS_REFERENCE".equals(StringManage.getValidString(str)))) {
						throw new IllegalArgumentException(
								"DATO seller alternatePartyIdentification type, INCORRECTO, DEBE SER UN VALOR [SELLER_ASSIGNED_IDENTIFIER_FOR_A_PARTY = N�mero interno del proveedor IEPS_REFERENCE = Referencia signada para el IEPS]: "
										+ str);
					}
					alternatePartyIdentification.setType(str);
					seller.setAlternatePartyIdentification(alternatePartyIdentification);
					requestForPayment.setSeller(seller);
					break;
				case 23:
					// shipTo gln
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 15)) {
						throw new IllegalArgumentException(
								"DATO shipTo gln, INCORRECTO, DEBE SER UN VALOR DE 1 a 13 CARACTERES: " + str);
					}
					shipTo.setGln(str);
					break;
				case 24:
					// shipTo nameAndAddress name
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO shipTo nameAndAddress name, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					nameAndAddress.setName(str);
					break;
				case 25:
					// shipTo nameAndAddress streetAddressOne
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO shipTo nameAndAddress streetAddressOne, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					nameAndAddress.setStreetAddressOne(str);
					break;
				case 26:
					// shipTo nameAndAddress city
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO shipTo nameAndAddress city, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					nameAndAddress.setCity(str);
					break;
				case 27:
					// shipTo nameAndAddress postalCode
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 19)) {
						throw new IllegalArgumentException(
								"DATO shipTo nameAndAddress postalCode, INCORRECTO, DEBE SER UN VALOR DE 1 a 19 CARACTERES: "
										+ str);
					}
					nameAndAddress.setPostalCode(str);
					shipTo.setNameAndAddress(nameAndAddress);
					requestForPayment.setShipTo(shipTo);
					break;
				case 28:
					// invoiceCreator gln
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 13)) {
						throw new IllegalArgumentException(
								"DATO invoiceCreator gln, INCORRECTO, DEBE SER UN VALOR DE 1 a 13 CARACTERES: " + str);
					}
					invoiceCreator.setGln(str);
					break;
				case 29:
					// invoiceCreator alternatePartyIdentification
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO invoiceCreator alternatePartyIdentification, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					alternatePartyIdentificationInv.setContent(str);
					break;
				case 30:
					// invoiceCreator alternatePartyIdentification type
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO invoiceCreator alternatePartyIdentification type, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					alternatePartyIdentificationInv.setType(str);
					invoiceCreator.setAlternatePartyIdentification(alternatePartyIdentificationInv);
					break;
				case 31:
					// invoiceCreator nameAndAddress name
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO invoiceCreator nameAndAddress name, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					nameAndAddressInv.setName(str);
					break;
				case 32:
					// invoiceCreator nameAndAddress streetAddressOne
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO invoiceCreator nameAndAddress streetAddressOne, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					nameAndAddressInv.setStreetAddressOne(str);
					break;
				case 33:
					// invoiceCreator nameAndAddress city
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO invoiceCreator nameAndAddress city, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					nameAndAddressInv.setCity(str);
					break;
				case 34:
					// invoiceCreator nameAndAddress postalCode
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO invoiceCreator nameAndAddress postalCode, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					nameAndAddressInv.setPostalCode(str);
					invoiceCreator.setNameAndAddress(nameAndAddressInv);
					requestForPayment.setInvoiceCreator(invoiceCreator);
					break;
				case 35:
					// currency currencyISOCode
					if (!("MXN".equals(StringManage.getValidString(str))
							|| "USD".equals(StringManage.getValidString(str))
							|| "XEU".equals(StringManage.getValidString(str)))) {
						throw new IllegalArgumentException(
								"DATO currency currencyISOCode, INCORRECTO, DEBE SER UN VALOR [MXN= Peso Mexicano USD= D�lar XEU= Euro]: "
										+ str);
					}
					currency.setCurrencyISOCode(str);
					break;
				case 36:
					// currency currencyFunction
					if (!("BILLING_CURRENCY".equals(StringManage.getValidString(str))
							|| "PRICE_CURRENCY".equals(StringManage.getValidString(str))
							|| "PAYMENT_CURRENCY".equals(StringManage.getValidString(str)))) {
						throw new IllegalArgumentException(
								"DATO currency currencyFunction, INCORRECTO, DEBE SER UN VALOR [BILLING_CURRENCY = Divisa de facturaci�n PRICE_CURRENCY = Divisa del precio PAYMENT_CURRENCY = Divisa de pago]: "
										+ str);
					}
					currency.setCurrencyFunction(str);
					break;
				case 37:
					// currency rateOfChange
					if (!gc.isDecimal(str, 1, 3, 1, 3)) {
						throw new IllegalArgumentException(
								"DATO currency rateOfChange, INCORRECTO, DEBE SER UN VALOR TIPO DECIMAL: " + str);
					}
					currency.setRateOfChange(str);
					requestForPayment.setCurrency(currency);
					break;
				case 38:
					// paymentTerms paymentTermsEvent
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 15)) {
						throw new IllegalArgumentException(
								"DATO paymentTerms paymentTermsEvent, INCORRECTO, DEBE SER UN VALOR DE 1 a 15 CARACTERES: "
										+ str);
					}
					paymentTerms.setPaymentTermsEvent(str);
					break;
				case 39:
					// paymentTerms paymentTermsRelationTime
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 15)) {
						throw new IllegalArgumentException(
								"DATO paymentTerms paymentTermsRelationTime, INCORRECTO, DEBE SER UN VALOR DE 1 a 15 CARACTERES: "
										+ str);
					}
					paymentTerms.setPaymentTermsRelationTime(str);
					break;
				case 40:
					// paymentTerms netPayment netPaymentTermsType
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 22)) {
						throw new IllegalArgumentException(
								"DATO paymentTerms netPayment netPaymentTermsType, INCORRECTO, DEBE SER UN VALOR DE 1 a 22 CARACTERES: "
										+ str);
					}
					netPayment.setNetPaymentTermsType(str);
					break;
				case 41:
					// paymentTerms netPayment paymentTimePeriod timePeriodDue timePeriod
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 6)) {
						throw new IllegalArgumentException(
								"DATO paymentTerms netPayment paymentTimePeriod timePeriodDue timePeriod, INCORRECTO, DEBE SER UN VALOR DE 1 a 6 CARACTERES: "
										+ str);
					}
					timePeriodDue.setTimePeriod(str);
					break;
				case 42:
					// paymentTerms netPayment paymentTimePeriod timePeriodDue value
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 5)) {
						throw new IllegalArgumentException(
								"DATO paymentTerms netPayment paymentTimePeriod timePeriodDue value, INCORRECTO, DEBE SER UN VALOR DE 1 a 5 CARACTERES: "
										+ str);
					}
					timePeriodDue.setValue(str);
					paymentTimePeriod.setTimePeriodDue(timePeriodDue);
					netPayment.setPaymentTimePeriod(paymentTimePeriod);
					paymentTerms.setNetPayment(netPayment);
					requestForPayment.setPaymentTerms(paymentTerms);
					break;
				case 43:
					// allowanceCharge allowanceChargeType
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 16)) {
						throw new IllegalArgumentException(
								"DATO allowanceCharge allowanceChargeType, INCORRECTO, DEBE SER UN VALOR DE 1 a 16 CARACTERES: "
										+ str);
					}
					allowanceCharge.setAllowanceChargeType(str);
					break;
				case 44:
					// allowanceCharge settlementType
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 11)) {
						throw new IllegalArgumentException(
								"DATO allowanceCharge settlementType, INCORRECTO, DEBE SER UN VALOR DE 1 a 11 CARACTERES: "
										+ str);
					}
					allowanceCharge.setSettlementType(str);
					break;
				case 45:
					// allowanceCharge specialServicesType
					if (!("AA".equals(StringManage.getValidString(str))
							|| "ABZ".equals(StringManage.getValidString(str))
							|| "ADS".equals(StringManage.getValidString(str))
							|| "ADT".equals(StringManage.getValidString(str))
							|| "ADO".equals(StringManage.getValidString(str))
							|| "AJ".equals(StringManage.getValidString(str))
							|| "CAC".equals(StringManage.getValidString(str))
							|| "COD".equals(StringManage.getValidString(str))
							|| "DA".equals(StringManage.getValidString(str))
							|| "DI".equals(StringManage.getValidString(str))
							|| "EAA".equals(StringManage.getValidString(str))
							|| "EAB".equals(StringManage.getValidString(str))
							|| "FA".equals(StringManage.getValidString(str))
							|| "FC".equals(StringManage.getValidString(str))
							|| "FG".equals(StringManage.getValidString(str))
							|| "FI".equals(StringManage.getValidString(str))
							|| "HD".equals(StringManage.getValidString(str))
							|| "QD".equals(StringManage.getValidString(str))
							|| "PAD".equals(StringManage.getValidString(str))
							|| "PI".equals(StringManage.getValidString(str))
							|| "QD".equals(StringManage.getValidString(str))
							|| "RAA".equals(StringManage.getValidString(str))
							|| "SAB".equals(StringManage.getValidString(str))
							|| "TAE".equals(StringManage.getValidString(str))
							|| "TD".equals(StringManage.getValidString(str))
							|| "TS".equals(StringManage.getValidString(str))
							|| "TX".equals(StringManage.getValidString(str))
							|| "TZ".equals(StringManage.getValidString(str))
							|| "UM".equals(StringManage.getValidString(str))
							|| "VAB".equals(StringManage.getValidString(str))
							|| "ZZZ".equals(StringManage.getValidString(str)))) {
						throw new IllegalArgumentException(
								"DATO allowanceCharge specialServicesType, INCORRECTO, DEBE SER UN VALOR [AA = Abono por Publicidad ABZ = Rebaja o descuento miscel�neo ADS = Pedido de un palet completo ADT = Recolecci�n ADO = Descuento Log�stico AJ   = Ajustes CAC = Descuento / efectivo COD = Pago contra entrega DA  = Descuento defectuoso DI   = Descuento EAA = Descuento por pronta compra de los clientes EAB = Descuento por pronto pago FA  = Descuento por flete FC  = Cargo por flete FG = Descuento por mercanc�as gratuitas FI = Cargo financiero HD = Cargo por manejo de mercanc�a QD  = Cantidad de descuento PAD = Descuento promocional PI  = Descuento por recolecci�n QD = Cantidad Descontada RAA = Rebaja SAB = Descuentos especiales TAE = Descuento por Camioneta TD  = Descuento comercial TS  =  Impuesto estatal TX  = Impuestos TZ = Descuento temporal UM = Descuento de mercanc�a invendible VAB = Descuento por volumen ZZZ = Mutuamente definido]: "
										+ str);
					}
					allowanceCharge.setSpecialServicesType(str);
					break;
				case 46:
					// allowanceCharge monetaryAmountOrPercentage rate percentage
					if (!gc.isDecimal(str, 1, 10, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO allowanceCharge monetaryAmountOrPercentage rate percentage, INCORRECTO, DEBE SER UN VALOR TIPO DECIMAL: "
										+ str);
					}
					rate.setPercentage(str);
					break;
				case 47:
					// allowanceCharge monetaryAmountOrPercentage rate base
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 13)) {
						throw new IllegalArgumentException(
								"DATO allowanceCharge monetaryAmountOrPercentage rate base, INCORRECTO, DEBE SER UN VALOR DE 1 a 13 CARACTERES: "
										+ str);
					}
					rate.setBase(str);
					monetaryAmountOrPercentage.setRate(rate);
					allowanceCharge.setMonetaryAmountOrPercentage(monetaryAmountOrPercentage);
					requestForPayment.setAllowanceCharge(allowanceCharge);
					break;
				case 48:
					// lineItem number
					if (!gc.isNumeric(str, 1, 32)) {
						throw new IllegalArgumentException(
								"DATO lineItem number, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO [1 a 32 CARACTERES]: "
										+ str);
					}
					lineItemA.setNumber(str);
					break;
				case 49:
					// lineItem type
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 32)) {
						throw new IllegalArgumentException(
								"DATO lineItem type, INCORRECTO, DEBE SER UN VALOR DE 1 a 32 CARACTERES: " + str);
					}
					lineItemA.setType(str);
					break;
				case 50:
					// lineItem tradeItemIdentification gtin
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 14)) {
						throw new IllegalArgumentException(
								"DATO lineItem tradeItemIdentification gtin, INCORRECTO, DEBE SER UN VALOR DE 1 a 14 CARACTERES: "
										+ str);
					}
					tradeItemIdentificationA.setGtin(str);
					lineItemA.setTradeItemIdentification(tradeItemIdentificationA);
					break;
				case 51:
					// lineItem alternateTradeItemIdentification
					if (!gc.isNumeric(str, 1, 6)) {
						throw new IllegalArgumentException(
								"DATO lineItem alternateTradeItemIdentification, INCORRECTO, DEBE SER UN VALOR NUMERICO [1 a 999999]: "
										+ str);
					}
					alternateTradeItemIdentificationA.setValue(str);
					break;
				case 52:
					// lineItem alternateTradeItemIdentification type
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO lineItem alternateTradeItemIdentification type, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					alternateTradeItemIdentificationA.setType(str);
					lineItemA.setAlternateTradeItemIdentification(alternateTradeItemIdentificationA);
					break;
				case 53:
					// lineItem tradeItemDescriptionInformation longText
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO lineItem tradeItemDescriptionInformation longText, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					tradeItemDescriptionInformationA.setLongText(str);
					break;
				case 54:
					// lineItem tradeItemDescriptionInformation language
					if (!("ES".equals(StringManage.getValidString(str))
							|| "EN".equals(StringManage.getValidString(str)))) {
						throw new IllegalArgumentException(
								"DATO lineItem tradeItemDescriptionInformation language, INCORRECTO, DEBE SER UN VALOR [ES= Espa�ol EN= Ingles]: "
										+ str);
					}
					tradeItemDescriptionInformationA.setLanguage(str);
					lineItemA.setTradeItemDescriptionInformation(tradeItemDescriptionInformationA);
					break;
				case 55:
					// lineItem invoicedQuantity
					if (!gc.isDecimal(str, 1, 10, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO lineItem invoicedQuantity, INCORRECTO, DEBE SER UN VALOR TIPO DECIMAL: " + str);
					}
					invoicedQuantityA.setValue(str);
					break;
				case 56:
					// lineItem invoicedQuantity unitOfMeasure
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 3)) {
						throw new IllegalArgumentException(
								"DATO lineItem invoicedQuantity unitOfMeasure, INCORRECTO, DEBE SER UN VALOR DE 1 a 3 CARACTERES: "
										+ str);
					}
					invoicedQuantityA.setUnitOfMeasure(str);
					lineItemA.setInvoicedQuantity(invoicedQuantityA);
					break;
				case 57:
					// lineItem aditionalQuantity
					if (!gc.isDecimal(str, 1, 4, 1, 4)) {
						throw new IllegalArgumentException(
								"DATO lineItem aditionalQuantity, INCORRECTO, DEBE SER UN VALOR DECIMAL: " + str);
					}
					aditionalQuantityA.setValue(str);
					break;
				case 58:
					// lineItem aditionalQuantity quantityType
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 18)) {
						throw new IllegalArgumentException(
								"DATO lineItem aditionalQuantity quantityType, INCORRECTO, DEBE SER UN VALOR DE 1 a 18 CARACTERES: "
										+ str);
					}
					aditionalQuantityA.setQuantityType(str);
					lineItemA.setAditionalQuantity(aditionalQuantityA);
					break;
				case 59:
					// lineItem grossPrice amount
					if (!gc.isDecimal(str, 1, 10, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO lineItem grossPrice amount, INCORRECTO, DEBE SER UN VALOR DECIMAL: " + str);
					}
					amountA.setAmount(str);
					lineItemA.setGrossPrice(amountA);
					break;
				case 60:
					// lineItem netPrice amount
					if (!gc.isDecimal(str, 1, 10, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO lineItem netPrice amount, INCORRECTO, DEBE SER UN VALOR DECIMAL: " + str);
					}
					amountAA.setAmount(str);
					lineItemA.setNetPrice(amountAA);
					break;
				case 61:
					// lineItem additionalInformation referenceIdentification
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO lineItem additionalInformation referenceIdentification, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					referenceIdentificationAdditional.setValue(str);
					break;
				case 62:
					// lineItem additionalInformation referenceIdentification type
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 35)) {
						throw new IllegalArgumentException(
								"DATO lineItem additionalInformation referenceIdentification type, INCORRECTO, DEBE SER UN VALOR DE 1 a 35 CARACTERES: "
										+ str);
					}
					referenceIdentificationAdditional.setType(str);
					additionalInformationReference.getReferenceIdentification().add(referenceIdentificationAdditional);
					lineItemA.setAdditionalInformation(additionalInformationReference);
					break;
				case 63:
					// lineItem tradeItemTaxInformation taxTypeDescription
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 3)) {
						throw new IllegalArgumentException(
								"DATO lineItem tradeItemTaxInformation taxTypeDescription, INCORRECTO, DEBE SER UN VALOR DE 1 a 3 CARACTERES: "
										+ str);
					}
					tradeItemTaxInformationA.setTaxTypeDescription(str);
					break;
				case 64:
					// lineItem tradeItemTaxInformation tradeItemTaxAmount taxPercentage
					if (!gc.isDecimal(str, 1, 5, 1, 5)) {
						throw new IllegalArgumentException(
								"DATO lineItem tradeItemTaxInformation tradeItemTaxAmount taxPercentage, INCORRECTO, DEBE SER UN VALOR DECIMAL: "
										+ str);
					}
					tradeItemTaxAmountAA.setTaxPercentage(str);
					break;
				case 65:
					// lineItem tradeItemTaxInformation tradeItemTaxAmount taxAmount
					if (!gc.isDecimal(str, 1, 5, 1, 5)) {
						throw new IllegalArgumentException(
								"DATO lineItem tradeItemTaxInformation tradeItemTaxAmount taxAmount, INCORRECTO, DEBE SER UN VALOR DECIMAL: "
										+ str);
					}
					tradeItemTaxAmountAA.setTaxAmount(str);
					tradeItemTaxInformationA.setTradeItemTaxAmount(tradeItemTaxAmountAA);
					lineItemA.getTradeItemTaxInformation().add(tradeItemTaxInformationA);
					break;
				case 66:
					// lineItem totalLineAmount grossAmount amount
					if (!gc.isDecimal(str, 1, 5, 1, 2)) {
						throw new IllegalArgumentException(
								"DATO lineItem totalLineAmount grossAmount amount, INCORRECTO, DEBE SER UN VALOR DECIMAL: "
										+ str);
					}
					amountAAZ.setAmount(str);
					totalLineAmountA.setGrossAmount(amountAAZ);
					break;
				case 67:
					// lineItem totalLineAmount netAmount amount
					if (!gc.isDecimal(str, 1, 5, 1, 2)) {
						throw new IllegalArgumentException(
								"DATO lineItem totalLineAmount netAmount amount, INCORRECTO, DEBE SER UN VALOR DECIMAL: "
										+ str);
					}
					amountAAY.setAmount(str);
					totalLineAmountA.setNetAmount(amountAAY);
					lineItemA.setTotalLineAmount(totalLineAmountA);
					listLineItem.add(lineItemA);
					break;
				case 68:
					// totalAmount amount
					if (!gc.isDecimal(str, 1, 5, 1, 5)) {
						throw new IllegalArgumentException(
								"DATO totalAmount amount, INCORRECTO, DEBE SER UN VALOR DECIMAL: " + str);
					}
					amountTotal.setAmount(str);
					requestForPayment.setTotalAmount(amountTotal);
					break;
				case 69:
					// totalAllowanceCharge amount
					if (!gc.isDecimal(str, 1, 5, 1, 5)) {
						throw new IllegalArgumentException(
								"DATO totalAllowanceCharge amount, INCORRECTO, DEBE SER UN VALOR DECIMAL: " + str);
					}
					totalAllowanceCharge.setAmount(str);
					break;
				case 70:
					// totalAllowanceCharge allowanceOrChargeType
					if (!(StringManage.getValidString(str).length() >= 1
							&& StringManage.getValidString(str).length() <= 9)) {
						throw new IllegalArgumentException(
								"DATO totalAllowanceCharge allowanceOrChargeType, INCORRECTO, DEBE SER UN VALOR DE 1 a 9 CARACTERES: "
										+ str);
					}
					totalAllowanceCharge.setAllowanceOrChargeType(str);
					requestForPayment.setTotalAllowanceCharge(totalAllowanceCharge);
					break;
				case 71:
					// baseAmount amount
					if (!(gc.isDecimal(str, 1, 10, 1, 10))) {
						throw new IllegalArgumentException(
								"DATO baseAmount amount, INCORRECTO, DEBE SER UN VALOR DECIMAL: " + str);
					}
					amountBase.setAmount(str);
					requestForPayment.setBaseAmount(amountBase);
					break;
				case 72:
					// tax type
					if (!("GST".equals(StringManage.getValidString(str))
							|| "VAT".equals(StringManage.getValidString(str))
							|| "LAC".equals(StringManage.getValidString(str)))) {
						throw new IllegalArgumentException(
								"DATO tax type, INCORRECTO, DEBE SER UN VALOR [GST = IEPS VAT = Impuesto sobre el valor agregado LAC= ISR]: "
										+ str);
					}
					taxA.setType(str);
					break;
				case 73:
					// tax taxPercentage
					if (!gc.isDecimal(str, 1, 5, 1, 5)) {
						throw new IllegalArgumentException(
								"DATO tax taxPercentage, INCORRECTO, DEBE SER UN VALOR DECIMAL: " + str);
					}
					taxA.setTaxPercentage(str);
					break;
				case 74:
					// tax taxAmount
					if (!gc.isDecimal(str, 1, 10, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO tax taxAmount, INCORRECTO, DEBE SER UN VALOR DECIMAL: " + str);
					}
					taxA.setTaxAmount(str);
					requestForPayment.getTax().add(taxA);
					break;
				case 75:
					// payableAmount amount
					if (!gc.isDecimal(str, 1, 10, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO payableAmount amount, INCORRECTO, DEBE SER UN VALOR DECIMAL: " + str);
					}
					amountPaya.setAmount(str);
					requestForPayment.setPayableAmount(amountPaya);
					requestForPayment.getLineItem().add(lineItemA);
					break;
				}
			}
			general.getRequestForPayment().add(requestForPayment);
			lineaDatosSoriana.setResponseDSCargaRemisionProv(general);
		} else {
			throw new IllegalArgumentException(
					"Renglon invalido. Se esperaba mas informacion para el identificador " + idRegistro);
		}
		return lineaDatosSoriana;
	}

	public static LineaDatosSorianaPieDeCamion fillDataPieDeCamion(String dataLine) {
		LineaDatosSorianaPieDeCamion lineaDatosSorianaPieDeCamion = null;
		String[] data = dataLine.split("\\|");
		int x;
		if (data.length > 0) {

			lineaDatosSorianaPieDeCamion = new LineaDatosSorianaPieDeCamion();

			mx.bigdata.sat.addenda.soriana.pie.schema.ObjectFactory factory = new mx.bigdata.sat.addenda.soriana.pie.schema.ObjectFactory();
			ResponseDSCargaRemisionProvPieDeCamion general = factory.createResponseDSCargaRemisionProvPieDeCamion();
			List<Object> remisionOrPedimentoOrPedidos = general.getRemisionOrPedimentoOrPedidos();
			
			ResponseDSCargaRemisionProvPieDeCamion.Remision remision = factory.createResponseDSCargaRemisionProvPieDeCamionRemision();
			ResponseDSCargaRemisionProvPieDeCamion.Pedimento pedimento = factory.createResponseDSCargaRemisionProvPieDeCamionPedimento();
			ResponseDSCargaRemisionProvPieDeCamion.Pedidos pedidos = factory.createResponseDSCargaRemisionProvPieDeCamionPedidos();
			ResponseDSCargaRemisionProvPieDeCamion.Articulos articulos = factory.createResponseDSCargaRemisionProvPieDeCamionArticulos();
			
			for (x = 0; x < data.length; x++) {
				String str = data[x];
				if (str != null) {
					str = str.trim();
					if ("".equals(str))
						str = null;
				}
				int count = x + 1;
				switch (count) {
				case 1:
					// Identificador Registro - REQUERIDO
					if (!idRegistroPieDeCamion.equals(StringManage.getValidString(str))) {
						throw new IllegalArgumentException(
								"IDENTIFICADOR DE REGISTRO NO VALIDO, DEBE SER ESTRICTAMENTE \"" + idRegistroPieDeCamion
										+ "\" PARA " + idRegistroPieDeCamion);
					}
					break;
				case 2:
					// Remision setProveedor
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Remision Proveedor, INCORRECTO, DEBE SER UN VALOR TIPO ENTERO: " + str);
					}
					remision.setProveedor(Integer.parseInt(str));
					break;
				case 3:
					// Remision setRemision
					if (null == StringManage.getValidString(str)) {
						throw new IllegalArgumentException(
								"DATO Remision Remision, INCORRECTO, DEBE SER UN VALOR TIPO LETRA: " + str);
					}
					remision.setRemision(str);
					break;
				case 4:
					// Remision setConsecutivo
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Remision Consecutivo, INCORRECTO, DEBE SER UN VALOR TIPO ENTERO: " + str);
					}
					remision.setConsecutivo(Short.valueOf(str));
					break;
				case 5:
					// Remision setFechaRemision
					if (!gc.isDate(str, "yyyyMMdd")) {
						throw new IllegalArgumentException(
								"DATO Remision FechaRemision, INCORRECTO, DEBE SER UN VALOR TIPO FECHA: " + str);
					}
					try{
						remision.setFechaRemision(DatatypeFactory.newInstance().newXMLGregorianCalendar(str));
					} catch (Exception e) {
						throw new IllegalArgumentException(
								"DATO Remision FechaRemision, INCORRECTO, DEBE SER UN VALOR TIPO FECHA: " + str);
					}
					break;
				case 6:
					// Remision setTienda
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Remision Tienda, INCORRECTO, DEBE SER UN VALOR TIPO ENTERO: " + str);
					}
					remision.setTienda(Short.valueOf(str));
					break;
				case 7:
					// Remision setTipoMoneda
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Remision TipoMoneda, INCORRECTO, DEBE SER UN VALOR TIPO ENTERO: " + str);
					}
					remision.setTipoMoneda(Short.valueOf(str));
					break;
				case 8:
					// Remision setTipoBulto
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Remision TipoBulto, INCORRECTO, DEBE SER UN VALOR TIPO ENTERO: " + str);
					}
					remision.setTipoBulto(Short.valueOf(str));
					break;
				case 9:
					// Remision setEntregaMercancia
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Remision EntregaMercancia, INCORRECTO, DEBE SER UN VALOR TIPO ENTERO: " + str);
					}
					remision.setEntregaMercancia(Short.valueOf(str));
					break;
				case 10:
					// Remision setCumpleReqFiscales
					if (!(str.toLowerCase().equals("true") || str.toLowerCase().equals("false"))) {
						throw new IllegalArgumentException(
								"DATO Remision CumpleReqFiscales, INCORRECTO, DEBE SER UN VALOR TIPO BOOLEAN [true false]: " + str);
					}
					remision.setCumpleReqFiscales(new Boolean(str.toLowerCase()));
					break;
				case 11:
					// Remision setCantidadBultos
					if (!gc.isDecimal(str, 1, 10, 1, 5)) {
						throw new IllegalArgumentException(
								"DATO Remision CantidadBultos, INCORRECTO, DEBE SER UN VALOR TIPO DECIMAL: " + str);
					}
					remision.setCantidadBultos(new BigDecimal(str));
					break;
				case 12:
					// Remision setSubtotal
					if (!gc.isDecimal(str, 1, 10, 1, 5)) {
						throw new IllegalArgumentException(
								"DATO Remision Subtotal, INCORRECTO, DEBE SER UN VALOR TIPO DECIMAL: " + str);
					}
					remision.setSubtotal(new BigDecimal(str));
					break;
				case 13:
					// Remision setIEPS
					if (!gc.isDecimal(str, 1, 10, 1, 5)) {
						throw new IllegalArgumentException(
								"DATO Remision IEPS, INCORRECTO, DEBE SER UN VALOR TIPO DECIMAL: " + str);
					}
					remision.setIEPS(new BigDecimal(str));
					break;
				case 14:
					// Remision setIVA
					if (!gc.isDecimal(str, 1, 10, 1, 5)) {
						throw new IllegalArgumentException(
								"DATO Remision IVA, INCORRECTO, DEBE SER UN VALOR TIPO DECIMAL: " + str);
					}
					remision.setIVA(new BigDecimal(str));
					break;
				case 15:
					// Remision setOtrosImpuestos
					if (!gc.isDecimal(str, 1, 10, 1, 5)) {
						throw new IllegalArgumentException(
								"DATO Remision OtrosImpuestos, INCORRECTO, DEBE SER UN VALOR TIPO DECIMAL: " + str);
					}
					remision.setOtrosImpuestos(new BigDecimal(str));
					break;
				case 16:
					// Remision setTotal
					if (!gc.isDecimal(str, 1, 10, 1, 5)) {
						throw new IllegalArgumentException(
								"DATO Remision Total, INCORRECTO, DEBE SER UN VALOR TIPO DECIMAL: " + str);
					}
					remision.setTotal(new BigDecimal(str));
					break;
				case 17:
					// Remision setCantidadPedidos
					if (null == StringManage.getValidString(str)) {
						throw new IllegalArgumentException(
								"DATO Remision CantidadPedidos, INCORRECTO, DEBE SER UN VALOR TIPO LETRA: " + str);
					}
					remision.setCantidadPedidos(Integer.parseInt(str));
					break;
				case 18:
					// Remision setFechaEntregaMercancia
					if (!gc.isDate(str, "yyyyMMdd")) {
						throw new IllegalArgumentException(
								"DATO Remision FechaEntregaMercancia, INCORRECTO, DEBE SER UN VALOR TIPO FECHA: " + str);
					}
					try{
						remision.setFechaEntregaMercancia(DatatypeFactory.newInstance().newXMLGregorianCalendar(str));
					} catch (Exception e) {
						throw new IllegalArgumentException(
								"DATO Remision FechaEntregaMercancia, INCORRECTO, DEBE SER UN VALOR TIPO FECHA: " + str);
					}
					break;
				case 19:
					// Remision setCita
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Remision Cita, INCORRECTO, DEBE SER UN VALOR TIPO ENTERO: " + str);
					}
					remision.setCita(new Integer(str));
					break;
				case 20:
					// Remision setFolioNotaEntrada
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Remision FolioNotaEntrada, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					remision.setFolioNotaEntrada(new Integer(str));
					
					remisionOrPedimentoOrPedidos.add(remision);
					break;
				case 21:
					// Pedimento setProveedor
					if (!(gc.isNumeric(str, 1, 10))) {
						throw new IllegalArgumentException(
								"DATO Pedimento Proveedor, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					pedimento.setProveedor(Integer.parseInt(str));
					break;
				case 22:
					// Pedimento setRemision
					if (null == StringManage.getValidString(str)) {
						throw new IllegalArgumentException(
								"DATO Pedimento Remision, INCORRECTO, DEBE SER UN VALOR TIPO LETRA: " + str);
					}
					pedimento.setRemision(str);
					break;
				case 23:
					// Pedimento setPedimento
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedimento Pedimento, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					pedimento.setPedimento(Integer.parseInt(str));
					break;
				case 24:
					// Pedimento setAduana
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedimento Aduana, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					pedimento.setAduana(Short.valueOf(str));
					break;
				case 25:
					// Pedimento setAgenteAduanal
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedimento AgenteAduanal, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					pedimento.setAgenteAduanal(Short.valueOf(str));
					break;
				case 26:
					// Pedimento setTipoPedimento
					if (null == StringManage.getValidString(str)) {
						throw new IllegalArgumentException(
								"DATO Pedimento TipoPedimento, INCORRECTO, DEBE SER UN VALOR TIPO LETRA: " + str);
					}
					pedimento.setTipoPedimento(str);
					break;
				case 27:
					// Pedimento setFechaPedimento
					if (!gc.isDate(str, "yyyyMMdd")) {
						throw new IllegalArgumentException(
								"DATO Pedimento FechaPedimento, INCORRECTO, DEBE SER UN VALOR TIPO FECHA: " + str);
					}
					try{
						pedimento.setFechaPedimento(DatatypeFactory.newInstance().newXMLGregorianCalendar(str));
					} catch (Exception e) {
						throw new IllegalArgumentException(
								"DATO Pedimento FechaPedimento, INCORRECTO, DEBE SER UN VALOR TIPO FECHA: " + str);
					}
					break;
				case 28:
					// Pedimento setFechaReciboLaredo
					if (!gc.isDate(str, "yyyyMMdd")) {
						throw new IllegalArgumentException(
								"DATO Pedimento FechaReciboLaredo, INCORRECTO, DEBE SER UN VALOR TIPO FECHA: " + str);
					}
					try{
						pedimento.setFechaReciboLaredo(DatatypeFactory.newInstance().newXMLGregorianCalendar(str));
					} catch (Exception e) {
						throw new IllegalArgumentException(
								"DATO Pedimento FechaReciboLaredo, INCORRECTO, DEBE SER UN VALOR TIPO FECHA: " + str);
					}
					break;
				case 29:
					// Pedimento setFechaBillOfLading
					if (!gc.isDate(str, "yyyyMMdd")) {
						throw new IllegalArgumentException(
								"DATO Pedimento FechaBillOfLading, INCORRECTO, DEBE SER UN VALOR TIPO FECHA: " + str);
					}
					try{
						pedimento.setFechaBillOfLading(DatatypeFactory.newInstance().newXMLGregorianCalendar(str));
					} catch (Exception e) {
						throw new IllegalArgumentException(
								"DATO Pedimento FechaBillOfLading, INCORRECTO, DEBE SER UN VALOR TIPO FECHA: " + str);
					}
					remisionOrPedimentoOrPedidos.add(pedimento);
					break;
				case 30:
					// Pedidos setProveedor
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedidos Proveedor, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					pedidos.setProveedor(Integer.parseInt(str));
					break;
				case 31:
					// Pedidos setRemision
					if (null == StringManage.getValidString(str)) {
						throw new IllegalArgumentException(
								"DATO Pedidos Remision, INCORRECTO, DEBE SER UN VALOR TIPO LETRA: " + str);
					}
					pedidos.setRemision(str);
					break;
				case 32:
					// Pedidos setFolioPedido
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedidos FolioPedido, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					pedidos.setFolioPedido(Integer.parseInt(str));
					break;
				case 33:
					// Pedidos setTienda
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedidos Tienda, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					pedidos.setTienda(Short.valueOf(str));
					break;
				case 34:
					// Pedidos setCantidadArticulos
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedidos CantidadArticulos, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					pedidos.setCantidadArticulos(Integer.parseInt(str));
					break;
				case 35:
					// Pedidos setPedidoEmitidoProveedor
					if (null == StringManage.getValidString(str)) {
						throw new IllegalArgumentException(
								"DATO Pedidos PedidoEmitidoProveedor, INCORRECTO, DEBE SER UN VALOR TIPO LETRA: " + str);
					}
					pedidos.setPedidoEmitidoProveedor(str);
					remisionOrPedimentoOrPedidos.add(pedidos);
					break;
				
				
				
				
				case 36:
					// Articulos setProveedor
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedidos Proveedor, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					articulos.setProveedor(Integer.parseInt(str));
					break;
				case 37:
					// Articulos setRemision
					if (null == StringManage.getValidString(str)) {
						throw new IllegalArgumentException(
								"DATO Pedidos Remision, INCORRECTO, DEBE SER UN VALOR TIPO LETRA: " + str);
					}
					articulos.setRemision(str);
					break;
				case 38:
					// Articulos setFolioPedido
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedidos FolioPedido, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					articulos.setFolioPedido(Integer.parseInt(str));
					break;
				case 39:
					// Articulos setTienda
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedidos Tienda, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					articulos.setTienda(Short.valueOf(str));
					break;
				case 40:
					// Articulos setCodigo
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedidos Codigo, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					articulos.setCodigo(new BigDecimal(str));
					break;
				case 41:
					// Articulos setCantidadUnidadCompra
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedidos CantidadUnidadCompra, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					articulos.setCantidadUnidadCompra(new BigDecimal(str));
					break;
				case 42:
					// Articulos setCostoNetoUnidadCompra
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedidos CostoNetoUnidadCompra, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					articulos.setCostoNetoUnidadCompra(new BigDecimal(str));
					break;
				case 43:
					// Articulos setPorcentajeIEPS
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedidos PorcentajeIEPS, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					articulos.setPorcentajeIEPS(new BigDecimal(str));
					break;
				case 44:
					// Articulos setPorcentajeIVA
					if (!gc.isNumeric(str, 1, 10)) {
						throw new IllegalArgumentException(
								"DATO Pedidos PorcentajeIVA, INCORRECTO, DEBE SER UN VALOR TIPO NUMERICO: " + str);
					}
					articulos.setPorcentajeIVA(new BigDecimal(str));
					remisionOrPedimentoOrPedidos.add(articulos);
					break;
				}
			}
			//general.getRemisionOrPedimentoOrPedidos().add(remisionOrPedimentoOrPedidos);
			lineaDatosSorianaPieDeCamion.setResponseDSCargaRemisionProvPieDeCamion(general);
		} else {
			throw new IllegalArgumentException(
					"Renglon invalido. Se esperaba mas informacion para el identificador " + idRegistro);
		}
		return lineaDatosSorianaPieDeCamion;
	}
}
