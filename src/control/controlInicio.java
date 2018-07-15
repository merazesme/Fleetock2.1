/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.vistaDetallesDestino;
import vistas.vistaInicio;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlInicio implements ActionListener{
    
    vistaInicio vista;
    vistaPrincipal vPrincipal;
    
    public controlInicio(vistaInicio vista, vistaPrincipal vPrincipal)
    {
        this.vista = vista;
        this.vPrincipal=vPrincipal;
        this.vista.btnDetallesDestino.addActionListener(this);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
    
    if(this.vista.btnDetallesDestino == e.getSource())
    {
        vistaDetallesDestino vDetallesDestino = new vistaDetallesDestino();
        controlDetalleDestino cDetalleDestino = new controlDetalleDestino(vDetallesDestino, vPrincipal);
        CambiaPanel cambiar = new CambiaPanel(vPrincipal.panelCambiante, vDetallesDestino);
    }
    
    }
}
