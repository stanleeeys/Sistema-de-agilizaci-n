package View;

import Controllers.DetalleOrdenController;
import Controllers.OrdenController;
import ViewModel.DetalleOrdenVM;
import ViewModel.TablaOrdenVM;
import java.io.FileInputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

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
            String codigo = ordenVM.getCodigo_escuela();
            int clienteId = ordenVM.getClienteId();
            int proveedorId = ordenVM.getProveedorId();
            int fechasErId = ordenVM.getFechasErId();
            String encargadoCompra = ordenVM.getEncargadoCompra();
            String nombreInstitucion = ordenVM.getNombreInstitucion();
            String municipio = ordenVM.getMunicipio();
            String nombreProveedor = ordenVM.getNombreProveedor();
            String desde = ordenVM.getHora_entrega_desde();
            String hasta = ordenVM.getHora_entrega_hasta();
            String tiempo_entrega = ordenVM.getTiempo_entrega();
            String plazo_entrega = ordenVM.getPlazo_entrega();
            String lugar_entrega = ordenVM.getLugar_entrega();
            String vigencia_de_la_cotizacion = ordenVM.getLugar_entrega();

            String tiempo_de_garantia  = ordenVM.getTiempo_de_garantia();
            SimpleDateFormat formatter = new SimpleDateFormat("d 'DE' MMMM 'DE' yyyy", new Locale("es", "ES"));
            String fechaSolicitudStr = formatter.format(ordenVM.getFechaSolicitud());
            String fechaCotizacionStr = formatter.format(ordenVM.getFechaCotizacion());
            String fechaOrdenStr = formatter.format(ordenVM.getFechaOrden());
            String fechaRecepcionStr = formatter.format(ordenVM.getFechaRecepcion());
            String fechaPlanComprasStr = formatter.format(ordenVM.getFechaPlanCompras());
            String limite_cotizacion = formatter.format(ordenVM.getLimite_cotizacion());
            String fecha_de_entrega = formatter.format(ordenVM.getFecha_de_entrega());

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
            nuevoParrafoRun2.setText(nombreInstitucion);

            XWPFRun nuevoParrafoRun3 = nuevoParrafo.createRun();
            nuevoParrafoRun3.setFontFamily("Times New Roman");
            nuevoParrafoRun3.setFontSize(10);
            nuevoParrafoRun3.setText(", ubicado en el Municipio de ");

            XWPFRun nuevoParrafoRun4 = nuevoParrafo.createRun();
            nuevoParrafoRun4.setFontFamily("Times New Roman");
            nuevoParrafoRun4.setFontSize(10);
            nuevoParrafoRun4.setBold(true);
            nuevoParrafoRun4.setText(municipio);

            XWPFRun nuevoParrafoRun5 = nuevoParrafo.createRun();
            nuevoParrafoRun5.setFontFamily("Times New Roman");
            nuevoParrafoRun5.setFontSize(10);
            nuevoParrafoRun5.setText(", por este medio solicito cotización y especificación técnica por escrito del material o servicio abajo detallados, la cual deberá ser enviada a nuestro centro educativo a más tardar el ");

            XWPFRun nuevoParrafoRun6 = nuevoParrafo.createRun();
            nuevoParrafoRun6.setFontFamily("Times New Roman");
            nuevoParrafoRun6.setFontSize(10);
            nuevoParrafoRun6.setBold(true);
            nuevoParrafoRun6.setText(limite_cotizacion);

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
//            run2.addTab();
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
            run3.setText(encargadoOrden);

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
            //*************************************************************************************************************************PAGINA 2
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
            runNuevo3.setText(",  suscrita por el CDE que administra el/la ");

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
            runNuevo5.setText(", ubicado en el Municipio de ");

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
            runNuevo7.addBreak();

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
            String[] encabezadoTabla20 = {"No.", "Cantidad", "Unidad de medida", "Descripción/especificación técnica", "Precio Unitario", "Precio Total"};
            XWPFTableRow filaEncabezado20 = tabla20.getRow(0);

// Color celeste pálido
            String colorCelestePálido = "9EC6E6";

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

                // Establecer el color de fondo
                celda20.setColor(colorCelestePálido);
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

                // Columna Precio Unitario
                XWPFTableCell celdaPrecioUnitario20 = fila20.getCell(4);
                if (celdaPrecioUnitario20 == null) {
                    celdaPrecioUnitario20 = fila20.createCell();
                }
                XWPFParagraph pPrecioUnitario20 = celdaPrecioUnitario20.getParagraphs().get(0);
                pPrecioUnitario20.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rPrecioUnitario20 = pPrecioUnitario20.createRun();
                rPrecioUnitario20.setFontSize(10);
                rPrecioUnitario20.setFontFamily("Times New Roman");
                rPrecioUnitario20.setText(String.valueOf(detalle20.precioUnitario));

                // Columna Precio Total
                XWPFTableCell celdaPrecioTotal20 = fila20.getCell(5);
                if (celdaPrecioTotal20 == null) {
                    celdaPrecioTotal20 = fila20.createCell();
                }
                XWPFParagraph pPrecioTotal20 = celdaPrecioTotal20.getParagraphs().get(0);
                pPrecioTotal20.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rPrecioTotal20 = pPrecioTotal20.createRun();
                rPrecioTotal20.setFontSize(10);
                rPrecioTotal20.setFontFamily("Times New Roman");
                rPrecioTotal20.setText(String.valueOf(detalle20.precioTotal));
            }

            XWPFTableRow pieTabla = tabla20.createRow();

// Crear celdas para las otras columnas
            for (int i = 0; i < encabezadoTabla20.length; i++) {
                XWPFTableCell celdaPie = pieTabla.getCell(i);
                if (celdaPie == null) {
                    celdaPie = pieTabla.createCell();
                }
                XWPFParagraph pPie = celdaPie.getParagraphs().get(0);
                pPie.setAlignment(ParagraphAlignment.RIGHT);
                XWPFRun rPie = pPie.createRun();
                rPie.setFontSize(10);
                rPie.setFontFamily("Times New Roman");
                rPie.setBold(true);
                if (i == 2) {
                    rPie.setText("Totales"); // Tercera columna
                } else if (i == encabezadoTabla20.length - 1) {
                    rPie.setText("$" + String.valueOf(totales)); // Última columna
                } else {
                    rPie.setText(""); // Otras columnas, dejar en blanco
                }
            }

// Crear celda para el valor de los totales en la tercera columna
            XWPFTableCell celdaTotal = pieTabla.getCell(2);
            if (celdaTotal == null) {
                celdaTotal = pieTabla.createCell();
            }
            XWPFParagraph pTotal = celdaTotal.getParagraphs().get(0);
            pTotal.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun rTotal = pTotal.createRun();
            rTotal.setFontSize(10);
            rTotal.setFontFamily("Times New Roman");
            rTotal.setBold(true);
            rTotal.setText(""); // Tercera columna

            // Después de agregar todas las filas y antes de agregar la fila de totales
            XWPFTableRow ultimaFila = tabla20.getRow(tabla20.getNumberOfRows() - 1);

            for (int i = 0; i < encabezadoTabla20.length; i++) {
                XWPFTableCell celda = ultimaFila.getCell(i);
                if (celda == null) {
                    celda = ultimaFila.createCell();
                }
                XWPFParagraph p = celda.getParagraphs().get(0);
                p.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun r = p.createRun();

                // Establecer el color de fondo
                celda.setColor(colorCelestePálido);
            }

            // Crear el párrafo para el nuevo contenido
            XWPFParagraph nuevoParrafo10 = documento.createParagraph();
            nuevoParrafo10.setAlignment(ParagraphAlignment.LEFT);
            nuevoParrafo10.setSpacingAfter(100);

