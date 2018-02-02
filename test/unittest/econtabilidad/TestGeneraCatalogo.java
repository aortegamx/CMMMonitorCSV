/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unittest.econtabilidad;

import mx.bigdata.sat.econtabilidad.CTLv1;
import mx.bigdata.sat.econtabilidad.v1.catalogo.schema.Catalogo;

/**
 *
 * @author ISCesar
 */
public class TestGeneraCatalogo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        TestGeneraCatalogo test = new TestGeneraCatalogo();
        test.run();
    }
    
    protected void run() throws Exception{
        Catalogo catalogo = new Catalogo();
        catalogo.setVersion("1.0");
        catalogo.setRFC("FJC780315E91");
        catalogo.setTotalCtas(2);
        catalogo.setMes("07");
        catalogo.setAno(2014);
        
        Catalogo.Ctas cta = new Catalogo.Ctas();
        cta.setCodAgrup("1.1");
        cta.setNumCta("1000");
        cta.setDesc("Caja");
        cta.setNivel(1);
        cta.setNatur("D");
        catalogo.getCtas().add(cta);
        
        Catalogo.Ctas cta2 = new Catalogo.Ctas();
        cta2.setCodAgrup("37.1");
        cta2.setNumCta("2100");
        cta2.setDesc("Proveedores");
        cta2.setNivel(1);
        cta2.setNatur("A");
        catalogo.getCtas().add(cta2);
        
        
        CTLv1 cTLv1 = new CTLv1(catalogo);
        cTLv1.validar();
        cTLv1.guardar(System.out);
    }
    
}
