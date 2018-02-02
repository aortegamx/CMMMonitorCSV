/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest;

import com.cmm.cvs2xml.bean.LineaDatosFactura;
import com.cmm.cvs2xml.utils.CmmCvsComprobanteUtils;
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
public class TestLineaDatosFactura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestLineaDatosFactura test = new TestLineaDatosFactura();
        test.run();
    }
    
    public void run (){
        String csvFile = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\ARCHIVO_PRUEBA_NOMINA.CSV";
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
 
                    if (line.startsWith(CmmCvsComprobanteUtils.idRegistro)){
                        LineaDatosFactura lineaDatosFactura = CmmCvsComprobanteUtils.fillData(line);

                        System.out.println("subtotal: " + lineaDatosFactura.getDatosComprobante().getComprobante().getSubTotal());
                        System.out.println("Descuento: " + lineaDatosFactura.getDatosComprobante().getComprobante().getDescuento());
                        System.out.println("% descuento: " + lineaDatosFactura.getDatosComprobante().getPorcentajeDescuento());
                        System.out.println("Total: " + lineaDatosFactura.getDatosComprobante().getComprobante().getTotal());
                        System.out.println("Lugar expedicion: " + lineaDatosFactura.getDatosComprobante().getComprobante().getLugarExpedicion());
                        System.out.println("Moneda: " + lineaDatosFactura.getDatosComprobante().getComprobante().getMoneda());
                        System.out.println("Monto IVA Grupal: " + lineaDatosFactura.getIVAGrupalMonto());
                        System.out.println("Tasa IVA Grupal: " + lineaDatosFactura.getIVAGrupalTasa());
                        System.out.println("Monto ISR: " + lineaDatosFactura.getISRRetenidoMonto());
                        
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