// Crear el contenido del párrafo
            XWPFRun nuevoRun10 = nuevoParrafo10.createRun();
            nuevoRun10.setFontFamily("Times New Roman");
            nuevoRun10.setFontSize(10);
            nuevoRun10.addBreak();
            nuevoRun10.setText("1.PLAZO DE ENTREGA: " + plazo_entrega);
            nuevoRun10.addBreak();
            nuevoRun10.setText("2. LUGAR DE ENTREGA: " + lugar_entrega);
            nuevoRun10.addBreak();
            nuevoRun10.setText("3. VIGENCIA DE LA COTIZACION: " + vigencia_de_la_cotizacion);
            nuevoRun10.addBreak();
            nuevoRun10.setText("4. TIEMPO DE GARANTIA DE LOS BIENES: " + tiempo_de_garantia);
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
            
            run2_20.setText("F.___________________________________");

// Tercer run
            XWPFRun run3_20 = parrafoNuevo20.createRun();
            run3_20.addBreak();
            run3_20.setFontFamily("Times New Roman");
            run3_20.setFontSize(10);
            run3_20.setText(encargadoOrden);
            run3_20.addTab();
            run3_20.addTab();
            run3_20.addTab();
            run3_20.addTab();
            run3_20.setText("Nombre del Encargado de Compras");

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
             * *********************************************************************************************************
             * PAGINA 3
             */

            documento.createParagraph().createRun().addBreak(BreakType.PAGE);

            // Crear el párrafo del título
            XWPFParagraph parrafoTitulo30 = documento.createParagraph();
            parrafoTitulo30.setAlignment(ParagraphAlignment.CENTER);
            parrafoTitulo30.setSpacingAfter(200);
            parrafoTitulo30.getCTP().addNewPPr().addNewShd().setFill("9EC6E6");
            XWPFRun runTitulo30 = parrafoTitulo30.createRun();
            runTitulo30.setBold(true);
            runTitulo30.setFontSize(12);
            runTitulo30.setFontFamily("Times New Roman");
            runTitulo30.setText("ORDEN DE COMPRA DE BIENES Y SERVICIOS N° ______");

// Crear el párrafo del encabezado
            XWPFParagraph parrafoEncabezado30 = documento.createParagraph();
            parrafoEncabezado30.setAlignment(ParagraphAlignment.LEFT);
            parrafoEncabezado30.setSpacingAfter(200);

// Parte "LUGAR Y FECHA: "
            XWPFRun runEncabezado301 = parrafoEncabezado30.createRun();
            runEncabezado301.setFontFamily("Times New Roman");
            runEncabezado301.setFontSize(10);
            runEncabezado301.setText("LUGAR Y FECHA: ");

// Parte "MUNICIPIO, FECHA" en negrita
            XWPFRun runEncabezado302 = parrafoEncabezado30.createRun();
            runEncabezado302.setFontFamily("Times New Roman");
            runEncabezado302.setFontSize(10);
            runEncabezado302.setBold(true); // Texto en negrita
            runEncabezado302.setText(municipio + ", " + fechaCotizacionStr);
            runEncabezado302.addBreak();

// Saltos de línea
            XWPFRun runEncabezado303 = parrafoEncabezado30.createRun();
            runEncabezado303.setFontFamily("Times New Roman");
            runEncabezado303.setFontSize(10);
            runEncabezado303.addBreak();
            runEncabezado303.setText("SEÑORES");
            runEncabezado303.addBreak();

// Parte "ESCUELA DE EDUCACIÓN PARVULARIA LUGAR" en negrita
            XWPFRun runEncabezado304 = parrafoEncabezado30.createRun();
            runEncabezado304.setFontFamily("Times New Roman");
            runEncabezado304.setFontSize(10);
            runEncabezado304.setBold(true); // Texto en negrita
            runEncabezado304.setText(nombreProveedor);
            runEncabezado304.addBreak();

// Parte "Presente."
            XWPFRun runEncabezado305 = parrafoEncabezado30.createRun();
            runEncabezado305.setFontFamily("Times New Roman");
            runEncabezado305.setFontSize(10);
            runEncabezado305.setText("Presente.");

// Crear el párrafo para el nuevo contenido
            XWPFParagraph parrafoNuevo301 = documento.createParagraph();
            parrafoNuevo301.setAlignment(ParagraphAlignment.LEFT);
            parrafoNuevo301.setSpacingAfter(50);

// Parte "Atendiendo la solicitud de cotización de fecha "
            XWPFRun runNuevo301 = parrafoNuevo301.createRun();
            runNuevo301.setFontFamily("Times New Roman");
            runNuevo301.setFontSize(10);
            runNuevo301.setText("Por este medio se comunica que el  CDE de el ");

// Parte con la fecha en negrita
            XWPFRun runNuevo302 = parrafoNuevo301.createRun();
            runNuevo302.setFontFamily("Times New Roman");
            runNuevo302.setFontSize(10);
            runNuevo302.setBold(true);
            runNuevo302.setText(nombreInstitucion);

// Parte ", suscrita por el CDE que administra el "
            XWPFRun runNuevo303 = parrafoNuevo301.createRun();
            runNuevo303.setFontFamily("Times New Roman");
            runNuevo303.setFontSize(10);
            runNuevo303.setText(", ha decidido adquirir en esa empresa los bienes o servicios que más adelante se detallan, de acuerdo a los precios y condiciones de su  oferta del día");

// Parte con el nombre de la institución en negrita
            XWPFRun runNuevo304 = parrafoNuevo301.createRun();
            runNuevo304.setFontFamily("Times New Roman");
            runNuevo304.setFontSize(10);
            runNuevo304.setBold(true);
            runNuevo304.setText("" + fechaCotizacionStr + ".");
            runNuevo304.addBreak();

// Parte ", ubicado en el Municipio de "
            XWPFRun runNuevo305 = parrafoNuevo301.createRun();
            runNuevo305.setFontFamily("Times New Roman");
            runNuevo305.setFontSize(10);
            runNuevo305.setText("Los bienes y/o servicios deberán entregarse  en la ");

// Parte con el municipio en negrita
            XWPFRun runNuevo306 = parrafoNuevo301.createRun();
            runNuevo306.setFontFamily("Times New Roman");
            runNuevo306.setFontSize(10);
            runNuevo306.setBold(true);
            runNuevo306.setText(nombreInstitucion);

// Parte final del párrafo
            XWPFRun runNuevo307 = parrafoNuevo301.createRun();
            runNuevo307.setFontFamily("Times New Roman");
            runNuevo307.setFontSize(10);
            runNuevo307.setText(" el día " + fecha_de_entrega + ", de las " + desde+  " horas a las HORA "+hasta+ " DE LA "+ tiempo_entrega);
            runNuevo307.addBreak();

