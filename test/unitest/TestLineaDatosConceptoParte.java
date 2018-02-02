/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest;

import com.cmm.cvs2xml.bean.LineaDatosConcepto;
import com.cmm.cvs2xml.utils.CmmCvsConceptoUtils;
import com.cmm.cvs2xml.utils.CmmCvsParteConceptoUtils;
import com.cmm.cvs2xml.util.UnicodeBOMInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.Parte.InformacionAduanera;

/**
 *
 * @author ISCesar
 */
public class TestLineaDatosConceptoParte {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        TestLineaDatosConceptoParte test = new TestLineaDatosConceptoParte();
        test.run();
    }
    
    public void run () throws Exception{
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
                
                LineaDatosConcepto lineaDatosConcepto = null;
                
		while ((line = br.readLine()) != null) {
                    if (line.startsWith(CmmCvsConceptoUtils.idRegistro)){
                         lineaDatosConcepto = CmmCvsConceptoUtils.fillData(line);

                        System.out.println("nombre: " + lineaDatosConcepto.getDatosConcepto().getConcepto().getDescripcion());
                        System.out.println("codigo: " + lineaDatosConcepto.getDatosConcepto().getConcepto().getNoIdentificacion());
                        System.out.println("descripcion: " + lineaDatosConcepto.getDatosConcepto().getNombre());
                        System.out.println("unidad de medida: " + lineaDatosConcepto.getDatosConcepto().getConcepto().getUnidad());
                        System.out.println("precio unitario: " + lineaDatosConcepto.getDatosConcepto().getConcepto().getValorUnitario());
                        System.out.println("cantidad: " + lineaDatosConcepto.getDatosConcepto().getConcepto().getCantidad());
                        System.out.println("importe: " + lineaDatosConcepto.getDatosConcepto().getConcepto().getImporte());
                        System.out.println("Porcentaje IVA: " + lineaDatosConcepto.getDatosConcepto().getIVAporcentaje());
                     
                        System.out.println(">>>>>");
                    }
                    
                    if (line.startsWith(CmmCvsParteConceptoUtils.idRegistro)){
                        lineaDatosConcepto = CmmCvsParteConceptoUtils.fillData(line, lineaDatosConcepto);

                        Comprobante.Conceptos.Concepto.Parte conceptoParte = lineaDatosConcepto.getDatosConcepto().getConcepto().getParte().get(0);
                        
                        InformacionAduanera informacionAduanera = null;
                        if (conceptoParte.getInformacionAduanera().size()>0) 
                            informacionAduanera = conceptoParte.getInformacionAduanera().get(0);
                        
                        System.out.println("---- PARTE DE CONCEPTO ----");
                        System.out.println("codigo: " + conceptoParte.getNoIdentificacion());
                        System.out.println("descripcion: " + conceptoParte.getDescripcion());
                        System.out.println("unidad de medida: " + conceptoParte.getUnidad());
                        System.out.println("precio unitario: " + conceptoParte.getValorUnitario());
                        System.out.println("cantidad: " + conceptoParte.getCantidad());
                        System.out.println("importe: " + conceptoParte.getImporte());
                        
                        if (informacionAduanera!=null){
                            System.out.println("---- INFORMACIÓN ADUANERA ----");
                            System.out.println("numero: " + informacionAduanera.getNumeroPedimento());
                            /*System.out.println("fecha: " + informacionAduanera.getFecha());
                            System.out.println("aduana: " + informacionAduanera.getAduana());*/
                        }
                        
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
