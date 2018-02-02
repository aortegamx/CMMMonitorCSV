/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unittest.econtabilidad.polizas;

import com.cmm.cvs2xml.econtabilidad.polizas.bean.LineaDatosPoliza;
import com.cmm.cvs2xml.econtabilidad.polizas.utils.CmmCvsPolizasPolizaUtils;
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
public class TestLineaDatosPoliza {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestLineaDatosPoliza test = new TestLineaDatosPoliza();
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
 
                    if (line.startsWith(CmmCvsPolizasPolizaUtils.idRegistro)){
                        LineaDatosPoliza lineaDatos = CmmCvsPolizasPolizaUtils.fillData(line);

                        System.out.println("Tipo: " + lineaDatos.getDatosPoliza().getPoliza().getTipo());
                        System.out.println("Numero: " + lineaDatos.getDatosPoliza().getPoliza().getNum());
                        System.out.println("Fecha: " + lineaDatos.getDatosPoliza().getPoliza().getFecha());
                        System.out.println("Concepto: " + lineaDatos.getDatosPoliza().getPoliza().getConcepto());
                        
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
