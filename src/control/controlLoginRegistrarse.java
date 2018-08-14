/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import modelo.modeloLogin;
import vistas.vistaLogin;
import vistas.vistaLoginRegistrarse;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlLoginRegistrarse implements ActionListener{
    vistaLoginRegistrarse vista;
    vistaLogin vistaLogin = new vistaLogin();
  
    public controlLoginRegistrarse(vistaLoginRegistrarse vista)
    {
        this.vista=vista;
        vista.btnIniciarSesion.addActionListener(this);
        this.vista.setTitle("Fleetock");
        this.vista.setIconImage(new ImageIcon(getClass().getResource("../images/logo_55px.png")).getImage());
        this.vista.setSize(916, 490);
        this.vista.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(vista.btnIniciarSesion == e.getSource())
        {
            vista.setVisible(false);
            vistaLogin.setVisible(true);
            vistaLogin.setLocationRelativeTo(null);
            vistaPrincipal vistaPrincipal = new vistaPrincipal();
            modeloLogin modelologin = new modeloLogin();
            controlLogin controlLogin = new controlLogin(vistaLogin,modelologin);
        }
    }
    
    
}
