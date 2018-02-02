/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest;

import com.cmm.cvs2xml.bean.DatosConcepto;
import com.cmm.cvs2xml.bean.LineaDatosConcepto;
import com.cmm.cvs2xml.utils.CmmCvsConceptoUtils;
import com.cmm.cvs2xml.utils.CmmCvsParteConceptoUtils;
import com.cmm.cvs2xml.util.UnicodeBOMInputStream;
import com.cmm.cvs2xml.utils.CmmCvsInfoAduaneraUtils;
import com.cmm.cvs2xml.utils.CmmCvsInfoPredialUtils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.InformacionAduanera;

/**
 *
 * @author ISCesar
 */
public class TestConceptoInclude {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        TestConceptoInclude test = new TestConceptoInclude();
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
                
                ArrayList<LineaDatosConcepto> listaLineaConcepto = new ArrayList<LineaDatosConcepto>();
                LineaDatosConcepto lineaDatosConcepto = null;
                
		while ((line = br.readLine()) != null) {
                    if (line.startsWith(CmmCvsConceptoUtils.idRegistro)){
                        
                        //Antes de crear una nueva, guardamos en lista general si existe una previa
                        if (lineaDatosConcepto!=null)
                            listaLineaConcepto.add(lineaDatosConcepto);
                                
                        //Despues creamos la nueva linea
                        lineaDatosConcepto = CmmCvsConceptoUtils.fillData(line);
                         
                        printConcepto(lineaDatosConcepto.getDatosConcepto());
                    }
                    
                    if (line.startsWith(CmmCvsInfoAduaneraUtils.idRegistro)){
                        lineaDatosConcepto = CmmCvsInfoAduaneraUtils.fillData(line, lineaDatosConcepto);

                        InformacionAduanera informacionAduanera = lineaDatosConcepto.getDatosConcepto().getConcepto().getInformacionAduanera().get(0);
                        printInfoAduanera(informacionAduanera);
                    }
                    
                    if (line.startsWith(CmmCvsInfoPredialUtils.idRegistro)){
                        lineaDatosConcepto = CmmCvsInfoPredialUtils.fillData(line, lineaDatosConcepto);

                        Comprobante.Conceptos.Concepto.CuentaPredial cuentaPredial = lineaDatosConcepto.getDatosConcepto().getConcepto().getCuentaPredial();
                        
                        printInfoPredial(cuentaPredial);
                    }
                    
                    if (line.startsWith(CmmCvsParteConceptoUtils.idRegistro)){
                        lineaDatosConcepto = CmmCvsParteConceptoUtils.fillData(line, lineaDatosConcepto);

                        Comprobante.Conceptos.Concepto.Parte conceptoParte = lineaDatosConcepto.getDatosConcepto().getConcepto().getParte().get(0);
                        
                        printParteConcepto(conceptoParte);

                    }
                    
		}
                
                //Para poder almacenar el ultimo concepto en la iteracion fuera del While
                if (lineaDatosConcepto!=null)
                            listaLineaConcepto.add(lineaDatosConcepto);
                
                for (LineaDatosConcepto linea : listaLineaConcepto){
                    System.out.println("\n==================");
                    System.out.println(linea.getDatosConcepto().getConcepto().getDescripcion());
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
    
    private void printConcepto(DatosConcepto datosConcepto){
        System.out.println("\n>>>>");
        System.out.println("nombre: " + datosConcepto.getConcepto().getDescripcion());
        System.out.println("codigo: " + datosConcepto.getConcepto().getNoIdentificacion());
        System.out.println("descripcion: " + datosConcepto.getNombre());
        System.out.println("unidad de medida: " + datosConcepto.getConcepto().getUnidad());
        System.out.println("precio unitario: " + datosConcepto.getConcepto().getValorUnitario());
        System.out.println("cantidad: " + datosConcepto.getConcepto().getCantidad());
        System.out.println("importe: " + datosConcepto.getConcepto().getImporte());
        System.out.println("Porcentaje IVA: " + datosConcepto.getIVAporcentaje());
    }
    
    private void printInfoAduanera(InformacionAduanera informacionAduanera){
        System.out.println("\t---- INFORMACIÓN ADUANERA ----");
        System.out.println("\tnumero: " + informacionAduanera.getNumeroPedimento());
        /*System.out.println("\tfecha: " + informacionAduanera.getFecha());
        System.out.println("\taduana: " + informacionAduanera.getAduana());*/
    }
    
    private void printInfoPredial(Comprobante.Conceptos.Concepto.CuentaPredial cuentaPredial){
        System.out.println("\t---- CUENTA PREDIAL ----");
        System.out.println("\tnumero: " + cuentaPredial.getNumero());
    }
    
    private void printParteConcepto(Comprobante.Conceptos.Concepto.Parte conceptoParte){
        mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.Parte.InformacionAduanera informacionAduanera = null;
        if (conceptoParte.getInformacionAduanera().size()>0) 
            informacionAduanera = conceptoParte.getInformacionAduanera().get(0);

        System.out.println("\t---- PARTE DE CONCEPTO ----");
        System.out.println("\tcodigo: " + conceptoParte.getNoIdentificacion());
        System.out.println("\tdescripcion: " + conceptoParte.getDescripcion());
        System.out.println("\tunidad de medida: " + conceptoParte.getUnidad());
        System.out.println("\tprecio unitario: " + conceptoParte.getValorUnitario());
        System.out.println("\tcantidad: " + conceptoParte.getCantidad());
        System.out.println("\timporte: " + conceptoParte.getImporte());

        if (informacionAduanera!=null){
            System.out.println("\t\t---- INFORMACIÓN ADUANERA ----");
            System.out.println("\t\tnumero: " + informacionAduanera.getNumeroPedimento());
            /*System.out.println("\t\tfecha: " + informacionAduanera.getFecha());
            System.out.println("\t\taduana: " + informacionAduanera.getAduana());*/
        }
    }
    
}