// Crear la tabla
            XWPFTable tabla3020 = documento.createTable();
            tabla3020.setWidth("100%");
            tabla3020.setTableAlignment(TableRowAlign.CENTER);

// Configurar bordes de la tabla
            CTTblPr tblPr3020 = tabla3020.getCTTbl().addNewTblPr();
            tblPr3020.addNewTblBorders().addNewBottom().setVal(STBorder.SINGLE);
            tblPr3020.getTblBorders().addNewLeft().setVal(STBorder.SINGLE);
            tblPr3020.getTblBorders().addNewRight().setVal(STBorder.SINGLE);
            tblPr3020.getTblBorders().addNewTop().setVal(STBorder.SINGLE);
            tblPr3020.getTblBorders().addNewInsideH().setVal(STBorder.SINGLE);
            tblPr3020.getTblBorders().addNewInsideV().setVal(STBorder.SINGLE);

// Crear el encabezado de la tabla
            String[] encabezadoTabla3020 = {"No.", "Cantidad", "Unidad de medida", "Descripción/especificación técnica", "Precio Unitario", "Precio Total"};
            XWPFTableRow filaEncabezado3020 = tabla3020.getRow(0);

            String colorCelestePálido30 = "9EC6E6";

            for (int i = 0; i < encabezadoTabla3020.length; i++) {
                XWPFTableCell celda3020 = filaEncabezado3020.getCell(i);
                if (celda3020 == null) {
                    celda3020 = filaEncabezado3020.createCell();
                }
                XWPFParagraph p3020 = celda3020.getParagraphs().get(0);
                p3020.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun r3020 = p3020.createRun();
                r3020.setFontSize(10);
                r3020.setFontFamily("Times New Roman");
                r3020.setBold(true);
                r3020.setText(encabezadoTabla3020[i]);

                // Establecer el color de fondo
                celda3020.setColor(colorCelestePálido30);
            }

// Poblar las filas con los datos
            for (DetalleOrdenVM detalle3020 : importarDetalles(ordenId)) {
                XWPFTableRow fila3020 = tabla3020.createRow();

                // Columna No.
                XWPFTableCell celdaNo3020 = fila3020.getCell(0);
                if (celdaNo3020 == null) {
                    celdaNo3020 = fila3020.createCell();
                }
                XWPFParagraph pNo3020 = celdaNo3020.getParagraphs().get(0);
                pNo3020.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rNo3020 = pNo3020.createRun();
                rNo3020.setFontSize(10);
                rNo3020.setFontFamily("Times New Roman");
                rNo3020.setText(String.valueOf(detalle3020.numArticulo));

                // Columna Cantidad
                XWPFTableCell celdaCantidad3020 = fila3020.getCell(1);
                if (celdaCantidad3020 == null) {
                    celdaCantidad3020 = fila3020.createCell();
                }
                XWPFParagraph pCantidad3020 = celdaCantidad3020.getParagraphs().get(0);
                pCantidad3020.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rCantidad3020 = pCantidad3020.createRun();
                rCantidad3020.setFontSize(10);
                rCantidad3020.setFontFamily("Times New Roman");
                rCantidad3020.setText(String.valueOf(detalle3020.cantidad));

                // Columna Unidad de medida
                XWPFTableCell celdaUnidadMedida3020 = fila3020.getCell(2);
                if (celdaUnidadMedida3020 == null) {
                    celdaUnidadMedida3020 = fila3020.createCell();
                }
                XWPFParagraph pUnidadMedida3020 = celdaUnidadMedida3020.getParagraphs().get(0);
                pUnidadMedida3020.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rUnidadMedida3020 = pUnidadMedida3020.createRun();
                rUnidadMedida3020.setFontSize(10);
                rUnidadMedida3020.setFontFamily("Times New Roman");
                rUnidadMedida3020.setText(detalle3020.unidadMedida);

                // Columna Descripción/especificación técnica
                XWPFTableCell celdaDescripcion3020 = fila3020.getCell(3);
                if (celdaDescripcion3020 == null) {
                    celdaDescripcion3020 = fila3020.createCell();
                }
                XWPFParagraph pDescripcion3020 = celdaDescripcion3020.getParagraphs().get(0);
                pDescripcion3020.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rDescripcion3020 = pDescripcion3020.createRun();
                rDescripcion3020.setFontSize(10);
                rDescripcion3020.setFontFamily("Times New Roman");
                rDescripcion3020.setText(detalle3020.descripcionArticulo);

                // Columna Precio Unitario
                XWPFTableCell celdaPrecioUnitario3020 = fila3020.getCell(4);
                if (celdaPrecioUnitario3020 == null) {
                    celdaPrecioUnitario3020 = fila3020.createCell();
                }
                XWPFParagraph pPrecioUnitario3020 = celdaPrecioUnitario3020.getParagraphs().get(0);
                pPrecioUnitario3020.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rPrecioUnitario3020 = pPrecioUnitario3020.createRun();
                rPrecioUnitario3020.setFontSize(10);
                rPrecioUnitario3020.setFontFamily("Times New Roman");
                rPrecioUnitario3020.setText(String.valueOf(detalle3020.precioUnitario));

                // Columna Precio Total
                XWPFTableCell celdaPrecioTotal3020 = fila3020.getCell(5);
                if (celdaPrecioTotal3020 == null) {
                    celdaPrecioTotal3020 = fila3020.createCell();
                }
                XWPFParagraph pPrecioTotal3020 = celdaPrecioTotal3020.getParagraphs().get(0);
                pPrecioTotal3020.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rPrecioTotal3020 = pPrecioTotal3020.createRun();
                rPrecioTotal3020.setFontSize(10);
                rPrecioTotal3020.setFontFamily("Times New Roman");
                rPrecioTotal3020.setText(String.valueOf(detalle3020.precioTotal));
            }

            XWPFTableRow pieTabla30 = tabla3020.createRow();

// Crear celdas para las otras columnas
            for (int i = 0; i < encabezadoTabla3020.length; i++) {
                XWPFTableCell celdaPie30 = pieTabla30.getCell(i);
                if (celdaPie30 == null) {
                    celdaPie30 = pieTabla30.createCell();
                }
                XWPFParagraph pPie30 = celdaPie30.getParagraphs().get(0);
                pPie30.setAlignment(ParagraphAlignment.RIGHT);
                XWPFRun rPie30 = pPie30.createRun();
                rPie30.setFontSize(10);
                rPie30.setFontFamily("Times New Roman");
                rPie30.setBold(true);
                if (i == 2) {
                    rPie30.setText("Totales"); // Tercera columna
                } else if (i == encabezadoTabla3020.length - 1) {
                    rPie30.setText("$" + String.valueOf(totales)); // Última columna
                } else {
                    rPie30.setText(""); // Otras columnas, dejar en blanco
                }
            }

