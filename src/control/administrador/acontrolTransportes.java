/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.administrador;

import vistas.administrador.avistaMenu;
import vistas.administrador.avistaTransporte;

/**
 *
 * @author Holi
 */
public class acontrolTransportes {
    
    avistaTransporte vista;
    avistaMenu avMenu;
    
    public acontrolTransportes(avistaTransporte avTransporte, avistaMenu avMenu)
    {
        this.vista=avTransporte;
        this.avMenu=avMenu;
    }
    
}
