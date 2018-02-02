/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.pago10.util;

import com.cmm.cvs2xml.pago10.bean.DatosPago;
import com.cmm.cvs2xml.util.DateManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import javax.xml.datatype.DatatypeConfigurationException;
import mx.bigdata.sat.cfdi.v33.schema.CMoneda;

/**
 *
 * @author user
 */
public class CmmCvsPago10DatosGeneralesUtils {
    public final static String idRegistro = "40";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS GENERALES PARA FORMATO COMPLEMENTO DE PAGO";
    
    public static DatosPago fillData(String elementoCfdi) throws DatatypeConfigurationException{
        DatosPago datosPago=null;
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            
            datosPago=new DatosPago();
            
            mx.bigdata.sat.cfdi.v33.schema.Pagos.Pago pago=new mx.bigdata.sat.cfdi.v33.schema.Pagos.Pago();
            boolean tipoCambio=false;
            boolean cadenaPago=false;
            
            for (x=0; x < data.length; x++){
               String str = data[x];
               if (str!=null){
                   str = str.trim();
                   if ("".equals(str)) str =null;
               }
               
               switch(x){
                   case 0:
                       //Identificador Registro - REQUERIDO
                        if (!idRegistro.equals(StringManage.getValidString(str))) {
                            throw new IllegalArgumentException("IDENTIFICADOR DE REGISTRO NO VALIDO, DEBE SER ESTRICTAMENTE \""+idRegistro+"\" PARA " + infoRegistro);
                        }
                       break;
                   case 1:
                       //fecha y hora en la que el  beneficiario  recibe  el  pago - REQUERIDO
                       if ("".equals(StringManage.getValidString(str))){
                           throw new IllegalArgumentException("NO SE PERMITE LA FECHA DE PAGO NULO O VACIO");
                       }
                       if(!gc.isDate(str)){
                           throw new IllegalArgumentException("LA FECHA DE PAGO ES INVALIDO");
                       }
                       pago.setFechaPago(DateManage.dateToXMLGregorianCalendar(DateManage.stringToDate(str, "dd/MM/yyyy")));
                       break;
                   case 2:
                       //forma en que se realiza el pago - REQUERIDO
                       if ("".equals(StringManage.getValidString(str))){
                           throw new IllegalArgumentException("NO SE PERMITE LA FORMA DE PAGO NULO O VACIO");
                       }
                       pago.setFormaDePagoP(str);
                       break;
                   case 3:
                       //Moneda - REQUERIDO
                       if ("".equals(StringManage.getValidString(str))){
                           throw new IllegalArgumentException("NO SE PERMITE EL TIPO DE MONEDA NULO O VACIO");
                       }
                       if(!str.equals("MXN")&&!str.equals("XXX")){
                           tipoCambio=true;
                       }
                       pago.setMonedaP(CMoneda.fromValue(str));
                       break;
                   case 4:
                       //tipo de cambio - CONDICIONAL
                       if(tipoCambio){
                           if ("".equals(StringManage.getValidString(str))){
                               throw new IllegalArgumentException("NO SE PERMITE EL TIPO DE CAMBIO NULO O VACIO");
                           }
                           pago.setTipoCambioP(new BigDecimal(str));
                       }
                       break;
                   case 5:
                       //Monto de pago - REQUERIDO
                       if ("".equals(StringManage.getValidString(str))){
                           throw new IllegalArgumentException("NO SE PERMITE EL MONTO NULO O VACIO");
                       }
                       pago.setMonto(new BigDecimal(str));
                       break;
                   case 6:
                       //número de cheque, número  de  autorización,  número  de  referencia - OPCIONAL
                       if(!"".equals(StringManage.getValidString(str))){
                           pago.setNumOperacion(str);
                       }
                       break;
                   case 7:
                       //RFC  de  la entidad   emisora   de   la   cuenta   origen - OPTIOCNAL
                       if(!"".equals(StringManage.getValidString(str))){
                           pago.setRfcEmisorCtaOrd(str);
                       }
                       break;
                   case 8:
                       //nombre  del  banco ordenante - OPCIONAL
                       if(!"".equals(StringManage.getValidString(str))){
                           pago.setNumOperacion(str);
                       }
                       break;
                   case 9:
                       //número  de  la cuenta con la que se realizó el pago - OPCIONAL
                       if(!"".equals(StringManage.getValidString(str))){
                           pago.setCtaOrdenante(str);
                       }
                       break;
                   case 10:
                       //RFC  de  la entidad  operadora  de  la cuenta  destino - OPCIONAL
                       if(!"".equals(StringManage.getValidString(str))){
                           pago.setRfcEmisorCtaBen(str);
                       }
                       break;
                   case 11:
                       //número de cuenta en  donde  se  recibió  el  pago - OPCIONAL
                       if(!"".equals(StringManage.getValidString(str))){
                           pago.setCtaBeneficiario(str);
                       }
                       break;
                   case 12:
                       //clave  del  tipo  de cadena de pago que genera la entidad receptora del pago - OPCIONAL
                       if(!"".equals(StringManage.getValidString(str))){
                           cadenaPago=true;
                           pago.setTipoCadPago(str);
                       }
                       break;
                   case 13:
                       //certificado que  ampara  al  pago - CONDICIONAL
                       if(cadenaPago){
                           if ("".equals(StringManage.getValidString(str))){
                               throw new IllegalArgumentException("NO SE PERMITE EL CERTIFICADO DE PAGO NULO O VACIO");
                           }
                           pago.setCertPago(str.getBytes());
                       }
                       break;
                   case 14:
                       //cadena  original  del comprobante de pago generado por la entidad emisora de la  cuenta  beneficiaria - CONDICIONAL
                       if(cadenaPago){
                           if ("".equals(StringManage.getValidString(str))){
                               throw new IllegalArgumentException("NO SE PERMITE LA CADENA DE PAGO NULO O VACIO");
                           }
                           pago.setCadPago(str);
                       }
                       break;
                   case 15:
                       //ello  digital  que  se asocie al pago - CONDICIONAL
                       if(cadenaPago){
                           if ("".equals(StringManage.getValidString(str))){
                               throw new IllegalArgumentException("NO SE PERMITE EL SELLO DE PAGO NULO O VACIO");
                           }
                           pago.setSelloPago(str.getBytes());
                       }
                       break;
               }
            }
            datosPago.setPagos(pago);
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        return datosPago;
    }
}
