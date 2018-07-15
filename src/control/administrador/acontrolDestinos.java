/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.administrador;

import vistas.administrador.avistaDestino;
import vistas.administrador.avistaMenu;

/**
 *
 * @author Holi
 */
public class acontrolDestinos {
    
    avistaDestino vista;
    avistaMenu vMenu;
    
    public acontrolDestinos(avistaDestino avdDestino, avistaMenu avMenu)
    {
        this.vista= avdDestino;
        this.vMenu=avMenu;
    }
    
}
