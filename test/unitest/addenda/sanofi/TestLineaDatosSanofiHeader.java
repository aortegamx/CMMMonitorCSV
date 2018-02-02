/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest.addenda.sanofi;

import com.cmm.cvs2xml.addendas.sanofi.bean.LineaDatosSanofiHeader;
import com.cmm.cvs2xml.addendas.sanofi.utils.CmmCvsSanofiHeaderUtils;
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
public class TestLineaDatosSanofiHeader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DatatypeConfigurationException {
        TestLineaDatosSanofiHeader test = new TestLineaDatosSanofiHeader();
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
 
                    if (line.startsWith(CmmCvsSanofiHeaderUtils.idRegistro)){
                        LineaDatosSanofiHeader lineaDatosSanofi = CmmCvsSanofiHeaderUtils.fillData(line);

                        Sanofi.Documento.Header sanofiHeader = lineaDatosSanofi.getSanofiDocHeader();
                        System.out.println(" Tipo Dcto : " + sanofiHeader.getTIPODOCTO());
                        System.out.println(" Num Orden : " + sanofiHeader.getNUMORDEN());
                        System.out.println(" Num Proveedor : " + sanofiHeader.getNUMPROVEEDOR());
                        System.out.println(" FCTCONV : " + sanofiHeader.getFCTCONV());
                        System.out.println(" Moneda : " + sanofiHeader.getMONEDA());
                        System.out.println(" Importe Retencion : " + sanofiHeader.getIMPRETENCION());
                        System.out.println(" Importe Total : " + sanofiHeader.getIMPTOTAL());
                        System.out.println(" Observaciones : " + sanofiHeader.getOBSERVACIONES());
                        System.out.println(" Cuenta Correo : " + sanofiHeader.getCTACORREO());
                        System.out.println(" Disponible 1 : " + sanofiHeader.getDISPONIBLE1());
                        System.out.println(" Disponible 2 : " + sanofiHeader.getDISPONIBLE2());
                        System.out.println(" Disponible 3 : " + sanofiHeader.getDISPONIBLE3());
                        System.out.println(" Disponible 4 : " + sanofiHeader.getDISPONIBLE4());
                        
                        
                        break;
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
