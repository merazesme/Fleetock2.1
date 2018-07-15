/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.administrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.administrador.avistaActividades;
import vistas.administrador.avistaMenu;

/**
 *
 * @author Holi
 */
public class acontrolActividades implements ActionListener{

    avistaActividades vista;
    avistaMenu vMenu;
    
    public acontrolActividades(avistaActividades avActividades, avistaMenu avMenu)
    {
        this.vista=avActividades;
        this.vMenu=avMenu;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
    }
    
}
