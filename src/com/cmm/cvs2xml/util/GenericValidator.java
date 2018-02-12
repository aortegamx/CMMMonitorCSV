/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ISCesarMartinez
 */
public class GenericValidator {
    
    //metodo para validar si la fecha es correcta con formato dd/MM/yyyy
    public boolean isDate(String fechax) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = formatoFecha.parse(fechax);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    //metodo para validar si la fecha es correcta con formato yyyy-MM-dd
    public boolean isDate2(String fechax) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatoFecha.parse(fechax);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    //metodo para validar correo electronio
    public boolean isEmail(String correo) {
        Pattern pat;
        Matcher mat;        
        pat = Pattern.compile("^[\\w\\-\\_]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        }else{
            return false;
        }        
    }
    
    /**
     * Método para validar Contraseñas/Passwords seguros
     * 
     * De esta forma comprobaremos:
     *      Contraseñas que contengan al menos una letra mayúscula.
     *      Contraseñas que contengan al menos una letra minúscula.
     *      Contraseñas que contengan al menos un número o caracter especial.
     *      Contraseñas cuya longitud sea como mínimo 8 caracteres.
     *      Contraseñas cuya longitud máxima no debe ser arbitrariamente limitada.
     * 
     * @param password
     * @return true en caso de ser contraseña segura, false en caso contrario
     */
    public boolean isPasswordSeguro(String password) {
        Pattern pat;
        Matcher mat;        
        pat = Pattern.compile("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
        mat = pat.matcher(password);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        }else{
            return false;
        }        
    }
    
    /*Método que tiene la función de validar el curp*/
     public boolean isCURP(String curp){
             curp=curp.toUpperCase().trim();
             return curp.matches("[A-Z][A,E,I,O,U,X][A-Z]{2}[0-9]{2}[0-1][0-9][0-3][0-9][M,H][A-Z]{2}[B,C,D,F,G,H,J,K,L,M,N,Ñ,P,Q,R,S,T,V,W,X,Y,Z]{3}[0-9,A-Z][0-9]");
     }//Cierra método validarCurp

     /*Método que tiene la función de validar el rfc*/
     public boolean isRFC(String rfc){
          rfc=rfc.toUpperCase().trim();
          if (rfc.trim().length()<12 || rfc.trim().length()>13){
              return false;
          }else{
            return rfc.toUpperCase().matches("[A-Z,Ñ,&]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z,0-9]?[A-Z,0-9]?[0-9,A-Z]?");
          }
      }//Cierra método validarRFC
     
     public boolean isCodigoPostal(String CP){
         CP = CP.trim();
         try{
             int test = Integer.parseInt(CP);
         }catch(NumberFormatException ex){
             return false;
         }
         if(CP.length()!=5){
             return false;
         }
         return true;
     }
     
     public boolean isNumeric(String cadena, int minLenght, int maxLenght){
         cadena = cadena.trim();
         try{
             long test = Long.parseLong(cadena);
         }catch(NumberFormatException ex){
             return false;
         }
         if(cadena.length()<minLenght){
             return false;
         }
         if(cadena.length()>maxLenght){
             return false;
         }
         return true;
     }
     
     /**
      * Verifica que sea una cadena valida según las especificaciones
      * @param cadena
      * @param minLenght longitud mínima
      * @param maxLenght longitud máxima
      * @return 
      */
     public boolean isValidString(String cadena, int minLenght, int maxLenght){
         cadena = cadena!=null?cadena.trim():"";
         if(cadena.length()<minLenght){
             return false;
         }
         if(cadena.length()>maxLenght){
             return false;
         }
         return true;
     }
     
     /*Método que tiene la función de validar un UUID de Timbre Fiscal Digital*/
     public boolean isUUID(String uuid){
        if (uuid==null)
            return false;
        
        if (uuid.length()!=36)
            return false;
         
        uuid=uuid.toUpperCase().trim();
        return uuid.matches("[a-f0-9A-F]{8}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{12}");
     }
     
