/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest.addenda.vwpmt;

import com.cmm.cvs2xml.addendas.vwpmt.bean.LineaDatosVWGenerales;
import com.cmm.cvs2xml.addendas.vwpmt.utils.CmmCvsVwPmtGeneralUtils;
import com.cmm.cvs2xml.util.UnicodeBOMInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import mx.bigdata.sat.addenda.vwpmt.schema.Factura;

/**
 *
 * @author ISCesar
 */
public class TestLineaDatosVwPmtGenerales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        TestLineaDatosVwPmtGenerales test = new TestLineaDatosVwPmtGenerales();
        test.run();
    }
    
    public void run () throws Exception{
        String csvFile = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\addendas\\VW\\Ejemplo\\00210_Factura_Addenda_VW PMT.CSV";
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
 
                    if (line.startsWith(CmmCvsVwPmtGeneralUtils.idRegistro)){
                        LineaDatosVWGenerales lineaDatos = CmmCvsVwPmtGeneralUtils.fillData(line);

                        Factura factura = lineaDatos.getFacturaVW();
                        System.out.println(" Tipo Dcto : " + factura.getTipoDocumentoFiscal());
                        System.out.println(" Tipo Dcto VWM : " + factura.getTipoDocumentoVWM());
                        System.out.println(" Division : " + factura.getDivision());
                        
                        
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
