/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.administrador.acontrolMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.modelologin;
import vistas.administrador.avistaMenu;
import vistas.vistaLogin;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlLogin implements ActionListener{
    
    vistaLogin vista;
    private modelologin modelo;
    vistaPrincipal vistaPrincipal;
    avistaMenu avmMenu;
    public String usu="";
    
       public controlLogin()
    {
        this.usu="";
    }
  
    public controlLogin(vistaLogin vista,modelologin modelo, vistaPrincipal vistaPrincipal, avistaMenu avMenu)
    {
        this.vista=vista;
        this.modelo=modelo;
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
        
        usu = vista.texto_usuario.getText();
        String contra = new String(vista.texto_contra.getPassword());
        
        if(vista.btnISLogin == e.getSource() || vista.texto_usuario == e.getSource() || vista.texto_contra == e.getSource())
        {
              switch (modelo.ingresar(usu, contra)) 
            {
                //SI EL USUARIO ES ADMINISTRADOR SE ABRE ESTE PANEL
                case 1:
                    System.out.println("hola");
                    JOptionPane.showMessageDialog(null, "Bienvenido " + usu);
                    vista.setVisible(false);
                    vistaPrincipal.setVisible(true);
                    vistaPrincipal.setLocationRelativeTo(null);
                                   
                    controlPrincipal controlPrincipal = new controlPrincipal(vistaPrincipal);
                    controlPrincipal.iniciarVista();
                    break;
                case 2:
                    //SI ES USUARIO SE ABRE EL INICIO
                    JOptionPane.showMessageDialog(null, "Administrador " + usu);
                    break;
                case 3:
                    //MENSAJE 
                    JOptionPane.showMessageDialog(null, "USUARIO/CONTRASEÑA INCORRECTOS");
                    break;
                default:

                    break;
            }
            
//            acontrolMenu acMenu = new acontrolMenu(avmMenu);
//            acMenu.iniciarVista();
        }
        
        }
    
    
    
}
