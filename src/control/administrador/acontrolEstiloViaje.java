/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.administrador;

import vistas.administrador.avistaEstiloViaje;
import vistas.administrador.avistaMenu;

/**
 *
 * @author Holi
 */
public class acontrolEstiloViaje {
    
    avistaEstiloViaje vista;
    avistaMenu avMenu;
    
    public acontrolEstiloViaje(avistaEstiloViaje avEstiloViaje, avistaMenu avMenu)
    {
        this.vista=avEstiloViaje;
        this.avMenu=avMenu;
    }
}
