/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.complementos.nomina.utils;

import com.cmm.cvs2xml.bean.FacturaDatos;
import com.cmm.cvs2xml.complementos.nomina.bean.NominaDatos;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 13/05/2014 06:08:50 PM
 */
public class CmmCvsConvert {
    
    public static void convert(FacturaDatos facturaDatos, String line) throws Exception{
        
        if (facturaDatos!=null){
            try{
                
                //Registro 20 - Nómina, datos generales
                if (line.startsWith(CmmCvsNominaDatosGeneralesUtils.IDREGISTRO)){

                    facturaDatos.setNominaDatos(new NominaDatos());
                    facturaDatos.getNominaDatos().setLineaDatosNomina(CmmCvsNominaDatosGeneralesUtils.fillData(line));
                }

                //Solo si el objeto actual ya paso anteriormente por un registro
                // que lleno su atributo NominaDatos
                if (facturaDatos.getNominaDatos()!=null){
                    if (facturaDatos.getNominaDatos().getLineaDatosNomina()!=null){
                        
                        //Registro 21 - Percepcion
                        if (line.startsWith(CmmCvsNominaPercepcionUtils.idRegistro)){
                            facturaDatos.getNominaDatos().getListaPercepciones().add(CmmCvsNominaPercepcionUtils.fillData(line));
                        }

                        //Registro 22 - Deducción
                        if (line.startsWith(CmmCvsNominaDeduccionUtils.IDREGISTRO)){
                            facturaDatos.getNominaDatos().getListaDeducciones().add(CmmCvsNominaDeduccionUtils.fillData(line));
                        }

                        //Registro 23 - Incapacidad
                        if (line.startsWith(CmmCvsNominaIncapacidadUtils.idRegistro)){
                            facturaDatos.getNominaDatos().getListaIncapacidades().add(CmmCvsNominaIncapacidadUtils.fillData(line));
                        }

                        //Registro 24 - Horas extra
                        if (line.startsWith(CmmCvsNominaHorasExtraUtils.idRegistro)){
                            facturaDatos.getNominaDatos().getListaHorasExtras().add(CmmCvsNominaHorasExtraUtils.fillData(line));
                        }
                        
                        //Registro 25 - Otros Pagos
                        if (line.startsWith(CmmCvsNominaOtrosPagosUtils.IDREGISTRO)){
                            facturaDatos.getNominaDatos().getListaOtrosPagos().add(CmmCvsNominaOtrosPagosUtils.fillData(line));
                        }
                        
                    }
                }
            }catch (Exception ex){
                throw new Exception(" [Error Complemento Nómina] :" + ex.getMessage());
            }
        }
        
    }

}
