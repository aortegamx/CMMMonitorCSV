package com.cmm.cvs2xml.addendas.soriana.utils;

import com.cmm.cvs2xml.addendas.soriana.bean.AddendaSorianaDatos;
import com.cmm.cvs2xml.addendas.soriana.bean.AddendaSorianaPieDeCamionDatos;
import com.cmm.cvs2xml.bean.FacturaDatos;

public class CmmCvsConvert {

	public static void convert(FacturaDatos facturaDatos, String line) throws Exception {

		if (facturaDatos != null) {
			try {

				// Registro 00250 - San Francisco
				if (line.startsWith(CmmCvsSorianaGeneralUtils.idRegistro)) {
					facturaDatos.setAddendaSorianaDatos(new AddendaSorianaDatos());
					facturaDatos.getAddendaSorianaDatos()
							.setLineaDatosSoriana(CmmCvsSorianaGeneralUtils.fillData(line));
				} else // Registro 00250 - Addenda Soriana
					if (line.startsWith(CmmCvsSorianaGeneralUtils.idRegistroPieDeCamion)) {
					facturaDatos.setAddendaSorianaPieDeCamionDatos(new AddendaSorianaPieDeCamionDatos());
					facturaDatos.getAddendaSorianaPieDeCamionDatos()
							.setLineaDatosSorianaPieDeCamion(CmmCvsSorianaGeneralUtils.fillDataPieDeCamion(line));
				}
			} catch (Exception ex) {
				throw new Exception(" [Error Addenda Soriana Fom v1.0] :" + ex.getMessage());
			}
		}
	}
	
	public static void main(String []args) {
		String line = "00250|20180207|1.3.1|ORIGINAL|1.3.1|UNH 0065|INVOICE|1 al 7|1 al 15|AAB|1 al 7|ON|20180207|1 al 35|AAE|1 al 35|20180207|1 al 13|1 al 35|1 al 13|1 al 35|SELLER_ASSIGNED_IDENTIFIER_FOR_A_PARTY|1 al 13|1 al 35|1 al 35|1 al 35|1 al 19|1 al 13|1 al 35|1 al 35|1 al 35|1 al 35|1 al 35|1 al 35|MXN|BILLING_CURRENCY|1.23|1 al 15|1 al 15|1 al 22|1 al 6|1 a 5|1 al 16|BILL_BACK|AA|100.01|1 al 13|1|1 al 32|GTIN|999999|BUYER_ASSIGNED|1 al 35|ES|99.09|SEC|9999|NUM_CONSUMER_UNITS|10.88|10.88|string|type|LAC|10.88|10.88|10.88|10.88|10.88|10.88|1 al 9|10.88|GST|10.88|10.88|10.88|";
		try {
			convert(new FacturaDatos(), line);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
