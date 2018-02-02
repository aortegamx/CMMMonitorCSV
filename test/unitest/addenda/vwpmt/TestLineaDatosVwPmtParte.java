/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest.addenda.vwpmt;

import com.cmm.cvs2xml.addendas.vwpmt.bean.LineaDatosVWParte;
import com.cmm.cvs2xml.addendas.vwpmt.utils.CmmCvsVwPmtParteUtils;
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
public class TestLineaDatosVwPmtParte {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        TestLineaDatosVwPmtParte test = new TestLineaDatosVwPmtParte();
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
 
                    if (line.startsWith(CmmCvsVwPmtParteUtils.idRegistro)){
                        LineaDatosVWParte lineaDatos = CmmCvsVwPmtParteUtils.fillData(line);

                        Factura.Partes.Parte parte = lineaDatos.getParte();
                        System.out.println(" Referencia OC : " + parte.getReferencias().getOrdenCompra());
                        System.out.println(" Posicion : " + parte.getPosicion());
                        System.out.println(" Numero de material : " + parte.getNumeroMaterial());
                        System.out.println(" Desc de material : " + parte.getDescripcionMaterial());
                        System.out.println(" Cantidad de material : " + parte.getCantidadMaterial());
                        System.out.println(" U medida : " + parte.getUnidadMedida());
                        System.out.println(" P unitario : " + parte.getPrecioUnitario());
                        System.out.println(" Monto de linea : " + parte.getMontoLinea());
                        System.out.println(" Peso bruto : " + parte.getPesoBruto());
                        System.out.println(" Peso neto : " + parte.getPesoNeto());
                        for (String nota : parte.getNota()){
                            System.out.println(" Nota : " + nota);
                        }
                        
                        System.out.println("\n----");
                        //break;
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
