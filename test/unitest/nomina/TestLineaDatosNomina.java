/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest.nomina;

import com.cmm.cvs2xml.complementos.nomina.bean.LineaDatosNomina;
import com.cmm.cvs2xml.complementos.nomina.utils.CmmCvsNominaDatosGeneralesUtils;
import com.cmm.cvs2xml.util.UnicodeBOMInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.datatype.DatatypeConfigurationException;
import mx.bigdata.sat.common.nomina12.schema.Nomina;

/**
 *
 * @author ISCesar
 */
public class TestLineaDatosNomina {

   // public static final String CSV_FILE = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\ARCHIVO_PRUEBA_NOMINA.CSV";
    
    public static final String CSV_FILE = "/Users/jdominguez/OneDrive/MOVILPYME/CMMMonitorDektp/Instalador V2plus/Instalador V2plus/repositorio/in/3_Nomina.CSV";
    /**
     * @param args the command line arguments
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    public static void main(String[] args) throws DatatypeConfigurationException {
        TestLineaDatosNomina test = new TestLineaDatosNomina();
        test.run();
    }
    
    public void run () throws DatatypeConfigurationException{
     //   String csvFile = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\ARCHIVO_PRUEBA_NOMINA.CSV";
	BufferedReader br = null;
	String line;
        
        FileInputStream fis = null;
        UnicodeBOMInputStream ubis = null;
        InputStreamReader isr = null;
        
	try {
 
                fis = new FileInputStream(CSV_FILE);
                ubis = new UnicodeBOMInputStream(fis);
                isr = new InputStreamReader(ubis);
                br = new BufferedReader(isr);
                ubis.skipBOM();
                
		//br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
 
                    if (line.startsWith(CmmCvsNominaDatosGeneralesUtils.IDREGISTRO)){
                        LineaDatosNomina lineaDatosNomina = CmmCvsNominaDatosGeneralesUtils.fillData(line);

                        Nomina nomina = lineaDatosNomina.getDatosNomina().getNomina();
                        System.out.println("Version: " + nomina.getVersion());
                        System.out.println("Reg patronal: " + nomina.getEmisor().getRegistroPatronal());
                        System.out.println("num empleado: " + nomina.getReceptor().getNumEmpleado());
                        System.out.println("CURP: " + nomina.getReceptor().getCurp());
                        System.out.println("Tipo Regimen: " + nomina.getReceptor().getTipoRegimen());
                        System.out.println("Antiguedad: " +nomina.getReceptor().getAntigüedad());
                        System.out.println("Clave ENTFED: " + nomina.getReceptor().getClaveEntFed().value());
                        System.out.println("Num Seg Social: " + nomina.getReceptor().getNumSeguridadSocial());
                        System.out.println("Fecha Pago: " + nomina.getFechaPago());
                        System.out.println("Fecha inicial: " + nomina.getFechaInicialPago());
                        System.out.println("Fecha final: " + nomina.getFechaFinalPago());
                        System.out.println("Puesto: " + nomina.getReceptor().getPuesto());
                        System.out.println("Periodicidad pago: " + nomina.getReceptor().getPeriodicidadPago());
                        System.out.println("Salario diario inte.: " + nomina.getReceptor().getSalarioDiarioIntegrado());
                        System.out.println("Tipo Nomina: " + nomina.getTipoNomina().value());
                        
                        System.out.println("Total percepciones grav: " + lineaDatosNomina.getTotalPercepcionesGravadas());
                        System.out.println("Total percepciones exe: " + lineaDatosNomina.getTotalPercepcionesExentas());
                        System.out.println("Total deducciones grav: " + lineaDatosNomina.getTotalDeduccionesGravadas());
                        System.out.println("Total deducciones exe: " + lineaDatosNomina.getTotalDeduccionesExentas());
                        
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
