/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.vistaEditarViaje;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlEditarViaje implements ActionListener{

    vistaEditarViaje vista;
    vistaPrincipal vistaPrincipal;
    
    public controlEditarViaje(vistaEditarViaje vista, vistaPrincipal vistaPrincipal)
    {
        this.vista=vista;
        this.vistaPrincipal=vistaPrincipal;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
    
    }
    
}
