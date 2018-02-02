/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest.addenda.fordfom;

import com.cmm.cvs2xml.addendas.fordfom.bean.LineaDatosFordFom;
import com.cmm.cvs2xml.addendas.fordfom.utils.CmmCvsFordFomGeneralUtils;
import com.cmm.cvs2xml.util.UnicodeBOMInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.datatype.DatatypeConfigurationException;
import mx.bigdata.sat.addenda.fordfom.schema.Addenda;
import mx.bigdata.sat.addenda.sanofi.schema.Sanofi;

/**
 *
 * @author ISCesar
 */
public class TestLineaDatosFordGeneral {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DatatypeConfigurationException {
        TestLineaDatosFordGeneral test = new TestLineaDatosFordGeneral();
        test.run();
    }
    
    public void run () throws DatatypeConfigurationException{
        String csvFile = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\addendas\\Ford\\Ejemplo\\00236_Factura_Addenda_Ford_FOM.CSV";
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
 
                    if (line.startsWith(CmmCvsFordFomGeneralUtils.idRegistro)){
                        LineaDatosFordFom lineaDatosFordFom = CmmCvsFordFomGeneralUtils.fillData(line);

                        Addenda.FOMASN fomasn = lineaDatosFordFom.getFomasn();
                        System.out.println(" GSDB : " + fomasn.getGSDB());
                        
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
