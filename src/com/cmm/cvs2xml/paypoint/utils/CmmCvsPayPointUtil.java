/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.paypoint.utils;

import com.cmm.cvs2xml.paypoint.bean.AtributosPayPoint;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;

/**
 *
 * @author DXN
 */
public class CmmCvsPayPointUtil {
    public final static String idRegistro = "00800";
    public final static String infoRegistro = "INFORMACION DE PAYPOINT";
    private final static int noElementosEsperados = 18;
    //public static LineaDatoPayPointUtilidad fillData(String elementoCfdi){
    public static AtributosPayPoint fillData(String elementoCfdi){
        LineaDatoPayPointUtilidad lineaDatos = null;
        AtributosPayPoint atributosPayPoint = null;
        String[] data = elementoCfdi.split("\\|");
        int x;
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            int maxDecimales = 6;
            
            lineaDatos = new LineaDatoPayPointUtilidad();
            atributosPayPoint = new AtributosPayPoint();
            
            for (x=0; x < data.length; x++){
                String str = data[x];
                if (str!=null){
                    str = str.trim();
                    if ("".equals(str)) str =null;
                }
                
                /*
                 * SWITCH X
                 */
                switch (x) {

                    case 0:
                        //Identificador Registro - REQUERIDO
                        if (!idRegistro.equals(StringManage.getValidString(str))) {
                            throw new IllegalArgumentException("IDENTIFICADOR DE REGISTRO NO VALIDO, DEBE SER ESTRICTAMENTE \""+idRegistro+"\" PARA " + infoRegistro);
                        }
                        break;
                    case 1:
                        //AppID - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE AppID NULO O VACIO");
                        }
                        atributosPayPoint.setAppID(str);
                        
                        System.out.println("APP_ID: "+str);
                        break;
                    case 2:
                        //Type - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TYPE NULO O VACIO");
                        }
                        atributosPayPoint.setType(str);
                        System.out.println("TYPE: "+str);
                        break;
                    case 3:
                         //CustomData - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CUSTOM DATA NULO O VACIO");
                        }
                        atributosPayPoint.setCustomData(str);
                        break;
                    case 4:
                       //ID - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE ID NULO O VACIO");
                        }
                        atributosPayPoint.setID(str);
                        break;
                    case 5:
                       //PaymentAmount - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE PAYMENTAMOUNT NULO O VACIO");
                        }
                        atributosPayPoint.setPaymentAmount(str);
                        break;
                    case 6:
                       //REFERENCE - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE REFERENCE NULO O VACIO");
                        }
                        atributosPayPoint.setReference(str);
                        System.out.println("REFERENCE: "+str);
                        break;
                    case 7:
                       //PAYMENTMEDIUM - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE PAYMENT MEDIUM NULO O VACIO");
                        }
                        atributosPayPoint.setPaymentMedium(str);
                        break;
                    case 8:
                       //CARD NUMBER - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CARD NUMBER NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 16)){
                            throw new IllegalArgumentException("DATO CARD NUMBER ES INCORRECTO,DEBE TENER MINIMO 1 CARACTER , MÁXIMO 16.");
                        }
                        atributosPayPoint.setCardNumber(str);
                        break;
                    case 9:
                       //EXPIRATION DATE - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE EXPIRATION DATE NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 16)){
                            throw new IllegalArgumentException("DATO EXPIRATION DATE ES INCORRECTO,DEBE TENER MINIMO 1 CARACTER , MÁXIMO 4.");
                        }
                        atributosPayPoint.setExpirationDate(str);
                        break; 
                    case 10:
                       //CARD STATUS FLAG - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CARD STATUS FLAG NULO O VACIO");
                        }
                        atributosPayPoint.setCardStatusFlag(str);
                        break;       
                     case 11:
                       //INSTALLMENT - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE INSTALLEMENT NULO O VACIO");
                        }
                        if (!str.equals("true") && !str.equals("false")){
                            throw new IllegalArgumentException("DATO INSTALLEMENT ES INCORRECTO,DEBE SER TRUE O FALSE.");
                        }
                        atributosPayPoint.setInstallment(str);
                        break;      
                      case 12:
                       //INSTALLMENT SEQUENCE- REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE INSTALLMENT SEQUENCE NULO O VACIO");
                        }
                        atributosPayPoint.setInstallmentSequence(str);
                        break;  
                    case 13:
                       //PURCHASE ID- REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE PURCHASE ID NULO O VACIO");
                        }
                        atributosPayPoint.setPurchaseID(str);
                        break;       
                    case 14:
                       //NAME FULL- REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE NAME FULL NULO O VACIO");
                        }
                        atributosPayPoint.setNameFull(str);
                        break;                        
                    case 15:
                       //APPLICATION ID- REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE APPLICATION ID NULO O VACIO");
                        }
                        atributosPayPoint.setApplicationID(str);
                        break;   
                    case 16:
                       //SECURITY KEY- REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE SECURITY KEY NULO O VACIO");
                        }
                        atributosPayPoint.setSecurityKey(str);
                        break;                          
                    case 17:
                       //PAYMENT CHANNEL- REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE PAYMENT CHANNEL NULO O VACIO");
                        }
                        atributosPayPoint.setPaymentChannel(str);
                        break;                         
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            //lineaDatos.setAtributosPayPointl(atributosPayPoint);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        
    //return lineaDatos;
    return atributosPayPoint;
    }
}
