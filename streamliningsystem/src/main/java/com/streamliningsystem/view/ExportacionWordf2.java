package com.streamliningsystem.view;

import com.streamliningsystem.model.Cotizacion;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;

public class ExportacionWordf2 {

    public void exportarTabla(List<Cotizacion> listaCotizaciones, String nombreArchivo) {
        try {
            XWPFDocument documento = new XWPFDocument();

           
            XWPFParagraph titulo = documento.createParagraph();
            titulo.setAlignment(ParagraphAlignment.CENTER);
            titulo.setSpacingAfter(200); 
            XWPFRun tituloRun = titulo.createRun();
            tituloRun.setBold(true); 
            tituloRun.setFontSize(12); 
            tituloRun.setFontFamily("Times New Roman"); 
            tituloRun.setText("ACTA DE RECEPCION DE BIENES     N°____");

            XWPFParagraph encabezado = documento.createParagraph();
            encabezado.setAlignment(ParagraphAlignment.LEFT); 
            encabezado.setSpacingAfter(200); 
            XWPFRun encabezadoRun = encabezado.createRun();
            encabezadoRun.setFontFamily("Times New Roman");
            encabezadoRun.setFontSize(10); 
            encabezadoRun.setText("EL ORGANISMO DE ADMINISTRACION ESCOLAR: CONSEJO DIRECTIVO ESCOLAR QUE ADMINISTRA LA ESCUELA DE EDUCACION PARVULARIA LA REINA, MUNICIPIO: LA REINA, CODIGO:_10882______________ DEPARTAMENTO DE CHALATENANGO.");
            encabezadoRun.addBreak();
            encabezadoRun.setText(" ");
            encabezadoRun.addBreak();
            encabezadoRun.setText("SEÑORES MIEMBROS DEL CDE.");
            encabezadoRun.addBreak();
            encabezadoRun.setText("ESCUELA DE EDUCACIÓN PARVULARIA LUGAR");
            encabezadoRun.addBreak(); 
            encabezadoRun.setText("Presente.");
            encabezadoRun.addBreak();
            encabezadoRun.setText(" ");
            encabezadoRun.addBreak();
            //encabezado.setAlignment(ParagraphAlignment.DISTRIBUTE);
            encabezadoRun.setText("QUE EN FECHA 01/06/2023, EL PRESIDENTE DEL CENTRO ESCOLAR  Y EL SUSCRITO HACE CONSTAR QUE HA RECIBIDO DE ACUERDO A  LO CONVENIDO CON EL/LA  SEÑOR/A: MARGARITA URBINA SIBRIAN, LOS BIENES QUE  A CONTINUACION  SE DETALLAN");
            encabezadoRun.addBreak();
            encabezadoRun.setText(" ");
            encabezadoRun.addBreak();

            XWPFTable tabla = documento.createTable();
            tabla.setWidth("100%");
            tabla.setTableAlignment(TableRowAlign.CENTER);

            
            CTTblPr tblPr = tabla.getCTTbl().addNewTblPr();
            tblPr.addNewTblBorders().addNewBottom().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewLeft().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewRight().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewTop().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewInsideH().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewInsideV().setVal(STBorder.SINGLE);

            
            String[] encabezadoTabla = {"N", "Cantidad", "Unidad de Medida", "Descripción", "Precio Unitario", "Precio Total"};
            XWPFTableRow filaEncabezado = tabla.getRow(0); 
            for (int i = 0; i < encabezadoTabla.length; i++) {
                XWPFTableCell celda = filaEncabezado.getCell(i);
                if (celda == null) {
                    celda = filaEncabezado.createCell();
                }
                XWPFParagraph p = celda.getParagraphs().get(0);
                p.setAlignment(ParagraphAlignment.DISTRIBUTE); 
                XWPFRun r = p.createRun();
                r.setFontSize(10); 
                r.setFontFamily("Times New Roman"); 
                r.setBold(true); 
                r.setText(encabezadoTabla[i]);
            }

            
            for (Cotizacion cotizacion : listaCotizaciones) {
                XWPFTableRow fila = tabla.createRow();
                for (int i = 0; i < 6; i++) {
                    
                    XWPFTableCell celda = fila.getCell(i);
                    
                    if (celda == null) {
                        celda = fila.createCell();
                    }
                   
                    XWPFParagraph p = celda.getParagraphs().get(0);
                    p.setAlignment(ParagraphAlignment.DISTRIBUTE); 
                    XWPFRun r = p.createRun();
                    r.setFontSize(10); 
                    r.setFontFamily("Times New Roman");
                    switch (i) {
                        case 0:
                            r.setText(String.valueOf(cotizacion.getId_cotizacion()));
                            break;
                        case 1:
                            r.setText(String.valueOf(cotizacion.getCantidad()));
                            break;
                        case 2:
                            r.setText(cotizacion.getUnidadMedida());
                            break;
                        case 3:
                            r.setText(cotizacion.getDescripcion());
                            break;
                        case 4:
                            r.setText(String.valueOf(cotizacion.getPrecioUnitario()));
                            break;
                        case 5:
                            r.setText(String.valueOf(cotizacion.getPrecioTotal()));
                            break;
                        default:
                            break;
                    }
                }
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar como");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Documento Word (.docx)", "docx"));
            int seleccion = fileChooser.showSaveDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath() + ".docx";
                FileOutputStream salida = new FileOutputStream(rutaArchivo);
                documento.write(salida);
                salida.close();
                documento.close();
                System.out.println("Documento guardado en: " + rutaArchivo);
            } else {
                System.out.println("Cancelado por el usuario");
            }
            
        } catch (Exception ex) {
            System.err.println("Error al exportar tabla: " + ex.getMessage());
        }

    }
}
