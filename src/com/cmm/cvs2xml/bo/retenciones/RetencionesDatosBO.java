/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bo.retenciones;

import com.cmm.cvs2xml.retenciones.bean.RetencionesDatos;
import com.cmm.cvs2xml.retenciones.bean.LineaDatosImpuestoRetenido;
import mx.bigdata.sat.retencion.v1.schema.Retenciones;
import mx.bigdata.sat.retencion.v1.schema.ObjectFactory;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/05/2014 05:19:31 PM
 */
public class RetencionesDatosBO {

    private RetencionesDatos retencionesDatos;
    
    private boolean compilarAddendas = false;
    
    public RetencionesDatosBO(){}
    
    public RetencionesDatosBO(RetencionesDatos retencionesDatos){
        this.retencionesDatos = retencionesDatos;
    }
    
    /**
     * Reune todos los datos del objeto FacturaDatos, y los une en un solo objeto
     * de Comprobante, no incluye datos adicionales que no corresponden al esquema
     * oficial del SAT (CFDI 3.2)
     * @return 
     */
    public Retenciones compilarComprobante(){
        if (retencionesDatos==null)
            return null;
        
        ObjectFactory of = new ObjectFactory();
        Retenciones comprobante = retencionesDatos.getLineaDatosComprobante().getRetenciones();
        comprobante.setVersion("1.0");
        
        comprobante.setReceptor(retencionesDatos.getLineaDatosReceptor().getReceptor());
        
        comprobante.setPeriodo(retencionesDatos.getLineaDatosPeriodoTotales().getPeriodo());
        comprobante.setTotales(retencionesDatos.getLineaDatosPeriodoTotales().getTotales());
        
        if (retencionesDatos.getListaLineaDatosImpuestoRetenidos().size()>0){
            for (LineaDatosImpuestoRetenido lineaImpRetenido : retencionesDatos.getListaLineaDatosImpuestoRetenidos()){
                if (lineaImpRetenido.getImpuestoRetenido()!=null)
                    comprobante.getTotales().getImpRetenidos().add(lineaImpRetenido.getImpuestoRetenido());
            }
        }
        
        //-------Complementos-----------------------------
            comprobante.setComplemento(of.createRetencionesComplemento());
            
            //Complemento ArrendamientoEnFideicomiso
            if (retencionesDatos.getArrendamientoEnFideicomisoDatos()!=null){
                comprobante.getComplemento().getAny().add(ArrendamientoEnFideicomisoDatosBO.compilarComplemento(retencionesDatos.getArrendamientoEnFideicomisoDatos()));
            }
            
            //Complemento Dividendos
            if (retencionesDatos.getDividendosDatos()!=null){
                comprobante.getComplemento().getAny().add(DividendosDatosBO.compilarComplemento(retencionesDatos.getDividendosDatos()));
            }
            
            //Complemento PagosAExtranjeros
            if (retencionesDatos.getPagosAExtranjerosDatos()!=null){
                comprobante.getComplemento().getAny().add(PagosAExtranjerosDatosBO.compilarComplemento(retencionesDatos.getPagosAExtranjerosDatos()));
            }
            
            //Si no se recupero ningun complemento, eliminamos el nodo
            // para que no salga vacío: "<Complemento/>
            if (comprobante.getComplemento().getAny().size()<=0)
                comprobante.setComplemento(null);
            
        //-------FIN Complementos-------------------------
            
        //-------Addendas--------------------
            //Solo si se solicita compilar Addendas, se agregan al XML
            if (compilarAddendas){
                comprobante.setAddenda(of.createRetencionesAddenda());
                
                /*
                //Addenda Sanofi v1.0
                if (retencionesDatos.getAddendaSanofiDatos()!=null){
                    comprobante.getAddenda().getAny().add(SanofiDatosBO.compilarAddenda(retencionesDatos.getAddendaSanofiDatos()));
                }
                
                //Addenda VW PMT v1.0
                if (retencionesDatos.getAddendaVwPmtDatos()!=null){
                    comprobante.getAddenda().getAny().add(VwPmtDatosBO.compilarAddenda(retencionesDatos.getAddendaVwPmtDatos()));
                }
                
                //Addenda Chrysler PUA v1.0
                if (retencionesDatos.getAddendaChryslerPuaDatos()!=null){
                    comprobante.getAddenda().getAny().add(ChryslerPuaDatosBO.compilarAddenda(retencionesDatos.getAddendaChryslerPuaDatos()));
                }
                */
                
                //Si no se recupero ninguna addenda, eliminamos el nodo
                // para que no salga vacío: "<Addenda/>
                if (comprobante.getAddenda().getAny().size()<=0)
                    comprobante.setAddenda(null);
                
            }
        //-------FIN Addendas----------------
        
        return comprobante;
    }

    public RetencionesDatos getRetencionesDatos() {
        return retencionesDatos;
    }

    public void setRetencionesDatos(RetencionesDatos retencionesDatos) {
        this.retencionesDatos = retencionesDatos;
    }

    public boolean isCompilarAddendas() {
        return compilarAddendas;
    }

    public void setCompilarAddendas(boolean compilarAddendas) {
        this.compilarAddendas = compilarAddendas;
    }
    
}
