/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.vistaActividadComentarios;
import vistas.vistaActividades;
import vistas.vistaPerfil;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlActividades implements ActionListener{

    vistaActividades vista;
    vistaPrincipal vPrincipal;
    
    public controlActividades(vistaActividades vista, vistaPrincipal vPrincipal)
    {
        this.vista=vista;
        this.vPrincipal=vPrincipal;
        this.vista.btnComentarios.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
        if(this.vista.btnComentarios == e.getSource())
        {
            vistaActividadComentarios vActividadComentarios = new vistaActividadComentarios();
            controlActividadComentarios comentarios = new controlActividadComentarios(vActividadComentarios, vPrincipal);
            CambiaPanel cambiar = new CambiaPanel(vPrincipal.panelCambiante, vActividadComentarios);
        }
    
    }
    
}