// Crear celda para el valor de los totales en la tercera columna
            XWPFTableCell celdaTotal30 = pieTabla30.getCell(2);
            if (celdaTotal30 == null) {
                celdaTotal30 = pieTabla30.createCell();
            }
            XWPFParagraph pTotal30 = celdaTotal30.getParagraphs().get(0);
            pTotal30.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun rTotal30 = pTotal30.createRun();
            rTotal30.setFontSize(10);
            rTotal30.setFontFamily("Times New Roman");
            rTotal30.setBold(true);
            rTotal30.setText(""); // Tercera columna

// Después de agregar todas las filas y antes de agregar la fila de totales
            XWPFTableRow ultimaFila30 = tabla3020.getRow(tabla3020.getNumberOfRows() - 1);

            for (int i = 0; i < encabezadoTabla3020.length; i++) {
                XWPFTableCell celda30 = ultimaFila30.getCell(i);
                if (celda30 == null) {
                    celda30 = ultimaFila30.createCell();
                }
                XWPFParagraph p30 = celda30.getParagraphs().get(0);
                p30.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun r30 = p30.createRun();

                // Establecer el color de fondo
                celda30.setColor(colorCelestePálido30);
            }

// Crear el párrafo para el nuevo contenido
            XWPFParagraph nuevoParrafo30 = documento.createParagraph();
            nuevoParrafo30.setAlignment(ParagraphAlignment.LEFT);
            nuevoParrafo30.setSpacingAfter(100);

            XWPFRun nuevoRun30 = nuevoParrafo30.createRun();
            nuevoRun30.setFontFamily("Times New Roman");
            nuevoRun30.setFontSize(10);
            nuevoRun30.addBreak();
            nuevoRun30.setText("Para efectos de cobro presentar esta orden de compra, original y copia de factura de consumidor final a nombre de:  C.D.E. " + nombreInstitucion + ".");
            nuevoRun30.addBreak();

// Crear el párrafo
            XWPFParagraph parrafoNuevo3020 = documento.createParagraph();
            parrafoNuevo3020.setAlignment(ParagraphAlignment.LEFT);
            parrafoNuevo3020.setSpacingAfter(200);

// Primer run
//            XWPFRun run1_3020 = parrafoNuevo3020.createRun();
//            run1_3020.setFontFamily("Times New Roman");
//            run1_3020.setFontSize(10);
//            run1_3020.addBreak();
//            run1_3020.setText("ENTREGA: ");
//            run1_3020.addTab();
//            run1_3020.addTab();
//            run1_3020.addTab();
//            run1_3020.addTab();
//            run1_3020.addTab();
//            run1_3020.addTab();
//            run1_3020.setText("RECIBE:");
// Segundo run
            XWPFRun run2_3020 = parrafoNuevo3020.createRun();
            run2_3020.addBreak();
            run2_3020.setFontFamily("Times New Roman");
            run2_3020.setFontSize(10);
            run2_3020.setText(" ");
            run2_3020.addBreak();
            run2_3020.setText(" ");
            run2_3020.addBreak();
            run2_3020.setText("F.___________________________________");
            run2_3020.addTab();
            
            run2_3020.setText("F.___________________________________");

// Tercer run
            XWPFRun run3_3020 = parrafoNuevo3020.createRun();
            run3_3020.addBreak();
            run3_3020.setFontFamily("Times New Roman");
            run3_3020.setFontSize(10);
            run3_3020.setText("Nombre del Encargado de Compras");
            run3_3020.addTab();
            run3_3020.addTab();
            run3_3020.addTab();
            run3_3020.addTab();
            run3_3020.setText(encargadoOrden);

// Cuarto run
            XWPFRun run4_3020 = parrafoNuevo3020.createRun();
            run4_3020.addBreak();
            run4_3020.setFontFamily("Times New Roman");
            run4_3020.setFontSize(10);
            run4_3020.setText("SELLO");
            run4_3020.addTab();
            run4_3020.addTab();
            run4_3020.addTab();
            run4_3020.addTab();
            run4_3020.addTab();
            run4_3020.addTab();
            run4_3020.addTab();
            run4_3020.setText("SELLO");

            /**
             * **********************************************************************************
             * PAGINA 4
             */
            documento.createParagraph().createRun().addBreak(BreakType.PAGE);

            // Crear el párrafo del título
            XWPFParagraph parrafoTitulo40 = documento.createParagraph();
            parrafoTitulo40.setAlignment(ParagraphAlignment.CENTER);
            parrafoTitulo40.setSpacingAfter(200);
            parrafoTitulo40.getCTP().addNewPPr().addNewShd().setFill("9EC6E6");
            XWPFRun runTitulo40 = parrafoTitulo40.createRun();
            runTitulo40.setBold(true);
            runTitulo40.setFontSize(12);
            runTitulo40.setFontFamily("Times New Roman");
            runTitulo40.setText("ACTA DE RECEPCION DE BIENES     N°____");

            // Crear la tabla
            XWPFTable tablaContenido = documento.createTable();
            tablaContenido.setWidth("100%");
            tablaContenido.setTableAlignment(TableRowAlign.CENTER);

// Agregar una fila a la tabla
            XWPFTableRow filaContenido = tablaContenido.getRow(0);
            if (filaContenido == null) {
                filaContenido = tablaContenido.createRow();
            }

// Columna de contenido
            XWPFTableCell celdaContenido = filaContenido.getCell(0);
            if (celdaContenido == null) {
                celdaContenido = filaContenido.createCell();
            }
            XWPFParagraph parrafoContenido = celdaContenido.getParagraphs().get(0);
            parrafoContenido.setAlignment(ParagraphAlignment.DISTRIBUTE);
            XWPFRun runContenido = parrafoContenido.createRun();
            runContenido.setFontSize(10);
            runContenido.setFontFamily("Times New Roman");
            runContenido.setText("EL ORGANISMO DE ADMINISTRACION ESCOLAR: CONSEJO DIRECTIVO ESCOLAR QUE ADMINISTRA " + nombreInstitucion + "MUNICIPIO: " + municipio +" "+ codigo +", DEPARTAMENTO DE CHALATENANGO.");

            // Crear el párrafo del título
            XWPFParagraph separador = documento.createParagraph();
            separador.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun separadorRun = separador.createRun();
            separadorRun.setBold(true);
            separadorRun.setFontSize(10);
            separadorRun.setFontFamily("Times New Roman");
            //separadorRun.setText(" ");
            separadorRun.addBreak();

            // Crear la tabla
            XWPFTable tablaContenidoNuevo = documento.createTable();
            tablaContenidoNuevo.setWidth("100%");
            tablaContenidoNuevo.setTableAlignment(TableRowAlign.CENTER);

// Agregar una fila a la tabla
            XWPFTableRow filaContenidoNuevo = tablaContenidoNuevo.getRow(0);
            if (filaContenidoNuevo == null) {
                filaContenidoNuevo = tablaContenidoNuevo.createRow();
            }

