/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest.addenda.sanofi;

import com.cmm.cvs2xml.addendas.sanofi.bean.LineaDatosSanofiDetails;
import com.cmm.cvs2xml.addendas.sanofi.bean.LineaDatosSanofiHeader;
import com.cmm.cvs2xml.addendas.sanofi.utils.CmmCvsSanofiDetailsUtils;
import com.cmm.cvs2xml.util.UnicodeBOMInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.datatype.DatatypeConfigurationException;
import mx.bigdata.sat.addenda.sanofi.schema.Sanofi;

/**
 *
 * @author ISCesar
 */
public class TestLineaDatosSanofiDetails {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DatatypeConfigurationException {
        TestLineaDatosSanofiDetails test = new TestLineaDatosSanofiDetails();
        test.run();
    }
    
    public void run () throws DatatypeConfigurationException{
        String csvFile = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\addendas\\sanofi\\200_Factura_Addenda_Sanofi.CSV";
	BufferedReader br = null;
	String line;
        
        FileInputStream fis = null;
        UnicodeBOMInputStream ubis = null;
        InputStreamReader isr = null;
        
	try {
 
                fis = new FileInputStream(csvFile);
                ubis = new UnicodeBOMInputStream(fis);
                isr = new InputStreamReader(ubis);
                br = new BufferedReader(isr);
                ubis.skipBOM();
                
		//br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
 
                    if (line.startsWith(CmmCvsSanofiDetailsUtils.idRegistro)){
                        LineaDatosSanofiDetails lineaDatosSanofiDetails = CmmCvsSanofiDetailsUtils.fillData(line);

                        Sanofi.Documento.Details sanofiDetails = lineaDatosSanofiDetails.getDetail();
                        System.out.println(" Num Linea : " + sanofiDetails.getNUMLINEA() );
                        System.out.println(" Num Entrada : " + sanofiDetails.getNUMENTRADA() );
                        System.out.println(" Cuenta Puente : " + sanofiDetails.getCUENTAPUENTE() );
                        System.out.println(" Unidades : " + sanofiDetails.getUNIDADES() );
                        System.out.println(" Precio Unitario : " + sanofiDetails.getPRECIOUNITARIO() );
                        System.out.println(" Importe : " + sanofiDetails.getIMPORTE() );
                        System.out.println(" Unidad Medida : " + sanofiDetails.getUNIDADMEDIDA() );
                        System.out.println(" Tasa IVA : " + sanofiDetails.getTASAIVA() );
                        System.out.println(" Importe IVA : " + sanofiDetails.getIMPORTEIVA() );
                        System.out.println(" Disponible 1 : " + sanofiDetails.getDISPONIBLE1());
                        System.out.println(" Disponible 2 : " + sanofiDetails.getDISPONIBLE2());
                        System.out.println(" Disponible 3 : " + sanofiDetails.getDISPONIBLE3());
                        System.out.println(" Disponible 4 : " + sanofiDetails.getDISPONIBLE4());
                        System.out.println(" Disponible 5 : " + sanofiDetails.getDISPONIBLE5());
                        System.out.println(" Disponible 6 : " + sanofiDetails.getDISPONIBLE6());
                        
                        
                        //break;
                        System.out.println("--------------------- \n");
                    }
		}
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
                try{
                    if (fis!=null)
                        fis.close();
                    if (ubis!=null)
                        ubis.close();
                    if (isr!=null)
                        isr.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
	}
 
	System.out.println("Terminado");
    }
    
}
