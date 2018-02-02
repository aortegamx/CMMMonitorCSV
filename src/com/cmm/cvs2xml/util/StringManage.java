package com.cmm.cvs2xml.util;

/**
 * 
 * @author ISCesarMartinez
 */
public class StringManage {
	
	public static final int FILL_DIRECTION_LEFT=1;
	public static final int FILL_DIRECTION_RIGHT=2;
	
	public static String getValidString(String value){
		String validString =  (value!=null?value.trim():"");
		return validString;
		
	}
	
	/**
	 * Rellena la cadena con el caracter especificado, solo en caso
	 * de que hagan falta caracteres para llegar al valor maximo "length"
	 * @param value Cadena a transformar
	 * @param length Numero de caracteres requeridos
	 * @param characterFill Caracter con el cual se rellenaran los espacios faltantes
	 * @param fillDirection Direccion de llenado, izquierda (1) o derecha (2). <p/>
	 * 			p. ej: x = "24" <p/>
	 * 				   getExactString(x,4,'0',StringManage.FILL_DIRECTION_RIGHT) = "2400"  <p/>
	 * 				   getExactString(x,4,'0',StringManage.FILL_DIRECTION_LEFT) = "0024" 
	 * @return Cadena transformada
	 */
	public static String getExactString(String value, int length, char characterFill, int fillDirection){
		String truncate ="";
		
		truncate = getValidString(value);
		
		if (truncate.length()>length){
			//Si excede el tama�o
			truncate = truncate.substring(0, length);
		}else{
			if (truncate.length()<length){
				//Si no cumple con el tama�o
				int actualSize=truncate.length();
				if (fillDirection==FILL_DIRECTION_RIGHT){
					for (int i=actualSize;i<=length;i++){
						//se rellena con espacios en blanco
						truncate+=characterFill;
					}
				}else{
					String aux = "";
					for (int i=actualSize;i<length;i++){
						//se rellena con espacios en blanco
						aux+=characterFill;
					}
					truncate = aux + truncate;
				}
			}
			
		}
		
		return truncate;
	}

}
