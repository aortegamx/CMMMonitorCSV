/*
 * To change this template, choose DateManage | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author ISC César Ulises Martínez García
 */
public class DateManage {

    /**
     * Método para convertir de una fecha java.util.Date a una fecha en formato
     * XMLGregorianCalendar contenida en javax.xml.datatype.XMLGregorianCalendar
     * @param dateToTransform
     * @return
     * @throws DatatypeConfigurationException
     */
    public static XMLGregorianCalendar dateToXMLGregorianCalendar(java.util.Date dateToTransform) throws DatatypeConfigurationException{
        XMLGregorianCalendar dateXMLGregorian = javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar();
        GregorianCalendar cal = new GregorianCalendar();

        cal.setTime(dateToTransform);
        dateXMLGregorian.setDay(cal.get(Calendar.DATE));
        dateXMLGregorian.setMonth(cal.get(Calendar.MONTH) + 1);
        dateXMLGregorian.setYear(cal.get(Calendar.YEAR));
        dateXMLGregorian.setHour(cal.get(Calendar.HOUR_OF_DAY));
        dateXMLGregorian.setMinute(cal.get(Calendar.MINUTE));
        dateXMLGregorian.setSecond(cal.get(Calendar.SECOND));

        return dateXMLGregorian;
    }
    
    public static XMLGregorianCalendar dateToXMLGregorianCalendar2(java.util.Date dateToTransform) throws DatatypeConfigurationException{
        XMLGregorianCalendar dateXMLGregorian = javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar();
        GregorianCalendar cal = new GregorianCalendar();

        cal.setTime(dateToTransform);
        dateXMLGregorian.setDay(cal.get(Calendar.DATE));
        dateXMLGregorian.setMonth(cal.get(Calendar.MONTH) + 1);
        dateXMLGregorian.setYear(cal.get(Calendar.YEAR));
        //dateXMLGregorian.setHour(cal.get(Calendar.HOUR_OF_DAY));
        //dateXMLGregorian.setMinute(cal.get(Calendar.MINUTE));
        //dateXMLGregorian.setSecond(cal.get(Calendar.SECOND));

        return dateXMLGregorian;
    }


    /**
     * Método para expresar en String la fecha y hora actual incluyendo hasta milisegundos
     * La cadena retornada tiene el formato: yyyyMMddHHmmssSSS
     * @return String con la fecha y hora con el formato yyyyMMddHHmmssSSS
     */
    public static String getDateHourString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    /**
     * Método para convertir Fecha de JAVA a
     * DateTime para SQL Formato yyyy-MM-dd HH:mm:ss
     */
    public static String dateToSQLDateTime(Date dateTime){
        if (dateTime==null)
            return null;
                    
        java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        return sdf.format(dateTime);
    }
    
    /**
     * Método para expresar en String la fecha indicada
     * La cadena retornada tiene el formato para sentencias SQL: yyyy-MM-dd
     * @return String con la fecha con el formato SQL yyyy-MM-dd
     */
    public static String formatDateToSQL(Date dateTime) {
        if (dateTime==null)
            return null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(dateTime);
    }
    
    /**
     * Método para expresar en String la fecha indicada
     * La cadena retornada tiene el formato para sentencias SQL: yyyy-MM-dd
     * @return String con la fecha con el formato SQL yyyy-MM-dd
     */
    public static String formatDateToNormal(Date dateTime) {
        if (dateTime==null)
            return null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(dateTime);
    }
    
    
    

    /**
     * Recibe una cadena de fecha con formato dd/MM/yyyy
     * y regresa el objeto Date correspondiente
     * @return Date
     */
    public static Date stringToDate(String stringTime) {
        try{
            String dia = stringTime.substring(0, 2);//dd
            String mes = stringTime.substring(3, 5);//MM
            String anio = stringTime.substring(6, 10);//yyyy
        

            Calendar cal = new GregorianCalendar();
            cal.set(Integer.parseInt(anio), Integer.parseInt(mes) - 1, Integer.parseInt(dia));
            //Date f = new Date(Integer.parseInt(anio),Integer.parseInt(mes)-1, Integer.parseInt(dia));
            return cal.getTime();
        }catch(Exception ex){
            return null;
        }
    }
    
    /**
     * Recibe un objeto Date y regresa un String con el formato dd 'de' MMMM 'de' yyyy
     * por ejemplo: 12 de Junio de 2009
     * @param date Date a transformar
     * @return Cadena con formato dd 'de' MMMM 'de' yyyy
     */
    public static String dateToStringEspanol(Date date) {
        String fecha = "";
        SimpleDateFormat formateador = new SimpleDateFormat(
                "EEEE ', ' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        fecha = formateador.format(date);
        return fecha;
    }
    
    /**
     * Recibe un objeto Date y regresa un String con el formato dd 'de' MMMM 'de' yyyy
     * por ejemplo: 12 de Junio de 2009
     * @param date Date a transformar
     * @return Cadena con formato dd 'de' MMMM 'de' yyyy hh:mm:ss
     */
    public static String dateTimeToStringEspanol(Date date) {
        String fecha = "";
        SimpleDateFormat formateador = new SimpleDateFormat(
                "EEEE ', ' dd 'de' MMMM 'de' yyyy hh':'mm':'ss", new Locale("ES"));
        fecha = formateador.format(date);
        return fecha;
    }
    
    /**
     * Compara si dos Objetos Date corresponden al mismo día
     * sin tomar en cuenta su hora, minuto, segundo, milisegundos...
     * @param date1 Fecha 1
     * @param date2 Fecha 2
     * @return true en caso de Ser el mismo día, false en caso contrario
     */
    public static boolean isOnDate(Date date1, Date date2){
        boolean isOnDate = false;
        
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        
        cal1.setTime(date1);
        cal2.setTime(date2);
        
        if ( cal1.get(Calendar.YEAR)== cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH)== cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH)== cal2.get(Calendar.DAY_OF_MONTH)
                ){
            isOnDate = true;
        }
        
        return isOnDate;
    }
    
    /**
     * Método para expresar en String la fecha indicada
     * La cadena retornada tiene el formato para sentencias SQL: dd/MM/yyyy
     * @return String con la fecha con el formato SQL dd/MM/yyyy
     */
    public static String formatDateTimeToNormalMinutes(Date dateTime) {
        if (dateTime==null)
            return null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        return dateFormat.format(dateTime);
    }
    
    
    /**
     * Recibe una cadena de fecha con formato indicado
     * y regresa el objeto Date correspondiente
     * @param stringTime
     * @param format
     * @return Date
     */
    public static Date stringToDate(String stringTime, String format) {
        if (stringTime==null)
            return null;
        
        try{
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat(format);
            Date fecha = formatoDelTexto.parse(stringTime);

            return fecha;
        }catch(Exception ex){
            return null;
        }
    }
    
    public static Date Date(String fechax) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatoFecha.parse(fechax);
            return fecha;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}
