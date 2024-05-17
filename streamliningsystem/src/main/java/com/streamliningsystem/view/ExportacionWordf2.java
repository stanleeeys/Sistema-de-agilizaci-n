package com.streamliningsystem.view;


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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

public class ExportacionWordf2 {

//    public void exportarTabla(List<Cotizacion> listaCotizaciones, String nombreArchivo) {
//        
//        try {
//            XWPFDocument documento = new XWPFDocument();
//
//            // Cambiar la orientación de la página a horizontal
//            CTSectPr sectPr = documento.getDocument().getBody().addNewSectPr();
//            CTPageSz pageSz = sectPr.addNewPgSz();
//            pageSz.setOrient(STPageOrientation.LANDSCAPE);
//            // Tamaño A4
//            pageSz.setW(16840); // 11906 para A4 Portrait (vertical), 16840 para A4 Landscape (horizontal)
//            pageSz.setH(11906); // 16840 para A4 Portrait (vertical), 11906 para A4 Landscape (horizontal)
//
//            for (int copy = 0; copy < 5; copy++) {
//                // Título
//                XWPFParagraph titulo = documento.createParagraph();
//                titulo.setAlignment(ParagraphAlignment.CENTER);
//                titulo.setSpacingAfter(200);
//                XWPFRun tituloRun = titulo.createRun();
//                tituloRun.setBold(true);
//                tituloRun.setFontSize(12);
//                tituloRun.setFontFamily("Times New Roman");
//                tituloRun.setText("COTIZACION");
//
//                // Encabezado
//                XWPFParagraph encabezado = documento.createParagraph();
//                encabezado.setAlignment(ParagraphAlignment.DISTRIBUTE);
//                encabezado.setSpacingAfter(200);
//                XWPFRun encabezadoRun = encabezado.createRun();
//                encabezadoRun.setFontFamily("Times New Roman");
//                encabezadoRun.setFontSize(10);
//                encabezadoRun.setText("LUGAR Y FECHA: LUGAR, 29 DE MAYO DE 2023.");
//                encabezadoRun.addBreak();
//                encabezadoRun.setText(" ");
//                encabezadoRun.addBreak();
//                encabezadoRun.setText("SEÑORES MIEMBROS DEL CDE.");
//                encabezadoRun.addBreak();
//                encabezadoRun.setText("ESCUELA DE EDUCACIÓN PARVULARIA LUGAR");
//                encabezadoRun.addBreak();
//                encabezadoRun.setText("Presente.");
//                encabezadoRun.addBreak();
//                encabezadoRun.setText(" ");
//                encabezadoRun.addBreak();
//                encabezadoRun.setText("Atendiendo la solicitud de cotización de fecha 27 DE Mayo de 2022, suscrita por el CDE que administra La Escuela de Educación Parvularia La LUGAR, ubicado en el Municipio de La LUGAR, atentamente remito la oferta en original apegada a las condiciones y especificaciones de compras detalladas a continuación:\n\n");
//                encabezadoRun.addBreak();
//                encabezadoRun.setText(" ");
//                encabezadoRun.addBreak();
//
//                // Tabla
//                XWPFTable tabla = documento.createTable();
//                tabla.setWidth("100%");
//                tabla.setTableAlignment(TableRowAlign.CENTER);
//
//                CTTblPr tblPr = tabla.getCTTbl().addNewTblPr();
//                tblPr.addNewTblBorders().addNewBottom().setVal(STBorder.SINGLE);
//                tblPr.getTblBorders().addNewLeft().setVal(STBorder.SINGLE);
//                tblPr.getTblBorders().addNewRight().setVal(STBorder.SINGLE);
//                tblPr.getTblBorders().addNewTop().setVal(STBorder.SINGLE);
//                tblPr.getTblBorders().addNewInsideH().setVal(STBorder.SINGLE);
//                tblPr.getTblBorders().addNewInsideV().setVal(STBorder.SINGLE);
//
//                String[] encabezadoTabla = {"N", "Cantidad", "Unidad de Medida", "Descripción", "Precio Unitario", "Precio Total"};
//                XWPFTableRow filaEncabezado = tabla.getRow(0);
//                for (int i = 0; i < encabezadoTabla.length; i++) {
//                    XWPFTableCell celda = filaEncabezado.getCell(i);
//                    if (celda == null) {
//                        celda = filaEncabezado.createCell();
//                    }
//                    XWPFParagraph p = celda.getParagraphs().get(0);
//                    p.setAlignment(ParagraphAlignment.DISTRIBUTE);
//                    XWPFRun r = p.createRun();
//                    r.setFontSize(10);
//                    r.setFontFamily("Times New Roman");
//                    r.setBold(true);
//                    r.setText(encabezadoTabla[i]);
//                }
//
//                for (Cotizacion cotizacion : listaCotizaciones) {
//                    XWPFTableRow fila = tabla.createRow();
//                    for (int i = 0; i < 6; i++) {
//                        XWPFTableCell celda = fila.getCell(i);
//                        if (celda == null) {
//                            celda = fila.createCell();
//                        }
//                        XWPFParagraph p = celda.getParagraphs().get(0);
//                        p.setAlignment(ParagraphAlignment.DISTRIBUTE);
//                        XWPFRun r = p.createRun();
//                        r.setFontSize(10);
//                        r.setFontFamily("Times New Roman");
//                        switch (i) {
//                            case 0:
//                                r.setText(String.valueOf(cotizacion.getId_cotizacion()));
//                                break;
//                            case 1:
//                                r.setText(String.valueOf(cotizacion.getCantidad()));
//                                break;
//                            case 2:
//                                r.setText(cotizacion.getUnidadMedida());
//                                break;
//                            case 3:
//                                r.setText(cotizacion.getDescripcion());
//                                break;
//                            case 4:
//                                r.setText(String.valueOf(cotizacion.getPrecioUnitario()));
//                                break;
//                            case 5:
//                                r.setText(String.valueOf(cotizacion.getPrecioTotal()));
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                }
//
//                if (copy < 4) { // Add a page break after each copy, except the last one
//                    documento.createParagraph().setPageBreak(true);
//                }
//            }
//
//            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.setDialogTitle("Guardar como");
//            fileChooser.setFileFilter(new FileNameExtensionFilter("Documento Word (.docx)", "docx"));
//            int seleccion = fileChooser.showSaveDialog(null);
//            if (seleccion == JFileChooser.APPROVE_OPTION) {
//                String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
//                if (!rutaArchivo.endsWith(".docx")) {
//                    rutaArchivo += ".docx";
//                }
//                try (FileOutputStream salida = new FileOutputStream(rutaArchivo)) {
//                    documento.write(salida);
//                    System.out.println("Documento guardado en: " + rutaArchivo);
//                }
//                documento.close();
//            } else {
//                System.out.println("Cancelado por el usuario");
//            }
//        } catch (Exception ex) {
//            System.err.println("Error al exportar tabla: " + ex.getMessage());
//        }
//    }
}