// Columna de contenido
            XWPFTableCell celdaContenidoNuevo = filaContenidoNuevo.getCell(0);
            if (celdaContenidoNuevo == null) {
                celdaContenidoNuevo = filaContenidoNuevo.createCell();
            }
            XWPFParagraph parrafoContenidoNuevo = celdaContenidoNuevo.getParagraphs().get(0);
            parrafoContenidoNuevo.setAlignment(ParagraphAlignment.DISTRIBUTE);
            XWPFRun runContenidoNuevo = parrafoContenidoNuevo.createRun();
            runContenidoNuevo.setFontSize(10);
            runContenidoNuevo.setFontFamily("Times New Roman");
            runContenidoNuevo.setText("EN FECHA " + fechaRecepcionStr + ", EL PRESIDENTE DEL CENTRO ESCOLAR Y EL SUSCRITO HACEN CONSTAR QUE HA RECIBIDO DE ACUERDO A LO CONVENIDO CON EL/LA SEÑOR/A: " + encargadoOrden + ", LOS BIENES QUE A CONTINUACIÓN SE DETALLAN:");

            // Crear el párrafo del título
            XWPFParagraph separador2 = documento.createParagraph();
            separador2.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun separador2Run = separador2.createRun();
            separador2Run.setBold(true);
            separador2Run.setFontSize(10);
            separador2Run.setFontFamily("Times New Roman");
            //separador2Run.setText(" ");
            separador2Run.addBreak();

            // Crear la tabla
            XWPFTable tabla40 = documento.createTable();
            tabla40.setWidth("100%");
            tabla40.setTableAlignment(TableRowAlign.CENTER);

// Configurar bordes de la tabla
            CTTblPr tblPr40 = tabla40.getCTTbl().addNewTblPr();
            tblPr40.addNewTblBorders().addNewBottom().setVal(STBorder.SINGLE);
            tblPr40.getTblBorders().addNewLeft().setVal(STBorder.SINGLE);
            tblPr40.getTblBorders().addNewRight().setVal(STBorder.SINGLE);
            tblPr40.getTblBorders().addNewTop().setVal(STBorder.SINGLE);
            tblPr40.getTblBorders().addNewInsideH().setVal(STBorder.SINGLE);
            tblPr40.getTblBorders().addNewInsideV().setVal(STBorder.SINGLE);

// Crear el encabezado de la tabla
            String[] encabezadoTabla40 = {"No.", "Cantidad", "Unidad de medida", "Descripción/especificación técnica", "Precio Unitario", "Precio Total"};
            XWPFTableRow filaEncabezado40 = tabla40.getRow(0);

            String colorCelestePálido40 = "9EC6E6";

            for (int i = 0; i < encabezadoTabla40.length; i++) {
                XWPFTableCell celda40 = filaEncabezado40.getCell(i);
                if (celda40 == null) {
                    celda40 = filaEncabezado40.createCell();
                }
                XWPFParagraph p40 = celda40.getParagraphs().get(0);
                p40.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun r40 = p40.createRun();
                r40.setFontSize(10);
                r40.setFontFamily("Times New Roman");
                r40.setBold(true);
                r40.setText(encabezadoTabla40[i]);

                // Establecer el color de fondo
                celda40.setColor(colorCelestePálido40);
            }

// Poblar las filas con los datos
            for (DetalleOrdenVM detalle40 : importarDetalles(ordenId)) {
                XWPFTableRow fila40 = tabla40.createRow();

                // Columna No.
                XWPFTableCell celdaNo40 = fila40.getCell(0);
                if (celdaNo40 == null) {
                    celdaNo40 = fila40.createCell();
                }
                XWPFParagraph pNo40 = celdaNo40.getParagraphs().get(0);
                pNo40.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rNo40 = pNo40.createRun();
                rNo40.setFontSize(10);
                rNo40.setFontFamily("Times New Roman");
                rNo40.setText(String.valueOf(detalle40.numArticulo));

                // Columna Cantidad
                XWPFTableCell celdaCantidad40 = fila40.getCell(1);
                if (celdaCantidad40 == null) {
                    celdaCantidad40 = fila40.createCell();
                }
                XWPFParagraph pCantidad40 = celdaCantidad40.getParagraphs().get(0);
                pCantidad40.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rCantidad40 = pCantidad40.createRun();
                rCantidad40.setFontSize(10);
                rCantidad40.setFontFamily("Times New Roman");
                rCantidad40.setText(String.valueOf(detalle40.cantidad));

                // Columna Unidad de medida
                XWPFTableCell celdaUnidadMedida40 = fila40.getCell(2);
                if (celdaUnidadMedida40 == null) {
                    celdaUnidadMedida40 = fila40.createCell();
                }
                XWPFParagraph pUnidadMedida40 = celdaUnidadMedida40.getParagraphs().get(0);
                pUnidadMedida40.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rUnidadMedida40 = pUnidadMedida40.createRun();
                rUnidadMedida40.setFontSize(10);
                rUnidadMedida40.setFontFamily("Times New Roman");
                rUnidadMedida40.setText(detalle40.unidadMedida);

                // Columna Descripción/especificación técnica
                XWPFTableCell celdaDescripcion40 = fila40.getCell(3);
                if (celdaDescripcion40 == null) {
                    celdaDescripcion40 = fila40.createCell();
                }
                XWPFParagraph pDescripcion40 = celdaDescripcion40.getParagraphs().get(0);
                pDescripcion40.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rDescripcion40 = pDescripcion40.createRun();
                rDescripcion40.setFontSize(10);
                rDescripcion40.setFontFamily("Times New Roman");
                rDescripcion40.setText(detalle40.descripcionArticulo);

                // Columna Precio Unitario
                XWPFTableCell celdaPrecioUnitario40 = fila40.getCell(4);
                if (celdaPrecioUnitario40 == null) {
                    celdaPrecioUnitario40 = fila40.createCell();
                }
                XWPFParagraph pPrecioUnitario40 = celdaPrecioUnitario40.getParagraphs().get(0);
                pPrecioUnitario40.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rPrecioUnitario40 = pPrecioUnitario40.createRun();
                rPrecioUnitario40.setFontSize(10);
                rPrecioUnitario40.setFontFamily("Times New Roman");
                rPrecioUnitario40.setText(String.valueOf(detalle40.precioUnitario));

                // Columna Precio Total
                XWPFTableCell celdaPrecioTotal40 = fila40.getCell(5);
                if (celdaPrecioTotal40 == null) {
                    celdaPrecioTotal40 = fila40.createCell();
                }
                XWPFParagraph pPrecioTotal40 = celdaPrecioTotal40.getParagraphs().get(0);
                pPrecioTotal40.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rPrecioTotal40 = pPrecioTotal40.createRun();
                rPrecioTotal40.setFontSize(10);
                rPrecioTotal40.setFontFamily("Times New Roman");
                rPrecioTotal40.setText(String.valueOf(detalle40.precioTotal));
            }

            // Crear una nueva fila para el pie de la tabla
            XWPFTableRow pieTabla40 = tabla40.createRow();

