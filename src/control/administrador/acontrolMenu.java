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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.administrador.modeloActividad;
import modelo.administrador.modeloDestino;
import modelo.administrador.modeloEstiloViaje;
import modelo.administrador.modeloSitioTuristico;
import modelo.administrador.modeloTransporte;
import modelo.administrador.modeloUsuario;
import modelo.modeloLogin;
import vistas.administrador.avistaActividades;
import vistas.administrador.avistaDestino;
import vistas.administrador.avistaDestinoActividades;
import vistas.administrador.avistaDestinoTipoSitio;
import vistas.administrador.avistaDestinoTransportes;
import vistas.administrador.avistaEstiloViaje;
import vistas.administrador.avistaMenu;
import vistas.administrador.avistaTipoSitio;
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
        this.vista.setTitle("Fleetock");
         this.vista.setIconImage(new ImageIcon(getClass().getResource("../../images/logo_55px.png")).getImage());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(this.vista.btnActividad == e.getSource())
        {
            modeloActividad modelo = new modeloActividad();
            avistaActividades avActividades = new avistaActividades();
            acontrolActividad actividades = new acontrolActividad(modelo, avActividades);
            CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, avActividades);
            actividades.iniciarVista();
        }
        
        if(this.vista.btnDestino == e.getSource())
        {
            avistaDestino avDestinos = new avistaDestino();
            avistaDestinoTipoSitio avDestinosTipoSitio = new avistaDestinoTipoSitio();
            avistaDestinoActividades avDestinosActividades = new avistaDestinoActividades();
            avistaDestinoTransportes avDestinosTransportes = new avistaDestinoTransportes();
            modeloDestino modelo = new modeloDestino();
            acontrolDestinos acDestinos = new acontrolDestinos(modelo, avDestinos, vista, avDestinosTipoSitio, avDestinosActividades, avDestinosTransportes);
            CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, avDestinos);
            acDestinos.iniciarVistaDestino();
        }
        
        if(this.vista.btnEstiloViaje == e.getSource())
        {
            modeloEstiloViaje modelo = new modeloEstiloViaje();
            avistaEstiloViaje avEstiloViaje = new avistaEstiloViaje();
            acontrolEstiloViaje acEstiloViaje = new acontrolEstiloViaje(modelo, avEstiloViaje);
            CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, avEstiloViaje);
            acEstiloViaje.iniciarVista();
        }
        
        if(this.vista.btnTransporte == e.getSource())
        {
            avistaTransporte avTransporte = new avistaTransporte();
            modeloTransporte modelo = new modeloTransporte();
            acontrolTransportes acTransportes = new acontrolTransportes(modelo, avTransporte);
            CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, avTransporte);
            acTransportes.iniciarVista();
        }
        
        if(this.vista.btnSitios == e.getSource())
        {
            modeloSitioTuristico modelo = new modeloSitioTuristico();
            avistaTipoSitio avSitiosTuristicos = new avistaTipoSitio();
            acontrolSitioTuristico acSitioTuristico = new acontrolSitioTuristico(modelo, avSitiosTuristicos);
            CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, avSitiosTuristicos);
            acSitioTuristico.iniciarVista();
        }
        
        if(this.vista.btnUsuarios == e.getSource())
        {
            avistaUsuarios avUsuarios = new avistaUsuarios();
            modeloUsuario modelo = new modeloUsuario();
            acontrolUsuarios acUsuarios = new acontrolUsuarios(modelo, avUsuarios);
            CambiaPanel cambiar = new CambiaPanel(vista.panelCambiante, avUsuarios);
            acUsuarios.iniciarVista();
        }
        
        if(this.vista.btnSalir == e.getSource())
        {
            if (JOptionPane.showConfirmDialog(vista,
                "¿Estás seguro que deseas cerrar sesión?", "Fleetock",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                vistaLogin vLogin = new vistaLogin();
                modeloLogin modelologin = new modeloLogin();
                controlLogin cLogin = new controlLogin(vLogin,modelologin);
                this.vista.setVisible(false);
                cLogin.iniciarVista();
            } 
        }
    }
}
