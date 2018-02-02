/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unittest.econtabilidad.polizas;

import com.cmm.cvs2xml.econtabilidad.polizas.utils.CmmCvsPolizasTransferenciaUtil;
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
public class TestPolizasTransferencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestPolizasTransferencia test = new TestPolizasTransferencia();
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
 
                    if (line.startsWith(CmmCvsPolizasTransferenciaUtil.idRegistro)){
                        transaccion = CmmCvsPolizasTransferenciaUtil.fillData(line, transaccion);
                        if (transaccion.getTransferencia().size()>0){
                            Polizas.Poliza.Transaccion.Transferencia transferencia = transaccion.getTransferencia().get(0);//CmmCvsPolizasTransferenciaUtil.fillData(line);

                            System.out.println("Cta Origen: " + transferencia.getCtaOri());
                            System.out.println("Banco Origen: " + transferencia.getBancoOri());
                            System.out.println("Monto: " + transferencia.getMonto());
                            System.out.println("Cta Origen: " + transferencia.getCtaDest());
                            System.out.println("Banco Origen: " + transferencia.getBancoDest());
                            System.out.println("Fecha: " + transferencia.getFecha());
                            System.out.println("Beneficiario: " + transferencia.getBenef());
                            System.out.println("RFC: " + transferencia.getRFC());

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
