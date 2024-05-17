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
import javax.swing.table.DefaultTableModel;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;

public class ExportacionWord {

    public void exportarTabla(DefaultTableModel model, String nombreArchivo) {
        try {
            XWPFDocument documento = new XWPFDocument();

            // Crear título
            XWPFParagraph titulo = documento.createParagraph();
            titulo.setAlignment(ParagraphAlignment.CENTER);
            titulo.setSpacingAfter(200);
            XWPFRun tituloRun = titulo.createRun();
            tituloRun.setBold(true);
            tituloRun.setFontSize(12);
            tituloRun.setFontFamily("Times New Roman");
            tituloRun.setText("COTIZACION");

            // Crear encabezado
            XWPFParagraph encabezado = documento.createParagraph();
            encabezado.setAlignment(ParagraphAlignment.LEFT);
            encabezado.setSpacingAfter(200);
            XWPFRun encabezadoRun = encabezado.createRun();
            encabezadoRun.setFontFamily("Times New Roman");
            encabezadoRun.setFontSize(10);
            encabezadoRun.setText("LUGAR Y FECHA: LUGAR, 29 DE MAYO DE 2023.");
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
            encabezadoRun.setText("Atendiendo la solicitud de cotización de fecha 27 DE Mayo de 2022, suscrita por el CDE que administra La Escuela de Educación Parvularia La LUGAR, ubicado en el Municipio de La LUGAR, atentamente remito la oferta en original apegada a las condiciones y especificaciones de compras detalladas a continuación:\n\n");
            encabezadoRun.addBreak();
            encabezadoRun.setText(" ");
            encabezadoRun.addBreak();

            // Crear tabla
            XWPFTable tabla = documento.createTable();
            tabla.setWidth("100%");
            tabla.setTableAlignment(TableRowAlign.CENTER);

            // Configurar bordes de la tabla
            CTTblPr tblPr = tabla.getCTTbl().addNewTblPr();
            tblPr.addNewTblBorders().addNewBottom().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewLeft().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewRight().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewTop().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewInsideH().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewInsideV().setVal(STBorder.SINGLE);

            // Agregar encabezado de la tabla
            String[] encabezadoTabla = {"N", "Area de inversion", "Cantidad", "Unidad de medida", "Descripcion/especificacion técnica", "Precio unitario", "Precio total", "Fecha de compra"};
            XWPFTableRow filaEncabezado = tabla.getRow(0);
            for (int i = 0; i < encabezadoTabla.length; i++) {
                XWPFTableCell celda = filaEncabezado.getCell(i);
                if (celda == null) {
                    celda = filaEncabezado.createCell();
                }
                XWPFParagraph p = celda.getParagraphs().get(0);
                p.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun r = p.createRun();
                r.setFontSize(10);
                r.setFontFamily("Times New Roman");
                r.setBold(true);
                r.setText(encabezadoTabla[i]);
            }

            
            // Agregar datos de la tabla
            for (int row = 0; row < model.getRowCount(); row++) {
                XWPFTableRow fila = tabla.createRow();
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Object value = model.getValueAt(row, col);
                    String cellValue = (value != null) ? value.toString() : ""; // Verificar si el valor es null
                    XWPFTableCell celda = fila.getCell(col);
                    if (celda == null) {
                        celda = fila.createCell();
                    }
                    XWPFParagraph p = celda.getParagraphs().get(0);
                    p.setAlignment(ParagraphAlignment.LEFT);
                    XWPFRun r = p.createRun();
                    r.setFontSize(10);
                    r.setFontFamily("Times New Roman");
                    r.setText(cellValue);
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
