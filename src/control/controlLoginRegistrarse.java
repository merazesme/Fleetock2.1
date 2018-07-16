/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.modelologin;
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
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(vista.btnIniciarSesion == e.getSource())
        {
            vista.setVisible(false);
            vistaLogin.setVisible(true);
            vistaLogin.setLocationRelativeTo(null);
            vistaPrincipal vistaPrincipal = new vistaPrincipal();
            modelologin modelologin = new modelologin();
            controlLogin controlLogin = new controlLogin(vistaLogin,modelologin, vistaPrincipal, null);
        }
        
        }
    
    
}