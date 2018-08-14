/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.administrador.acontrolMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.modeloLogin;
import vistas.administrador.avistaMenu;
import vistas.vistaLogin;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlLogin implements ActionListener{
    
    private vistaLogin vista;
    private modeloLogin modelo;
    public String usu="";
        
    public controlLogin()
    {
        this.usu="";
    }
  
    public controlLogin(vistaLogin vista, modeloLogin modelo)
    {
        this.vista=vista;
        this.modelo=modelo;
        this.vista.btnISLogin.addActionListener(this);
        this.vista.texto_usuario.addActionListener(this);
        this.vista.texto_contra.addActionListener(this);
        //this.vista.btnISLogin.addKeyListener(this);
        this.vista.setTitle("Fleetock: Inicio de sesión");
        this.vista.setIconImage(new ImageIcon(getClass().getResource("../images/logo_55px.png")).getImage());
        this.vista.setSize(916, 490);
        this.vista.setResizable(false);
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
                case 1:
                    //SI ES USUARIO SE ABRE ESTE PANEL
                    JOptionPane.showMessageDialog(null, "Bienvenido " + usu);
                    this.vista.dispose();               
                    
                    vistaPrincipal vistaPrincipa1 = new vistaPrincipal();
                    controlPrincipal controlPrincipal = new controlPrincipal(vistaPrincipa1, modelo.jalarIdUsuario(usu, contra));
                    controlPrincipal.iniciarVista();
                    break;
                case 2:
                    //SI ES ADMINISTRADOR SE ABRE ESTE PANEL
                    JOptionPane.showMessageDialog(null, "Administrador " + usu);
                    this.vista.dispose();
                    avistaMenu avMenu = new avistaMenu();
                    acontrolMenu acMenu = new acontrolMenu(avMenu);
                    acMenu.iniciarVista();
                    break;
                case 3:
                    //MENSAJE 
                    JOptionPane.showMessageDialog(null, "USUARIO/CONTRASEÑA INCORRECTOS");
                    break;
                default:

                    break;
            }
        }
        }
}