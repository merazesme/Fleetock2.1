/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

/**
 *
 * @author Holi
 */
public class vistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form inicio
     */
    public vistaPrincipal() {
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

        panelCambiante = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnInico = new javax.swing.JButton();
        btnViaje = new javax.swing.JButton();
        btnAyuda = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnPerfil = new javax.swing.JButton();
        btnCalculadora = new javax.swing.JButton();
        Cabecera = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelCambiante.setBackground(new java.awt.Color(255, 255, 255));
        panelCambiante.setLayout(new javax.swing.BoxLayout(panelCambiante, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBackground(new java.awt.Color(56, 0, 107));

        btnInico.setBackground(new java.awt.Color(56, 0, 107));
        btnInico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_50px.png"))); // NOI18N
        btnInico.setBorder(null);
        btnInico.setBorderPainted(false);
        btnInico.setContentAreaFilled(false);
        btnInico.setFocusPainted(false);
        btnInico.setFocusable(false);
        btnInico.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_55px.png"))); // NOI18N

        btnViaje.setBackground(new java.awt.Color(56, 0, 107));
        btnViaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Briefcase_50px_4.png"))); // NOI18N
        btnViaje.setBorder(null);
        btnViaje.setBorderPainted(false);
        btnViaje.setContentAreaFilled(false);
        btnViaje.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnViaje.setDefaultCapable(false);
        btnViaje.setFocusPainted(false);
        btnViaje.setFocusable(false);
        btnViaje.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Briefcase_55px.png"))); // NOI18N

        btnAyuda.setBackground(new java.awt.Color(56, 0, 107));
        btnAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png"))); // NOI18N
        btnAyuda.setBorder(null);
        btnAyuda.setBorderPainted(false);
        btnAyuda.setContentAreaFilled(false);
        btnAyuda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAyuda.setDefaultCapable(false);
        btnAyuda.setFocusPainted(false);
        btnAyuda.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Help_55px.png"))); // NOI18N

        btnLogout.setBackground(new java.awt.Color(56, 0, 107));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        btnLogout.setBorder(null);
        btnLogout.setBorderPainted(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setDefaultCapable(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setFocusable(false);
        btnLogout.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Logout_Rounded_Left_55px.png"))); // NOI18N

        btnPerfil.setBackground(new java.awt.Color(56, 0, 107));
        btnPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile.png"))); // NOI18N
        btnPerfil.setBorder(null);
        btnPerfil.setBorderPainted(false);
        btnPerfil.setContentAreaFilled(false);
        btnPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPerfil.setDefaultCapable(false);
        btnPerfil.setFocusPainted(false);
        btnPerfil.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Cat_Profile_55px_1.png"))); // NOI18N

        btnCalculadora.setBackground(new java.awt.Color(56, 0, 107));
        btnCalculadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calculator.png"))); // NOI18N
        btnCalculadora.setBorder(null);
        btnCalculadora.setBorderPainted(false);
        btnCalculadora.setContentAreaFilled(false);
        btnCalculadora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCalculadora.setDefaultCapable(false);
        btnCalculadora.setFocusPainted(false);
        btnCalculadora.setFocusable(false);
        btnCalculadora.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Calculator_55px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCalculadora, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout)
                    .addComponent(btnAyuda))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInico, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnInico, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnPerfil)
                .addGap(48, 48, 48)
                .addComponent(btnViaje)
                .addGap(47, 47, 47)
                .addComponent(btnCalculadora)
                .addGap(47, 47, 47)
                .addComponent(btnAyuda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        Cabecera.setBackground(new java.awt.Color(100, 221, 23));

        jLabel2.setFont(new java.awt.Font("Candara", 2, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("El arte de viajar");

        javax.swing.GroupLayout CabeceraLayout = new javax.swing.GroupLayout(Cabecera);
        Cabecera.setLayout(CabeceraLayout);
        CabeceraLayout.setHorizontalGroup(
            CabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
                .addContainerGap())
        );
        CabeceraLayout.setVerticalGroup(
            CabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CabeceraLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelCambiante, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Cabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelCambiante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(vistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cabecera;
    public javax.swing.JButton btnAyuda;
    public javax.swing.JButton btnCalculadora;
    public javax.swing.JButton btnInico;
    public javax.swing.JButton btnLogout;
    public javax.swing.JButton btnPerfil;
    public javax.swing.JButton btnViaje;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPanel panelCambiante;
    // End of variables declaration//GEN-END:variables
}