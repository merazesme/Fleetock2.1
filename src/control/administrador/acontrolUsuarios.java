/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.administrador;

import vistas.administrador.avistaMenu;
import vistas.administrador.avistaUsuarios;

/**
 *
 * @author Holi
 */
public class acontrolUsuarios {
    
    avistaUsuarios vista;
    avistaMenu avMenu;
    
    public acontrolUsuarios(avistaUsuarios avUsuarios, avistaMenu avMenu)
    {
        this.vista=avUsuarios;
        this.avMenu=avMenu;
    }
    
}
