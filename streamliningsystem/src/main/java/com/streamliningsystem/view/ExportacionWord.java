/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.streamliningsystem.view;

import com.streamliningsystem.model.Cotizacion;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import java.io.FileOutputStream;
import java.util.List;
import java.util.ArrayList;

public class ExportacionWord {

    public void exportarTabla(List<Cotizacion> listaCotizaciones, String nombreArchivo) {
        try {
            // Crear documento .docx
            XWPFDocument documento = new XWPFDocument();

            // Crear párrafo para título
            XWPFParagraph titulo = documento.createParagraph();
            XWPFRun tituloRun = titulo.createRun();
            tituloRun.setText("Tabla de Cotizaciones");

            // Crear tabla
            XWPFTable tabla = documento.createTable();
            
            // Agregar encabezado de la tabla
            String[] encabezado = {"N", "Cantidad", "Unidad de Medida", "Descripción", "Precio Unitario", "Precio Total"};
            XWPFTableRow filaEncabezado = tabla.getRow(0); // Obtener la primera fila (encabezado)
            for (int i = 0; i < encabezado.length; i++) {
                XWPFTableCell celda = filaEncabezado.getCell(i);
                if (celda == null) {
                    celda = filaEncabezado.createCell();
                }
                celda.setText(encabezado[i]);
            }

            // Agregar filas y celdas a la tabla
            for (Cotizacion cotizacion : listaCotizaciones) {
                XWPFTableRow fila = tabla.createRow();
                for (int i = 0; i < 6; i++) {
                    // Obtener la celda en la posición actual
                    XWPFTableCell celda = fila.getCell(i);
                    // Si la celda no existe, crear una nueva
                    if (celda == null) {
                        celda = fila.createCell();
                    }
                    // Asignar el texto a la celda
                    switch (i) {
                        case 0:
                            celda.setText(String.valueOf(cotizacion.getId_cotizacion()));
                            break;
                        case 1:
                            celda.setText(String.valueOf(cotizacion.getCantidad()));
                            break;
                        case 2:
                            celda.setText(cotizacion.getUnidadMedida());
                            break;
                        case 3:
                            celda.setText(cotizacion.getDescripcion());
                            break;
                        case 4:
                            celda.setText(String.valueOf(cotizacion.getPrecioUnitario()));
                            break;
                        case 5:
                            celda.setText(String.valueOf(cotizacion.getPrecioTotal()));
                            break;
                        default:
                            break;
                    }
                }
            }

            // Escribir documento en archivo
            FileOutputStream salida = new FileOutputStream(nombreArchivo);
            documento.write(salida);
            salida.close();
            documento.close();

            System.out.println("Tabla exportada correctamente a " + nombreArchivo);
        } catch (Exception ex) {
            System.err.println("Error al exportar tabla: " + ex.getMessage());
        }
    }
}