// Crear celdas para las otras columnas
            for (int i = 0; i < encabezadoTabla40.length; i++) {
                XWPFTableCell celdaPie40 = pieTabla40.getCell(i);
                if (celdaPie40 == null) {
                    celdaPie40 = pieTabla40.createCell();
                }
                XWPFParagraph pPie40 = celdaPie40.getParagraphs().get(0);
                pPie40.setAlignment(ParagraphAlignment.RIGHT);
                XWPFRun rPie40 = pPie40.createRun();
                rPie40.setFontSize(10);
                rPie40.setFontFamily("Times New Roman");
                rPie40.setBold(true);
                if (i == 2) {
                    rPie40.setText("Totales"); // Tercera columna
                } else if (i == encabezadoTabla40.length - 1) {
                    rPie40.setText("$" + String.valueOf(totales)); // Última columna
                } else {
                    rPie40.setText(""); // Otras columnas, dejar en blanco
                }
            }

// Crear celda para el valor de los totales en la tercera columna
            XWPFTableCell celdaTotal40 = pieTabla40.getCell(2);
            if (celdaTotal40 == null) {
                celdaTotal40 = pieTabla40.createCell();
            }
            XWPFParagraph pTotal40 = celdaTotal40.getParagraphs().get(0);
            pTotal40.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun rTotal40 = pTotal40.createRun();
            rTotal40.setFontSize(10);
            rTotal40.setFontFamily("Times New Roman");
            rTotal40.setBold(true);
            rTotal40.setText(""); // Tercera columna

// Después de agregar todas las filas y antes de agregar la fila de totales
            XWPFTableRow ultimaFila40 = tabla40.getRow(tabla40.getNumberOfRows() - 1);

            for (int i = 0; i < encabezadoTabla40.length; i++) {
                XWPFTableCell celda40 = ultimaFila40.getCell(i);
                if (celda40 == null) {
                    celda40 = ultimaFila40.createCell();
                }
                XWPFParagraph p40 = celda40.getParagraphs().get(0);
                p40.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun r40 = p40.createRun();

                // Establecer el color de fondo
                celda40.setColor(colorCelestePálido40);
            }

// Crear el párrafo
            XWPFParagraph parrafoNuevo4030 = documento.createParagraph();
            parrafoNuevo4030.setAlignment(ParagraphAlignment.LEFT);
            parrafoNuevo4030.setSpacingAfter(200);

            // Primer run
            XWPFRun run1_40 = parrafoNuevo4030.createRun();
            run1_40.setFontFamily("Times New Roman");
            run1_40.setFontSize(10);
            run1_40.addBreak();
            run1_40.setText("RECIBIDO POR: ");
            run1_40.addBreak();
            run1_40.setText(" ");
            run1_40.addBreak();
            run1_40.setText("___________________________________");
            run1_40.addTab();
            run1_40.setText("PRESIDENTE");
            run1_40.addTab();
            run1_40.setText("FIRMA Y SELLO");
            run1_40.addBreak();
            run1_40.setText("NOMBRE");

// Segundo run
            XWPFRun run2_4030 = parrafoNuevo4030.createRun();
            run2_4030.addBreak();
            run2_4030.setFontFamily("Times New Roman");
            run2_4030.setFontSize(10);
            run2_4030.addBreak();
            run2_4030.setText("ENTREGADO POR: ");
            run2_4030.addBreak();
            run2_4030.setText(" ");
            run2_4030.addBreak();
            run2_4030.setText("___________________________________");
            run2_4030.addTab();
            run2_4030.setText("PROVEEDORA");
            run2_4030.addTab();
            run2_4030.setText("FIRMA Y SELLO.");
            run2_4030.addBreak();
            run2_4030.setText("NOMBRE");

            CTSectPr sectPr = parrafoNuevo4030.getCTP().addNewPPr().addNewSectPr();
            CTPageSz pageSize = sectPr.addNewPgSz();
            pageSize.setOrient(STPageOrientation.PORTRAIT);
            pageSize.setW(BigInteger.valueOf(11900));
            pageSize.setH(BigInteger.valueOf(16840));
            /**
             * *****************************************************************************************
             * PAGINA 5
             */
            // Añadir un salto de página antes de cambiar la orientación
            documento.createParagraph().createRun().addBreak(BreakType.PAGE);

            XWPFParagraph nuevo1ParrafoSeccion = documento.createParagraph();
            nuevo1ParrafoSeccion.setAlignment(ParagraphAlignment.LEFT);
            nuevo1ParrafoSeccion.setSpacingAfter(10);
            nuevo1ParrafoSeccion.getCTP().addNewPPr().addNewShd().setFill("9EC6E6");
            XWPFRun runTitulo41 = nuevo1ParrafoSeccion.createRun();
            runTitulo41.setBold(true);
            runTitulo41.setFontSize(12);
            runTitulo41.setFontFamily("Times New Roman");
            runTitulo41.setText("PLAN DE COMPRAS");

            XWPFParagraph plan = documento.createParagraph();
            nuevoParrafo.setAlignment(ParagraphAlignment.LEFT);
            nuevoParrafo.setSpacingAfter(50);

            XWPFRun planrun = plan.createRun();
            planrun.setFontFamily("Times New Roman");
            planrun.setFontSize(10);
            planrun.setBold(true);
            planrun.setText(" ");
            planrun.addBreak();
            planrun.setText("ORGANISMO DE ADMINISTRACION ESCOLAR: C.D.E. "+ nombreInstitucion+ ", CODIGO: " + codigo);
            planrun.addBreak();

            XWPFRun planrun2 = plan.createRun();
            planrun2.setFontFamily("Times New Roman");
            planrun2.setFontSize(10);
            planrun2.setBold(true);
            planrun2.setText("MUNICPIO: " + municipio + ", DEPARTAMENTO: CHALATENANGO, FECHA: (MES DE ANIO)");
            planrun2.addBreak();

            XWPFRun planrun3 = plan.createRun();
            planrun3.setFontFamily("Times New Roman");
            planrun3.setFontSize(10);
            planrun3.setBold(true);
            planrun3.setText("DIRECCION: (INGRESE SU DIREECCION)");
            planrun3.addBreak();
            planrun3.setText("");
            planrun3.addBreak();

            XWPFTable tablaPlan12 = documento.createTable();
            tablaPlan12.setWidth("100%");
            tablaPlan12.setTableAlignment(TableRowAlign.CENTER);

// Configurar bordes de la tabla
            CTTblPr tblPrPlan12 = tablaPlan12.getCTTbl().addNewTblPr();
            tblPrPlan12.addNewTblBorders().addNewBottom().setVal(STBorder.SINGLE);
            tblPrPlan12.getTblBorders().addNewLeft().setVal(STBorder.SINGLE);
            tblPrPlan12.getTblBorders().addNewRight().setVal(STBorder.SINGLE);
            tblPrPlan12.getTblBorders().addNewTop().setVal(STBorder.SINGLE);
            tblPrPlan12.getTblBorders().addNewInsideH().setVal(STBorder.SINGLE);
            tblPrPlan12.getTblBorders().addNewInsideV().setVal(STBorder.SINGLE);

