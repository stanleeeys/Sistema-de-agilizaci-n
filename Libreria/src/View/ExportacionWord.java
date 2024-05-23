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
import javax.swing.JOptionPane;
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
            double totales = ordenVM.getTotales();
            int clienteId = ordenVM.getClienteId();
            int proveedorId = ordenVM.getProveedorId();
            int fechasErId = ordenVM.getFechasErId();
            String codOrden = ordenVM.getCodOrden().toUpperCase();
            String encargadoOrden = ordenVM.getEncargadoOrden().toUpperCase();
            String codigo = ordenVM.getCodigo_escuela().toUpperCase();
            String encargadoCompra = ordenVM.getEncargadoCompra().toUpperCase();
            String nombreInstitucion = ordenVM.getNombreInstitucion().toUpperCase();
            String municipio = ordenVM.getMunicipio().toUpperCase();
            String nombreProveedor = ordenVM.getNombreProveedor().toUpperCase();
            String desde = ordenVM.getHora_entrega_desde().toUpperCase();
            String hasta = ordenVM.getHora_entrega_hasta().toUpperCase();
            String tiempo_entrega = ordenVM.getTiempo_entrega().toUpperCase();
            String plazo_entrega = ordenVM.getPlazo_entrega().toUpperCase();
            String lugar_entrega = ordenVM.getLugar_entrega().toUpperCase();
            String vigencia_de_la_cotizacion = ordenVM.getLugar_entrega().toUpperCase();
            String tiempo_de_garantia = ordenVM.getTiempo_de_garantia().toUpperCase();
            SimpleDateFormat formatter = new SimpleDateFormat("d 'DE' MMMM 'DE' yyyy", new Locale("es", "ES"));
            String fechaSolicitudStr = formatter.format(ordenVM.getFechaSolicitud()).toUpperCase();
            String fechaCotizacionStr = formatter.format(ordenVM.getFechaCotizacion()).toUpperCase();
            String fechaOrdenStr = formatter.format(ordenVM.getFechaOrden()).toUpperCase();
            String fechaRecepcionStr = formatter.format(ordenVM.getFechaRecepcion()).toUpperCase();
            String fechaPlanComprasStr = formatter.format(ordenVM.getFechaPlanCompras()).toUpperCase();
            String limite_cotizacion = formatter.format(ordenVM.getLimite_cotizacion()).toUpperCase();
            String fecha_de_entrega = formatter.format(ordenVM.getFecha_de_entrega()).toUpperCase();

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
            nuevoParrafo.setAlignment(ParagraphAlignment.LEFT);
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
            nuevoParrafo1.setSpacingAfter(70);

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

            CTTblPr tblPr = tabla.getCTTbl().addNewTblPr();
            tblPr.addNewTblBorders().addNewBottom().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewLeft().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewRight().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewTop().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewInsideH().setVal(STBorder.SINGLE);
            tblPr.getTblBorders().addNewInsideV().setVal(STBorder.SINGLE);

            String[] encabezadoTabla = {"No.", "Cantidad", "Unidad de medida", "Descripción/especificación técnica"};
            XWPFTableRow filaEncabezado = tabla.getRow(0);

            int[] anchurasColumnas = {1440, 2880, 2880, 8640};

            for (int i = 0; i < encabezadoTabla.length; i++) {
                XWPFTableCell celda = filaEncabezado.getCell(i);
                if (celda == null) {
                    celda = filaEncabezado.createCell();
                }
                celda.setWidth(String.valueOf(anchurasColumnas[i]));

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

                for (int i = 0; i < encabezadoTabla.length; i++) {
                    XWPFTableCell celda = fila.getCell(i);
                    if (celda == null) {
                        celda = fila.createCell();
                    }

                    celda.setWidth(String.valueOf(anchurasColumnas[i]));

                    XWPFParagraph p = celda.getParagraphs().get(0);
                    p.setAlignment(ParagraphAlignment.LEFT);
                    XWPFRun r = p.createRun();
                    r.setFontSize(10);
                    r.setFontFamily("Times New Roman");

                    switch (i) {
                        case 0: // No.
                            r.setText(String.valueOf(detalle.numArticulo));
                            break;
                        case 1: // Cantidad
                            r.setText(String.valueOf(detalle.cantidad));
                            break;
                        case 2: // Unidad de medida
                            r.setText(detalle.unidadMedida);
                            break;
                        case 3: // Descripción/especificación técnica
                            r.setText(detalle.descripcionArticulo);
                            break;
                        default:
                            break;
                    }
                }
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
            parrafoNuevo1.setAlignment(ParagraphAlignment.LEFT);
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
            runNuevo3.setText(",  suscrita por el CDE que administra la/el ");

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

// Anchuras de columna deseadas (en unidades TWIP, 1 pulgada = 1440 unidades TWIP)
            int[] anchurasColumnas20 = {1440, 1440, 1440, 4320, 1440, 1440}; // Por ejemplo

            for (int i = 0; i < encabezadoTabla20.length; i++) {
                XWPFTableCell celda20 = filaEncabezado20.getCell(i);
                if (celda20 == null) {
                    celda20 = filaEncabezado20.createCell();
                }

                // Establecer el ancho de la celda de acuerdo con el arreglo anchurasColumnas20
                celda20.setWidth(String.valueOf(anchurasColumnas20[i]));

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

// Iterar sobre los detalles de la orden
            for (DetalleOrdenVM detalle20 : importarDetalles(ordenId)) {
                XWPFTableRow fila20 = tabla20.createRow();

                // Iterar sobre las columnas de la fila
                for (int i = 0; i < encabezadoTabla20.length; i++) {
                    XWPFTableCell celda20 = fila20.getCell(i);
                    if (celda20 == null) {
                        celda20 = fila20.createCell();
                    }

                    // Establecer el ancho de la celda de acuerdo con el arreglo anchurasColumnas20
                    celda20.setWidth(String.valueOf(anchurasColumnas20[i]));

                    XWPFParagraph p20 = celda20.getParagraphs().get(0);
                    p20.setAlignment(ParagraphAlignment.LEFT);
                    XWPFRun r20 = p20.createRun();
                    r20.setFontSize(10);
                    r20.setFontFamily("Times New Roman");

                    // Asignar el texto correspondiente del detalle a la celda
                    switch (i) {
                        case 0: // No.
                            r20.setText(String.valueOf(detalle20.numArticulo));
                            break;
                        case 1: // Cantidad
                            r20.setText(String.valueOf(detalle20.cantidad));
                            break;
                        case 2: // Unidad de medida
                            r20.setText(detalle20.unidadMedida);
                            break;
                        case 3: // Descripción/especificación técnica
                            r20.setText(detalle20.descripcionArticulo);
                            break;
                        case 4: // Precio Unitario
                            r20.setText(String.valueOf("$" + detalle20.precioUnitario));
                            break;
                        case 5: // Precio Total
                            r20.setText(String.valueOf("$" + detalle20.precioTotal));
                            break;
                        default:
                            break;
                    }
                }
            }

// Crear la fila del pie de tabla
            XWPFTableRow pieTabla = tabla20.createRow();

// Crear celdas para las otras columnas
            for (int i = 0; i < encabezadoTabla20.length; i++) {
                XWPFTableCell celdaPie = pieTabla.getCell(i);
                if (celdaPie == null) {
                    celdaPie = pieTabla.createCell();
                }

                // Establecer el ancho de la celda de acuerdo con el arreglo anchurasColumnas20
                celdaPie.setWidth(String.valueOf(anchurasColumnas20[i]));

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

// Crear celda para el valor de los totales en la última columna
            XWPFTableCell celdaTotal = pieTabla.getCell(encabezadoTabla20.length - 1);
            if (celdaTotal == null) {
                celdaTotal = pieTabla.createCell();
            }
            celdaTotal.setWidth(String.valueOf(anchurasColumnas20[encabezadoTabla20.length - 1]));

            XWPFParagraph pTotal = celdaTotal.getParagraphs().get(0);
            pTotal.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun rTotal = pTotal.createRun();
            rTotal.setFontSize(10);
            rTotal.setFontFamily("Times New Roman");
            rTotal.setBold(true);
            rTotal.setText(""); // Última columna

// Después de agregar todas las filas y antes de agregar la fila de totales
            XWPFTableRow ultimaFila = tabla20.getRow(tabla20.getNumberOfRows() - 1);

            for (int i = 0; i < encabezadoTabla20.length; i++) {
                XWPFTableCell celda = ultimaFila.getCell(i);
                if (celda == null) {
                    celda = ultimaFila.createCell();
                }

                // Establecer el ancho de la celda de acuerdo con el arreglo anchurasColumnas20
                celda.setWidth(String.valueOf(anchurasColumnas20[i]));

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

            XWPFParagraph parrafoNuevo301 = documento.createParagraph();
            parrafoNuevo301.setAlignment(ParagraphAlignment.LEFT);
            parrafoNuevo301.setSpacingAfter(50);

            XWPFRun runNuevo301 = parrafoNuevo301.createRun();
            runNuevo301.setFontFamily("Times New Roman");
            runNuevo301.setFontSize(10);
            runNuevo301.setText("Por este medio se comunica que el  CDE de el/la ");

            XWPFRun runNuevo302 = parrafoNuevo301.createRun();
            runNuevo302.setFontFamily("Times New Roman");
            runNuevo302.setFontSize(10);
            runNuevo302.setBold(true);
            runNuevo302.setText(nombreInstitucion);

            XWPFRun runNuevo303 = parrafoNuevo301.createRun();
            runNuevo303.setFontFamily("Times New Roman");
            runNuevo303.setFontSize(10);
            runNuevo303.setText(", ha decidido adquirir en esa empresa los "
                    + "bienes o servicios que más adelante se detallan, "
                    + "de acuerdo a los precios y condiciones de su  "
                    + "oferta del día ");

// Parte con el nombre de la institución en negrita
            XWPFRun runNuevo304 = parrafoNuevo301.createRun();
            runNuevo304.setFontFamily("Times New Roman");
            runNuevo304.setFontSize(10);
            runNuevo304.setBold(true);
            runNuevo304.setText(fechaCotizacionStr + ".");
            runNuevo304.addBreak();

// Parte ", ubicado en el Municipio de "
            XWPFRun runNuevo305 = parrafoNuevo301.createRun();
            runNuevo305.setFontFamily("Times New Roman");
            runNuevo305.setFontSize(10);
            runNuevo305.setText("Los bienes y/o servicios deberán entregarse  en el/la ");

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
            runNuevo307.setText(" EL DIA " + fecha_de_entrega + ", DE LAS " + desde + " HORAS A LAS " + hasta + " HORAS DE LA " + tiempo_entrega);
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

// Color celeste pálido
            String colorCelestePálido30 = "9EC6E6";

// Anchuras de columna deseadas (en unidades TWIP, 1 pulgada = 1440 unidades TWIP)
            int[] anchurasColumnas3020 = {1440, 1440, 1440, 4320, 1440, 1440}; // Mismos anchos que el encabezado anterior

            for (int i = 0; i < encabezadoTabla3020.length; i++) {
                XWPFTableCell celdaEncabezado3020 = filaEncabezado3020.getCell(i);
                if (celdaEncabezado3020 == null) {
                    celdaEncabezado3020 = filaEncabezado3020.createCell();
                }
                celdaEncabezado3020.setWidth(String.valueOf(anchurasColumnas3020[i]));

                XWPFParagraph pEncabezado3020 = celdaEncabezado3020.getParagraphs().get(0);
                pEncabezado3020.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rEncabezado3020 = pEncabezado3020.createRun();
                rEncabezado3020.setFontSize(10);
                rEncabezado3020.setFontFamily("Times New Roman");
                rEncabezado3020.setBold(true);
                rEncabezado3020.setText(encabezadoTabla3020[i]);

                celdaEncabezado3020.setColor(colorCelestePálido30);
            }

// Iterar sobre los detalles de la orden
            for (DetalleOrdenVM detalle3020 : importarDetalles(ordenId)) {
                XWPFTableRow fila3020 = tabla3020.createRow();

                // Iterar sobre las columnas de la fila
                for (int i = 0; i < encabezadoTabla3020.length; i++) {
                    XWPFTableCell celda3020 = fila3020.getCell(i);
                    if (celda3020 == null) {
                        celda3020 = fila3020.createCell();
                    }

                    // Establecer el ancho de la celda de acuerdo con el arreglo anchurasColumnas3020
                    celda3020.setWidth(String.valueOf(anchurasColumnas3020[i]));

                    XWPFParagraph p3020 = celda3020.getParagraphs().get(0);
                    p3020.setAlignment(ParagraphAlignment.LEFT);
                    XWPFRun r3020 = p3020.createRun();
                    r3020.setFontSize(10);
                    r3020.setFontFamily("Times New Roman");

                    // Asignar el texto correspondiente del detalle a la celda
                    switch (i) {
                        case 0: // No.
                            r3020.setText(String.valueOf(detalle3020.numArticulo));
                            break;
                        case 1: // Cantidad
                            r3020.setText(String.valueOf(detalle3020.cantidad));
                            break;
                        case 2: // Unidad de medida
                            r3020.setText(detalle3020.unidadMedida);
                            break;
                        case 3: // Descripción/especificación técnica
                            r3020.setText(detalle3020.descripcionArticulo);
                            break;
                        case 4: // Precio Unitario
                            r3020.setText(String.valueOf("$" + detalle3020.precioUnitario));
                            break;
                        case 5: // Precio Total
                            r3020.setText(String.valueOf("$" + detalle3020.precioTotal));
                            break;
                        default:
                            break;
                    }
                }
            }

// Crear la fila del pie de tabla
            XWPFTableRow pieTabla30 = tabla3020.createRow();

// Crear celdas para las otras columnas
            for (int i = 0; i < encabezadoTabla3020.length; i++) {
                XWPFTableCell celdaPie30 = pieTabla30.getCell(i);
                if (celdaPie30 == null) {
                    celdaPie30 = pieTabla30.createCell();
                }
                celdaPie30.setWidth(String.valueOf(anchurasColumnas3020[i]));

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
            celdaTotal30.setWidth(String.valueOf(anchurasColumnas3020[2]));

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
                celda30.setWidth(String.valueOf(anchurasColumnas3020[i]));

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

            XWPFParagraph parrafoNuevo3020 = documento.createParagraph();
            parrafoNuevo3020.setAlignment(ParagraphAlignment.LEFT);
            parrafoNuevo3020.setSpacingAfter(200);

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

            XWPFTableCell celdaContenido = filaContenido.getCell(0);
            if (celdaContenido == null) {
                celdaContenido = filaContenido.createCell();
            }
            XWPFParagraph parrafoContenido = celdaContenido.getParagraphs().get(0);
            parrafoContenido.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun runContenido = parrafoContenido.createRun();
            runContenido.setFontSize(10);
            runContenido.setFontFamily("Times New Roman");
            runContenido.setText("EL ORGANISMO DE ADMINISTRACION ESCOLAR: CONSEJO DIRECTIVO ESCOLAR QUE ADMINISTRA EL/LA " + nombreInstitucion + " EN EL MUNICIPIO: " + municipio + ", CODIGO: " + codigo + ", DEPARTAMENTO DE CHALATENANGO.");

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
            parrafoContenidoNuevo.setAlignment(ParagraphAlignment.LEFT);
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

// Color celeste pálido
            String colorCelestePálido40 = "9EC6E6";

// Anchuras de columna deseadas (en unidades TWIP, 1 pulgada = 1440 unidades TWIP)
            int[] anchurasColumnas40 = {1440, 1440, 1440, 4320, 1440, 1440}; // Mismos anchos que el encabezado anterior

            for (int i = 0; i < encabezadoTabla40.length; i++) {
                XWPFTableCell celdaEncabezado40 = filaEncabezado40.getCell(i);
                if (celdaEncabezado40 == null) {
                    celdaEncabezado40 = filaEncabezado40.createCell();
                }
                celdaEncabezado40.setWidth(String.valueOf(anchurasColumnas40[i]));

                XWPFParagraph pEncabezado40 = celdaEncabezado40.getParagraphs().get(0);
                pEncabezado40.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun rEncabezado40 = pEncabezado40.createRun();
                rEncabezado40.setFontSize(10);
                rEncabezado40.setFontFamily("Times New Roman");
                rEncabezado40.setBold(true);
                rEncabezado40.setText(encabezadoTabla40[i]);

                celdaEncabezado40.setColor(colorCelestePálido40);
            }

// Iterar sobre los detalles de la orden
            for (DetalleOrdenVM detalle40 : importarDetalles(ordenId)) {
                XWPFTableRow fila40 = tabla40.createRow();

                // Iterar sobre las columnas de la fila
                for (int i = 0; i < encabezadoTabla40.length; i++) {
                    XWPFTableCell celda40 = fila40.getCell(i);
                    if (celda40 == null) {
                        celda40 = fila40.createCell();
                    }

                    // Establecer el ancho de la celda de acuerdo con el arreglo anchurasColumnas40
                    celda40.setWidth(String.valueOf(anchurasColumnas40[i]));

                    XWPFParagraph p40 = celda40.getParagraphs().get(0);
                    p40.setAlignment(ParagraphAlignment.LEFT);
                    XWPFRun r40 = p40.createRun();
                    r40.setFontSize(10);
                    r40.setFontFamily("Times New Roman");

                    // Asignar el texto correspondiente del detalle a la celda
                    switch (i) {
                        case 0: // No.
                            r40.setText(String.valueOf(detalle40.numArticulo));
                            break;
                        case 1: // Cantidad
                            r40.setText(String.valueOf(detalle40.cantidad));
                            break;
                        case 2: // Unidad de medida
                            r40.setText(detalle40.unidadMedida);
                            break;
                        case 3: // Descripción/especificación técnica
                            r40.setText(detalle40.descripcionArticulo);
                            break;
                        case 4: // Precio Unitario
                            r40.setText(String.valueOf("$" + detalle40.precioUnitario));
                            break;
                        case 5: // Precio Total
                            r40.setText(String.valueOf("$" + detalle40.precioTotal));
                            break;
                        default:
                            break;
                    }
                }
            }

// Crear una nueva fila para el pie de la tabla
            XWPFTableRow pieTabla40 = tabla40.createRow();

// Crear celdas para las otras columnas
            for (int i = 0; i < encabezadoTabla40.length; i++) {
                XWPFTableCell celdaPie40 = pieTabla40.getCell(i);
                if (celdaPie40 == null) {
                    celdaPie40 = pieTabla40.createCell();
                }
                celdaPie40.setWidth(String.valueOf(anchurasColumnas40[i]));

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
            celdaTotal40.setWidth(String.valueOf(anchurasColumnas40[2]));

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
                celda40.setWidth(String.valueOf(anchurasColumnas40[i]));

                XWPFParagraph p40 = celda40.getParagraphs().get(0);
                p40.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun r40 = p40.createRun();

                // Establecer el color de fondo
                celda40.setColor(colorCelestePálido40);
            }

// Crear el párrafo
            XWPFParagraph parrafoNuevo4030 = documento.createParagraph();
            parrafoNuevo4030.setAlignment(ParagraphAlignment.LEFT);
            //parrafoNuevo4030.setSpacingAfter(200);

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
            nuevo1ParrafoSeccion.setAlignment(ParagraphAlignment.CENTER);
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
            planrun.setText("ORGANISMO DE ADMINISTRACION ESCOLAR: C.D.E. " + nombreInstitucion + ", CODIGO: " + codigo);
            planrun.addBreak();

            XWPFRun planrun2 = plan.createRun();
            planrun2.setFontFamily("Times New Roman");
            planrun2.setFontSize(10);
            planrun2.setBold(true);
            planrun2.setText("MUNICPIO: " + municipio + ", DEPARTAMENTO: CHALATENANGO, FECHA: " + fechaPlanComprasStr);
            planrun2.addBreak();

            XWPFRun planrun3 = plan.createRun();
            planrun3.setFontFamily("Times New Roman");
            planrun3.setFontSize(10);
            planrun3.setBold(true);
            planrun3.setText("DIRECCION: (INGRESE SU DIREECCION)");
            planrun3.addBreak();
            planrun3.setText("");
            planrun3.addBreak();

            // Crear la tabla
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
            String[] encabezadoTablaPlan12 = {"AREA DE INVERSION Y RUBROS ESPECIFICOS", "No.", "Cantidad", "Unidad de medida", "Descripción/especificación técnica", "Precio Unitario", "Precio Total", "FECHA DE COMPRA"};
            XWPFTableRow filaEncabezadoPlan12 = tablaPlan12.getRow(0);

// Color celeste pálido
            String colorCelestePalidoPlan12 = "9EC6E6";

// Anchuras de columna deseadas (en unidades TWIP, 1 pulgada = 1440 unidades TWIP)
            int[] anchurasColumnasPlan12 = {1440, 700, 700, 700, 4320, 700, 700, 1440}; // Por ejemplo

            for (int i = 0; i < encabezadoTablaPlan12.length; i++) {
                XWPFTableCell celdaPlan12 = filaEncabezadoPlan12.getCell(i);
                if (celdaPlan12 == null) {
                    celdaPlan12 = filaEncabezadoPlan12.createCell();
                }

                // Establecer el ancho de la celda de acuerdo con el arreglo anchurasColumnasPlan12
                celdaPlan12.setWidth(String.valueOf(anchurasColumnasPlan12[i]));

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

// Iterar sobre los detalles de la orden
            for (DetalleOrdenVM detallePlan12 : importarDetalles(ordenId)) {
                XWPFTableRow filaPlan12 = tablaPlan12.createRow();

                // Iterar sobre las columnas de la fila
                for (int i = 0; i < encabezadoTablaPlan12.length; i++) {
                    XWPFTableCell celdaPlan12 = filaPlan12.getCell(i);
                    if (celdaPlan12 == null) {
                        celdaPlan12 = filaPlan12.createCell();
                    }

                    // Establecer el ancho de la celda de acuerdo con el arreglo anchurasColumnasPlan12
                    celdaPlan12.setWidth(String.valueOf(anchurasColumnasPlan12[i]));

                    XWPFParagraph pPlan12 = celdaPlan12.getParagraphs().get(0);
                    pPlan12.setAlignment(ParagraphAlignment.LEFT);
                    XWPFRun rPlan12 = pPlan12.createRun();
                    rPlan12.setFontSize(10);
                    rPlan12.setFontFamily("Times New Roman");

                    // Asignar el texto correspondiente del detalle a la celda
                    switch (i) {
                        case 0: // AREA DE INVERSION Y RUBROS ESPECIFICOS (Dejar vacío o agregar un valor si corresponde)
                            rPlan12.setText(" "); // Suponiendo que hay un campo 'areaInversion'
                            break;
                        case 1: // No.
                            rPlan12.setText(String.valueOf(detallePlan12.numArticulo));
                            break;
                        case 2: // Cantidad
                            rPlan12.setText(String.valueOf(detallePlan12.cantidad));
                            break;
                        case 3: // Unidad de medida
                            rPlan12.setText(detallePlan12.unidadMedida);
                            break;
                        case 4: // Descripción/especificación técnica
                            rPlan12.setText(detallePlan12.descripcionArticulo);
                            break;
                        case 5: // Precio Unitario
                            rPlan12.setText(String.valueOf("$" + detallePlan12.precioUnitario));
                            break;
                        case 6: // Precio Total
                            rPlan12.setText(String.valueOf("$" + detallePlan12.precioTotal));
                            break;
                        case 7: // FECHA DE COMPRA (Dejar vacío o agregar un valor si corresponde)
                            rPlan12.setText(" "); // Suponiendo que hay un campo 'fechaCompra'
                            break;
                        default:
                            break;
                    }
                }
            }

            // Crear la fila del pie de tabla
            XWPFTableRow filaPieTablaPlan12 = tablaPlan12.createRow();

// Crear celdas para las otras columnas
            for (int i = 0; i < encabezadoTablaPlan12.length; i++) {
                XWPFTableCell celdaPiePlan12 = filaPieTablaPlan12.getCell(i);
                if (celdaPiePlan12 == null) {
                    celdaPiePlan12 = filaPieTablaPlan12.createCell();
                }

                // Establecer el ancho de la celda de acuerdo con el arreglo anchurasColumnasPlan12
                celdaPiePlan12.setWidth(String.valueOf(anchurasColumnasPlan12[i]));

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
            celdaTotalPlan12.setWidth(String.valueOf(anchurasColumnasPlan12[encabezadoTablaPlan12.length - 2]));

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

                // Establecer el ancho de la celda de acuerdo con el arreglo anchurasColumnasPlan12
                celdaUltimaPlan12.setWidth(String.valueOf(anchurasColumnasPlan12[i]));

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
                JOptionPane.showMessageDialog(null, "Documento guardado en: " + rutaArchivo);
            } else {
                JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "A ocurrido un error interno, Comuniquese con Soporte", "Error", JOptionPane.ERROR_MESSAGE);
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
