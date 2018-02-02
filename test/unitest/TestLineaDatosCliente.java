/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest;

import com.cmm.cvs2xml.bean.LineaDatosCliente;
import com.cmm.cvs2xml.utils.CmmCvsReceptorUtils;
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
public class TestLineaDatosCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestLineaDatosCliente test = new TestLineaDatosCliente();
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
 
                    if (line.startsWith(CmmCvsReceptorUtils.idRegistro)){
                        LineaDatosCliente lineaDatosCliente = CmmCvsReceptorUtils.fillData(line);

                        System.out.println("RFC emisor: " + lineaDatosCliente.getRfcEmisor());
                        System.out.println("Tipo persona: " + lineaDatosCliente.getDatosReceptor().getTipoPersona());
                        System.out.println("Nombre: " + lineaDatosCliente.getDatosReceptor().getReceptor().getNombre());
                        System.out.println("RFC receptor: " + lineaDatosCliente.getDatosReceptor().getReceptor().getRfc());
                        /*System.out.println("Direccion receptor: " + lineaDatosCliente.getDatosReceptor().getReceptor().getDomicilio().getCalle()
                                            + ", " + lineaDatosCliente.getDatosReceptor().getReceptor().getDomicilio().getPais());*/

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
