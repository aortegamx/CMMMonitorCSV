/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unittest.econtabilidad.polizas;

import com.cmm.cvs2xml.econtabilidad.polizas.bean.LineaDatosPolizas;
import com.cmm.cvs2xml.econtabilidad.polizas.utils.CmmCvsPolizasDatosGeneralesUtils;
import com.cmm.cvs2xml.util.UnicodeBOMInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ISCesar
 */
public class TestLineaDatosPolizas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestLineaDatosPolizas test = new TestLineaDatosPolizas();
        test.run();
    }
    
    public void run (){
        String csvFile = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\Ejemplos\\Contabilidad Electrónica\\120_Pólizas_del_Periodo.CSV";
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
 
                    if (line.startsWith(CmmCvsPolizasDatosGeneralesUtils.idRegistro)){
                        LineaDatosPolizas lineaDatos = CmmCvsPolizasDatosGeneralesUtils.fillData(line);

                        System.out.println("RFC: " + lineaDatos.getDatosGeneralesPolizas().getPolizas().getRFC());
                        System.out.println("Mes: " + lineaDatos.getDatosGeneralesPolizas().getPolizas().getMes());
                        System.out.println("Año: " + lineaDatos.getDatosGeneralesPolizas().getPolizas().getAno());
                        
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
