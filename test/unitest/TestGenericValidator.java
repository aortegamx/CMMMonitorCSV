/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest;

import com.cmm.cvs2xml.util.GenericValidator;

/**
 *
 * @author ISCesar
 */
public class TestGenericValidator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String decimal = "1234567891.55";
        
        GenericValidator gc = new GenericValidator();
        
        boolean valid = gc.isDecimal(decimal, 1, 10, 0, 2);
        
        System.out.println("resultado: " + valid);
    }
    
}
