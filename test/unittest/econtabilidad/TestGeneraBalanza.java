/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unittest.econtabilidad;

import java.math.BigDecimal;
import mx.bigdata.sat.econtabilidad.BCEv1;
import mx.bigdata.sat.econtabilidad.v1.balanza.schema.Balanza;

/**
 *
 * @author ISCesar
 */
public class TestGeneraBalanza {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        TestGeneraBalanza test = new TestGeneraBalanza();
        test.run();
    }
    
    protected void run() throws Exception{
        Balanza balanza = new Balanza();
        balanza.setVersion("1.0");
        balanza.setRFC("FJC780315E91");
        balanza.setTotalCtas(2);
        balanza.setMes("07");
        balanza.setAno(2014);
        
        Balanza.Ctas cta = new Balanza.Ctas();
        cta.setNumCta("1000");
        cta.setSaldoIni(new BigDecimal(0.00));
        cta.setDebe(new BigDecimal(1000.00));
        cta.setHaber(new BigDecimal(990.00));
        cta.setSaldoFin(new BigDecimal(10.00));
        balanza.getCtas().add(cta);
        
        Balanza.Ctas cta2 = new Balanza.Ctas();
        cta2.setNumCta("2100");
        cta2.setSaldoIni(new BigDecimal(2000.00));
        cta2.setDebe(new BigDecimal(1000.00));
        cta2.setHaber(new BigDecimal(1000.00));
        cta2.setSaldoFin(new BigDecimal(2000.00));
        balanza.getCtas().add(cta2);
        
        
        BCEv1 bCEv1 = new BCEv1(balanza);
        bCEv1.validar();
        bCEv1.guardar(System.out);
    }
    
}
