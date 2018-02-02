/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unittest.econtabilidad.polizas;

import com.cmm.cvs2xml.econtabilidad.polizas.utils.CmmCvsPolizasTransaccionUtil;
import com.cmm.cvs2xml.util.UnicodeBOMInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.bigdata.sat.econtabilidad.v1.polizas.schema.Polizas;

/**
 *
 * @author ISCesar
 */
public class TestPolizasTransaccion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestPolizasTransaccion test = new TestPolizasTransaccion();
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
                
                Polizas.Poliza poliza = new Polizas.Poliza();
		//br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
 
                    if (line.startsWith(CmmCvsPolizasTransaccionUtil.idRegistro)){
                        poliza = CmmCvsPolizasTransaccionUtil.fillData(line, poliza);
                        if (poliza.getTransaccion().size()>0){
                            Polizas.Poliza.Transaccion transaccion = poliza.getTransaccion().get(0);
                            //Polizas.Poliza.Transaccion transaccion = CmmCvsPolizasTransaccionUtil.fillData(line);

                            System.out.println("Clave: " + transaccion.getNumCta());
                            System.out.println("Concepto: " + transaccion.getConcepto());
                            System.out.println("Debe: " + transaccion.getDebe());
                            System.out.println("Haber: " + transaccion.getHaber());
                            System.out.println("Moneda: " + transaccion.getMoneda());
                            System.out.println("Tipo Cambio: " + transaccion.getTipCamb());

                            break;
                        }
                    }
		}
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (Exception e) {
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
