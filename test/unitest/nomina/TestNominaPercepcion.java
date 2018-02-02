/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest.nomina;

import com.cmm.cvs2xml.complementos.nomina.utils.CmmCvsNominaPercepcionUtils;
import com.cmm.cvs2xml.util.UnicodeBOMInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import mx.bigdata.sat.common.nomina12.schema.Nomina;

/**
 *
 * @author ISCesar
 */
public class TestNominaPercepcion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestNominaPercepcion test = new TestNominaPercepcion();
        test.run();
    }
    
    public void run () {
      //  String csvFile = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\ARCHIVO_PRUEBA_NOMINA.CSV";
	BufferedReader br = null;
	String line;
        
        FileInputStream fis = null;
        UnicodeBOMInputStream ubis = null;
        InputStreamReader isr = null;
        
	try {
 
                fis = new FileInputStream(TestLineaDatosNomina.CSV_FILE);
                ubis = new UnicodeBOMInputStream(fis);
                isr = new InputStreamReader(ubis);
                br = new BufferedReader(isr);
                ubis.skipBOM();
                
		//br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
 
                    if (line.startsWith(CmmCvsNominaPercepcionUtils.idRegistro)){
                        Nomina.Percepciones.Percepcion percepcion = CmmCvsNominaPercepcionUtils.fillData(line);
                        
                        System.out.println("Tipo percepción: " + percepcion.getTipoPercepcion());
                        System.out.println("Código percepción: " + percepcion.getClave());
                        System.out.println("Descripción: " + percepcion.getConcepto());
                        System.out.println("Importe gravado: " + percepcion.getImporteGravado());
                        System.out.println("Importe exento: " + percepcion.getImporteExento());
                        
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
