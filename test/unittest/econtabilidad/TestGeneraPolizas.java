/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unittest.econtabilidad;

import com.cmm.cvs2xml.util.DateManage;
import java.math.BigDecimal;
import mx.bigdata.sat.econtabilidad.PLZv1;
import mx.bigdata.sat.econtabilidad.v1.polizas.schema.Polizas;

/**
 *
 * @author ISCesar
 */
public class TestGeneraPolizas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        TestGeneraPolizas test = new TestGeneraPolizas();
        test.run();
    }
    
    protected void run() throws Exception{
        Polizas polizas = new Polizas();
        polizas.setVersion("1.0");
        polizas.setRFC("FJC780315E91");
        polizas.setMes("07");
        polizas.setAno(2014);
        
        Polizas.Poliza poliza = new Polizas.Poliza();
        poliza.setTipo(1);
        poliza.setNum("1A");
        poliza.setFecha(DateManage.dateToXMLGregorianCalendar2(DateManage.stringToDate("2014-07-01","yyyy-MM-dd")));
        poliza.setConcepto("Poliza de imngreso 1A");
        
            Polizas.Poliza.Transaccion transaccion = new Polizas.Poliza.Transaccion();
            transaccion.setNumCta("1000");
            transaccion.setConcepto("Cargo a Caja por un pago de un proveedor");
            transaccion.setDebe(new BigDecimal("1000.00"));
            transaccion.setHaber(new BigDecimal("0.00"));
            transaccion.setMoneda("MXN");
        poliza.getTransaccion().add(transaccion);

            Polizas.Poliza.Transaccion transaccion2 = new Polizas.Poliza.Transaccion();
            transaccion2.setNumCta("2100");
            transaccion2.setConcepto("Abono al proveedor");
            transaccion2.setDebe(new BigDecimal("0.00"));
            transaccion2.setHaber(new BigDecimal("1000.00"));
            transaccion2.setMoneda("MXN");
        
                Polizas.Poliza.Transaccion.Comprobantes comprobantes = new  Polizas.Poliza.Transaccion.Comprobantes();
                comprobantes.setUUIDCFDI("277D0E42-869B-4CEA-BF48-717CD0525C24");
                comprobantes.setMonto(new BigDecimal("1000.00"));
                comprobantes.setRFC("AAAA010101000");
            transaccion2.getComprobantes().add(comprobantes);
        
        poliza.getTransaccion().add(transaccion2);
        
        polizas.getPoliza().add(poliza);
        
        
        PLZv1 pLZv1 = new PLZv1(polizas);
        pLZv1.validar();
        pLZv1.guardar(System.out);
    }
    
}
