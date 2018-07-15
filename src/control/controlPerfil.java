/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.vistaEditarPerfil;
import vistas.vistaEditarViaje;
import vistas.vistaPerfil;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlPerfil implements ActionListener{

    vistaPerfil vista;
    vistaPrincipal vistaPrincipal;
    
    public controlPerfil(vistaPerfil vista, vistaPrincipal vistaPrincipal)
    {
        this.vista=vista;
        this.vistaPrincipal=vistaPrincipal;
        this.vista.btnEditarPerfil.addActionListener(this);
        this.vista.btnModificarViaje.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
        if(this.vista.btnEditarPerfil == e.getSource())
        {
            vistaEditarPerfil vistaEditarPerfil = new vistaEditarPerfil();
            controlEditarPerfil controlEditarPerfil = new controlEditarPerfil(vistaEditarPerfil, vistaPrincipal);
            CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vistaEditarPerfil);
        }
        
        if(this.vista.btnModificarViaje == e.getSource())
        {
            vistaEditarViaje vistaEditarViaje = new vistaEditarViaje();
            controlEditarViaje controlEditarViaje= new controlEditarViaje(vistaEditarViaje, vistaPrincipal);
            CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vistaEditarViaje);
        }
    }
    
}
