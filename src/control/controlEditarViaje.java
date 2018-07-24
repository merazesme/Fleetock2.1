/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.modeloEditarViaje;
import vistas.vistaEditarViaje;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlEditarViaje implements ActionListener{

    private vistaEditarViaje vista;
    private vistaPrincipal vistaPrincipal;
    private modeloEditarViaje modelo;
    private String idV;
    
    public controlEditarViaje(vistaEditarViaje vista, vistaPrincipal vistaPrincipal, modeloEditarViaje modelo, String idV)
    {
        this.vista=vista;
        this.vistaPrincipal=vistaPrincipal;
        this.modelo=modelo;
        this.idV=idV;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
    
    }
    
}
