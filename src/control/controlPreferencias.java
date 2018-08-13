/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.modeloEditarPerfil;
import modelo.modeloPreferencias;
import vistas.vistaEditarPerfil;
import vistas.vistaNuevoViajeSS;
import vistas.vistaPreferencias;
import vistas.vistaPrincipal;

/**
 *
 * @author ITZEL
 */
public class controlPreferencias implements ActionListener
{
    private vistaPreferencias vista;
    private modeloPreferencias modelo;
    private vistaPrincipal vistaPrincipal;
    private int contador=0,
            perate=0,
            contador2=0;
    private int playa=0, 
            desierto=0,
            bosque=0,
            montaña=0,
            jungla=0,
            manglar=0,
            ciudad=0,
            borrar=0,
            bandera=0,
            bandera2=0,
            zonavolcanica=0;
    
    public controlPreferencias(vistaPreferencias vista, vistaPrincipal vistaPrincipal, modeloPreferencias modelo)
    {
        this.vista=vista;
        this.modelo = modelo;
        this.vistaPrincipal=vistaPrincipal;
        
        this.vista.guardar.addActionListener(this);
        this.vista.bosque.addActionListener(this);
        this.vista.ciudad.addActionListener(this);
        this.vista.desierto.addActionListener(this);
        this.vista.jungla.addActionListener(this);
        this.vista.manglar.addActionListener(this);
        this.vista.montaña.addActionListener(this);
        this.vista.playa.addActionListener(this);
        this.vista.zonavolcanica.addActionListener(this);

        if(modelo.preferensias(controlPrincipal.usuario[0]).length > 0)
        {
            String [] string = modelo.preferensias(controlPrincipal.usuario[0]);
            int [] entero = new int [string.length];
            
            for(int i=0; i<string.length; i++)
            { 
                entero[i] = Integer.valueOf(string[i]).intValue();
                if(entero[i] == 1)
                {
                    vista.playa.setSelected(true);
                }
                else if(entero[i] == 2)
                {
                    vista.desierto.setSelected(true);
                }
                else if(entero[i] == 3)
                {
                    vista.bosque.setSelected(true);
                }
                else if(entero[i] == 4)
                {
                    vista.montaña.setSelected(true);
                }
                else if(entero[i] == 9)
                {
                    vista.jungla.setSelected(true);
                }
                else if(entero[i] == 13)
                {
                    vista.manglar.setSelected(true);
                }
                else if(entero[i] == 14)
                {
                    vista.ciudad.setSelected(true);
                }
                else if(entero[i] == 15)
                {
                    vista.zonavolcanica.setSelected(true);
                }
            }
        } 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //Aqui para para validar cuales preferencias vas actualizar y no enviar nuevamente las que ya tenias en tu perfil
        if(vista.playa.isSelected() && vista.playa == e.getSource())
        {
            contador = contador + 1;
            playa = 1;
            System.out.println("playa: " + playa);      
        }     
        else if(vista.desierto.isSelected() && vista.desierto == e.getSource()){
            contador = contador + 1;
            desierto = 2;
            System.out.println("desierto: " + desierto);
        }
        else if(vista.bosque.isSelected() && vista.bosque == e.getSource()){
            contador = contador + 1;
            bosque = 3;
            System.out.println("bosque: " + bosque);
        }
        else if(vista.montaña.isSelected() && vista.montaña == e.getSource()){
            contador = contador + 1;
            montaña = 4;
           System.out.println("montaña: " + montaña);
        }
        else if(vista.jungla.isSelected() && vista.jungla == e.getSource()){
            contador = contador + 1;
            jungla = 5;
            System.out.println("jungla: " + jungla);
        }
        else if(vista.manglar.isSelected() && vista.manglar == e.getSource()){
            contador = contador + 1;
            manglar = 6;
            System.out.println("manglar: " + manglar);
        }
        else if(vista.ciudad.isSelected() && vista.ciudad == e.getSource()){
            contador = contador + 1;
            ciudad = 7;
            System.out.println("ciudad: " + ciudad);
        }
        else if(vista.zonavolcanica.isSelected() && vista.zonavolcanica == e.getSource()){
            contador = contador + 1;
            zonavolcanica = 8;
            System.out.println("zonavolcanica: " + zonavolcanica);
        }     
        if(contador >= 1)
        {
            //Aqui cuando hayas elegido una preferencia y cambiaste quitar esa preferencia
            if(!vista.playa.isSelected() && vista.playa == e.getSource())
            {
                contador = contador - 1;
                playa = -1;
                System.out.println("Se quito playa: " + playa);      
            }
            else if(!vista.desierto.isSelected() && vista.desierto == e.getSource()){
                contador = contador - 1;
                desierto = -2;
                System.out.println("Se quito desierto: " + desierto);
            }
            else if(!vista.bosque.isSelected() && vista.bosque == e.getSource()){
                contador = contador - 1;
                bosque = -3;
                System.out.println("Se quito bosque: " + bosque);
            }
            else if(!vista.montaña.isSelected() && vista.montaña == e.getSource()){
                contador = contador - 1;
                montaña = -4;
               System.out.println("Se quito montaña: " + montaña);
            }
            else if(!vista.jungla.isSelected() && vista.jungla == e.getSource()){
                contador = contador - 1;
                jungla = -5;
                System.out.println("Se quito jungla: " + jungla);
            }
            else if(!vista.manglar.isSelected() && vista.manglar == e.getSource()){
                contador = contador - 1;
                manglar = -6;
                System.out.println("Se quitomanglar: " + manglar);
            }
            else if(!vista.ciudad.isSelected() && vista.ciudad == e.getSource()){
                contador = contador - 1;
                ciudad = -7;
                System.out.println("Se quito ciudad: " + ciudad);
            }
            else if(!vista.zonavolcanica.isSelected() && vista.zonavolcanica == e.getSource()){
                contador = contador - 1;
                zonavolcanica = -8;
                System.out.println("Se quito zonavolcanica: " + zonavolcanica);
            }
        }
        //Aqui para eliminar una preferencia que tengas guardada en tu perfil
        if(!vista.playa.isSelected() && vista.playa == e.getSource())
        {
            contador = contador - 1;
            playa = -1;
            System.out.println("Se quito playa: " + playa);      
        }
        else if(!vista.desierto.isSelected() && vista.desierto == e.getSource()){
            contador = contador - 1;
            desierto = -2;
            System.out.println("Se quito desierto: " + desierto);
        }
        else if(!vista.bosque.isSelected() && vista.bosque == e.getSource()){
            contador = contador - 1;
            bosque = -3;
            System.out.println("Se quito bosque: " + bosque);
        }
        else if(!vista.montaña.isSelected() && vista.montaña == e.getSource()){
            contador = contador - 1;
            montaña = -4;
           System.out.println("Se quito montaña: " + montaña);
        }
        else if(!vista.jungla.isSelected() && vista.jungla == e.getSource()){
            contador = contador - 1;
            jungla = -5;
            System.out.println("Se quito jungla: " + jungla);
        }
        else if(!vista.manglar.isSelected() && vista.manglar == e.getSource()){
            contador = contador - 1;
            manglar = -6;
            System.out.println("Se quitomanglar: " + manglar);
        }
        else if(!vista.ciudad.isSelected() && vista.ciudad == e.getSource()){
            contador = contador - 1;
            ciudad = -7;
            System.out.println("Se quito ciudad: " + ciudad);
        }
        else if(!vista.zonavolcanica.isSelected() && vista.zonavolcanica == e.getSource()){
            contador = contador - 1;
            zonavolcanica = -8;
            System.out.println("Se quito zonavolcanica: " + zonavolcanica);
        }
        //Boton guardar
        if(vista.guardar == e.getSource())
        {   
            System.out.println("Estos inser se haran: " + contador);
            System.out.println("Estos delete se haran: " + contador2);
            System.out.println("Entra agregar");
            if(playa == 1)
            {   
                System.out.println("Se insertara la playa vuelta: ");
                System.out.println("El usuario es: " + controlPrincipal.usuario[0]);
                System.out.println("El id es: " + playa);
                if(modelo.ModificarTipoDesitio(controlPrincipal.usuario[2], 1)){
                    System.out.println("Se insertó playa");
                    bandera = 1;
                }
            }
            if(desierto == 2)
            {
                System.out.println("Se insertara desierto vuelta " );
                System.out.println("El usuario es: " + controlPrincipal.usuario[0]);
                System.out.println("El id es: " + desierto);
                if(modelo.ModificarTipoDesitio(controlPrincipal.usuario[2], 2)){
                    System.out.println("Se insertó desierto");
                    bandera = 1;
                }
            }
            if(bosque == 3)
            {
                System.out.println("Se insertara bosque vuelta " );
                System.out.println("El usuario es: " + controlPrincipal.usuario[0]);
                System.out.println("El id es: " + bosque);
                if(modelo.ModificarTipoDesitio(controlPrincipal.usuario[2], 3)){
                    System.out.println("Se insertó bosque");
                    bandera = 1;
                }
            }
            if(montaña == 4)
            {
                System.out.println("Se insertara montaña vuelta " );
                System.out.println("El usuario es: " + controlPrincipal.usuario[0]);
                System.out.println("El id es: " + montaña);
                if(modelo.ModificarTipoDesitio(controlPrincipal.usuario[2], 4)){
                    System.out.println("Se insertó montaña");
                    bandera = 1;
                }
            }
            if(jungla == 5)
            {
                System.out.println("Se insertara jungla vuelta " );
                System.out.println("El usuario es: " + controlPrincipal.usuario[0]);
                System.out.println("El id es: " + jungla);
                if(modelo.ModificarTipoDesitio(controlPrincipal.usuario[2], 9)){
                    System.out.println("Se insertó jungla");
                    bandera = 1;
                }
            }
            if(manglar == 6)
            {
                System.out.println("Se insertara manglar vuelta " );
                System.out.println("El usuario es: " + controlPrincipal.usuario[0]);
                System.out.println("El id es: " + manglar);
                if(modelo.ModificarTipoDesitio(controlPrincipal.usuario[2], 13)){
                    System.out.println("Se insertó manglar");
                    bandera = 1;
                }
            }
            if(ciudad == 7)
            {
                System.out.println("Se insertara ciudad vuelta " );
                System.out.println("El usuario es: " + controlPrincipal.usuario[0]);
                System.out.println("El id es: " + ciudad);
                if(modelo.ModificarTipoDesitio(controlPrincipal.usuario[2], 14)){
                    System.out.println("Se insertó ciudad");
                    bandera = 1;
                }
            }
            if(zonavolcanica == 8)
            {
                System.out.println("Se insertara zonavolcanica vuelta " );
                System.out.println("El usuario es: " + controlPrincipal.usuario[0]);
                System.out.println("El id es: " + zonavolcanica);
                if(modelo.ModificarTipoDesitio(controlPrincipal.usuario[2], 15)){
                    System.out.println("Se insertó zonavolcanica");
                    bandera = 1;
                }
            }
            if(!vista.playa.isSelected() && !vista.desierto.isSelected() && !vista.bosque.isSelected() && !vista.montaña.isSelected() && !vista.jungla.isSelected() && !vista.manglar.isSelected() && !vista.ciudad.isSelected() && !vista.zonavolcanica.isSelected())
            {
                JOptionPane.showMessageDialog(null, "Selecciona al menos un tipo de sitio", "¡Atención!", JOptionPane.ERROR_MESSAGE);
                bandera2 = 1;
                modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 1);
                        System.out.println("Se borro playa");
                       // bandera = 1;
                modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 2);
                        System.out.println("Se borro desierto");
                       // bandera = 1;
                modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 3);   
                        System.out.println("Se borro bosque");
                       // bandera = 1;
                modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 4);   
                        System.out.println("Se borro montaña");
                        //bandera = 1;
                modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 9);   
                        System.out.println("Se borro jungla");
                        //bandera = 1;
                modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 13);  
                        System.out.println("Se borro manglar");
                        //bandera = 1;
                modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 14);   
                        System.out.println("Se borro ciudad");
                        //bandera = 1;
                modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 15);   
                        System.out.println("Se borro zonavolcanica");
                        //bandera = 1;              
