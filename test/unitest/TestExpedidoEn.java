/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest;

import com.cmm.cvs2xml.utils.CmmCvsExpedidoEnUtils;
import com.cmm.cvs2xml.util.UnicodeBOMInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import mx.bigdata.sat.cfdi.v32.schema.TUbicacion;

/**
 *
 * @author ISCesar
 */
public class TestExpedidoEn {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestExpedidoEn test = new TestExpedidoEn();
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
 
                    if (line.startsWith(CmmCvsExpedidoEnUtils.idRegistro)){
                        TUbicacion expedidoEn = CmmCvsExpedidoEnUtils.fillData(line);

                        System.out.println("Calle: " + expedidoEn.getCalle());
                        System.out.println("Codigo Postal: " + expedidoEn.getCodigoPostal());
                        System.out.println("Colonia: " + expedidoEn.getColonia());
                        System.out.println("Estado: " + expedidoEn.getEstado());
                        System.out.println("Localidad: "+ expedidoEn.getLocalidad());
                        System.out.println("Municipio: "+ expedidoEn.getMunicipio());
                        System.out.println("No Ext: "+ expedidoEn.getNoExterior());
                        System.out.println("No Int: "+ expedidoEn.getNoInterior());
                        System.out.println("País: "+ expedidoEn.getPais());
                        System.out.println("Referencia: "+ expedidoEn.getReferencia());

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
