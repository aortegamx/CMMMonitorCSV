/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.complementos.donat.utils;

import com.cmm.cvs2xml.bean.FacturaDatos;
import com.cmm.cvs2xml.complementos.donat.bean.DonatDatos;
import mx.bigdata.sat.common.donat.schema.Donatarias;

/**
 *
 * @author leonardo
 */
public class CmmCvsConvert {

    public static void convert(FacturaDatos facturaDatos, String line) throws Exception{
        if (facturaDatos!=null){
            try{

                //Registro 50 - Donatarias de Comprobación, datos generales
                if (line.startsWith(CmmCvsDonatDatosGeneralesUtils.idRegistro)){
                    System.out.println("---------");
                    System.out.println("---------: " + "DONATARIAS A");
                    System.out.println("---------");
                    facturaDatos.setDonatDatos(new DonatDatos());
                    facturaDatos.getDonatDatos().setLineaDatosDonat(CmmCvsDonatDatosGeneralesUtils.fillData(line));
                    System.out.println("---------: " + "DONATARIAS B");
                }
                
                if(line.startsWith(CmmCvsDonatDatosGeneralesUtils.idRegistro) && facturaDatos != null && facturaDatos.getDonatDatos() != null && facturaDatos.getDonatDatos().getLineaDatosDonat() != null
                        && facturaDatos.getDonatDatos().getLineaDatosDonat().getDatosGeneralesDonatarias()!= null &&
                        facturaDatos.getDonatDatos().getLineaDatosDonat().getDatosGeneralesDonatarias().getDonatarias() != null
                        && facturaDatos.getDonatDatos().getLineaDatosDonat().getDatosGeneralesDonatarias().getDonatarias().getNoAutorizacion()!=null){
                    System.out.println("---------: " + "DONATARIAS C");
                }else if(line.startsWith(CmmCvsDonatDatosGeneralesUtils.idRegistro)){
                    System.out.println("---------: " + "DONATARIAS D");
                    try{
                        facturaDatos.getDonatDatos();
                        System.out.println("---------: " + "DONATARIAS E");
                    }catch(Exception e){e.printStackTrace();}
                    try{
                        facturaDatos.getDonatDatos().getLineaDatosDonat();
                        System.out.println("---------: " + "DONATARIAS F");
                    }catch(Exception e){e.printStackTrace();}
                    try{
                        facturaDatos.getDonatDatos().getLineaDatosDonat().getDatosGeneralesDonatarias();
                        System.out.println("---------: " + "DONATARIAS G");
                    }catch(Exception e){e.printStackTrace();}
                    try{
                        facturaDatos.getDonatDatos().getLineaDatosDonat().getDatosGeneralesDonatarias().getDonatarias();
                        System.out.println("---------: " + "DONATARIAS H");
                    }catch(Exception e){e.printStackTrace();}
                    try{
                        facturaDatos.getDonatDatos().getLineaDatosDonat().getDatosGeneralesDonatarias().getDonatarias().getNoAutorizacion();
                        System.out.println("---------: " + "DONATARIAS I");
                    }catch(Exception e){e.printStackTrace();}                    
                }

                /*if (donatariasDatos!=null){
                    //Solo si el objeto actual ya paso anteriormente por un registro de inicio para Donatarias
                    if (donatariasDatos.getLineaDatosDonat()!=null){
                        if (donatariasDatos.getLineaDatosDonat().getDatosGeneralesDonatarias()!=null){
                            if (donatariasDatos.getLineaDatosDonat().getDatosGeneralesDonatarias().getDonatarias()!=null){

                                    //Registro 111 - Detalles de Cuentas de Donatarias
                                    if (line.startsWith(CmmCvsDonatariasCuentaUtils.idRegistro)){
                                        donatariasDatos.getListaCuentas().add(CmmCvsDonatariasCuentaUtils.fillData(line));
                                    }

                            }
                        }
                    }
                }*/
            }catch (Exception ex){
                throw new Exception(" Error en conversión Formato Donatarias de Comprobación :" + ex.getMessage());
            }
        }
        
        //return donatariasDatos;
    }
    
}