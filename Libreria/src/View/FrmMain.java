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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FrmMain extends javax.swing.JFrame {

    // Objetos globales *******************************************************
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

    //array y objetos gloables
    ArrayList<Object> listaId = new ArrayList<>();

    //
    DefaultTableModel modeloTabla = new DefaultTableModel();

    public FrmMain() {
        initComponents();
        poblarTablaOrden();
        this.setLocationRelativeTo(null);
        this.setTitle("Creadora de DOC");
//        tblOrdenes.setRowSelectionInterval(0, 0);
//        tblOrdenes.requestFocus();

// Formatear la fecha
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d 'DE' MMMM 'DE' yyyy", new Locale("es", "ES"));
        String fechaActual = dateFormat.format(calendar.getTime()).toUpperCase();
        lblFecha.setText(fechaActual);
    }

    public void poblarTablaOrden() {

        listaId.clear();
        ArrayList<TablaOrdenVM> ordenesVM = ordenController.listarOrden();
        modeloTabla = (DefaultTableModel) tblOrdenes.getModel();
        modeloTabla.setRowCount(0); // Limpiar el modeloTabla de tabla antes de agregar nuevas filas
        for (TablaOrdenVM cl : ordenesVM) {

            Object[] ob = new Object[3]; // Crear el arreglo dentro del bucle
            ob[0] = cl.getEncargadoCompra() + " " + cl.getNombreInstitucion();
            ob[1] = cl.getFechaOrden();
            ob[2] = cl.getFechaRecepcion();

            modeloTabla.addRow(ob);

            int id = cl.getIdOrden();
            listaId.add(id);
        }
        tblOrdenes.setModel(modeloTabla); // Establecer el modeloTabla de tabla una vez que se han agregado todas las filas
    }

    private TablaOrdenVM importarDatos(int id) {

        return ordenController.datosOrden(id);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrdenes = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalles = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtTotales = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btnCrearNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCrearDocumento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Ordenes"));

        tblOrdenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Cliente", "Fecha Orden", "Fecha de recepcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblOrdenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrdenesMouseClicked(evt);
            }
        });
        tblOrdenes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblOrdenesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrdenes);
        if (tblOrdenes.getColumnModel().getColumnCount() > 0) {
            tblOrdenes.getColumnModel().getColumn(0).setPreferredWidth(250);
            tblOrdenes.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblOrdenes.getColumnModel().getColumn(2).setPreferredWidth(40);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Detalles de Orden"));

        tblDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N", "Cantidad", "U. de medida", "Descripcion", "Precio Unitario", "Precio Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDetalles);
        if (tblDetalles.getColumnModel().getColumnCount() > 0) {
            tblDetalles.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblDetalles.getColumnModel().getColumn(1).setPreferredWidth(25);
            tblDetalles.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblDetalles.getColumnModel().getColumn(3).setPreferredWidth(200);
            tblDetalles.getColumnModel().getColumn(4).setPreferredWidth(45);
            tblDetalles.getColumnModel().getColumn(5).setPreferredWidth(45);
        }

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        jLabel6.setText("TOTALES:");

        txtTotales.setEditable(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 437, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("PRINCIPAL", jPanel3);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel1.setText("LIBRERIAS Y ACCESORIOS DANY");

        lblFecha.setBackground(new java.awt.Color(153, 204, 255));
        lblFecha.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Opciones"));

        btnCrearNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new_file256_25213.png"))); // NOI18N
        btnCrearNuevo.setText("Crear Nuevo");
        btnCrearNuevo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 51), 2, true));
        btnCrearNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearNuevoActionPerformed(evt);
            }
        });
        btnCrearNuevo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCrearNuevoKeyPressed(evt);
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1486564394-edit_81508.png"))); // NOI18N
        btnActualizar.setText("Editar");
        btnActualizar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 2, true));
        btnActualizar.setMaximumSize(new java.awt.Dimension(106, 36));
        btnActualizar.setMinimumSize(new java.awt.Dimension(106, 36));
        btnActualizar.setPreferredSize(new java.awt.Dimension(106, 36));
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

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 51), 2, true));
        btnEliminar.setPreferredSize(new java.awt.Dimension(106, 36));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        btnEliminar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEliminarKeyPressed(evt);
            }
        });

        btnCrearDocumento.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnCrearDocumento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Word_Mac_23563.png"))); // NOI18N
        btnCrearDocumento.setText("GENERAR DOCUMENTO");
        btnCrearDocumento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnCrearDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearDocumentoActionPerformed(evt);
            }
        });
        btnCrearDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCrearDocumentoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnCrearDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnCrearNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(btnCrearDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnCrearNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*  EVENTO PARA CREAR NUEVA ORDEN*/
    private void btnCrearNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearNuevoActionPerformed
        // TODO add your handling code here:

        FrmSistema FrmSistema = new FrmSistema(0);

        FrmSistema.setVisible(true);

        this.setVisible(false);

    }//GEN-LAST:event_btnCrearNuevoActionPerformed

    /**
     * ************************************************************************************************************************************************************************************
     */
    private void btnCrearDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearDocumentoActionPerformed
        // TODO add your handling code here:

        crearDocumento();
    }//GEN-LAST:event_btnCrearDocumentoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:        
        actualizar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:       
        eliminar();

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tblOrdenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrdenesMouseClicked
        // TODO add your handling code here:
        llenarDetalles();
    }//GEN-LAST:event_tblOrdenesMouseClicked

    private void tblOrdenesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblOrdenesKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            llenarDetalles();
        }
        
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){
            
            btnCrearNuevo.requestFocus();
        }
    }//GEN-LAST:event_tblOrdenesKeyPressed

    private void btnCrearDocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCrearDocumentoKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            crearDocumento();
        }

    }//GEN-LAST:event_btnCrearDocumentoKeyPressed

    private void btnCrearNuevoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCrearNuevoKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            FrmSistema FrmSistema = new FrmSistema(0);

            FrmSistema.setVisible(true);

            this.setVisible(false);
        }

    }//GEN-LAST:event_btnCrearNuevoKeyPressed

    private void btnActualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnActualizarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            actualizar();

        }
    }//GEN-LAST:event_btnActualizarKeyPressed

    private void btnEliminarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEliminarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            
            eliminar();
        }
    }//GEN-LAST:event_btnEliminarKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMain().setVisible(true);
            }
        });
    }

    public void eliminarTodasLasFilas(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    public void eliminarFilasTblOrden(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    private void eliminar() {

        int fila = tblOrdenes.getSelectedRow();
        if (fila != -1) { // Verifica si hay una fila seleccionada
            try {

                Object objId = listaId.get(fila);
                int idOrden = (Integer) objId;

                TablaOrdenVM ordenVM = importarDatos(idOrden);
                int clienteId = ordenVM.getClienteId();
                int fechasErId = ordenVM.getFechasErId();

                detalleOrdenController.eliminarTotalDetalle(idOrden);
                ordenController.eliminarOrden(idOrden);
                clienteC.eliminarOrden(clienteId);
                fechaERController.eliminarFecha(fechasErId);
                eliminarFilasTblOrden(tblOrdenes);
                eliminarTodasLasFilas(tblDetalles);
                poblarTablaOrden();
                JOptionPane.showMessageDialog(this, "Orden Eliminada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
//                tblOrdenes.setRowSelectionInterval(0, 0);
//                tblOrdenes.requestFocus();

            } catch (NullPointerException ex) {
                // Manejar la excepción en caso de que no se pueda obtener un valor de la fila seleccionada
                JOptionPane.showMessageDialog(null, "Error al obtener los datos de la fila seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila antes de eliminar los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizar() {

        int fila = tblOrdenes.getSelectedRow();
        if (fila != -1) { // Verifica si hay una fila seleccionada
            try {

                Object objId = listaId.get(fila);
                int idOrden = (Integer) objId;

                int numeroParaPasar = idOrden; // Ejemplo de número que deseas pasar

                FrmSistema frmSistema = new FrmSistema(numeroParaPasar);

                frmSistema.setVisible(true);

                this.setVisible(false);

            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Error al obtener los datos de la fila seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila antes de crear El documento.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void llenarDetalles() {

        int fila = tblOrdenes.getSelectedRow();
        if (fila != -1) { // Verifica si hay una fila seleccionada
            try {

                double totales = 0;
                Object objId = listaId.get(fila);
                int idOrden = (Integer) objId;

                ArrayList<DetalleOrdenVM> detalleOrdenesVM = detalleOrdenController.listarDetalleOrden(idOrden);
                modeloTabla = (DefaultTableModel) tblDetalles.getModel();
                modeloTabla.setRowCount(0); // Limpiar el modeloTabla de tabla antes de agregar nuevas filas
                for (DetalleOrdenVM cl : detalleOrdenesVM) {

                    Object[] ob = new Object[6]; // Crear el arreglo dentro del bucle
                    ob[0] = cl.getNumArticulo();
                    ob[1] = cl.getCantidad();
                    ob[2] = cl.getUnidadMedida();
                    ob[3] = cl.getDescripcionArticulo();
                    ob[4] = cl.getPrecioUnitario();
                    ob[5] = cl.getPrecioTotal();
                    totales += cl.getPrecioTotal();

                    modeloTabla.addRow(ob);

                }

                tblDetalles.setModel(modeloTabla); // Establecer el modeloTabla de tabla una vez que se han agregado todas las filas
                txtTotales.setText(String.valueOf(totales));
                btnCrearDocumento.requestFocus();

            } catch (NullPointerException ex) {
                // Manejar la excepción en caso de que no se pueda obtener un valor de la fila seleccionada
                JOptionPane.showMessageDialog(null, "Error al obtener los datos de la fila seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error Interno Comunquese con soporte", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void crearDocumento() {

        int fila = tblOrdenes.getSelectedRow();
        if (fila != -1) { // Verifica si hay una fila seleccionada
            try {

                Object objId = listaId.get(fila);
                int idOrden = (Integer) objId;

                ExportacionWord exportador = new ExportacionWord();
                exportador.exportarTabla(idOrden);
                tblOrdenes.setRowSelectionInterval(0, 0);
                tblOrdenes.requestFocus();

            } catch (NullPointerException ex) {
                // Manejar la excepción en caso de que no se pueda obtener un valor de la fila seleccionada
                JOptionPane.showMessageDialog(null, "Error al obtener los datos de la fila seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila antes de crear El documento.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCrearDocumento;
    private javax.swing.JButton btnCrearNuevo;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JTable tblDetalles;
    private javax.swing.JTable tblOrdenes;
    private javax.swing.JTextField txtTotales;
    // End of variables declaration//GEN-END:variables

}