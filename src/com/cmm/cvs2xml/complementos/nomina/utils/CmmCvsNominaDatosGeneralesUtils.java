/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.complementos.nomina.utils;

import com.cmm.cvs2xml.complementos.nomina.bean.DatosGeneralesNomina;
import com.cmm.cvs2xml.util.StringManage;
import com.cmm.cvs2xml.util.GenericValidator;
import com.cmm.cvs2xml.complementos.nomina.bean.LineaDatosNomina;
import com.cmm.cvs2xml.util.DateManage;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import javax.xml.datatype.DatatypeConfigurationException;
import mx.bigdata.sat.common.nomina12.schema.CEstado;
import mx.bigdata.sat.common.nomina12.schema.CTipoNomina;
import mx.bigdata.sat.common.nomina12.schema.Nomina;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 12/05/2014 05:08:09 PM
 */
public class CmmCvsNominaDatosGeneralesUtils {

    public final static String IDREGISTRO = "20";
    public final static String INFO_REGISTRO = "INFORMACIÓN PARA EMISIÓN DE CFDI NÓMINA";
    
    public static LineaDatosNomina fillData(String elementoCfdi) throws DatatypeConfigurationException{
        LineaDatosNomina lineaDatos = null;
        
        String[] data = elementoCfdi.split("\\|");
        int x;
        
        if (data.length>0){
            GenericValidator gc = new GenericValidator();
            String formatoFecha = "yyyy-MM-dd";
            int maxDecimales;
            
            lineaDatos = new LineaDatosNomina();
            DatosGeneralesNomina datosGeneralesNomina = new DatosGeneralesNomina();
            Nomina nomina = new Nomina();
            nomina.setVersion("1.2");
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
                        if (!IDREGISTRO.equals(StringManage.getValidString(str))) {
                            throw new IllegalArgumentException("IDENTIFICADOR DE REGISTRO NO VALIDO, DEBE SER ESTRICTAMENTE \""+IDREGISTRO+"\" PARA " + INFO_REGISTRO);
                        }
                        break;
                    case 1:
                        //Registro Patronal - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isValidString(str, 1, 20)){
                                throw new IllegalArgumentException("DATO REGISTRO PATRONAL, OPCIONAL, SI SE EXPRESA DEBE TENER MINIMO 1 CARACTER , MÁXIMO 20.");
                            }
                         //   nomina.setRegistroPatronal(str);   ---> Version 1.0
                            Nomina.Emisor em = new Nomina.Emisor();
                            em.setRegistroPatronal(str);
                            nomina.setEmisor(em);
                        }
                        break;
                    case 2:
                        //Numero de empleado - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE NUMERO DE EMPLEADO NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 15)){
                            throw new IllegalArgumentException("DATO NUMERO DE EMPLEADO INCORRECTO,DEBE TENER MINIMO 1 CARACTER , MÁXIMO 15.");
                        }
                     //   nomina.setNumEmpleado(str); ---> Version 1.0
                        Nomina.Receptor rec = new Nomina.Receptor();
                        rec.setNumEmpleado(str);
                        nomina.setReceptor(rec);
                        break;
                    case 3:
                        //CURP - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE CURP NULO O VACIO");
                        }
                        if (!gc.isCURP(str)){
                            throw new IllegalArgumentException("DATO CURP INCORRECTO.");
                        }
                      //  nomina.setCURP(str);  ---> Version 1.0
                        nomina.getReceptor().setCurp(str);
                        break;
                    case 4:
                        //Tipo de Régimen - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TIPO DE REGIMEN NULO O VACIO");
                        }
                        if (!gc.isNumeric(str, 1, 10)){
                            throw new IllegalArgumentException("DATO TIPO DE REGIMEN INCORRECTO, DEBE SER UN VALOR ENTERO, CON UN VALOR MÍNIMO DE 1");
                        }
                      //  nomina.setTipoRegimen(Integer.parseInt(str));
                        if((str+"").length()<2){
                            nomina.getReceptor().setTipoRegimen("0"+str);
                        }else{
                            nomina.getReceptor().setTipoRegimen(str);
                        }
                        
                        break;
                    case 5:
                        //Numero de Seguridad Social - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isValidString(str, 1, 15)){
                                throw new IllegalArgumentException("DATO NUMERO DE SEGURIDAD SOCIAL, OPCIONAL, SI SE EXPRESA DEBE TENER MINIMO 1 CARACTER , MÁXIMO 15.");
                            }
                           // nomina.setNumSeguridadSocial(str);          ---> Version 1.0
                            nomina.getReceptor().setNumSeguridadSocial(str);
                        }
                        break;
                    case 6:
                        //Fecha de Pago - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE FECHA DE PAGO NULO O VACIO");
                        }
                        if (!gc.isDate(str, formatoFecha)){
                            throw new IllegalArgumentException("DATO FECHA DE PAGO INCORRECTO, DEBE TENER EL FORMATO " + formatoFecha);
                        }
                        nomina.setFechaPago(DateManage.dateToXMLGregorianCalendar2(DateManage.stringToDate(str, formatoFecha)));
                        break;
                    case 7:
                        //Fecha inicial de pago - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE FECHA INICIAL DE PAGO NULO O VACIO");
                        }
                        if (!gc.isDate(str, formatoFecha)){
                            throw new IllegalArgumentException("DATO FECHA INICIAL DE PAGO INCORRECTO, DEBE TENER EL FORMATO " + formatoFecha);
                        }
                        nomina.setFechaInicialPago(DateManage.dateToXMLGregorianCalendar2(DateManage.stringToDate(str, formatoFecha)));
                        break;
                    case 8:
                        //Fecha final de pago - REQUERIDO
                         if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE FECHA FINAL DE PAGO NULO O VACIO");
                        }
                        if (!gc.isDate(str, formatoFecha)){
                            throw new IllegalArgumentException("DATO FECHA FINAL DE PAGO INCORRECTO, DEBE TENER EL FORMATO " + formatoFecha);
                        }
                        nomina.setFechaFinalPago(DateManage.dateToXMLGregorianCalendar2(DateManage.stringToDate(str, formatoFecha)));
                        break;
                    case 9:
                        //Dias Pagados - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE DIAS PAGADOS NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO DIAS PAGADOS INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        nomina.setNumDiasPagados(new BigDecimal(str));
                        break;
                    case 10:
                        //Nombre Departamento - Opcional
                       // nomina.setDepartamento(str);  ---> version 1.0
                        nomina.getReceptor().setDepartamento(str);
                        break;
                    case 11:
                        //CLABE - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isCLABE(str) && !gc.isValidString(str, 10, 11) && !gc.isValidString(str, 15, 16)){
                                throw new IllegalArgumentException("DATO CUENTA CLABE INCORRECTO.");
                            }
                           // nomina.setCLABE(new BigInteger(str));                 ---> Version 1.0
                            nomina.getReceptor().setCuentaBancaria(str);
                        }
                        break;
                    case 12:
                        //Clave Banco - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.validaConExpresionRegular(str, "^([0-9]{3})$")){
                                throw new IllegalArgumentException("DATO BANCO INCORRECTO, DEBE SER UN ENTERO DE 3 DIGITOS CONFORME AL CATÁLOGO DEL SAT APLICABLE.");
                            }
                            //nomina.setBanco(Integer.parseInt(str));  ---> Versiom1.0
                            nomina.getReceptor().setBanco(str);
                        }
                        break;
                    case 13:
                        //Fecha Laboral de inicio - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isDate(str, formatoFecha)){
                                throw new IllegalArgumentException("DATO FECHA LABORAL DE INICIO INCORRECTO, DEBE TENER EL FORMATO " + formatoFecha);
                            }
                          //  nomina.setFechaInicioRelLaboral(DateManage.dateToXMLGregorianCalendar2(DateManage.stringToDate(str, formatoFecha))); --->Version 1.0
                          nomina.getReceptor().setFechaInicioRelLaboral(DateManage.dateToXMLGregorianCalendar2(DateManage.stringToDate(str, formatoFecha)));
                           
                        }
                        break;
                    case 14:
                        //Antiguedad - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isNumeric(str, 1, 5)){
                                throw new IllegalArgumentException("DATO ANTIGUEDAD INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER UN VALOR ENTERO, CON UN VALOR MÍNIMO DE 1, MÁXIMO 5 DÍGITOS.");
                            }
                            //nomina.setAntiguedad(Integer.parseInt(str));   ---> Version 1.0
                            nomina.getReceptor().setAntigüedad("P"+str+"W");
                        }
                        break;
                    case 15:
                        //Puesto - Opcional
                       // nomina.setPuesto(str);    ---> Version 1.0
                        nomina.getReceptor().setPuesto(str);
                        break;
                    case 16:
                        //Tipo de Contrato - Opcional
                      //  nomina.setTipoContrato(str);   ---> Version 1.0
                        nomina.getReceptor().setTipoContrato(str);
                        
                        break;
                    case 17:
                        //Tipo de Jornada - Opcional
                       // nomina.setTipoJornada(str);    ---> Version 1.0
                        String tjornada = "";
                        if (str != null) {
                            switch(str.toLowerCase()){
                                case "diurna": tjornada = "01"; break;
                                case "nocturna": tjornada = "02"; break;
                                case "mixta": tjornada = "03"; break;
                                case "por hora": tjornada = "04"; break;
                                case "reducida": tjornada = "05"; break;
                                case "continuada": tjornada = "06"; break;
                                case "partida": tjornada = "07"; break;
                                case "por turnos": tjornada = "08"; break;
                                case "otra jornada": tjornada = "99"; break;
                            }
                        }
                        if(!tjornada.trim().equals("")){
                            nomina.getReceptor().setTipoJornada(tjornada);
                        }
                        break;
                    case 18:
                        //Periodicidad de Pago - REQUERIDO
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE PERIODICIDAD DE PAGO NULO O VACIO");
                        }
                        if (!gc.isValidString(str, 1, 100)){
                            throw new IllegalArgumentException("DATO PERIODICIDAD DE PAGO INCORRECTO,DEBE TENER MINIMO 1 CARACTER , MÁXIMO 100.");
                        }
                      //  nomina.setPeriodicidadPago(str);   ---> Version 1.0
                        String periocidadStr = "";  // Quincenal por Default
                        if (str != null){
                            switch(str.toLowerCase()){
                                case "diario": periocidadStr = "01"; break;
                                case "semanal": periocidadStr = "02"; break;
                                case "catorcenal": periocidadStr = "03"; break;
                                case "quincenal": periocidadStr = "04"; break;
                                case "mensual": periocidadStr = "05"; break;
                                case "bimestral": periocidadStr = "06"; break;
                                case "unidad de obra": periocidadStr = "07"; break;
                                case "comision": periocidadStr = "08"; break;
                                case "precio alzado": periocidadStr = "09"; break;
                                case "otra": periocidadStr = "99"; break;
                            }
                        }
                        if(!periocidadStr.trim().equals("")){
                            nomina.getReceptor().setPeriodicidadPago(periocidadStr);
                        }
                        break;
                    case 19:
                        //Salario Base Cotización por aportaciones - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            maxDecimales = 6; 
                            if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO SALARIO BASE COTIZACIÓN POR APORTACIONES INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                          //  nomina.setSalarioBaseCotApor(new BigDecimal(str));   ---> Version 1.0
                            nomina.getReceptor().setSalarioBaseCotApor(new BigDecimal(str));
                        }
                        break;
                    case 20:
                        //Riesgo del puesto - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isNumeric(str, 1, 10)){
                                throw new IllegalArgumentException("DATO RIESGO DEL PUESTO INCORRECTO, ES OPCIONAL, SI SE EXPRESA DEBE SER UN VALOR ENTERO, CON UN VALOR MÍNIMO DE 1, MÁXIMO 10 DÍGITOS.");
                            }
                           // nomina.setRiesgoPuesto(Integer.parseInt(str));
                           nomina.getReceptor().setRiesgoPuesto(str);
                        }
                        break;
                    case 21:
                        //Salario diario integrado - Opcional
                        if (!"".equals(StringManage.getValidString(str))){
                            maxDecimales = 6; 
                            if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO SALARIO DIARIO INTEGRADO INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                         //   nomina.setSalarioDiarioIntegrado(new BigDecimal(str));
                            nomina.getReceptor().setSalarioDiarioIntegrado(new BigDecimal(str));
                        }
                        break;
                    case 22:
                        //Percepciones Gravadas - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TOTAL PERCEPCIONES GRAVADAS NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO TOTAL PERCEPCIONES GRAVADAS INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        lineaDatos.setTotalPercepcionesGravadas(new BigDecimal(str));
                        break;
                    case 23:
                        //Percepciones Exentas - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TOTAL PERCEPCIONES EXENTAS NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO TOTAL PERCEPCIONES EXENTAS INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        lineaDatos.setTotalPercepcionesExentas(new BigDecimal(str));
                        break;
                    case 24:
                        //Deducciones Gravadas - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TOTAL DEDUCCIONES GRAVADAS NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO TOTAL DEDUCCIONES GRAVADAS INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        lineaDatos.setTotalDeduccionesGravadas(new BigDecimal(str));
                        break;
                    case 25:
                        //Deducciones Exentas - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TOTAL DEDUCCIONES EXENTAS NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO TOTAL DEDUCCIONES EXENTAS INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        lineaDatos.setTotalDeduccionesExentas(new BigDecimal(str));
                        break;
                    case 26:
                        // Tipo de Nomina , Version 1.2
                        nomina.setTipoNomina(CTipoNomina.fromValue(str));
                        break;
                    case 27:
                        // Clave Entidad Federativa, Version 1.2
                        nomina.getReceptor().setClaveEntFed(CEstado.valueOf(str));
                        break;
                    case 28:
                        //Otros Pagos - REQUERIDO
                        maxDecimales = 6; 
                        if ("".equals(StringManage.getValidString(str))){
                            throw new IllegalArgumentException("NO SE PERMITE TOTAL OTROS PAGOS EXENTAS NULO O VACIO");
                        }
                        if (!gc.isDecimal(str, 1, 10, 0, maxDecimales)){
                            throw new IllegalArgumentException("DATO TOTAL OTROS PAGOS INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                        }
                        lineaDatos.setTotalOtrosPagos(new BigDecimal(str));
                        break;
                    case 29:
                        // RFC de porsona contratante, Version 1.2
                        if (!"".equals(StringManage.getValidString(str))){
                            if (!gc.isRFC(str)){
                                throw new IllegalArgumentException("RFC SUBCONTRATACION INVÁLIDO EN ESTRUCTURA");
                            }
                            Nomina.Receptor.SubContratacion subContratacion = new Nomina.Receptor.SubContratacion();
                            subContratacion.setRfcLabora(str);                        
                            nomina.getReceptor().getSubContratacion().add(subContratacion);
                        }
                        break;
                    case 30:
                        if (!"".equals(StringManage.getValidString(str))){
                            maxDecimales = 3; 
                            if (!gc.isDecimal(str, 0, 100, 0, maxDecimales)){
                                throw new IllegalArgumentException("DATO TOTAL OTROS PAGOS INCORRECTO, DEBE SER DECIMAL Y TENER MAXIMO "+ maxDecimales+ " DECIMALES.");
                            }
                            nomina.getReceptor().getSubContratacion().get(0).setPorcentajeTiempo(new BigDecimal(str).setScale(3, RoundingMode.HALF_UP));
                        }
                        break;
                }
                /*
                 * SWITCH X
                 */
                
            }
            
            datosGeneralesNomina.setNomina(nomina);
            lineaDatos.setDatosNomina(datosGeneralesNomina);
            
        }else{
            throw new IllegalArgumentException("Renglon inválido. Se esperaba mas información para el identificador " + IDREGISTRO);
        }
        
        return lineaDatos;
    }
    
}
