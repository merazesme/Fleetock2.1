/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import modelo.modeloActividadComentarios;
import vistas.vistaActividadComentarios;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlActividadComentarios {
    
    private vistaActividadComentarios vista;
    private vistaPrincipal vPrincipal;
    private modeloActividadComentarios modelo;
    private String idA; 
       
    public controlActividadComentarios(vistaActividadComentarios vActividadComentarios, vistaPrincipal vPrincipal, modeloActividadComentarios modelo, String idA)
    {
        this.vista=vActividadComentarios;
        this.vPrincipal=vPrincipal;
        this.modelo=modelo;
        this.idA=idA;
        imagenN(idA);
    }
    
    public void imagenN(String idA){
        System.out.println("función");
        String [] act;
        act=modelo.datosActividades(idA);
        if(act!=null){
            System.out.println("condicion");
            vista.lblNombre.setText(act[0]);
            ImageIcon imagen = new ImageIcon(act[1]);
            Icon fondo = new ImageIcon(imagen.getImage().getScaledInstance(827, 500, Image.SCALE_DEFAULT));
            vista.lblImagen.setIcon(fondo);
            vista.lblDescripcion.setText(act[2]);
            vista.lblLocalizacion.setText(act[3]);
        }
    }
}
