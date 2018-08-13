/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.administrador;

/**
 *
 * @author Hector
 */
public class avistaDestinoTransportes extends javax.swing.JPanel {

    /**
     * Creates new form avistaDestino22
     */
    public avistaDestinoTransportes() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelNuevo2 = new javax.swing.JPanel();
        panelOpciones = new javax.swing.JPanel();
        nombreDestino = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_Transportes = new javax.swing.JTable();
        jcb_Transportes = new javax.swing.JComboBox<>();
        btnActividades1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jcb_EstiloViaje = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btn_Cancelar = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btn_AgregarTransportes = new javax.swing.JButton();
        btn_Finalizar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_Costo = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        PanelNuevo2.setBackground(new java.awt.Color(255, 255, 255));
        PanelNuevo2.setLayout(new javax.swing.BoxLayout(PanelNuevo2, javax.swing.BoxLayout.LINE_AXIS));

        panelOpciones.setBackground(new java.awt.Color(255, 255, 255));

        nombreDestino.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        nombreDestino.setForeground(new java.awt.Color(102, 102, 102));
        nombreDestino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Map_Marker_30px.png"))); // NOI18N
        nombreDestino.setBorder(null);
        nombreDestino.setBorderPainted(false);
        nombreDestino.setContentAreaFilled(false);
        nombreDestino.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nombreDestino.setDefaultCapable(false);
        nombreDestino.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nombreDestino.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_To_Do_45px.png"))); // NOI18N
        nombreDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreDestinoActionPerformed(evt);
            }
        });

        tbl_Transportes = new javax.swing.JTable()
        {   public boolean isCellEditable( int rowIndex, int colIndex)
            {   return false;
            }
        };
        tbl_Transportes.setFont(new java.awt.Font("Candara", 0, 12)); // NOI18N
        tbl_Transportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_Transporte", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_Transportes.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tbl_Transportes);

        jcb_Transportes.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N

        btnActividades1.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        btnActividades1.setForeground(new java.awt.Color(102, 102, 102));
        btnActividades1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Public_Transportation_30px.png"))); // NOI18N
        btnActividades1.setText("Transportes");
        btnActividades1.setBorder(null);
        btnActividades1.setBorderPainted(false);
        btnActividades1.setContentAreaFilled(false);
        btnActividades1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActividades1.setDefaultCapable(false);
        btnActividades1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnActividades1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_To_Do_45px.png"))); // NOI18N
        btnActividades1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActividades1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel6.setText("Transporte:");

        jLabel8.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel8.setText("Estilo de Viaje:");

        jcb_EstiloViaje.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jcb_EstiloViaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_EstiloViajeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_30px.png"))); // NOI18N
        jLabel5.setText("Transporte");

        txtBuscar.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N

        btn_Cancelar.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        btn_Cancelar.setText("Cancelar");
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarActionPerformed(evt);
            }
        });

        btn_Eliminar.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });

        btn_AgregarTransportes.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        btn_AgregarTransportes.setText("Agregar");
        btn_AgregarTransportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarTransportesActionPerformed(evt);
            }
        });

        btn_Finalizar.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        btn_Finalizar.setText("Finalizar");

        jLabel7.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel7.setText("Costo:");

        txt_Costo.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        txt_Costo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CostoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcionesLayout = new javax.swing.GroupLayout(panelOpciones);
        panelOpciones.setLayout(panelOpcionesLayout);
        panelOpcionesLayout.setHorizontalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOpcionesLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btnActividades1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelOpcionesLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelOpcionesLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelOpcionesLayout.createSequentialGroup()
                                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelOpcionesLayout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jcb_Transportes, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(txt_Costo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcb_EstiloViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btn_AgregarTransportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_Cancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_Finalizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(89, 89, 89))))
                    .addGroup(panelOpcionesLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(nombreDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelOpcionesLayout.setVerticalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombreDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActividades1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcb_Transportes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_AgregarTransportes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOpcionesLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Costo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelOpcionesLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btn_Eliminar)))
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOpcionesLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcb_EstiloViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelOpcionesLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btn_Cancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Finalizar)
                        .addGap(22, 22, 22))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(738, 738, 738)
                .addComponent(PanelNuevo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelNuevo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nombreDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreDestinoActionPerformed

    private void btnActividades1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActividades1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActividades1ActionPerformed

    private void jcb_EstiloViajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_EstiloViajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcb_EstiloViajeActionPerformed

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_CancelarActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void btn_AgregarTransportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarTransportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_AgregarTransportesActionPerformed

    private void txt_CostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CostoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel PanelNuevo2;
    public javax.swing.JButton btnActividades1;
    public javax.swing.JButton btn_AgregarTransportes;
    public javax.swing.JButton btn_Cancelar;
    public javax.swing.JButton btn_Eliminar;
    public javax.swing.JButton btn_Finalizar;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane6;
    public javax.swing.JComboBox<String> jcb_EstiloViaje;
    public javax.swing.JComboBox<String> jcb_Transportes;
    public javax.swing.JButton nombreDestino;
    public javax.swing.JPanel panelOpciones;
    public javax.swing.JTable tbl_Transportes;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JTextField txt_Costo;
    // End of variables declaration//GEN-END:variables
}
