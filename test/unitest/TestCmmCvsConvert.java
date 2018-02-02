/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitest;

import com.cmm.cvs2xml.CmmCvsConvert;
import com.cmm.cvs2xml.bean.FacturaDatos;
import com.cmm.cvs2xml.bo.AuxiliarCtasDatosBO;
import com.cmm.cvs2xml.bo.AuxiliarFoliosDatosBO;
import com.cmm.cvs2xml.bo.BalanzaDatosBO;
import com.cmm.cvs2xml.bo.CatalogoDatosBO;
import com.cmm.cvs2xml.bo.FacturaDatosBO;
import com.cmm.cvs2xml.bo.PolizasDatosBO;
import com.cmm.cvs2xml.bo.retenciones.RetencionesDatosBO;
import com.cmm.cvs2xml.util.DateManage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import mx.bigdata.sat.cfdiv.CFDv33;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;
import mx.bigdata.sat.econtabilidad.v13.BCEv13;
import mx.bigdata.sat.econtabilidad.v13.CTLv13;
import mx.bigdata.sat.econtabilidad.v13.Ctasv13;
import mx.bigdata.sat.econtabilidad.v13.PLZv13;
import mx.bigdata.sat.econtabilidad.v13.RespAuxv13;
import mx.bigdata.sat.econtabilidad.v13.schema.balanza.Balanza;
import mx.bigdata.sat.econtabilidad.v13.schema.ctas.AuxiliarCtas;
import mx.bigdata.sat.econtabilidad.v13.schema.cuentas.Catalogo;
import mx.bigdata.sat.econtabilidad.v13.schema.folios.RepAuxFol;
import mx.bigdata.sat.econtabilidad.v13.schema.polizas.Polizas;
import mx.bigdata.sat.retencion.CFDIRetencionv10;
import mx.bigdata.sat.retencion.v1.schema.Retenciones;
import mx.bigdata.sat.security.KeyLoader;
import mx.bigdata.sat.security.factory.v.KeyLoaderFactory;
import mx.bigdata.sat.security.v.PrivateKeyLoader;
/**
 *
 * @author ISCesar
 */
public class TestCmmCvsConvert {
    
    protected String rutaResultados = "C:\\Users\\leonardo\\Documents\\Leonardo\\Proyectos 2017\\CFDI32 a CFDI33\\Monitor de texto\\";
    //CFDI
    protected String rutaArchivoEntrada = "C:\\Users\\user\\Desktop\\Instalador V3 plus\\repositorio\\exec\\retencion_2.csv";
    //CFDI Nomina
    //protected String rutaArchivoEntrada = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\ARCHIVO_PRUEBA_NOMINA.CSV";
    //CFDI Addenda Sanofi
    //protected String rutaArchivoEntrada = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\addendas\\sanofi\\Ejemplo\\00200_Factura_Addenda_Sanofi.CSV";
    //CFDI Addenda VW PMT
    //protected String rutaArchivoEntrada = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\addendas\\VW\\Ejemplo\\00210_Factura_Addenda_VW PMT.CSV";
    //CFDI Addenda Chrysler PUA
    //protected String rutaArchivoEntrada = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\addendas\\Chrysler\\Ejemplo\\00221_Factura_Addenda_Chrysler PUA.CSV";
    //CatalogoCta
    //protected String rutaArchivoEntrada = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\Ejemplos\\Contabilidad Electrónica\\100_Catálogo_de_Cuentas.CSV";
    //Balanza Comprobacion
    //protected String rutaArchivoEntrada = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\Ejemplos\\Contabilidad Electrónica\\110_Balanza_de_Comprobacion.CSV";
    //Polizas
    //protected String rutaArchivoEntrada = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\Ejemplos\\Contabilidad Electrónica\\120_Pólizas_del_Periodo.CSV";
    //Retenciones
    //protected String rutaArchivoEntrada = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\Retenciones\\Ejemplos\\00500_Retenciones_ReceptorExtranjero.CSV";
    //Retenciones- Pagos A Extranjeros
    //protected String rutaArchivoEntrada = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\Retenciones\\Ejemplos\\pagosAExtranjeros\\00500_Retenciones_PagosAExtranjeros.CSV";
    //Retenciones- Arrendamiento en Fideicomiso
    //protected String rutaArchivoEntrada = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\Retenciones\\Ejemplos\\arrendamientoEnFideicomiso\\00505_Retenciones_ArrendamientoEnFideicomiso.CSV";
    //Retenciones- Dividendos
    //protected String rutaArchivoEntrada = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\Retenciones\\Ejemplos\\dividendos\\00506_Retenciones_Dividendos.CSV";
    
