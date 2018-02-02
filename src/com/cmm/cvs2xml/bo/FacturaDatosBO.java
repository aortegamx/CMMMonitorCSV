/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml.bo;

import com.cmm.cvs2xml.bean.FacturaDatos;
import com.cmm.cvs2xml.bean.LineaDatosConcepto;
import com.cmm.cvs2xml.bo.addenda.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import mx.bigdata.sat.cfdi.v33.schema.CMoneda;
import mx.bigdata.sat.cfdi.v33.schema.CTipoDeComprobante;
import mx.bigdata.sat.cfdi.v33.schema.CUsoCFDI;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;
import mx.bigdata.sat.cfdi.v33.schema.ObjectFactory;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/05/2014 05:19:31 PM
 */
public class FacturaDatosBO {

    private FacturaDatos facturaDatos;
    
    private boolean compilarAddendas = false;
    
    public FacturaDatosBO(){}
    
    public FacturaDatosBO(FacturaDatos facturaDatos){
        this.facturaDatos = facturaDatos;
    }
    
    /**
     * Reune todos los datos del objeto FacturaDatos, y los une en un solo objeto
     * de Comprobante, no incluye datos adicionales que no corresponden al esquema
     * oficial del SAT (CFDI 3.2)
     * @return 
     */
    public Comprobante compilarComprobante(){
        if (facturaDatos==null)
            return null;
        
        ObjectFactory of = new ObjectFactory();
        Comprobante comprobante = facturaDatos.getLineaDatosFactura().getDatosComprobante().getComprobante();
        comprobante.setVersion("3.3");
        
            Comprobante.Emisor emisor = of.createComprobanteEmisor();
            emisor.setRfc(facturaDatos.getLineaDatosCliente().getRfcEmisor());
            if(facturaDatos.getLineaDatosEmisor() !=null && facturaDatos.getLineaDatosEmisor().getRfcEmisor()!=null){
                if(facturaDatos.getLineaDatosEmisor().getDatosEmisor().getEmisor().getNombre()!=null){
                    emisor.setNombre(facturaDatos.getLineaDatosEmisor().getDatosEmisor().getEmisor().getNombre());
                }
                if(facturaDatos.getLineaDatosEmisor().getDatosEmisor().getEmisor().getRegimenFiscal()!=null){
                    emisor.setRegimenFiscal(facturaDatos.getLineaDatosEmisor().getDatosEmisor().getEmisor().getRegimenFiscal());
                }
            }
            
        comprobante.setEmisor(emisor);
        if(facturaDatos.getNominaDatos()!=null){//si es de tipo nomina no le cargamos el nodo de domicilio del receptor
            Comprobante.Receptor receptor = of.createComprobanteReceptor();
            receptor.setNombre(facturaDatos.getLineaDatosCliente().getDatosReceptor().getReceptor().getNombre());
            receptor.setRfc(facturaDatos.getLineaDatosCliente().getDatosReceptor().getReceptor().getRfc());
            receptor.setUsoCFDI(facturaDatos.getLineaDatosCliente().getDatosReceptor().getReceptor().getUsoCFDI());
            receptor.setResidenciaFiscal(facturaDatos.getLineaDatosCliente().getDatosReceptor().getReceptor().getResidenciaFiscal());
            receptor.setNumRegIdTrib(facturaDatos.getLineaDatosCliente().getDatosReceptor().getReceptor().getNumRegIdTrib());
            comprobante.setReceptor(receptor);
        }else{
            comprobante.setReceptor(facturaDatos.getLineaDatosCliente().getDatosReceptor().getReceptor());
        }
        
        Comprobante.Impuestos impuestos = of.createComprobanteImpuestos();
        
        //CFDI Relacionados
        if(facturaDatos.getListaDatosCFDIRelacionado()!=null){
            Comprobante.CfdiRelacionados listaCFDI=new Comprobante.CfdiRelacionados();
            listaCFDI.setTipoRelacion(facturaDatos.getListaDatosCFDIRelacionado().getTipoRelacion());
            if(facturaDatos.getListaDatosCFDIRelacionado().getDatosCFDIRelacionado()!=null&&facturaDatos.getListaDatosCFDIRelacionado().getDatosCFDIRelacionado().size()>0){
                listaCFDI.getCfdiRelacionado().addAll(facturaDatos.getListaDatosCFDIRelacionado().getDatosCFDIRelacionado());
                comprobante.setCfdiRelacionados(listaCFDI);
            }
        }
        
        
        //Total de Impuestos
        if (facturaDatos.getLineaDatosFactura().getTotalRetenciones()!=null){
                impuestos.setTotalImpuestosRetenidos(facturaDatos.getLineaDatosFactura().getTotalRetenciones());
        }
        if (facturaDatos.getLineaDatosFactura().getTotalTraslados()!=null){
                impuestos.setTotalImpuestosTrasladados(facturaDatos.getLineaDatosFactura().getTotalTraslados());
        }
        
        double totalRetenidos=0;
        double totalTransladados=0;
        
        //Traslados
        {
            boolean trans1=false;
            boolean trans2=false;
            //IVA
            if (facturaDatos.getLineaDatosFactura().getIVAGrupalTasa()!=null
                    && facturaDatos.getLineaDatosFactura().getIVAGrupalMonto()!=null
                    && facturaDatos.getLineaDatosFactura().getIVAGrupalMonto().doubleValue()>0){
                Comprobante.Impuestos.Traslados.Traslado traslado = of.createComprobanteImpuestosTrasladosTraslado();
                traslado.setImpuesto("002");
                traslado.setTipoFactor(facturaDatos.getLineaDatosFactura().getTipoFactorIVA());
                traslado.setTasaOCuota(new BigDecimal(facturaDatos.getLineaDatosFactura().getIVAGrupalTasa()));
                traslado.setImporte(facturaDatos.getLineaDatosFactura().getIVAGrupalMonto());
                
                if (impuestos.getTraslados()==null)
                    impuestos.setTraslados(of.createComprobanteImpuestosTraslados());
                impuestos.getTraslados().getTraslado().add(traslado);
                totalTransladados=facturaDatos.getLineaDatosFactura().getIVAGrupalMonto().doubleValue();
                trans1=true;
            }
            
            //IEPS
            if (facturaDatos.getLineaDatosFactura().getIEPSGrupalTasa()!=null
                    && facturaDatos.getLineaDatosFactura().getIEPSGrupalMonto()!=null
                    && facturaDatos.getLineaDatosFactura().getIEPSGrupalMonto().doubleValue()>0){
                Comprobante.Impuestos.Traslados.Traslado traslado = of.createComprobanteImpuestosTrasladosTraslado();
                traslado.setImpuesto("003");
                traslado.setTasaOCuota(new BigDecimal(facturaDatos.getLineaDatosFactura().getIEPSGrupalTasa()));
                traslado.setTipoFactor(facturaDatos.getLineaDatosFactura().getTipoFactorIEPS());
                traslado.setImporte(facturaDatos.getLineaDatosFactura().getIEPSGrupalMonto());
                
                if (impuestos.getTraslados()==null)
                    impuestos.setTraslados(of.createComprobanteImpuestosTraslados());
                impuestos.getTraslados().getTraslado().add(traslado);
                if(trans1){
                    totalTransladados+=facturaDatos.getLineaDatosFactura().getIEPSGrupalMonto().doubleValue();
                }else{
                    totalTransladados=facturaDatos.getLineaDatosFactura().getIEPSGrupalMonto().doubleValue();
                }
                trans2=true;
            }
            if(trans1||trans2){
                if(totalTransladados>0){
                    impuestos.setTotalImpuestosTrasladados(new BigDecimal(totalTransladados).setScale(2, RoundingMode.HALF_UP));
                }
            }
        }
        
        //Retenciones
        {
            boolean reten1=false;
            boolean reten2=false;
            //IVA Retenido
            if (facturaDatos.getLineaDatosFactura().getIVARetenidoMonto()!=null && facturaDatos.getLineaDatosFactura().getIVARetenidoMonto().doubleValue() > 0){
                Comprobante.Impuestos.Retenciones.Retencion retencion = of.createComprobanteImpuestosRetencionesRetencion();
                retencion.setImpuesto("002");
                retencion.setImporte(facturaDatos.getLineaDatosFactura().getIVARetenidoMonto());
                
                if (impuestos.getRetenciones()==null)
                    impuestos.setRetenciones(of.createComprobanteImpuestosRetenciones());
                impuestos.getRetenciones().getRetencion().add(retencion);
                totalRetenidos=facturaDatos.getLineaDatosFactura().getIVARetenidoMonto().doubleValue();
                reten1=true;
            }
            
            //ISR
            if (facturaDatos.getLineaDatosFactura().getISRRetenidoMonto()!=null && facturaDatos.getLineaDatosFactura().getISRRetenidoMonto().doubleValue() > 0){
                Comprobante.Impuestos.Retenciones.Retencion retencion = of.createComprobanteImpuestosRetencionesRetencion();
                retencion.setImpuesto("001");
                retencion.setImporte(facturaDatos.getLineaDatosFactura().getISRRetenidoMonto());
                
                if (impuestos.getRetenciones()==null)
                    impuestos.setRetenciones(of.createComprobanteImpuestosRetenciones());
                impuestos.getRetenciones().getRetencion().add(retencion);
                if(reten1){
                    totalRetenidos=facturaDatos.getLineaDatosFactura().getISRRetenidoMonto().doubleValue();
                }else{
                    totalRetenidos+=facturaDatos.getLineaDatosFactura().getISRRetenidoMonto().doubleValue();
                }
                reten2=true;
            }
            if(reten1||reten2){
                if(totalRetenidos>0){
                    impuestos.setTotalImpuestosRetenidos(new BigDecimal(totalRetenidos).setScale(2, RoundingMode.HALF_UP));
                }
            }
        }
        if(totalRetenidos>0||totalTransladados>0){
            comprobante.setImpuestos(impuestos);
        }
        
        
        if (facturaDatos.getListaLineaDatosConceptos().size()>0){
            comprobante.setConceptos(of.createComprobanteConceptos());
            
            for (LineaDatosConcepto lineaDatosConcepto : facturaDatos.getListaLineaDatosConceptos()){
                if (lineaDatosConcepto.getDatosConcepto().getConcepto()!=null)
                    comprobante.getConceptos().getConcepto().add(lineaDatosConcepto.getDatosConcepto().getConcepto());
            }
            //verificamos los impuestos
            int i=0;
            for(mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto item:comprobante.getConceptos().getConcepto()){
                double totalTransladadoConcepto=0;
                double totalRetenidoConcepto=0;
                
                int aux=0;
                int index=-1;
                //transladados
                if(item.getImpuestos() != null && item.getImpuestos().getTraslados() != null){
                    for(mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado translado:item.getImpuestos().getTraslados().getTraslado()){
                        totalTransladadoConcepto+=translado.getImporte().doubleValue();
                        if(translado.getImporte().doubleValue()<=0){
                            index=aux;
                        }
                        aux++;
                    }
                
                
                    //remover el nodo si se encuentra en 0
                    if(index!=-1){
                        item.getImpuestos().getTraslados().getTraslado().remove(index);
                    }

                    if(totalTransladadoConcepto<=0){
                        comprobante.getConceptos().getConcepto().get(i).getImpuestos().setTraslados(null);
                    }
                }
                
                aux=0;
                index=-1;
                //retenidos
                if(item.getImpuestos() != null && item.getImpuestos().getRetenciones() != null){
                    for(mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.Impuestos.Retenciones.Retencion retencion:item.getImpuestos().getRetenciones().getRetencion()){
                        totalRetenidoConcepto+=retencion.getImporte().doubleValue();
                        if(retencion.getImporte().doubleValue()<=0){
                            index=aux;
                        }
                    }
                
                
                    //remover el nodo si se encuentra en 0
                    if(index!=-1){
                        item.getImpuestos().getRetenciones().getRetencion().remove(index);
                    }

                    if(totalRetenidoConcepto<=0){
                        comprobante.getConceptos().getConcepto().get(i).getImpuestos().setRetenciones(null);
                    }

                    //verificamos si quitamos el nodo
                    if(totalTransladadoConcepto<=0&&totalRetenidoConcepto<=0){
                        comprobante.getConceptos().getConcepto().get(i).setImpuestos(null);
                    }
                }
                
                i++;
            }
        }
        
        //-------Complementos-----------------------------
            //comprobante.setComplemento(of.createComprobanteComplemento());
            comprobante.getComplemento().add(of.createComprobanteComplemento());
        
            //Complemento Nómina
            if (facturaDatos.getNominaDatos()!=null){
                comprobante.getConceptos().getConcepto().get(0).setClaveProdServ("84111505");
                comprobante.getConceptos().getConcepto().get(0).setClaveUnidad("ACT");
                comprobante.getConceptos().getConcepto().get(0).setImpuestos(null);
                comprobante.getConceptos().getConcepto().get(0).setDescripcion("Pago de nómina");
                comprobante.setImpuestos(null);
                comprobante.getComplemento().get(0).getAny().add(NominaDatosBO.compilarComplemento(facturaDatos.getNominaDatos()));
                if(NominaDatosBO.totalDeducciones.doubleValue() > 0){
                    comprobante.setDescuento(NominaDatosBO.totalDeducciones);
                    comprobante.getConceptos().getConcepto().get(0).setDescuento(NominaDatosBO.totalDeducciones);
                }
            }
            
            //complemento de pago
            if(facturaDatos.getPagos()!=null){
                comprobante.setSubTotal(new BigDecimal(0));
                comprobante.setMoneda(CMoneda.XXX);
                comprobante.setTotal(new BigDecimal(0));
                comprobante.setTipoDeComprobante(CTipoDeComprobante.P);
                comprobante.getReceptor().setUsoCFDI(CUsoCFDI.P_01);
                comprobante.setDescuento(BigDecimal.ZERO);
                comprobante.getConceptos().getConcepto().get(0).setClaveProdServ("84111506");
                comprobante.getConceptos().getConcepto().get(0).setClaveUnidad("ACT");
                comprobante.getConceptos().getConcepto().get(0).setValorUnitario(BigDecimal.ZERO);
                comprobante.getConceptos().getConcepto().get(0).setImporte(BigDecimal.ZERO);
                comprobante.getConceptos().getConcepto().get(0).setDescripcion("Pago");
                comprobante.getConceptos().getConcepto().get(0).setImpuestos(null);
                comprobante.getConceptos().getConcepto().get(0).setNoIdentificacion(null);
                comprobante.setImpuestos(null);
                comprobante.getComplemento().get(0).getAny().add(new PagoDatosBO().compilarPago(facturaDatos.getPagos()));
            }
            
            //Complemento Impuestos Locales
            if (facturaDatos.getImpLocalDatos()!=null){
                comprobante.getComplemento().get(0).getAny().add(ImpLocalDatosBO.compilarComplemento(facturaDatos.getImpLocalDatos()));
            }
            
            try{
                if(facturaDatos.getDonatDatos() != null && facturaDatos.getDonatDatos().getLineaDatosDonat() != null
                        && facturaDatos.getDonatDatos().getLineaDatosDonat().getDatosGeneralesDonatarias()!= null &&
                        facturaDatos.getDonatDatos().getLineaDatosDonat().getDatosGeneralesDonatarias().getDonatarias() != null){
                    comprobante.getComplemento().get(0).getAny().add(facturaDatos.getDonatDatos().getLineaDatosDonat().getDatosGeneralesDonatarias().getDonatarias());
                }}catch(Exception e){e.printStackTrace();}
            
        //-------FIN Complementos-------------------------
            
        //-------Addendas--------------------
            //Solo si se solicita compilar Addendas, se agregan al XML
            if (compilarAddendas){
                comprobante.setAddenda(of.createComprobanteAddenda());
                
                //Addenda Sanofi v1.0
                if (facturaDatos.getAddendaSanofiDatos()!=null){
                    comprobante.getAddenda().getAny().add(SanofiDatosBO.compilarAddenda(facturaDatos.getAddendaSanofiDatos()));
                }
                
                //Addenda VW PMT v1.0
                if (facturaDatos.getAddendaVwPmtDatos()!=null){
                    comprobante.getAddenda().getAny().add(VwPmtDatosBO.compilarAddenda(facturaDatos.getAddendaVwPmtDatos()));
                }
                
                //Addenda Chrysler PUA v1.0
                if (facturaDatos.getAddendaChryslerPuaDatos()!=null){
                    comprobante.getAddenda().getAny().add(ChryslerPuaDatosBO.compilarAddenda(facturaDatos.getAddendaChryslerPuaDatos()));
                }
                
                //Addenda GM v1.3
                if (facturaDatos.getAddendaGMv13Datos()!=null){
                    comprobante.getAddenda().getAny().add(GMv13DatosBO.compilarAddenda(facturaDatos.getAddendaGMv13Datos()));
                }
                
                //Addenda Ford Fom v1.0
                if (facturaDatos.getAddendaFordFomDatos()!=null){
                    comprobante.getAddenda().getAny().add(FordFomDatosBO.compilarAddenda(facturaDatos.getAddendaFordFomDatos()));
                }
                
                //Addenda Chrysler PY v1.0
                if (facturaDatos.getAddendaChryslerPpyDatos()!=null){
                    comprobante.getAddenda().getAny().add(ChryslerPpyDatosBO.compilarAddenda(facturaDatos.getAddendaChryslerPpyDatos()));
                }
                
                //Si no se recupero ninguna addenda, eliminamos el nodo
                // para que no salga vacío: "<Addenda/>
                if (comprobante.getAddenda().getAny().size()<=0)
                    comprobante.setAddenda(null);
                
            }
        //-------FIN Addendas----------------
        
        return comprobante;
    }

    public FacturaDatos getFacturaDatos() {
        return facturaDatos;
    }

    public void setFacturaDatos(FacturaDatos facturaDatos) {
        this.facturaDatos = facturaDatos;
    }

    public boolean isCompilarAddendas() {
        return compilarAddendas;
    }

    public void setCompilarAddendas(boolean compilarAddendas) {
        this.compilarAddendas = compilarAddendas;
    }
    
}
