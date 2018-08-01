/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import modelo.modeloActividadComentarios;
import modelo.modeloActividades;
import vistas.vistaActividadComentarios;
import vistas.vistaActividades;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlActividades implements ActionListener, KeyListener{

    private vistaActividades vista;
    private vistaPrincipal vPrincipal;
    private modeloActividades modelo;
    private String idD;
    private JButton btnImagen, btnNuevoViaje;
    
    public controlActividades(vistaActividades vista, vistaPrincipal vPrincipal, modeloActividades modelo, String idD)
    {
        this.vista=vista;
        this.vPrincipal=vPrincipal;
        this.modelo=modelo;
        this.idD=idD;
        this.vista.txtBusqueda.addKeyListener(this);
        act(modelo.datosActividades(idD), vista.pnlBusqueda);
        vista.pnlBusqueda.setBorder(new EmptyBorder(5, 40, 0, 0));
    }
    
    public void act(String [][] act, JPanel p){
        //limpia el panel
        p.removeAll();
        p.revalidate();
        p.repaint();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        if(act.length > 0){
            for(int i=0; i<act.length; i++){
                //Panel principal: verde
                JPanel principal = new JPanel();
                principal.setLayout(new GridLayout( 2, 1, 0, 0));
                principal.setBackground(new java.awt.Color(156,255,87));
                principal.setSize(250,250);
                
                //Imagen
                ImageIcon image = new ImageIcon(getClass().getResource("../images/icons8-billete-con-estrella-100.png"));
                if(act[i][2] != null){
                    image = new ImageIcon(act[i][2]);
                }
                
                Icon fondo = new ImageIcon(image.getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT));
                btnImagen = new JButton(fondo);
                
                    //Para hacerlo invisible
                btnImagen.setBorderPainted(false);
                btnImagen.setContentAreaFilled(false);
                btnImagen.setDefaultCapable(false);
                btnImagen.setFocusPainted(false);
                btnImagen.setFocusable(false);
                btnImagen.setSize(250, 150);
                btnImagen.addActionListener(this);               
                
                //Panel de información
                JPanel informacion = new JPanel(new BorderLayout(0, -70));
                informacion.setBackground(new java.awt.Color(156,255,87));
                    //Nombre del destino
                JLabel nombreD = new JLabel(act[i][1]);
                nombreD.setFont(new Font("Candara", Font.PLAIN, 14));
                nombreD.setBorder(new EmptyBorder(10, 5, 0, 0));
                nombreD.setSize(250, 15);
                

                //id de la actividad
                btnImagen.setName("A"+act[i][0]);
                btnImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnImagen.setToolTipText("Ver Actividad");

                 //Calificación
                JPanel cal = new JPanel();
                cal.setSize(250, 50);
                cal.setLayout(new BoxLayout(cal, BoxLayout.X_AXIS));
                cal.setBackground(new java.awt.Color(156,255,87));
                cal.setBorder(new EmptyBorder(10, 5, 0, 0));

                    //si tiene calificación que cree los radiobutton              
                if(modelo.actividadCal(act[i][0]) != null){
                    JRadioButton r1 = new JRadioButton();
                    JRadioButton r2 = new JRadioButton();
                    JRadioButton r3 = new JRadioButton();
                    JRadioButton r4 = new JRadioButton();
                    JRadioButton r5 = new JRadioButton();

                    r1.setBackground(new java.awt.Color(156,255,87));
                    r2.setBackground(new java.awt.Color(156,255,87));
                    r3.setBackground(new java.awt.Color(156,255,87));
                    r4.setBackground(new java.awt.Color(156,255,87));
                    r5.setBackground(new java.awt.Color(156,255,87));

                    r1.setEnabled(false);
                    r2.setEnabled(false);
                    r3.setEnabled(false);
                    r4.setEnabled(false);
                    r5.setEnabled(false);

                    cal.add(r1);
                    cal.add(r2);
                    cal.add(r3);
                    cal.add(r4);
                    cal.add(r5);
                        //Rellenar los radiobutton
                    double c = Double.parseDouble(modelo.actividadCal(act[i][0]));
                    if(c<=1.99){
                        r1.setSelected(true);
                    }
                    else if(c<=2.99){
                        r1.setSelected(true);
                        r2.setSelected(true);
                    }
                    else if(c<=3.99){
                        r1.setSelected(true);
                        r2.setSelected(true);
                        r3.setSelected(true);
                    }
                    else if(c<=4.99){
                        r1.setSelected(true);
                        r2.setSelected(true);
                        r3.setSelected(true);
                        r4.setSelected(true);
                    }
                    else{
                        r1.setSelected(true);
                        r2.setSelected(true);
                        r3.setSelected(true);
                        r4.setSelected(true);
                        r5.setSelected(true);
                    }
                }
                    //Si no tiene calificación que aparezca el mensaje
                else{
                    JLabel mensajec = new JLabel("¡Sé el primero en calificarlo!");
                    mensajec.setForeground(new Color(76,2,131));
                    mensajec.setFont(new Font("Candara", Font.PLAIN, 14));
                    mensajec.setHorizontalAlignment(SwingConstants.LEFT);
                    mensajec.setBorder(new EmptyBorder(10, 5, 0, 0));
                    mensajec.setSize(250,50);
                    cal.add(mensajec);
                }
                informacion.add(nombreD, BorderLayout.NORTH); 
                informacion.add(cal, BorderLayout.CENTER);
                informacion.setSize(250,80);
                
                //Agregar la imagen y la información
                principal.add(btnImagen);
                principal.add(informacion);
                //Agregar el panel principal al scroll
                p.add(principal);
                //separación en blanco
                JPanel separacion = new JPanel();
                separacion.setBackground(new java.awt.Color(255,255,255));
                separacion.setSize(5,5);
                p.add(separacion);
                principal.setPreferredSize(new Dimension(250, 250));
            }
        }
        else
        {
            //Si no se encuentra 
            JLabel mensaje = new JLabel("No se ha encontrado nada :(");
            mensaje.setForeground(new Color(76,2,131));
            mensaje.setFont(new Font("Candara", Font.PLAIN, 14));
            p.add(mensaje);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            JComponent selectedButton = (JComponent) e.getSource();
            String letra = selectedButton.getName().substring(0, 1);  
            String idA = selectedButton.getName().substring(1);
            //Botón de detalles de actividad
            if(letra.equals("A")){
                vistaActividadComentarios vActividadComentarios = new vistaActividadComentarios();
                modeloActividadComentarios mActividadComentarios = new modeloActividadComentarios();
                controlActividadComentarios comentarios = new controlActividadComentarios(vActividadComentarios, vPrincipal, mActividadComentarios, idA, idD);
                CambiaPanel cambiar = new CambiaPanel(vPrincipal.panelCambiante, vActividadComentarios);
            }
        }
        catch(NullPointerException ex){}    
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!vista.txtBusqueda.getText().equals("")){
            act(modelo.datosBusqueda(vista.txtBusqueda.getText(), idD) , vista.pnlBusqueda);
            vista.txtBusqueda.requestFocus();
        } 
        else{
           act(modelo.datosActividades(idD), vista.pnlBusqueda);
        }
    }
    
}