    /**NO SE PERMITE EL TIPO DE FACTOR IVA NULO O V
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalArgumentException, NumberFormatException, ParseException, Exception{
        TestCmmCvsConvert test = new TestCmmCvsConvert();
        test.run();
    }
    
    protected void run() throws IllegalArgumentException, NumberFormatException, ParseException, Exception{
        //String csvFile = "C:\\Users\\ISCesar\\Dropbox\\TSP\\Equipo Fens\\Monitor CSV\\ARCHIVO_PRUEBA_NOMINA.CSV";
        File file = new File(rutaArchivoEntrada);
        
        CmmCvsConvert cmmCvsConvert = new CmmCvsConvert();
        cmmCvsConvert.convertFile(file);
        
        if (cmmCvsConvert.getListaFacturaDatos().size()>0){
            for (FacturaDatos facturaDatos : cmmCvsConvert.getListaFacturaDatos()){
                FacturaDatosBO facturaDatosBO = new FacturaDatosBO(facturaDatos);
                
                facturaDatosBO.setCompilarAddendas(true);
                
                Comprobante comp = facturaDatosBO.compilarComprobante();
                
                //Datos de prueba
                comp.setFecha(DateManage.dateToXMLGregorianCalendar(new Date()));
                /*comp.setCertificado("XXXXXXXXX");
                comp.setNoCertificado("12311111110000001234");
                */comp.setSello("djshdjkshXS=");
                comp.getEmisor().setNombre("cosita");
                comp.getEmisor().setRfc("XAXX010101000");
                comp.getEmisor().setRegimenFiscal("601");
                
                //Escribimos Archivo resultado (XML)
                String rutaArchivoResultado = rutaResultados + new Date().getTime() +".xml";
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                CFDv33 cFDv32 = new CFDv33(comp, 
                        "mx.bigdata.sat.common.nomina12.schema", 
                        "mx.bigdata.sat.common.implocal.schema",
                        "mx.bigdata.sat.addenda.sanofi.schema",
                        "mx.bigdata.sat.addenda.vwpmt.schema",
                        "mx.bigdata.sat.addenda.chryslerpua.schema",
                        "mx.bigdata.sat.common.donat.schema");
                cFDv32.addNamespace("http://www.sat.gob.mx/Pagos", "pago10");
                cFDv32.addNamespace("http://www.sat.gob.mx/nomina12", "nomina");
                cFDv32.addNamespace("http://www.sat.gob.mx/implocal", "implocal");
                cFDv32.addNamespace("https://mexico.sanofi.com/schemas", "sanofi");
                cFDv32.addNamespace("http://www.vwnovedades.com/volkswagen/kanseilab/shcp/2009/Addenda/PMT", "PMT");
                cFDv32.addNamespace("http://www.sat.gob.mx/donat", "donat");
                
                
                //Recuperamos objetos de Certificado y Llave privada del emisor
                java.security.cert.X509Certificate certX509 = null;
                java.security.PrivateKey pkey = null;
                try{
                     certX509 = KeyLoaderFactory.loadX509Certificate(new FileInputStream("C:\\SystemaDeArchivos\\AAA010101AAA\\CSD01_AAA010101AAA.cer"));
                     PrivateKeyLoader privateKey=new PrivateKeyLoader(new FileInputStream("C:\\SystemaDeArchivos\\AAA010101AAA\\CSD01_AAA010101AAA.key"), "12345678a");
                     pkey=privateKey.getKey();
                     //pkey = PrivateKeyLoader.loadPKCS8PrivateKey(new FileInputStream("C:\\Users\\user\\Desktop\\Instalador V3 plus\\Monitor_Fens_Dist\\CSD_UNICA_MLI0809307S7_20131121_091247.key"), "bolo0507");
                }catch(Exception ex){
                    throw new Exception("El emisor de RFC '' configurado"
                            + " no tiene Certificado y Llave privada validos." + ex.toString());
                }
                
