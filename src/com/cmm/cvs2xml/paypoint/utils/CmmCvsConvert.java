/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.paypoint.utils;

import com.cmm.cvs2xml.paypoint.bean.CreaPayPoint;
import com.cmm.cvs2xml.paypoint.bean.PayPointDatos;
import com.cmm.cvs2xml.util.UnicodeBOMInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import org.w3c.dom.Document;

/**
 *
 * @author DXN
 */
public class CmmCvsConvert {
    public void convertFile(File file,String rutaEscritura) throws NullPointerException, IllegalArgumentException, NumberFormatException, ParseException, Exception {
        
        byte[] bytes;

        FileInputStream fis = new FileInputStream(file);
        bytes = new byte[(int) file.length()];
        fis.read(bytes);
        fis.close();
        
        convertFile(bytes,rutaEscritura);
    }
    
    /**
     * Convierte el
     * <code>CSV</code> en un objetos JAVA
     * accesibles.
     *
     * @param bytesFromFile
     * @throws NullPointerException si <code>txt</code> es nulo.
     * @throws Exception si el <code>txt</code> está mal formado.
     * @throws java.text.ParseException
     */
    public void convertFile(byte[] bytesFromFile,String rutaEscritura) throws NullPointerException, IllegalArgumentException, NumberFormatException, ParseException, Exception {
       //this.contenidoArchivo = bytesFromFile;
        
        BufferedReader br = null;
	String line;
        
        //FileInputStream fis = null;
        ByteArrayInputStream bais = null;
        UnicodeBOMInputStream ubis = null;
        InputStreamReader isr = null;
        
        int lineaActual = 1;
        
        try {
 
                //fis = new FileInputStream(csvFile);
                bais = new ByteArrayInputStream(bytesFromFile);
                ubis = new UnicodeBOMInputStream(bais);//(fis);
                isr = new InputStreamReader(ubis);
                br = new BufferedReader(isr);
                ubis.skipBOM(); //Esquivamos lectura de BOM en caso de existir
                                
                PayPointDatos payPointDatos = new PayPointDatos();
		while ((line = br.readLine()) != null) {
                    //rutaEscritura = rutaEscritura.replaceAll(".xml", "");//
                    //rutaEscritura += "_" +lineaActual+".xml";//
                    //PayPointDatos payPointDatos = new PayPointDatos();
                    //convert(payPointDatos,line,rutaEscritura + "_" +lineaActual+".xml");
                    System.out.println("Ruta salida: " +rutaEscritura);
                    convert(payPointDatos,line,rutaEscritura);
                    lineaActual++;
		}
                
                GeneraProcesoPayPoint generaProcesoPayPoint = new GeneraProcesoPayPoint();
                //System.out.println("Desde convert aapp_id: "+payPointDatos.getLineaDatoPayPointUtilidad().getAtributosPayPoint().getAppID());
                //generaProcesoPayPoint.payPointXML(rutaEscritura,payPointDatos.getLineaDatoPayPointUtilidad().getAtributosPayPoint());
                generaProcesoPayPoint.payPointXML(rutaEscritura,payPointDatos.getLineaDatoPayPointUtilidad().getAtributosPayPoints());
                
        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
	} catch (IOException e) {
            e.printStackTrace();
	} catch (Exception e){
            throw new Exception("Error (linea " + lineaActual + "): " + e.getMessage());
        } finally {
            if (br != null) {
                    try {
                            br.close();
                    } catch (IOException e) {
                            e.printStackTrace();
                    }
            }
            try{
                /*if (fis!=null)
                    fis.close();*/
                if (bais!=null)
                    bais.close();
                if (ubis!=null)
                    ubis.close();
                if (isr!=null)
                    isr.close();
            } catch (IOException e) {
                    e.printStackTrace();
            }
	}
    }
    
    
    public static void convert(PayPointDatos payPointDatos, String line,String rutaEscritura) throws Exception{
    if (payPointDatos!=null){
            try{
                
                //Registro 00800 - PayPoint (0 a N ocurrencias)
                if (line.startsWith(CmmCvsPayPointUtil.idRegistro)){                    
                    //payPointDatos.setLineaDatoPayPointUtilidad(CmmCvsPayPointUtil.fillData(line));
                    payPointDatos.getLineaDatoPayPointUtilidad().getAtributosPayPoints().add(CmmCvsPayPointUtil.fillData(line));

                    /*GeneraProcesoPayPoint generaProcesoPayPoint = new GeneraProcesoPayPoint();
                    //System.out.println("Desde convert aapp_id: "+payPointDatos.getLineaDatoPayPointUtilidad().getAtributosPayPoint().getAppID());
                    //generaProcesoPayPoint.payPointXML(rutaEscritura,payPointDatos.getLineaDatoPayPointUtilidad().getAtributosPayPoint());
                    generaProcesoPayPoint.payPointXML(rutaEscritura,payPointDatos.getLineaDatoPayPointUtilidad().getAtributosPayPoints());*/
                }
               
            }catch (Exception ex){
                ex.printStackTrace();
                //throw new Exception(" [Error Complemento PayPoint v1.0] :" + ex.getMessage());
            }
        }
    }
}
