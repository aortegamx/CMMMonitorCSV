/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cmm.cvs2xml;

import com.cmm.cvs2xml.bean.FacturaDatos;
import com.cmm.cvs2xml.bean.LineaDatosConcepto;
import com.cmm.cvs2xml.econtabilidad.v13.balanza.bean.BalanzaDatos;
import com.cmm.cvs2xml.econtabilidad.v13.catalogo.bean.CatalogoDatos;
import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.bean.AuxiliarCtasDatos;
import com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.bean.AuxiliarFoliosDatos;
import com.cmm.cvs2xml.econtabilidad.v13.polizas.bean.PolizasDatos;
import com.cmm.cvs2xml.paypoint.bean.PayPointDatos;
import com.cmm.cvs2xml.retenciones.bean.RetencionesDatos;
import com.cmm.cvs2xml.retenciones.utils.CmmCvsRetencionesComprobanteUtils;
import com.cmm.cvs2xml.util.UnicodeBOMInputStream;
import com.cmm.cvs2xml.utils.CmmCvsCFDIRelacionadoUtils;
import com.cmm.cvs2xml.utils.CmmCvsComprobanteUtils;
import com.cmm.cvs2xml.utils.CmmCvsConceptoUtils;
import com.cmm.cvs2xml.utils.CmmCvsDatosAccionPersonalizadaUtils;
import com.cmm.cvs2xml.utils.CmmCvsDatosAdicionalesUtils;
import com.cmm.cvs2xml.utils.CmmCvsDatosTipoObjetoConceptoUtils;
import com.cmm.cvs2xml.utils.CmmCvsEmisorUtils;
import com.cmm.cvs2xml.utils.CmmCvsExpedidoEnUtils;
import com.cmm.cvs2xml.utils.CmmCvsInfoAduaneraUtils;
import com.cmm.cvs2xml.utils.CmmCvsInfoPredialUtils;
import com.cmm.cvs2xml.utils.CmmCvsParteConceptoUtils;
import com.cmm.cvs2xml.utils.CmmCvsReceptorUtils;
import com.cmm.cvs2xml.utils.CmmCvsTotalImpuestosFederalesUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISCesar poseidon24@hotmail.com
 * @since 9/05/2014 10:33:49 AM
 */
public class CmmCvsConvert {
    
    private List<FacturaDatos> listaFacturaDatos;
    private CatalogoDatos catalogoDatos = null;
    private BalanzaDatos balanzaDatos = null;
    private PolizasDatos polizasDatos = null;
    private AuxiliarCtasDatos auxCtasDatos=null;
    private AuxiliarFoliosDatos auxFoliosDatos=null;
    private RetencionesDatos retencionesDatos = null;
    private PayPointDatos payPointDatos = null;
    private byte[] contenidoArchivo;
    
    
    public void convertFile(File file) throws NullPointerException, IllegalArgumentException, NumberFormatException, ParseException, Exception {
        
        byte[] bytes;

        FileInputStream fis = new FileInputStream(file);
        bytes = new byte[(int) file.length()];
        fis.read(bytes);
        fis.close();
        
        convertFile(bytes);
    }
    
