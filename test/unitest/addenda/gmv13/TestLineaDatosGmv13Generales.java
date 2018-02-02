/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest.addenda.gmv13;

import com.cmm.cvs2xml.addendas.gm.v13.bean.LineaDatosGMv13Generales;
import com.cmm.cvs2xml.addendas.gm.v13.utils.CmmCvsGMv13GeneralUtils;
import com.cmm.cvs2xml.util.UnicodeBOMInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.datatype.DatatypeConfigurationException;
import mx.bigdata.sat.addenda.gm.v13.schema.ADDENDAGM;

/**
 *
 * @author ISCesar
 */
public class TestLineaDatosGmv13Generales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DatatypeConfigurationException {
        TestLineaDatosGmv13Generales test = new TestLineaDatosGmv13Generales();
        test.run();
    }
    
    public void run () throws DatatypeConfigurationException{
        String csvFile = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\addendas\\GM\\Ejemplo\\00235_Factura_Addenda_GMv13.CSV";
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
 
                    if (line.startsWith(CmmCvsGMv13GeneralUtils.idRegistro)){
                        LineaDatosGMv13Generales lineaDatos = CmmCvsGMv13GeneralUtils.fillData(line);

                        ADDENDAGM.HEADER header = lineaDatos.getHeader();
                        System.out.println(" Num Remision : " + header.getNUMEROREMISION());
                        System.out.println(" Fecha Recibo : " + header.getFECHARECIBO());
                        System.out.println(" Folio Interno : " + header.getFOLIOINTERNO());
                        System.out.println(" Moneda : " + header.getMONEDA());                        
                        
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
