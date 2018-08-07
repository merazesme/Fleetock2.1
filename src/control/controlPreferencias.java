/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.vistaNuevoViajeSS;
import vistas.vistaPreferencias;
import vistas.vistaPrincipal;

/**
 *
 * @author ITZEL
 */
public class controlPreferencias implements ActionListener{
    private vistaPreferencias vista;
    private vistaPrincipal vistaPrincipal;
    
    public controlPreferencias(vistaPreferencias vista, vistaPrincipal vistaPrincipal)
    {
        this.vista=vista;
        this.vistaPrincipal=vistaPrincipal;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    
    
    }
}
