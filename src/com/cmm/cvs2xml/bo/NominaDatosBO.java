/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bo;

import com.cmm.cvs2xml.complementos.nomina.bean.NominaDatos;
import java.math.BigDecimal;
import java.math.RoundingMode;
import mx.bigdata.sat.common.nomina12.schema.Nomina;
import mx.bigdata.sat.common.nomina12.schema.Nomina.Deducciones.Deduccion;
import mx.bigdata.sat.common.nomina12.schema.Nomina.Percepciones.Percepcion;
import mx.bigdata.sat.common.nomina12.schema.ObjectFactory;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 13/05/2014 06:41:13 PM
 */
public class NominaDatosBO {
    
    static BigDecimal totalDeducciones = BigDecimal.ZERO;
    
    public static Nomina compilarComplemento(NominaDatos nominaDatos){
        Nomina nomina = null;
        
        if (nominaDatos!=null){
            if (nominaDatos.getLineaDatosNomina()!=null){
                
                nomina = nominaDatos.getLineaDatosNomina().getDatosNomina().getNomina();
                
                if (nomina!=null){
                    nomina.setVersion("1.2");
                    ObjectFactory of = new ObjectFactory();
                    
                    //Percepciones
                    if (nominaDatos.getListaPercepciones().size()>0){
                        nomina.setPercepciones(of.createNominaPercepciones());
                        nomina.getPercepciones().setTotalExento(nominaDatos.getLineaDatosNomina().getTotalPercepcionesExentas());
                        nomina.getPercepciones().setTotalGravado(nominaDatos.getLineaDatosNomina().getTotalPercepcionesGravadas());
                        nomina.getPercepciones().getPercepcion().addAll(nominaDatos.getListaPercepciones());
                        
                        ////***totales dependiendo el tipo de percepcion:
                        BigDecimal totalExentoPercepcionesTotalSueldos = BigDecimal.ZERO;
                        BigDecimal totalAgravadoPercepcionesTotalSueldos = BigDecimal.ZERO;
                        BigDecimal totalExentoPercepcionesTotalSeparacionIndemnizacion = BigDecimal.ZERO;
                        BigDecimal totalAgravadoPercepcionesTotalSeparacionIndemnizacion = BigDecimal.ZERO;
                        BigDecimal totalExentoPercepcionesTotalJubilacionPensionRetiro = BigDecimal.ZERO;
                        BigDecimal totalAgravadoPercepcionesTotalJubilacionPensionRetiro = BigDecimal.ZERO;
                        for(Percepcion perce : nomina.getPercepciones().getPercepcion()){
                            if(perce.getTipoPercepcion().trim().equals("019")){
                                for(Percepcion.HorasExtra horasExtra : nominaDatos.getListaHorasExtras()){
                                //Percepcion.HorasExtra horasExtra = of.createNominaPercepcionesPercepcionHorasExtra();                                
                                    perce.getHorasExtra().add(horasExtra);
                                }
                            }
                            //nomina.getPercepciones().getPercepcion().add(npp);
                            if(!perce.getTipoPercepcion().trim().equals("022") && !perce.getTipoPercepcion().trim().equals("023") && !perce.getTipoPercepcion().trim().equals("025") && !perce.getTipoPercepcion().trim().equals("039") && !perce.getTipoPercepcion().trim().equals("044")){
                                totalExentoPercepcionesTotalSueldos = totalExentoPercepcionesTotalSueldos.add(perce.getImporteExento());
                                totalAgravadoPercepcionesTotalSueldos = totalAgravadoPercepcionesTotalSueldos.add(perce.getImporteGravado());

                            }
                            if(perce.getTipoPercepcion().trim().equals("022") || perce.getTipoPercepcion().trim().equals("023") || perce.getTipoPercepcion().trim().equals("025")){
                                totalExentoPercepcionesTotalSeparacionIndemnizacion = totalExentoPercepcionesTotalSeparacionIndemnizacion.add(perce.getImporteExento());
                                totalAgravadoPercepcionesTotalSeparacionIndemnizacion = totalAgravadoPercepcionesTotalSeparacionIndemnizacion.add(perce.getImporteGravado());
                            }
                            if(perce.getTipoPercepcion().trim().equals("039") || perce.getTipoPercepcion().trim().equals("044")){
                                totalExentoPercepcionesTotalJubilacionPensionRetiro = totalExentoPercepcionesTotalJubilacionPensionRetiro.add(perce.getImporteExento());
                                totalAgravadoPercepcionesTotalJubilacionPensionRetiro = totalAgravadoPercepcionesTotalJubilacionPensionRetiro.add(perce.getImporteGravado());
                            }
                        }                       
                        
                        if(totalExentoPercepcionesTotalSueldos.doubleValue() > 0 || totalAgravadoPercepcionesTotalSueldos.doubleValue() > 0){
                            nomina.getPercepciones().setTotalSueldos((totalExentoPercepcionesTotalSueldos.add(totalAgravadoPercepcionesTotalSueldos)).setScale(2, RoundingMode.HALF_UP));
                        } 
                        if(totalExentoPercepcionesTotalSeparacionIndemnizacion.doubleValue() > 0 || totalAgravadoPercepcionesTotalSeparacionIndemnizacion.doubleValue() > 0){
                            nomina.getPercepciones().setTotalSeparacionIndemnizacion((totalExentoPercepcionesTotalSeparacionIndemnizacion.add(totalAgravadoPercepcionesTotalSeparacionIndemnizacion)).setScale(2, RoundingMode.HALF_UP));                            
                        }
                        if(totalExentoPercepcionesTotalJubilacionPensionRetiro.doubleValue() > 0 || totalAgravadoPercepcionesTotalJubilacionPensionRetiro.doubleValue() > 0){
                            nomina.getPercepciones().setTotalJubilacionPensionRetiro((totalExentoPercepcionesTotalJubilacionPensionRetiro.add(totalAgravadoPercepcionesTotalJubilacionPensionRetiro)).setScale(2, RoundingMode.HALF_UP));
                        }
                        
                        BigDecimal totalPercepcionesTotales = BigDecimal.ZERO;
                        totalPercepcionesTotales = (nominaDatos.getLineaDatosNomina().getTotalPercepcionesExentas().add(nominaDatos.getLineaDatosNomina().getTotalPercepcionesGravadas()));
                        nomina.setTotalPercepciones((totalPercepcionesTotales).setScale(2, RoundingMode.HALF_UP));
                        ////***
                        
                    }
                    
                    //Deducciones
                    if (nominaDatos.getListaDeducciones().size()>0){
                        nomina.setDeducciones(of.createNominaDeducciones());
                     //   nomina.getDeducciones().setTotalExento(nominaDatos.getLineaDatosNomina().getTotalDeduccionesExentas());
                     //   nomina.getDeducciones().setTotalGravado(nominaDatos.getLineaDatosNomina().getTotalDeduccionesGravadas());
                       
                        nomina.getDeducciones().getDeduccion().addAll(nominaDatos.getListaDeducciones());
                        ////***totales dependiendo el tipo de percepcion:
                        BigDecimal totalOtrosDeducciones = BigDecimal.ZERO;
                        BigDecimal totalRetenidoDeducciones = BigDecimal.ZERO;
                        for(Deduccion deduc : nominaDatos.getListaDeducciones()){
                            if(deduc.getTipoDeduccion().equals("002")){
                                totalRetenidoDeducciones = totalRetenidoDeducciones.add(deduc.getImporte());
                            }else{
                                totalOtrosDeducciones = totalOtrosDeducciones.add(deduc.getImporte());
                            }
                        }
                        if(totalOtrosDeducciones.doubleValue() > 0){
                            nomina.getDeducciones().setTotalOtrasDeducciones(totalOtrosDeducciones);
                        }
                        if(totalRetenidoDeducciones.doubleValue() > 0){
                            nomina.getDeducciones().setTotalImpuestosRetenidos(totalRetenidoDeducciones);
                        }
                        nomina.setTotalDeducciones(totalOtrosDeducciones.add(totalRetenidoDeducciones).setScale(2, RoundingMode.HALF_UP));
                        totalDeducciones = totalOtrosDeducciones.add(totalRetenidoDeducciones).setScale(2, RoundingMode.HALF_UP);
                        ////***
                    }
                    
                    //Incapacidades
                    if (nominaDatos.getListaIncapacidades().size()>0){
                        nomina.setIncapacidades(of.createNominaIncapacidades());
                        nomina.getIncapacidades().getIncapacidad().addAll(nominaDatos.getListaIncapacidades());
                    }
                    
                    //Horas Extra
                    if (nominaDatos.getListaHorasExtras().size()>0){
                      //  nomina.setHorasExtras(of.createNominaHorasExtras());
                      //  nomina.getHorasExtras().getHorasExtra().addAll(nominaDatos.getListaHorasExtras());
                    }
                    
                    if (nominaDatos.getListaOtrosPagos().size()>0){
                        nomina.setOtrosPagos(of.createNominaOtrosPagos());
                     //   nomina.getDeducciones().setTotalExento(nominaDatos.getLineaDatosNomina().getTotalDeduccionesExentas());
                     //   nomina.getDeducciones().setTotalGravado(nominaDatos.getLineaDatosNomina().getTotalDeduccionesGravadas());
                       
                        nomina.getOtrosPagos().getOtroPago().addAll(nominaDatos.getListaOtrosPagos());
                        ////***totales dependiendo el tipo de percepcion:
                        BigDecimal totalOtrosPagos = BigDecimal.ZERO;                        
                        for(Nomina.OtrosPagos.OtroPago otroPag : nominaDatos.getListaOtrosPagos()){
                            if(otroPag.getTipoOtroPago().equals("002")){
                                Nomina.OtrosPagos.OtroPago.SubsidioAlEmpleo noose = of.createNominaOtrosPagosOtroPagoSubsidioAlEmpleo();
                                noose.setSubsidioCausado(otroPag.getImporte());   
                                otroPag.setSubsidioAlEmpleo(noose);
                                totalOtrosPagos = totalOtrosPagos.add(otroPag.getImporte()).setScale(2, RoundingMode.HALF_UP);
                            }                            
                        } 
                        
                        if(nominaDatos.getLineaDatosNomina().getTotalOtrosPagos().doubleValue() > 0){
                            nomina.setTotalOtrosPagos(nominaDatos.getLineaDatosNomina().getTotalOtrosPagos().setScale(2, RoundingMode.HALF_UP));                            
                        }
                        ////***
                    }
                    
                }
                
            }
        }
        
        return nomina;
    }

}
