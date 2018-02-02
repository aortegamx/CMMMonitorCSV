/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml;

import java.io.UnsupportedEncodingException;

/**
 * Clase que convierte en <code>String</code> un arreglo de bytes.
 * @author cmartinez
 */
public class CmmCvsRead {
    
    /**
     * Devuelve una cadena a partir de un arreglo de bytes proporcionado
     * @param bytes byte[]
     * @return String
     * @throws java.io.UnsupportedEncodingException
     */
    public static String readStringBytesUTF8(byte[] bytes) throws UnsupportedEncodingException{
        return new String(bytes,"UTF-8");
    }
    
    /**
     * Devuelve una cadena a partir de un arreglo de bytes proporcionado con el charset requerido
     * @param bytes byte[]
     * @param charset String
     * @return String
     * @throws java.io.UnsupportedEncodingException
     */
    public static String readStringBytes(byte[] bytes, String charset) throws UnsupportedEncodingException{
        if(charset!=null && !charset.trim().equals("")){
            return new String(bytes,"UTF-8");
        }
        return new String(bytes);
    }
    
}
