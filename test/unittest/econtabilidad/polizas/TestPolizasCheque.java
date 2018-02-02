/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unittest.econtabilidad.polizas;

import com.cmm.cvs2xml.econtabilidad.polizas.utils.CmmCvsPolizasChequeUtil;
import com.cmm.cvs2xml.util.UnicodeBOMInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import mx.bigdata.sat.econtabilidad.v1.polizas.schema.Polizas;

/**
 *
 * @author ISCesar
 */
public class TestPolizasCheque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestPolizasCheque test = new TestPolizasCheque();
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
                
		Polizas.Poliza.Transaccion transaccion = new Polizas.Poliza.Transaccion();
		while ((line = br.readLine()) != null) {
 
                    if (line.startsWith(CmmCvsPolizasChequeUtil.idRegistro)){
                        transaccion = CmmCvsPolizasChequeUtil.fillData(line, transaccion);
                        if (transaccion.getCheque().size()>0){
                            Polizas.Poliza.Transaccion.Cheque cheque = transaccion.getCheque().get(0);//CmmCvsPolizasChequeUtil.fillData(line);

                            System.out.println("Numero: " + cheque.getNum());
                            System.out.println("Banco: " + cheque.getBanco());
                            System.out.println("Cta Origen: " + cheque.getCtaOri());
                            System.out.println("Fecha: " + cheque.getFecha());
                            System.out.println("Monto: " + cheque.getMonto());
                            System.out.println("Beneficiario: " + cheque.getBenef());
                            System.out.println("RFC: " + cheque.getRFC());

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
