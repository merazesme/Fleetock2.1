/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.modeloActividadComentarios;
import vistas.vistaActividadComentarios;
import vistas.vistaActividades;
import vistas.vistaPerfil;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlActividades implements ActionListener{

    private vistaActividades vista;
    private vistaPrincipal vPrincipal;
    private String idD;
    
    public controlActividades(vistaActividades vista, vistaPrincipal vPrincipal, String idD)
    {
        this.vista=vista;
        this.vPrincipal=vPrincipal;
        this.vista.btnComentarios.addActionListener(this);
        this.idD=idD;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String idA="";
        
        if(this.vista.btnComentarios == e.getSource())
        {
            vistaActividadComentarios vActividadComentarios = new vistaActividadComentarios();
            modeloActividadComentarios mActividadComentarios = new modeloActividadComentarios();
            controlActividadComentarios comentarios = new controlActividadComentarios(vActividadComentarios, vPrincipal, mActividadComentarios, idA);
            CambiaPanel cambiar = new CambiaPanel(vPrincipal.panelCambiante, vActividadComentarios);
        }
    
    }
    
}
