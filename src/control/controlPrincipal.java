/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.modelologin;
import vistas.vistaCalculadora;
import vistas.vistaInicio;
import vistas.vistaLogin;
import vistas.vistaNuevoViajeSS;
import vistas.vistaPerfil;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlPrincipal implements ActionListener{
    
    vistaPrincipal vista;
    public controlPrincipal(vistaPrincipal vista)
    {
        this.vista=vista;
        this.vista.btnInico.addActionListener(this);
        this.vista.btnPerfil.addActionListener(this);
        this.vista.btnViaje.addActionListener(this);
        this.vista.btnCalculadora.addActionListener(this);
        this.vista.btnAyuda.addActionListener(this);
        this.vista.btnLogout.addActionListener(this);
    }
    
    public void iniciarVista()
    {
        vistaInicio vistaInicio = new vistaInicio();
        controlInicio controlInicio = new controlInicio(vistaInicio, vista);
        CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, vistaInicio);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
       if(this.vista.btnInico == e.getSource())
       {
           this.iniciarVista();
       }
       
       if(this.vista.btnPerfil == e.getSource())
       {
           vistaPerfil vistaPerfil = new vistaPerfil();
           controlPerfil controlPerfil = new controlPerfil(vistaPerfil, vista);
           CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, vistaPerfil);
       }
       
       if(this.vista.btnViaje == e.getSource())
       {
           vistaNuevoViajeSS vistaNuevoViajeSS = new vistaNuevoViajeSS();
           controlNuevoViajeSS controlNuevoViajeSS = new controlNuevoViajeSS(vistaNuevoViajeSS, vista);
           CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, vistaNuevoViajeSS);
       }
       
       if(this.vista.btnCalculadora == e.getSource())
       {
           vistaCalculadora vistaCalculadora = new vistaCalculadora();
           controlCalculadora controlCalculadora = new controlCalculadora(vistaCalculadora, vista);
           CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, vistaCalculadora);
       }
       
       if(this.vista.btnLogout == e.getSource())
       {
           this.vista.setVisible(false);
           vistaLogin vistaLogin = new vistaLogin();
           modelologin modelologin = new modelologin();
           controlLogin controLogin = new controlLogin(vistaLogin,modelologin, vista, null);
           controLogin.iniciarVista();
       }
    
    }
    
    
}