//                 contador=0;
//                 contador2=0;
                perate = 1;
            }
            if(bandera2 == 0)
            {
                if(playa == -1)
                {   
                    System.out.println("Se borrara la playa vuelta: ");
                    if(modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 1))
                    {   
                        System.out.println("Se borro playa");
                        bandera = 1;
                    }
                }
                if(desierto == -2){
                    System.out.println("Se borrara desierto vuelta " );
                    if(modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 2))
                    {   
                        System.out.println("Se borro desierto");
                        bandera = 1;
                    }
                }
                if(bosque == -3){
                    System.out.println("Se borrara bosque vuelta " );
                    if(modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 3))
                    {   
                        System.out.println("Se borro bosque");
                        bandera = 1;
                    }
                }
                if(montaña == -4){
                    System.out.println("Se borrara montaña vuelta " );
                    if(modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 4))
                    {   
                        System.out.println("Se borro montaña");
                        bandera = 1;
                    }
                }
                if(jungla == -5){
                    System.out.println("Se borrara jungla vuelta " );
                    if(modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 9))
                    {   
                        System.out.println("Se borro jungla");
                        bandera = 1;
                    }
                }
                if(manglar == -6){
                    System.out.println("Se borrara manglar vuelta " );
                    if(modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 13))
                    {   
                        System.out.println("Se borro manglar");
                        bandera = 1;
                    }
                }
                if(ciudad == -7){
                    System.out.println("Se borrara ciudad vuelta " );
                    if(modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 14))
                    {   
                        System.out.println("Se borro ciudad");
                        bandera = 1;
                    }
                }
                if(zonavolcanica == -8){
                    System.out.println("Se borrara zonavolcanica vuelta " );
                    if(modelo.BorrarTipoDesitio(controlPrincipal.usuario[2], 15))
                    {   
                        System.out.println("Se borro zonavolcanica");
                        bandera = 1;
                    }
                }
            }
            //este if es para cuando borras un tipo de sitio y le des guardar, se guarde
            if(bandera >= 1)
            {
                System.out.println("guarda desde la bandera");
                JOptionPane.showMessageDialog(null, "Se han guardado tus datos"); 
                vistaEditarPerfil vistaEditarPerfil = new vistaEditarPerfil();
                modeloEditarPerfil modeloEditarPerfil = new modeloEditarPerfil();
                controlEditarPerfil controlEditarPerfil = new controlEditarPerfil(vistaEditarPerfil,vistaPrincipal,modeloEditarPerfil);
                CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vistaEditarPerfil);
           }
            else if(vista.playa == e.getSource() && vista.desierto == e.getSource() && vista.bosque == e.getSource() && vista.montaña == e.getSource() && vista.jungla == e.getSource() && vista.manglar == e.getSource() && vista.ciudad == e.getSource() && vista.zonavolcanica == e.getSource())
            {
                //este if es para si no se le pico a nada entre al else if que sigue y poder guardar tus datos
            }
            else if(perate == 0)
            {
                System.out.println("Guarda desde el else");
                JOptionPane.showMessageDialog(null, "Se han guardado tus datos"); 
                vistaEditarPerfil vistaEditarPerfil = new vistaEditarPerfil();
                modeloEditarPerfil modeloEditarPerfil = new modeloEditarPerfil();
                controlEditarPerfil controlEditarPerfil = new controlEditarPerfil(vistaEditarPerfil,vistaPrincipal,modeloEditarPerfil);
                CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vistaEditarPerfil);
            } 
        }
    }
}