    /**
     * Convierte el
     * <code>CSV</code> en un objetos JAVA
     * accesibles.
     *
     * @param bytesFromFile
     * @throws NullPointerException si <code>txt</code> es nulo.
     * @throws Exception si el <code>txt</code> está mal formado.
     * @throws java.text.ParseException
     */
    public void convertFile(byte[] bytesFromFile) throws NullPointerException, IllegalArgumentException, NumberFormatException, ParseException, Exception {
        this.contenidoArchivo = bytesFromFile;
        
        BufferedReader br = null;
	String line;
        
        //FileInputStream fis = null;
        ByteArrayInputStream bais = null;
        UnicodeBOMInputStream ubis = null;
        InputStreamReader isr = null;
        
        int lineaActual = 1;
        
        try {
 
                //fis = new FileInputStream(csvFile);
                bais = new ByteArrayInputStream(bytesFromFile);
                ubis = new UnicodeBOMInputStream(bais);//(fis);
                isr = new InputStreamReader(ubis);
                br = new BufferedReader(isr);
                ubis.skipBOM(); //Esquivamos lectura de BOM en caso de existir
                
                FacturaDatos facturaDatos = null;
                LineaDatosConcepto lineaDatosConcepto = null;
                boolean existRetencion=false;
		while ((line = br.readLine()) != null) {
                    //Comprobantes Fiscales CFDI
                    //<editor-fold defaultstate="collapsed" desc="Conversión Comprobantes Fiscales CFDI">
                    {
                        //Registro 01 - Datos Receptor/Emisor
                        if (line.startsWith(CmmCvsReceptorUtils.idRegistro)){

                            //Antes de crear una nueva, guardamos en lista general si existe una previa
                            if (facturaDatos!=null){
                                //Para poder almacenar el ultimo concepto en Factura anterior, antes de iniciar una nueva
                                if (lineaDatosConcepto!=null)
                                    facturaDatos.getListaLineaDatosConceptos().add(lineaDatosConcepto);
                                //Asignamos valores
                                getListaFacturaDatos().add(facturaDatos);//listaFacturaDatos.add(facturaDatos);
                                //Reiniciamos variable auxiliar para conceptos
                                lineaDatosConcepto = null;
                            }
                            if(facturaDatos==null){
                                facturaDatos = new FacturaDatos();
                            }
                            facturaDatos.setLineaDatosCliente(CmmCvsReceptorUtils.fillData(line));
                        }
                        
                        //Registro 00 - Emisor Comprobante
                        System.out.println("----- cargando emisor..."+CmmCvsRetencionesComprobanteUtils.idRegistro+","+line.startsWith(CmmCvsRetencionesComprobanteUtils.idRegistro));
                        if(line.startsWith(CmmCvsRetencionesComprobanteUtils.idRegistro)){
                            if(!existRetencion){
                                existRetencion=true;
                            }
                        }
                        if(!existRetencion){
                            if (line.startsWith(CmmCvsEmisorUtils.idRegistro)){
                                if(facturaDatos==null){
                                    facturaDatos = new FacturaDatos();
                                }
                                System.out.println("----- cargando emisor...");
                                facturaDatos.setLineaDatosEmisor(CmmCvsEmisorUtils.fillData(line));
                            }
                        }

                        //Registro 02 - Datos Factura
                        if (line.startsWith(CmmCvsComprobanteUtils.idRegistro) && facturaDatos!=null){
                            facturaDatos.setLineaDatosFactura(CmmCvsComprobanteUtils.fillData(line));
                        }

                        //Registro 03 - Concepto
                        if (line.startsWith(CmmCvsConceptoUtils.idRegistro) && facturaDatos!=null){

                            //Antes de crear una nueva, guardamos en lista general si existe una previa
                            if (lineaDatosConcepto!=null)
                                facturaDatos.getListaLineaDatosConceptos().add(lineaDatosConcepto);

                            //Despues creamos la nueva linea
                            lineaDatosConcepto = CmmCvsConceptoUtils.fillData(line);
                        }

                        //INFORMACIÓN ADICIONAL PARA CONCEPTO
                        {
                            //Registro 04 - Concepto-->Informacion Aduanera
                            if (line.startsWith(CmmCvsInfoAduaneraUtils.idRegistro)){
                                lineaDatosConcepto = CmmCvsInfoAduaneraUtils.fillData(line, lineaDatosConcepto);
                                //TInformacionAduanera informacionAduanera = lineaDatosConcepto.getDatosConcepto().getConcepto().getInformacionAduanera().get(0);
                            }

                            //Registro 05 - Concepto-->Predial
                            if (line.startsWith(CmmCvsInfoPredialUtils.idRegistro)){
                                lineaDatosConcepto = CmmCvsInfoPredialUtils.fillData(line, lineaDatosConcepto);
                                //Comprobante.Conceptos.Concepto.CuentaPredial cuentaPredial = lineaDatosConcepto.getDatosConcepto().getConcepto().getCuentaPredial();
                            }

                            //Registro 06 - Concepto-->Parte
                            if (line.startsWith(CmmCvsParteConceptoUtils.idRegistro)){
                                lineaDatosConcepto = CmmCvsParteConceptoUtils.fillData(line, lineaDatosConcepto);
                                //Comprobante.Conceptos.Concepto.Parte conceptoParte = lineaDatosConcepto.getDatosConcepto().getConcepto().getParte().get(0);
                            }
                        }
                        
                        //Registro 07 - Informacion de Total de Impuestos Federales
                        if (line.startsWith(CmmCvsTotalImpuestosFederalesUtils.idRegistro) && facturaDatos!=null){
                            if (facturaDatos.getLineaDatosFactura()!=null){
                                facturaDatos.setLineaDatosFactura(CmmCvsTotalImpuestosFederalesUtils.fillData(line, facturaDatos.getLineaDatosFactura()));
                            }
                        }

                        //Registro 08 - CFDI relacionados
                        if (line.startsWith(CmmCvsCFDIRelacionadoUtils.idRegistro) && facturaDatos!=null){
                            facturaDatos.setListaDatosCFDIRelacionado(CmmCvsCFDIRelacionadoUtils.fillData(line));
                        }
                        
                        //Registro 90 - Expedido En (emisor)
                        /*if (line.startsWith(CmmCvsExpedidoEnUtils.idRegistro) && facturaDatos!=null){
                            facturaDatos.setExpedidoEn(CmmCvsExpedidoEnUtils.fillData(line));
                        }*/

                        //Registro 97 - Dato adicional Tipo Objeto Concepto para Representacion impresa
                        if (line.startsWith(CmmCvsDatosTipoObjetoConceptoUtils.idRegistro) && facturaDatos!=null){
                            facturaDatos.setLineaDatosTipoObjetoConcepto(CmmCvsDatosTipoObjetoConceptoUtils.fillData(line));
                        }
                        
                        //Registro 98 - Datos acciones personalizadas
                        if (line.startsWith(CmmCvsDatosAccionPersonalizadaUtils.idRegistro) && facturaDatos!=null){
                            facturaDatos.setLineaDatosAccionPersonalizada(CmmCvsDatosAccionPersonalizadaUtils.fillData(line));
                        }

                        //Registro 99 - Datos adicionales
                        if (line.startsWith(CmmCvsDatosAdicionalesUtils.idRegistro) && facturaDatos!=null){
                            facturaDatos.setLineaDatosAdicionales(CmmCvsDatosAdicionalesUtils.fillData(line));
                        }

                        //----Intentamos con complementos----

                            //complemento Nómina - 20
                            com.cmm.cvs2xml.complementos.nomina.utils.CmmCvsConvert.convert(facturaDatos, line);
                            //complemento Impuestos Locales - 30
                            com.cmm.cvs2xml.complementos.implocal.utils.CmmCvsConvert.convert(facturaDatos, line);
                            
                            
                            //complemento de pago
                            com.cmm.cvs2xml.pago10.util.CmmCvsConvert.convert(facturaDatos, line);
                            
                            //complemento de Donatarias - 50
                            com.cmm.cvs2xml.complementos.donat.utils.CmmCvsConvert.convert(facturaDatos, line);

                        //----FIN Intentamos con complementos----
                        
                        //----Intentamos con addendas ------
                            //addenda Sanofi v1.0 - 00200
                            com.cmm.cvs2xml.addendas.sanofi.utils.CmmCvsConvert.convert(facturaDatos, line);
                            
                            //addenda VW PMT v1.0 - 00210
                            com.cmm.cvs2xml.addendas.vwpmt.utils.CmmCvsConvert.convert(facturaDatos, line);
                            
                            //addenda Chrysler PUA v1.0 - 00221
                            com.cmm.cvs2xml.addendas.chryslerpua.utils.CmmCvsConvert.convert(facturaDatos, line);
                            
                            //addenda GM v1.3 - 00235
                            com.cmm.cvs2xml.addendas.gm.v13.utils.CmmCvsConvert.convert(facturaDatos, line);
                            
                            //addenda Ford Fom v1.0 - 00240
                            com.cmm.cvs2xml.addendas.fordfom.utils.CmmCvsConvert.convert(facturaDatos, line);
                            
                            //addenda Chrysler PPY v1.0 - 00245
                            com.cmm.cvs2xml.addendas.chryslerppy.utils.CmmCvsConvert.convert(facturaDatos, line);
                            
                        //----FIN Intentamos con addendas ------
                    }
                    // </editor-fold>
                    
                    //Formatos Contabilidad Electrónica
                    //<editor-fold defaultstate="collapsed" desc="Conversión Formatos Contabilidad Electrónica">
                    {
                        //Formato Catálogo de Cuentas - 100
                        catalogoDatos = com.cmm.cvs2xml.econtabilidad.v13.catalogo.utils.CmmCvsConvert.convert(catalogoDatos, line);
                        
                        //Formato Balanza de Comprobación - 110
                        balanzaDatos = com.cmm.cvs2xml.econtabilidad.v13.balanza.utils.CmmCvsConvert.convert(balanzaDatos, line);
                        
                        //Formato Pólizas - 120
                        polizasDatos = com.cmm.cvs2xml.econtabilidad.v13.polizas.utils.CmmCvsConvert.convert(polizasDatos, line);
                        
                        //Formato AuciliarCtas - 130
                        auxCtasDatos=com.cmm.cvs2xml.econtabilidad.v13.AuxiliarCtas.utils.CmmCvsConvert.convert(auxCtasDatos, line);
                        
                        //Formato AuciliarFolios - 140
                        auxFoliosDatos=com.cmm.cvs2xml.econtabilidad.v13.AuxiliarFolios.utils.CmmCvsConvert.convert(auxFoliosDatos, line);
                    }
                    // </editor-fold>
                    
                    //Comprobante Retenciones v1.0
                    //<editor-fold defaultstate="collapsed" desc="Conversión Retenciones v1.0">
                    {
                        //Retenciones
                        retencionesDatos = com.cmm.cvs2xml.retenciones.utils.CmmCvsConvert.convert(retencionesDatos, line);
                        
                        //----Intentamos con complementos----

                            //complemento ArrendamientoEnFideicomiso - 00505
                            com.cmm.cvs2xml.retenciones.complementos.arrendamientoenfideicomiso.utils.CmmCvsConvert.convert(retencionesDatos, line);
                            
                            //complemento Dividendos - 00506
                            com.cmm.cvs2xml.retenciones.complementos.dividendos.utils.CmmCvsConvert.convert(retencionesDatos, line);
                            
                            //complemento PagosAExtranjeros - 00513
                            com.cmm.cvs2xml.retenciones.complementos.pagosaextranjeros.utils.CmmCvsConvert.convert(retencionesDatos, line);

                        //----FIN Intentamos con complementos----
                    }
                    
                    
                    // </editor-fold>
                    
                    
                    //Aumentamos uno al contador de linea
                    lineaActual++;
		}
                
                //Para poder almacenar el ultimo concepto en la ultima Factura en la iteracion fuera del While
                if (lineaDatosConcepto!=null && facturaDatos!=null)
                    facturaDatos.getListaLineaDatosConceptos().add(lineaDatosConcepto);
                
                //Para poder almacenar la ultima Factura en la iteracion fuera del While
                if (facturaDatos!=null){
                    //Asignamos valores
                    getListaFacturaDatos().add(facturaDatos);//listaFacturaDatos.add(facturaDatos);
                }
        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
	} catch (IOException e) {
            e.printStackTrace();
	} catch (Exception e){
            e.printStackTrace();
            throw new Exception("Error (linea " + lineaActual + "): " + e.getMessage());
        } finally {
            if (br != null) {
                    try {
                            br.close();
                    } catch (IOException e) {
                            e.printStackTrace();
                    }
            }
            try{
                /*if (fis!=null)
                    fis.close();*/
                if (bais!=null)
                    bais.close();
                if (ubis!=null)
                    ubis.close();
                if (isr!=null)
                    isr.close();
            } catch (IOException e) {
                    e.printStackTrace();
            }
	}
    }

