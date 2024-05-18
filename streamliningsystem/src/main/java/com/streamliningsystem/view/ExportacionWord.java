package com.streamliningsystem.view;

import com.streamliningsystem.controllers.DetalleOrdenController;
import com.streamliningsystem.controllers.OrdenController;
import com.streamliningsystem.models.ViewModels.DetalleOrdenVM;
import com.streamliningsystem.models.ViewModels.TablaOrdenVM;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;

public class ExportacionWord {

    //objetos globales
    DetalleOrdenController detalleOrdenController = new DetalleOrdenController();
    OrdenController ordenController = new OrdenController();

    private ArrayList<DetalleOrdenVM> importarDetalles(int id) {

        ArrayList<DetalleOrdenVM> detalleOrdenesVM = detalleOrdenController.listarDetalleOrden(id);

        return detalleOrdenesVM;
    }

    private TablaOrdenVM importarDatos(int id) {

        return ordenController.datosOrden(id);
    }

    public void exportarTabla(int ordenId) {
        try {

            TablaOrdenVM ordenVM = importarDatos(ordenId);
            int idOrden = ordenVM.getIdOrden();
            String codOrden = ordenVM.getCodOrden();
            String encargadoOrden = ordenVM.getEncargadoOrden();
            double totales = ordenVM.getTotales();
            int clienteId = ordenVM.getClienteId();
            int proveedorId = ordenVM.getProveedorId();
            int fechasErId = ordenVM.getFechasErId();
            String encargadoCompra = ordenVM.getEncargadoCompra();
            String nombreInstitucion = ordenVM.getNombreInstitucion();
            String municipio = ordenVM.getMunicipio();
            String nombreProveedor = ordenVM.getNombreProveedor();

            SimpleDateFormat formatter = new SimpleDateFormat("d 'DE' MMMM 'DE' yyyy", new Locale("es", "ES"));
            String fechaSolicitudStr = formatter.format(ordenVM.getFechaSolicitud());
            String fechaCotizacionStr = formatter.format(ordenVM.getFechaCotizacion());
            String fechaOrdenStr = formatter.format(ordenVM.getFechaOrden());
            String fechaRecepcionStr = formatter.format(ordenVM.getFechaRecepcion());
            String fechaPlanComprasStr = formatter.format(ordenVM.getFechaPlanCompras());

            XWPFDocument documento = new XWPFDocument();

            XWPFParagraph titulo = documento.createParagraph();
            titulo.setAlignment(ParagraphAlignment.CENTER);
            titulo.setSpacingAfter(200);
            titulo.getCTP().addNewPPr().addNewShd().setFill("9EC6E6");
            XWPFRun tituloRun = titulo.createRun();
            tituloRun.setBold(true);
            tituloRun.setFontSize(12);
            tituloRun.setFontFamily("Times New Roman");
            tituloRun.setText("SOLICITUD DE COTIZACIÓN.");

            /**
             * **************************************************************
             */
            XWPFParagraph encabezado = documento.createParagraph();
            encabezado.setAlignment(ParagraphAlignment.LEFT);
            encabezado.setSpacingAfter(200);

            XWPFRun encabezadoRun1 = encabezado.createRun();
            encabezadoRun1.setFontFamily("Times New Roman");
            encabezadoRun1.setFontSize(10);
            encabezadoRun1.setText("LUGAR Y FECHA: ");

            XWPFRun encabezadoRun2 = encabezado.createRun();
            encabezadoRun2.setFontFamily("Times New Roman");
            encabezadoRun2.setFontSize(10);
            encabezadoRun2.setBold(true); // Texto en negrita
            encabezadoRun2.setText(municipio + ", " + fechaSolicitudStr);
            encabezadoRun2.addBreak();

            XWPFRun encabezadoRun3 = encabezado.createRun();
            encabezadoRun3.setFontFamily("Times New Roman");
            encabezadoRun3.setFontSize(10);
            encabezadoRun3.addBreak();
            encabezadoRun3.setText("SEÑORES:");
            encabezadoRun3.addBreak();

            XWPFRun encabezadoRun4 = encabezado.createRun();
            encabezadoRun4.setFontFamily("Times New Roman");
            encabezadoRun4.setFontSize(10);
            encabezadoRun4.setBold(true); // Texto en negrita
            encabezadoRun4.setText(nombreProveedor);
            encabezadoRun4.addBreak();

            XWPFRun encabezadoRun5 = encabezado.createRun();
            encabezadoRun5.setFontFamily("Times New Roman");
            encabezadoRun5.setFontSize(10);
            encabezadoRun5.setText("Presente.");

            /**
             * ***********************************************************
             */
            XWPFParagraph nuevoParrafo = documento.createParagraph();
            nuevoParrafo.setAlignment(ParagraphAlignment.DISTRIBUTE);
            nuevoParrafo.setSpacingAfter(50);

            XWPFRun nuevoParrafoRun1 = nuevoParrafo.createRun();
            nuevoParrafoRun1.setFontFamily("Times New Roman");
            nuevoParrafoRun1.setFontSize(10);
            nuevoParrafoRun1.setText("El Suscrito Presidente del Organismo de Administración Escolar: ");

            XWPFRun nuevoParrafoRun2 = nuevoParrafo.createRun();
            nuevoParrafoRun2.setFontFamily("Times New Roman");
            nuevoParrafoRun2.setFontSize(10);
            nuevoParrafoRun2.setBold(true);
            nuevoParrafoRun2.setText("ESCUELA DE EDUCACION PARVULARIA LA REINA");

            XWPFRun nuevoParrafoRun3 = nuevoParrafo.createRun();
            nuevoParrafoRun3.setFontFamily("Times New Roman");
            nuevoParrafoRun3.setFontSize(10);
            nuevoParrafoRun3.setText(", ubicado en el Municipio de ");

            XWPFRun nuevoParrafoRun4 = nuevoParrafo.createRun();
            nuevoParrafoRun4.setFontFamily("Times New Roman");
            nuevoParrafoRun4.setFontSize(10);
            nuevoParrafoRun4.setBold(true);
            nuevoParrafoRun4.setText("LA REINA, Chalatenango");

            XWPFRun nuevoParrafoRun5 = nuevoParrafo.createRun();
            nuevoParrafoRun5.setFontFamily("Times New Roman");
            nuevoParrafoRun5.setFontSize(10);
            nuevoParrafoRun5.setText(", por este medio solicito cotización y especificación técnica por escrito del material o servicio abajo detallados, la cual deberá ser enviada a nuestro centro educativo a más tardar el ");

            XWPFRun nuevoParrafoRun6 = nuevoParrafo.createRun();
            nuevoParrafoRun6.setFontFamily("Times New Roman");
            nuevoParrafoRun6.setFontSize(10);
            nuevoParrafoRun6.setBold(true);
            nuevoParrafoRun6.setText("29 DE MAYO DEL CORRIENTE AÑO");

            XWPFRun nuevoParrafoRun7 = nuevoParrafo.createRun();
            nuevoParrafoRun7.setFontFamily("Times New Roman");
            nuevoParrafoRun7.setFontSize(10);
            nuevoParrafoRun7.setText("; las cotizaciones que se reciban posterior a esta fecha no serán consideradas para decidir la compra. Deberá presentarse las ofertas en original a nombre de CDE del Centro Educativo, indicándose a vigencia de la cotización, así como especificar las siguientes condiciones de compra:");

            /**
             * ****************************************************************
             */
            XWPFParagraph nuevoParrafo1 = documento.createParagraph();
            nuevoParrafo1.setAlignment(ParagraphAlignment.LEFT);
            nuevoParrafo1.setSpacingAfter(50);

            XWPFRun nuevoParrafo1Run = nuevoParrafo1.createRun();
            nuevoParrafo1Run.setFontFamily("Times New Roman");
            nuevoParrafo1Run.setFontSize(10);
            nuevoParrafo1Run.setText("1.PLAZO DE ENTREGA");
            nuevoParrafo1Run.addBreak();
            nuevoParrafo1Run.setText("2. LUGAR DE ENTREGA.");
            nuevoParrafo1Run.addBreak();
            nuevoParrafo1Run.setText("3. VIGENCIA DE LA COTIZACION.");
            nuevoParrafo1Run.addBreak();
            nuevoParrafo1Run.setText("4. TIEMPO DE GARANTIA DE LOS BIENES (EN CASO QUE APLIQUE)");
            nuevoParrafo1Run.addBreak();

            /**
             * ***************************************************************
             */
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

            // Crear el encabezado de la tabla
            String[] encabezadoTabla = {"No.", "Cantidad", "Unidad de medida", "Descripción/especificación técnica"};
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

            for (DetalleOrdenVM detalle : importarDetalles(ordenId)) {
                XWPFTableRow fila = tabla.createRow();

                // Columna No.
                XWPFTableCell celdaNo = fila.getCell(0);
                if (celdaNo == null) {
                    celdaNo = fila.createCell();
                }
                XWPFParagraph pNo = celdaNo.getParagraphs().get(0);
                pNo.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rNo = pNo.createRun();
                rNo.setFontSize(10);
                rNo.setFontFamily("Times New Roman");
                rNo.setText(String.valueOf(detalle.numArticulo));

                XWPFTableCell celdaCantidad = fila.getCell(1);
                if (celdaCantidad == null) {
                    celdaCantidad = fila.createCell();
                }
                XWPFParagraph pCantidad = celdaCantidad.getParagraphs().get(0);
                pCantidad.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rCantidad = pCantidad.createRun();
                rCantidad.setFontSize(10);
                rCantidad.setFontFamily("Times New Roman");
                rCantidad.setText(String.valueOf(detalle.cantidad));

                XWPFTableCell celdaUnidadMedida = fila.getCell(2);
                if (celdaUnidadMedida == null) {
                    celdaUnidadMedida = fila.createCell();
                }
                XWPFParagraph pUnidadMedida = celdaUnidadMedida.getParagraphs().get(0);
                pUnidadMedida.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rUnidadMedida = pUnidadMedida.createRun();
                rUnidadMedida.setFontSize(10);
                rUnidadMedida.setFontFamily("Times New Roman");
                rUnidadMedida.setText(detalle.unidadMedida);

                XWPFTableCell celdaDescripcion = fila.getCell(3);
                if (celdaDescripcion == null) {
                    celdaDescripcion = fila.createCell();
                }
                XWPFParagraph pDescripcion = celdaDescripcion.getParagraphs().get(0);
                pDescripcion.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rDescripcion = pDescripcion.createRun();
                rDescripcion.setFontSize(10);
                rDescripcion.setFontFamily("Times New Roman");
                rDescripcion.setText(detalle.descripcionArticulo);
            }

            /**
             * ***************************************************************
             */
            XWPFParagraph parrafoNuevo = documento.createParagraph();
            parrafoNuevo.setAlignment(ParagraphAlignment.LEFT);
            parrafoNuevo.setSpacingAfter(200);

            XWPFRun run1 = parrafoNuevo.createRun();
            run1.setFontFamily("Times New Roman");
            run1.setFontSize(10);
            run1.addBreak();
            run1.setText("ENTREGA: ");
            run1.addTab();
            run1.addTab();
            run1.addTab();
            run1.addTab();
            run1.addTab();
            run1.addTab();
            run1.setText("RECIBE:");

            XWPFRun run2 = parrafoNuevo.createRun();
            run2.addBreak();
            run2.setFontFamily("Times New Roman");
            run2.setFontSize(10);
            run2.setText(" ");
            run1.addBreak();
            run2.setText(" ");
            run1.addBreak();
            run2.setText("F.___________________________________");
            run2.addTab();
            run2.addTab();
            run2.setText("F.___________________________________");

            XWPFRun run3 = parrafoNuevo.createRun();
            run3.addBreak();
            run3.setFontFamily("Times New Roman");
            run3.setFontSize(10);
            run3.setText("Nombre del Encargado de Compras");
            run3.addTab();
            run3.addTab();
            run3.addTab();
            run3.addTab();
            run3.setText("MARGARITA URBINA SIBRIAN");

            XWPFRun run4 = parrafoNuevo.createRun();
            run4.addBreak();
            run4.setFontFamily("Times New Roman");
            run4.setFontSize(10);
            run4.setText("SELLO");
            run4.addTab();
            run4.addTab();
            run4.addTab();
            run4.addTab();
            run4.addTab();
            run4.addTab();
            run4.addTab();
            run4.setText("SELLO");

            /**
             * ***************************************************************
             */
            //PAGINA 2
            documento.createParagraph().createRun().addBreak(BreakType.PAGE);

            XWPFParagraph parrafoTitulo = documento.createParagraph();
            parrafoTitulo.setAlignment(ParagraphAlignment.CENTER);
            parrafoTitulo.setSpacingAfter(200);
            parrafoTitulo.getCTP().addNewPPr().addNewShd().setFill("9EC6E6");
            XWPFRun runTitulo = parrafoTitulo.createRun();
            runTitulo.setBold(true);
            runTitulo.setFontSize(12);
            runTitulo.setFontFamily("Times New Roman");
            runTitulo.setText("COTIZACIÓN.");

            // Crear el párrafo del encabezado
            XWPFParagraph parrafoEncabezado = documento.createParagraph();
            parrafoEncabezado.setAlignment(ParagraphAlignment.LEFT);
            parrafoEncabezado.setSpacingAfter(200);

// Parte "LUGAR Y FECHA: "
            XWPFRun runEncabezado1 = parrafoEncabezado.createRun();
            runEncabezado1.setFontFamily("Times New Roman");
            runEncabezado1.setFontSize(10);
            runEncabezado1.setText("LUGAR Y FECHA: ");

// Parte "MUNICIPIO, FECHA" en negrita
            XWPFRun runEncabezado2 = parrafoEncabezado.createRun();
            runEncabezado2.setFontFamily("Times New Roman");
            runEncabezado2.setFontSize(10);
            runEncabezado2.setBold(true); // Texto en negrita
            runEncabezado2.setText(municipio + ", " + fechaCotizacionStr);
            runEncabezado2.addBreak();

// Saltos de línea
            XWPFRun runEncabezado3 = parrafoEncabezado.createRun();
            runEncabezado3.setFontFamily("Times New Roman");
            runEncabezado3.setFontSize(10);
            runEncabezado3.addBreak();
            runEncabezado3.setText("SEÑORES MIEMBROS DEL CDE.");
            runEncabezado3.addBreak();

// Parte "ESCUELA DE EDUCACIÓN PARVULARIA LUGAR" en negrita
            XWPFRun runEncabezado4 = parrafoEncabezado.createRun();
            runEncabezado4.setFontFamily("Times New Roman");
            runEncabezado4.setFontSize(10);
            runEncabezado4.setBold(true); // Texto en negrita
            runEncabezado4.setText(nombreInstitucion);
            runEncabezado4.addBreak();

// Parte "Presente."
            XWPFRun runEncabezado5 = parrafoEncabezado.createRun();
            runEncabezado5.setFontFamily("Times New Roman");
            runEncabezado5.setFontSize(10);
            runEncabezado5.setText("Presente.");

            // Crear el párrafo para el nuevo contenido
            XWPFParagraph parrafoNuevo1 = documento.createParagraph();
            parrafoNuevo1.setAlignment(ParagraphAlignment.DISTRIBUTE);
            parrafoNuevo1.setSpacingAfter(50);

// Parte "El Suscrito Presidente del Organismo de Administración Escolar: "
            XWPFRun runNuevo1 = parrafoNuevo1.createRun();
            runNuevo1.setFontFamily("Times New Roman");
            runNuevo1.setFontSize(10);
            runNuevo1.setText("Atendiendo la solicitud de cotización de fecha ");

// Parte "ESCUELA DE EDUCACION PARVULARIA LA REINA" en negrita
            XWPFRun runNuevo2 = parrafoNuevo1.createRun();
            runNuevo2.setFontFamily("Times New Roman");
            runNuevo2.setFontSize(10);
            runNuevo2.setBold(true);
            runNuevo2.setText(fechaSolicitudStr);

// Parte ", ubicado en el Municipio de "
            XWPFRun runNuevo3 = parrafoNuevo1.createRun();
            runNuevo3.setFontFamily("Times New Roman");
            runNuevo3.setFontSize(10);
            runNuevo3.setText(",  suscrita por el CDE que administra La ");

// Parte "LA REINA, Chalatenango" en negrita
            XWPFRun runNuevo4 = parrafoNuevo1.createRun();
            runNuevo4.setFontFamily("Times New Roman");
            runNuevo4.setFontSize(10);
            runNuevo4.setBold(true);
            runNuevo4.setText(nombreInstitucion);

// Parte ", por este medio solicito cotización y especificación técnica por escrito del material o servicio abajo detallados, la cual deberá ser enviada a nuestro centro educativo a más tardar el "
            XWPFRun runNuevo5 = parrafoNuevo1.createRun();
            runNuevo5.setFontFamily("Times New Roman");
            runNuevo5.setFontSize(10);
            runNuevo5.setText(", ubicado en el Municipio de");

// Parte "29 DE MAYO DEL CORRIENTE AÑO" en negrita
            XWPFRun runNuevo6 = parrafoNuevo1.createRun();
            runNuevo6.setFontFamily("Times New Roman");
            runNuevo6.setFontSize(10);
            runNuevo6.setBold(true);
            runNuevo6.setText(municipio);

// Parte "; las cotizaciones que se reciban posterior a esta fecha no serán consideradas para decidir la compra. Deberá presentarse las ofertas en original a nombre de CDE del Centro Educativo, indicándose a vigencia de la cotización, así como especificar las siguientes condiciones de compra:"
            XWPFRun runNuevo7 = parrafoNuevo1.createRun();
            runNuevo7.setFontFamily("Times New Roman");
            runNuevo7.setFontSize(10);
            runNuevo7.setText(", atentamente remito la oferta en original apegada a las condiciones y especificaciones de compras detalladas a continuación:");

            // Crear la tabla
            XWPFTable tabla20 = documento.createTable();
            tabla20.setWidth("100%");
            tabla20.setTableAlignment(TableRowAlign.CENTER);

// Configurar bordes de la tabla
            CTTblPr tblPr20 = tabla20.getCTTbl().addNewTblPr();
            tblPr20.addNewTblBorders().addNewBottom().setVal(STBorder.SINGLE);
            tblPr20.getTblBorders().addNewLeft().setVal(STBorder.SINGLE);
            tblPr20.getTblBorders().addNewRight().setVal(STBorder.SINGLE);
            tblPr20.getTblBorders().addNewTop().setVal(STBorder.SINGLE);
            tblPr20.getTblBorders().addNewInsideH().setVal(STBorder.SINGLE);
            tblPr20.getTblBorders().addNewInsideV().setVal(STBorder.SINGLE);

// Crear el encabezado de la tabla
            String[] encabezadoTabla20 = {"No.", "Cantidad", "Unidad de medida", "Descripción/especificación técnica"};
            XWPFTableRow filaEncabezado20 = tabla20.getRow(0);
            for (int i = 0; i < encabezadoTabla20.length; i++) {
                XWPFTableCell celda20 = filaEncabezado20.getCell(i);
                if (celda20 == null) {
                    celda20 = filaEncabezado20.createCell();
                }
                XWPFParagraph p20 = celda20.getParagraphs().get(0);
                p20.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun r20 = p20.createRun();
                r20.setFontSize(10);
                r20.setFontFamily("Times New Roman");
                r20.setBold(true);
                r20.setText(encabezadoTabla20[i]);
            }

// Poblar las filas con los datos
            for (DetalleOrdenVM detalle20 : importarDetalles(ordenId)) {
                XWPFTableRow fila20 = tabla20.createRow();

                // Columna No.
                XWPFTableCell celdaNo20 = fila20.getCell(0);
                if (celdaNo20 == null) {
                    celdaNo20 = fila20.createCell();
                }
                XWPFParagraph pNo20 = celdaNo20.getParagraphs().get(0);
                pNo20.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rNo20 = pNo20.createRun();
                rNo20.setFontSize(10);
                rNo20.setFontFamily("Times New Roman");
                rNo20.setText(String.valueOf(detalle20.numArticulo));

                // Columna Cantidad
                XWPFTableCell celdaCantidad20 = fila20.getCell(1);
                if (celdaCantidad20 == null) {
                    celdaCantidad20 = fila20.createCell();
                }
                XWPFParagraph pCantidad20 = celdaCantidad20.getParagraphs().get(0);
                pCantidad20.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rCantidad20 = pCantidad20.createRun();
                rCantidad20.setFontSize(10);
                rCantidad20.setFontFamily("Times New Roman");
                rCantidad20.setText(String.valueOf(detalle20.cantidad));

                // Columna Unidad de medida
                XWPFTableCell celdaUnidadMedida20 = fila20.getCell(2);
                if (celdaUnidadMedida20 == null) {
                    celdaUnidadMedida20 = fila20.createCell();
                }
                XWPFParagraph pUnidadMedida20 = celdaUnidadMedida20.getParagraphs().get(0);
                pUnidadMedida20.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rUnidadMedida20 = pUnidadMedida20.createRun();
                rUnidadMedida20.setFontSize(10);
                rUnidadMedida20.setFontFamily("Times New Roman");
                rUnidadMedida20.setText(detalle20.unidadMedida);

                // Columna Descripción/especificación técnica
                XWPFTableCell celdaDescripcion20 = fila20.getCell(3);
                if (celdaDescripcion20 == null) {
                    celdaDescripcion20 = fila20.createCell();
                }
                XWPFParagraph pDescripcion20 = celdaDescripcion20.getParagraphs().get(0);
                pDescripcion20.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rDescripcion20 = pDescripcion20.createRun();
                rDescripcion20.setFontSize(10);
                rDescripcion20.setFontFamily("Times New Roman");
                rDescripcion20.setText(detalle20.descripcionArticulo);
            }

            // Crear el párrafo para el nuevo contenido
            XWPFParagraph nuevoParrafo10 = documento.createParagraph();
            nuevoParrafo10.setAlignment(ParagraphAlignment.LEFT);
            nuevoParrafo10.setSpacingAfter(50);

// Crear el contenido del párrafo
            XWPFRun nuevoRun10 = nuevoParrafo10.createRun();
            nuevoRun10.setFontFamily("Times New Roman");
            nuevoRun10.setFontSize(10);
            nuevoRun10.setText("1.PLAZO DE ENTREGA");
            nuevoRun10.addBreak();
            nuevoRun10.setText("2. LUGAR DE ENTREGA.");
            nuevoRun10.addBreak();
            nuevoRun10.setText("3. VIGENCIA DE LA COTIZACION.");
            nuevoRun10.addBreak();
            nuevoRun10.setText("4. TIEMPO DE GARANTIA DE LOS BIENES (EN CASO QUE APLIQUE)");
            nuevoRun10.addBreak();

            // Crear el párrafo
            XWPFParagraph parrafoNuevo20 = documento.createParagraph();
            parrafoNuevo20.setAlignment(ParagraphAlignment.LEFT);
            parrafoNuevo20.setSpacingAfter(200);

// Primer run
            XWPFRun run1_20 = parrafoNuevo20.createRun();
            run1_20.setFontFamily("Times New Roman");
            run1_20.setFontSize(10);
            run1_20.addBreak();
            run1_20.setText("ENTREGA: ");
            run1_20.addTab();
            run1_20.addTab();
            run1_20.addTab();
            run1_20.addTab();
            run1_20.addTab();
            run1_20.addTab();
            run1_20.setText("RECIBE:");

// Segundo run
            XWPFRun run2_20 = parrafoNuevo20.createRun();
            run2_20.addBreak();
            run2_20.setFontFamily("Times New Roman");
            run2_20.setFontSize(10);
            run2_20.setText(" ");
            run2_20.addBreak();
            run2_20.setText(" ");
            run2_20.addBreak();
            run2_20.setText("F.___________________________________");
            run2_20.addTab();
            run2_20.addTab();
            run2_20.setText("F.___________________________________");

// Tercer run
            XWPFRun run3_20 = parrafoNuevo20.createRun();
            run3_20.addBreak();
            run3_20.setFontFamily("Times New Roman");
            run3_20.setFontSize(10);
            run3_20.setText("Nombre del Encargado de Compras");
            run3_20.addTab();
            run3_20.addTab();
            run3_20.addTab();
            run3_20.addTab();
            run3_20.setText("MARGARITA URBINA SIBRIAN");

// Cuarto run
            XWPFRun run4_20 = parrafoNuevo20.createRun();
            run4_20.addBreak();
            run4_20.setFontFamily("Times New Roman");
            run4_20.setFontSize(10);
            run4_20.setText("SELLO");
            run4_20.addTab();
            run4_20.addTab();
            run4_20.addTab();
            run4_20.addTab();
            run4_20.addTab();
            run4_20.addTab();
            run4_20.addTab();
            run4_20.setText("SELLO");

            /**
             * ***************
             */
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
