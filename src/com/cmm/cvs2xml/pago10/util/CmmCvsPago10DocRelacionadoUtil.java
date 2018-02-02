/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.pago10.util;

import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.util.StringManage;
import java.math.BigDecimal;
import java.math.BigInteger;
import mx.bigdata.sat.cfdi.v33.schema.CMetodoPago;
import mx.bigdata.sat.cfdi.v33.schema.CMoneda;
/**
 *
 * @author user
 */
public class CmmCvsPago10DocRelacionadoUtil {
    public final static String idRegistro = "41";
    public final static String infoRegistro = "INFORMACIÓN DE DATOS RELACIONA DE DOCUMENTOS PARA FORMATO COMPLEMENTO DE PAGO";
    
    public static mx.bigdata.sat.cfdi.v33.schema.Pagos.Pago.DoctoRelacionado fillData(String elementoCfdi){
        mx.bigdata.sat.cfdi.v33.schema.Pagos.Pago.DoctoRelacionado docRel=null;
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            docRel=new mx.bigdata.sat.cfdi.v33.schema.Pagos.Pago.DoctoRelacionado();
            boolean tipoCambio=false;
            
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
                       //identificador   del documento relacionado con el pago - REQUERDIO
                       if ("".equals(StringManage.getValidString(str))){
                           throw new IllegalArgumentException("NO SE PERMITE EL FOLIO FISCAL DIGITAL NULO O VACIO");
                       }
                       if(!gc.isUUID(str)){
                           throw new IllegalArgumentException("EL FOLIO FISCAL DIGITAL ES INVALIDO");
                       }
                       docRel.setIdDocumento(str);
                       break;
                   case 2:
                       //Serie - OPCIONAL
                       if (!"".equals(StringManage.getValidString(str))){
                           docRel.setSerie(str);
                       }
                       break;
                   case 3:
                       //Folio - OPCIONAL
                       if (!"".equals(StringManage.getValidString(str))){
                           docRel.setFolio(str);
                       }
                       break;
                   case 4:
                       //Moneda - REQUERIDO
                       if ("".equals(StringManage.getValidString(str))){
                           throw new IllegalArgumentException("NO SE PERMITE EL TIPO DE MONEDA NULO O VACIO");
                       }
                       if(!str.equals("MXN")&&!str.equals("XXX")){
                           tipoCambio=true;
                       }
                       docRel.setMonedaDR(CMoneda.fromValue(str));
                       break;
                   case 5:
                       //tipo de cambio - CONDICIONAL
                       if(tipoCambio){
                           if ("".equals(StringManage.getValidString(str))){
                               throw new IllegalArgumentException("NO SE PERMITE EL TIPO DE CAMBIO NULO O VACIO");
                           }
                           docRel.setTipoCambioDR(new BigDecimal(str));
                       }
                       break;
                   case 6:
                       //metodo pago - REQUERIDO
                       if ("".equals(StringManage.getValidString(str))){
                           throw new IllegalArgumentException("NO SE PERMITE EL METODO DE PAGO NULO O VACIO");
                       }
                       docRel.setMetodoDePagoDR(CMetodoPago.fromValue(str));
                       break;
                   case 7:
                       //numero de parcialidad - OPCIONAL
                       if (!"".equals(StringManage.getValidString(str))){
                           docRel.setNumParcialidad(new BigInteger(str));
                       }
                       break;
                   case 8:
                       //saldo anterior - OPCIONAL
                       if (!"".equals(StringManage.getValidString(str))){
                           docRel.setImpSaldoAnt(new BigDecimal(str));
                       }
                       break;
                   case 9:
                       //importe pago - OPCIONAL
                       if (!"".equals(StringManage.getValidString(str))){
                           docRel.setImpPagado(new BigDecimal(str));
                       }
                       break;
                   case 10:
                       //importe saldo insoluto - OPCIONAL
                       if (!"".equals(StringManage.getValidString(str))){
                           docRel.setImpSaldoInsoluto(new BigDecimal(str));
                       }
                       break;
               }
               
            }
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + idRegistro);
        }
        return docRel;
    }
}
