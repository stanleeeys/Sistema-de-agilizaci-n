package View;

import Controllers.ClienteController;
import Controllers.DetalleOrdenController;
import Controllers.FechaERController;
import Controllers.OrdenController;
import Controllers.ProveedorController;
import ViewModel.ClienteVM;
import ViewModel.DetalleOrdenVM;
import ViewModel.FechaErVM;
import ViewModel.OrdenVM;
import ViewModel.ProveedorVM;
import ViewModel.TablaOrdenVM;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import javax.swing.DefaultComboBoxModel;

public class FrmSistema extends javax.swing.JFrame {

    // Objetos globales *******************************************************
    //varables y array
    private int _codId;
    private String codOrden;
    private int cliente;
    private int fechas;
    private int numFilas;
    private int numIdDetallesIniciales;
    private int numNuevosDetalles;
    ArrayList<Object> idDetallesArray = new ArrayList<>();
    ArrayList<Object> idEliminacionDetallesArray = new ArrayList<>();

    //CONTROLADORES
    ClienteController clienteC = new ClienteController();
    FechaERController fechaERController = new FechaERController();
    ProveedorController proveedorController = new ProveedorController();
    OrdenController ordenController = new OrdenController();
    DetalleOrdenController detalleOrdenController = new DetalleOrdenController();

    //VIEW MODELS
    ClienteVM clienteVM;
    FechaErVM fechaErVM;
    ProveedorVM proveedorVM;
    OrdenVM ordenVM;
    DetalleOrdenVM detalleOrdenVM;

    DefaultTableModel modeloTabla = new DefaultTableModel();

    public FrmSistema(int idOrden) {

        initComponents();
        txtEncargadoCompra.requestFocus();
        this._codId = idOrden;
        cargarCombobox();
        botonesDesabilitado();
        datosActualizar(idOrden);
        this.setLocationRelativeTo(null);
        this.setTitle("Creadora de DOC");
        //txtId.setVisible(false);

    }

    private void botonesDesabilitado() {

        if (!(_codId == 0)) {

            btnGuardar.setEnabled(false);

        } else {

            btnActualizar.setEnabled(false);
        }

    }

    private void datosActualizar(int id) {

        if (!(id == 0)) {

            TablaOrdenVM ordenVM = importarDatos(id);

            cliente = ordenVM.getClienteId();
            fechas = ordenVM.getFechasErId();
            codOrden = ordenVM.getCodOrden();
            txtEncargadoOrden.setText(ordenVM.getEncargadoOrden());
            //txtTotales.setText(String.valueOf(ordenVM.getTotales()));
            dtmLimite.setDate(ordenVM.getLimite_cotizacion());
            dtmFechaEntrega.setDate(ordenVM.getFecha_de_entrega());
            txtDesde.setText(ordenVM.getHora_entrega_desde());
            txthasta.setText(ordenVM.getHora_entrega_hasta());
            txtTiempo.setText(ordenVM.getTiempo_entrega());
            txtPlazoEntrega.setText(ordenVM.plazo_entrega);
            txtLugarEntrega.setText(ordenVM.lugar_entrega);
            txtVigenciaCotizacion.setText(ordenVM.vigencia_de_la_cotizacion);
            txtGarantia.setText(ordenVM.tiempo_de_garantia);

            txtEncargadoCompra.setText(ordenVM.getEncargadoCompra());
            cbxProveedor.setSelectedIndex(ordenVM.getProveedorId() - 1);
            txtNombreInstitucion.setText(ordenVM.getNombreInstitucion());
            txtMunicipio.setText(ordenVM.getMunicipio());
            txtCodigoEscuela.setText(ordenVM.getCodigo_escuela());

            dtmSolicitudCotizacion.setDate(ordenVM.getFechaSolicitud());
            dtmCotizacion.setDate(ordenVM.getFechaCotizacion());
            dtmOrdenCompra.setDate(ordenVM.getFechaOrden());
            dtmRecepcion.setDate(ordenVM.getFechaRecepcion());

            //importarDetalles(ordenId
            double totales = 0;
            ArrayList<DetalleOrdenVM> detalleOrdenesVM = detalleOrdenController.listarDetalleOrden(id);
            modeloTabla = (DefaultTableModel) tblCotizacion.getModel();
            modeloTabla.setRowCount(0);
            for (DetalleOrdenVM cl : detalleOrdenesVM) {

                Object[] ob = new Object[6]; // Crear el arreglo dentro del bucle

                // Agregar fila a la tabla
                int numeroItem = tblCotizacion.getRowCount() + 1;
                ob[0] = numeroItem;
                ob[1] = cl.getCantidad();
                ob[2] = cl.getUnidadMedida();
                ob[3] = cl.getDescripcionArticulo();
                ob[4] = cl.getPrecioUnitario();
                ob[5] = cl.getPrecioTotal();
                totales += cl.getPrecioTotal();

                int idO = cl.getIdDetalleOrden();
                idDetallesArray.add(idO);

                modeloTabla.addRow(ob);

            }

            numFilas = modeloTabla.getRowCount();
            numIdDetallesIniciales = idDetallesArray.size();
            tblCotizacion.setModel(modeloTabla);
            txtTotales.setText(String.valueOf(totales));
        }
    }

    private TablaOrdenVM importarDatos(int id) {

        return ordenController.datosOrden(id);
    }

