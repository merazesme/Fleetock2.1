/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.vistaAgregarViaje;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlAgregarViaje implements ActionListener{

    
    vistaAgregarViaje vista;
    vistaPrincipal vPrincipal;
    
    public controlAgregarViaje(vistaAgregarViaje vista, vistaPrincipal vPrincipal)
    {
        this.vista=vista;
        this.vPrincipal=vPrincipal;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    
        
    
    }
    
}