// Crear el encabezado de la tabla
            String[] encabezadoTablaPlan12 = {"AREA DE INVERSION Y RUBROS ESPECIFICOS", "No.", "Cantidad", "Unidad de medida", "Descripción/especificación técnica", "Precio Unitario", "Precio Total", "FECHA  DE COMPRA "};
            XWPFTableRow filaEncabezadoPlan12 = tablaPlan12.getRow(0);

            String colorCelestePalidoPlan12 = "9EC6E6";

            for (int i = 0; i < encabezadoTablaPlan12.length; i++) {
                XWPFTableCell celdaPlan12 = filaEncabezadoPlan12.getCell(i);
                if (celdaPlan12 == null) {
                    celdaPlan12 = filaEncabezadoPlan12.createCell();
                }
                XWPFParagraph pPlan12 = celdaPlan12.getParagraphs().get(0);
                pPlan12.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rPlan12 = pPlan12.createRun();
                rPlan12.setFontSize(10);
                rPlan12.setFontFamily("Times New Roman");
                rPlan12.setBold(true);
                rPlan12.setText(encabezadoTablaPlan12[i]);

                // Establecer el color de fondo
                celdaPlan12.setColor(colorCelestePalidoPlan12);
            }

// Poblar las filas con los datos
            for (DetalleOrdenVM detallePlan12 : importarDetalles(ordenId)) {
                XWPFTableRow filaPlan12 = tablaPlan12.createRow();

                // Columna No.
                XWPFTableCell celdaNoPlan12 = filaPlan12.getCell(1);
                if (celdaNoPlan12 == null) {
                    celdaNoPlan12 = filaPlan12.createCell();
                }
                XWPFParagraph pNoPlan12 = celdaNoPlan12.getParagraphs().get(0);
                pNoPlan12.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rNoPlan12 = pNoPlan12.createRun();
                rNoPlan12.setFontSize(10);
                rNoPlan12.setFontFamily("Times New Roman");
                rNoPlan12.setText(String.valueOf(detallePlan12.numArticulo));

                // Columna Cantidad
                XWPFTableCell celdaCantidadPlan12 = filaPlan12.getCell(2);
                if (celdaCantidadPlan12 == null) {
                    celdaCantidadPlan12 = filaPlan12.createCell();
                }
                XWPFParagraph pCantidadPlan12 = celdaCantidadPlan12.getParagraphs().get(0);
                pCantidadPlan12.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rCantidadPlan12 = pCantidadPlan12.createRun();
                rCantidadPlan12.setFontSize(10);
                rCantidadPlan12.setFontFamily("Times New Roman");
                rCantidadPlan12.setText(String.valueOf(detallePlan12.cantidad));

                // Columna Unidad de medida
                XWPFTableCell celdaUnidadMedidaPlan12 = filaPlan12.getCell(3);
                if (celdaUnidadMedidaPlan12 == null) {
                    celdaUnidadMedidaPlan12 = filaPlan12.createCell();
                }
                XWPFParagraph pUnidadMedidaPlan12 = celdaUnidadMedidaPlan12.getParagraphs().get(0);
                pUnidadMedidaPlan12.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rUnidadMedidaPlan12 = pUnidadMedidaPlan12.createRun();
                rUnidadMedidaPlan12.setFontSize(10);
                rUnidadMedidaPlan12.setFontFamily("Times New Roman");
                rUnidadMedidaPlan12.setText(detallePlan12.unidadMedida);

                // Columna Descripción/especificación técnica
                XWPFTableCell celdaDescripcionPlan12 = filaPlan12.getCell(4);
                if (celdaDescripcionPlan12 == null) {
                    celdaDescripcionPlan12 = filaPlan12.createCell();
                }
                XWPFParagraph pDescripcionPlan12 = celdaDescripcionPlan12.getParagraphs().get(0);
                pDescripcionPlan12.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rDescripcionPlan12 = pDescripcionPlan12.createRun();
                rDescripcionPlan12.setFontSize(10);
                rDescripcionPlan12.setFontFamily("Times New Roman");
                rDescripcionPlan12.setText(detallePlan12.descripcionArticulo);

                // Columna Precio Unitario
                XWPFTableCell celdaPrecioUnitarioPlan12 = filaPlan12.getCell(5);
                if (celdaPrecioUnitarioPlan12 == null) {
                    celdaPrecioUnitarioPlan12 = filaPlan12.createCell();
                }
                XWPFParagraph pPrecioUnitarioPlan12 = celdaPrecioUnitarioPlan12.getParagraphs().get(0);
                pPrecioUnitarioPlan12.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rPrecioUnitarioPlan12 = pPrecioUnitarioPlan12.createRun();
                rPrecioUnitarioPlan12.setFontSize(10);
                rPrecioUnitarioPlan12.setFontFamily("Times New Roman");
                rPrecioUnitarioPlan12.setText(String.valueOf(detallePlan12.precioUnitario));

                // Columna Precio Total
                XWPFTableCell celdaPrecioTotalPlan12 = filaPlan12.getCell(6);
                if (celdaPrecioTotalPlan12 == null) {
                    celdaPrecioTotalPlan12 = filaPlan12.createCell();
                }
                XWPFParagraph pPrecioTotalPlan12 = celdaPrecioTotalPlan12.getParagraphs().get(0);
                pPrecioTotalPlan12.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rPrecioTotalPlan12 = pPrecioTotalPlan12.createRun();
                rPrecioTotalPlan12.setFontSize(10);
                rPrecioTotalPlan12.setFontFamily("Times New Roman");
                rPrecioTotalPlan12.setText(String.valueOf(detallePlan12.precioTotal));
            }

            XWPFTableRow filaPieTablaPlan12 = tablaPlan12.createRow();

// Crear celdas para las otras columnas
            for (int i = 0; i < encabezadoTablaPlan12.length; i++) {
                XWPFTableCell celdaPiePlan12 = filaPieTablaPlan12.getCell(i);
                if (celdaPiePlan12 == null) {
                    celdaPiePlan12 = filaPieTablaPlan12.createCell();
                }
                XWPFParagraph pPiePlan12 = celdaPiePlan12.getParagraphs().get(0);
                pPiePlan12.setAlignment(ParagraphAlignment.RIGHT);
                XWPFRun rPiePlan12 = pPiePlan12.createRun();
                rPiePlan12.setFontSize(10);
                rPiePlan12.setFontFamily("Times New Roman");
                rPiePlan12.setBold(true);
                if (i == 3) {
                    rPiePlan12.setText("Totales"); // Cuarta columna
                } else if (i == encabezadoTablaPlan12.length - 2) {
                    rPiePlan12.setText("$" + String.valueOf(totales)); // Penúltima columna
                } else {
                    rPiePlan12.setText(""); // Otras columnas, dejar en blanco
                }
            }

// Crear celda para el valor de los totales en la penúltima columna
            XWPFTableCell celdaTotalPlan12 = filaPieTablaPlan12.getCell(encabezadoTablaPlan12.length - 2);
            if (celdaTotalPlan12 == null) {
                celdaTotalPlan12 = filaPieTablaPlan12.createCell();
            }
            XWPFParagraph pTotalPlan12 = celdaTotalPlan12.getParagraphs().get(0);
            pTotalPlan12.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun rTotalPlan12 = pTotalPlan12.createRun();
            rTotalPlan12.setFontSize(10);
            rTotalPlan12.setFontFamily("Times New Roman");
            rTotalPlan12.setBold(true);
            rTotalPlan12.setText(""); // Penúltima columna