    public void cargarCombobox() {

        ArrayList<ProveedorVM> listProveedor = proveedorController.mostrarProveedores();
        Iterator iteratorProveedores = listProveedor.iterator();
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        comboBoxModel.removeAllElements();
        cbxProveedor.removeAll();
        String filasCBX[] = new String[2];
        int valueMember[] = new int[listProveedor.size()];

        int contador = 0;
        while (iteratorProveedores.hasNext()) {

            ProveedorVM proveedorVM;
            proveedorVM = (ProveedorVM) iteratorProveedores.next();
            valueMember[contador] = proveedorVM.getIdProveedor();
            comboBoxModel.addElement(proveedorVM.getNombreProveedor());
            contador++;
        }
        cbxProveedor.setModel(comboBoxModel);
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCotizacion = new javax.swing.JTable();
        txtCantidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPrecioU = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTotales = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtUnidadM = new javax.swing.JTextField();
        btnAgregarDetalle = new javax.swing.JButton();
        btnLimpiarTodo = new javax.swing.JButton();
        btnEliminarDetalle = new javax.swing.JButton();
        btnActualizarDetalle = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cbxProveedor = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtEncargadoOrden = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dtmSolicitudCotizacion = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        dtmCotizacion = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        dtmOrdenCompra = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        dtmRecepcion = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtNombreInstitucion = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtMunicipio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCodigoEscuela = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtPlazoEntrega = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtLugarEntrega = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtVigenciaCotizacion = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtGarantia = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        dtmLimite = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        dtmFechaEntrega = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        txtDesde = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txthasta = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTiempo = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1366, 720));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Detalles Orden", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblCotizacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N", "CANTIDAD", "UNIDAD DE MEDIDA", "DESCRIPCION/ESPECIFICACION TÉCNICA", "PRECIO UNITARIO", "PRECIO TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCotizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCotizacionMouseClicked(evt);
            }
        });
        tblCotizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCotizacionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblCotizacion);
        if (tblCotizacion.getColumnModel().getColumnCount() > 0) {
            tblCotizacion.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblCotizacion.getColumnModel().getColumn(1).setPreferredWidth(5);
            tblCotizacion.getColumnModel().getColumn(2).setPreferredWidth(5);
            tblCotizacion.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblCotizacion.getColumnModel().getColumn(4).setPreferredWidth(20);
            tblCotizacion.getColumnModel().getColumn(5).setPreferredWidth(20);
        }

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Cantidad");

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Descripción/especificacion técnica");

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Precio unitario");

        txtPrecioU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioUKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        jLabel1.setText("TOTALES");

        txtTotales.setEditable(false);
        txtTotales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalesActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Unidad de medida");

        txtUnidadM.setText("C/U");
        txtUnidadM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnidadMKeyPressed(evt);
            }
        });

        btnAgregarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GuardarTodo.png"))); // NOI18N
        btnAgregarDetalle.setText("Agregar");
        btnAgregarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDetalleActionPerformed(evt);
            }
        });
        btnAgregarDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAgregarDetalleKeyPressed(evt);
            }
        });

        btnLimpiarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo_orange_ccleaner_clean_icon_134365.png"))); // NOI18N
        btnLimpiarTodo.setText("Limpiar");
        btnLimpiarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarTodoActionPerformed(evt);
            }
        });
        btnLimpiarTodo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLimpiarTodoKeyPressed(evt);
            }
        });

        btnEliminarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminarDetalle.setText("Eliminar");
        btnEliminarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDetalleActionPerformed(evt);
            }
        });
        btnEliminarDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEliminarDetalleKeyPressed(evt);
            }
        });

        btnActualizarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/emblemsynchronizing_93485.png"))); // NOI18N
        btnActualizarDetalle.setText("Actualizar");
        btnActualizarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarDetalleActionPerformed(evt);
            }
        });
        btnActualizarDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnActualizarDetalleKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecioU, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                        .addComponent(txtUnidadM, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPrecioU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUnidadM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Datos de Provedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        cbxProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxProveedorKeyPressed(evt);
            }
        });

        jLabel13.setText("Provedor:");

        jLabel14.setText("Nombre de encargado de la orden:");

        txtEncargadoOrden.setText("MARGARITA URBINA SIBRIAN");
        txtEncargadoOrden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEncargadoOrdenKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(cbxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEncargadoOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEncargadoOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Fechas de Emicion Y Recepcion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel2.setText("Fecha de solicitud de cotizacion:");

        dtmSolicitudCotizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dtmSolicitudCotizacionKeyPressed(evt);
            }
        });

        jLabel8.setText("Fecha de Emicion de cotizacion:");

        dtmCotizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dtmCotizacionKeyPressed(evt);
            }
        });

        jLabel10.setText("Fecha de orden de compra:");

        dtmOrdenCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dtmOrdenCompraKeyPressed(evt);
            }
        });

        jLabel11.setText("Fecha de recepcion de bienes:");

        dtmRecepcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dtmRecepcionKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtmOrdenCompra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addComponent(dtmSolicitudCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)))
                .addGap(58, 58, 58)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtmCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(23, 23, 23))
                    .addComponent(dtmRecepcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtmSolicitudCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtmCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtmOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtmRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Datos del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel15.setText("A nombre de:");

        jLabel16.setText("Nombre de la  Escuela o Institucion:");

        txtNombreInstitucion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreInstitucionKeyPressed(evt);
            }
        });

        jLabel17.setText("Municipio:");

        txtMunicipio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMunicipioKeyPressed(evt);
            }
        });

        jLabel9.setText("Codigo:");

        txtCodigoEscuela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoEscuelaKeyPressed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtNombreInstitucion)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtCodigoEscuela, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                .addGap(29, 29, 29)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreInstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoEscuela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel18.setText("Plazo de entrega:");

        txtPlazoEntrega.setText("INMEDIATA");
        txtPlazoEntrega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPlazoEntregaKeyPressed(evt);
            }
        });

        jLabel19.setText("Lugar de entrega:");

        txtLugarEntrega.setText("CENTRO ESCOLAR");
        txtLugarEntrega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLugarEntregaKeyPressed(evt);
            }
        });

        jLabel20.setText("Vigencia de la cotizacion:");

        txtVigenciaCotizacion.setText("15 DIAS");
        txtVigenciaCotizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtVigenciaCotizacionKeyPressed(evt);
            }
        });

        jLabel21.setText("Tiempo de garantia de los bienes:");

        txtGarantia.setText("30 DIAS");
        txtGarantia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGarantiaKeyPressed(evt);
            }
        });

        jLabel22.setText("Fecha limite cotizacion");

        dtmLimite.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dtmLimiteKeyPressed(evt);
            }
        });

        jLabel7.setText("Fecha de entrega en Escuela");

        dtmFechaEntrega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dtmFechaEntregaKeyPressed(evt);
            }
        });

        jLabel23.setText("Hora de entrega: DESDE");

        txtDesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDesdeKeyPressed(evt);
            }
        });

        jLabel24.setText("HASTA");

        txthasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txthastaKeyPressed(evt);
            }
        });

        jLabel25.setText("DE LA:");

        txtTiempo.setText("MAÑANA");
        txtTiempo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTiempoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(dtmLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(dtmFechaEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDesde, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtVigenciaCotizacion)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPlazoEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                    .addComponent(jLabel20)))
                            .addComponent(jLabel23))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLugarEntrega)
                            .addComponent(txtGarantia)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel25)
                                .addGap(88, 88, 88))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel19))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txthasta, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(126, 126, 126)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtmLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtmFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txthasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLugarEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlazoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVigenciaCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GuardarTodo.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGuardarKeyPressed(evt);
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/emblemsynchronizing_93485.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        btnActualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnActualizarKeyPressed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/return_icon_154912.png"))); // NOI18N
        btnCancelar.setText("Salir al menu");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        btnCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:

        try {
            if (verificacionCamposVacios()) {
                // Inicializar los objetos de las entidades
                ClienteVM clienteVM = new ClienteVM();
                clienteVM.setIdCliente(cliente);
                clienteVM.setEncargadoCompra(txtEncargadoCompra.getText().trim());
                clienteVM.setNombreInstitucion(txtNombreInstitucion.getText().trim());
                clienteVM.setMunicipio(txtMunicipio.getText().trim());
                clienteVM.setCodigo(txtCodigoEscuela.getText().trim());

                // Guardar cliente
                boolean g1 = clienteC.actualizarCliente(clienteVM);
                if (!g1) {
                    throw new Exception("Error al guardar el cliente");
                }

                FechaErVM fechaErVM = new FechaErVM();
                fechaErVM.setIdFechas(fechas);
                fechaErVM.setFechaSolicitud(dtmSolicitudCotizacion.getDate());
                fechaErVM.setFechaCotizacion(dtmCotizacion.getDate());
                fechaErVM.setFechaOrden(dtmOrdenCompra.getDate());
                fechaErVM.setFechaRecepcion(dtmRecepcion.getDate());

                // Guardar fechas
                boolean g2 = fechaERController.actualizarFechas(fechaErVM);
                if (!g2) {
                    throw new Exception("Error al guardar las fechas");
                }

                OrdenVM ordenVM = new OrdenVM();
                ordenVM.setIdOrden(_codId);
                ordenVM.setCodOrden(codOrden);
                ordenVM.setEncargadoOrden(txtEncargadoOrden.getText().trim());
                ordenVM.setTotales(Double.parseDouble(txtTotales.getText().trim()));
                ordenVM.setLimite_cotizacion(dtmLimite.getDate());
                ordenVM.setFecha_de_entrega(dtmFechaEntrega.getDate());
                ordenVM.setHora_entrega_desde(txtDesde.getText().trim());
                ordenVM.setHora_entrega_hasta(txthasta.getText().trim());
                ordenVM.setTiempo_entrega(txtTiempo.getText().trim());
                ordenVM.setPlazo_entrega(txtPlazoEntrega.getText().trim());
                ordenVM.setLugar_entrega(txtLugarEntrega.getText().trim());
                ordenVM.setVigencia_de_la_cotizacion(txtVigenciaCotizacion.getText().trim());
                ordenVM.setTiempo_de_garantia(txtGarantia.getText().trim());
                ordenVM.setClienteId(cliente);
                ordenVM.setProveedorId(cbxProveedor.getSelectedIndex() + 1);
                ordenVM.setFechasErId(fechas);

                // Guardar orden
                boolean g3 = ordenController.actualizarOrden(ordenVM);
                if (!g3) {
                    throw new Exception("Error al guardar la orden");
                }

                int g4 = 0;
                modeloTabla = (DefaultTableModel) tblCotizacion.getModel();
                int filasActualizadas = modeloTabla.getRowCount();
                if (filasActualizadas != numFilas) {

                    if (!(idEliminacionDetallesArray.isEmpty()) && numIdDetallesIniciales != idDetallesArray.size() && 0 != numNuevosDetalles) {

                        eliminarDetalle();
                        guardarDetalle();

                    } else if (idEliminacionDetallesArray.isEmpty() && numIdDetallesIniciales == idDetallesArray.size() && 0 != numNuevosDetalles) {

                        guardarDetalle();

                    } else {

                        eliminarDetalle();
                    }
                }

                g4 = actualizar();

                //int ordenId = ordenController.obtenerOrden();
                if (g1 && g2 && g3 && !(g4 == 0)) {
                    JOptionPane.showMessageDialog(this, "Orden Actualizada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    Limpiar();
                    FrmMain frmMain = new FrmMain();
                    frmMain.setVisible(true);
                    this.setVisible(false);
                } else {
                    throw new Exception("Error interno detectado");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar la orden: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    /*ESTE EVENTO SIRVE PARA GUARDAR LOS DATOS DEL FORMULARIO EN LA BD*/
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        try {
            if (verificacionCamposVacios()) {
                // Inicializar los objetos de las entidades
                ClienteVM clienteVM = new ClienteVM();
                clienteVM.setEncargadoCompra(txtEncargadoCompra.getText().trim());
                clienteVM.setNombreInstitucion(txtNombreInstitucion.getText().trim());
                clienteVM.setMunicipio(txtMunicipio.getText().trim());
                clienteVM.setCodigo(txtCodigoEscuela.getText().trim());

                // Guardar cliente
                boolean g1 = clienteC.guardarCliente(clienteVM);
                if (!g1) {
                    throw new Exception("Error al guardar el cliente");
                }

                FechaErVM fechaErVM = new FechaErVM();
                fechaErVM.setFechaSolicitud(dtmSolicitudCotizacion.getDate());
                fechaErVM.setFechaCotizacion(dtmCotizacion.getDate());
                fechaErVM.setFechaOrden(dtmOrdenCompra.getDate());
                fechaErVM.setFechaRecepcion(dtmRecepcion.getDate());

                // Guardar fechas
                boolean g2 = fechaERController.guardarFechas(fechaErVM);
                if (!g2) {
                    throw new Exception("Error al guardar las fechas");
                }

                int clienteId = clienteC.obtenerCliente();
                int fechaId = fechaERController.obtenerFecha();

                OrdenVM ordenVM = new OrdenVM();
                ordenVM.setCodOrden(generarCodigo());
                ordenVM.setEncargadoOrden(txtEncargadoOrden.getText().trim());
                ordenVM.setTotales(Double.parseDouble(txtTotales.getText().trim()));
                ordenVM.setLimite_cotizacion(dtmLimite.getDate());
                ordenVM.setFecha_de_entrega(dtmFechaEntrega.getDate());
                ordenVM.setHora_entrega_desde(txtDesde.getText().trim());
                ordenVM.setHora_entrega_hasta(txthasta.getText().trim());
                ordenVM.setTiempo_entrega(txtTiempo.getText().trim());
                ordenVM.setPlazo_entrega(txtPlazoEntrega.getText().trim());
                ordenVM.setLugar_entrega(txtLugarEntrega.getText().trim());
                ordenVM.setVigencia_de_la_cotizacion(txtVigenciaCotizacion.getText().trim());
                ordenVM.setTiempo_de_garantia(txtGarantia.getText().trim());
                ordenVM.setClienteId(clienteId);
                ordenVM.setProveedorId(cbxProveedor.getSelectedIndex() + 1);
                ordenVM.setFechasErId(fechaId);

                // Guardar orden
                boolean g3 = ordenController.guardarOrden(ordenVM);
                if (!g3) {
                    throw new Exception("Error al guardar la orden");
                }

                int ordenId = ordenController.obtenerOrden();

                DefaultTableModel modeloTabla = (DefaultTableModel) tblCotizacion.getModel();
                int g4 = 0;

                for (int fila = 0; fila < modeloTabla.getRowCount(); fila++) {
                    DetalleOrdenVM detalleOrdenVM = new DetalleOrdenVM();
                    detalleOrdenVM.setNumArticulo((Integer) modeloTabla.getValueAt(fila, 0));
                    detalleOrdenVM.setCantidad((Integer) modeloTabla.getValueAt(fila, 1));
                    detalleOrdenVM.setUnidadMedida((String) modeloTabla.getValueAt(fila, 2));
                    detalleOrdenVM.setDescripcionArticulo((String) modeloTabla.getValueAt(fila, 3));
                    detalleOrdenVM.setPrecioUnitario((Double) modeloTabla.getValueAt(fila, 4));
                    detalleOrdenVM.setPrecioTotal((Double) modeloTabla.getValueAt(fila, 5));
                    detalleOrdenVM.setOrdenId(ordenId);

                    boolean guardado = detalleOrdenController.guardarDetalleOrden(detalleOrdenVM);
                    if (!guardado) {
                        throw new Exception("Error al guardar el detalle de la orden");
                    }
                    g4++;
                }

                if (g1 && g2 && g3 && g4 == modeloTabla.getRowCount()) {
                    JOptionPane.showMessageDialog(this, "Orden registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    // Limpiar los campos y actualizar la tabla
                    Limpiar();
                    FrmMain frmMain = new FrmMain();
                    frmMain.setVisible(true);
                    this.setVisible(false);
                } else {
                    throw new Exception("Error interno detectado");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar la orden: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarDetalleActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modeloTabla = (DefaultTableModel) tblCotizacion.getModel();

        // Obtener el índice de la fila seleccionada
        int filaSeleccionada = tblCotizacion.getSelectedRow();

        // Verificar si hay una fila seleccionada
        if (filaSeleccionada != -1) {
            try {
                // Obtener y validar los valores de los campos de entrada
                String descripcion = txtDescripcion.getText();
                if (descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor llene la descripción.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String cantidadStr = txtCantidad.getText();
                int cantidad;
                try {
                    cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad <= 0) {
                        JOptionPane.showMessageDialog(this, "La cantidad debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String precioUnitarioStr = txtPrecioU.getText();
                double precioUnitario;
                try {
                    precioUnitario = Double.parseDouble(precioUnitarioStr);
                    if (precioUnitario < 0) {
                        JOptionPane.showMessageDialog(this, "El precio unitario no puede ser negativo.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "El precio unitario debe ser un número.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String unidadMedida = txtUnidadM.getText();
                if (unidadMedida.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "La unidad de medida no puede estar vacía.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Calcular el precio total
                double precioTotal = cantidad * precioUnitario;

                // Actualizar la fila en la tabla
                modeloTabla.setValueAt(cantidad, filaSeleccionada, 1);
                modeloTabla.setValueAt(unidadMedida, filaSeleccionada, 2);
                modeloTabla.setValueAt(descripcion, filaSeleccionada, 3);
                modeloTabla.setValueAt(precioUnitario, filaSeleccionada, 4);
                modeloTabla.setValueAt(precioTotal, filaSeleccionada, 5);

                txtDescripcion.setText("");
                txtCantidad.setText("");
                txtPrecioU.setText("");

                // Recalcular el total de todos los precios después de actualizar la fila
                double totales = 0;
                int numFilas = modeloTabla.getRowCount();
                for (int fila = 0; fila < numFilas; fila++) {
                    double pTotal = (Double) modeloTabla.getValueAt(fila, modeloTabla.getColumnCount() - 1);
                    totales += pTotal;
                }
                txtTotales.setText(String.valueOf(totales));

                JOptionPane.showMessageDialog(this, "Detalle actualizado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);

                Limpiar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar el detalle: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Mostrar mensaje de advertencia si no hay ninguna fila seleccionada
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarDetalleActionPerformed

    private void btnEliminarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDetalleActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modeloTabla = (DefaultTableModel) tblCotizacion.getModel();

        // Obtener el índice de la fila seleccionada
        int filaSeleccionada = tblCotizacion.getSelectedRow();

        // Verificar si hay una fila seleccionada
        if (filaSeleccionada != -1) {

            // Eliminar la fila del modelo de la tabla
            modeloTabla.removeRow(filaSeleccionada);

            if (_codId != 0) {

                Object idremove = idDetallesArray.get(filaSeleccionada);
                int idOrden = (Integer) idremove;
                idEliminacionDetallesArray.add(idOrden);
                idDetallesArray.remove(filaSeleccionada);
            }

            txtDescripcion.setText("");
            txtCantidad.setText("");
            txtPrecioU.setText("");

            for (int fila = 0; fila < modeloTabla.getRowCount(); fila++) {

                modeloTabla.setValueAt(fila + 1, fila, 0);
            }

            // Recalcular el total de todos los precios después de eliminar la fila
            double totales = 0.00;
            int numFilas = modeloTabla.getRowCount();
            for (int fila = 0; fila < numFilas; fila++) {
                double precioTotal = (Double) modeloTabla.getValueAt(fila, modeloTabla.getColumnCount() - 1);
                totales += precioTotal;
            }
            DecimalFormat df = new DecimalFormat("0.00");

            String formattedTotal = df.format(totales);
            txtTotales.setText(formattedTotal);

            JOptionPane.showMessageDialog(this, "Detalle eliminado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Mostrar mensaje de advertencia si no hay ninguna fila seleccionada
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarDetalleActionPerformed

    // EVENTO PARA AGREGAR DATOS A LA TABLA
    private void btnAgregarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDetalleActionPerformed

        agregarDetalle();
    }//GEN-LAST:event_btnAgregarDetalleActionPerformed

    private void txtTotalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalesActionPerformed

    private void tblCotizacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCotizacionMouseClicked
        // TODO add your handling code here:

        int fila = tblCotizacion.getSelectedRow();
        if (fila != -1) { // Verifica si hay una fila seleccionada
            try {
                //txtId.setText(tblCotizacion.getValueAt(fila, 0).toString());
                txtCantidad.setText(tblCotizacion.getValueAt(fila, 1).toString());
                txtUnidadM.setText(tblCotizacion.getValueAt(fila, 2).toString());
                txtDescripcion.setText(tblCotizacion.getValueAt(fila, 3).toString());
                txtPrecioU.setText(tblCotizacion.getValueAt(fila, 4).toString());
                // Corregir el índice para el precio total
                //txtTotales.setText(tblCotizacion.getValueAt(fila, 5).toString());
                btnActualizarDetalle.requestFocus();
            } catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {
                // Manejar la excepción en caso de que no se pueda obtener un valor de la fila seleccionada
                JOptionPane.showMessageDialog(null, "Error al obtener los datos de la fila seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila antes de editar los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_tblCotizacionMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:

        FrmMain frmMain = new FrmMain();

        frmMain.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnLimpiarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarTodoActionPerformed
        // TODO add your handling code here:
        Limpiar();
        txtDescripcion.requestFocus();
    }//GEN-LAST:event_btnLimpiarTodoActionPerformed

    private void txtNombreInstitucionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreInstitucionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            txtCodigoEscuela.requestFocus();
        }
    }//GEN-LAST:event_txtNombreInstitucionKeyPressed

    private void txtCodigoEscuelaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoEscuelaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            txtMunicipio.requestFocus();
        }
    }//GEN-LAST:event_txtCodigoEscuelaKeyPressed

    private void txtMunicipioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMunicipioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            dtmLimite.requestFocus();
        }
    }//GEN-LAST:event_txtMunicipioKeyPressed

    private void dtmLimiteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtmLimiteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            dtmFechaEntrega.requestFocus();
        }
    }//GEN-LAST:event_dtmLimiteKeyPressed

    private void dtmFechaEntregaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtmFechaEntregaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            txtDesde.requestFocus();
        }
    }//GEN-LAST:event_dtmFechaEntregaKeyPressed

    private void txtDesdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesdeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            txthasta.requestFocus();
        }
    }//GEN-LAST:event_txtDesdeKeyPressed

    private void txthastaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthastaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            txtTiempo.requestFocus();
        }
    }//GEN-LAST:event_txthastaKeyPressed

    private void txtTiempoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            txtPlazoEntrega.requestFocus();
        }
    }//GEN-LAST:event_txtTiempoKeyPressed

    private void txtPlazoEntregaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlazoEntregaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            txtLugarEntrega.requestFocus();
        }
    }//GEN-LAST:event_txtPlazoEntregaKeyPressed

    private void txtLugarEntregaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLugarEntregaKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            txtVigenciaCotizacion.requestFocus();
        }
    }//GEN-LAST:event_txtLugarEntregaKeyPressed

    private void txtVigenciaCotizacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVigenciaCotizacionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVigenciaCotizacionKeyPressed

    private void txtGarantiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGarantiaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            cbxProveedor.requestFocus();
        }
    }//GEN-LAST:event_txtGarantiaKeyPressed

    private void cbxProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxProveedorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            txtEncargadoOrden.requestFocus();
        }
    }//GEN-LAST:event_cbxProveedorKeyPressed

    private void txtEncargadoOrdenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEncargadoOrdenKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            dtmSolicitudCotizacion.requestFocus();
        }
    }//GEN-LAST:event_txtEncargadoOrdenKeyPressed

    private void dtmSolicitudCotizacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtmSolicitudCotizacionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            dtmCotizacion.requestFocus();
        }
    }//GEN-LAST:event_dtmSolicitudCotizacionKeyPressed

    private void dtmCotizacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtmCotizacionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            dtmOrdenCompra.requestFocus();
        }
    }//GEN-LAST:event_dtmCotizacionKeyPressed

    private void dtmOrdenCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtmOrdenCompraKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            dtmRecepcion.requestFocus();
        }
    }//GEN-LAST:event_dtmOrdenCompraKeyPressed

    private void dtmRecepcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtmRecepcionKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_dtmRecepcionKeyPressed

    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            txtCantidad.requestFocus();
        }
    }//GEN-LAST:event_txtDescripcionKeyPressed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            txtPrecioU.requestFocus();
        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtPrecioUKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioUKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            txtUnidadM.requestFocus();
        }
    }//GEN-LAST:event_txtPrecioUKeyPressed

    private void txtUnidadMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadMKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            btnAgregarDetalle.requestFocus();
        }
    }//GEN-LAST:event_txtUnidadMKeyPressed

    private void btnAgregarDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAgregarDetalleKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            agregarDetalle();
            txtDescripcion.requestFocus();
        }
    }//GEN-LAST:event_btnAgregarDetalleKeyPressed

    private void btnEliminarDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEliminarDetalleKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            DefaultTableModel modeloTabla = (DefaultTableModel) tblCotizacion.getModel();

            // Obtener el índice de la fila seleccionada
            int filaSeleccionada = tblCotizacion.getSelectedRow();

            // Verificar si hay una fila seleccionada
            if (filaSeleccionada != -1) {

                // Eliminar la fila del modelo de la tabla
                modeloTabla.removeRow(filaSeleccionada);

                if (_codId != 0) {

                    Object idremove = idDetallesArray.get(filaSeleccionada);
                    int idOrden = (Integer) idremove;
                    idEliminacionDetallesArray.add(idOrden);
                    idDetallesArray.remove(filaSeleccionada);
                }

                txtDescripcion.setText("");
                txtCantidad.setText("");
                txtPrecioU.setText("");

                for (int fila = 0; fila < modeloTabla.getRowCount(); fila++) {

                    modeloTabla.setValueAt(fila + 1, fila, 0);
                }

                // Recalcular el total de todos los precios después de eliminar la fila
                double totales = 0;
                int numFilas = modeloTabla.getRowCount();
                for (int fila = 0; fila < numFilas; fila++) {
                    double precioTotal = (Double) modeloTabla.getValueAt(fila, modeloTabla.getColumnCount() - 1);
                    totales += precioTotal;
                }
                txtTotales.setText(String.valueOf(totales));

                JOptionPane.showMessageDialog(this, "Detalle eliminado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Mostrar mensaje de advertencia si no hay ninguna fila seleccionada
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnEliminarDetalleKeyPressed

    private void btnActualizarDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnActualizarDetalleKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            DefaultTableModel modeloTabla = (DefaultTableModel) tblCotizacion.getModel();

            // Obtener el índice de la fila seleccionada
            int filaSeleccionada = tblCotizacion.getSelectedRow();

            // Verificar si hay una fila seleccionada
            if (filaSeleccionada != -1) {
                try {
                    // Obtener y validar los valores de los campos de entrada
                    String descripcion = txtDescripcion.getText();
                    if (descripcion.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Por favor llene la descripción.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String cantidadStr = txtCantidad.getText();
                    int cantidad;
                    try {
                        cantidad = Integer.parseInt(cantidadStr);
                        if (cantidad <= 0) {
                            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "La cantidad debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String precioUnitarioStr = txtPrecioU.getText();
                    double precioUnitario;
                    try {
                        precioUnitario = Double.parseDouble(precioUnitarioStr);
                        if (precioUnitario < 0) {
                            JOptionPane.showMessageDialog(this, "El precio unitario no puede ser negativo.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "El precio unitario debe ser un número.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String unidadMedida = txtUnidadM.getText();
                    if (unidadMedida.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "La unidad de medida no puede estar vacía.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Calcular el precio total
                    double precioTotal = cantidad * precioUnitario;

                    // Actualizar la fila en la tabla
                    modeloTabla.setValueAt(cantidad, filaSeleccionada, 1);
                    modeloTabla.setValueAt(unidadMedida, filaSeleccionada, 2);
                    modeloTabla.setValueAt(descripcion, filaSeleccionada, 3);
                    modeloTabla.setValueAt(precioUnitario, filaSeleccionada, 4);
                    modeloTabla.setValueAt(precioTotal, filaSeleccionada, 5);

                    txtDescripcion.setText("");
                    txtCantidad.setText("");
                    txtPrecioU.setText("");

                    // Recalcular el total de todos los precios después de actualizar la fila
                    double totales = 0;
                    int numFilas = modeloTabla.getRowCount();
                    for (int fila = 0; fila < numFilas; fila++) {
                        double pTotal = (Double) modeloTabla.getValueAt(fila, modeloTabla.getColumnCount() - 1);
                        totales += pTotal;
                    }
                    txtTotales.setText(String.valueOf(totales));

                    JOptionPane.showMessageDialog(this, "Detalle actualizado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);

                    Limpiar();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el detalle: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Mostrar mensaje de advertencia si no hay ninguna fila seleccionada
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnActualizarDetalleKeyPressed

    private void btnLimpiarTodoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLimpiarTodoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Limpiar();
            txtDescripcion.requestFocus();
        }
    }//GEN-LAST:event_btnLimpiarTodoKeyPressed

    private void tblCotizacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCotizacionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            int fila = tblCotizacion.getSelectedRow();
            if (fila != -1) { // Verifica si hay una fila seleccionada
                try {
                    //txtId.setText(tblCotizacion.getValueAt(fila, 0).toString());
                    txtCantidad.setText(tblCotizacion.getValueAt(fila, 1).toString());
                    txtUnidadM.setText(tblCotizacion.getValueAt(fila, 2).toString());
                    txtDescripcion.setText(tblCotizacion.getValueAt(fila, 3).toString());
                    txtPrecioU.setText(tblCotizacion.getValueAt(fila, 4).toString());
                    // Corregir el índice para el precio total
                    //txtTotales.setText(tblCotizacion.getValueAt(fila, 5).toString());
                    btnActualizarDetalle.requestFocus();
                } catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {
                    // Manejar la excepción en caso de que no se pueda obtener un valor de la fila seleccionada
                    JOptionPane.showMessageDialog(null, "Error al obtener los datos de la fila seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila antes de editar los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        }
    }//GEN-LAST:event_tblCotizacionKeyPressed

    private void btnGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            try {
                if (verificacionCamposVacios()) {
                    // Inicializar los objetos de las entidades
                    ClienteVM clienteVM = new ClienteVM();
                    clienteVM.setEncargadoCompra(txtEncargadoCompra.getText().trim());
                    clienteVM.setNombreInstitucion(txtNombreInstitucion.getText().trim());
                    clienteVM.setMunicipio(txtMunicipio.getText().trim());
                    clienteVM.setCodigo(txtCodigoEscuela.getText().trim());

                    // Guardar cliente
                    boolean g1 = clienteC.guardarCliente(clienteVM);
                    if (!g1) {
                        throw new Exception("Error al guardar el cliente");
                    }

                    FechaErVM fechaErVM = new FechaErVM();
                    fechaErVM.setFechaSolicitud(dtmSolicitudCotizacion.getDate());
                    fechaErVM.setFechaCotizacion(dtmCotizacion.getDate());
                    fechaErVM.setFechaOrden(dtmOrdenCompra.getDate());
                    fechaErVM.setFechaRecepcion(dtmRecepcion.getDate());

                    // Guardar fechas
                    boolean g2 = fechaERController.guardarFechas(fechaErVM);
                    if (!g2) {
                        throw new Exception("Error al guardar las fechas");
                    }

                    int clienteId = clienteC.obtenerCliente();
                    int fechaId = fechaERController.obtenerFecha();

                    OrdenVM ordenVM = new OrdenVM();
                    ordenVM.setCodOrden(generarCodigo());
                    ordenVM.setEncargadoOrden(txtEncargadoOrden.getText().trim());
                    ordenVM.setTotales(Double.parseDouble(txtTotales.getText().trim()));
                    ordenVM.setLimite_cotizacion(dtmLimite.getDate());
                    ordenVM.setFecha_de_entrega(dtmFechaEntrega.getDate());
                    ordenVM.setHora_entrega_desde(txtDesde.getText().trim());
                    ordenVM.setHora_entrega_hasta(txthasta.getText().trim());
                    ordenVM.setTiempo_entrega(txtTiempo.getText().trim());
                    ordenVM.setPlazo_entrega(txtPlazoEntrega.getText().trim());
                    ordenVM.setLugar_entrega(txtLugarEntrega.getText().trim());
                    ordenVM.setVigencia_de_la_cotizacion(txtVigenciaCotizacion.getText().trim());
                    ordenVM.setTiempo_de_garantia(txtGarantia.getText().trim());
                    ordenVM.setClienteId(clienteId);
                    ordenVM.setProveedorId(cbxProveedor.getSelectedIndex() + 1);
                    ordenVM.setFechasErId(fechaId);

                    // Guardar orden
                    boolean g3 = ordenController.guardarOrden(ordenVM);
                    if (!g3) {
                        throw new Exception("Error al guardar la orden");
                    }

                    int ordenId = ordenController.obtenerOrden();

                    DefaultTableModel modeloTabla = (DefaultTableModel) tblCotizacion.getModel();
                    int g4 = 0;

                    for (int fila = 0; fila < modeloTabla.getRowCount(); fila++) {
                        DetalleOrdenVM detalleOrdenVM = new DetalleOrdenVM();
                        detalleOrdenVM.setNumArticulo((Integer) modeloTabla.getValueAt(fila, 0));
                        detalleOrdenVM.setCantidad((Integer) modeloTabla.getValueAt(fila, 1));
                        detalleOrdenVM.setUnidadMedida((String) modeloTabla.getValueAt(fila, 2));
                        detalleOrdenVM.setDescripcionArticulo((String) modeloTabla.getValueAt(fila, 3));
                        detalleOrdenVM.setPrecioUnitario((Double) modeloTabla.getValueAt(fila, 4));
                        detalleOrdenVM.setPrecioTotal((Double) modeloTabla.getValueAt(fila, 5));
                        detalleOrdenVM.setOrdenId(ordenId);

                        boolean guardado = detalleOrdenController.guardarDetalleOrden(detalleOrdenVM);
                        if (!guardado) {
                            throw new Exception("Error al guardar el detalle de la orden");
                        }
                        g4++;
                    }

                    if (g1 && g2 && g3 && g4 == modeloTabla.getRowCount()) {
                        JOptionPane.showMessageDialog(this, "Orden registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                        // Limpiar los campos y actualizar la tabla
                        Limpiar();
                        FrmMain frmMain = new FrmMain();
                        frmMain.setVisible(true);
                        this.setVisible(false);
                    } else {
                        throw new Exception("Error interno detectado");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al registrar la orden: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnGuardarKeyPressed

    private void btnActualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnActualizarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                if (verificacionCamposVacios()) {
                    // Inicializar los objetos de las entidades
                    ClienteVM clienteVM = new ClienteVM();
                    clienteVM.setIdCliente(cliente);
                    clienteVM.setEncargadoCompra(txtEncargadoCompra.getText().trim());
                    clienteVM.setNombreInstitucion(txtNombreInstitucion.getText().trim());
                    clienteVM.setMunicipio(txtMunicipio.getText().trim());
                    clienteVM.setCodigo(txtCodigoEscuela.getText().trim());

                    // Guardar cliente
                    boolean g1 = clienteC.actualizarCliente(clienteVM);
                    if (!g1) {
                        throw new Exception("Error al guardar el cliente");
                    }

                    FechaErVM fechaErVM = new FechaErVM();
                    fechaErVM.setIdFechas(fechas);
                    fechaErVM.setFechaSolicitud(dtmSolicitudCotizacion.getDate());
                    fechaErVM.setFechaCotizacion(dtmCotizacion.getDate());
                    fechaErVM.setFechaOrden(dtmOrdenCompra.getDate());
                    fechaErVM.setFechaRecepcion(dtmRecepcion.getDate());

                    // Guardar fechas
                    boolean g2 = fechaERController.actualizarFechas(fechaErVM);
                    if (!g2) {
                        throw new Exception("Error al guardar las fechas");
                    }

                    OrdenVM ordenVM = new OrdenVM();
                    ordenVM.setIdOrden(_codId);
                    ordenVM.setCodOrden(codOrden);
                    ordenVM.setEncargadoOrden(txtEncargadoOrden.getText().trim());
                    ordenVM.setTotales(Double.parseDouble(txtTotales.getText().trim()));
                    ordenVM.setLimite_cotizacion(dtmLimite.getDate());
                    ordenVM.setFecha_de_entrega(dtmFechaEntrega.getDate());
                    ordenVM.setHora_entrega_desde(txtDesde.getText().trim());
                    ordenVM.setHora_entrega_hasta(txthasta.getText().trim());
                    ordenVM.setTiempo_entrega(txtTiempo.getText().trim());
                    ordenVM.setPlazo_entrega(txtPlazoEntrega.getText().trim());
                    ordenVM.setLugar_entrega(txtLugarEntrega.getText().trim());
                    ordenVM.setVigencia_de_la_cotizacion(txtVigenciaCotizacion.getText().trim());
                    ordenVM.setTiempo_de_garantia(txtGarantia.getText().trim());
                    ordenVM.setClienteId(cliente);
                    ordenVM.setProveedorId(cbxProveedor.getSelectedIndex() + 1);
                    ordenVM.setFechasErId(fechas);

                    // Guardar orden
                    boolean g3 = ordenController.actualizarOrden(ordenVM);
                    if (!g3) {
                        throw new Exception("Error al guardar la orden");
                    }

                    int g4 = 0;
                    modeloTabla = (DefaultTableModel) tblCotizacion.getModel();
                    int filasActualizadas = modeloTabla.getRowCount();
                    if (filasActualizadas != numFilas) {

                        if (!(idEliminacionDetallesArray.isEmpty()) && numIdDetallesIniciales != idDetallesArray.size() && 0 != numNuevosDetalles) {

                            eliminarDetalle();
                            guardarDetalle();

                        } else if (idEliminacionDetallesArray.isEmpty() && numIdDetallesIniciales == idDetallesArray.size() && 0 != numNuevosDetalles) {

                            guardarDetalle();

                        } else {

                            eliminarDetalle();
                        }
                    }

                    g4 = actualizar();

                    //int ordenId = ordenController.obtenerOrden();
                    if (g1 && g2 && g3 && !(g4 == 0)) {
                        JOptionPane.showMessageDialog(this, "Orden Actualizada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                        Limpiar();
                        FrmMain frmMain = new FrmMain();
                        frmMain.setVisible(true);
                        this.setVisible(false);
                    } else {
                        throw new Exception("Error interno detectado");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al registrar la orden: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnActualizarKeyPressed

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            FrmMain frmMain = new FrmMain();

            frmMain.setVisible(true);

            this.dispose();
        }
    }//GEN-LAST:event_btnCancelarKeyPressed

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmSistema(0).setVisible(true);
//            }
//        });
//    }
    private void agregarDetalle() {

        try {
            // Validación de campos vacíos
            String descripcion = txtDescripcion.getText();
            if (descripcion.isEmpty()) {
                throw new IllegalArgumentException("La descripción no puede estar vacía.");
            }

            String strCantidad = txtCantidad.getText();
            if (strCantidad.isEmpty()) {
                throw new IllegalArgumentException("La cantidad no puede estar vacía.");
            }

            String strPrecioUnitario = txtPrecioU.getText();
            if (strPrecioUnitario.isEmpty()) {
                throw new IllegalArgumentException("El precio unitario no puede estar vacío.");
            }

            String unidadMedida = txtUnidadM.getText();
            if (unidadMedida.isEmpty()) {
                throw new IllegalArgumentException("La unidad de medida no puede estar vacía.");
            }

            // Validación de números
            int cantidad;
            try {
                cantidad = Integer.parseInt(strCantidad);
                if (cantidad <= 0) {
                    throw new IllegalArgumentException("La cantidad debe ser un número positivo.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("La cantidad debe ser un número válido.");
            }

            double precioUnitario;
            try {
                precioUnitario = Double.parseDouble(strPrecioUnitario);
                if (precioUnitario <= 0) {
                    throw new IllegalArgumentException("El precio unitario debe ser un número positivo.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("El precio unitario debe ser un número válido.");
            }

            // Cálculo de precio total
            double precioTotal = cantidad * precioUnitario;
            DecimalFormat formato = new DecimalFormat("0.00");
            String PrecioUnitariostr = formato.format(precioUnitario);
            String PrecioTotaltr = formato.format(precioTotal);
            

            // Agregar fila a la tabla
            int numeroItem = tblCotizacion.getRowCount() + 1;
            modeloTabla = (DefaultTableModel) tblCotizacion.getModel();
            Object[] filaAgregar = {numeroItem, cantidad, unidadMedida, descripcion, PrecioUnitariostr, PrecioTotaltr};
            modeloTabla.addRow(filaAgregar);
            numNuevosDetalles++;

            // Calcular totales
            double totales = 0;
            int numFilas = modeloTabla.getRowCount();
            for (int fila = 0; fila < numFilas; fila++) { 
                String totalP = String.valueOf(modeloTabla.getValueAt(fila, modeloTabla.getColumnCount() - 1));
                double pTotal = Double.parseDouble(totalP);
                totales += pTotal;
            }
            String Totalestr = String.valueOf(formato.format(totales));

            txtTotales.setText(String.valueOf(Totalestr));
            txtDescripcion.setText("");
            txtCantidad.setText("");
            txtPrecioU.setText("");

        } catch (IllegalArgumentException e) {
            // Manejo de excepciones y mostrar mensaje de error
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnActualizarDetalle;
    private javax.swing.JButton btnAgregarDetalle;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarDetalle;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiarTodo;
    private javax.swing.JComboBox<String> cbxProveedor;
    private com.toedter.calendar.JDateChooser dtmCotizacion;
    private com.toedter.calendar.JDateChooser dtmFechaEntrega;
    private com.toedter.calendar.JDateChooser dtmLimite;
    private com.toedter.calendar.JDateChooser dtmOrdenCompra;
    private com.toedter.calendar.JDateChooser dtmRecepcion;
    private com.toedter.calendar.JDateChooser dtmSolicitudCotizacion;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCotizacion;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigoEscuela;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtEncargadoOrden;
    private javax.swing.JTextField txtGarantia;
    private javax.swing.JTextField txtLugarEntrega;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtNombreInstitucion;
    private javax.swing.JTextField txtPlazoEntrega;
    private javax.swing.JTextField txtPrecioU;
    private javax.swing.JTextField txtTiempo;
    private javax.swing.JTextField txtTotales;
    private javax.swing.JTextField txtUnidadM;
    private javax.swing.JTextField txtVigenciaCotizacion;
    private javax.swing.JTextField txthasta;
    // End of variables declaration//GEN-END:variables

    private int actualizar() {

        DefaultTableModel modeloTabla = (DefaultTableModel) tblCotizacion.getModel();
        int g4 = 0;

        int hasta = idDetallesArray.size();
        int hasta1 = modeloTabla.getRowCount();

        for (int i = 0; i < hasta; i++) {

            Object objId = idDetallesArray.get(i);
            int idDetalleOrden = (Integer) objId;

            DetalleOrdenVM detalleOrdenVM = new DetalleOrdenVM();
            detalleOrdenVM.setIdDetalleOrden(idDetalleOrden);
            detalleOrdenVM.setNumArticulo((Integer) modeloTabla.getValueAt(i, 0));
            detalleOrdenVM.setCantidad((Integer) modeloTabla.getValueAt(i, 1));
            detalleOrdenVM.setUnidadMedida((String) modeloTabla.getValueAt(i, 2));
            detalleOrdenVM.setDescripcionArticulo((String) modeloTabla.getValueAt(i, 3));
            detalleOrdenVM.setPrecioUnitario((Double) modeloTabla.getValueAt(i, 4));
            detalleOrdenVM.setPrecioTotal((Double) modeloTabla.getValueAt(i, 5));
            detalleOrdenVM.setOrdenId(_codId);

            boolean guardado = detalleOrdenController.actualizarDetalleOrden(detalleOrdenVM);
            g4++;
        }

        return g4;
    }

    private int guardarDetalle() {

        int aPartirDe = idDetallesArray.size();
        int hasta = modeloTabla.getRowCount();

        DefaultTableModel modeloTabla = (DefaultTableModel) tblCotizacion.getModel();
        int g4 = 0;

        for (int fila = aPartirDe; fila < hasta; fila++) {

            DetalleOrdenVM detalleOrdenVM = new DetalleOrdenVM();
            detalleOrdenVM.setNumArticulo((Integer) modeloTabla.getValueAt(fila, 0));
            detalleOrdenVM.setCantidad((Integer) modeloTabla.getValueAt(fila, 1));
            detalleOrdenVM.setUnidadMedida((String) modeloTabla.getValueAt(fila, 2));
            detalleOrdenVM.setDescripcionArticulo((String) modeloTabla.getValueAt(fila, 3));
            detalleOrdenVM.setPrecioUnitario((Double) modeloTabla.getValueAt(fila, 4));
            detalleOrdenVM.setPrecioTotal((Double) modeloTabla.getValueAt(fila, 5));
            detalleOrdenVM.setOrdenId(_codId);

            boolean guardado = detalleOrdenController.guardarDetalleOrden(detalleOrdenVM);

            g4++;
        }

        return g4;
    }

    private void eliminarDetalle() {

        for (Object it : idEliminacionDetallesArray) {

            int idDetalle = (Integer) it;
            detalleOrdenController.eliminarDetalle(idDetalle);
        }
    }

    private String generarCodigo() {

        /*Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActualS = formatoFecha.format(fechaActual);
        String fechaSinBarras = fechaActualS.replaceAll("/", "");*/
        UUID uuid = UUID.randomUUID();

        return txtEncargadoCompra.getText() + uuid;
    }

    private boolean camposProductoCompletos() {

        return !txtEncargadoCompra.getText().isEmpty()
                && !txtNombreInstitucion.getText().isEmpty()
                && !txtMunicipio.getText().isEmpty();
    }

    private boolean verificacionCamposVacios() {

        DefaultTableModel modeloTabla = (DefaultTableModel) tblCotizacion.getModel();
        int num = modeloTabla.getRowCount();

        return !txtEncargadoCompra.getText().isEmpty()
                && !txtNombreInstitucion.getText().isEmpty()
                && !txtMunicipio.getText().isEmpty()
                && (dtmSolicitudCotizacion.getDate() != null)
                && (dtmCotizacion.getDate() != null)
                && (dtmOrdenCompra.getDate() != null)
                && (dtmRecepcion.getDate() != null)
                && (num != 0);
    }

    private void Limpiar() {

        txtCantidad.setText("");
        txtDescripcion.setText("");
        txtPrecioU.setText("");
        //txtUnidadM.setText("");

    }

}
