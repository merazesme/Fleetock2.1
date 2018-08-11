/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.administrador;

import control.CambiaPanel;
import control.controlLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.modeloLogin;
import vistas.administrador.avistaActividades;
import vistas.administrador.avistaDestino;
import vistas.administrador.avistaEstiloViaje;
import vistas.administrador.avistaMenu;
import vistas.administrador.avistaSitiosTuristicos;
import vistas.administrador.avistaTransporte;
import vistas.administrador.avistaUsuarios;
import vistas.vistaLogin;

/**
 *
 * @author Holi
 */
public class acontrolMenu implements ActionListener{

    avistaMenu vista;
    
    public acontrolMenu(avistaMenu vista)
    {
        this.vista=vista;
        this.vista.btnActividad.addActionListener(this);
        this.vista.btnDestino.addActionListener(this);
        this.vista.btnEstiloViaje.addActionListener(this);
        this.vista.btnSitios.addActionListener(this);
        this.vista.btnUsuarios.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
        this.vista.btnTransporte.addActionListener(this);
    }
    public void iniciarVista()
    {
        this.vista.setVisible(true);
        this.vista.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(this.vista.btnActividad == e.getSource())
        {
            avistaActividades avActividades = new avistaActividades();
            acontrolActividades actividades = new acontrolActividades(avActividades, vista);
            CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, avActividades);
        }
        
        if(this.vista.btnDestino == e.getSource())
        {
            avistaDestino avDestinos = new avistaDestino();
            acontrolDestinos acDestinos = new acontrolDestinos(avDestinos, vista);
            CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, avDestinos);
        }
        
        if(this.vista.btnEstiloViaje == e.getSource())
        {
            avistaEstiloViaje avEstiloViaje = new avistaEstiloViaje();
            acontrolEstiloViaje acEstiloViaje = new acontrolEstiloViaje(avEstiloViaje, vista);
            CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, avEstiloViaje);
        }
        
        if(this.vista.btnTransporte == e.getSource())
        {
            avistaTransporte avTransporte = new avistaTransporte();
            acontrolTransportes acTransportes = new acontrolTransportes(avTransporte, vista);
            CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, avTransporte);
        }
        
        if(this.vista.btnSitios == e.getSource())
        {
            avistaSitiosTuristicos avSitiosTuristicos = new avistaSitiosTuristicos();
            acontrolSitioTuristico acSitioTuristico = new acontrolSitioTuristico(avSitiosTuristicos, vista);
            CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, avSitiosTuristicos);
        }
        
        if(this.vista.btnUsuarios == e.getSource())
        {
            avistaUsuarios avUsuarios = new avistaUsuarios();
            acontrolUsuarios acUsuarios = new acontrolUsuarios(avUsuarios, vista);
            CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, avUsuarios);
        }
        
        if(this.vista.btnSalir == e.getSource())
        {
            this.vista.dispose();
            vistaLogin vistaLogin = new vistaLogin();
            modeloLogin modelologin = new modeloLogin();
            controlLogin controLogin = new controlLogin(vistaLogin,modelologin, null, vista);
            controLogin.iniciarVista();
        }
    
    }
    
}
