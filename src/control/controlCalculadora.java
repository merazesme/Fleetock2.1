/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.vistaCalculadora;
import vistas.vistaPerfil;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlCalculadora implements ActionListener{

    vistaCalculadora vista;
    vistaPrincipal vistaPrincipal;
    public controlCalculadora(vistaCalculadora vista, vistaPrincipal vistaPrincipal)
    {
        this.vista=vista;
        this.vistaPrincipal=vistaPrincipal;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
    
}