     /**
	 * Verifica si es un numero de tarjeta de credito valido
	 * Solo para VISA o Mastercard
	 * @param creditCardNumber
	 * @return true en caso de ser un numero de tarjeta válido, false en caso contrario
	 */
	public boolean isCreditCardNumber(String creditCardNumber){
		boolean isValid;
		// VISA:  		^4\\d{3}-?\\d{4}-?\\d{4}-?\\d{4}$ 
		// MASTERCARD: 	^5[1-5]\\d{2}-?\\d{4}-?\\d{4}-?\\d{4}$
		
		Pattern patVisa = Pattern.compile("^4\\d{3}-?\\d{4}-?\\d{4}-?\\d{4}$");
		Matcher matVisa = patVisa.matcher(creditCardNumber);
		if (matVisa.find()) {
			System.out.println("[" + matVisa.group() + "]");
			return true;
		} else {
			isValid = false;
		}
		
		Pattern patMasterCard = Pattern.compile("^5[1-5]\\d{2}-?\\d{4}-?\\d{4}-?\\d{4}$");
		Matcher matMasterCard = patMasterCard.matcher(creditCardNumber);
		if (matMasterCard.find()) {
			System.out.println("[" + matMasterCard.group() + "]");
			return true;
		} else {
			isValid = false;
		}
		
		
		return isValid;
	}
	
	/**
	 * Verifica la fecha de expiración de una tarjeta de crédito
	 * Debe cumplir con el formato 'MM/YY'
	 * y ser una fecha válida   
	 * @param creditCardExpirationDate
	 * @return true en caso de ser aprobado, false en caso contrario
	 */
	public boolean isCreditCardExpirationDate(String creditCardExpirationDate){
		// FECHA DE EXPIRACION:  ^((0[1-9])|(1[0-2]))\\/(([1-2][0-9]))$
		
		boolean isValid;
		
		Pattern pat = Pattern.compile("^((0[1-9])|(1[0-2]))\\/(([1-2][0-9]))$");
		Matcher mat = pat.matcher(creditCardExpirationDate);
		if (mat.find()) {
			System.out.println("[" + mat.group() + "]");
			return true;
		} else {
			isValid = false;
		}
		
		return isValid;
	}
        
    /**
     * Método que tiene la función de validar una CLABE interbancaria
     * @param CLABE
     * @return 
     */
     public boolean isCLABE(String CLABE){
        CLABE=CLABE.toUpperCase().trim();
        return CLABE.matches("[0-9]{18}");
     }
     
     /**
     * Método que tiene la función de validar una cadena contra una expresion regular
     * @param str Cadena a validar
     * @param exreg Expresion regular a utilizar
     * @return true en caso de corresponder, false en caso contrario
     */
     public boolean validaConExpresionRegular(String str, String exreg){
         if (str==null)
             return false;
        return str.matches(exreg);
     }
     
     /**
      * Valida si una cadena es un valor decimal valido
      * con restricciones en minimo y maximo de longitud para la parte entera, 
      * y mínimo y máximo de longitud para la parte de fracciones
      * @param cadena Cadena a evaluar
      * @param minIntLenght Longitud minima de parte entera
      * @param maxIntLenght Longitud maxima de parte entera
      * @param minFractionLenght Longitud minima de parte fraccional (despues del punto)
      * @param maxFractionLenght Longitud maxima de parte fraccional (despues del punto)
      * @return true en caso de ser valida, false en caso contrario
      */
     public boolean isDecimal(String cadena, int minIntLenght, int maxIntLenght, int minFractionLenght, int maxFractionLenght){
         cadena = cadena.trim();
         
         // "^-?\\d{1,18}(?>\\.\\d{0,12})?$";
         String expression = "^-?\\d{"+minIntLenght+","+maxIntLenght+"}(?>\\.\\d{"+minFractionLenght+","+maxFractionLenght+"})?$";
         
         return validaConExpresionRegular(cadena, expression);
     }
     
     /**
      * Valida si una fecha es correcta comparandola contra un formato indicado
      * @param fechax cadena a validar
      * @param formato formato a comparar
      * @return true en caso de que sea valida segun el formato, false en caso contrario
      */
     public boolean isDate(String fechax, String formato) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
            Date fecha = formatoFecha.parse(fechax);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    public boolean isVersion(String version, int count) {
        try {
            String []split = version.split("\\.");
            if(split.length < 1) {
                return isNumeric(version, 1, count);
            }
            for (int i = 0; i < split.length; i++) {
                if(!isNumeric("" + split[i], 1, count)) {
                        return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