    public byte[] getContenidoArchivo() {
        return contenidoArchivo;
    }

    public void setContenidoArchivo(byte[] contenidoArchivo) {
        this.contenidoArchivo = contenidoArchivo;
    }

    public List<FacturaDatos> getListaFacturaDatos() {
        if (listaFacturaDatos==null)
            listaFacturaDatos = new ArrayList<FacturaDatos>();
        return listaFacturaDatos;
    }   

    public CatalogoDatos getCatalogoDatos() {
        return catalogoDatos;
    }

    public void setCatalogoDatos(CatalogoDatos catalogoDatos) {
        this.catalogoDatos = catalogoDatos;
    }

    public BalanzaDatos getBalanzaDatos() {
        return balanzaDatos;
    }

    public void setBalanzaDatos(BalanzaDatos balanzaDatos) {
        this.balanzaDatos = balanzaDatos;
    }

    public PolizasDatos getPolizasDatos() {
        return polizasDatos;
    }

    public void setPolizasDatos(PolizasDatos polizasDatos) {
        this.polizasDatos = polizasDatos;
    }

    public RetencionesDatos getRetencionesDatos() {
        return retencionesDatos;
    }

    public void setRetencionesDatos(RetencionesDatos retencionesDatos) {
        this.retencionesDatos = retencionesDatos;
    }
    
    public PayPointDatos getPayPointDatos() {
        return payPointDatos;
    }

    public void setPayPointDatos(PayPointDatos payPointDatos) {
        this.payPointDatos = payPointDatos;
    }

    /**
     * @return the auxCtasDatos
     */
    public AuxiliarCtasDatos getAuxCtasDatos() {
        return auxCtasDatos;
    }

    /**
     * @param auxCtasDatos the auxCtasDatos to set
     */
    public void setAuxCtasDatos(AuxiliarCtasDatos auxCtasDatos) {
        this.auxCtasDatos = auxCtasDatos;
    }

    /**
     * @return the auxFoliosDatos
     */
    public AuxiliarFoliosDatos getAuxFoliosDatos() {
        return auxFoliosDatos;
    }

    /**
     * @param auxFoliosDatos the auxFoliosDatos to set
     */
    public void setAuxFoliosDatos(AuxiliarFoliosDatos auxFoliosDatos) {
        this.auxFoliosDatos = auxFoliosDatos;
    }
    
}
