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
public class avistaUsuarios extends javax.swing.JPanel {

    /**
     * Creates new form Adriana
     */
    public avistaUsuarios() {
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

        jPanel3 = new javax.swing.JPanel();
        lbl_Usuario = new javax.swing.JLabel();
        txt_Usuario = new javax.swing.JTextField();
        txt_Buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Login = new javax.swing.JTable();
        lbl_Estatus = new javax.swing.JLabel();
        txt_Estatus = new javax.swing.JTextField();
        lbl_Tipo = new javax.swing.JLabel();
        txt_Tipo = new javax.swing.JTextField();
        btn_Actualizar = new javax.swing.JButton();
        lbl_Contraseña = new javax.swing.JLabel();
        txt_Contraseña = new javax.swing.JTextField();
        lbl_UsuarioBuscar = new javax.swing.JLabel();
        btnActividades = new javax.swing.JButton();
        btn_BorrarTexto1 = new javax.swing.JButton();
        btnGenerarR = new javax.swing.JButton();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lbl_Usuario.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        lbl_Usuario.setText("Usuario");

        txt_Usuario.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        txt_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_UsuarioActionPerformed(evt);
            }
        });

        txt_Buscar.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        txt_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BuscarActionPerformed(evt);
            }
        });

        tbl_Login.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        tbl_Login.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idLogin", "Usuario", "Contraseña", "Estatus", "Tipo"
            }
        ));
        tbl_Login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_LoginMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Login);

        lbl_Estatus.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        lbl_Estatus.setText("Estatus");

        txt_Estatus.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        txt_Estatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_EstatusActionPerformed(evt);
            }
        });

        lbl_Tipo.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        lbl_Tipo.setText("Tipo");

        txt_Tipo.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        txt_Tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TipoActionPerformed(evt);
            }
        });

        btn_Actualizar.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        btn_Actualizar.setText("Actualizar");
        btn_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ActualizarActionPerformed(evt);
            }
        });

        lbl_Contraseña.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        lbl_Contraseña.setText("Contraseña");

        txt_Contraseña.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        txt_Contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ContraseñaActionPerformed(evt);
            }
        });

        lbl_UsuarioBuscar.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        lbl_UsuarioBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_30px.png"))); // NOI18N
        lbl_UsuarioBuscar.setText("Usuario");

        btnActividades.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        btnActividades.setForeground(new java.awt.Color(102, 102, 102));
        btnActividades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Cat_Profile_30px_1.png"))); // NOI18N
        btnActividades.setText("Usuarios");
        btnActividades.setBorder(null);
        btnActividades.setBorderPainted(false);
        btnActividades.setContentAreaFilled(false);
        btnActividades.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActividades.setDefaultCapable(false);
        btnActividades.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnActividades.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_To_Do_45px.png"))); // NOI18N
        btnActividades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActividadesActionPerformed(evt);
            }
        });

        btn_BorrarTexto1.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        btn_BorrarTexto1.setText("Borrar texto");

        btnGenerarR.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        btnGenerarR.setText("Generar reporte");
        btnGenerarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnActividades, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Estatus, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Estatus, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117)
                .addComponent(btn_BorrarTexto1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117)
                .addComponent(btn_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(640, 640, 640)
                .addComponent(btnGenerarR, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbl_UsuarioBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnActividades, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbl_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbl_Estatus)
                        .addGap(2, 2, 2)
                        .addComponent(txt_Estatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_BorrarTexto1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbl_Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbl_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(btnGenerarR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_UsuarioBuscar)
                    .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_UsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_UsuarioActionPerformed

    private void txt_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BuscarActionPerformed

    private void tbl_LoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_LoginMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_LoginMouseClicked

    private void txt_EstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_EstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EstatusActionPerformed

    private void txt_TipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TipoActionPerformed

    private void btn_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ActualizarActionPerformed

    private void txt_ContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ContraseñaActionPerformed

    private void btnActividadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActividadesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActividadesActionPerformed

    private void btnGenerarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenerarRActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActividades;
    public javax.swing.JButton btnGenerarR;
    public javax.swing.JButton btn_Actualizar;
    public javax.swing.JButton btn_BorrarTexto1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lbl_Contraseña;
    public javax.swing.JLabel lbl_Estatus;
    public javax.swing.JLabel lbl_Tipo;
    public javax.swing.JLabel lbl_Usuario;
    public javax.swing.JLabel lbl_UsuarioBuscar;
    public javax.swing.JTable tbl_Login;
    public javax.swing.JTextField txt_Buscar;
    public javax.swing.JTextField txt_Contraseña;
    public javax.swing.JTextField txt_Estatus;
    public javax.swing.JTextField txt_Tipo;
    public javax.swing.JTextField txt_Usuario;
    // End of variables declaration//GEN-END:variables
}