// Después de agregar todas las filas y antes de agregar la fila de totales
            XWPFTableRow ultimaFilaPlan12 = tablaPlan12.getRow(tablaPlan12.getNumberOfRows() - 1);

            for (int i = 0; i < encabezadoTablaPlan12.length; i++) {
                XWPFTableCell celdaUltimaPlan12 = ultimaFilaPlan12.getCell(i);
                if (celdaUltimaPlan12 == null) {
                    celdaUltimaPlan12 = ultimaFilaPlan12.createCell();
                }
                XWPFParagraph pUltimaPlan12 = celdaUltimaPlan12.getParagraphs().get(0);
                pUltimaPlan12.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rUltimaPlan12 = pUltimaPlan12.createRun();

                // Establecer el color de fondo
                celdaUltimaPlan12.setColor(colorCelestePalidoPlan12);
            }

            XWPFParagraph presidente = documento.createParagraph();
            XWPFParagraph tesorero = documento.createParagraph();
            XWPFParagraph consejalDocente = documento.createParagraph();
            XWPFParagraph secretario = documento.createParagraph();
            XWPFParagraph consejalPadre1 = documento.createParagraph();
            XWPFParagraph consejalPadre2 = documento.createParagraph();
            XWPFParagraph consejalAlumno1 = documento.createParagraph();
            XWPFParagraph consejalAlumno2 = documento.createParagraph();

// Agregar texto y espacios en blanco para llenar los espacios en la línea
            presidente.setAlignment(ParagraphAlignment.LEFT);
            presidente.createRun().setText(" ");
            presidente.createRun().addBreak();
            presidente.setSpacingAfter(0);
            presidente.createRun().setText("NOMBRE DE PRESIDENTE/A PROPIETARIO _______________________________");
            presidente.createRun().addTab();
            presidente.createRun().addTab();
            presidente.createRun().addTab();
            
            presidente.createRun().setText("F.__________________________");

            tesorero.setAlignment(ParagraphAlignment.LEFT);
            tesorero.setSpacingAfter(0);
            tesorero.createRun().setText("NOMBRE DE TESORERO/A PROPIETARIO _________________________________");
            tesorero.createRun().addTab(); // Espacio para la firma
            tesorero.createRun().setText("SELLO CDE");
            tesorero.createRun().addTab();
            
            tesorero.createRun().setText("F.__________________________");

            consejalDocente.setAlignment(ParagraphAlignment.LEFT);
            consejalDocente.setSpacingAfter(0);
            consejalDocente.createRun().setText("NOMBRE DE CONSEJAL PROPIETARIO DOCENTE __________________________");
            consejalDocente.createRun().addTab();
            consejalDocente.createRun().addTab();
            consejalDocente.createRun().addTab();
            
            consejalDocente.createRun().setText("F.__________________________");

            secretario.setAlignment(ParagraphAlignment.LEFT);
            secretario.setSpacingAfter(0);
            secretario.createRun().setText("NOMBRE DE SECRETARIO PROPIETARIO ________________________________");
            secretario.createRun().addTab();
            secretario.createRun().addTab();
            secretario.createRun().addTab();
            secretario.createRun().addTab();
            
            secretario.createRun().setText("F.__________________________");

            consejalPadre1.setAlignment(ParagraphAlignment.LEFT);
            consejalPadre1.setSpacingAfter(0);
            consejalPadre1.createRun().setText("NOMBRE DE CONSEJAL PROPIETARIO PADRE DE FAM.______________________");
            consejalPadre1.createRun().addTab();
            consejalPadre1.createRun().addTab();
            consejalPadre1.createRun().addTab();
            
            consejalPadre1.createRun().setText("F.__________________________");

            consejalPadre2.setAlignment(ParagraphAlignment.LEFT);
            consejalPadre2.setSpacingAfter(0);
            consejalPadre2.createRun().setText("NOMBRE DE CONSEJAL PROPIETARIO PADRE DE FAM.______________________");
            consejalPadre2.createRun().addTab();
            consejalPadre2.createRun().addTab();
            consejalPadre2.createRun().addTab();
            
            consejalPadre2.createRun().setText("F.__________________________");

            consejalAlumno1.setAlignment(ParagraphAlignment.LEFT);
            consejalAlumno1.setSpacingAfter(0);
            consejalAlumno1.createRun().setText("NOMBRE DE CONSEJAL PROPIETARIO ALUMNO ____________________________");
            consejalAlumno1.createRun().addTab();
            consejalAlumno1.createRun().addTab();
            consejalAlumno1.createRun().addTab();
            
            consejalAlumno1.createRun().setText("F.__________________________");

            consejalAlumno2.setAlignment(ParagraphAlignment.LEFT);
            consejalAlumno2.setSpacingAfter(0);
            consejalAlumno2.createRun().setText("NOMBRE DE CONSEJAL PROPIETARIO ALUMNO____________________________");
            consejalAlumno2.createRun().addTab();
            consejalAlumno2.createRun().addTab();
            consejalAlumno2.createRun().addTab();
            
            consejalAlumno2.createRun().setText("F.__________________________");

            XWPFParagraph ultimoParrafo = documento.createParagraph();
            ultimoParrafo.setAlignment(ParagraphAlignment.LEFT);
            ultimoParrafo.setSpacingAfter(50);

            XWPFRun runUltimo = ultimoParrafo.createRun();
            runUltimo.setFontFamily("Times New Roman");
            runUltimo.setFontSize(10);
            runUltimo.setBold(true);
            runUltimo.setText(" ");
            runUltimo.addBreak();
            runUltimo.setText("Nota: ");

            XWPFRun runultimo2 = ultimoParrafo.createRun();
            runultimo2.setFontFamily("Times New Roman");
            runultimo2.setFontSize(10);
            runultimo2.setText("El Plan de Compras deberá contener nombre, "
                    + "firmas y cargo de todas las personas integrantes "
                    + "propietarias del organismo, para su aprobación y "
                    + "sello correspondiententes propietarias del organismo, "
                    + "para su aprobación y sello correspondiente");

            CTSectPr nuevoSectPr = ultimoParrafo.getCTP().addNewPPr().addNewSectPr();
            CTPageSz nuevoPageSize = nuevoSectPr.addNewPgSz();
            nuevoPageSize.setOrient(STPageOrientation.LANDSCAPE);
            nuevoPageSize.setW(BigInteger.valueOf(16840));
            nuevoPageSize.setH(BigInteger.valueOf(11900));

            /**
             * ***************************************************
             */
            // Obtener la fecha actual
            LocalDate currentDate = LocalDate.now();

            String dateString = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
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

    private static void removePage(XWPFDocument document, int pageNumber) {
        int currentPage = 1;
        int paragraphCount = 0;

        for (XWPFParagraph paragraph : document.getParagraphs()) {
            paragraphCount++;
            // Si el número de página es el que queremos eliminar, eliminamos todos los párrafos a partir de aquí
            if (currentPage == pageNumber) {
                for (int i = paragraphCount - 1; i >= 0; i--) {
                    document.removeBodyElement(i);
                }
                return;
            }
            // Contamos los saltos de página
            if (paragraph.getText().contains("\f")) {
                currentPage++;
            }
        }
    }
}
