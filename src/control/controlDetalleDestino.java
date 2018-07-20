/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.vistaActividades;
import vistas.vistaAgregarViaje;
import vistas.vistaDetallesDestino;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlDetalleDestino implements ActionListener{

    private vistaDetallesDestino vista;
    private vistaPrincipal vistaPrincipal;
    private String idD;
    public controlDetalleDestino(vistaDetallesDestino vista, vistaPrincipal vistaPrincipal, String idD)
    {
        this.vista=vista;
        this.vistaPrincipal=vistaPrincipal;
        this.idD = idD;
        this.vista.btnActividades.addActionListener(this);
        this.vista.btnViaje.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
    if(this.vista.btnActividades == e.getSource())
    {
        
        vistaActividades vActividades = new vistaActividades();
        controlActividades cActividades = new controlActividades(vActividades, vistaPrincipal);
        CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vActividades);
    }
    
    if(this.vista.btnViaje == e.getSource())
    {
        vistaAgregarViaje vAgregarViaje = new vistaAgregarViaje();
        controlAgregarViaje cAgregarViaje = new controlAgregarViaje(vAgregarViaje, vistaPrincipal, idD);
        CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vAgregarViaje);
        
    }
    
    }
    
}
