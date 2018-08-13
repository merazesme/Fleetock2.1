/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fleetock;


import control.administrador.acontrolMenu;
import control.controlLogin;
import control.controlLoginRegistrarse;
import vistas.administrador.avistaMenu;
import vistas.vistaDetallesDestino;
import vistas.vistaLoginRegistrarse;
import vistas.vistaPrincipal;

/**
 *
 * @author LOS MAS GUAPOS♥
 */
public class Fleetock 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        vistaLoginRegistrarse vista = new vistaLoginRegistrarse();
        controlLoginRegistrarse control = new controlLoginRegistrarse(vista);
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    }
}
