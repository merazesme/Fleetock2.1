/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.administrador.acontrolMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.administrador.avistaMenu;
import vistas.vistaLogin;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlLogin implements ActionListener{
    
    vistaLogin vista;
    vistaPrincipal vistaPrincipal;
    avistaMenu avmMenu;
  
    public controlLogin(vistaLogin vista, vistaPrincipal vistaPrincipal, avistaMenu avMenu)
    {
        this.vista=vista;
        this.vista.btnISLogin.addActionListener(this);
        this.vistaPrincipal=vistaPrincipal;
        this.avmMenu=avMenu;
    }
    
    public void iniciarVista()
    {
           vista.setVisible(true);
           vista.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(vista.btnISLogin == e.getSource())
        {
            System.out.println("hola");
            vista.setVisible(false);
            vistaPrincipal.setVisible(true);
            vistaPrincipal.setLocationRelativeTo(null);
            
            controlPrincipal controlPrincipal = new controlPrincipal(vistaPrincipal);
            controlPrincipal.iniciarVista();
            
//            acontrolMenu acMenu = new acontrolMenu(avmMenu);
//            acMenu.iniciarVista();
        }
        
        }
    
    
    
}