                if(certX509!=null&&pkey!=null){
                    try{
                        System.out.println("C");
                        cFDv32.sellar(pkey, certX509);
                        System.out.println("D");
                    }catch(Exception ex){
                        throw new Exception("Error en sellado de XML traducido: " + ex.getMessage());
                    }
                }
                
                
                cFDv32.guardar(baos);
                
                byte[] byteResultado = baos.toByteArray();
                
                System.out.println(new String(byteResultado));
                
                File newTempFile = new File(rutaArchivoResultado);
                FileOutputStream fos = new FileOutputStream(newTempFile);
                fos.write(byteResultado);
                fos.flush();
                fos.close();
                
                
                
                try{
                    cFDv32.validar();
                    cFDv32.verificar();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }else{
            //System.out.println("Archivo sin facturas validas.");
            
            //Formato Contabilidad Electrónica Catalogo de Cuentas
            if (cmmCvsConvert.getCatalogoDatos()!=null){
                CatalogoDatosBO catalogoDatosBO = new CatalogoDatosBO(cmmCvsConvert.getCatalogoDatos());
                Catalogo catalogo = catalogoDatosBO.compilarFormato();
                
                //Escribimos Archivo resultado (XML)
                String rutaArchivoResultado = rutaResultados + "catalogoCtas_" + new Date().getTime() +".xml";
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                CTLv13 cTLv1 = new CTLv13(catalogo);
                cTLv1.guardar(baos);
                
                byte[] byteResultado = baos.toByteArray();
                
                File newTempFile = new File(rutaArchivoResultado);
                FileOutputStream fos = new FileOutputStream(newTempFile);
                fos.write(byteResultado);
                fos.flush();
                fos.close();
                
                
                cTLv1.validar();
            }
            
            //Formato Contabilidad Electrónica Balanza de Comprobacion
            if (cmmCvsConvert.getBalanzaDatos()!=null){
                BalanzaDatosBO balanzaDatosBO = new BalanzaDatosBO(cmmCvsConvert.getBalanzaDatos());
                Balanza balanza = balanzaDatosBO.compilarFormato();
                
                //Escribimos Archivo resultado (XML)
                String rutaArchivoResultado = rutaResultados + "balanzaComprobacion_" + new Date().getTime() +".xml";
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BCEv13 bCEv1 = new BCEv13(balanza);
                bCEv1.guardar(baos);
                
                byte[] byteResultado = baos.toByteArray();
                
                File newTempFile = new File(rutaArchivoResultado);
                FileOutputStream fos = new FileOutputStream(newTempFile);
                fos.write(byteResultado);
                fos.flush();
                fos.close();
                
                
                bCEv1.validar();
            }
            
            //Formato Contabilidad Electrónica Polizas
            if (cmmCvsConvert.getPolizasDatos()!=null){
                PolizasDatosBO polizasDatosBO = new PolizasDatosBO(cmmCvsConvert.getPolizasDatos());
                Polizas polizas = polizasDatosBO.compilarFormato();
                
                //Escribimos Archivo resultado (XML)
                String rutaArchivoResultado = rutaResultados + "polizas_" + new Date().getTime() +".xml";
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PLZv13 pLZv1 = new PLZv13(polizas);
                pLZv1.guardar(baos);
                
                byte[] byteResultado = baos.toByteArray();
                
                File newTempFile = new File(rutaArchivoResultado);
                FileOutputStream fos = new FileOutputStream(newTempFile);
                fos.write(byteResultado);
                fos.flush();
                fos.close();
                
                
                pLZv1.validar();
            }
            
            //Comprobante Retenciones
            if (cmmCvsConvert.getRetencionesDatos()!=null){
                RetencionesDatosBO retencionesDatosBO = new RetencionesDatosBO(cmmCvsConvert.getRetencionesDatos());
                Retenciones retenciones = retencionesDatosBO.compilarComprobante();
                
                //Datos de prueba
                retenciones.setFechaExp(new Date());
                retenciones.setCert("XXXXXXXXX");
                retenciones.setNumCert("12311111110000001234");
                retenciones.setSello("djshdjkshXS=");
                retenciones.setFolioInt("123");
                
                //Escribimos Archivo resultado (XML)
                String rutaArchivoResultado = rutaResultados + new Date().getTime() +".xml";
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                CFDIRetencionv10 cFDIRetencionv10 = new CFDIRetencionv10(retenciones, 
                        "mx.bigdata.sat.retencion.common.arrendamientoenfideicomiso.schema",
                        "mx.bigdata.sat.retencion.common.dividendos.schema",
                        "mx.bigdata.sat.retencion.common.pagosaextranjeros.schema");
                cFDIRetencionv10.addNamespace("http://www.sat.gob.mx/esquemas/retencionpago/1/arrendamientoenfideicomiso", "arrendamientoenfideicomiso");
                cFDIRetencionv10.addNamespace("http://www.sat.gob.mx/esquemas/retencionpago/1/dividendos", "dividendos");
                cFDIRetencionv10.addNamespace("http://www.sat.gob.mx/esquemas/retencionpago/1/pagosaextranjeros", "pagosaextranjeros");
                cFDIRetencionv10.guardar(baos);
                
                byte[] byteResultado = baos.toByteArray();
                
                File newTempFile = new File(rutaArchivoResultado);
                FileOutputStream fos = new FileOutputStream(newTempFile);
                fos.write(byteResultado);
                fos.flush();
                fos.close();
                
                
                cFDIRetencionv10.validar();
                
            }
            
            //Formato Contabilidad Electrónica AuxiliarCtas
            if (cmmCvsConvert.getAuxCtasDatos()!=null){
                AuxiliarCtasDatosBO auxCtasBO = new AuxiliarCtasDatosBO(cmmCvsConvert.getAuxCtasDatos());
                AuxiliarCtas auxCtas = auxCtasBO.compilarFormato();
                
                //Escribimos Archivo resultado (XML)
                String rutaArchivoResultado = rutaResultados + "auxiliar_ctas_" + new Date().getTime() +".xml";
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Ctasv13 ctasv13 = new Ctasv13(auxCtas);
                ctasv13.guardar(baos);
                
                byte[] byteResultado = baos.toByteArray();
                
                File newTempFile = new File(rutaArchivoResultado);
                FileOutputStream fos = new FileOutputStream(newTempFile);
                fos.write(byteResultado);
                fos.flush();
                fos.close();
                
                
                ctasv13.validar();
            }
            
            //Formato Contabilidad Electrónica AuxiliarFolios
            if (cmmCvsConvert.getAuxFoliosDatos()!=null){
                AuxiliarFoliosDatosBO auxFoliosBO = new AuxiliarFoliosDatosBO(cmmCvsConvert.getAuxFoliosDatos());
                RepAuxFol auxFolios = auxFoliosBO.compilarFormato();
                
                //Escribimos Archivo resultado (XML)
                String rutaArchivoResultado = rutaResultados + "auxiliar_folios_" + new Date().getTime() +".xml";
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                RespAuxv13 auxFolioV13 = new RespAuxv13(auxFolios);
                auxFolioV13.guardar(baos);
                
                byte[] byteResultado = baos.toByteArray();
                
                File newTempFile = new File(rutaArchivoResultado);
                FileOutputStream fos = new FileOutputStream(newTempFile);
                fos.write(byteResultado);
                fos.flush();
                fos.close();
                
                
                auxFolioV13.validar();
            }
            
        }
    }
    
}
